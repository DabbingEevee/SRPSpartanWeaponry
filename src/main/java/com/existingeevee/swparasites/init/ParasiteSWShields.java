package com.existingeevee.swparasites.init;

import java.util.ArrayList;
import java.util.List;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.config.ParasiteSWConfig;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.init.ModelRenderRegistry;
import com.oblivioussp.spartanweaponry.item.ItemThrowingWeapon;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ParasiteSWShields {

	public static ToolMaterialEx livingMaterial;

	private static List<Item> initalizeItems(List<Item> items) {

		livingMaterial = getLivingMaterial();

		return items;
	}
	
	

	@SubscribeEvent
	public static void onItemRegisterEvent(RegistryEvent.Register<Item> ev) {
		IForgeRegistry<Item> reg = ev.getRegistry();

		for (Item i : initalizeItems(new ArrayList<>())) {

			if (i != null) {
				reg.register(i);

				if (!(i instanceof IWeaponPropertyContainer)) {
					continue;
				}

				IWeaponPropertyContainer<?> container = (IWeaponPropertyContainer<?>) i;

				container.addWeaponProperty(ParasiteSWProperties.HEAVY_1);
				if (!(i instanceof ItemThrowingWeapon)) { //throwing weapons dont work with uncapped atm, sorgy
					container.addWeaponProperty(ParasiteSWProperties.UNCAPPED);
				}
				ToolMaterialEx mat = container.getMaterialEx();
				String modelPath = mat.getUnlocName() + "/" + i.getRegistryName().getPath();

				if (mat.getPrimaryColour() >= 0 && mat.getSecondaryColour() >= 0) {
					ModelRenderRegistry.addItemToRegistry(i, new ResourceLocation(SRPSpartanWeaponry.MODID, modelPath), mat);
				} else {
					ModelRenderRegistry.addItemToRegistry(i, new ResourceLocation(SRPSpartanWeaponry.MODID, modelPath));
				}
			}
		}
	}
}
