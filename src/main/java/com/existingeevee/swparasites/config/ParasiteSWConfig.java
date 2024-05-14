package com.existingeevee.swparasites.config;

import com.existingeevee.swparasites.SRPSpartanWeaponry;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

@Config(modid = SRPSpartanWeaponry.MODID)
public class ParasiteSWConfig {

	@Name("Long Blade Damage")
	@Comment("What should the base damage value for Long Blade be? (default: 3.5)")
	@RequiresMcRestart
	public static float longBladeDmg = 4.5f;

	@Name("Living Weapon Base Durability")
	@Comment("What should the base durability value for the living weapons added be? (default: 1000)")
	@RequiresMcRestart
	public static int maxLivingDamage = 1000;

	@Name("Living Weapon Base Damage")
	@Comment("What should the base damage value for the living weapons added be? (default: 6.0)")
	@RequiresMcRestart
	public static float livingBaseDmg = 15.0f;

	@Name("Parasite Weapon Slowness")
	@Comment("How much slower should parasite weapons be in terms of swinging? (default: 0.25)")
	@RequiresMcRestart
	public static double weaponSlowness = 0.33;

	@Name("Cloaking Durability Drain Interval")
	@Comment("In how many ticks should 1 durability drain while using the cloaking ability? (default: 7)")
	@RequiresMcRestart
	public static int cloakingDrain = 7;

	@Name("Cloaking Detection Range")
	@Comment("How far should mobs be able to detect players while they are using the cloaking ability? (default: 4.0)")
	@RequiresMcRestart
	public static double cloakingRange = 4.0;

	@Name("Easter Egg")
	@Comment("Should easter eggs when holding down specific keys on an item show a funny little message? (default: true)")
	@RequiresMcRestart
	public static boolean easterEgg = true;
}
