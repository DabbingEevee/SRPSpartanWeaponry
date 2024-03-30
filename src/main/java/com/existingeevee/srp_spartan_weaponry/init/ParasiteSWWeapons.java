package com.existingeevee.srp_spartan_weaponry.init;

import java.util.ArrayList;
import java.util.List;

import com.existingeevee.srp_spartan_weaponry.SRPSpartanWeaponry;
import com.existingeevee.srp_spartan_weaponry.Utils;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.init.ModelRenderRegistry;
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
import com.oblivioussp.spartanweaponry.item.ItemWarhammer;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class ParasiteSWWeapons {

	public static final ToolMaterialEx MATERIAL_LIVING = new ToolMaterialEx("living", "$nothing", SRPSpartanWeaponry.MODID, -1, -1, 4, 1000, 7.5f, 5.0f, 18);

	public static ItemGreatsword claymoreLiving = null; 
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
		
		claymoreLiving = (ItemGreatsword) SpartanWeaponryAPI.createGreatsword(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD)
				.setTranslationKey("claymore_living");
		Utils.forceSetRegistryName(claymoreLiving, "claymore_living");
		Utils.resetAutogenName(claymoreLiving);
		items.add(claymoreLiving);
		
		daggerLiving = (ItemDagger) SpartanWeaponryAPI.createDagger(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		items.add(daggerLiving);

		glaiveLiving = (ItemGlaive) SpartanWeaponryAPI.createGlaive(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		glaiveLiving.addWeaponProperty(ParasiteSWProperties.BLEEDING_3);
		items.add(glaiveLiving);
		
		halbardLiving = (ItemHalberd) SpartanWeaponryAPI.createHalberd(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		items.add(halbardLiving);
		
		hammerLiving = (ItemHammer) SpartanWeaponryAPI.createHammer(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		items.add(hammerLiving);
		
		javelinLiving = (ItemJavelin) SpartanWeaponryAPI.createJavelin(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		javelinLiving.addWeaponProperty(ParasiteSWProperties.BLEEDING_2);
		items.add(javelinLiving);
		
		katanaLiving = (ItemKatana) SpartanWeaponryAPI.createKatana(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		items.add(katanaLiving);
		
		longswordLiving = (ItemLongsword) SpartanWeaponryAPI.createLongsword(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		items.add(longswordLiving);
		
		maceLiving  = (ItemMace) SpartanWeaponryAPI.createMace(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		items.add(maceLiving);
		
		parryDaggerLiving = (ItemParryingDagger) SpartanWeaponryAPI.createParryingDagger(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		items.add(parryDaggerLiving);
		
		pikeLiving = (ItemPike) SpartanWeaponryAPI.createPike(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		pikeLiving.addWeaponProperty(ParasiteSWProperties.BLEEDING_2);
		items.add(pikeLiving);
		
		saberLiving = (ItemSaber) SpartanWeaponryAPI.createSaber(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		saberLiving.addWeaponProperty(ParasiteSWProperties.BLEEDING_2);
		items.add(saberLiving);
		
		quarterstaffLiving = (ItemQuarterstaff) SpartanWeaponryAPI.createQuarterstaff(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		items.add(quarterstaffLiving);
		
		rapierLiving = (ItemRapier) SpartanWeaponryAPI.createRapier(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		saberLiving.addWeaponProperty(ParasiteSWProperties.BLEEDING_1);
		items.add(rapierLiving);
		
		spearLiving = (ItemSpear) SpartanWeaponryAPI.createSpear(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		spearLiving.addWeaponProperty(ParasiteSWProperties.BLEEDING_1);
		items.add(spearLiving);
		
		throwingAxeLiving = (ItemThrowingAxe) SpartanWeaponryAPI.createThrowingAxe(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		items.add(throwingAxeLiving);
		
		throwingKnifeLiving = (ItemThrowingKnife) SpartanWeaponryAPI.createThrowingKnife(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		items.add(throwingKnifeLiving);
		
		warhammerLiving = (ItemWarhammer) SpartanWeaponryAPI.createWarhammer(MATERIAL_LIVING, SRPSpartanWeaponry.MODID, CreativeTabsSW.TAB_SW_MOD);
		items.add(warhammerLiving);
		
		return items;
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
