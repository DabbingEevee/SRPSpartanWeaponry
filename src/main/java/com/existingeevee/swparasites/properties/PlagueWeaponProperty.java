package com.existingeevee.swparasites.properties;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class PlagueWeaponProperty extends WeaponPropertyWithCallback {

	public PlagueWeaponProperty(int propLevel) {
		super("plague", SRPSpartanWeaponry.MODID, propLevel, propLevel);
	}

	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
		target.addPotionEffect(new PotionEffect(MobEffects.POISON, 100, (int) (getMagnitude() - 1), false, false));
	}
}
