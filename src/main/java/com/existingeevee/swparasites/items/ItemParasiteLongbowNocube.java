package com.existingeevee.swparasites.items;

import javax.annotation.Nullable;

import com.oblivioussp.spartanweaponry.api.IWeaponCallback;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityArrowBase;
import com.oblivioussp.spartanweaponry.item.ItemArrowSW;
import com.oblivioussp.spartanweaponry.item.ItemLongbow;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class ItemParasiteLongbowNocube extends ItemLongbow {

	public ItemParasiteLongbowNocube(String unlocName, String externalModId, ToolMaterialEx material,
			IWeaponCallback weaponCallback) {
		super(unlocName, externalModId, material, weaponCallback);
	}

	public ItemParasiteLongbowNocube(String unlocName, String externalModId, ToolMaterialEx material) {
		super(unlocName, externalModId, material);
	}

	public ItemParasiteLongbowNocube(String unlocName, ToolMaterialEx toolMaterial) {
		super(unlocName, toolMaterial);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {

			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			boolean flag = entityplayer.capabilities.isCreativeMode
					|| EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack itemstack = findAmmo(entityplayer);

			int i = getMaxItemUseDuration(stack) - timeLeft;
			i = ForgeEventFactory.onArrowLoose(stack, worldIn, (EntityPlayer) entityLiving, i,
					itemstack != null || flag);
			if (i < 0)
				return;

			if (!itemstack.isEmpty() || flag) {
				if (itemstack.isEmpty())
					itemstack = new ItemStack(Items.ARROW);

				float f = getArrowSpeed(i);

				if (f >= 0.1D) {
					boolean flag1 = entityplayer.capabilities.isCreativeMode
							|| (itemstack.getItem() instanceof ItemArrow
									? ((ItemArrow) itemstack.getItem()).isInfinite(itemstack, stack, entityplayer)
									: false);

					if (!worldIn.isRemote) {
						ItemArrow itemarrow = ((ItemArrow) (itemstack.getItem() instanceof ItemArrow
								? itemstack.getItem()
								: Items.ARROW));
						EntityArrow entityarrow = itemarrow.createArrow(worldIn, itemstack, entityplayer);
						entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F,
								f * 3.0F, 0.5F);
						// entityarrow.setDamage(entityarrow.getDamage() * 1.25f);

//                        if (f == maxVelocity)

						System.out.println(stack.getTranslationKey());
						boolean hijackedIron = stack.getTranslationKey()
								.equals("item.swparasites:longbow_hijacked_iron");

						if (i >= getDrawTicks())
							entityarrow.setIsCritical(true);

						int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

						if (j > 0) {
							entityarrow.setDamage(entityarrow.getDamage() + j * 0.5D + 0.5D);
						}

						int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

						if (k > 0) {
							entityarrow.setKnockbackStrength(k);
						}

						if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
							entityarrow.setFire(100);
						}

						stack.damageItem(1, entityplayer);

						if (flag1) {
							entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
						}

						System.out.println(hijackedIron);
						if (hijackedIron) {
							entityarrow.getTags().add("ParasiteLongbowHijackedIron");
						}

						System.out.println("GFRAHHHH");

						worldIn.spawnEntity(entityarrow);
					}

					worldIn.playSound((EntityPlayer) null, entityplayer.posX, entityplayer.posY, entityplayer.posZ,
							SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F,
							1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

					if (!flag1) {
						itemstack.shrink(1);

						/*
						 * if(!) { // Make sure to set the quiver contents to make sure that the arrow
						 * is used up if(quiverHandler != null && quiverSlot != -1)
						 * quiverHandler.setInventorySlotContents(quiverSlot, itemstack); } else
						 */ if (/* quiver.isEmpty() && */ itemstack.isEmpty()) {
							entityplayer.inventory.deleteStack(itemstack);
						}
					}

					entityplayer.addStat(StatList.getObjectUseStats(this));

				}
			}
		}
	}
}
