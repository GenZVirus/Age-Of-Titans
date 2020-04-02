package com.GenZVirus.AgeOfTitans.Objects.Items;

import java.util.List;
import java.util.Random;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Init.DimensionInit;
import com.GenZVirus.AgeOfTitans.Util.Helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class OrbOfSummoning extends Item {

	public OrbOfSummoning(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Teleports player between Eden and Overworld"));
		} else {
			tooltip.add(new StringTextComponent("Hold" + "\u00A7e" + " Shift " + "\u00A77" + "for more information!"));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (worldIn.isRemote) {
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
		PlayerEntity targetPlayer = worldIn.getPlayers().get(new Random().nextInt(worldIn.getPlayers().size()));
		if(targetPlayer.dimension == playerIn.dimension) {
			playerIn.getServer().getCommandManager()
			.handleCommand(worldIn.getServer().getCommandSource(),
					"/teleport " 
							+ targetPlayer.getName().getFormattedText()
							+ " " + playerIn.getPosition().getX()
							+ " " + playerIn.getPosition().getY()
							+ " " + playerIn.getPosition().getZ());
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
