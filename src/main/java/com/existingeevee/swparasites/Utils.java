package com.existingeevee.swparasites;

import com.oblivioussp.spartanweaponry.item.ItemSwordBase;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;

public class Utils {

	public static void forceSetRegistryName(Impl impl, String str) {
		ObfuscationReflectionHelper.setPrivateValue(Impl.class, impl, GameData.checkPrefix(str, true), "registryName");
	}

	public static void resetAutogenName(ItemSwordBase impl) {
		ObfuscationReflectionHelper.setPrivateValue(ItemSwordBase.class, impl, null, "displayName");
	}
		
	public static void multiplyAttackSpeed(ItemSwordBase impl, double multiplier) {
		ObfuscationReflectionHelper.setPrivateValue(ItemSwordBase.class, impl, null, "attackSpeed");
	}
}
