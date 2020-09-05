package com.GenZVirus.AgeOfTitans.Common.Objects.Blocks;

import java.util.Random;
import java.util.stream.Stream;

import com.GenZVirus.AgeOfTitans.Common.Particles.SoulParticle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class SoulStone extends Block{
	
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	private static final VoxelShape SHAPE_N = Stream.of(
			Block.makeCuboidShape(7, 1, 7, 9, 2, 9),
			Block.makeCuboidShape(7, 1, 8, 9, 2, 12),
			Block.makeCuboidShape(7.5, 0.75, 8, 8.5, 2.25, 12),
			Block.makeCuboidShape(7.5, 1, 12, 8.5, 2, 13),
			Block.makeCuboidShape(7, 1, 4, 9, 2, 8),
			Block.makeCuboidShape(7.5, 1, 3, 8.5, 2, 4),
			Block.makeCuboidShape(7.5, 0.75, 4, 8.5, 2.25, 8),
			Block.makeCuboidShape(8, 1, 7, 12, 2, 9),
			Block.makeCuboidShape(12, 1, 7.5, 13, 2, 8.5),
			Block.makeCuboidShape(8, 0.75, 7.5, 12, 2.25, 8.5),
			Block.makeCuboidShape(4, 1, 7, 8, 2, 9),
			Block.makeCuboidShape(3, 1, 7.5, 4, 2, 8.5),
			Block.makeCuboidShape(4, 0.75, 7.5, 8, 2.25, 8.5),
			Block.makeCuboidShape(7.5, -0.25, 8, 8.5, 1.25, 12),
			Block.makeCuboidShape(7.5, 3, 7, 8.5, 7, 9),
			Block.makeCuboidShape(7.5, 7, 7.5, 8.5, 8, 8.5),
			Block.makeCuboidShape(7.25, 3, 7.5, 8.75, 7, 8.5),
			Block.makeCuboidShape(7, 0, 8, 9, 1, 12),
			Block.makeCuboidShape(7.5, 0, 12, 8.5, 1, 13),
			Block.makeCuboidShape(7, 0, 7, 9, 1, 9),
			Block.makeCuboidShape(7, 0, 4, 9, 1, 8),
			Block.makeCuboidShape(7.5, 0, 3, 8.5, 1, 4),
			Block.makeCuboidShape(7.5, -0.25, 4, 8.5, 1.25, 8),
			Block.makeCuboidShape(8, 0, 7, 12, 1, 9),
			Block.makeCuboidShape(12, 0, 7.5, 13, 1, 8.5),
			Block.makeCuboidShape(8, -0.25, 7.5, 12, 1.25, 8.5),
			Block.makeCuboidShape(4, 0, 7, 8, 1, 9),
			Block.makeCuboidShape(3, 0, 7.5, 4, 1, 8.5),
			Block.makeCuboidShape(4, -0.25, 7.5, 8, 1.25, 8.5)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	private static final VoxelShape SHAPE_W = Stream.of(
			Block.makeCuboidShape(7, 1, 7, 9, 2, 9),
			Block.makeCuboidShape(7, 1, 8, 9, 2, 12),
			Block.makeCuboidShape(7.5, 0.75, 8, 8.5, 2.25, 12),
			Block.makeCuboidShape(7.5, 1, 12, 8.5, 2, 13),
			Block.makeCuboidShape(7, 1, 4, 9, 2, 8),
			Block.makeCuboidShape(7.5, 1, 3, 8.5, 2, 4),
			Block.makeCuboidShape(7.5, 0.75, 4, 8.5, 2.25, 8),
			Block.makeCuboidShape(8, 1, 7, 12, 2, 9),
			Block.makeCuboidShape(12, 1, 7.5, 13, 2, 8.5),
			Block.makeCuboidShape(8, 0.75, 7.5, 12, 2.25, 8.5),
			Block.makeCuboidShape(4, 1, 7, 8, 2, 9),
			Block.makeCuboidShape(3, 1, 7.5, 4, 2, 8.5),
			Block.makeCuboidShape(4, 0.75, 7.5, 8, 2.25, 8.5),
			Block.makeCuboidShape(7.5, -0.25, 8, 8.5, 1.25, 12),
			Block.makeCuboidShape(7.5, 3, 7, 8.5, 7, 9),
			Block.makeCuboidShape(7.5, 7, 7.5, 8.5, 8, 8.5),
			Block.makeCuboidShape(7.25, 3, 7.5, 8.75, 7, 8.5),
			Block.makeCuboidShape(7, 0, 8, 9, 1, 12),
			Block.makeCuboidShape(7.5, 0, 12, 8.5, 1, 13),
			Block.makeCuboidShape(7, 0, 7, 9, 1, 9),
			Block.makeCuboidShape(7, 0, 4, 9, 1, 8),
			Block.makeCuboidShape(7.5, 0, 3, 8.5, 1, 4),
			Block.makeCuboidShape(7.5, -0.25, 4, 8.5, 1.25, 8),
			Block.makeCuboidShape(8, 0, 7, 12, 1, 9),
			Block.makeCuboidShape(12, 0, 7.5, 13, 1, 8.5),
			Block.makeCuboidShape(8, -0.25, 7.5, 12, 1.25, 8.5),
			Block.makeCuboidShape(4, 0, 7, 8, 1, 9),
			Block.makeCuboidShape(3, 0, 7.5, 4, 1, 8.5),
			Block.makeCuboidShape(4, -0.25, 7.5, 8, 1.25, 8.5)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	private static final VoxelShape SHAPE_S = Stream.of(
			Block.makeCuboidShape(7, 1, 7, 9, 2, 9),
			Block.makeCuboidShape(7, 1, 8, 9, 2, 12),
			Block.makeCuboidShape(7.5, 0.75, 8, 8.5, 2.25, 12),
			Block.makeCuboidShape(7.5, 1, 12, 8.5, 2, 13),
			Block.makeCuboidShape(7, 1, 4, 9, 2, 8),
			Block.makeCuboidShape(7.5, 1, 3, 8.5, 2, 4),
			Block.makeCuboidShape(7.5, 0.75, 4, 8.5, 2.25, 8),
			Block.makeCuboidShape(8, 1, 7, 12, 2, 9),
			Block.makeCuboidShape(12, 1, 7.5, 13, 2, 8.5),
			Block.makeCuboidShape(8, 0.75, 7.5, 12, 2.25, 8.5),
			Block.makeCuboidShape(4, 1, 7, 8, 2, 9),
			Block.makeCuboidShape(3, 1, 7.5, 4, 2, 8.5),
			Block.makeCuboidShape(4, 0.75, 7.5, 8, 2.25, 8.5),
			Block.makeCuboidShape(7.5, -0.25, 8, 8.5, 1.25, 12),
			Block.makeCuboidShape(7.5, 3, 7, 8.5, 7, 9),
			Block.makeCuboidShape(7.5, 7, 7.5, 8.5, 8, 8.5),
			Block.makeCuboidShape(7.25, 3, 7.5, 8.75, 7, 8.5),
			Block.makeCuboidShape(7, 0, 8, 9, 1, 12),
			Block.makeCuboidShape(7.5, 0, 12, 8.5, 1, 13),
			Block.makeCuboidShape(7, 0, 7, 9, 1, 9),
			Block.makeCuboidShape(7, 0, 4, 9, 1, 8),
			Block.makeCuboidShape(7.5, 0, 3, 8.5, 1, 4),
			Block.makeCuboidShape(7.5, -0.25, 4, 8.5, 1.25, 8),
			Block.makeCuboidShape(8, 0, 7, 12, 1, 9),
			Block.makeCuboidShape(12, 0, 7.5, 13, 1, 8.5),
			Block.makeCuboidShape(8, -0.25, 7.5, 12, 1.25, 8.5),
			Block.makeCuboidShape(4, 0, 7, 8, 1, 9),
			Block.makeCuboidShape(3, 0, 7.5, 4, 1, 8.5),
			Block.makeCuboidShape(4, -0.25, 7.5, 8, 1.25, 8.5)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	private static final VoxelShape SHAPE_E = Stream.of(
			Block.makeCuboidShape(7, 1, 7, 9, 2, 9),
			Block.makeCuboidShape(7, 1, 8, 9, 2, 12),
			Block.makeCuboidShape(7.5, 0.75, 8, 8.5, 2.25, 12),
			Block.makeCuboidShape(7.5, 1, 12, 8.5, 2, 13),
			Block.makeCuboidShape(7, 1, 4, 9, 2, 8),
			Block.makeCuboidShape(7.5, 1, 3, 8.5, 2, 4),
			Block.makeCuboidShape(7.5, 0.75, 4, 8.5, 2.25, 8),
			Block.makeCuboidShape(8, 1, 7, 12, 2, 9),
			Block.makeCuboidShape(12, 1, 7.5, 13, 2, 8.5),
			Block.makeCuboidShape(8, 0.75, 7.5, 12, 2.25, 8.5),
			Block.makeCuboidShape(4, 1, 7, 8, 2, 9),
			Block.makeCuboidShape(3, 1, 7.5, 4, 2, 8.5),
			Block.makeCuboidShape(4, 0.75, 7.5, 8, 2.25, 8.5),
			Block.makeCuboidShape(7.5, -0.25, 8, 8.5, 1.25, 12),
			Block.makeCuboidShape(7.5, 3, 7, 8.5, 7, 9),
			Block.makeCuboidShape(7.5, 7, 7.5, 8.5, 8, 8.5),
			Block.makeCuboidShape(7.25, 3, 7.5, 8.75, 7, 8.5),
			Block.makeCuboidShape(7, 0, 8, 9, 1, 12),
			Block.makeCuboidShape(7.5, 0, 12, 8.5, 1, 13),
			Block.makeCuboidShape(7, 0, 7, 9, 1, 9),
			Block.makeCuboidShape(7, 0, 4, 9, 1, 8),
			Block.makeCuboidShape(7.5, 0, 3, 8.5, 1, 4),
			Block.makeCuboidShape(7.5, -0.25, 4, 8.5, 1.25, 8),
			Block.makeCuboidShape(8, 0, 7, 12, 1, 9),
			Block.makeCuboidShape(12, 0, 7.5, 13, 1, 8.5),
			Block.makeCuboidShape(8, -0.25, 7.5, 12, 1.25, 8.5),
			Block.makeCuboidShape(4, 0, 7, 8, 1, 9),
			Block.makeCuboidShape(3, 0, 7.5, 4, 1, 8.5),
			Block.makeCuboidShape(4, -0.25, 7.5, 8, 1.25, 8.5)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	
	public SoulStone(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	
	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		worldIn.addParticle(new SoulParticle.SoulParticleData(1.0F, 1.0F, 1.0F, 0.6F), true, pos.getX() + rand.nextDouble(), pos.getY() + 0.3, pos.getZ() + rand.nextDouble(), 0, 0.05, 0);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.get(FACING)) {
		case NORTH:
			return SHAPE_N;
		case SOUTH:
			return SHAPE_S;
		case EAST:
			return SHAPE_E;
		case WEST:
			return SHAPE_W;
		default:
			return SHAPE_N;
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 * 
	 * @deprecated call via {@link IBlockState#withRotation(Rotation)} whenever
	 *             possible. Implementing/overriding is fine.
	 */
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 * 
	 * @deprecated call via {@link IBlockState#withMirror(Mirror)} whenever
	 *             possible. Implementing/overriding is fine.
	 */
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}	
}
