package com.existingeevee.swparasites.properties;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ShockwaveWeaponProperty extends WeaponPropertyWithCallback {

	public ShockwaveWeaponProperty(int propLevel) {
		super("shockwave", SRPSpartanWeaponry.MODID, propLevel, propLevel);
	}

	
}
