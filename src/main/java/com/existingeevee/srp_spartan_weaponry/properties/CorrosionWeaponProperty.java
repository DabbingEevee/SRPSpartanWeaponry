package com.existingeevee.srp_spartan_weaponry.properties;

import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.existingeevee.srp_spartan_weaponry.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class CorrosionWeaponProperty extends WeaponPropertyWithCallback {

	public CorrosionWeaponProperty(int propLevel) {
		super("corrosion", SRPSpartanWeaponry.MODID, propLevel, propLevel);
	}

	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {		
		//Im sorry
		for (int i = 0; i < getMagnitude(); i++) {
			SRPItems.weapon_axe.hitEntity(stack, target, attacker);
		}
	}
}
