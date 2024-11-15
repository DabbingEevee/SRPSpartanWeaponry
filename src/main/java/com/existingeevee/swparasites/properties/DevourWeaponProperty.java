package com.existingeevee.swparasites.properties;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

// meow
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DevourWeaponProperty extends WeaponPropertyWithCallback {

	public DevourWeaponProperty(int propLevel) {
		super("devour", SRPSpartanWeaponry.MODID, propLevel, propLevel);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
    public void onItemRightClick(PlayerInteractEvent.RightClickItem ev) {
		System.out.println("yes the right click worked");
		EntityPlayer playerIn = ev.getEntityPlayer();
		if (!playerIn.isSneaking() || playerIn.isPotionActive(MobEffects.STRENGTH)) {
			System.out.println("player is either not sneaking or has strength already");
			return;
		}
		for (int i = 0; i < playerIn.inventory.getSizeInventory(); i++) {
			ItemStack slotStack = playerIn.inventory.getStackInSlot(i);
			System.out.println("testing slot " + i + ", this is a " + slotStack.getItem().getRegistryName().toString());
			if (slotStack.getItem().getRegistryName().toString().equals("nocubessrparmory:gorepart")) {
				slotStack.setCount(slotStack.getCount()-1);
				
				playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 400, 1, false, false));
				System.out.println("strength should happen here");
				
				break;
			}
		}
    }
}