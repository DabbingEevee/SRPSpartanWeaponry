package com.existingeevee.swparasites.init.weapons;

import java.util.ArrayList;
import java.util.List;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.Utils;
import com.existingeevee.swparasites.init.CustomWeaponCreator;
import com.existingeevee.swparasites.init.ParasiteSWProperties;
import com.existingeevee.swparasites.items.ItemParasiteCrossbowNocube;
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

public class ParasiteSWEvolution {

	public static ToolMaterialEx evolutionMaterial;

	public static ItemGreatsword greatswordEvolution = null;
	public static ItemBoomerang boomerangEvolution = null;
	public static ItemDagger daggerEvolution = null;
	public static ItemGlaive glaiveEvolution = null;
	public static ItemHalberd halberdEvolution = null;
	public static ItemHammer hammerEvolution = null;
	public static ItemJavelin javelinEvolution = null;
	public static ItemKatana katanaEvolution = null;
	public static ItemLongsword longswordEvolution = null;
	public static ItemMace maceEvolution = null;
	public static ItemParryingDagger parryDaggerEvolution = null;
	public static ItemPike pikeEvolution = null;
	public static ItemSaber saberEvolution = null;
	public static ItemQuarterstaff quarterstaffEvolution = null;
	public static ItemRapier rapierEvolution = null;
	public static ItemSpear spearEvolution = null;
	public static ItemThrowingAxe throwingAxeEvolution = null;
	public static ItemThrowingKnife throwingKnifeEvolution = null;
	public static ItemWarhammer warhammerEvolution = null;
	public static ItemLance lanceEvolution = null;
	public static ItemCrossbow crossbowEvolution = null;

	private static List<Item> initalizeItems(List<Item> items) {

		evolutionMaterial = getEvolutionMaterial();

		greatswordEvolution = (ItemGreatsword) SpartanWeaponryAPI.createGreatsword(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(greatswordEvolution);

		boomerangEvolution = (ItemBoomerang) CustomWeaponCreator.addBoomerang(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(boomerangEvolution);

		glaiveEvolution = (ItemGlaive) SpartanWeaponryAPI.createGlaive(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(glaiveEvolution); 

		halberdEvolution = (ItemHalberd) SpartanWeaponryAPI.createHalberd(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(halberdEvolution);
		
		hammerEvolution = (ItemHammer) SpartanWeaponryAPI.createHammer(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(hammerEvolution);

		javelinEvolution = (ItemJavelin) CustomWeaponCreator.addJavelin(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(javelinEvolution);

		katanaEvolution = (ItemKatana) SpartanWeaponryAPI.createKatana(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(katanaEvolution);

		longswordEvolution = (ItemLongsword) SpartanWeaponryAPI.createLongsword(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(longswordEvolution);

		maceEvolution = (ItemMace) SpartanWeaponryAPI.createMace(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(maceEvolution);

		parryDaggerEvolution = (ItemParryingDagger) SpartanWeaponryAPI.createParryingDagger(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(parryDaggerEvolution);

		pikeEvolution = (ItemPike) SpartanWeaponryAPI.createPike(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(pikeEvolution);

		saberEvolution = (ItemSaber) SpartanWeaponryAPI.createSaber(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(saberEvolution);

		quarterstaffEvolution = (ItemQuarterstaff) SpartanWeaponryAPI.createQuarterstaff(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(quarterstaffEvolution);

		rapierEvolution = (ItemRapier) SpartanWeaponryAPI.createRapier(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(rapierEvolution);

		spearEvolution = (ItemSpear) SpartanWeaponryAPI.createSpear(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(spearEvolution);

		throwingAxeEvolution = (ItemThrowingAxe) CustomWeaponCreator.addThrowingAxe(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(throwingAxeEvolution);

		throwingKnifeEvolution = (ItemThrowingKnife) CustomWeaponCreator.addThrowingKnife(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(throwingKnifeEvolution);

		warhammerEvolution = (ItemWarhammer) SpartanWeaponryAPI.createWarhammer(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(warhammerEvolution);
		
		lanceEvolution = (ItemLance) SpartanWeaponryAPI.createLance(evolutionMaterial, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD, ParasiteSWProperties.IGNITE_1);
		items.add(lanceEvolution);
		
		crossbowEvolution = new ItemParasiteCrossbowNocube("crossbow_evolution", SRPSpartanWeaponry.MODID, evolutionMaterial);
		crossbowEvolution.setCreativeTab(CreativeTabsSW.TAB_SW_MOD);
		Utils.resetAutogenName(crossbowEvolution);
		items.add(crossbowEvolution);

		return items;
	}
	
	public static ToolMaterialEx getEvolutionMaterial() {
		if (evolutionMaterial == null) {
			evolutionMaterial = new ToolMaterialEx("evolution", "$nothing", SRPSpartanWeaponry.MODID, 0xb00b69, 0x69b00b, 8, 800, 7.5f, 16, 18);
		}
		return evolutionMaterial;
	}

	@SubscribeEvent
	public static void onItemRegisterEvent(RegistryEvent.Register<Item> ev) {
		IForgeRegistry<Item> reg = ev.getRegistry();

		for (Item i : initalizeItems(new ArrayList<>())) {

			if (i != null) {
				reg.register(i);

				ToolMaterialEx mat = getEvolutionMaterial(); //default to evolution
				
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
