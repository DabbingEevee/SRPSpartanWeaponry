package com.existingeevee.swparasites.items;

import com.oblivioussp.spartanweaponry.api.IWeaponCallback;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBolt;
import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.init.SoundRegistry;
import com.oblivioussp.spartanweaponry.item.ItemBolt;
import com.oblivioussp.spartanweaponry.item.ItemCrossbow;
import com.oblivioussp.spartanweaponry.util.NBTHelper;
import com.oblivioussp.spartanweaponry.util.Quaternion;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemParasiteCrossbow extends ItemCrossbow {

	public static final String NBT_MULTI_REMAINING = "multiRemaining";
	public static final String NBT_MULTI_COOLDOWN = "multiCooldown";
	public static final String NBT_MULTI_ARROW = "multiArrow";
	public static final String NBT_MULTI_ACC = "multiAcc";

	int multiAmount = 0;

	public ItemParasiteCrossbow(String unlocName, String externalModId, ToolMaterialEx material, IWeaponCallback weaponCallback) {
		super(unlocName, externalModId, material, weaponCallback);
	}

	public ItemParasiteCrossbow(String unlocName, String externalModId, ToolMaterialEx material) {
		super(unlocName, externalModId, material);
	}

	public ItemParasiteCrossbow(String unlocName, ToolMaterialEx toolMaterial) {
		super(unlocName, toolMaterial);
	}

	public ItemParasiteCrossbow withMultiAmount(int newAmount) {
		this.multiAmount = newAmount;
		return this;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (NBTHelper.getInteger(stack, NBT_MULTI_REMAINING) <= 0)
			return super.onItemUseFinish(stack, worldIn, entityLiving);
		return stack;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;

			ItemStack ammoStack = ItemStack.EMPTY;
			NBTTagCompound tag = NBTHelper.getTagCompound(stack, nbtAmmoStack);
			if (tag != null)
				ammoStack = new ItemStack(tag);

			int i = getMaxItemUseDuration(stack) - timeLeft;

			if (i < 0 || !NBTHelper.getBoolean(stack, NBT_IS_LOADED))
				return;

			if (!ammoStack.isEmpty() || flag) {
				if (ammoStack.isEmpty()) {
					ammoStack = new ItemStack(ItemRegistrySW.bolt);
				}

				float vel = getBoltSpeed();

				boolean flag1 = entityplayer.capabilities.isCreativeMode || (ammoStack.getItem() instanceof ItemBolt ? ((ItemBolt) ammoStack.getItem()).isInfinite(ammoStack, stack, entityplayer) : false);

				if (!worldIn.isRemote) {
					ItemBolt itemBolt = ((ItemBolt) (ammoStack.getItem() instanceof ItemBolt ? ammoStack.getItem() : ItemRegistrySW.bolt));

					// Account for lack of accuracy.
					int aimTicks = getAimTicks(stack);
					int inaccuracy = aimTicks - i;
					float inaccuracyModifier = 0.0f;
					if (i >= aimTicks)
						inaccuracy = 0; // Max accuracy

					if (inaccuracy != 0) { // Apply inaccuracy if there is any.
						inaccuracyModifier = 10.0f * ((float) inaccuracy / aimTicks);
					}

					this.attemptFire(stack, ammoStack, itemBolt, worldIn, entityplayer, flag1, inaccuracyModifier);

					int damage = ammoStack.getCount() > 1 ? 3 : 1;
					stack.damageItem(damage, entityplayer);

					NBTHelper.setBoolean(stack, NBT_IS_LOADED, false);
					NBTHelper.setTagCompound(stack, nbtAmmoStack, new NBTTagCompound());
				}

				worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundRegistry.CROSSBOW_FIRE, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + vel * 0.5F);

				entityplayer.addStat(StatList.getObjectUseStats(this));
			}
		}
	}

	@Override
	protected void spawnProjectile(ItemStack crossbow, ItemBolt boltItem, ItemStack boltStack, World world, EntityPlayer player, boolean noPickup, float inaccuracyModifier, float projectileAngle) {
		EntityBolt bolt = boltItem.createBolt(world, boltStack, player);
		bolt.setIsCritical(true);

		Vec3d lookVec = player.getLook(1.0f);
		Vec3d vector = new Vec3d(lookVec.x, lookVec.y, lookVec.z);

		if (projectileAngle != 0.0f) {
			Vec3d playerUpVec = calculateEntityViewVector(player.rotationPitch - 90.0f, player.rotationYaw);
			Quaternion quat = new Quaternion(playerUpVec, projectileAngle, true);
			vector = quat.transformVector(lookVec);
		}

		bolt.shoot(vector.x, vector.y, vector.z, getBoltSpeed() * 3.0f, inaccuracyModifier);

		int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, crossbow);

		if (j > 0) {
			bolt.setDamage(bolt.getDamage() + j * 0.5D + 0.5D);
		}

		int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, crossbow);

		if (k > 0) {
			bolt.setKnockbackStrength(k);
		}

		if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, crossbow) > 0) {
			bolt.setFire(100);
		}

		if (noPickup || projectileAngle != 0.0f) {
			bolt.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
		}

		bolt.getTags().add("ParasiteCrossbowMultifire");
		
		world.spawnEntity(bolt);
	}

	public void attemptFire(ItemStack stack, ItemStack ammoStack, ItemBolt itemBolt, World worldIn, EntityPlayer player, boolean flag1, float inaccuracyModifier) {
		spawnProjectile(stack, itemBolt, ammoStack, worldIn, player, flag1, inaccuracyModifier, 0.0f);
		if (ammoStack.getCount() > 1) {
			spawnProjectile(stack, itemBolt, ammoStack, worldIn, player, true, inaccuracyModifier, -10.0f);
			spawnProjectile(stack, itemBolt, ammoStack, worldIn, player, true, inaccuracyModifier, 10.0f);
		}
		NBTHelper.setInteger(stack, NBT_MULTI_COOLDOWN, 3);
	}

	public void initMulti(ItemStack stack, ItemStack ammoStack, float accMod) {
		NBTHelper.setInteger(stack, NBT_MULTI_REMAINING, multiAmount);
		
		ItemStack boltToStore = ammoStack.copy();
		NBTTagCompound nbtBolt = new NBTTagCompound();
		boltToStore.writeToNBT(nbtBolt);
		
		NBTHelper.setTagCompound(stack, NBT_MULTI_ARROW, nbtBolt);
		NBTHelper.setFloat(stack, NBT_MULTI_ACC, accMod);
		
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		int multiAmount = NBTHelper.getInteger(stack, NBT_MULTI_REMAINING);
		if (multiAmount > 0 && entityIn instanceof EntityPlayer) {
			int cooldown = NBTHelper.getInteger(stack, NBT_MULTI_COOLDOWN);
			if (cooldown > 0) {
				NBTHelper.setInteger(stack, NBT_MULTI_COOLDOWN, cooldown - 1);
			} else {
				float accMod = NBTHelper.getFloat(stack, NBT_MULTI_ACC);
				
				ItemStack ammoStack = ItemStack.EMPTY;
				NBTTagCompound tag = NBTHelper.getTagCompound(stack, nbtAmmoStack);
				if (tag != null)
					ammoStack = new ItemStack(tag);
				ItemBolt itemBolt = ((ItemBolt) (ammoStack.getItem() instanceof ItemBolt ? ammoStack.getItem() : ItemRegistrySW.bolt));

				this.attemptFire(stack, ammoStack, itemBolt, worldIn, (EntityPlayer) entityIn, true, accMod);
				
				NBTHelper.setInteger(stack, NBT_MULTI_REMAINING, multiAmount - 1);
			}
		}
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	private Vec3d calculateEntityViewVector(float pitch, float yaw) {
		float degToRad = (2.0f * (float) Math.PI) / 360.0f;
		float yawCos = MathHelper.cos(-yaw * degToRad - (float) Math.PI);
		float yawSin = MathHelper.sin(-yaw * degToRad - (float) Math.PI);
		float pitchCos = -MathHelper.cos(-pitch * degToRad - (float) Math.PI);
		float pitchSin = MathHelper.sin(-pitch * degToRad - (float) Math.PI);

		return new Vec3d((double) (yawSin * pitchCos), (double) pitchSin, (double) (yawCos * pitchCos));
	}
}
