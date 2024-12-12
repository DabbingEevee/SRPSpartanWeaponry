package com.existingeevee.swparasites.items;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.existingeevee.swparasites.Utils;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.item.ItemCaestus;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemParasiteGauntlet extends ItemCaestus implements IHasSRPEvolutionProgress {
	
//	private boolean toggle = false;
	
	public ItemParasiteGauntlet(String unlocName, ToolMaterialEx material) {
		super(unlocName, material);
		modId = "swparasites";
		setNoRepair();

		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!usingBothGauntlets((EntityPlayer) attacker)) {
			System.out.println("gauntlet not detected, getting the hell out");
			return true;
		}
		
		System.out.println("gauntlet detected, continuing");

		EntityPlayer player = (EntityPlayer) attacker;
		
		player.attackTargetEntityWithCurrentItem(target);
		
    	return true;
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if (usingBothGauntlets(playerIn) && !target.world.isRemote) {
			ItemStack itemMain = playerIn.getHeldItem(EnumHand.MAIN_HAND);
			ItemStack itemOff = playerIn.getHeldItem(EnumHand.OFF_HAND);
			
			playerIn.swingArm(EnumHand.OFF_HAND);
			playerIn.setHeldItem(EnumHand.MAIN_HAND, itemOff);
			
			playerIn.attackTargetEntityWithCurrentItem(target);
			
			playerIn.setHeldItem(EnumHand.MAIN_HAND, itemMain);
			
			// if entity killed from this attack, then add kill count to both gauntlets, each gauntlet gets max hp of target / 2 points
			
			
		}
		
        return true;
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