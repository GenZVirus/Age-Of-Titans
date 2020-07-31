package com.GenZVirus.AgeOfTitans.Common.Objects.Items;

import java.util.List;
import java.util.Random;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class OrbOfSummoning extends Item {

	public OrbOfSummoning(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent(""));
		tooltip.add(new StringTextComponent("\n\u00A75Summons a random player to the user's location"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (worldIn.isRemote) {
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
		if(worldIn.getPlayers().size() == 1) {
			System.out.println("One player on the server");
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
}
