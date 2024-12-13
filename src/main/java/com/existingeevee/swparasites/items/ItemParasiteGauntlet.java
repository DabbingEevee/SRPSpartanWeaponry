package com.existingeevee.swparasites.items;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.Utils;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.item.ItemCaestus;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemParasiteGauntlet extends ItemCaestus implements IHasSRPEvolutionProgress {

	public ItemParasiteGauntlet(String unlocName, ToolMaterialEx material) {
		super(unlocName, material);
		modId = "swparasites"; // Item
		setNoRepair();

		MinecraftForge.EVENT_BUS.register(this);
	}

	public void attackTargetGauntlet(ItemStack stack, EntityPlayer playerIn, Entity target, EnumHand hand) {
		int comboCount = playerIn.getEntityData().getInteger(SRPSpartanWeaponry.MODID + ".ParasiteGauntlet_ComboCount");

		//TODO attr using comboCount
		
		Utils.attackAsPlayerWithItem(playerIn, target, stack);
		playerIn.setHeldItem(hand, stack);

		boolean isMain = hand == EnumHand.MAIN_HAND;
		boolean diffHand = playerIn.getEntityData().getBoolean(SRPSpartanWeaponry.MODID + ".ParasiteGauntlet_LastMain") != isMain;

		playerIn.getEntityData().setBoolean(SRPSpartanWeaponry.MODID + ".ParasiteGauntlet_LastMain", isMain);
		playerIn.getEntityData().setInteger(SRPSpartanWeaponry.MODID + ".ParasiteGauntlet_ComboCount", diffHand ? comboCount + 1 : 0);
		playerIn.swingArm(hand);
	}

	@SubscribeEvent
	public void onEntityInteract(EntityInteractSpecific ev) {		
		if (ev.getWorld().isRemote) {
			return;
		}
		
		if (ev.getEntityPlayer().getHeldItemOffhand().getItem() == this) {
			attackTargetGauntlet(ev.getEntityPlayer().getHeldItemOffhand(), ev.getEntityPlayer(), ev.getTarget(), EnumHand.OFF_HAND);
			ev.setCanceled(true);
		}
	}

	protected static final ThreadLocal<Boolean> isRunning = ThreadLocal.withInitial(() -> false);
	
	@SubscribeEvent
	public void onAttackEntityEvent(AttackEntityEvent ev) {
		if (ev.getEntity().world.isRemote || isRunning.get())
			return;

		if (ev.getEntityPlayer().getHeldItemMainhand().getItem() == this) {
			isRunning.set(true);
			attackTargetGauntlet(ev.getEntityPlayer().getHeldItemMainhand(), ev.getEntityPlayer(), ev.getTarget(), EnumHand.MAIN_HAND);
			isRunning.set(false);
			ev.setCanceled(true);
		}
	}

	@Override
	public ItemStack getRepairItemStack() {
		return ItemStack.EMPTY;
	}

	public static boolean usingBothGauntlets(EntityLivingBase entityLivingBase) {
		return entityLivingBase.getHeldItemMainhand().getItem() instanceof ItemParasiteGauntlet && 
				entityLivingBase.getHeldItemOffhand().getItem() instanceof ItemParasiteGauntlet;
	}
}