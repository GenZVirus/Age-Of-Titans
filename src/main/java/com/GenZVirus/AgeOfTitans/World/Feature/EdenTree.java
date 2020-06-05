package com.GenZVirus.AgeOfTitans.World.Feature;

import java.util.Random;

import com.GenZVirus.AgeOfTitans.Init.BlockInit;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraftforge.common.IPlantable;

public class EdenTree extends Tree {

	// This class is the eden tree configuration
	
	// Tree configuration
	
	public static final TreeFeatureConfig EDEN_TREE_CONFIG = (new TreeFeatureConfig.Builder(
			new SimpleBlockStateProvider(BlockInit.EDEN_WOOD_LOG.get().getDefaultState()),
			new SimpleBlockStateProvider(BlockInit.EDEN_LEAVES.get().getDefaultState()), 
			new BlobFoliagePlacer(2, 0)))
					.baseHeight(9)
					.heightRandA(2)
					.trunkHeight(6)
					.trunkHeightRandom(1)
					.ignoreVines()
					.setSapling((IPlantable) BlockInit.EDEN_SAPLING.get()).build();

	// Get tree feature method
	
	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean bool) {
		return Feature.NORMAL_TREE.withConfiguration(EDEN_TREE_CONFIG);
	}

}
