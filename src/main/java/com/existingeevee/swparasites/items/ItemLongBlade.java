package com.existingeevee.swparasites.items;

import com.existingeevee.swparasites.config.ParasiteSWConfig;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ItemLongBlade extends ItemSword {

	public static ToolMaterial material;
	
	public ItemLongBlade() {
		super(getMaterial());
	}

	private static final ThreadLocal<Boolean> shouldProc = ThreadLocal.withInitial(() -> true);

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!attacker.world.isRemote && attacker.getRNG().nextInt(2) < 1 && shouldProc.get() && attacker instanceof EntityPlayer) {
			shouldProc.set(false);
			ObfuscationReflectionHelper.setPrivateValue(EntityLivingBase.class, attacker, 1000, "field_184617_aD"); //Reset the attack cooldown
			((EntityPlayer) attacker).attackTargetEntityWithCurrentItem(attacker);
			shouldProc.set(true);
		}
		return true;
	}
	
	private static ToolMaterial getMaterial() {
		if (material == null) {
			material = EnumHelper.addToolMaterial("SRPSW$LONG_BLADE", 0, 0, 0, ParasiteSWConfig.longBladeDmg, 0);
		}
		return material;
	}
}
