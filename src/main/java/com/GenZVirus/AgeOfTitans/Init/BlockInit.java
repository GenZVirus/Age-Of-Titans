package com.GenZVirus.AgeOfTitans.Init;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.AgeOfTitans.AgeOfTitansItemGroup;
import com.GenZVirus.AgeOfTitans.Objects.Blocks.SpecialBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

/*
 * BlockInit class
*/

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.MOD)
@ObjectHolder(AgeOfTitans.MOD_ID)
public class BlockInit {
	
	/*
     * Here are the instances of custom blocks added to the game
    */
	
	public static final Block example_block = null;
	public static final Block special_block = null;	
	/*
     * This function registers the blocks
    */
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(10.0F, 1000.0F).sound(SoundType.SAND).harvestLevel(3)).setRegistryName("example_block"));
		event.getRegistry().register(new SpecialBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(10.0F, 1000.0F).sound(SoundType.SAND).harvestLevel(3)).setRegistryName("special_block"));

	}
	
	/*
     * This function registers the items from blocks
    */
	
	@SubscribeEvent
	public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new BlockItem(example_block, new Item.Properties().group(AgeOfTitansItemGroup.instance)).setRegistryName("example_block"));
		event.getRegistry().register(new BlockItem(special_block, new Item.Properties().group(AgeOfTitansItemGroup.instance)).setRegistryName("special_block"));
	}
	
}
