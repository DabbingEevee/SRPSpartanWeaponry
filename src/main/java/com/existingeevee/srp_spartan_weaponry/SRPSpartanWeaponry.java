package com.existingeevee.srp_spartan_weaponry;

import net.minecraftforge.fml.common.Mod;

@Mod(modid = SRPSpartanWeaponry.MODID, dependencies = SRPSpartanWeaponry.DEPENDANCY)
public class SRPSpartanWeaponry {
    public static final String MODID = "srp_spartan_weaponry";

    public static final String DEPENDANCY =
    		"required-after:spartanweaponry;" +
    		"required-after:srparasites";

}
