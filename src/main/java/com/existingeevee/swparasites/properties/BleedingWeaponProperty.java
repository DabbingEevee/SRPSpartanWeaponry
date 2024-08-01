package com.existingeevee.swparasites.properties;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class BleedingWeaponProperty extends WeaponPropertyWithCallback {

	public BleedingWeaponProperty(int propLevel) {
		super("bleeding", SRPSpartanWeaponry.MODID, propLevel, propLevel);
	}

	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {		
		//I fixed it :D
		for (int i = 0; i < getMagnitude(); i++) {
            if (attacker.world.rand.nextDouble() < 0.25) {
                SRPPotions.applyStackPotion(SRPPotions.BLEED_E, target, 100, 0);
            }
		}
	}
}
