package com.existingeevee.srp_spartan_weaponry.init;

import com.existingeevee.srp_spartan_weaponry.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.ModelRenderRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ParasiteSWItems {

	public static Item infLongBlade = new Item().setCreativeTab(CreativeTabs.MATERIALS).setTranslationKey("infectious_long_blade_fragment").setRegistryName("infectious_long_blade_fragment");
	public static Item hardbonePole = new Item().setCreativeTab(CreativeTabs.MATERIALS).setTranslationKey("hardened_bone_pole").setRegistryName("hardened_bone_pole");
	
	@SubscribeEvent
	public static void onItemRegisterEvent(RegistryEvent.Register<Item> ev) {
		ev.getRegistry().register(infLongBlade);
		ModelRenderRegistry.addItemToRegistry(infLongBlade, 
				new ResourceLocation(SRPSpartanWeaponry.MOD_ID, "components/" + infLongBlade.getRegistryName().getPath()));

		ev.getRegistry().register(hardbonePole);
		ModelRenderRegistry.addItemToRegistry(hardbonePole, 
				new ResourceLocation(SRPSpartanWeaponry.MOD_ID, "components/" + hardbonePole.getRegistryName().getPath()));

	} 
	
}
