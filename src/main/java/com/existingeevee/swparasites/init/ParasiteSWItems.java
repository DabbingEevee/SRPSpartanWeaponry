package com.existingeevee.swparasites.init;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.items.ItemLongBlade;
import com.oblivioussp.spartanweaponry.init.ModelRenderRegistry;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ParasiteSWItems {

	public static ToolMaterial longBladeToolMat = EnumHelper.addToolMaterial("SRPSW$LONG_BLADE", 0, 0, 0, 1, 10);
	
	public static Item infLongBlade = null;
	public static Item hardbonePole = null;
	
	@SubscribeEvent
	public static void onItemRegisterEvent(RegistryEvent.Register<Item> ev) {
		infLongBlade = new ItemLongBlade(longBladeToolMat).setCreativeTab(SRPMain.SRP_CREATIVETAB).setTranslationKey("infectious_long_blade_fragment").setRegistryName("infectious_long_blade_fragment");
		hardbonePole = new Item().setCreativeTab(SRPMain.SRP_CREATIVETAB).setTranslationKey("hardened_bone_pole").setRegistryName("hardened_bone_pole");
		
		ev.getRegistry().register(infLongBlade);
		ModelRenderRegistry.addItemToRegistry(infLongBlade, 
				new ResourceLocation(SRPSpartanWeaponry.MODID, "components/" + infLongBlade.getRegistryName().getPath()));

		ev.getRegistry().register(hardbonePole);
		ModelRenderRegistry.addItemToRegistry(hardbonePole, 
				new ResourceLocation(SRPSpartanWeaponry.MODID, "components/" + hardbonePole.getRegistryName().getPath()));

	} 
	
}
