package com.GenZVirus.AgeOfTitans.Objects.Items;

import java.util.List;

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

public class OrbOfEden extends Item {

	public OrbOfEden(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Test Information"));
		} else {
			tooltip.add(new StringTextComponent("Hold" + "\u00A7e" + "Shift" + "\u00A77" + "for more information!"));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (worldIn.isRemote)
			return super.onItemRightClick(worldIn, playerIn, handIn);
		if (worldIn.getDimension().getType() != DimensionManager.registerOrGetDimension(AgeOfTitans.EDEN_DIMENSION_TYPE,
				DimensionInit.EDEN.get(), null, true)) {
			playerIn.getServer().getCommandManager().handleCommand(playerIn.getCommandSource(),
					"/forge setdimension Dev ageoftitans:eden");
		} else {
			playerIn.getServer().getCommandManager().handleCommand(playerIn.getCommandSource(),
					"/forge setdimension Dev minecraft:overworld");

		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public int getBurnTime(ItemStack itemStack) {
		return super.getBurnTime(itemStack);
	}
}
