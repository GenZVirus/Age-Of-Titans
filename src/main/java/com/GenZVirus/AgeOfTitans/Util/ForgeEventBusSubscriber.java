package com.GenZVirus.AgeOfTitans.Util;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Capabilities.SpellCapability;
import com.GenZVirus.AgeOfTitans.Init.DimensionInit;
import com.GenZVirus.AgeOfTitans.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Network.PlayerSpellSlotPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class ForgeEventBusSubscriber {
	
	private static final ResourceLocation SPELL_RESOURCE = new ResourceLocation(AgeOfTitans.MOD_ID, "spell");
	
	@SubscribeEvent
    public static void onAttachPlayer(AttachCapabilitiesEvent<Entity> e)
    {
        if(e.getObject().getEntity() instanceof PlayerEntity) e.addCapability(SPELL_RESOURCE, new SpellCapability());

        e.getObject().getCapability(SpellCapability.SPELL_CAPABILITY).ifPresent(cap -> {
            cap.setPlayer((PlayerEntity) e.getObject());
        });
    }
	
	@SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e)
    {
        e.getPlayer().getCapability(SpellCapability.SPELL_CAPABILITY).ifPresent(cap -> {
            PacketHandler.INSTANCE.sendTo(new PlayerSpellSlotPacket(cap.getSpellID(), cap.getSpellSlot(), cap.getPlayerUuid()), ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
        });
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone e)
    {
        if(e.isWasDeath())
        {
            e.getOriginal().getCapability(SpellCapability.SPELL_CAPABILITY).ifPresent(cap1 -> {
                e.getPlayer().getCapability(SpellCapability.SPELL_CAPABILITY).ifPresent(cap2 -> {
                    cap2.setSpell(cap1.getSpellID(), cap1.getSpellSlot());
                    cap2.setPlayer(e.getPlayer());
                    PacketHandler.INSTANCE.sendTo(new PlayerSpellSlotPacket(cap1.getSpellID(), cap1.getSpellSlot(), cap1.getPlayerUuid()), ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
                });
            });
        }
    }
	
	@SubscribeEvent
	public static void registerDimensions(final RegisterDimensionsEvent event) {
		if (DimensionType.byName(AgeOfTitans.EDEN_DIMENSION_TYPE) == null) {
			DimensionManager.registerDimension(AgeOfTitans.EDEN_DIMENSION_TYPE, DimensionInit.EDEN.get(), null,
					true);
		}
		AgeOfTitans.LOGGER.info("Dimensions Registered!");
	}
}
