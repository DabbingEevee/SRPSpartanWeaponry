package com.existingeevee.swparasites.handlers;

import com.existingeevee.swparasites.Utils;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBolt;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CrossbowBoltHandler {

	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent e) {
		Entity immSource = e.getSource().getImmediateSource();
		
		if (immSource instanceof EntityBolt && immSource.getTags().contains("ParasiteCrossbowMultifire")) {
			e.getEntityLiving().hurtResistantTime = Math.min(1, e.getEntityLiving().maxHurtResistantTime);
			
			Utils.executeInNTicks(() -> {
				if (e.getEntityLiving().getHealth() <= 0) {
					//vec3d 
				}
			}, 1);
		}
	}
}
