package com.GenZVirus.AgeOfTitans.Common.Events;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Client.GUI.HUD.ModHUD;
import com.GenZVirus.AgeOfTitans.Client.GUI.SpellTree.ModScreen;
import com.GenZVirus.AgeOfTitans.Common.Init.EffectInit;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Common.Network.SpellPacket;
import com.GenZVirus.AgeOfTitans.Common.Network.berserkerBlockBreakerPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
import com.GenZVirus.AgeOfTitans.Util.Helpers.KeyboardHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ChatVisibility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class KeyPressedEvent {

	// This class purpose is to execute when a key is pressed

	private static int timer = 0; // cooldown for for keys
	private static int clickTimer = 0;
	public static boolean wasPRESSED = false; // checks if a was pressed
	
	// Minecraft width and height
	
		private static int oldMinecraftWidth = Minecraft.getInstance().getMainWindow().getWidth();
		private static int oldMinecraftHeight = Minecraft.getInstance().getMainWindow().getHeight();
	
	@SubscribeEvent
	public static void keyPressedEvent(ClientTickEvent event) {
		if(event.phase == Phase.START) {
			return;
		}
		if(timer > 0) timer--;
		Minecraft mc = Minecraft.getInstance();
		if(timer == 0 && KeyboardHelper.isCharacterKeyDown()) {
			timer = 15;
			if(Spell.applesEaten > 0) {
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
		
		// Spin the HUD counterclockwise
		
		if(timer == 0 && KeyboardHelper.isLockHUDKeyDown()) {
			if(!ModHUD.locked)
			ModHUD.locked = true;
			else ModHUD.locked = false;
		}
	}
	
	@SubscribeEvent
	public static void clientCheckResize(ClientTickEvent event) {
		Minecraft mc = Minecraft.getInstance();
		if(mc.world == null || mc.currentScreen == null) {
			return;
		}
		int newMinecraftWidth = Minecraft.getInstance().getMainWindow().getWidth();
		int newMinecraftHeight = Minecraft.getInstance().getMainWindow().getHeight();
		
		
		if(oldMinecraftHeight != newMinecraftHeight || oldMinecraftWidth != newMinecraftWidth) {
			oldMinecraftHeight = newMinecraftHeight;
			oldMinecraftWidth = newMinecraftWidth;
			PacketHandler.INSTANCE.sendToServer(new SpellPacket(0, 0, 0, 0, mc.player.getUniqueID(), true)); 
		}
	}
	
	@SubscribeEvent
	public static void pressLeftClick(ClientTickEvent event) {
		if(event.phase == Phase.START) {
			return;
		}
		Minecraft mc = Minecraft.getInstance();
		if ((mc.currentScreen != null && mc.gameSettings.chatVisibility != ChatVisibility.HIDDEN) || mc.world == null) { 
			return;
		}
		if(clickTimer == 0 && mc.mouseHelper.isLeftDown()) {
			clickTimer = 5;
			PlayerEntity player = mc.player;
			if(!player.world.isRemote) {
				return;
			}
			
			if(!player.getHeldItemMainhand().getItem().equals(Items.AIR)) {
				return;
			}
			
			if(player.isPotionActive(EffectInit.BERSERKER.get())){
				PacketHandler.INSTANCE.sendToServer(new berserkerBlockBreakerPacket(player.getUniqueID()));
			}	
		} else if(clickTimer > 0) clickTimer --;
	}
	
}
