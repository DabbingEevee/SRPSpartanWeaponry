package com.existingeevee.swparasites.properties;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

// meow
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DevourWeaponProperty extends WeaponPropertyWithCallback {

	public DevourWeaponProperty(int propLevel) {
		super("devour", SRPSpartanWeaponry.MODID, propLevel, propLevel);
	}

	@SubscribeEvent
    public void onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!playerIn.isSneaking() || playerIn.isPotionActive(MobEffects.STRENGTH)) {
			return;
		}
		
		for (int i = 0; i < playerIn.inventory.getSizeInventory(); i++) {
			ItemStack slotStack = playerIn.inventory.getStackInSlot(i);
			if (slotStack.getItem().getRegistryName().toString() == "nocubessrparmory:gorepart") {
				slotStack.setCount(slotStack.getCount()-1);
				
				playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 400, 1, false, false));
			}
		}
    }
}