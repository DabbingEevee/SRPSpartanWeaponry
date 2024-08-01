package com.existingeevee.swparasites.init;

import com.existingeevee.swparasites.items.ItemParasiteBoomerang;
import com.existingeevee.swparasites.items.ItemParasiteDagger;
import com.existingeevee.swparasites.items.ItemParasiteJavelin;
import com.existingeevee.swparasites.items.ItemParasiteThrowingAxe;
import com.existingeevee.swparasites.items.ItemParasiteThrowingKnife;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.item.ItemBoomerang;
import com.oblivioussp.spartanweaponry.item.ItemDagger;
import com.oblivioussp.spartanweaponry.item.ItemJavelin;
import com.oblivioussp.spartanweaponry.item.ItemThrowingAxe;
import com.oblivioussp.spartanweaponry.item.ItemThrowingKnife;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CustomWeaponCreator {

	public static Item addBoomerang(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) {
		if (ConfigHandler.disableBoomerang)
			return null;

		ItemBoomerang boomerang = new ItemParasiteBoomerang("boomerang_" + material.getUnlocName(), modId, material);
		boomerang.setCreativeTab(tab);

		for (WeaponProperty prop : properties) {
			boomerang.addWeaponProperty(prop);
		}
		return boomerang;
	}
	
	public static Item addJavelin(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) {
		if (ConfigHandler.disableJavelin)
			return null;

		ItemJavelin javelin = new ItemParasiteJavelin("javelin_" + material.getUnlocName(), modId, material);
		javelin.setCreativeTab(tab);

		for (WeaponProperty prop : properties) {
			javelin.addWeaponProperty(prop);
		}
		return javelin;
	}
	
	public static Item addThrowingAxe(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) {
		if (ConfigHandler.disableThrowingAxe)
			return null;

		ItemThrowingAxe throwingAxe = new ItemParasiteThrowingAxe("throwing_axe_" + material.getUnlocName(), modId, material);
		throwingAxe.setCreativeTab(tab);

		for (WeaponProperty prop : properties) {
			throwingAxe.addWeaponProperty(prop);
		}
		return throwingAxe;
	}
	
	public static Item addThrowingKnife(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) {
		if (ConfigHandler.disableThrowingKnife)
			return null;

		ItemThrowingKnife dagger = new ItemParasiteThrowingKnife("throwing_knife_" + material.getUnlocName(), modId, material);
		dagger.setCreativeTab(tab);

		for (WeaponProperty prop : properties) {
			dagger.addWeaponProperty(prop);
		}
		return dagger;
	}
	
	public static Item addDagger(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) {
		if (ConfigHandler.disableDagger)
			return null;

		ItemDagger dagger = new ItemParasiteDagger("dagger_" + material.getUnlocName(), modId, material);
		dagger.setCreativeTab(tab);

		for (WeaponProperty prop : properties) {
			dagger.addWeaponProperty(prop);
		}
		return dagger;
	}
}
