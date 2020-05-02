package com.GenZVirus.AgeOfTitans.Events;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModHUD;
import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModScreen;
import com.GenZVirus.AgeOfTitans.Util.Helpers.KeyboardHelper;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class KeyPressedEvent {


	private static int timer = 0;
	private static boolean PRESSED = false;
	
	@SubscribeEvent
	public static void keyPressedEvent(ClientTickEvent event) {
		if(timer > 0) timer--;
		if(timer == 0 && KeyboardHelper.isCharacterKeyDown()) {
			timer = 5;
			if(!PRESSED) {
				Minecraft.getInstance().displayGuiScreen(ModScreen.SCREEN);
				PRESSED = true;
			} else {
				ModScreen.SCREEN.onClose();
				PRESSED = false;
			}
		}
		if(timer == 0 && KeyboardHelper.isScrollUpKeyDown() && !ModHUD.previous) {
			ModHUD.next = true;
		}
		if(timer == 0 && KeyboardHelper.isScrollDownKeyDown() && !ModHUD.next) {
			ModHUD.previous = true;
		}
	}
	
}
