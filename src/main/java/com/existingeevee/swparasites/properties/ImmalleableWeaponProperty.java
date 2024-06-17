package com.existingeevee.swparasites.properties;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ImmalleableWeaponProperty extends WeaponPropertyWithCallback {

	public ImmalleableWeaponProperty(int propLevel) {
		super("immalleable", SRPSpartanWeaponry.MODID, propLevel, propLevel);
	}

	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
		double chance = this.getMagnitude() / 8;

		if (attacker.world.rand.nextDouble() < chance) {
			target.addPotionEffect(new PotionEffect(SRPPotions.RES_E, 40, 0, false, false));
		}
	}
}
