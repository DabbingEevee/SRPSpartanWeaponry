package com.existingeevee.swparasites.properties;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.init.ParasiteSWProperties;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

// meow
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
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
		EntityPlayer playerIn = ev.getEntityPlayer();
		
		boolean hasGoreWeapon = playerIn.getHeldItemMainhand().getItem() instanceof IWeaponPropertyContainer<?> && ((IWeaponPropertyContainer<?>) playerIn.getHeldItemMainhand().getItem()).hasWeaponProperty(ParasiteSWProperties.DEVOUR);
		
		if (!playerIn.isSneaking() || playerIn.isPotionActive(MobEffects.STRENGTH) || !hasGoreWeapon ){
			return;
		}
		
		for (int i = 0; i < playerIn.inventory.getSizeInventory(); i++) {
			ItemStack slotStack = playerIn.inventory.getStackInSlot(i);
			if (slotStack.getItem().getRegistryName().toString().equals("nocubessrparmory:gorepart")) {
				slotStack.setCount(slotStack.getCount()-1);
				
				playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 400, 1, false, false));
				playerIn.world.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_HORSE_EAT, SoundCategory.PLAYERS, 0.7F, 1.0F);
				
				break;
			}
		}
    }
}	