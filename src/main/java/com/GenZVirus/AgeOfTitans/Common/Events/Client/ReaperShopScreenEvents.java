package com.GenZVirus.AgeOfTitans.Common.Events.Client;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Client.GUI.ReaperShop.ReaperShopScreen;
import com.GenZVirus.AgeOfTitans.Client.GUI.ReaperShop.ShopItemButton;

import net.minecraft.client.gui.widget.Widget;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent.MouseClickedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ReaperShopScreenEvents {

	@SubscribeEvent
	public static void ItemSelection(MouseClickedEvent.Post event) {
		if (event.getGui() instanceof ReaperShopScreen) {
			List<Widget> buttons = ReaperShopScreen.SCREEN.getButtons();
			for (Widget button : buttons) {
				int widthIn = button.x;
				int heightIn = button.y;
				int width = button.getWidth();
				int height = button.getHeight();
				int x = (int) event.getMouseX();
				int y = (int) event.getMouseY();
				if (x >= widthIn && x < widthIn + width && y >= heightIn && y < heightIn + height) {
					if(button instanceof ShopItemButton) {
						((ShopItemButton) button).onPress();
					}
				}
			}
		}
	}
	
}
