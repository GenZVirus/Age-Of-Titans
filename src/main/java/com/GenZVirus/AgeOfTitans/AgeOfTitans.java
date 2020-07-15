package com.GenZVirus.AgeOfTitans;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.GenZVirus.AgeOfTitans.Client.Keybind.ModKeybind;
import com.GenZVirus.AgeOfTitans.Common.Config.AOTConfig;
import com.GenZVirus.AgeOfTitans.Common.Init.BiomeInit;
import com.GenZVirus.AgeOfTitans.Common.Init.BlockInit;
import com.GenZVirus.AgeOfTitans.Common.Init.DimensionInit;
import com.GenZVirus.AgeOfTitans.Common.Init.EffectInit;
import com.GenZVirus.AgeOfTitans.Common.Init.ItemInit;
import com.GenZVirus.AgeOfTitans.Common.Init.ModContainerTypes;
import com.GenZVirus.AgeOfTitans.Common.Init.ModEntityTypes;
import com.GenZVirus.AgeOfTitans.Common.Init.ModTileEntityTypes;
import com.GenZVirus.AgeOfTitans.Common.Init.ParticleInit;
import com.GenZVirus.AgeOfTitans.Common.Init.SoundInit;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;
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
    	
    	ModLoadingContext.get().registerConfig(Type.COMMON, AOTConfig.COMMON_SPEC, "AgeOfTitans.toml");
    	
    // Creating an event bus for all registries
    	
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	modEventBus.addListener(this::setup);
    	modEventBus.addListener(this::doClientStuff);
    
    // Registering custom sounds
    	
    	SoundInit.SOUNDS.register(modEventBus);
    	
    // Registering custom particles
    	
    	ParticleInit.PARTICLES.register(modEventBus);
    	
    // Registering custom items
    	
    	ItemInit.ITEMS.register(modEventBus);
    	
    // Register custom effects
    	
    	EffectInit.MOD_EFFECTS.register(modEventBus);
    	
    // Registering custom blocks
    	
    	BlockInit.BLOCKS.register(modEventBus);
    	
    // Registering custom tile entity types
    	
    	ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
    	
    // Registering custom container types
    	
    	ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
    	
    // Registering custom entity types
    	
    	ModEntityTypes.ENTITY_TYPES.register(modEventBus);
    	
    // Registering custom biomes
    	
    	BiomeInit.BIOMES.register(modEventBus);
    	
    // Register custom Dimensions
    	
    	DimensionInit.MOD_DIMENSIONS.register(modEventBus);
    	
    // Register Spell
    	
    	Spell.registerSpells();
    	
    	instance = this;
        
    	MinecraftForge.EVENT_BUS.register(this);
    }


    @SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
    	
    // Registering items from blocks
    	
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
    	
    // Registering custom biomes
    	
    	BiomeInit.registerBiomes();
    }
    
    /*
     * Unknown
    */
    
    private void setup(final FMLCommonSetupEvent event)
    {

    // Initializing the PacketHandler
    	
    	PacketHandler.init();
    	
    }

    /*
     * Client side stuff
    */
    
    private void doClientStuff(final FMLClientSetupEvent event) {
    	
    // Registering the custom Keybinds
    	
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
    	
    // Generating ores
    	
//    	ModOreGen.generateOre();
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
