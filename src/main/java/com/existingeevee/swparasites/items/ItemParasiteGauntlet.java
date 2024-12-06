package com.existingeevee.swparasites.items;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.existingeevee.swparasites.Utils;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.item.ItemCaestus;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
	
	public ItemParasiteGauntlet(String unlocName, ToolMaterialEx material) {
		super(unlocName, material);
		modId = "swparasites";
		setNoRepair();

		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		System.out.println("let me also test if this works: " + Utils.getOrCreateTag(stack).getBoolean("PunchOffhand"));
		Item item = attacker.getHeldItem(EnumHand.OFF_HAND).getItem();
		
		if (!(item == this)) {
			System.out.println("gauntlet not detected, getting the hell out");
			return false;
		}
		System.out.println("gauntlet detected, continuing");
		
		boolean punchOffhand = Utils.getOrCreateTag(stack).getBoolean("PunchOffhand");
		
		System.out.println("offhand punch?" + punchOffhand);
		
		if (punchOffhand) {
			System.out.println("offhand punch should have happened here");
			attacker.setActiveHand(EnumHand.OFF_HAND);
			attacker.swingArm(EnumHand.OFF_HAND);
		}
		else {
			System.out.println("mainhand punch should have happened here");
			attacker.swingArm(EnumHand.MAIN_HAND);
		}
		
		Utils.getOrCreateTag(stack).setBoolean("PunchOffhand", !punchOffhand);
		
		System.out.println("also here too: " + Utils.getOrCreateTag(stack).getBoolean("PunchOffhand"));
		
    	return false;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		if (!playerIn.onGround) {
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
		
		WeaponProperty shockwave = this.getFirstWeaponPropertyWithType("shockwave");
		

		boolean lvl2 = shockwave.getLevel() != 1;
		if (shockwave != null) {
			if (!worldIn.isRemote) {
			playerIn.world.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ,
					SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.7F, lvl2 ? 0.5f : 1.5f);
			}
		}
		
		
		playerIn.getCooldownTracker().setCooldown(item.getItem(), playerIn.isPotionActive(SRPPotions.RAGE_E) ? 50 : 100);
		
		playerIn.swingArm(handIn);
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
	}
	
	
	@Override
	public ItemStack getRepairItemStack()
    {
		return ItemStack.EMPTY;
    }
}