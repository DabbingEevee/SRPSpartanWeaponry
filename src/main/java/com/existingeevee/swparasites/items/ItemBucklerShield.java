package com.existingeevee.swparasites.items;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.existingeevee.swparasites.config.ParasiteSWConfig;
import com.existingeevee.swparasites.event.EvolutionHandler;
import com.existingeevee.swparasites.init.ParasiteSWProperties;
import com.oblivioussp.spartanshields.item.ItemShieldBase;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemBucklerShield extends ItemShieldBase {

	int level = 0;

	public ItemBucklerShield(String unlocName, int maxDurability, int potionLevel) {
		super(unlocName);

		this.level = potionLevel;
		this.setMaxDamage(maxDurability);

		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void attackEvent(LivingAttackEvent ev) {
		if (ev.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) ev.getEntityLiving();

			if (player.isHandActive() && !player.getActiveItemStack().isEmpty()) {
				ItemStack activeStack = player.getActiveItemStack();

				if (activeStack.getItem() == this && player.canBlockDamageSource(ev.getSource())) {
					// Yaya woo we blocked it
					player.addPotionEffect(new PotionEffect(SRPPotions.RAGE_E, 5 * 20, level));
					if (level == 0) {
						NBTTagCompound compound = activeStack.getTagCompound();
						if (compound == null) {
							compound = new NBTTagCompound();
						}
						if (compound.hasKey("srpkills")) {
							final int key = (int) (compound.getInteger("srpkills") + ev.getAmount());
							compound.setInteger("srpkills", key);
						} else {
							compound.setInteger("srpkills", (int) ev.getAmount());
						}
						activeStack.setTagCompound(compound);

					}
				}
			}
		}
	}

	@Override
	public void onItemUpdate(ToolMaterialEx material, ItemStack stack, World world, EntityLivingBase entity,
			int itemSlot, boolean isSelected) {
		if (!world.isRemote) {
			if (ParasiteSWConfig.sentientScent && level == 1 && SRPConfigSystems.useScent
					&& world.rand.nextInt(100) == 0 && entity.ticksExisted % 40 == 0) {
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(SRPPotions.PREY_E, 1200, 0, false, false));
			}
			if (entity.ticksExisted % 80 == 0) {
				int key = 0;
				final NBTTagCompound compound = stack.getTagCompound();
				if (compound != null && EvolutionHandler.getEvolved(stack.getItem()) != null) {
					if (compound.hasKey("srpkills")) {
						key = compound.getInteger("srpkills");
					}
					if (key > SRPConfig.weapon_livingSentient_HP_needed) {
						compound.setInteger("srpkills", 0);
						final ItemStack stackW = new ItemStack(EvolutionHandler.getEvolved(stack.getItem()), 1);
						if (ParasiteSWConfig.evolutionKeepNBT) {
							stackW.setTagCompound(compound.copy());
						}
						final EntityItem entityitem = new EntityItem(world, entity.posX, entity.posY, entity.posZ,
								stackW);
						if (ParasiteSWConfig.evolutionDropOnGround) {
							entityitem.setDefaultPickupDelay();
						} else {
							entityitem.setNoPickupDelay();
						}
						world.spawnEntity((Entity) entityitem);
						stack.shrink(1);
						if (SRPConfig.thunderEnable) {
							world.addWeatherEffect((Entity) new EntityLightningBolt(world, entity.posX, entity.posY,
									entity.posZ, true));
						}
					}
				}
			}
		}
	}
}
