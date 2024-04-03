package com.existingeevee.srp_spartan_weaponry.properties;

import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.existingeevee.srp_spartan_weaponry.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class BleedingWeaponProperty extends WeaponPropertyWithCallback {

	public BleedingWeaponProperty(int propLevel) {
		super("bleeding", SRPSpartanWeaponry.MODID, propLevel, propLevel);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {		
		//Im sorry
		for (int i = 0; i < getMagnitude(); i++) {
			SRPItems.weapon_sword.hitEntity(stack, target, attacker);
		}
	}
}
