package com.existingeevee.swparasites.items;

import com.oblivioussp.spartanshields.item.ItemShieldBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemImpalerShield extends ItemShieldBase {

	int level = 0;

	public ItemImpalerShield(String unlocName, int maxDurability, int damageLevel) {
		super(unlocName);

		this.level = damageLevel;
		this.setMaxDamage(maxDurability);

		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		if (!playerIn.isSneaking() || playerIn.getCooldownTracker().getCooldown(item.getItem(), 0) > 0) { 
			return null;
		}
		
		Vec3d vec = playerIn.getLookVec();
		
        playerIn.motionX += vec.x * 1.5;
        playerIn.motionY += 1;
        playerIn.motionZ += vec.z * 1.5;
        playerIn.velocityChanged = true;
        
		AxisAlignedBB box = new AxisAlignedBB(playerIn.posX, playerIn.posY, playerIn.posZ, playerIn.posX, playerIn.posY, playerIn.posZ).grow(1);
        
		for (Entity entity : worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, box))
		{
			Vec3d motionVector = new Vec3d(entity.posX - playerIn.posX, 0.6D, entity.posZ - playerIn.posZ).normalize();
			entity.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 15 * level);
			if (!worldIn.isRemote)
			{
				entity.motionX += motionVector.x * 1.5D;
				entity.motionY += motionVector.y;
				entity.motionZ += motionVector.z * 1.5D;
				entity.velocityChanged = true;
			}
		}
        playerIn.getCooldownTracker().setCooldown(item.getItem(), 50);
	    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
	}
}