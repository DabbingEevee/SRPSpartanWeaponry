package com.existingeevee.swparasites.properties;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CloakingWeaponProperty extends WeaponPropertyWithCallback {

	public CloakingWeaponProperty() {
		super("cloaking", SRPSpartanWeaponry.MODID, 0, 0);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void onItemUpdate(ToolMaterialEx material, ItemStack stack, World world, EntityLivingBase entity, int itemSlot, boolean isSelected) {
		PotionEffect effect = entity.getActivePotionEffect(MobEffects.INVISIBILITY);

		if (entity.isSneaking() && (effect == null || effect.getDuration() <= 1) && isSelected) {
			entity.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 2, 0, false, false));
		}
	}

	private static final ThreadLocal<Boolean> isResetting = ThreadLocal.withInitial(() -> false);

	private static final double maxDetectRangeSq = 2.5 * 2.5;
	
	@SubscribeEvent
	public void onLivingSetAttackTargetEvent(LivingSetAttackTargetEvent event) {
		if (isResetting.get() || event.getTarget() == null)
			return;

		Item mainHand = event.getTarget().getHeldItemMainhand().getItem();

		if (mainHand instanceof IWeaponPropertyContainer<?>) {
			IWeaponPropertyContainer<?> container = (IWeaponPropertyContainer<?>) mainHand;
			
			boolean wasAttacked = event.getEntityLiving().getRevengeTarget() == event.getTarget();
			boolean outOfRange = event.getEntityLiving().getDistanceSq(event.getTarget()) > maxDetectRangeSq;
			
			if (container.hasWeaponProperty(this) && event.getTarget().isSneaking() && outOfRange && !wasAttacked && event.getEntityLiving() instanceof EntityLiving) {
				isResetting.set(true);
				((EntityLiving) event.getEntityLiving()).setAttackTarget(null);
				isResetting.set(false);
			}
		}
	}

}
