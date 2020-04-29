package com.GenZVirus.AgeOfTitans.Events;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModButton;
import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModHUD;
import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModScreen;
import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModSkillButton;
import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModSkillSlot;

import net.minecraft.client.gui.widget.Widget;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent.MouseClickedEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ModScreenEvents {

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
