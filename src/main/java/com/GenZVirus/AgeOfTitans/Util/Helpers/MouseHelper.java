package com.GenZVirus.AgeOfTitans.Util.Helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MouseHelper {
	
	// This is the MouseHelper class
	
	private static final long WINDOW = Minecraft.getInstance().getMainWindow().getHandle();
	
	// Checks if the left click is being held down
	
	@OnlyIn(Dist.CLIENT)
	public static boolean isHoldingLeftClick() {
		return InputMappings.isKeyDown(WINDOW, 1);
	}
}
