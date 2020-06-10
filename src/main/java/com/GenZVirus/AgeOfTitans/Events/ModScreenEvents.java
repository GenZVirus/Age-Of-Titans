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

import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent.MouseClickedEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ModScreenEvents {

	private static int timer = 0;
	
	@SubscribeEvent
	public static void PlayerSpellUseEvent(PlayerTickEvent event) {
		if(event.phase == Phase.START) {
			return;
		}
		if(timer > 0) {
			timer--;
		}
		PlayerEntity player = event.player;
		if(ModHUD.selectedSpell.getId() == 1 && player.getHeldItemMainhand().getItem() instanceof SwordItem && timer == 0) {
			if(player.isSwingInProgress) {
				timer = 120;
				PacketHandler.INSTANCE.sendToServer(new PlayerUseSpellPacket(1, player.getUniqueID())); 
			}
		}
		
		if(ModHUD.selectedSpell.getId() == 2 && timer == 0) {
			if(player.getActiveHand() == Hand.OFF_HAND && player.getActiveItemStack().getItem() instanceof ShieldItem) {
				timer = 60;
				PacketHandler.INSTANCE.sendToServer(new PlayerUseSpellPacket(2, player.getUniqueID()));
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
