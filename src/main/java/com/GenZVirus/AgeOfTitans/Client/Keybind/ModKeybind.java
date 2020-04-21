package com.GenZVirus.AgeOfTitans.Client.Keybind;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ModKeybind {

	 public static KeyBinding character = new KeyBinding("key.character", GLFW.GLFW_KEY_C, "key.categories.ageoftitans");;
	 
	    public static void register()
	    {
	        ClientRegistry.registerKeyBinding(character);
	    }
	
}
