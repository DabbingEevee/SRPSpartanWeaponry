package com.existingeevee.swparasites.properties;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class CloakingWeaponProperty extends WeaponPropertyWithCallback {

	public CloakingWeaponProperty() {
		super("cloaking", SRPSpartanWeaponry.MODID, 0, 0);
	}

	@Override
	public void onItemUpdate(ToolMaterialEx material, ItemStack stack, World world, EntityLivingBase entity, int itemSlot, boolean isSelected) {
		PotionEffect effect = entity.getActivePotionEffect(MobEffects.INVISIBILITY);
		
		if (entity.isSneaking() && (effect == null || effect.getDuration() <= 1) && isSelected) {
			entity.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 2, 0, false, false));
		}
	}
}
