package com.existingeevee.swparasites.properties;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;
import com.oblivioussp.spartanweaponry.item.ItemThrowingWeapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;

public class ImmalleableWeaponProperty extends WeaponPropertyWithCallback {

	final int immalLevel;
	
	public ImmalleableWeaponProperty(int propLevel) {
		super("immalleable", SRPSpartanWeaponry.MODID, propLevel, propLevel);
		immalLevel = propLevel;
	}

	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
		if (!attacker.world.isRemote) {
			double chance = this.getMagnitude() / 8;
			ItemStack tool = ((EntityPlayer) attacker).getHeldItemMainhand();
			
			if (attacker instanceof EntityPlayer && ((EntityPlayer) attacker).getCooldownTracker().hasCooldown(tool.getItem())) {
				return;
			}
			if (attacker.world.rand.nextDouble() < chance) {
				target.addPotionEffect(new PotionEffect(SRPPotions.RES_E, 10+(immalLevel*20), 0, false, false));
				if (!(stack.getItem() instanceof ItemThrowingWeapon)) {
					((EntityPlayer) attacker).getCooldownTracker().setCooldown(stack.getItem(), 60);
				}
				attacker.world.playSound(null, attacker.posX, attacker.posY, attacker.posZ, SRPSounds.ADAPTATION_P, SoundCategory.PLAYERS, 0.7F, 0.5F);
			}
		}
	}
}
