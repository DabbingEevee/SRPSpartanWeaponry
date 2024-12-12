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
				Utils.attackAsPlayerWithItem(playerIn, target, stack);
				playerIn.setHeldItem(hand, stack);
			}
			playerIn.resetCooldown();
			playerIn.swingArm(hand);
	        return true;
		}
		return false;
    }
	
//	@Override
//	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
//		ItemStack item = playerIn.getHeldItem(handIn);
//
//		if (playerIn.onGround && playerIn.isSneaking()) {
//			WeaponProperty shockwave = this.getFirstWeaponPropertyWithType("shockwave");
//			
//
//			boolean lvl2 = shockwave.getLevel() != 1;
//			if (shockwave != null) {
//				if (!worldIn.isRemote) {
//				playerIn.world.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ,
//						SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.7F, lvl2 ? 0.5f : 1.5f);
//				}
//			}
//			
//			
//			playerIn.getCooldownTracker().setCooldown(item.getItem(), playerIn.isPotionActive(SRPPotions.RAGE_E) ? 50 : 100);
//			
//			playerIn.swingArm(handIn);
//			
//			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
//		}
//		
//		return super.onItemRightClick(worldIn, playerIn, handIn);
//	}
	
	
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

}