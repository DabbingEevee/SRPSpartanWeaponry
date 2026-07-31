package com.existingeevee.swparasites.properties;

import java.text.DecimalFormat;
import java.util.List;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.item.hijacked.HijackedHitEffects;
import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.config.ParasiteSWConfig;
import com.existingeevee.swparasites.init.ParasiteSWProperties;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.item.IBlockingWeapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MalignantParryWeaponProperty extends WeaponProperty {

	final int propLevel;
	
	public MalignantParryWeaponProperty(int propLevel) {
		super("malignantparry", SRPSpartanWeaponry.MODID, propLevel, propLevel);
		MinecraftForge.EVENT_BUS.register(this);
		
		this.propLevel = propLevel;
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void attackEvent(LivingAttackEvent ev) {
		if (ev.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) ev.getEntityLiving();
			Entity entity = ev.getSource().getTrueSource();
			
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
						double procChance = (propLevel==1 ? 0.33 : 1);
						if ((Math.random() <= procChance || procChance == 1) && !player.world.isRemote) {//jank ahh code
							EntityLivingBase attacker = (EntityLivingBase) entity;
							HijackedHitEffects.apply(player, attacker);
						}
					}
				}
			}
		}
	}
	
	private static final DecimalFormat FORMATTER = new DecimalFormat("0.##");
	
	@Override
	@SideOnly(Side.CLIENT)
	protected void addTooltipDescription(ItemStack stack, List<String> tooltip) {
		String percent = FORMATTER
				.format((propLevel==1d ? 0.33d : 1d) * 100d);

		System.out.println("yes dumbass, this runs");
		
		tooltip.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "  " + SpartanWeaponryAPI.internalHandler
				.translateString(type + ".desc", "tooltip", modId).replace("$s", percent + "%"));
	}
}