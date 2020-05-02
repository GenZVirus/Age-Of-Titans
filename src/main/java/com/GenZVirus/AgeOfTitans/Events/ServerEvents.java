package com.GenZVirus.AgeOfTitans.Events;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Capabilities.SpellCapability;

import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class ServerEvents {

	@SubscribeEvent
	public static void serverEvent(TickEvent event) {
		
//		if(Minecraft.getInstance().player != null)
//			Minecraft.getInstance().player.getCapability(SpellCapability.SPELL_CAPABILITY).ifPresent(cap ->{
//				System.out.println("Slot1: " + cap.getSpellSlotbyID(1));
//				System.out.println("Slot2: " + cap.getSpellSlotbyID(2));
//				System.out.println("Slot3: " + cap.getSpellSlotbyID(3));
//				System.out.println("Slot4: " + cap.getSpellSlotbyID(4));
//				System.out.println("Handle: " + cap.getPlayerUuid());
//			});
	}
	
}
