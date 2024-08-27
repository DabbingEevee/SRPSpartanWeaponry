package com.existingeevee.swparasites;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.oblivioussp.spartanweaponry.item.ItemCrossbow;
import com.oblivioussp.spartanweaponry.item.ItemSwordBase;
import com.oblivioussp.spartanweaponry.item.ItemThrowingWeapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;

public class Utils {

	public static void forceSetRegistryName(Impl<?> impl, String str) {
		if (impl == null)
			return;
		ObfuscationReflectionHelper.setPrivateValue(Impl.class, impl, GameData.checkPrefix(str, true), "registryName");
	}

	public static void resetAutogenName(Item impl) {
		if (impl instanceof ItemSwordBase)
			ObfuscationReflectionHelper.setPrivateValue(ItemSwordBase.class, (ItemSwordBase) impl, null, "displayName");
	
		if (impl instanceof ItemCrossbow)
			ObfuscationReflectionHelper.setPrivateValue(ItemCrossbow.class, (ItemCrossbow) impl, null, "displayName");

		if (impl instanceof ItemThrowingWeapon)
			ObfuscationReflectionHelper.setPrivateValue(ItemThrowingWeapon.class, (ItemThrowingWeapon) impl, null, "displayName");
	
	}

	public static void executeInNTicks(Runnable executor, int executeIn) {
		new Object() {
			private int ticks = 0;
			private float waitTicks;

			public void start(int waitTicks) {
				this.waitTicks = waitTicks;
				MinecraftForge.EVENT_BUS.register(this);
			}

			@SubscribeEvent
			public void tick(TickEvent.ServerTickEvent event) {
				if (event.phase == TickEvent.Phase.END) {
					this.ticks += 1;
					if (this.ticks >= this.waitTicks) {
						run();
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}
			}

			private void run() {
				executor.run();
			}
		}.start(executeIn);
	}
	
	public static RayTraceResult rayTrace(EntityLivingBase entityLiving, double maxRange, List<Entity> exclude) {
		return rayTrace(entityLiving, maxRange, exclude, true);
	}


	public static RayTraceResult rayTrace(EntityLivingBase entityLiving, double maxRange, List<Entity> exclude, boolean affectedByBlocks) {
		Vec3d start = entityLiving.getPositionEyes(0.5f);
		Vec3d lookVec = entityLiving.getLookVec();

		exclude = exclude == null ? new ArrayList<>() : new ArrayList<>(exclude);
		exclude.add(entityLiving);

		return rayTrace(start, lookVec, entityLiving.world, maxRange, exclude::contains, affectedByBlocks, false);
	}

	public static RayTraceResult rayTrace(Vec3d start, Vec3d direction, World world, double maxRange, Predicate<Entity> exclude, boolean affectedByBlocks, boolean ignoreNoBounding) {
		Vec3d end = start.add(direction.scale(maxRange));
		RayTraceResult firstTrace = affectedByBlocks ? world.rayTraceBlocks(start, end, false, ignoreNoBounding, true) : null;
		AxisAlignedBB area = new AxisAlignedBB(start, firstTrace != null ? firstTrace.hitVec : end);
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(null, area);

		Entity closestValid = null;
		double closestDistSq = Double.MAX_VALUE;

		for (Entity e : entities) {
			if (!(e instanceof EntityLivingBase) || (exclude != null && exclude.test(e))) {
				continue;
			}

			RayTraceResult intercept = e.getEntityBoundingBox().calculateIntercept(start, end);

			if (intercept != null) {
				double distSq = intercept.hitVec.squareDistanceTo(start);
				if (closestDistSq > distSq) {
					closestValid = e;
					closestDistSq = distSq;
				}
			}
		}

		if (closestValid != null) {
			return new RayTraceResult(closestValid);
		} else if (firstTrace != null) {
			return firstTrace;
		} else {
			return new RayTraceResult(RayTraceResult.Type.MISS, end, EnumFacing.DOWN, new BlockPos(end));
		}
	}
	
	public static Vec3d getCenter(AxisAlignedBB box) {
		return new Vec3d(box.minX + (box.maxX - box.minX) * 0.5D, box.minY + (box.maxY - box.minY) * 0.5D, box.minZ + (box.maxZ - box.minZ) * 0.5D);
	}
	
}
