package com.existingeevee.swparasites.init.weapons;

import java.util.ArrayList;
import java.util.List;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.Utils;
import com.existingeevee.swparasites.init.CustomWeaponCreator;
import com.existingeevee.swparasites.init.ParasiteSWProperties;
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
import com.oblivioussp.spartanweaponry.item.ItemLance;
import com.oblivioussp.spartanweaponry.item.ItemLongsword;
import com.oblivioussp.spartanweaponry.item.ItemMace;
import com.oblivioussp.spartanweaponry.item.ItemParryingDagger;
import com.oblivioussp.spartanweaponry.item.ItemPike;
import com.oblivioussp.spartanweaponry.item.ItemQuarterstaff;
import com.oblivioussp.spartanweaponry.item.ItemRapier;
import com.oblivioussp.spartanweaponry.item.ItemSaber;
import com.oblivioussp.spartanweaponry.item.ItemScythe;
import com.oblivioussp.spartanweaponry.item.ItemSpear;
import com.oblivioussp.spartanweaponry.item.ItemThrowingAxe;
import com.oblivioussp.spartanweaponry.item.ItemThrowingKnife;
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
	public static ItemLance lanceTwisted = null;
	public static ItemScythe scytheTwisted= null;
	public static ItemCrossbow crossbowTwisted = null;

	private static List<Item> initalizeItems(List<Item> items) {

		twistedMaterial = getTwistedMaterial();

		greatswordTwisted = (ItemGreatsword) SpartanWeaponryAPI.createGreatsword(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_1);
		items.add(greatswordTwisted);

		boomerangTwisted = (ItemBoomerang) CustomWeaponCreator.addBoomerang(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_1);
		items.add(boomerangTwisted);

		glaiveTwisted = (ItemGlaive) SpartanWeaponryAPI.createGlaive(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_2);
		items.add(glaiveTwisted); 

		halberdTwisted = (ItemHalberd) SpartanWeaponryAPI.createHalberd(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_1);
		items.add(halberdTwisted);

		javelinTwisted = (ItemJavelin) CustomWeaponCreator.addJavelin(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_2);
		items.add(javelinTwisted);

		katanaTwisted = (ItemKatana) SpartanWeaponryAPI.createKatana(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_1);
		items.add(katanaTwisted);

		longswordTwisted = (ItemLongsword) SpartanWeaponryAPI.createLongsword(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_1);
		items.add(longswordTwisted);

		maceTwisted = (ItemMace) SpartanWeaponryAPI.createMace(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_2);
		items.add(maceTwisted);

		parryDaggerTwisted = (ItemParryingDagger) SpartanWeaponryAPI.createParryingDagger(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_1);
		items.add(parryDaggerTwisted);

		pikeTwisted = (ItemPike) SpartanWeaponryAPI.createPike(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_1);
		items.add(pikeTwisted);

		saberTwisted = (ItemSaber) SpartanWeaponryAPI.createSaber(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_1);
		items.add(saberTwisted);

		quarterstaffTwisted = (ItemQuarterstaff) SpartanWeaponryAPI.createQuarterstaff(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_2);
		items.add(quarterstaffTwisted);

		rapierTwisted = (ItemRapier) SpartanWeaponryAPI.createRapier(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_1);
		items.add(rapierTwisted);

		spearTwisted = (ItemSpear) SpartanWeaponryAPI.createSpear(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_1);
		items.add(spearTwisted);

		throwingAxeTwisted = (ItemThrowingAxe) CustomWeaponCreator.addThrowingAxe(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_1);
		items.add(throwingAxeTwisted);

		throwingKnifeTwisted = (ItemThrowingKnife) CustomWeaponCreator.addThrowingKnife(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_1);
		items.add(throwingKnifeTwisted);

		warhammerTwisted = (ItemWarhammer) SpartanWeaponryAPI.createWarhammer(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_1);
		items.add(warhammerTwisted);
		
		lanceTwisted = (ItemLance) SpartanWeaponryAPI.createLance(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_2);
		items.add(lanceTwisted);
		
		scytheTwisted = (ItemScythe) SpartanWeaponryAPI.createScythe(twistedMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.ATROPHY_1);
		items.add(scytheTwisted);
		
		crossbowTwisted = new ItemCrossbow("crossbow_twisted", SRPSpartanWeaponry.MODID, twistedMaterial);
		crossbowTwisted.setCreativeTab(CreativeTabsSW.TAB_SW_MOD);
		Utils.resetAutogenName(crossbowTwisted);
		items.add(crossbowTwisted);

		return items;
	}
	
	public static ToolMaterialEx getTwistedMaterial() {
		if (twistedMaterial == null) {
			twistedMaterial = new ToolMaterialEx("twisted", "$nothing", SRPSpartanWeaponry.MODID, 0xb00b69, 0x69b00b, 3, 800, 7.5f, 3, 18);
		}
		return twistedMaterial;
	}

	@SubscribeEvent
	public static void onItemRegisterEvent(RegistryEvent.Register<Item> ev) {
		IForgeRegistry<Item> reg = ev.getRegistry();

		for (Item i : initalizeItems(new ArrayList<>())) {

			if (i != null) {
				reg.register(i);

				ToolMaterialEx mat = getTwistedMaterial(); //default to twisted
				
				if (i instanceof IWeaponPropertyContainer) {
					IWeaponPropertyContainer<?> container = (IWeaponPropertyContainer<?>) i;
					Utils.resetAutogenName(i);
					
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
