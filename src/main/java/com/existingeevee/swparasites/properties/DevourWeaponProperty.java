package com.existingeevee.swparasites.properties;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class DevourWeaponProperty extends WeaponPropertyWithCallback {

	public DevourWeaponProperty(int propLevel) {
		super("devour", SRPSpartanWeaponry.MODID, propLevel, propLevel);
	}

	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
		target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, (int) (1 + getMagnitude()), false, false));
	}
}
