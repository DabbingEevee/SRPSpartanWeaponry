package com.existingeevee.swparasites.init.weapons;

import java.util.ArrayList;
import java.util.List;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.init.ParasiteSWProperties;
import com.existingeevee.swparasites.items.ItemBucklerShield;
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

	public static ItemBucklerShield bucklerShieldLiving = null;
	public static ItemBucklerShield bucklerShieldSentient = null;
	
	private static List<Item> initalizeItems(List<Item> items) {
		bucklerShieldLiving = new ItemBucklerShield("buckler_shield_living", ParasiteSWLiving.getLivingMaterial().getMaxUses(), 0);
		items.add(bucklerShieldLiving);

		bucklerShieldSentient = new ItemBucklerShield("buckler_shield_sentient", ParasiteSWSentient.getSentientMaterial().getMaxUses(), 1);
		items.add(bucklerShieldSentient);
		
		return items;
	}
	
	

	@SubscribeEvent
	public static void onItemRegisterEvent(RegistryEvent.Register<Item> ev) {
		IForgeRegistry<Item> reg = ev.getRegistry();

		for (Item i : initalizeItems(new ArrayList<>())) {

			if (i != null) {
				reg.register(i);
				String modelPath = "shield/" + i.getRegistryName().getPath();
				ModelRenderRegistry.addItemToRegistry(i, new ResourceLocation(SRPSpartanWeaponry.MODID, modelPath));
			}
		}
	}
}
