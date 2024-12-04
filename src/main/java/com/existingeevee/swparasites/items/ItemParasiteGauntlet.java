package com.existingeevee.swparasites.items;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.item.ItemCaestus;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

public class ItemParasiteGauntlet extends ItemCaestus implements IHasSRPEvolutionProgress {

	private ToolMaterialEx material;
	
	public ItemParasiteGauntlet(String unlocName, ToolMaterialEx material) {
		super(unlocName, material);
		this.material = material;

		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		if (!playerIn.onGround) {
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
		if (!worldIn.isRemote) {
			playerIn.world.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ,
					SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.7F, material.getUnlocName().equals("living") ? 1.5f : 0.5f);
		}
		
		playerIn.getCooldownTracker().setCooldown(item.getItem(), playerIn.isPotionActive(SRPPotions.RAGE_E) ? 25 : 50);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
	}
	
	
	@Override
	public ItemStack getRepairItemStack()
    {
		return new ItemStack(Items.LEATHER, 1, OreDictionary.WILDCARD_VALUE);
    }
}