package com.GenZVirus.AgeOfTitans.Common.Objects.Items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class OrbOfEnd extends Item implements PricedItem {

	private int price = 0;
	
	public OrbOfEnd(Properties properties) {
		super(properties);
		this.price = 1000;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent(""));
		tooltip.add(new StringTextComponent("\u00A75Teleports the user between End and Overworld"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (worldIn.isRemote)
			return super.onItemRightClick(worldIn, playerIn, handIn);
		if (worldIn.getDimension().getType() != DimensionType.THE_END) {
			teleportToDimension(playerIn, DimensionType.THE_END);
		} else {
			teleportToDimension(playerIn, DimensionType.OVERWORLD);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public int getBurnTime(ItemStack itemStack) {
		return super.getBurnTime(itemStack);
	}
	private void teleportToDimension(PlayerEntity player, DimensionType dimension) {
	    player.changeDimension(dimension);
	}

	@Override
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int getPrice() {
		return this.price;
	}
}
