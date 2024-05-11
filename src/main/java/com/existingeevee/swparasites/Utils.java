package com.existingeevee.swparasites;

import com.oblivioussp.spartanweaponry.item.ItemSwordBase;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;

public class Utils {

	public static void forceSetRegistryName(Impl impl, String str) {
		if (impl == null)
			return;
		ObfuscationReflectionHelper.setPrivateValue(Impl.class, impl, GameData.checkPrefix(str, true), "registryName");
	}

	public static void resetAutogenName(ItemSwordBase impl) {
		if (impl == null)
			return;	
		ObfuscationReflectionHelper.setPrivateValue(ItemSwordBase.class, impl, null, "displayName");
	}
}
