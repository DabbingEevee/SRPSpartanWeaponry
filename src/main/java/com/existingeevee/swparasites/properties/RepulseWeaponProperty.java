package com.existingeevee.swparasites.properties;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.item.IBlockingWeapon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RepulseWeaponProperty extends WeaponProperty {

	public RepulseWeaponProperty() {
		super("repulse", SRPSpartanWeaponry.MODID, 0, 0);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void attackEvent(LivingAttackEvent ev) {
		if (ev.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) ev.getEntityLiving();

			if (player.isHandActive() && !player.getActiveItemStack().isEmpty()) {
				ItemStack activeStack = player.getActiveItemStack();

				if (activeStack.getItem() instanceof IBlockingWeapon && activeStack.getItem() instanceof IWeaponPropertyContainer<?>) {
					IBlockingWeapon weapon = (IBlockingWeapon) activeStack.getItem();
					IWeaponPropertyContainer<?> container = (IWeaponPropertyContainer<?>) activeStack.getItem();
					
					if (!container.hasWeaponProperty(this)) {
						return;
					}
					
					DamageSource source = ev.getSource();
					boolean blockSuccess = false;

					if (weapon.canBlockMelee() && !source.isExplosion() && !source.isFireDamage() && !source.isMagicDamage() && !source.isProjectile() && !source.isUnblockable()) {
						blockSuccess = true;
					} else if (weapon.canBlockProjectiles() && ev.getSource().isProjectile()) {
						blockSuccess = true;
					}
					if (blockSuccess) {
						//Blocked (EZ)
						
						player.addPotionEffect(new PotionEffect(SRPPotions.RAGE_E, 5 * 20, 1));
						
					}
				}
			}
		}
	}
}