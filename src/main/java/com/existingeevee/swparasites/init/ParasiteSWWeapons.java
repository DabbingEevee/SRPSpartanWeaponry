package com.existingeevee.swparasites.init;

import java.util.ArrayList;
import java.util.List;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.Utils;
import com.existingeevee.swparasites.config.ParasiteSWConfig;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.init.ModelRenderRegistry;
import com.oblivioussp.spartanweaponry.item.ItemDagger;
import com.oblivioussp.spartanweaponry.item.ItemBoomerang;
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
import com.oblivioussp.spartanweaponry.item.ItemThrowingAxe;
import com.oblivioussp.spartanweaponry.item.ItemThrowingKnife;
import com.oblivioussp.spartanweaponry.item.ItemWarhammer;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ParasiteSWWeapons {

	public static ToolMaterialEx livingMaterial;

	public static ItemGreatsword claymoreLiving = null;
	public static ItemBoomerang boomerangLiving = null;
	public static ItemDagger daggerLiving = null;
	public static ItemGlaive glaiveLiving = null;
	public static ItemHalberd halbardLiving = null;
	public static ItemHammer hammerLiving = null;
	public static ItemJavelin javelinLiving = null;
	public static ItemKatana katanaLiving = null;
	public static ItemLongsword longswordLiving = null;
	public static ItemMace maceLiving = null;
	public static ItemParryingDagger parryDaggerLiving = null;
	public static ItemPike pikeLiving = null;
	public static ItemSaber saberLiving = null;
	public static ItemQuarterstaff quarterstaffLiving = null;
	public static ItemRapier rapierLiving = null;
	public static ItemSpear spearLiving = null;
	public static ItemThrowingAxe throwingAxeLiving = null;
	public static ItemThrowingKnife throwingKnifeLiving = null;
	public static ItemWarhammer warhammerLiving = null;

	private static List<Item> initalizeItems(List<Item> items) {

		livingMaterial = getLivingMaterial();

		claymoreLiving = (ItemGreatsword) SpartanWeaponryAPI.createGreatsword(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD)
				.setTranslationKey("claymore_living");
		Utils.forceSetRegistryName(claymoreLiving, "claymore_living");
		Utils.resetAutogenName(claymoreLiving);
		claymoreLiving.addWeaponProperty(ParasiteSWProperties.VIRAL_1);
		items.add(claymoreLiving);

		daggerLiving = (ItemDagger) SpartanWeaponryAPI.createDagger(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		daggerLiving.addWeaponProperty(ParasiteSWProperties.CLOAKING);
		items.add(daggerLiving);

		boomerangLiving = (ItemBoomerang) SpartanWeaponryAPI.createBoomerang(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		boomerangLiving.addWeaponProperty(ParasiteSWProperties.VIRAL_1);
		items.add(boomerangLiving);

		glaiveLiving = (ItemGlaive) SpartanWeaponryAPI.createGlaive(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		glaiveLiving.addWeaponProperty(ParasiteSWProperties.BLEEDING_2);
		items.add(glaiveLiving);

		halbardLiving = (ItemHalberd) SpartanWeaponryAPI.createHalberd(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		halbardLiving.addWeaponProperty(ParasiteSWProperties.CORROSION_1);
		items.add(halbardLiving);

		hammerLiving = (ItemHammer) SpartanWeaponryAPI.createHammer(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		hammerLiving.addWeaponProperty(ParasiteSWProperties.IMMALLEABLE);
		items.add(hammerLiving);

		javelinLiving = (ItemJavelin) SpartanWeaponryAPI.createJavelin(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		javelinLiving.addWeaponProperty(ParasiteSWProperties.BLEEDING_2);
		items.add(javelinLiving);

		katanaLiving = (ItemKatana) SpartanWeaponryAPI.createKatana(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		katanaLiving.addWeaponProperty(ParasiteSWProperties.VIRAL_1);
		items.add(katanaLiving);

		longswordLiving = (ItemLongsword) SpartanWeaponryAPI.createLongsword(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		longswordLiving.addWeaponProperty(ParasiteSWProperties.VIRAL_2);
		items.add(longswordLiving);

		maceLiving = (ItemMace) SpartanWeaponryAPI.createMace(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		maceLiving.addWeaponProperty(ParasiteSWProperties.CORROSION_2);
		items.add(maceLiving);

		parryDaggerLiving = (ItemParryingDagger) SpartanWeaponryAPI.createParryingDagger(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		parryDaggerLiving.addWeaponProperty(ParasiteSWProperties.REPULSE);
		items.add(parryDaggerLiving);

		pikeLiving = (ItemPike) SpartanWeaponryAPI.createPike(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		pikeLiving.addWeaponProperty(ParasiteSWProperties.BLEEDING_2);
		items.add(pikeLiving);

		saberLiving = (ItemSaber) SpartanWeaponryAPI.createSaber(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		saberLiving.addWeaponProperty(ParasiteSWProperties.BLEEDING_1);
		items.add(saberLiving);

		quarterstaffLiving = (ItemQuarterstaff) SpartanWeaponryAPI.createQuarterstaff(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		quarterstaffLiving.addWeaponProperty(ParasiteSWProperties.IMMALLEABLE);
		items.add(quarterstaffLiving);

		rapierLiving = (ItemRapier) SpartanWeaponryAPI.createRapier(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		rapierLiving.addWeaponProperty(ParasiteSWProperties.BLEEDING_1);
		items.add(rapierLiving);

		spearLiving = (ItemSpear) SpartanWeaponryAPI.createSpear(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		spearLiving.addWeaponProperty(ParasiteSWProperties.BLEEDING_1);
		items.add(spearLiving);

		throwingAxeLiving = (ItemThrowingAxe) SpartanWeaponryAPI.createThrowingAxe(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingAxeLiving.addWeaponProperty(ParasiteSWProperties.CORROSION_1);
		items.add(throwingAxeLiving);

		throwingKnifeLiving = (ItemThrowingKnife) SpartanWeaponryAPI.createThrowingKnife(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingKnifeLiving.addWeaponProperty(ParasiteSWProperties.VIRAL_1);
		items.add(throwingKnifeLiving);

		warhammerLiving = (ItemWarhammer) SpartanWeaponryAPI.createWarhammer(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		warhammerLiving.addWeaponProperty(ParasiteSWProperties.IMMALLEABLE);
		items.add(warhammerLiving);

		return items;
	}

	public static ToolMaterialEx getLivingMaterial() {
		if (livingMaterial == null) {
			livingMaterial = new ToolMaterialEx("living", "$nothing", SRPSpartanWeaponry.MODID, -1, -1, 4, ParasiteSWConfig.maxLivingDamage, 7.5f, ParasiteSWConfig.livingBaseDmg, 18);
		}
		return livingMaterial;
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

				container.addWeaponProperty(ParasiteSWProperties.SLOW);

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
}
