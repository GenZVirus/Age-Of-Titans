package com.GenZVirus.AgeOfTitans.Client.GUI.SpellTree;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;

public class ModASButton extends ModButton{

	public ModASButton(int widthIn, int heightIn, int width, int height, String text) {
		super(widthIn, heightIn, width, height, text);
	}

	@Override
	public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
		Minecraft minecraft = Minecraft.getInstance();
	    minecraft.getTextureManager().bindTexture(BUTTONS_LOCATION);
	    AbstractGui.blit(this.x, this.y, 0, 0, 0, 10, 10, 10, 10);
	}
	@Override
	public void onPress() {
		this.playDownSound(Minecraft.getInstance().getSoundHandler());
	}
	
}
