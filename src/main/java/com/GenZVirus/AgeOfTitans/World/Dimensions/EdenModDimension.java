package com.GenZVirus.AgeOfTitans.World.Dimensions;

import java.util.function.BiFunction;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

public class EdenModDimension extends ModDimension{
	
	// This class is used as a getter
	
	// This method returns the dimension type through a BiFunction
	
	@Override
	public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
		return EdenDimension::new;
	}	
}
