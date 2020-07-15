package com.GenZVirus.AgeOfTitans.Common.Init;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Objects.Blocks.ExampleChestBlock;
import com.GenZVirus.AgeOfTitans.Common.Objects.Blocks.ModSaplingBlock;
import com.GenZVirus.AgeOfTitans.Common.Objects.Blocks.SpecialBlock;
import com.GenZVirus.AgeOfTitans.World.Feature.EdenTree;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/*
 * BlockInit class
*/


public class BlockInit {
	
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, AgeOfTitans.MOD_ID);
	
	/*
     * Here are the instances of custom blocks added to the game
    */
	public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block", () -> new SpecialBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(1.0F, 1.0F).sound(SoundType.METAL).harvestLevel(3)));

	
	public static final RegistryObject<Block> TITANIUM_BLOCK = BLOCKS.register("titanium_block", () -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(10.0F, 1000.0F).sound(SoundType.METAL).harvestLevel(3)));
	public static final RegistryObject<Block> TITANIUM_ORE = BLOCKS.register("titanium_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(10.0F, 1000.0F).sound(SoundType.STONE).harvestLevel(3)));
	
	public static final RegistryObject<Block> EDEN_WOOD_PLANKS = BLOCKS.register("eden_wood_planks", () -> new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 1.0F).sound(SoundType.WOOD).harvestLevel(3)));
	public static final RegistryObject<Block> EDEN_WOOD_LOG = BLOCKS.register("eden_wood_log", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD).hardnessAndResistance(5.0F, 5.0F).sound(SoundType.WOOD).harvestLevel(3)));
	public static final RegistryObject<Block> EDEN_LEAVES = BLOCKS.register("eden_leaves", () -> new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> EDEN_SAPLING = BLOCKS.register("eden_sapling", () -> new ModSaplingBlock(() -> new EdenTree(), Block.Properties.from(Blocks.OAK_SAPLING)));

	public static final RegistryObject<Block> EXAMPLE_CHEST = BLOCKS.register("example_chest", () -> new ExampleChestBlock(Block.Properties.from(BlockInit.TITANIUM_BLOCK.get())));

}
