package com.existingeevee.swparasites.items;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.existingeevee.swparasites.config.ParasiteSWConfig;
import com.existingeevee.swparasites.handlers.EvolutionHandler;
import com.existingeevee.swparasites.init.weapons.ParasiteSWTwisted;
import com.existingeevee.swparasites.items.IHasSRPEvolutionProgress;
import com.oblivioussp.spartanweaponry.api.IWeaponCallback;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBolt;
import com.oblivioussp.spartanweaponry.init.EnchantmentRegistrySW;
import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.init.SoundRegistry;
import com.oblivioussp.spartanweaponry.item.ItemBolt;
import com.oblivioussp.spartanweaponry.item.ItemCrossbow;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.Defaults;
import com.oblivioussp.spartanweaponry.util.NBTHelper;
import com.oblivioussp.spartanweaponry.util.Quaternion;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemParasiteCrossbowNocube extends ItemCrossbow {

	public ItemParasiteCrossbowNocube(String unlocName, String externalModId, ToolMaterialEx material, IWeaponCallback weaponCallback) {
		super(unlocName, externalModId, material, weaponCallback);
	}

	public ItemParasiteCrossbowNocube(String unlocName, String externalModId, ToolMaterialEx material) {
		super(unlocName, externalModId, material);
	}

	public ItemParasiteCrossbowNocube(String unlocName, ToolMaterialEx toolMaterial) {
		super(unlocName, toolMaterial);
	}

	@Override
	protected void spawnProjectile(ItemStack crossbow, ItemBolt boltItem, ItemStack boltStack, World world, EntityPlayer player, boolean noPickup, float inaccuracyModifier, float projectileAngle) {
		EntityBolt bolt = boltItem.createBolt(world, boltStack, player);
		bolt.setIsCritical(true);

		Vec3d lookVec = player.getLook(1.0f);
		Vec3d vector = new Vec3d(lookVec.x, lookVec.y, lookVec.z);

		if (projectileAngle != 0.0f) {
			Vec3d playerUpVec = calculateEntityViewVector(player.rotationPitch - 90.0f, player.rotationYaw);
			Quaternion quat = new Quaternion(playerUpVec, projectileAngle, true);
			vector = quat.transformVector(lookVec);
		}

		bolt.shoot(vector.x, vector.y, vector.z, getBoltSpeed() * 3.0f, inaccuracyModifier);
		
		boolean twisted = crossbow.getTranslationKey().equals("item.swparasites:spartanweaponry:crossbow_twisted");
		boolean pestilent = crossbow.getTranslationKey().equals("item.swparasites:spartanweaponry:crossbow_pestilent");
		boolean gore = crossbow.getTranslationKey().equals("item.swparasites:spartanweaponry:crossbow_gore");
		boolean evolution = crossbow.getTranslationKey().equals("item.swparasites:spartanweaponry:crossbow_evolution");

		int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, crossbow);

		if (j > 0) {
			bolt.setDamage(bolt.getDamage() + j * 0.5D + 0.5D);
		}

		int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, crossbow);

		if (k > 0) {
			k += gore ? 1 : 0;
			k += evolution ? 2 : 0;
			bolt.setKnockbackStrength(k);
		}

		if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, crossbow) > 0) {
			bolt.setFire(evolution ? 200 : 100);
		}

		if (noPickup || projectileAngle != 0.0f) {
			bolt.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
		}

		System.out.println(crossbow.getTranslationKey());
		if (twisted) {
			bolt.getTags().add("ParasiteCrossbowTwisted");
		}
		
		if (pestilent) {
			bolt.getTags().add("ParasiteCrossbowPestilent");
		}
		
		if (gore) {
			bolt.getTags().add("ParasiteCrossbowGore");
		}
		
		if (evolution) {
			bolt.getTags().add("ParasiteCrossbowEvolution");
		}
		
		world.spawnEntity(bolt);
	}

	private Vec3d calculateEntityViewVector(float pitch, float yaw) {
		float degToRad = (2.0f * (float) Math.PI) / 360.0f;
		float yawCos = MathHelper.cos(-yaw * degToRad - (float) Math.PI);
		float yawSin = MathHelper.sin(-yaw * degToRad - (float) Math.PI);
		float pitchCos = -MathHelper.cos(-pitch * degToRad - (float) Math.PI);
		float pitchSin = MathHelper.sin(-pitch * degToRad - (float) Math.PI);

		return new Vec3d((double) (yawSin * pitchCos), (double) pitchSin, (double) (yawCos * pitchCos));
	}
}
