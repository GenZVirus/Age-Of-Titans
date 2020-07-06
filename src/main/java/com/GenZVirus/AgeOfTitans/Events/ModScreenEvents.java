package com.GenZVirus.AgeOfTitans.Events;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModButton;
import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModHUD;
import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModScreen;
import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModSkillButton;
import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModSkillSlot;
import com.GenZVirus.AgeOfTitans.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Network.PlayerUseSpellPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.player.ChatVisibility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent.MouseClickedEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ModScreenEvents {

	private static int timer = 0;

	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void PlayerSpellUseEvent(ClientTickEvent event) {
		if(event.phase == Phase.START) {
			return;
		}
		Minecraft mc = Minecraft.getInstance();
		if ((mc.currentScreen != null && mc.gameSettings.chatVisibility != ChatVisibility.HIDDEN) || mc.world == null) {
			return;
		}
		
		if(timer > 0) {
			timer--;
		}
		PlayerEntity player = Minecraft.getInstance().player;
		if(player == null) return;
		if(ModHUD.selectedSpell.getId() == 1 && player.getHeldItemMainhand().getItem() instanceof SwordItem && timer == 0) {
			if(mc.mouseHelper.isLeftDown()) {
				timer = 20;
				PacketHandler.INSTANCE.sendToServer(new PlayerUseSpellPacket(1, player.getUniqueID())); 
			}
		}
		
		if(ModHUD.selectedSpell.getId() == 2 && timer == 0) {
			if(player.getActiveHand() == Hand.OFF_HAND && player.getActiveItemStack().getItem() instanceof ShieldItem) {
				timer = 20;
				PacketHandler.INSTANCE.sendToServer(new PlayerUseSpellPacket(2, player.getUniqueID()));
			}
		}
		if(ModHUD.selectedSpell.getId() == 3 && timer == 0) {
			if(mc.mouseHelper.isRightDown()) {
				timer = 20;
				PacketHandler.INSTANCE.sendToServer(new PlayerUseSpellPacket(3, player.getUniqueID()));
			}
		}
		if(ModHUD.selectedSpell.getId() == 4 && timer == 0) {
			if(mc.mouseHelper.isRightDown()) {
				timer = 20;
				PacketHandler.INSTANCE.sendToServer(new PlayerUseSpellPacket(4, player.getUniqueID()));
			}
		}
	}
	
	
	@SubscribeEvent
	public static void skillSection(MouseClickedEvent.Post event) {
		if (event.getGui() instanceof ModScreen) {
			List<Widget> buttons = ModScreen.SCREEN.getButtons();
			for (Widget button : buttons) {
				int widthIn = button.x;
				int heightIn = button.y;
				int width = button.getWidth();
				int height = button.getHeight();
				int x = (int) event.getMouseX();
				int y = (int) event.getMouseY();
				if (x >= widthIn && x < widthIn + width && y >= heightIn && y < heightIn + height) {
					if (button instanceof ModButton) {
						((ModButton) button).onPress();
					} else if (button instanceof ModSkillButton) {
						((ModSkillButton) button).onPress();
					} else if (button instanceof ModSkillSlot) {
						((ModSkillSlot) button).onPress();
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void HUD(TickEvent.RenderTickEvent event) {
		ModHUD.renderHUD();
	}

}
