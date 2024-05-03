package com.existingeevee.swparasites.properties;

import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ViralWeaponProperty extends WeaponPropertyWithCallback {

	public ViralWeaponProperty(int propLevel) {
		super("virulent", SRPSpartanWeaponry.MODID, propLevel, propLevel);
	}

	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {		
		//Im sorry
		for (int i = 0; i < getMagnitude(); i++) {
			SRPItems.weapon_cleaver.hitEntity(stack, target, attacker);
		}
	}
}
