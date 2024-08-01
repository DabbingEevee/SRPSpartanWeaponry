package com.existingeevee.swparasites.items;

import com.existingeevee.swparasites.config.ParasiteSWConfig;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.item.ItemThrowingAxe;

import net.minecraft.item.ItemStack;

public class ItemParasiteThrowingAxe extends ItemThrowingAxe {

	public ItemParasiteThrowingAxe(String unlocName, String externalModId, ToolMaterialEx material) {
		super(unlocName, externalModId, material);
	}

	@Override
	public int getMaxChargeTicks(ItemStack stack) {
		WeaponProperty prop = this.getFirstWeaponPropertyWithType("heavy");
		if (prop != null) { 
			boolean lvl2 = prop.getLevel() != 1;
			float mult = (float) (1f / (lvl2 ? ParasiteSWConfig.weaponIISlowness : ParasiteSWConfig.weaponSlowness));
			return (int) Math.round(super.getMaxChargeTicks(stack) * mult);
		}
		return super.getMaxChargeTicks(stack);
	}
}
