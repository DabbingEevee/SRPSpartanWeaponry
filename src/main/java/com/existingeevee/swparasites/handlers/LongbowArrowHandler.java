package com.existingeevee.swparasites.handlers;

import com.dhanantry.scapeandrunparasites.item.hijacked.HijackedHitEffects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LongbowArrowHandler {

	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent e) {
		Entity immSource = e.getSource().getImmediateSource();

		
		//System.out.println(immSource.getTags()); evil print statement that crashes the fucking game
		if (immSource instanceof IProjectile && immSource.getTags().contains("ParasiteLongbowHijackedIron")) {
			EntityLivingBase player = (EntityLivingBase) e.getSource().getTrueSource();
			HijackedHitEffects.apply(player, e.getEntityLiving());
			System.out.println("b");
		}
	}
}
