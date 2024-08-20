package com.existingeevee.swparasites.init.weapons;

import java.util.ArrayList;
import java.util.List;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.items.ItemBucklerShield;
import com.existingeevee.swparasites.items.ItemImpalerShield;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.init.ModelRenderRegistry;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ParasiteSWShields {

	public static ToolMaterialEx livingMaterial;

	public static ItemBucklerShield bucklerShieldLiving = null;
	public static ItemBucklerShield bucklerShieldSentient = null;
	public static ItemImpalerShield impalerShieldLiving = null;
	public static ItemImpalerShield impalerShieldSentient = null;
	
	private static List<Item> initalizeItems(List<Item> items) {
		bucklerShieldLiving = new ItemBucklerShield("buckler_shield_living", ParasiteSWLiving.getLivingMaterial().getMaxUses(), 0);
		items.add(bucklerShieldLiving);

		bucklerShieldSentient = new ItemBucklerShield("buckler_shield_sentient", ParasiteSWSentient.getSentientMaterial().getMaxUses(), 1);
		items.add(bucklerShieldSentient);
		
		impalerShieldLiving = new ItemImpalerShield("impaler_shield_living", ParasiteSWLiving.getLivingMaterial().getMaxUses(), 1, 1, 1);
		items.add(impalerShieldLiving);

		impalerShieldSentient = new ItemImpalerShield("impaler_shield_sentient", ParasiteSWSentient.getSentientMaterial().getMaxUses(), 2, 1.5f, 1.5f);
		items.add(impalerShieldSentient);
		
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
