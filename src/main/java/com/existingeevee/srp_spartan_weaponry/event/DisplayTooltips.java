package com.existingeevee.srp_spartan_weaponry.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.existingeevee.srp_spartan_weaponry.SRPSpartanWeaponry;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class DisplayTooltips {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onItemTooltip(ItemTooltipEvent e) {
		try {
			ItemStack stack = e.getItemStack();

			if (!stack.getItem().getRegistryName().getNamespace().equals(SRPSpartanWeaponry.MODID))
				return;

			List<String> tooltip = e.getToolTip();
			
			//System.out.println(tooltip);
			
			int indexToInsert = tooltip.isEmpty() ? 0 : 1;

			String key = stack.getItem().getTranslationKey() + ".desc";

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
		} catch (Exception er) {
		}
	}

	private static List<String> smartSplitString(String toSplit, int max) {
		List<String> ret = new ArrayList<String>();
		String temp = "";
		for (String s : toSplit.split(" ")) {
			if (temp.replace("%s%", " ").length() + s.replace("%s%", " ").length() > max) {
				ret.add("" + temp.trim().replace("%s%", " "));
				temp = s + " ";
			} else {
				temp += s + " ";
			}
		}
		ret.add("" + temp.trim().replace("%s%", " "));
		return ret;
	}

}
