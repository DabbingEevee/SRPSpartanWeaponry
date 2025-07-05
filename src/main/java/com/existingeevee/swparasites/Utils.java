package com.existingeevee.swparasites;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.oblivioussp.spartanweaponry.item.ItemCrossbow;
import com.oblivioussp.spartanweaponry.item.ItemSwordBase;
import com.oblivioussp.spartanweaponry.item.ItemThrowingWeapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketEntityEquipment;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
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
		Vec3d endTrace = firstTrace != null ? firstTrace.hitVec : end;
		
		AxisAlignedBB area = new AxisAlignedBB(start.x, start.y, start.z, endTrace.x, endTrace.y, endTrace.z);
		
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
	
	public static NBTTagCompound getOrCreateTag(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        return stack.getTagCompound();
    }

    public static NBTTagCompound getOrEmptyTag(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            return new NBTTagCompound();
        }
        return stack.getTagCompound();
    }
    
	private static final Field ticksSinceLastAtt = ObfuscationReflectionHelper.findField(EntityLivingBase.class, "field_184617_aD");
	private static final Field handInventory = ObfuscationReflectionHelper.findField(EntityLivingBase.class, "field_184630_bs");
	private static final Field armorArray = ObfuscationReflectionHelper.findField(EntityLivingBase.class, "field_184631_bt");

	@SuppressWarnings("unchecked")
	public static void refreshAttributes(EntityLivingBase entity) {
		if (entity.world.isRemote)
			return;
		try {
			for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values()) {
				ItemStack itemstack;

				switch (entityequipmentslot.getSlotType()) {
				case HAND:
					itemstack = ((NonNullList<ItemStack>) handInventory.get(entity)).get(entityequipmentslot.getIndex());
					break;
				case ARMOR:
					itemstack = ((NonNullList<ItemStack>) armorArray.get(entity)).get(entityequipmentslot.getIndex());
					break;
				default:
					continue;
				}

				ItemStack itemstack1 = entity.getItemStackFromSlot(entityequipmentslot);

				if (!ItemStack.areItemStacksEqual(itemstack1, itemstack)) {
					if (!ItemStack.areItemStacksEqualUsingNBTShareTag(itemstack1, itemstack))
						((WorldServer) entity.world).getEntityTracker().sendToTracking(entity, new SPacketEntityEquipment(entity.getEntityId(), entityequipmentslot, itemstack1));
					MinecraftForge.EVENT_BUS.post(new LivingEquipmentChangeEvent(entity, entityequipmentslot, itemstack, itemstack1));

					if (!itemstack.isEmpty()) {
						entity.getAttributeMap().removeAttributeModifiers(itemstack.getAttributeModifiers(entityequipmentslot));
					}

					if (!itemstack1.isEmpty()) {
						entity.getAttributeMap().applyAttributeModifiers(itemstack1.getAttributeModifiers(entityequipmentslot));
					}

					switch (entityequipmentslot.getSlotType()) {
					case HAND:
						((NonNullList<ItemStack>) handInventory.get(entity)).set(entityequipmentslot.getIndex(), itemstack1.isEmpty() ? ItemStack.EMPTY : itemstack1.copy());
						break;
					case ARMOR:
						((NonNullList<ItemStack>) armorArray.get(entity)).set(entityequipmentslot.getIndex(), itemstack1.isEmpty() ? ItemStack.EMPTY : itemstack1.copy());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void attackAsPlayerWithItem(EntityPlayer player, Entity target, ItemStack stack) {
		try {
			//Get some of the original states that might get messed up
			ItemStack currentHandSlot = player.getHeldItemMainhand();
			int orig = ticksSinceLastAtt.getInt(player);

			//change some states
			player.setHeldItem(EnumHand.MAIN_HAND, stack);
			ticksSinceLastAtt.set(player, orig);
			refreshAttributes(player);
			
			System.out.println(target);
			player.attackTargetEntityWithCurrentItem(target);
			
			//changing it back
			player.setHeldItem(EnumHand.MAIN_HAND, currentHandSlot);
			refreshAttributes(player);
			
			//setting this to 0 because we did attack
			ticksSinceLastAtt.set(player, 0);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
