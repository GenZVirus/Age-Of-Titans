package com.GenZVirus.AgeOfTitans.Common.Init;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.TileEntity.TileEntityInventoryBasic;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
			ForgeRegistries.TILE_ENTITIES, AgeOfTitans.MOD_ID);
	
	public static final RegistryObject<TileEntityType<TileEntityInventoryBasic>> BLACK_HOLE = TILE_ENTITY_TYPES.register("black_hole", () -> TileEntityType.Builder.create(TileEntityInventoryBasic::new, BlockInit.BLACK_HOLE.get()).build(null));

}
