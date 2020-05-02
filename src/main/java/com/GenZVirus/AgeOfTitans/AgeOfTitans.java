package com.GenZVirus.AgeOfTitans;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.GenZVirus.AgeOfTitans.Capabilities.Instances.SpellInstance;
import com.GenZVirus.AgeOfTitans.Capabilities.Interfaces.ISpell;
import com.GenZVirus.AgeOfTitans.Client.Keybind.ModKeybind;
import com.GenZVirus.AgeOfTitans.Init.BiomeInit;
import com.GenZVirus.AgeOfTitans.Init.BlockInit;
import com.GenZVirus.AgeOfTitans.Init.DimensionInit;
import com.GenZVirus.AgeOfTitans.Init.ItemInit;
import com.GenZVirus.AgeOfTitans.Init.ModContainerTypes;
import com.GenZVirus.AgeOfTitans.Init.ModEntityTypes;
import com.GenZVirus.AgeOfTitans.Init.ModTileEntityTypes;
import com.GenZVirus.AgeOfTitans.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
import com.GenZVirus.AgeOfTitans.World.Gen.ModOreGen;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
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
	
	public static final ResourceLocation EDEN_DIMENSION_TYPE = new ResourceLocation(MOD_ID, "eden");
	
    /*
     * AgeOfTitans class constructor
    */
    public AgeOfTitans() {
    	
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	modEventBus.addListener(this::setup);
    	modEventBus.addListener(this::doClientStuff);
    	
    	ItemInit.ITEMS.register(modEventBus);
    	BlockInit.BLOCKS.register(modEventBus);
    	ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
    	ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
    	
    	ModEntityTypes.ENTITY_TYPES.register(modEventBus);
    	
    	BiomeInit.BIOMES.register(modEventBus);
    	DimensionInit.MOD_DIMENSIONS.register(modEventBus);
    	
    	Spell.registerSpells();
    	
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

    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
    	BiomeInit.registeBiomes();
    }
    
    /*
     * Unknown
    */
    
    private void setup(final FMLCommonSetupEvent event)
    {

    	PacketHandler.init();
    	CapabilityManager.INSTANCE.register(ISpell.class, new SpellInstance.Storage(), () -> new SpellInstance());
    	
    }

    /*
     * Client side stuff
    */
    
    private void doClientStuff(final FMLClientSetupEvent event) {
    	ModKeybind.register();
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
