package com.existingeevee.swparasites.items;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.item.ItemCaestus;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class ItemParasiteGauntlet extends ItemCaestus implements IHasSRPEvolutionProgress {

	public ItemParasiteGauntlet(String unlocName, ToolMaterialEx material) {
		super(unlocName, material);
		modId = "swparasites"; // Item
		setNoRepair();

		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public ItemStack getRepairItemStack() {
		return ItemStack.EMPTY;
	}
}