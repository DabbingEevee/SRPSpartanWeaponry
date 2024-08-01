package com.existingeevee.swparasites.items;

import com.existingeevee.swparasites.config.ParasiteSWConfig;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;
import com.oblivioussp.spartanweaponry.item.ItemDagger;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow.PickupStatus;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemParasiteDagger extends ItemDagger {

	public ItemParasiteDagger(String unlocName, String externalModId, ToolMaterialEx material) {
		super(unlocName, externalModId, material);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return !ItemStack.areItemsEqualIgnoreDurability(oldStack, newStack);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (this.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_THROWABLE) != null && entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;

			int charge = this.getMaxItemUseDuration(stack) - timeLeft;
			
			/*-------*/			
			WeaponProperty heavy = this.getFirstWeaponPropertyWithType("heavy");
			if (heavy != null) {
				boolean lvl2 = heavy.getLevel() != 1;
				float mult = (float) (lvl2 ? ParasiteSWConfig.weaponIISlowness : ParasiteSWConfig.weaponSlowness);
				charge = (int) Math.round(charge * mult);
			}
			/*-------*/
			
			if (charge >= 5)
				charge = 5;

			if (!worldIn.isRemote && charge > 2) {
				EntityThrownWeapon thrown = new EntityThrownWeapon(worldIn, player);
				thrown.setWeapon(stack);
				thrown.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5f * (charge / 10.0f + 0.5f), 0.5f);
				thrown.setDamage(getDirectAttackDamage() + 1.0d);

				double damageModifier = 0.0d;
				// Apply enchantments as necessary
				if (Loader.isModLoaded("somanyenchantments")) {
					Enchantment supSharpness = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation("somanyenchantments:supremesharpness"));
					Enchantment advSharpness = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation("somanyenchantments:advancedsharpness"));
					Enchantment lesSharpness = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation("somanyenchantments:lessersharpness"));

					int j;
					if ((j = EnchantmentHelper.getEnchantmentLevel(supSharpness, stack)) > 0)
						damageModifier = 4.0d + j * 1.6d;
					else if ((j = EnchantmentHelper.getEnchantmentLevel(advSharpness, stack)) > 0)
						damageModifier = 1.25d + j * 0.95d;
					else if ((j = EnchantmentHelper.getEnchantmentLevel(lesSharpness, stack)) > 0)
						damageModifier = 0.25d + j * 0.25d;
				}
				int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, stack);
				if (j > 0)
					damageModifier = 0.5d + j * 0.5d;

				if (damageModifier > 0.0d)
					thrown.setDamage(thrown.getDamage() + damageModifier);

				int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, stack);
				if (k > 0) {
					thrown.setKnockbackStrength(k);
				}
				if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, stack) > 0) {
					thrown.setFire(100);
				}

				if (player.capabilities.isCreativeMode)
					thrown.pickupStatus = PickupStatus.CREATIVE_ONLY;
				else if (thrown.isValidThrowingWeapon()) {
					stack.setCount(stack.getCount() - 1);
					if (stack.getCount() <= 0)
						player.inventory.deleteStack(stack);
				}
				if (thrown.isValidThrowingWeapon()) {
					worldIn.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
					worldIn.spawnEntity(thrown);
				}
				player.addStat(StatList.getObjectUseStats(this));
			}

		}
	}
}
