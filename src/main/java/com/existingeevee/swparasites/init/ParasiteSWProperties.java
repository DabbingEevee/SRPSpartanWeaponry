package com.existingeevee.swparasites.init;

import com.existingeevee.swparasites.properties.BleedingWeaponProperty;
import com.existingeevee.swparasites.properties.CloakingWeaponProperty;
import com.existingeevee.swparasites.properties.CorrosionWeaponProperty;
import com.existingeevee.swparasites.properties.ImmalleableWeaponProperty;
import com.existingeevee.swparasites.properties.RepulseWeaponProperty;
import com.existingeevee.swparasites.properties.HeavyWeaponProperty;
import com.existingeevee.swparasites.properties.ViralWeaponProperty;

public class ParasiteSWProperties {
	
	public static final BleedingWeaponProperty BLEEDING_1 = new BleedingWeaponProperty(1);
	public static final BleedingWeaponProperty BLEEDING_2 = new BleedingWeaponProperty(2);

	public static final CorrosionWeaponProperty CORROSION_1 = new CorrosionWeaponProperty(1);
	public static final CorrosionWeaponProperty CORROSION_2 = new CorrosionWeaponProperty(2);
	
	public static final ImmalleableWeaponProperty IMMALLEABLE = new ImmalleableWeaponProperty();
	
	public static final ViralWeaponProperty VIRAL_1 = new ViralWeaponProperty(1);
	public static final ViralWeaponProperty VIRAL_2 = new ViralWeaponProperty(2);
	
	public static final CloakingWeaponProperty CLOAKING = new CloakingWeaponProperty();
	
	public static final RepulseWeaponProperty REPULSE = new RepulseWeaponProperty();
	
	public static final HeavyWeaponProperty SLOW = new HeavyWeaponProperty();

	
}
