package com.GenZVirus.AgeOfTitans.Util.Helpers;

import org.lwjgl.glfw.GLFW;

import com.GenZVirus.AgeOfTitans.Client.Keybind.ModKeybind;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class KeyboardHelper {

	// This class is the KeyBoard helper it checks for keys 
	
	private static final long WINDOW = Minecraft.getInstance().getMainWindow().getHandle();
	
	// Checks if the shift is being held down
	
	@OnlyIn(Dist.CLIENT)
	public static boolean isHoldingShift() {
		return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(WINDOW,GLFW.GLFW_KEY_RIGHT_SHIFT);
	}

	// Checks if the ctrl is being held down
	
	@OnlyIn(Dist.CLIENT)
	public static boolean isHoldingCTRL() {
		return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_CONTROL) || InputMappings.isKeyDown(WINDOW,GLFW.GLFW_KEY_RIGHT_CONTROL);
	}
	
	// Checks if the CharacterKey is being held down, which is a key that opens and closes the GUI
	
	@OnlyIn(Dist.CLIENT)
	public static boolean isCharacterKeyDown() {
		return InputMappings.isKeyDown(WINDOW, ModKeybind.CHARACTER.getKey().getKeyCode());
	}

	// Checks if the SCROLL_UP is being held down, which is a key that changes what skill is selected from the HUD
	
	@OnlyIn(Dist.CLIENT)
	public static boolean isScrollUpKeyDown() {
		return InputMappings.isKeyDown(WINDOW, ModKeybind.SCROLL_UP.getKey().getKeyCode());
	}
	
	// Checks if the SCROLL_DOWN is being held down, which is a key that changes what skill is selected from the HUD
	
	@OnlyIn(Dist.CLIENT)
	public static boolean isScrollDownKeyDown() {
		return InputMappings.isKeyDown(WINDOW, ModKeybind.SCROLL_DOWN.getKey().getKeyCode());
	}
}
