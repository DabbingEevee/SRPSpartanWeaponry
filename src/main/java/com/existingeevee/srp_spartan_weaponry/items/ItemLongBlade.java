package com.existingeevee.srp_spartan_weaponry.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLongBlade extends ItemSword {

	public ItemLongBlade(ToolMaterial material) {
		super(material);
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

	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
	}
}
