package com.existingeevee.swparasites.init;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.properties.AtrophyWeaponProperty;
import com.existingeevee.swparasites.properties.BleedingWeaponProperty;
import com.existingeevee.swparasites.properties.CloakingWeaponProperty;
import com.existingeevee.swparasites.properties.CorrosionWeaponProperty;
import com.existingeevee.swparasites.properties.DevourWeaponProperty;
import com.existingeevee.swparasites.properties.HeavyWeaponProperty;
import com.existingeevee.swparasites.properties.IgniteWeaponProperty;
import com.existingeevee.swparasites.properties.ImmalleableWeaponProperty;
import com.existingeevee.swparasites.properties.MalignantParryWeaponProperty;
import com.existingeevee.swparasites.properties.MalignantWeaponProperty;
import com.existingeevee.swparasites.properties.PlagueWeaponProperty;
import com.existingeevee.swparasites.properties.RepulseWeaponProperty;
import com.existingeevee.swparasites.properties.ShockwaveWeaponProperty;
import com.existingeevee.swparasites.properties.ViralWeaponProperty;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;

public class ParasiteSWProperties {

	public static final BleedingWeaponProperty BLEEDING_1 = new BleedingWeaponProperty(1);
	public static final BleedingWeaponProperty BLEEDING_2 = new BleedingWeaponProperty(2);
	public static final BleedingWeaponProperty BLEEDING_3 = new BleedingWeaponProperty(3);

	public static final CorrosionWeaponProperty CORROSION_1 = new CorrosionWeaponProperty(1);
	public static final CorrosionWeaponProperty CORROSION_2 = new CorrosionWeaponProperty(2);
	public static final CorrosionWeaponProperty CORROSION_3 = new CorrosionWeaponProperty(3);

	public static final ViralWeaponProperty VIRAL_1 = new ViralWeaponProperty(1);
	public static final ViralWeaponProperty VIRAL_2 = new ViralWeaponProperty(2);
	public static final ViralWeaponProperty VIRAL_3 = new ViralWeaponProperty(3);

	public static final ImmalleableWeaponProperty IMMALLEABLE_1 = new ImmalleableWeaponProperty(1);
	public static final ImmalleableWeaponProperty IMMALLEABLE_2 = new ImmalleableWeaponProperty(2);
	public static final ImmalleableWeaponProperty IMMALLEABLE_3 = new ImmalleableWeaponProperty(3);
	
	public static final CloakingWeaponProperty CLOAKING_1 = new CloakingWeaponProperty(false);
	public static final CloakingWeaponProperty CLOAKING_2 = new CloakingWeaponProperty(true);

	public static final RepulseWeaponProperty REPULSE_1 = new RepulseWeaponProperty(1);
	public static final RepulseWeaponProperty REPULSE_2 = new RepulseWeaponProperty(2);
	public static final RepulseWeaponProperty REPULSE_3 = new RepulseWeaponProperty(3);

	public static final HeavyWeaponProperty HEAVY_1 = new HeavyWeaponProperty(false);
	public static final HeavyWeaponProperty HEAVY_2 = new HeavyWeaponProperty(true);

	public static final WeaponProperty REACH_3 = new WeaponProperty(WeaponProperties.PROPERTY_TYPE_REACH, SpartanWeaponryAPI.ModID, 3, 8.0f);
	
	public static final WeaponProperty UNCAPPED = new WeaponProperty("uncapped", SRPSpartanWeaponry.MODID, 0, 0);
	
	public static final ShockwaveWeaponProperty SHOCKWAVE_1 = new ShockwaveWeaponProperty(1);
	public static final ShockwaveWeaponProperty SHOCKWAVE_2 = new ShockwaveWeaponProperty(2);

	public static final AtrophyWeaponProperty ATROPHY_1 = new AtrophyWeaponProperty(1);
	public static final AtrophyWeaponProperty ATROPHY_2 = new AtrophyWeaponProperty(2);
	
	public static final PlagueWeaponProperty PLAGUE_1 = new PlagueWeaponProperty(1);
	public static final PlagueWeaponProperty PLAGUE_2 = new PlagueWeaponProperty(2);
	
	public static final DevourWeaponProperty DEVOUR = new DevourWeaponProperty(1);
	
	public static final MalignantWeaponProperty MALIGNANT = new MalignantWeaponProperty();
	
	public static final MalignantParryWeaponProperty MALIGNANT_PARRY_1 = new MalignantParryWeaponProperty(1);
	public static final MalignantParryWeaponProperty MALIGNANT_PARRY_2 = new MalignantParryWeaponProperty(2);
	
	public static final IgniteWeaponProperty IGNITE_1 = new IgniteWeaponProperty(1);
	public static final IgniteWeaponProperty IGNITE_2 = new IgniteWeaponProperty(2);
}
