package com.existingeevee.swparasites.items;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.existingeevee.swparasites.Utils;
import com.existingeevee.swparasites.config.ParasiteSWConfig;
import com.existingeevee.swparasites.event.EvolutionHandler;
import com.oblivioussp.spartanshields.item.ItemShieldBase;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemImpalerShield extends ItemShieldBase {

	float attackDamage = 15;
	float falloffPerBlock = 3; // radians
	double maxRange = 5;
	double maxDeltaAngle = Math.PI / 4; // radians
	float power = 1;

	public ItemImpalerShield(String unlocName, int maxDurability, float damageLevel, float powerLevel,
			float rangeLevel) {
		super(unlocName);

		this.attackDamage = attackDamage * damageLevel;
		this.power = power * powerLevel;
		this.maxRange = maxRange * rangeLevel;
		this.setMaxDamage(maxDurability);

		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		if (!playerIn.isSneaking() || !playerIn.onGround) {
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
		if (!worldIn.isRemote) {
			playerIn.world.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.7F, power == 1 ? 1.25f : 0.75f); // ev dont you dare say it
		}

		Vec3d vec = playerIn.getLookVec();

		playerIn.motionX += vec.x * 2.0 * power;
		playerIn.motionZ += vec.z * 2.0 * power;
		playerIn.velocityChanged = true;

		AxisAlignedBB box = new AxisAlignedBB(playerIn.posX, playerIn.posY, playerIn.posZ, playerIn.posX, playerIn.posY,
				playerIn.posZ).grow(maxRange);
		for (Entity entity : worldIn.getEntitiesInAABBexcluding(playerIn, box, e -> isValidTarget(e, playerIn))) {

			// Deal the proper damage
			Vec3d eyePos = playerIn.getPositionEyes(0.5f);
			Vec3d targetCenterPos = Utils.getCenter(entity.getEntityBoundingBox());
			float distanceTo = (float) eyePos.distanceTo(targetCenterPos);
			// entity.attackEntityFrom(DamageSource.causePlayerDamage(playerIn),
			// attackDamage - distanceTo * falloffPerBlock);
			entity.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), attackDamage);

			Vec3d motionVector = new Vec3d(entity.posX - playerIn.posX, 0, entity.posZ - playerIn.posZ).normalize()
					.scale(1.5) // 1.5 m/s of initial push
					.scale(1 - distanceTo / (maxRange * 2)) // put some falloff on it
					.add(0, 0.6D, 0); // bit of an upwards push as well bc why not

			if (!worldIn.isRemote) {
				entity.motionX += motionVector.x;
				entity.motionY += motionVector.y * 0.5;
				entity.motionZ += motionVector.z;
				entity.velocityChanged = true;
				entity.world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ITEM_SHIELD_BLOCK,
						SoundCategory.PLAYERS, 0.7F, 1.0F);
			}
			playerIn.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20, 4));
			item.damageItem(1, playerIn);
			System.out.println("target hit");
			if (((EntityLivingBase) entity).getHealth() <= 0.0f) {
				System.out.println("target dead, increased srpkills");
				add(item, (int) ((EntityLivingBase) entity).getMaxHealth());
			}
		}
		playerIn.getCooldownTracker().setCooldown(item.getItem(), 50);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
	}

	public static void add(ItemStack stack, int amount) {
		NBTTagCompound compound = stack.getTagCompound();
		if (compound == null) {
			compound = new NBTTagCompound();
		}
		if (compound.hasKey("srpkills")) {
			final int key = (int) (compound.getInteger("srpkills") + amount);
			compound.setInteger("srpkills", key);
		} else {
			compound.setInteger("srpkills", amount);
		}
		stack.setTagCompound(compound);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void attackEvent(LivingAttackEvent ev) {
		if (ev.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) ev.getEntityLiving();

			if (player.isHandActive() && !player.getActiveItemStack().isEmpty()) {
				ItemStack activeStack = player.getActiveItemStack();

				if (activeStack.getItem() == this && player.canBlockDamageSource(ev.getSource())) {
					// Yaya woo we blocked it
					if (power == 1) {
						NBTTagCompound compound = activeStack.getTagCompound();
						if (compound == null) {
							compound = new NBTTagCompound();
						}
						if (compound.hasKey("srpkills")) {
							final int key = (int) (compound.getInteger("srpkills") + ev.getAmount());
							compound.setInteger("srpkills", key);
						} else {
							compound.setInteger("srpkills", (int) ev.getAmount() / 2); // Blocking dmg still builds kills, but less than the buckler
						}
						activeStack.setTagCompound(compound);

					}
				}
			}
		}
	}

	private boolean isValidTarget(Entity target, EntityPlayer player) {
		if (target == null || player == null)
			return false;

		Vec3d eyePos = player.getPositionEyes(0.5f);
		Vec3d targetCenterPos = Utils.getCenter(target.getEntityBoundingBox());

		Vec3d lookVec = player.getLookVec();
		Vec3d targetDirection = targetCenterPos.subtract(eyePos).normalize();

		double deltaAngle = Math.abs(Math.acos(lookVec.dotProduct(targetDirection)));

		if (deltaAngle > maxDeltaAngle) // Too far off the angle. nope.
			return false;

		RayTraceResult result = Utils.rayTrace(eyePos, targetDirection, player.world, this.maxRange, e -> e != target,
				true, true);

		if (result.entityHit != target) // Too far away or obstructed by blocks. nope
			return false;

		return target instanceof EntityLivingBase; // they have to be living smhhh
	}
	
	@Override
	public void onItemUpdate(ToolMaterialEx material, ItemStack stack, World world, EntityLivingBase entity,
			int itemSlot, boolean isSelected) {
		System.out.println("yep, item update works");
		if (!world.isRemote) {
			System.out.println("she world on my remote til i :3");
			if (ParasiteSWConfig.sentientScent && power == 1.5 && SRPConfigSystems.useScent && world.rand.nextInt(100) == 0 && entity.ticksExisted % 40 == 0) {
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(SRPPotions.PREY_E, 1200, 0, false, false));
				System.out.println("yep, prey is working");
			}
			if (entity.ticksExisted % 80 == 0) {
				System.out.println("an evolution check should have happened");
				int key = 0;
				final NBTTagCompound compound = stack.getTagCompound();
				if (compound != null && EvolutionHandler.getEvolved(stack.getItem()) != null) {
					if (compound.hasKey("srpkills")) {
						key = compound.getInteger("srpkills");
					}
					if (key > SRPConfig.weapon_livingSentient_HP_needed) {
						System.out.println("evolution should have happened, if it didnt, code broked :(");
						compound.setInteger("srpkills", 0);
						final ItemStack stackW = new ItemStack(EvolutionHandler.getEvolved(stack.getItem()), 1);
						if (ParasiteSWConfig.evolutionKeepNBT) {
							stackW.setTagCompound(compound.copy());
						}
						final EntityItem entityitem = new EntityItem(world, entity.posX, entity.posY, entity.posZ,
								stackW);
						if (ParasiteSWConfig.evolutionDropOnGround) {
							entityitem.setDefaultPickupDelay();
						} else {
							entityitem.setNoPickupDelay();
						}
						world.spawnEntity((Entity) entityitem);
						stack.shrink(1);
						if (SRPConfig.thunderEnable) {
							world.addWeatherEffect((Entity) new EntityLightningBolt(world, entity.posX, entity.posY,
									entity.posZ, true));
						}
					}
				}
			}
		}
	}
}