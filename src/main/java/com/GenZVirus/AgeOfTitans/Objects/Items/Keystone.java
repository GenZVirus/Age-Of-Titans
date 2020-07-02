package com.GenZVirus.AgeOfTitans.Objects.Items;

import java.util.List;
import java.util.function.Function;

import com.GenZVirus.AgeOfTitans.Init.ItemInit;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
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
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

public class Keystone extends Item {
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
			tooltip.add(new StringTextComponent("\u00A7e" + "Right-Click" + "\u00A77" + " to teleport to the bed location, if the user is in the same dimension as the bed."));
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("The keystone will be consumed on use."));

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (worldIn.isRemote)
			return super.onItemRightClick(worldIn, playerIn, handIn);
		pos = playerIn.getBedLocation(playerIn.dimension);
		dimType = playerIn.dimension;
		
		if(pos != null && dimType != null) {
			if(playerIn.getActiveItemStack().equals(ItemInit.KEYSTONE.get().getDefaultInstance())) {
				playerIn.getActiveItemStack().shrink(1);
			}
		if (playerIn.dimension != dimType) {
			teleportToDimension(playerIn, dimType, pos);
		}else {
			playerIn.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
		}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack) {
		return super.getBurnTime(itemStack);
	}
	
	private void teleportToDimension(PlayerEntity player, DimensionType dimension, BlockPos pos) {
	    player.changeDimension(dimension, new ITeleporter() {
	        @Override
	        public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
	            entity = repositionEntity.apply(false);
	            int i = 0;
	            while(!entity.world.getBlockState(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ())).getBlock().equals(Blocks.AIR) && !entity.world.getBlockState(new BlockPos(pos.getX(), pos.getY() + i + 1, pos.getZ())).getBlock().equals(Blocks.AIR)) {
	            	i++;
	            }
	            while(entity.world.getBlockState(new BlockPos(pos.getX(), pos.getY() + i - 1, pos.getZ())).getBlock().equals(Blocks.AIR)) {
	            	i--;
	            }
	            entity.setPositionAndUpdate(pos.getX(), pos.getY() + i + 1, pos.getZ());
	            
	            return entity;
	        }
	    });
	}
}
