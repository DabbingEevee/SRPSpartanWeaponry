package com.existingeevee.swparasites.items;

import com.existingeevee.swparasites.Utils;
import com.oblivioussp.spartanshields.item.ItemShieldBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemImpalerShield extends ItemShieldBase {

	float attackDamage = 15;
	float falloffPerBlock = 3; // radians
	double maxRange = 5;
	double maxDeltaAngle = Math.PI / 4; // radians
	float power = 1;

	public ItemImpalerShield(String unlocName, int maxDurability, float damageLevel, float powerLevel, float rangeLevel) {
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

		Vec3d vec = playerIn.getLookVec();

		playerIn.motionX += vec.x * 2.0 * power;
		playerIn.motionZ += vec.z * 2.0 * power;
		playerIn.velocityChanged = true;

		AxisAlignedBB box = new AxisAlignedBB(playerIn.posX, playerIn.posY, playerIn.posZ, playerIn.posX, playerIn.posY, playerIn.posZ).grow(maxRange);
		for (Entity entity : worldIn.getEntitiesInAABBexcluding(playerIn, box, e -> isValidTarget(e, playerIn))) {
			
				//Deal the proper damage
				Vec3d eyePos = playerIn.getPositionEyes(0.5f);
				Vec3d targetCenterPos = Utils.getCenter(entity.getEntityBoundingBox());
				float distanceTo = (float) eyePos.distanceTo(targetCenterPos);
				//entity.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), attackDamage - distanceTo * falloffPerBlock);
				entity.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), attackDamage);
			
				Vec3d motionVector = new Vec3d(entity.posX - playerIn.posX, 0, entity.posZ - playerIn.posZ).normalize()
						.scale(1.5) //1.5 m/s of initial push
						.scale(1 - distanceTo / (maxRange * 2)) //put some falloff on it
						.add(0, 0.6D, 0); //bit of an upwards push as well bc why not

			
				if (!worldIn.isRemote) {
					entity.motionX += motionVector.x;
					entity.motionY += motionVector.y * 0.5;
					entity.motionZ += motionVector.z;
					entity.velocityChanged = true;
				}
				playerIn.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20, 4));
				item.damageItem(2, playerIn);
			}
			playerIn.getCooldownTracker().setCooldown(item.getItem(), 50);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
		}



	private boolean isValidTarget(Entity target, EntityPlayer player) {
		if (target == null || player == null)
			return false;

		Vec3d eyePos = player.getPositionEyes(0.5f);
		Vec3d targetCenterPos = Utils.getCenter(target.getEntityBoundingBox());

		Vec3d lookVec = player.getLookVec();
		Vec3d targetDirection = targetCenterPos.subtract(eyePos).normalize();

		double deltaAngle = Math.abs(Math.acos(lookVec.dotProduct(targetDirection)));

		if (deltaAngle > maxDeltaAngle) //Too far off the angle. nope.
			return false;

		RayTraceResult result = Utils.rayTrace(eyePos, targetDirection, player.world, this.maxRange, e -> e != target, true, true);
	
		if (result.entityHit != target) //Too far away or obstructed by blocks. nope
			return false;
		
		return target instanceof EntityLivingBase; //they have to be living smhhh
	}
}