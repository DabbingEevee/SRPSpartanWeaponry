package com.existingeevee.swparasites;

import com.existingeevee.swparasites.event.DisplayTooltips;
import com.existingeevee.swparasites.init.ParasiteSWItems;
import com.existingeevee.swparasites.init.weapons.ParasiteSWLiving;
import com.existingeevee.swparasites.init.weapons.ParasiteSWSentient;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SRPSpartanWeaponry.MODID, dependencies = SRPSpartanWeaponry.DEPENDANCY)
public class SRPSpartanWeaponry {
    public static final String MODID = "swparasites";

    public static final String DEPENDANCY =
    		"required-after:spartanweaponry;" +
    		"required-after:srparasites";

    @EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
    	MinecraftForge.EVENT_BUS.register(DisplayTooltips.class);
    	MinecraftForge.EVENT_BUS.register(ParasiteSWItems.class);
    	
    	MinecraftForge.EVENT_BUS.register(ParasiteSWLiving.class);
    	MinecraftForge.EVENT_BUS.register(ParasiteSWSentient.class);
    }
    
}
