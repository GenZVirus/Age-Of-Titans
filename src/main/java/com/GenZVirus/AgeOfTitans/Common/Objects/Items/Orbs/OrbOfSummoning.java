package com.GenZVirus.AgeOfTitans.Common.Objects.Items.Orbs;

import java.util.List;
import java.util.Random;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.PricedItem;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class OrbOfSummoning extends Item implements PricedItem {
		
	private int price = 0;
		
	public OrbOfSummoning(Properties properties) {
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
		tooltip.add(new StringTextComponent("\u00A75Summons a random player to the user's location"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (worldIn.isRemote) {
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
		if(worldIn.getPlayers().size() == 1) {
			AgeOfTitans.LOGGER.info("One player on the server!");
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
		PlayerEntity targetPlayer = worldIn.getPlayers().get(new Random().nextInt(worldIn.getPlayers().size()));
		while(targetPlayer == playerIn) {
			targetPlayer = worldIn.getPlayers().get(new Random().nextInt(worldIn.getPlayers().size()));
		}
		if(targetPlayer.dimension == playerIn.dimension) {
			targetPlayer.setPositionAndUpdate(playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ());
		} else {
		playerIn.getServer().getCommandManager()
				.handleCommand(worldIn.getServer().getCommandSource(),
						"/forge setdimension " 
								+ targetPlayer.getName().getFormattedText()
								+ " " + playerIn.dimension.getRegistryName().toString()
								+ " " + playerIn.getPosition().getX()
								+ " " + playerIn.getPosition().getY()
								+ " " + playerIn.getPosition().getZ());
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
