package com.GenZVirus.AgeOfTitans.Util;

import java.util.List;
import java.util.UUID;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Capabilities.SpellCapability;
import com.GenZVirus.AgeOfTitans.Init.DimensionInit;
import com.GenZVirus.AgeOfTitans.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Network.SpellPacket;
import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
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
	private static List<UUID> uuidList = Lists.newArrayList();
	private static List<Integer> slot1 = Lists.newArrayList();
	private static List<Integer> slot2 = Lists.newArrayList();
	private static List<Integer> slot3 = Lists.newArrayList();
	private static List<Integer> slot4 = Lists.newArrayList();

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
	public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent e) {
		slot1.remove(uuidList.indexOf(e.getPlayer().getUniqueID()));
		slot2.remove(uuidList.indexOf(e.getPlayer().getUniqueID()));
		slot3.remove(uuidList.indexOf(e.getPlayer().getUniqueID()));
		slot4.remove(uuidList.indexOf(e.getPlayer().getUniqueID()));
		uuidList.remove(e.getPlayer().getUniqueID());
		
	}

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
		e.getPlayer().getCapability(SpellCapability.SPELL_CAPABILITY).ifPresent(cap -> {
			System.out.println("OnPlayerLogin:");
			System.out.println("cap.getSpellSlotbyID(1): " + cap.getSpellSlotbyID(1));
			System.out.println("cap.getSpellSlotbyID(2): " + cap.getSpellSlotbyID(2));
			System.out.println("cap.getSpellSlotbyID(3): " + cap.getSpellSlotbyID(3));
			System.out.println("cap.getSpellSlotbyID(4): " + cap.getSpellSlotbyID(4));
			System.out.println("PlayerUuid: " + e.getPlayer().getUniqueID());
			uuidList.add(e.getPlayer().getUniqueID());
			slot1.add(uuidList.indexOf(e.getPlayer().getUniqueID()), cap.getSpellSlotbyID(1));
			slot2.add(uuidList.indexOf(e.getPlayer().getUniqueID()), cap.getSpellSlotbyID(2));
			slot3.add(uuidList.indexOf(e.getPlayer().getUniqueID()), cap.getSpellSlotbyID(3));
			slot4.add(uuidList.indexOf(e.getPlayer().getUniqueID()), cap.getSpellSlotbyID(4));
			
			System.out.println("slots:");
			System.out.println("slot1: " + slot1.get(uuidList.indexOf(e.getPlayer().getUniqueID())));
			System.out.println("slot2: " + slot2.get(uuidList.indexOf(e.getPlayer().getUniqueID())));
			System.out.println("slot3: " + slot3.get(uuidList.indexOf(e.getPlayer().getUniqueID())));
			System.out.println("slot4: " + slot4.get(uuidList.indexOf(e.getPlayer().getUniqueID())));
			PacketHandler.INSTANCE.sendTo(
					new SpellPacket(cap.getSpellSlotbyID(1), cap.getSpellSlotbyID(2), cap.getSpellSlotbyID(3), cap.getSpellSlotbyID(4), e.getPlayer().getUniqueID()),
					((ServerPlayerEntity) e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);

		});
	}
 
	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone e) {
		if (e.isWasDeath()) {
			e.getOriginal().getCapability(SpellCapability.SPELL_CAPABILITY).ifPresent(cap1 -> {
				cap1.setSpell(slot1.get(uuidList.indexOf(e.getPlayer().getUniqueID())), slot2.get(uuidList.indexOf(e.getPlayer().getUniqueID())), slot3.get(uuidList.indexOf(e.getPlayer().getUniqueID())), slot4.get(uuidList.indexOf(e.getPlayer().getUniqueID())));
				cap1.setPlayerUuid(e.getPlayer().getUniqueID());
				System.out.println("OnPlayerClone:");
				System.out.println("cap1.getSpellSlotbyID(1): " + cap1.getSpellSlotbyID(1));
				System.out.println("cap1.getSpellSlotbyID(2): " + cap1.getSpellSlotbyID(2));
				System.out.println("cap1.getSpellSlotbyID(3): " + cap1.getSpellSlotbyID(3));
				System.out.println("cap1.getSpellSlotbyID(4): " + cap1.getSpellSlotbyID(4));
				System.out.println("PlayerUuid: " + cap1.getPlayerUuid());
				int id1 = cap1.getSpellSlotbyID(1);
				int id2 = cap1.getSpellSlotbyID(2);
				int id3 = cap1.getSpellSlotbyID(3);
				int id4 = cap1.getSpellSlotbyID(4);
				
				e.getPlayer().getCapability(SpellCapability.SPELL_CAPABILITY).ifPresent(cap2 -> {
					System.out.println("Setting capability");
					System.out.println("id1: " + id1);
					System.out.println("id2: " + id2);
					System.out.println("id3: " + id3);
					System.out.println("id4: " + id4);
					System.out.println("PlayerUuid: " + cap1.getPlayerUuid());
					cap2.setSpell(id1, id2, id3, id4);
					PacketHandler.INSTANCE.sendToServer(new SpellPacket(cap2.getSpellSlotbyID(1), cap2.getSpellSlotbyID(2), cap2.getSpellSlotbyID(3), cap2.getSpellSlotbyID(4), Minecraft.getInstance().player.getUniqueID()));
//					PacketHandler.INSTANCE.sendTo(
//							new SpellPacket(cap2.getSpellSlotbyID(1), cap2.getSpellSlotbyID(2), cap2.getSpellSlotbyID(3), cap2.getSpellSlotbyID(4), e.getPlayer().getUniqueID()),
//							((ServerPlayerEntity) e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
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
