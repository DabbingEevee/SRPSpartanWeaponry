package com.existingeevee.swparasites.init.weapons;

import java.util.ArrayList;
import java.util.List;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.Utils;
import com.existingeevee.swparasites.config.ParasiteSWConfig;
import com.existingeevee.swparasites.init.CustomWeaponCreator;
import com.existingeevee.swparasites.init.ParasiteSWProperties;
import com.existingeevee.swparasites.items.ItemParasiteCrossbow;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.init.ModelRenderRegistry;
import com.oblivioussp.spartanweaponry.item.ItemBoomerang;
import com.oblivioussp.spartanweaponry.item.ItemCrossbow;
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
import com.oblivioussp.spartanweaponry.item.ItemThrowingAxe;
import com.oblivioussp.spartanweaponry.item.ItemThrowingKnife;
import com.oblivioussp.spartanweaponry.item.ItemThrowingWeapon;
import com.oblivioussp.spartanweaponry.item.ItemWarhammer;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ParasiteSWTwisted {

	public static ToolMaterialEx twistedMaterial;

	public static ItemGreatsword greatswordTwisted = null;
	public static ItemBoomerang boomerangTwisted = null;
	public static ItemDagger daggerTwisted = null;
	public static ItemGlaive glaiveTwisted = null;
	public static ItemHalberd halberdTwisted = null;
	public static ItemHammer hammerTwisted = null;
	public static ItemJavelin javelinTwisted = null;
	public static ItemKatana katanaTwisted = null;
	public static ItemLongsword longswordTwisted = null;
	public static ItemMace maceTwisted = null;
	public static ItemParryingDagger parryDaggerTwisted = null;
	public static ItemPike pikeTwisted = null;
	public static ItemSaber saberTwisted = null;
	public static ItemQuarterstaff quarterstaffTwisted = null;
	public static ItemRapier rapierTwisted = null;
	public static ItemSpear spearTwisted = null;
	public static ItemThrowingAxe throwingAxeTwisted = null;
	public static ItemThrowingKnife throwingKnifeTwisted = null;
	public static ItemWarhammer warhammerTwisted = null;
	public static ItemCrossbow crossbowTwisted = null;

	private static List<Item> initalizeItems(List<Item> items) {

		twistedMaterial = getTwistedMaterial();

		claymoreLiving = (ItemGreatsword) SpartanWeaponryAPI.createGreatsword(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.VIRAL_1);
		if (claymoreLiving != null) {
			claymoreLiving.setTranslationKey("claymore_living");
			Utils.forceSetRegistryName(claymoreLiving, "claymore_living");
			Utils.resetAutogenName(claymoreLiving);
		}
		items.add(claymoreLiving);

		daggerLiving = (ItemDagger) CustomWeaponCreator.addDagger(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.CLOAKING_1);
		items.add(daggerLiving);

		boomerangLiving = (ItemBoomerang) CustomWeaponCreator.addBoomerang(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IMMALLEABLE_1);
		items.add(boomerangLiving);

		glaiveLiving = (ItemGlaive) SpartanWeaponryAPI.createGlaive(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.BLEEDING_2);
		items.add(glaiveLiving); 

		halberdLiving = (ItemHalberd) SpartanWeaponryAPI.createHalberd(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.CORROSION_1);
		items.add(halberdLiving);

		hammerLiving = (ItemHammer) SpartanWeaponryAPI.createHammer(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IMMALLEABLE_1);
		items.add(hammerLiving);

		javelinLiving = (ItemJavelin) CustomWeaponCreator.addJavelin(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.BLEEDING_2);
		items.add(javelinLiving);

		katanaLiving = (ItemKatana) SpartanWeaponryAPI.createKatana(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.VIRAL_1);
		items.add(katanaLiving);

		longswordLiving = (ItemLongsword) SpartanWeaponryAPI.createLongsword(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.VIRAL_2);
		items.add(longswordLiving);

		maceLiving = (ItemMace) SpartanWeaponryAPI.createMace(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.CORROSION_2);
		items.add(maceLiving);

		parryDaggerLiving = (ItemParryingDagger) SpartanWeaponryAPI.createParryingDagger(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.REPULSE_2);
		items.add(parryDaggerLiving);

		pikeLiving = (ItemPike) SpartanWeaponryAPI.createPike(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.BLEEDING_2);
		items.add(pikeLiving);

		saberLiving = (ItemSaber) SpartanWeaponryAPI.createSaber(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.BLEEDING_2);
		items.add(saberLiving);

		quarterstaffLiving = (ItemQuarterstaff) SpartanWeaponryAPI.createQuarterstaff(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IMMALLEABLE_1);
		items.add(quarterstaffLiving);

		rapierLiving = (ItemRapier) SpartanWeaponryAPI.createRapier(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.BLEEDING_1);
		items.add(rapierLiving);

		spearLiving = (ItemSpear) SpartanWeaponryAPI.createSpear(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.BLEEDING_1);
		items.add(spearLiving);

		throwingAxeLiving = (ItemThrowingAxe) CustomWeaponCreator.addThrowingAxe(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.CORROSION_1);
		items.add(throwingAxeLiving);

		throwingKnifeLiving = (ItemThrowingKnife) CustomWeaponCreator.addThrowingKnife(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.VIRAL_1);
		items.add(throwingKnifeLiving);

		warhammerLiving = (ItemWarhammer) SpartanWeaponryAPI.createWarhammer(livingMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IMMALLEABLE_1);
		items.add(warhammerLiving);
		
		crossbowLiving = new ItemParasiteCrossbow("crossbow_living", SRPSpartanWeaponry.MODID, livingMaterial).withMultiAmount(ParasiteSWConfig.livingMultishot);
		crossbowLiving.setCreativeTab(CreativeTabsSW.TAB_SW_MOD);
		Utils.resetAutogenName(crossbowLiving);
		items.add(crossbowLiving);

		return items;
	}
	
	public static ToolMaterialEx getTwistedMaterial() {
		if (twistedMaterial == null) {
			twistedMaterial = new ToolMaterialEx("twisted", "$nothing", SRPSpartanWeaponry.MODID, -1, -1, 4, ParasiteSWConfig.maxLivingDamage, 7.5f, ParasiteSWConfig.livingBaseDmg, 18);
		}
		return twistedMaterial;
	}

	@SubscribeEvent
	public static void onItemRegisterEvent(RegistryEvent.Register<Item> ev) {
		IForgeRegistry<Item> reg = ev.getRegistry();

		for (Item i : initalizeItems(new ArrayList<>())) {

			if (i != null) {
				reg.register(i);

				ToolMaterialEx mat = getLivingMaterial(); //default to living
				
				if (i instanceof IWeaponPropertyContainer) {
					IWeaponPropertyContainer<?> container = (IWeaponPropertyContainer<?>) i;
					container.addWeaponProperty(ParasiteSWProperties.HEAVY_1);
					Utils.resetAutogenName(i);
					
					if (!(i instanceof ItemThrowingWeapon)) { //throwing weapons dont work with uncapped atm, sorgy
						container.addWeaponProperty(ParasiteSWProperties.UNCAPPED);
					}
					mat = container.getMaterialEx();
				} 
				if (i instanceof ItemCrossbow) {
					ItemCrossbow crossbow = (ItemCrossbow) i;
					mat = (ToolMaterialEx) ObfuscationReflectionHelper.getPrivateValue(ItemCrossbow.class, crossbow, "material");
				}

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
