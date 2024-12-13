package com.existingeevee.swparasites.items;

import com.existingeevee.swparasites.Utils;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.item.ItemCaestus;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.MinecraftForge;

public class ItemParasiteGauntlet extends ItemCaestus implements IHasSRPEvolutionProgress {
		
	public ItemParasiteGauntlet(String unlocName, ToolMaterialEx material) {
		super(unlocName, material);
		modId = "swparasites";
		setNoRepair();

		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if (hand == EnumHand.OFF_HAND) {
			if (!target.world.isRemote) {
				playerIn.addTag("offhand_last_punched");
				Utils.attackAsPlayerWithItem(playerIn, target, stack);
				playerIn.setHeldItem(hand, stack);
			}
			playerIn.swingArm(hand);
			
			//playerIn.removeTag("offhand_last_punched");
			//target.getTags().contains("offhand_last_punched");
			
	        return true;
		}
		return false;
    }
	
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		updateCombo((EntityPlayer)attacker);
		return true;
	}
	
	@Override
	public ItemStack getRepairItemStack()
    {
		return ItemStack.EMPTY;
    }
	
	public boolean usingBothGauntlets(EntityPlayer player) {
		if (player.getHeldItem(EnumHand.OFF_HAND).getItem() == player.getHeldItem(EnumHand.MAIN_HAND).getItem()) {
			return true;
		}
		return false;
	}

	public void updateCombo(EntityPlayer playerIn) {
		
		return;
	}
}