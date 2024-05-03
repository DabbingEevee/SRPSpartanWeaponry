package com.existingeevee.swparasites.properties;

import java.util.UUID;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.config.ParasiteSWConfig;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HeavyWeaponProperty extends WeaponProperty {

	public HeavyWeaponProperty() {
		super("heavy", SRPSpartanWeaponry.MODID, 0, 0);
		MinecraftForge.EVENT_BUS.register(this);
	}

	private static AttributeModifier modifier;
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		Item item = event.getEntityLiving().getHeldItemMainhand().getItem();
		
		boolean shouldHaveSlowing = false;
		
		if (item instanceof IWeaponPropertyContainer<?>) {
			IWeaponPropertyContainer<?> container = (IWeaponPropertyContainer<?>) item;
			
			if (container.getAllWeaponProperties().stream().anyMatch(p -> p == this)) {
				shouldHaveSlowing = true;
			}
		}
		
		IAttributeInstance attr = event.getEntityLiving().getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_SPEED);
			
		if (attr != null) {
			if (shouldHaveSlowing && !attr.hasModifier(getModifier())) {
				attr.applyModifier(getModifier());
			} 
			if (!shouldHaveSlowing && attr.hasModifier(getModifier())) {
				attr.removeModifier(getModifier());
			} 
		}
	}
	
	private static AttributeModifier getModifier() {
		if (modifier == null) {
			modifier = new AttributeModifier(UUID.fromString("aeee73df-79af-4de9-eeee-44b5eee4df1d"), "heavy_weapon_property", -ParasiteSWConfig.weaponSlowness, 2);
		}
		return modifier;
	}
}
