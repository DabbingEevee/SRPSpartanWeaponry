package com.existingeevee.swparasites.items;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.item.ItemDagger;

import net.minecraft.item.ItemStack;

public class ItemNoReequipDagger extends ItemDagger {

	public ItemNoReequipDagger(String unlocName, String externalModId, ToolMaterialEx material) {
		super(unlocName, externalModId, material);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return !ItemStack.areItemsEqualIgnoreDurability(oldStack, newStack);
	}

}
