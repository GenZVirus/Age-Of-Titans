package com.GenZVirus.AgeOfTitans.Common.Objects.Items.Orbs;

import java.util.List;
import java.util.Random;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.PricedItem;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class OrbOfDislocation extends Item implements PricedItem {

	private int price = 0;
	
	public OrbOfDislocation(Properties properties) {
		super(properties);
		this.price = 2000;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent(""));
		tooltip.add(new StringTextComponent("\u00A75Switches the user's position with a random player on the server no matter the dimension!"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (worldIn.isRemote) { return super.onItemRightClick(worldIn, playerIn, handIn); }
		if (worldIn.getPlayers().size() == 1) {
			AgeOfTitans.LOGGER.info("One player on the server!");
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
		PlayerEntity targetPlayer = worldIn.getPlayers().get(new Random().nextInt(worldIn.getPlayers().size()));
		while (targetPlayer == playerIn) {
			targetPlayer = worldIn.getPlayers().get(new Random().nextInt(worldIn.getPlayers().size()));
		}
		PlayerEntity conjurer = playerIn;
		BlockPos targetPlayerPos = targetPlayer.getPosition();
		BlockPos conjurerPos = playerIn.getPosition();
		DimensionType targetPlayerDimension = targetPlayer.dimension;
		DimensionType conjurerDimension = conjurer.dimension;
		Commands manager = playerIn.getServer().getCommandManager();
		CommandSource source = worldIn.getServer().getCommandSource();
		if (targetPlayer.dimension == playerIn.dimension) {
			targetPlayer.setPositionAndUpdate(conjurerPos.getX(), conjurerPos.getY(), conjurerPos.getZ());
			conjurer.setPositionAndUpdate(targetPlayerPos.getX(), targetPlayerPos.getY(), targetPlayerPos.getZ());
		} else {
			manager.handleCommand(source, "/forge setdimension " + targetPlayer.getName().getFormattedText() + " " + conjurerDimension.getRegistryName().toString() + " " + conjurerPos.getX() + " " + conjurerPos.getY() + " " + conjurerPos.getZ());
			manager.handleCommand(source, "/forge setdimension " + conjurer.getName().getFormattedText() + " " + targetPlayerDimension.getRegistryName().toString() + " " + targetPlayerPos.getX() + " " + targetPlayerPos.getY() + " " + targetPlayerPos.getZ());
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public int getBurnTime(ItemStack itemStack) {
		return super.getBurnTime(itemStack);
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
