package com.existingeevee.srp_spartan_weaponry.properties;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.existingeevee.srp_spartan_weaponry.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ImmalleableWeaponProperty extends WeaponPropertyWithCallback {

	public ImmalleableWeaponProperty() {
		super("immalleable", SRPSpartanWeaponry.MODID, 0, 0);
	}

	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
		double chance = 1d / 8;
		int amp = 0;

		if (attacker.world.rand.nextDouble() < chance) {
			target.addPotionEffect(new PotionEffect(SRPPotions.RES_E, 40, amp, false, false));
		}
	}
}
