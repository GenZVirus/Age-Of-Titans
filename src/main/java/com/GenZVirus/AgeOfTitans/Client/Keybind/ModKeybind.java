package com.GenZVirus.AgeOfTitans.Client.Keybind;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ModKeybind {

	// This class sets up keybinds
	
	 public static final KeyBinding LOCKSHUD = new KeyBinding("key.lockhud", GLFW.GLFW_KEY_V, "key.categories.ageoftitans");
	 public static final KeyBinding ABILITY_TREE = new KeyBinding("key.ability_tree", GLFW.GLFW_KEY_C, "key.categories.ageoftitans");
	 public static final KeyBinding SCROLL_UP = new KeyBinding("key.scroll_up", GLFW.GLFW_KEY_X, "key.categories.ageoftitans");
	 public static final KeyBinding SCROLL_DOWN = new KeyBinding("key.scroll_down", GLFW.GLFW_KEY_Z, "key.categories.ageoftitans");
	 public static final KeyBinding FIRE_ABILITY = new KeyBinding("key.fire_ability", GLFW.GLFW_KEY_F, "key.categories.ageoftitans");
	 
	 
	 // Registering keybings
	 
	    public static void register()
	    {
	    	ClientRegistry.registerKeyBinding(LOCKSHUD);
	        ClientRegistry.registerKeyBinding(ABILITY_TREE);
	        ClientRegistry.registerKeyBinding(SCROLL_UP);
	        ClientRegistry.registerKeyBinding(SCROLL_DOWN);
	        ClientRegistry.registerKeyBinding(FIRE_ABILITY);
	    }
	
}
