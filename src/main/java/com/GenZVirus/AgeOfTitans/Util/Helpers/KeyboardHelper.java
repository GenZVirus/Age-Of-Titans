package com.GenZVirus.AgeOfTitans.Util.Helpers;

import org.lwjgl.glfw.GLFW;

import com.GenZVirus.AgeOfTitans.Client.Keybind.ModKeybind;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class KeyboardHelper {

	private static final long WINDOW = Minecraft.getInstance().getMainWindow().getHandle();
	
	@OnlyIn(Dist.CLIENT)
	public static boolean isHoldingShift() {
		return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(WINDOW,GLFW.GLFW_KEY_RIGHT_SHIFT);
	}

	@OnlyIn(Dist.CLIENT)
	public static boolean isHoldingCTRL() {
		return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_CONTROL) || InputMappings.isKeyDown(WINDOW,GLFW.GLFW_KEY_RIGHT_CONTROL);
	}
	
	@OnlyIn(Dist.CLIENT)
	public static boolean isCharacterKeyDown() {
		return InputMappings.isKeyDown(WINDOW, ModKeybind.CHARACTER.getKey().getKeyCode());
	}
	
	@OnlyIn(Dist.CLIENT)
	public static boolean isScrollUpKeyDown() {
		return InputMappings.isKeyDown(WINDOW, ModKeybind.SCROLL_UP.getKey().getKeyCode());
	}
	
	@OnlyIn(Dist.CLIENT)
	public static boolean isScrollDownKeyDown() {
		return InputMappings.isKeyDown(WINDOW, ModKeybind.SCROLL_DOWN.getKey().getKeyCode());
	}
}
