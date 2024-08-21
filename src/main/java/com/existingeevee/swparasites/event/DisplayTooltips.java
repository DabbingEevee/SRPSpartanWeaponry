package com.existingeevee.swparasites.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.existingeevee.swparasites.SRPSpartanWeaponry;
import com.existingeevee.swparasites.config.ParasiteSWConfig;
import com.existingeevee.swparasites.init.ParasiteSWProperties;
import com.oblivioussp.spartanshields.item.ItemShieldBase;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DisplayTooltips {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onItemTooltip(ItemTooltipEvent e) {
		try {
			ItemStack stack = e.getItemStack();

			if (!stack.getItem().getRegistryName().getNamespace().equals(SRPSpartanWeaponry.MODID))
				return;

			List<String> tooltip = e.getToolTip();

			int indexToInsert = tooltip.isEmpty() ? 0 : 1;

			String key = stack.getItem().getTranslationKey() + ".desc";

			if (ParasiteSWConfig.easterEgg) {
				if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
					key = "easter_egg.swparasites:nerdvirus.desc";
				} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
					key = "easter_egg.swparasites:sweebozo.desc";
				} else if (Keyboard.isKeyDown(Keyboard.KEY_X)) {
					key = "easter_egg.swparasites:ev.desc";
				} else if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
					key = "easter_egg.swparasites:light.desc";
				}
			}

			if (I18n.canTranslate(key)) {
				String translation = I18n.translateToLocal(key);
				if (!translation.contains("--null")) {
					List<String> toAdd = smartSplitString(translation, 100000);
					if (tooltip.size() > indexToInsert + 1 && !tooltip.get(indexToInsert + 1).isEmpty()) {
						toAdd.add("");
					}
					Collections.reverse(toAdd);
					toAdd.forEach(t -> tooltip.add(indexToInsert, t));

				}
			}
			String progress = null;
			if (stack.getItem() instanceof IWeaponPropertyContainer<?>) {
				progress = getProgress(stack);
			} else if (stack.getItem() instanceof ItemShieldBase){
				progress = getShieldProgress(stack);
			}
			if (progress != null) {
				tooltip.add(1, "");
				tooltip.add(1, progress);
			}
		} catch (Exception er) {
		}
	}

	private static List<String> smartSplitString(String toSplit, int max) {
		List<String> ret = new ArrayList<String>();

		if (toSplit.indexOf("\\n") >= 0) {
			String[] newlined = toSplit.split("\\\\n");
			for (String n : newlined) {
				ret.addAll(smartSplitString(n, max));
			}
		} else {

			String temp = "";
			for (String s : toSplit.split(" ")) {
				if (temp.replace("%s%", " ").length() + s.replace("%s%", " ").length() > max) {
					ret.add(temp.trim().replace("%s%", " "));
					temp = s + " ";
				} else {
					temp += s + " ";
				}
			}
			ret.add("" + temp.trim().replace("%s%", " "));
		}
		return ret;
	}

	public static String getProgress(final ItemStack stack) {
		if (stack.getItem() instanceof IWeaponPropertyContainer<?>) {
			IWeaponPropertyContainer<?> container = (IWeaponPropertyContainer<?>) stack.getItem();

			if (container.getAllWeaponProperties().stream()
					.anyMatch(p -> p == ParasiteSWProperties.HEAVY_1 || p == ParasiteSWProperties.HEAVY_2)) {
				final NBTTagCompound compound = stack.getTagCompound();
				if (compound != null) {
					return (TextFormatting.BLUE + "---> " + compound.getInteger("srpkills"));
				}
			}
		}
		return null;
	}
	
	public static String getShieldProgress(final ItemStack stack) {
		if (stack.getItem() instanceof ItemShieldBase) {
			if (stack.getItem().getRegistryName().getNamespace().equals(SRPSpartanWeaponry.MODID)) {
				final NBTTagCompound compound = stack.getTagCompound();
				if (compound != null) {
					return (TextFormatting.BLUE + "---> " + compound.getInteger("srpkills"));
				}
			}
		}
		return null;
	}
}
