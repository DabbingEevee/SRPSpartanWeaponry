package com.existingeevee.swparasites.items;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.oblivioussp.spartanshields.item.ItemShieldBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemBucklerShield extends ItemShieldBase {

	int level = 0;

	public ItemBucklerShield(String unlocName, int maxDurability, int potionLevel) {
		super(unlocName);

		this.level = potionLevel;
		this.setMaxDamage(maxDurability);

		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void attackEvent(LivingAttackEvent ev) {
		if (ev.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) ev.getEntityLiving();

			if (player.isHandActive() && !player.getActiveItemStack().isEmpty()) {
				ItemStack activeStack = player.getActiveItemStack();

				if (activeStack.getItem() == this && player.canBlockDamageSource(ev.getSource())) {
					// Yaya woo we blocked it
					player.addPotionEffect(new PotionEffect(SRPPotions.RAGE_E, 5 * 20, level));
				}
			}
		}
	}

}
