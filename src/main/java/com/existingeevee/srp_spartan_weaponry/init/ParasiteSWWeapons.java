package com.existingeevee.srp_spartan_weaponry.init;

import java.util.ArrayList;
import java.util.List;

import com.existingeevee.srp_spartan_weaponry.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.init.ModelRenderRegistry;
import com.oblivioussp.spartanweaponry.item.ItemDagger;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class ParasiteSWWeapons {

	public static final ToolMaterialEx MATERIAL_LIVING = new ToolMaterialEx("living", "coreLiving", SRPSpartanWeaponry.MOD_ID, -1, -1, 4, 2048, 7.5f, 8.0f, 18);

	public static ItemDagger daggerLiving = null;

	private static List<Item> initalizeItems(List<Item> items) {

		daggerLiving = (ItemDagger) SpartanWeaponryAPI.createDagger(MATERIAL_LIVING, SRPSpartanWeaponry.MOD_ID, CreativeTabsSW.TAB_SW_MOD);
		//daggerLiving.addWeaponProperty(null);
		items.add(daggerLiving);

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
				ToolMaterialEx mat = container.getMaterialEx();
				String modelPath = mat.getUnlocName() + "/" + i.getRegistryName().getPath();
				
				if (mat.getPrimaryColour() >= 0 && mat.getSecondaryColour() >= 0) {
					ModelRenderRegistry.addItemToRegistry(i, new ResourceLocation(SRPSpartanWeaponry.MOD_ID, modelPath), mat);
				} else {
					ModelRenderRegistry.addItemToRegistry(i, new ResourceLocation(SRPSpartanWeaponry.MOD_ID, modelPath));
				}
			}
		}
	}
}
