package com.GenZVirus.AgeOfTitans.Common.Objects.Items;

import java.util.List;

import com.GenZVirus.AgeOfTitans.Util.Helpers.KeyboardHelper;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class SpecialItem extends Item {

	public SpecialItem(Properties properties) {
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
		if(worldIn.isRemote) return super.onItemRightClick(worldIn, playerIn, handIn);
		Vec3d vec = playerIn.getLookVec();
		Vec3d pos = playerIn.getPositionVec();
		pos = new Vec3d(pos.x + 0, pos.y + 1.7D, pos.z + 0);
		int nr = 0;
		while((worldIn.getBlockState(new BlockPos(pos)).getBlock().equals(Blocks.AIR) || worldIn.getBlockState(new BlockPos(pos)).getBlock().equals(Blocks.CAVE_AIR))&& nr < 40) {
			pos = new Vec3d(pos.x + vec.x / 2, pos.y + vec.y / 2, pos.z + vec.z / 2);
			nr++;
		}
		worldIn.destroyBlock(new BlockPos(pos), false);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public int getBurnTime(ItemStack itemStack) {
		return super.getBurnTime(itemStack);
	}
}
