package com.GenZVirus.AgeOfTitans.Objects.Items;

import java.util.List;

import com.GenZVirus.AgeOfTitans.Util.Helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
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

public class Keystone extends Item {
	private String Location = null, Dimension = null;
	private BlockPos pos = null;
	private DimensionType dimType = null;

	public Keystone(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if(Location != null || Dimension != null) {
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("Dimension: " + Dimension));
			tooltip.add(new StringTextComponent("Location: " + Location));
			tooltip.add(new StringTextComponent(""));
		}
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Hold" + "\u00A7e" + "Shift" + "\u00A77" + "to set the location you want to teleport back to"));
		} else {
			tooltip.add(new StringTextComponent("Used to teleport player back to the set location"));
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("Hold" + "\u00A7e" + "Shift" + "\u00A77" + "for more information!"));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (worldIn.isRemote)
			return super.onItemRightClick(worldIn, playerIn, handIn);
		
		if (KeyboardHelper.isHoldingShift()) {
				pos = playerIn.getPosition();
				dimType = playerIn.dimension;
				Location = "X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ(); 
				Dimension = dimType.getRegistryName().toString();
				removeText();
		}
		else if(pos != null && dimType != null) {
			
		if (playerIn.dimension != dimType) {
			playerIn.getServer().getCommandManager().handleCommand(worldIn.getServer().getCommandSource(),
					"/forge setdimension" 
							+ " " + playerIn.getName().getFormattedText()
							+ " " + dimType.getRegistryName().toString()
							+ " " + pos.getX()
							+ " " + pos.getY()
							+ " " + pos.getZ());
		}else {
			playerIn.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
		}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	private void removeText() {
		
		String copy = Dimension;
		copy = copy.substring(copy.indexOf(":") + 1);
		copy = copy.replaceAll("_", " ");
		String[] split = copy.split(" ");
		copy = "";
		for(String part : split) {
			copy = copy + part.substring(0, 1).toUpperCase() + part.substring(1) + " ";
		}
		System.out.println(copy);
		Dimension = copy;
		
	}

	@Override
	public int getBurnTime(ItemStack itemStack) {
		return super.getBurnTime(itemStack);
	}
}
