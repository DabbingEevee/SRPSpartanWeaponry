package com.existingeevee.swparasites;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.existingeevee.swparasites.event.DisplayTooltips;
import com.existingeevee.swparasites.init.ParasiteSWItems;
import com.existingeevee.swparasites.init.ParasiteSWProperties;
import com.existingeevee.swparasites.init.weapons.ParasiteSWLiving;
import com.existingeevee.swparasites.init.weapons.ParasiteSWSentient;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(modid = SRPSpartanWeaponry.MODID, dependencies = SRPSpartanWeaponry.DEPENDANCY)
public class SRPSpartanWeaponry {
	public static final String MODID = "swparasites";

	public static final String DEPENDANCY = "required-after:spartanweaponry;" +
			"required-after:srparasites";

	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(DisplayTooltips.class);
		MinecraftForge.EVENT_BUS.register(ParasiteSWItems.class);

		MinecraftForge.EVENT_BUS.register(ParasiteSWLiving.class);
		MinecraftForge.EVENT_BUS.register(ParasiteSWSentient.class);
	}

	@EventHandler
	public void onLoadComplete(FMLLoadCompleteEvent event) {
		boolean invertedMode = SRPConfig.damageCapBlackListWhite;
		List<String> bypass = new ArrayList<>(Arrays.asList(SRPConfig.damageCapBlackListItem));
		
		for (Item item : ForgeRegistries.ITEMS) {
			if (item instanceof IWeaponPropertyContainer<?>) {
				IWeaponPropertyContainer<?> container = (IWeaponPropertyContainer<?>) item;

				if (container.hasWeaponProperty(ParasiteSWProperties.UNCAPPED)) {
					if (invertedMode) {
						bypass.remove(item.getRegistryName().toString());
					} else {
						bypass.add(item.getRegistryName().toString());
					}
				}
			}
		}
		
		SRPConfig.damageCapBlackListItem = bypass.stream().toArray(String[]::new);
	}
}
