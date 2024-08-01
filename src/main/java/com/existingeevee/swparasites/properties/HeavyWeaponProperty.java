package com.existingeevee.swparasites.properties;

import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.config.ParasiteSWConfig;
import com.existingeevee.swparasites.event.EvolutionHandler;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;
import com.oblivioussp.spartanweaponry.item.ItemThrowingWeapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HeavyWeaponProperty extends WeaponPropertyWithCallback { // https://wiki.teamfortress.com/wiki/Heavy :3

	final boolean lvl2;

	public HeavyWeaponProperty(boolean lvl2) {
		super("heavy", SRPSpartanWeaponry.MODID, lvl2 ? 2 : 1, 0);
		MinecraftForge.EVENT_BUS.register(this);

		this.lvl2 = lvl2;
	}

	private static AttributeModifier modifier;
	private static AttributeModifier modifierII;

	@Override
	public void onItemUpdate(ToolMaterialEx material, ItemStack stack, World world, EntityLivingBase entity, int itemSlot, boolean isSelected) {
		// We didn't want to make another trait for living weapon evolution and sentient weapons giving Prey, so we hid it in Heavy :3
        if (!world.isRemote) {
            if (ParasiteSWConfig.sentientScent && this.lvl2 && SRPConfigSystems.useScent && world.rand.nextInt(100) == 0 && entity.ticksExisted % 40 == 0) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(SRPPotions.PREY_E, 1200, 0, false, false));
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
                        final EntityItem entityitem = new EntityItem(world, entity.posX, entity.posY, entity.posZ, stackW);
                        if (ParasiteSWConfig.evolutionDropOnGround) {
                        	entityitem.setDefaultPickupDelay();
                        }
                        else {
                        	entityitem.setNoPickupDelay();
                        }
                        world.spawnEntity((Entity)entityitem);
                        stack.shrink(1);
                        if (SRPConfig.thunderEnable) {
                            world.addWeatherEffect((Entity)new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ, true));
                        }
                    }
                }
            }
        }
	}

	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
		if (target.getHealth() <= 0.0f) {
			if (projectile instanceof EntityThrownWeapon) {
				if (!(attacker instanceof EntityPlayer))
					return;

				EntityThrownWeapon projThrown = (EntityThrownWeapon) projectile;
				EntityPlayer player = (EntityPlayer) attacker;

				ItemStack weapon = projThrown.getWeaponStack();

				// Find any stack that might fit this item.
				for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
					ItemStack slotStack = player.inventory.getStackInSlot(i);
					if (ItemStack.areItemsEqualIgnoreDurability(slotStack, weapon) && weapon.hasTagCompound() && slotStack.hasTagCompound() &&
							weapon.getTagCompound().getUniqueId(ItemThrowingWeapon.NBT_UUID).equals(slotStack.getTagCompound().getUniqueId(ItemThrowingWeapon.NBT_UUID)) &&
							weapon.getItem() instanceof ItemThrowingWeapon) {
						
						add(slotStack, (int) target.getMaxHealth());
					}
				}
			} else {
				add(stack, (int) target.getMaxHealth());
			}
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
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		Item item = event.getEntityLiving().getHeldItemMainhand().getItem();

		boolean shouldHaveSlowing = false;

		if (item instanceof IWeaponPropertyContainer<?>) {
			IWeaponPropertyContainer<?> container = (IWeaponPropertyContainer<?>) item;

			if (container.getAllWeaponProperties().stream().anyMatch(p -> p == this)) {
				shouldHaveSlowing = true;
			}
		}

		IAttributeInstance attr = event.getEntityLiving().getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_SPEED);

		if (attr != null) {
			AttributeModifier modifier = lvl2 ? getModifierII() : getModifier();

			if (shouldHaveSlowing && !attr.hasModifier(modifier)) {
				attr.applyModifier(modifier);
			}
			if (!shouldHaveSlowing && attr.hasModifier(modifier)) {
				attr.removeModifier(modifier);
			}
		}
	}

	@Override
	public PropertyQuality getQuality() {
		return PropertyQuality.NEGATIVE;
	}

	private static final DecimalFormat FORMATTER = new DecimalFormat("0.##");

	@Override
	@SideOnly(Side.CLIENT)
	protected void addTooltipDescription(ItemStack stack, List<String> tooltip) {
		String percent = FORMATTER.format((lvl2 ? ParasiteSWConfig.weaponIISlowness : ParasiteSWConfig.weaponSlowness) * 100);

		tooltip.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "  " + SpartanWeaponryAPI.internalHandler.translateString(type + ".desc", "tooltip", modId).replace("$s", percent + "%"));
	}

	private static AttributeModifier getModifier() {
		if (modifier == null) {
			modifier = new AttributeModifier(UUID.fromString("aeee73df-79af-4de9-eeee-44b5eee4df1d"), "heavy_weapon_property", -ParasiteSWConfig.weaponSlowness, 2);
		}
		return modifier;
	}

	private static AttributeModifier getModifierII() {
		if (modifierII == null) {
			modifierII = new AttributeModifier(UUID.fromString("aeeee3df-79af-4de9-eeee-44b5eee4df1d"), "heavy_weapon_property", -ParasiteSWConfig.weaponIISlowness, 2);
		}
		return modifierII;
	}
}
