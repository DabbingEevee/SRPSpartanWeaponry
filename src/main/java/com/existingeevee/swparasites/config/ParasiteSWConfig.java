package com.existingeevee.swparasites.config;

import com.existingeevee.swparasites.SRPSpartanWeaponry;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

@Config(modid = SRPSpartanWeaponry.MODID)
public class ParasiteSWConfig {

	@Name("Parasite Weapon Slowness")
	@Comment("How much slower should parasite weapons be in terms of swinging?")
	@RequiresMcRestart
	public static double weaponSlowness = 0.25;
	
	@Name("Long Blade Damage")
	@Comment("What should the base damage value for Long Blade be?")
	@RequiresMcRestart
	public static float longBladeDmg = 3.5f;
	
	@Name("Living Weapon Base Damage")
	@Comment("What should the base damage value for the living weapons added be?")
	@RequiresMcRestart
	public static float livingBaseDmg = 6f;
}
