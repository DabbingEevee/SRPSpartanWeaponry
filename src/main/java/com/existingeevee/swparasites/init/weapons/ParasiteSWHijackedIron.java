package com.existingeevee.swparasites.init.weapons;

import java.util.ArrayList;
import java.util.List;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.Utils;
import com.existingeevee.swparasites.config.ParasiteSWConfig;
import com.existingeevee.swparasites.init.CustomWeaponCreator;
import com.existingeevee.swparasites.init.ParasiteSWProperties;
import com.existingeevee.swparasites.items.ItemParasiteCrossbowNocube;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.init.ModelRenderRegistry;
import com.oblivioussp.spartanweaponry.item.ItemBattleaxe;
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
import com.oblivioussp.spartanweaponry.item.ItemLongbow;
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

public class ParasiteSWHijackedIron {

	public static ToolMaterialEx hijackedIronMaterial;

	public static ItemGreatsword greatswordHijackedIron = null;
	public static ItemBoomerang boomerangHijackedIron = null;
	public static ItemDagger daggerHijackedIron = null;
	public static ItemBattleaxe battleaxeHijackedIron = null;
	public static ItemGlaive glaiveHijackedIron = null;
	public static ItemHalberd halberdHijackedIron = null;
	public static ItemHammer hammerHijackedIron = null;
	public static ItemJavelin javelinHijackedIron = null;
	public static ItemKatana katanaHijackedIron = null;
	public static ItemLongsword longswordHijackedIron = null;
	public static ItemMace maceHijackedIron = null;
	public static ItemParryingDagger parryDaggerHijackedIron = null;
	public static ItemPike pikeHijackedIron = null;
	public static ItemSaber saberHijackedIron = null;
	public static ItemQuarterstaff quarterstaffHijackedIron = null;
	public static ItemRapier rapierHijackedIron = null;
	public static ItemSpear spearHijackedIron = null;
	public static ItemThrowingAxe throwingAxeHijackedIron = null;
	public static ItemThrowingKnife throwingKnifeHijackedIron = null;
	public static ItemWarhammer warhammerHijackedIron = null;
	public static ItemLance lanceHijackedIron = null;
	public static ItemScythe scytheHijackedIron= null;
	public static ItemCrossbow crossbowHijackedIron = null;
	public static ItemLongbow longbowHijackedIron = null;

	private static List<Item> initalizeItems(List<Item> items) {

		hijackedIronMaterial = getHijackedIronMaterial();

		greatswordHijackedIron = (ItemGreatsword) SpartanWeaponryAPI.createGreatsword(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(greatswordHijackedIron);

		boomerangHijackedIron = (ItemBoomerang) CustomWeaponCreator.addBoomerang(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(boomerangHijackedIron);

		glaiveHijackedIron = (ItemGlaive) SpartanWeaponryAPI.createGlaive(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(glaiveHijackedIron); 
		
		daggerHijackedIron = (ItemDagger) SpartanWeaponryAPI.createDagger(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(daggerHijackedIron); 
		
		battleaxeHijackedIron = (ItemBattleaxe) SpartanWeaponryAPI.createBattleaxe(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(battleaxeHijackedIron); 

		halberdHijackedIron = (ItemHalberd) SpartanWeaponryAPI.createHalberd(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(halberdHijackedIron);

		javelinHijackedIron = (ItemJavelin) CustomWeaponCreator.addJavelin(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(javelinHijackedIron);

		katanaHijackedIron = (ItemKatana) SpartanWeaponryAPI.createKatana(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(katanaHijackedIron);

		longswordHijackedIron = (ItemLongsword) SpartanWeaponryAPI.createLongsword(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(longswordHijackedIron);

		maceHijackedIron = (ItemMace) SpartanWeaponryAPI.createMace(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(maceHijackedIron);

		parryDaggerHijackedIron = (ItemParryingDagger) SpartanWeaponryAPI.createParryingDagger(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT, ParasiteSWProperties.MALIGNANT_PARRY_2);
		items.add(parryDaggerHijackedIron);

		pikeHijackedIron = (ItemPike) SpartanWeaponryAPI.createPike(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(pikeHijackedIron);

		saberHijackedIron = (ItemSaber) SpartanWeaponryAPI.createSaber(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(saberHijackedIron);

		quarterstaffHijackedIron = (ItemQuarterstaff) SpartanWeaponryAPI.createQuarterstaff(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(quarterstaffHijackedIron);

		rapierHijackedIron = (ItemRapier) SpartanWeaponryAPI.createRapier(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(rapierHijackedIron);

		spearHijackedIron = (ItemSpear) SpartanWeaponryAPI.createSpear(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(spearHijackedIron);

		throwingAxeHijackedIron = (ItemThrowingAxe) CustomWeaponCreator.addThrowingAxe(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(throwingAxeHijackedIron);

		throwingKnifeHijackedIron = (ItemThrowingKnife) CustomWeaponCreator.addThrowingKnife(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(throwingKnifeHijackedIron);

		warhammerHijackedIron = (ItemWarhammer) SpartanWeaponryAPI.createWarhammer(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(warhammerHijackedIron);
		
		lanceHijackedIron = (ItemLance) SpartanWeaponryAPI.createLance(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(lanceHijackedIron);
		
		scytheHijackedIron = (ItemScythe) SpartanWeaponryAPI.createScythe(hijackedIronMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.MALIGNANT);
		items.add(scytheHijackedIron);
		
		crossbowHijackedIron = new ItemParasiteCrossbowNocube("crossbow_hijacked_iron", SRPSpartanWeaponry.MODID, hijackedIronMaterial);
		crossbowHijackedIron.setCreativeTab(CreativeTabsSW.TAB_SW_MOD);
		items.add(crossbowHijackedIron);

		//longbowHijackedIron = new ItemParasiteLongbowNocube("crossbow_hijacked_iron", SRPSpartanWeaponry.MODID, hijackedIronMaterial);
		//longbowHijackedIron.setCreativeTab(CreativeTabsSW.TAB_SW_MOD);
		//items.add(crossbowHijackedIron);
		return items;
	}
	
	public static ToolMaterialEx getHijackedIronMaterial() {
		if (hijackedIronMaterial == null) {
			hijackedIronMaterial = new ToolMaterialEx("hijacked_iron", "$nothing", SRPSpartanWeaponry.MODID, -1, -1, 4, ParasiteSWConfig.maxHijackedIronDamage, 7.5f, ParasiteSWConfig.hijackedIronBaseDmg, 18);
		}
		return hijackedIronMaterial;
	}

	@SubscribeEvent
	public static void onItemRegisterEvent(RegistryEvent.Register<Item> ev) {
		IForgeRegistry<Item> reg = ev.getRegistry();

		for (Item i : initalizeItems(new ArrayList<>())) {

			if (i != null) {
				reg.register(i);

				ToolMaterialEx mat = getHijackedIronMaterial(); //default to hijacked iron
				
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
