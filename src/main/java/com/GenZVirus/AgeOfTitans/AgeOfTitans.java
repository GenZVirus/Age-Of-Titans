package com.GenZVirus.AgeOfTitans;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.GenZVirus.AgeOfTitans.Init.BlockInit;
import com.GenZVirus.AgeOfTitans.Init.ItemInit;
import com.GenZVirus.AgeOfTitans.World.Gen.ModOreGen;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

/*
 * AgeOfTitans class
*/

@Mod("ageoftitans")
@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.MOD)
public class AgeOfTitans
{
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	/*
     * Mod id reference
    */
	public static final String MOD_ID = "ageoftitans";
	
	/*
     * Instance of AgeOfTitans
    */
	public static AgeOfTitans instance;
	
    /*
     * AgeOfTitans class constructor
    */
	
    public AgeOfTitans() {
    	
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	modEventBus.addListener(this::setup);
    	modEventBus.addListener(this::doClientStuff);
    	
    	ItemInit.ITEMS.register(modEventBus);
    	BlockInit.BLOCKS.register(modEventBus);
    	
        instance = this;
        
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();

		BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			final Item.Properties properties = new Item.Properties().group(AgeOfTitansItemGroup.instance);
			final BlockItem blockItem = new BlockItem(block, properties);
			blockItem.setRegistryName(block.getRegistryName());
			registry.register(blockItem);
		});

		LOGGER.debug("Registered BlockItems!");
	}
    
    /*
     * Unknown
    */
    
    private void setup(final FMLCommonSetupEvent event)
    {

    }

    /*
     * Client side stuff
    */
    
    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    /*
     * Server side stuff
    */
    
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }
    
    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
    	ModOreGen.generateOre();
    }
    
    /*
     * Custom ItemGroup class
    */
    public static class AgeOfTitansItemGroup extends ItemGroup{
    	
        /*
         * Custom ItemGroup instances, you can use these to set the ItemGroup of items, blocks etc.
        */
    	public static final AgeOfTitansItemGroup instance = new AgeOfTitansItemGroup(ItemGroup.GROUPS.length, "ageoftitans");
    	
        /*
         * Custom ItemGroup class constructor
        */
    	private AgeOfTitansItemGroup(int index, String label) {
    		super(index, label);
    	}
    	
        /*
         * This function overrides the createIcone function from ItemGroup class which sets the icon in creative tab to the selected item, just change the return to give the ItemStack you want to be shown
        */
    	
    	@Override
    	public ItemStack createIcon() {
    		return new ItemStack(ItemInit.FRUIT_OF_THE_GODS.get());
    	}
    }
}
