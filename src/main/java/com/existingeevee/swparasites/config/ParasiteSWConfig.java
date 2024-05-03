package com.existingeevee.swparasites.config;

import com.existingeevee.swparasites.SRPSpartanWeaponry;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

@Config(modid = SRPSpartanWeaponry.MODID)
public class ParasiteSWConfig {

	@Name("Parasite Weapon Slowness")
	@Comment("How much slower should parasite weapons be in terms of swinging.")
	@RequiresMcRestart
	public static double weaponSlowness = 0.2;
}
