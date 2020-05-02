package com.GenZVirus.AgeOfTitans.Util;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Capabilities.SpellCapability;
import com.GenZVirus.AgeOfTitans.Capabilities.Instances.SpellInstance;
import com.GenZVirus.AgeOfTitans.Init.DimensionInit;
import com.GenZVirus.AgeOfTitans.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Network.SpellPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class ForgeEventBusSubscriber {

	private static final ResourceLocation SPELL_RESOURCE = new ResourceLocation(AgeOfTitans.MOD_ID, "spell");

	@SubscribeEvent
	public static void storeEvent(Event e) {
		
	}
	
	@SubscribeEvent
	public static void onAttachPlayer(AttachCapabilitiesEvent<Entity> e) {

		if (e.getObject().getEntity() instanceof PlayerEntity) {
			e.addCapability(SPELL_RESOURCE, new SpellCapability());
		}
	}

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
		e.getPlayer().getCapability(SpellCapability.SPELL_CAPABILITY).ifPresent(cap -> {
			PacketHandler.INSTANCE.sendTo(
					new SpellPacket(cap.getSpellSlotbyID(1), cap.getSpellSlotbyID(2), cap.getSpellSlotbyID(3),
							cap.getSpellSlotbyID(4), e.getPlayer().getUniqueID()),
					((ServerPlayerEntity) e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);

		});
	}

	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone e) {
		if (e.isWasDeath()) {
			e.getOriginal().getCapability(SpellCapability.SPELL_CAPABILITY).ifPresent(cap1 -> {
				int id1 = cap1.getSpellSlotbyID(1);
				int id2 = cap1.getSpellSlotbyID(2);
				int id3 = cap1.getSpellSlotbyID(3);
				int id4 = cap1.getSpellSlotbyID(4);
				
				e.getPlayer().getCapability(SpellCapability.SPELL_CAPABILITY).ifPresent(cap2 -> {
					System.out.println(id1);
					System.out.println(id2);
					System.out.println(id3);
					System.out.println(id4);
					cap2.setSpell(id1, id2, id3, id4);
					PacketHandler.INSTANCE.sendTo(
							new SpellPacket(id1, id2, id3, id4, e.getPlayer().getUniqueID()),
							((ServerPlayerEntity) e.getPlayer()).connection.netManager,
							NetworkDirection.PLAY_TO_CLIENT);
				});
			});
		}
	}

	@SubscribeEvent
	public static void registerDimensions(final RegisterDimensionsEvent event) {
		if (DimensionType.byName(AgeOfTitans.EDEN_DIMENSION_TYPE) == null) {
			DimensionManager.registerDimension(AgeOfTitans.EDEN_DIMENSION_TYPE, DimensionInit.EDEN.get(), null, true);
		}
		AgeOfTitans.LOGGER.info("Dimensions Registered!");
	}
}
