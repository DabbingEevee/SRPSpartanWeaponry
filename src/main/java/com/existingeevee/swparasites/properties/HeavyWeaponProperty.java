package com.existingeevee.swparasites.properties;

import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.config.ParasiteSWConfig;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

	@Override
	public PropertyQuality getQuality() {
		return PropertyQuality.NEGATIVE;
	}

	private static final DecimalFormat FORMATTER = new DecimalFormat("0.##");
	
	@Override
	@SideOnly(Side.CLIENT)
	protected void addTooltipDescription(ItemStack stack, List<String> tooltip) {
		String percent = FORMATTER.format(ParasiteSWConfig.weaponSlowness * 100);
		
		tooltip.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "  " + SpartanWeaponryAPI.internalHandler.translateString(type + ".desc", "tooltip", modId).replace("$s", percent + "%"));
	}

	private static AttributeModifier getModifier() {
		if (modifier == null) {
			modifier = new AttributeModifier(UUID.fromString("aeee73df-79af-4de9-eeee-44b5eee4df1d"), "heavy_weapon_property", -ParasiteSWConfig.weaponSlowness, 2);
		}
		return modifier;
	}
}
