package com.existingeevee.swparasites.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemVilePlate extends ItemShield {

	public ItemVilePlate() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name").trim();
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void attackEvent(LivingAttackEvent ev) {
		if (ev.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) ev.getEntityLiving();

			if (player.isHandActive() && !player.getActiveItemStack().isEmpty()) {
				ItemStack activeStack = player.getActiveItemStack();

				if (activeStack.getItem() == this && player.canBlockDamageSource(ev.getSource())) {
					// Yaya woo we blocked it
					if (Math.random() < 0.34 && !player.world.isRemote) {
						player.getCooldownTracker().setCooldown(activeStack.getItem(), 50);
						player.resetActiveHand();
						player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.PLAYERS, 0.7F, 1.0F);
					}
				}
			}
		}
	}

}
