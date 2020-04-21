package com.GenZVirus.AgeOfTitans.Events;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Client.Keybind.ModKeybind;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
@OnlyIn(Dist.CLIENT)
public class KeyPressedEvent {

	@SubscribeEvent
	public static void keyPressedEvent(KeyInputEvent event) {
		if(ModKeybind.character.isPressed()) {
			Minecraft.getInstance().player.sendChatMessage("Testing!");
		}
	}
	
}
