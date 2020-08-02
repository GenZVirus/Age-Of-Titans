package com.GenZVirus.AgeOfTitans.Common.Objects.Blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.GenZVirus.AgeOfTitans.Common.TileEntity.TileEntityInventoryBasic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

/**
 * BlockInventoryBasic is a simple inventory capable of storing 9 item stacks.
 * The block itself doesn't do much more then any regular block except create a
 * tile entity when placed, open a gui when right clicked and drop tne
 * inventory's contents when harvested. The actual storage is handled by the
 * tile entity.
 */

public class TitanLocker extends ContainerBlock {
	
	public static final DirectionProperty FACING = DirectionalBlock.FACING;
	
	public TitanLocker(Properties properties) {
		super(properties);
	}
	
	@Override
		public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
			return false;
		}
	
	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("\u00A75Without it's key, the Orb of Storage, the contents of this container cannot be seen."));

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	/**
	 * Create the Tile Entity for this block. Forge has a default but I've included
	 * it anyway for clarity
	 * 
	 * @return
	 */
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return createNewTileEntity(world);
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.FACING);
	}
	
	@Nullable
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new TileEntityInventoryBasic();
	}

	// not needed if your block implements ITileEntityProvider (in this case
	// implemented by BlockContainer), but it
	// doesn't hurt to include it anyway...
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	public BlockState getStateForPlacement(BlockItemUseContext context) {
	      return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	 @Override
	    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
	        return state.with(FACING, direction.rotate(state.get(FACING)));
	    }

	    @Override
	    public BlockState mirror(BlockState state, Mirror mirrorIn) {
	        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	    }
	
	// Called when the block is right clicked
	// We use it to open the block gui when right clicked by a player
	// Copied from ChestBlock
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand hand, BlockRayTraceResult rayTraceResult) {
		return ActionResultType.FAIL;
	}

	  // This is where you can do something when the block is broken. In this case drop the inventory's contents
	  // Code is copied directly from vanilla eg ChestBlock, CampfireBlock
	  @SuppressWarnings("deprecation")
	public void onReplaced(BlockState state, World world, BlockPos blockPos, BlockState newState, boolean isMoving) {
	    if (state.getBlock() != newState.getBlock()) {
	      TileEntity tileentity = world.getTileEntity(blockPos);
	      if (tileentity instanceof TileEntityInventoryBasic) {
	        TileEntityInventoryBasic tileEntityInventoryBasic = (TileEntityInventoryBasic)tileentity;
	        tileEntityInventoryBasic.dropAllContents(world, blockPos);
	      }
//	      worldIn.updateComparatorOutputLevel(pos, this);  if the inventory is used to set redstone power for comparators
	      super.onReplaced(state, world, blockPos, newState, isMoving);  // call it last, because it removes the TileEntity
	    }
		}

	// ---------------------------------------------------------
	
	// required because the default (super method) is INVISIBLE for BlockContainers.
	@Override
	public BlockRenderType getRenderType(BlockState iBlockState) {
		return BlockRenderType.MODEL;
	}

	// returns the shape of the block:
	// The image that you see on the screen (when a block is rendered) is determined
	// by the block model (i.e. the model json file).
	// But Minecraft also uses a number of other "shapes" to control the interaction
	// of the block with its environment and with the player.
	// See
	// https://greyminecraftcoder.blogspot.com/2020/02/block-shapes-voxelshapes-1144.html
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
//		return CHEST_SHAPE;
		return VoxelShapes.fullCube();
	}
}
