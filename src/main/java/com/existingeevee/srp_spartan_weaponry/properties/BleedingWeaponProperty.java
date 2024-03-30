package com.existingeevee.srp_spartan_weaponry.properties;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.existingeevee.srp_spartan_weaponry.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;

public class BleedingWeaponProperty extends WeaponPropertyWithCallback {

	public BleedingWeaponProperty(int propLevel) {
		super("bleeding", SRPSpartanWeaponry.MODID, propLevel, propLevel);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
		int levelToApply = (int) getMagnitude() - 1;

		target.addPotionEffect(new PotionEffect(SRPPotions.BLEED_E, 100, levelToApply));
	}
}
