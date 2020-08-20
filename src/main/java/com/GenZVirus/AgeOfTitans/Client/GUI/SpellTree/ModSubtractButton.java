package com.GenZVirus.AgeOfTitans.Client.GUI.SpellTree;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Network.EditElementPacket;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
import com.GenZVirus.AgeOfTitans.Common.Network.ReadElementPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public class ModSubtractButton extends ModButton{

	public Spell spell;
	public boolean isActive = false;
	
	public ModSubtractButton(int widthIn, int heightIn, Spell spell) {
		super(widthIn + 21, heightIn + 11, 10, 10, "");
		this.spell = spell;
	}

	@Override
	public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
		if(!isActive) return;
		if(spell.level > 0) {
			BUTTONS_LOCATION = new ResourceLocation(AgeOfTitans.MOD_ID,"textures/gui/arrowdown.png");
		} else {
			BUTTONS_LOCATION = new ResourceLocation(AgeOfTitans.MOD_ID,"textures/gui/arrowdownoff.png");
		}
		Minecraft minecraft = Minecraft.getInstance();
	    minecraft.getTextureManager().bindTexture(BUTTONS_LOCATION);
	    AbstractGui.blit(this.x, this.y, 0, 0, 0, 10, 10, 10, 10);
	}
	
	@SuppressWarnings("resource")
	@Override
	public void onPress() {
		if(!isActive) return;
		PlayerEntity player = Minecraft.getInstance().player;
		PacketHandlerCommon.INSTANCE.sendToServer(new ReadElementPacket(player.getUniqueID(), "PlayerPoints", 1));
		String element = "Spell" + "_Level" + spell.getId();
		PacketHandlerCommon.INSTANCE.sendToServer(new ReadElementPacket(player.getUniqueID(), element, 1));
		if(spell.level > 0) {
			PacketHandlerCommon.INSTANCE.sendToServer(new EditElementPacket(player.getUniqueID(), element, -1));
			PacketHandlerCommon.INSTANCE.sendToServer(new EditElementPacket(player.getUniqueID(), "PlayerPoints", 1));
		}
		this.playDownSound(Minecraft.getInstance().getSoundHandler());
	}
	
}
