package com.existingeevee.swparasites.handlers;

import com.existingeevee.swparasites.init.weapons.ParasiteSWLiving;
import com.existingeevee.swparasites.init.weapons.ParasiteSWSentient;
import com.existingeevee.swparasites.init.weapons.ParasiteSWShields;

import net.minecraft.item.Item;

public class EvolutionHandler {
	public static Item getEvolved(Item item) {
		if (item == ParasiteSWLiving.boomerangLiving) {
			return ParasiteSWSentient.boomerangSentient;
		}
		if (item == ParasiteSWLiving.claymoreLiving) {
			return ParasiteSWSentient.claymoreSentient;
		}
		if (item == ParasiteSWLiving.daggerLiving) {
			return ParasiteSWSentient.daggerSentient;
		}
		if (item == ParasiteSWLiving.glaiveLiving) {
			return ParasiteSWSentient.glaiveSentient;
		}
		if (item == ParasiteSWLiving.halberdLiving) {
			return ParasiteSWSentient.halberdSentient;
		}
		if (item == ParasiteSWLiving.hammerLiving) {
			return ParasiteSWSentient.hammerSentient;
		}
		if (item == ParasiteSWLiving.javelinLiving) {
			return ParasiteSWSentient.javelinSentient;
		}
		if (item == ParasiteSWLiving.katanaLiving) {
			return ParasiteSWSentient.katanaSentient;
		}
		if (item == ParasiteSWLiving.longswordLiving) {
			return ParasiteSWSentient.longswordSentient;
		}
		if (item == ParasiteSWLiving.maceLiving) {
			return ParasiteSWSentient.maceSentient;
		}
		if (item == ParasiteSWLiving.parryDaggerLiving) {
			return ParasiteSWSentient.parryDaggerSentient;
		}
		if (item == ParasiteSWLiving.pikeLiving) {
			return ParasiteSWSentient.pikeSentient;
		}
		if (item == ParasiteSWLiving.saberLiving) {
			return ParasiteSWSentient.saberSentient;
		}
		if (item == ParasiteSWLiving.quarterstaffLiving) {
			return ParasiteSWSentient.quarterstaffSentient;
		}
		if (item == ParasiteSWLiving.rapierLiving) {
			return ParasiteSWSentient.rapierSentient;
		}
		if (item == ParasiteSWLiving.spearLiving) {
			return ParasiteSWSentient.spearSentient;
		}
		if (item == ParasiteSWLiving.throwingAxeLiving) {
			return ParasiteSWSentient.throwingAxeSentient;
		}
		if (item == ParasiteSWLiving.throwingKnifeLiving) {
			return ParasiteSWSentient.throwingKnifeSentient;
		}
		if (item == ParasiteSWLiving.warhammerLiving) {
			return ParasiteSWSentient.warhammerSentient;
		}
		if (item == ParasiteSWShields.bucklerShieldLiving) {
			return ParasiteSWShields.bucklerShieldSentient;
		}
		if (item == ParasiteSWShields.impalerShieldLiving) {
			return ParasiteSWShields.impalerShieldSentient;
		}
		return null;
	}
}
