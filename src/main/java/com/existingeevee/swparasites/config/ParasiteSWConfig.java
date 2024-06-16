package com.existingeevee.swparasites.config;

import com.existingeevee.swparasites.SRPSpartanWeaponry;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

@Config(modid = SRPSpartanWeaponry.MODID)
public class ParasiteSWConfig {

	@Name("Long Blade Damage")
	@Comment("What should the base damage value for Long Blade be? (default: 4.5)")
	@RequiresMcRestart
	public static float longBladeDmg = 4.5f;

	@Name("Living Weapon Base Durability")
	@Comment("What should the base durability value for the living weapons added be? (default: 1000)")
	@RequiresMcRestart
	public static int maxLivingDamage = 1000;

	@Name("Sentient Weapon Base Durability")
	@Comment("What should the base durability value for the sentient weapons added be? (default: 1000)")
	@RequiresMcRestart
	public static int maxSentientDamage = 1000;

	@Name("Living Weapon Base Damage")
	@Comment("What should the base damage value for the living weapons added be? (default: 15.0)")
	@RequiresMcRestart
	public static float livingBaseDmg = 15.0f;

	@Name("Sentient Weapon Base Damage")
	@Comment("What should the base damage value for the sentient weapons added be? (default: 34.0)")
	@RequiresMcRestart
	public static float sentientBaseDmg = 34.0f;

	@Name("Living Weapon Slowness")
	@Comment("How much slower should living weapons be in terms of swinging? (Note: The weapons have high dmg to compensate for the low atk speed, keep this in mind) (default: 0.33)")
	@RequiresMcRestart
	public static double weaponSlowness = 0.33;

	@Name("Sentient Weapon Slowness")
	@Comment("How much slower should sentient weapons be in terms of swinging? (default: 0.50)")
	@RequiresMcRestart
	public static double weaponIISlowness = 0.50;

	@Name("Cloaking Durability Drain Interval")
	@Comment("In how many ticks should 1 durability drain while using the cloaking ability? (default: 7)")
	@RequiresMcRestart
	public static int cloakingDrain = 7;

	@Name("Living Cloaking Detection Range")
	@Comment("How far should mobs be able to detect players while they are using the Living Dagger's cloaking ability? (default: 4.0)")
	@RequiresMcRestart
	public static double cloakingRange = 4.0;

	@Name("Sentient Cloaking Detection Range")
	@Comment("How far should mobs be able to detect players while they are using the Sentient Dagger's cloaking ability? (default: 3.0)")
	@RequiresMcRestart
	public static double cloakingIIRange = 3.0;

	@Name("Easter Egg")
	@Comment("Should easter eggs when holding down specific keys on an item show a funny little message? (default: true)")
	@RequiresMcRestart
	public static boolean easterEgg = true;
}
