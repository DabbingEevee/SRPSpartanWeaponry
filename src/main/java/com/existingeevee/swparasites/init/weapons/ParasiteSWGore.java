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

public class ParasiteSWGore {

	public static ToolMaterialEx goreMaterial;

	public static ItemGreatsword greatswordGore = null;
	public static ItemBoomerang boomerangGore = null;
	public static ItemDagger daggerGore = null;
	public static ItemGlaive glaiveGore = null;
	public static ItemHalberd halberdGore = null;
	public static ItemHammer hammerGore = null;
	public static ItemJavelin javelinGore = null;
	public static ItemKatana katanaGore = null;
	public static ItemLongsword longswordGore = null;
	public static ItemMace maceGore = null;
	public static ItemParryingDagger parryDaggerGore = null;
	public static ItemPike pikeGore = null;
	public static ItemSaber saberGore = null;
	public static ItemQuarterstaff quarterstaffGore = null;
	public static ItemRapier rapierGore = null;
	public static ItemSpear spearGore = null;
	public static ItemThrowingAxe throwingAxeGore = null;
	public static ItemThrowingKnife throwingKnifeGore = null;
	public static ItemWarhammer warhammerGore = null;
	public static ItemLance lanceGore = null;
	public static ItemScythe scytheGore = null;
	public static ItemCrossbow crossbowGore = null;

	private static List<Item> initalizeItems(List<Item> items) {

		goreMaterial = getGoreMaterial();

		greatswordGore = (ItemGreatsword) SpartanWeaponryAPI.createGreatsword(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(greatswordGore);
		
		daggerGore = (ItemDagger) SpartanWeaponryAPI.createDagger(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(daggerGore);

		boomerangGore = (ItemBoomerang) CustomWeaponCreator.addBoomerang(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(boomerangGore);

		glaiveGore = (ItemGlaive) SpartanWeaponryAPI.createGlaive(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(glaiveGore); 

		halberdGore = (ItemHalberd) SpartanWeaponryAPI.createHalberd(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(halberdGore);
		
		hammerGore = (ItemHammer) SpartanWeaponryAPI.createHammer(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(hammerGore);

		javelinGore = (ItemJavelin) CustomWeaponCreator.addJavelin(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(javelinGore);

		katanaGore = (ItemKatana) SpartanWeaponryAPI.createKatana(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(katanaGore);

		longswordGore = (ItemLongsword) SpartanWeaponryAPI.createLongsword(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(longswordGore);

		maceGore = (ItemMace) SpartanWeaponryAPI.createMace(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(maceGore);

		parryDaggerGore = (ItemParryingDagger) SpartanWeaponryAPI.createParryingDagger(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(parryDaggerGore);

		pikeGore = (ItemPike) SpartanWeaponryAPI.createPike(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(pikeGore);

		saberGore = (ItemSaber) SpartanWeaponryAPI.createSaber(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(saberGore);

		quarterstaffGore = (ItemQuarterstaff) SpartanWeaponryAPI.createQuarterstaff(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(quarterstaffGore);

		spearGore = (ItemSpear) SpartanWeaponryAPI.createSpear(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(spearGore);

		throwingAxeGore = (ItemThrowingAxe) CustomWeaponCreator.addThrowingAxe(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(throwingAxeGore);

		throwingKnifeGore = (ItemThrowingKnife) CustomWeaponCreator.addThrowingKnife(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(throwingKnifeGore);

		warhammerGore = (ItemWarhammer) SpartanWeaponryAPI.createWarhammer(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(warhammerGore);
		
		lanceGore = (ItemLance) SpartanWeaponryAPI.createLance(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(lanceGore);
		
		scytheGore = (ItemScythe) SpartanWeaponryAPI.createScythe(goreMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.DEVOUR);
		items.add(scytheGore);
		
		crossbowGore = new ItemCrossbow("crossbow_gore", SRPSpartanWeaponry.MODID, goreMaterial);
		crossbowGore.setCreativeTab(CreativeTabsSW.TAB_SW_MOD);
		Utils.resetAutogenName(crossbowGore);
		items.add(crossbowGore);

		return items;
	}
	
	public static ToolMaterialEx getGoreMaterial() {
		if (goreMaterial == null) {
			goreMaterial = new ToolMaterialEx("gore", "$nothing", SRPSpartanWeaponry.MODID, 0xb00b69, 0x69b00b, 6, 800, 7.5f, 7, 18);
		}
		return goreMaterial;
	}

	@SubscribeEvent
	public static void onItemRegisterEvent(RegistryEvent.Register<Item> ev) { //ev? like like existingeevee?
		IForgeRegistry<Item> reg = ev.getRegistry();

		for (Item i : initalizeItems(new ArrayList<>())) {

			if (i != null) {
				reg.register(i);

				ToolMaterialEx mat = getGoreMaterial(); //default to gore
				
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
