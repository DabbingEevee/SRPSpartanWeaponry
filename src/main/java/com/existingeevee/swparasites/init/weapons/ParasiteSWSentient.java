package com.existingeevee.swparasites.init.weapons;

import java.util.ArrayList;
import java.util.List;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.Utils;
import com.existingeevee.swparasites.config.ParasiteSWConfig;
import com.existingeevee.swparasites.init.ParasiteSWProperties;
import com.existingeevee.swparasites.items.ItemNoReequipDagger;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.init.ModelRenderRegistry;
import com.oblivioussp.spartanweaponry.item.ItemBoomerang;
import com.oblivioussp.spartanweaponry.item.ItemDagger;
import com.oblivioussp.spartanweaponry.item.ItemGlaive;
import com.oblivioussp.spartanweaponry.item.ItemGreatsword;
import com.oblivioussp.spartanweaponry.item.ItemHalberd;
import com.oblivioussp.spartanweaponry.item.ItemHammer;
import com.oblivioussp.spartanweaponry.item.ItemJavelin;
import com.oblivioussp.spartanweaponry.item.ItemKatana;
import com.oblivioussp.spartanweaponry.item.ItemLongsword;
import com.oblivioussp.spartanweaponry.item.ItemMace;
import com.oblivioussp.spartanweaponry.item.ItemParryingDagger;
import com.oblivioussp.spartanweaponry.item.ItemPike;
import com.oblivioussp.spartanweaponry.item.ItemQuarterstaff;
import com.oblivioussp.spartanweaponry.item.ItemRapier;
import com.oblivioussp.spartanweaponry.item.ItemSaber;
import com.oblivioussp.spartanweaponry.item.ItemSpear;
import com.oblivioussp.spartanweaponry.item.ItemSwordBase;
import com.oblivioussp.spartanweaponry.item.ItemThrowingAxe;
import com.oblivioussp.spartanweaponry.item.ItemThrowingKnife;
import com.oblivioussp.spartanweaponry.item.ItemWarhammer;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ParasiteSWSentient {

	public static ToolMaterialEx sentientMaterial;

	public static ItemGreatsword claymoreSentient = null;
	public static ItemBoomerang boomerangSentient = null;
	public static ItemDagger daggerSentient = null;
	public static ItemGlaive glaiveSentient = null;
	public static ItemHalberd halbardSentient = null;
	public static ItemHammer hammerSentient = null;
	public static ItemJavelin javelinSentient = null;
	public static ItemKatana katanaSentient = null;
	public static ItemLongsword longswordSentient = null;
	public static ItemMace maceSentient = null;
	public static ItemParryingDagger parryDaggerSentient = null;
	public static ItemPike pikeSentient = null;
	public static ItemSaber saberSentient = null;
	public static ItemQuarterstaff quarterstaffSentient = null;
	public static ItemRapier rapierSentient = null;
	public static ItemSpear spearSentient = null;
	public static ItemThrowingAxe throwingAxeSentient = null;
	public static ItemThrowingKnife throwingKnifeSentient = null;
	public static ItemWarhammer warhammerSentient = null;

	private static List<Item> initalizeItems(List<Item> items) {

		sentientMaterial = getSentientMaterial();

		claymoreSentient = (ItemGreatsword) SpartanWeaponryAPI.createGreatsword(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.VIRAL_2).setTranslationKey("claymore_sentient");
		Utils.forceSetRegistryName(claymoreSentient, "claymore_sentient");
		Utils.resetAutogenName(claymoreSentient);
		items.add(claymoreSentient);

		daggerSentient = (ItemDagger) addDagger(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.CLOAKING_2);
		items.add(daggerSentient);

		boomerangSentient = (ItemBoomerang) SpartanWeaponryAPI.createBoomerang(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.VIRAL_2);
		items.add(boomerangSentient);

		glaiveSentient = (ItemGlaive) SpartanWeaponryAPI.createGlaive(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.BLEEDING_3);
		items.add(glaiveSentient);

		halbardSentient = (ItemHalberd) SpartanWeaponryAPI.createHalberd(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.CORROSION_2);
		items.add(halbardSentient);

		hammerSentient = (ItemHammer) SpartanWeaponryAPI.createHammer(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IMMALLEABLE_2);
		items.add(hammerSentient);

		javelinSentient = (ItemJavelin) SpartanWeaponryAPI.createJavelin(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.BLEEDING_3);
		items.add(javelinSentient);

		katanaSentient = (ItemKatana) SpartanWeaponryAPI.createKatana(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.VIRAL_2);
		items.add(katanaSentient);

		longswordSentient = (ItemLongsword) SpartanWeaponryAPI.createLongsword(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.VIRAL_3);
		items.add(longswordSentient);

		maceSentient = (ItemMace) SpartanWeaponryAPI.createMace(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.CORROSION_3);
		items.add(maceSentient);

		parryDaggerSentient = (ItemParryingDagger) SpartanWeaponryAPI.createParryingDagger(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.REPULSE_2);
		items.add(parryDaggerSentient);

		pikeSentient = (ItemPike) SpartanWeaponryAPI.createPike(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.BLEEDING_3);
		items.add(pikeSentient);

		saberSentient = (ItemSaber) SpartanWeaponryAPI.createSaber(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.BLEEDING_3);
		items.add(saberSentient);

		quarterstaffSentient = (ItemQuarterstaff) SpartanWeaponryAPI.createQuarterstaff(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IMMALLEABLE_2);
		items.add(quarterstaffSentient);

		rapierSentient = (ItemRapier) SpartanWeaponryAPI.createRapier(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.BLEEDING_2);
		items.add(rapierSentient);

		spearSentient = (ItemSpear) SpartanWeaponryAPI.createSpear(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.BLEEDING_2);
		items.add(spearSentient);

		throwingAxeSentient = (ItemThrowingAxe) SpartanWeaponryAPI.createThrowingAxe(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.CORROSION_2);
		items.add(throwingAxeSentient);

		throwingKnifeSentient = (ItemThrowingKnife) SpartanWeaponryAPI.createThrowingKnife(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.VIRAL_2);
		items.add(throwingKnifeSentient);

		warhammerSentient = (ItemWarhammer) SpartanWeaponryAPI.createWarhammer(sentientMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IMMALLEABLE_2);
		items.add(warhammerSentient);

		return items;
	}

	public static ToolMaterialEx getSentientMaterial() {
		if (sentientMaterial == null) {
			sentientMaterial = new ToolMaterialEx("sentient", "$nothing", SRPSpartanWeaponry.MODID, -1, -1, 4, ParasiteSWConfig.maxSentientDamage, 7.5f, ParasiteSWConfig.sentientBaseDmg, 18);
		}
		return sentientMaterial;
	}

	public static Item addDagger(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) {
		if (ConfigHandler.disableDagger)
			return null;

		ItemDagger dagger = new ItemNoReequipDagger("dagger_" + material.getUnlocName(), modId, material);
		dagger.setCreativeTab(tab);

		for (WeaponProperty prop : properties) {
			dagger.addWeaponProperty(prop);
		}
		return dagger;
	}

	@SubscribeEvent
	public static void onItemRegisterEvent(RegistryEvent.Register<Item> ev) {
		IForgeRegistry<Item> reg = ev.getRegistry();

		for (Item i : initalizeItems(new ArrayList<>())) {

			if (i != null) {
				reg.register(i);

				if (!(i instanceof IWeaponPropertyContainer)) {
					continue;
				}

				IWeaponPropertyContainer<?> container = (IWeaponPropertyContainer<?>) i;

				container.addWeaponProperty(ParasiteSWProperties.SLOW_2);

				if (container.hasWeaponProperty(WeaponProperties.REACH_2)) {
					tryRemoveProperty(container, WeaponProperties.REACH_2);
					container.addWeaponProperty(ParasiteSWProperties.REACH_3);
				} else if (container.hasWeaponProperty(WeaponProperties.REACH_1)) {
					tryRemoveProperty(container, WeaponProperties.REACH_1);
					container.addWeaponProperty(WeaponProperties.REACH_2);
				} else {
					container.addWeaponProperty(WeaponProperties.REACH_1);
				}

				ToolMaterialEx mat = container.getMaterialEx();
				String modelPath = mat.getUnlocName() + "/" + i.getRegistryName().getPath();

				if (mat.getPrimaryColour() >= 0 && mat.getSecondaryColour() >= 0) {
					ModelRenderRegistry.addItemToRegistry(i, new ResourceLocation(SRPSpartanWeaponry.MODID, modelPath), mat);
				} else {
					ModelRenderRegistry.addItemToRegistry(i, new ResourceLocation(SRPSpartanWeaponry.MODID, modelPath));
				}
			}
		}
	}

	public static void tryRemoveProperty(IWeaponPropertyContainer<?> cont, WeaponProperty prop) {
		if (cont instanceof ItemSwordBase) {
			List<WeaponProperty> props = ObfuscationReflectionHelper.getPrivateValue(ItemSwordBase.class, (ItemSwordBase) cont, "properties", "field_185051_m");
			props.remove(prop);
		}
	}
}
