package com.existingeevee.swparasites.handlers;

import com.existingeevee.swparasites.Utils;
import com.existingeevee.swparasites.items.ItemParasiteCrossbow;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBolt;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CrossbowBoltHandler {

	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent e) {
		Entity immSource = e.getSource().getImmediateSource();
		
		if (immSource instanceof EntityBolt && immSource.getTags().contains("ParasiteCrossbowMultifire")) {
			e.getEntityLiving().hurtResistantTime = Math.min(1, e.getEntityLiving().maxHurtResistantTime);
			
			Utils.executeInNTicks(() -> {
				Entity shooter = ((EntityBolt) immSource).shootingEntity;
				if (e.getEntityLiving().getHealth() <= 0 && shooter instanceof EntityPlayer && shooter.world instanceof WorldServer) {
					Vec3d start = Utils.getCenter(e.getEntityLiving().getEntityBoundingBox());
					Vec3d end = Utils.getCenter(shooter.getEntityBoundingBox());
					
					Vec3d path = end.subtract(start);
					double pathMagnitudeSq = path.lengthSquared();
					
					Vec3d unit = path.normalize();
					
					for (Vec3d cur = Vec3d.ZERO; cur.lengthSquared() < pathMagnitudeSq; cur = cur.add(unit.scale(0.1))) {
						Vec3d curPos = start.add(cur);
						((WorldServer) shooter.world).spawnParticle(EnumParticleTypes.REDSTONE, curPos.x, curPos.y, curPos.z, 1, 0, 0, 0, 0d);
					}
					if (shooter instanceof EntityLivingBase) {
						EntityLivingBase living = (EntityLivingBase) shooter;
						if (living.getHeldItemMainhand().getItem() instanceof ItemParasiteCrossbow) {
							add(living.getHeldItemMainhand(), (int) (e.getEntityLiving().getMaxHealth()));
						}
					}
				}
			}, 1);
		}
	}
	
	public static void add(ItemStack stack, int amount) {
		NBTTagCompound compound = stack.getTagCompound();
		if (compound == null) {
			compound = new NBTTagCompound();
		}
		if (compound.hasKey("srpkills")) {
			final int key = (int) (compound.getInteger("srpkills") + amount);
			compound.setInteger("srpkills", key);
		} else {
			compound.setInteger("srpkills", amount);
		}
		stack.setTagCompound(compound);
	}
}
