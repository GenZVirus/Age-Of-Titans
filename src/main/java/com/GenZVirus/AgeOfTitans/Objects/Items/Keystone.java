package com.GenZVirus.AgeOfTitans.Objects.Items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class Keystone extends Item {
	private BlockPos pos = null;

	public Keystone(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent("\u00A7e" + "Right-Click" + "\u00A77" + " to teleport to the bed location, if the user is in the same dimension as the bed."));
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("The keystone will be consumed on use."));

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		return super.onItemUse(context);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (worldIn.isRemote)
			return super.onItemRightClick(worldIn, playerIn, handIn);
		pos = playerIn.getBedLocation(playerIn.dimension);
		if(pos != null) {
			playerIn.getHeldItem(handIn).shrink(1);
				playerIn.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
		}
		return ActionResult.resultConsume(playerIn.getHeldItem(handIn));
	   }
	
	@Override
	public int getBurnTime(ItemStack itemStack) {
		return super.getBurnTime(itemStack);
	}
	
}
