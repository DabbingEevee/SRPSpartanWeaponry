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
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ParasiteSWPestilent {

	public static ToolMaterialEx pestilentMaterial;

	public static ItemGreatsword greatswordPestilent = null;
	public static ItemBoomerang boomerangPestilent = null;
	public static ItemDagger daggerPestilent = null;
	public static ItemGlaive glaivePestilent = null;
	public static ItemHalberd halberdPestilent = null;
	public static ItemHammer hammerPestilent = null;
	public static ItemJavelin javelinPestilent = null;
	public static ItemKatana katanaPestilent = null;
	public static ItemLongsword longswordPestilent = null;
	public static ItemMace macePestilent = null;
	public static ItemParryingDagger parryDaggerPestilent = null;
	public static ItemPike pikePestilent = null;
	public static ItemSaber saberPestilent = null;
	public static ItemQuarterstaff quarterstaffPestilent = null;
	public static ItemRapier rapierPestilent = null;
	public static ItemSpear spearPestilent = null;
	public static ItemThrowingAxe throwingAxePestilent = null;
	public static ItemThrowingKnife throwingKnifePestilent = null;
	public static ItemWarhammer warhammerPestilent = null;
	public static ItemBattleaxe battleaxePestilent = null;
	public static ItemLance lancePestilent = null;
//	public static ItemScythe scythePestilent = null;
	public static ItemCrossbow crossbowPestilent = null;

	private static List<Item> initalizeItems(List<Item> items) {

		pestilentMaterial = getPestilentMaterial();

		greatswordPestilent = (ItemGreatsword) SpartanWeaponryAPI.createGreatsword(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(greatswordPestilent);

		boomerangPestilent = (ItemBoomerang) CustomWeaponCreator.addBoomerang(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(boomerangPestilent);

		glaivePestilent = (ItemGlaive) SpartanWeaponryAPI.createGlaive(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(glaivePestilent); 

		halberdPestilent = (ItemHalberd) SpartanWeaponryAPI.createHalberd(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(halberdPestilent);
		
		hammerPestilent = (ItemHammer) SpartanWeaponryAPI.createHammer(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(hammerPestilent);

		javelinPestilent = (ItemJavelin) CustomWeaponCreator.addJavelin(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(javelinPestilent);

		katanaPestilent = (ItemKatana) SpartanWeaponryAPI.createKatana(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(katanaPestilent);

		longswordPestilent = (ItemLongsword) SpartanWeaponryAPI.createLongsword(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(longswordPestilent);

		macePestilent = (ItemMace) SpartanWeaponryAPI.createMace(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(macePestilent);

		parryDaggerPestilent = (ItemParryingDagger) SpartanWeaponryAPI.createParryingDagger(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(parryDaggerPestilent);

		pikePestilent = (ItemPike) SpartanWeaponryAPI.createPike(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(pikePestilent);

		saberPestilent = (ItemSaber) SpartanWeaponryAPI.createSaber(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(saberPestilent);

		quarterstaffPestilent = (ItemQuarterstaff) SpartanWeaponryAPI.createQuarterstaff(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(quarterstaffPestilent);

		rapierPestilent = (ItemRapier) SpartanWeaponryAPI.createRapier(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(rapierPestilent);

		spearPestilent = (ItemSpear) SpartanWeaponryAPI.createSpear(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(spearPestilent);

		throwingAxePestilent = (ItemThrowingAxe) CustomWeaponCreator.addThrowingAxe(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(throwingAxePestilent);

		throwingKnifePestilent = (ItemThrowingKnife) CustomWeaponCreator.addThrowingKnife(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(throwingKnifePestilent);

		warhammerPestilent = (ItemWarhammer) SpartanWeaponryAPI.createWarhammer(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(warhammerPestilent);
		
		battleaxePestilent = (ItemBattleaxe) SpartanWeaponryAPI.createBattleaxe(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(battleaxePestilent);
		
		lancePestilent = (ItemLance) SpartanWeaponryAPI.createLance(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
		items.add(lancePestilent);
		
//		scythePestilent = (ItemScythe) SpartanWeaponryAPI.createScythe(pestilentMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.PLAGUE_1);
//		items.add(scythePestilent);
		
		crossbowPestilent = new ItemCrossbow("crossbow_pestilent", SRPSpartanWeaponry.MODID, pestilentMaterial);
		crossbowPestilent.setCreativeTab(CreativeTabsSW.TAB_SW_MOD);
		Utils.resetAutogenName(crossbowPestilent);
		items.add(crossbowPestilent);

		return items;
	}
	
	public static ToolMaterialEx getPestilentMaterial() {
		if (pestilentMaterial == null) {
			pestilentMaterial = new ToolMaterialEx("pestilent", "$nothing", SRPSpartanWeaponry.MODID, 0xb00b69, 0x69b00b, 4, 800, 7.5f, 4, 18);
		}
		return pestilentMaterial;
	}

	@SubscribeEvent
	public static void onItemRegisterEvent(RegistryEvent.Register<Item> ev) {
		IForgeRegistry<Item> reg = ev.getRegistry();

		for (Item i : initalizeItems(new ArrayList<>())) {

			if (i != null) {
				reg.register(i);

				ToolMaterialEx mat = getPestilentMaterial(); //default to pestilent
				
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
