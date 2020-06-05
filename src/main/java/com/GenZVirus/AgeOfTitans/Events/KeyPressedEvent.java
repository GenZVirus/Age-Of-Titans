package com.GenZVirus.AgeOfTitans.Events;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModHUD;
import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModScreen;
import com.GenZVirus.AgeOfTitans.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Network.SpellPacket;
import com.GenZVirus.AgeOfTitans.Util.Helpers.KeyboardHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ChatVisibility;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class KeyPressedEvent {

	// This class purpose is to execute when a key is pressed

	private static int timer = 0; // cooldown for for keys
	public static boolean wasPRESSED = false; // checks if a was pressed
	
	@SubscribeEvent
	public static void keyPressedEvent(ClientTickEvent event) {
		if(timer > 0) timer--;
		Minecraft mc = Minecraft.getInstance();
		if(timer == 0 && KeyboardHelper.isCharacterKeyDown()) {
			timer = 10;
			if(!wasPRESSED) {
				
				// checks for others screens or chat screen, if there are not hidden, the character screen will not show up
				
				if ((mc.currentScreen != null && mc.gameSettings.chatVisibility != ChatVisibility.HIDDEN) || mc.world == null) { 
					return;
				}
				
				// display character screen
				
				Minecraft.getInstance().displayGuiScreen(ModScreen.SCREEN); 
				
				// send a packet to the server and tells it to send data to the player requesting it
				
				wasPRESSED = true;
				PacketHandler.INSTANCE.sendToServer(new SpellPacket(0, 0, 0, 0, mc.player.getUniqueID(), true)); 
			
			} else {
				
				// closes the screen
				
				ModScreen.SCREEN.onClose();
			}
		}
		
		// Blocks the HUD from moving while any screen is on
		
		if ((mc.currentScreen != null && mc.gameSettings.chatVisibility != ChatVisibility.HIDDEN) || mc.world == null) {
			return;
		}
		
		// Spin the HUD clockwise		
		
		if(timer == 0 && KeyboardHelper.isScrollUpKeyDown() && !ModHUD.previous) {
			ModHUD.next = true;
		}
		
		// Spin the HUD counterclockwise
		
		if(timer == 0 && KeyboardHelper.isScrollDownKeyDown() && !ModHUD.next) {
			ModHUD.previous = true;
		}
		event.
	}
	
}
