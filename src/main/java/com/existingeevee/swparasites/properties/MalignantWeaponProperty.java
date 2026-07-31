package com.existingeevee.swparasites.properties;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;
import com.dhanantry.scapeandrunparasites.item.hijacked.*;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class MalignantWeaponProperty extends WeaponPropertyWithCallback {
	public MalignantWeaponProperty() {
		super("malignant", SRPSpartanWeaponry.MODID);
	}

	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
		if (!attacker.world.isRemote) {
			HijackedHitEffects.apply(attacker, target);
		}
	}
}
