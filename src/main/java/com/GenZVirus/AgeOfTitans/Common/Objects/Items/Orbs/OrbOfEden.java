package com.GenZVirus.AgeOfTitans.Common.Objects.Items.Orbs;

import java.util.List;
import java.util.function.Function;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Init.DimensionInit;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.PricedItem;

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
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.ITeleporter;

public class OrbOfEden extends Item implements PricedItem {

	private int price = 0;
	
	public OrbOfEden(Properties properties) {
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
		tooltip.add(new StringTextComponent("\u00A75Teleports the user between Eden and Overworld"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (worldIn.isRemote)
			return super.onItemRightClick(worldIn, playerIn, handIn);
		if (worldIn.getDimension().getType() != DimensionManager.registerOrGetDimension(AgeOfTitans.EDEN_DIMENSION_TYPE,
				DimensionInit.EDEN.get(), null, true)) {
			teleportToDimension(playerIn, DimensionManager.registerOrGetDimension(AgeOfTitans.EDEN_DIMENSION_TYPE, DimensionInit.EDEN.get(), null, true), playerIn.getPosition());
		} else {
			teleportToDimension(playerIn, DimensionType.OVERWORLD, playerIn.getPosition());
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	private void teleportToDimension(PlayerEntity player, DimensionType dimension, BlockPos pos) {
	    player.changeDimension(dimension, new ITeleporter() {
	        @Override
	        public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
	        	
	        	// true make portal, false don't make portal
	        	
	            entity = repositionEntity.apply(false);
	            
	            // Begin search for air blocks
	            
	            int i = 0;
	            while(!entity.world.getBlockState(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ())).getBlock().equals(Blocks.AIR) && !entity.world.getBlockState(new BlockPos(pos.getX(), pos.getY() + i + 1, pos.getZ())).getBlock().equals(Blocks.AIR)) {
	            	i++;
	            }
	            while(entity.world.getBlockState(new BlockPos(pos.getX(), pos.getY() + i - 1, pos.getZ())).getBlock().equals(Blocks.AIR)) {
	            	i--;
	            }
	            
	            // End search for air blocks
	            
	            entity.setPositionAndUpdate(pos.getX(), pos.getY() + i + 1, pos.getZ());
	            
	            return entity;
	        }
	    });
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
