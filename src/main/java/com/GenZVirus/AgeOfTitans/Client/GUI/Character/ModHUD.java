package com.GenZVirus.AgeOfTitans.Client.GUI.Character;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.ChatVisibility;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModHUD {

	public static ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(AgeOfTitans.MOD_ID,"textures/gui/hud.png");
	public static Spell SPELL1 = Spell.SPELL_LIST.get(0);
	public static Spell SPELL2 = Spell.SPELL_LIST.get(0);
	public static Spell SPELL3 = Spell.SPELL_LIST.get(0);
	public static Spell SPELL4 = Spell.SPELL_LIST.get(0);
	public static Spell selectedSpell = SPELL1;
	private static Minecraft mc = Minecraft.getInstance();
	private static final int ICON_SIZE = 32;
	private static final int IMAGE_SIZE = 384;
	private static final int RESIZE = 3;
	private static final int IMAGE_RESIZED = IMAGE_SIZE / RESIZE;
	private static int i = 0;
	private static int j = 0;
	private static int spellPos = j;
	public static boolean next = false;
	public static boolean previous = false;
	public static int nr = 0;

	public static void renderHUD() {

		Minecraft mc = Minecraft.getInstance();
		if ((mc.currentScreen != null && mc.gameSettings.chatVisibility != ChatVisibility.HIDDEN) || mc.world == null) {
			return;
		}

		assert mc.player != null;

		if (!Minecraft.isGuiEnabled()) {
			return;
		}

		if (mc.gameSettings.showDebugInfo) {
			return;
		}

		renderOverlay();
	}

	public static void renderOverlay() {

		RenderSystem.scalef(1.0F, 1.0F, 1.0F);
		
//		RenderSystem.disableRescaleNormal();
//		RenderHelper.disableStandardItemLighting();
//		RenderSystem.disableLighting();
//		RenderSystem.disableDepthTest();

		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		int posX = 0;
		int posY;
		if(mc.gameSettings.guiScale > mc.getMainWindow().getHeight() / 240) mc.gameSettings.guiScale = mc.getMainWindow().getHeight() / 240;
		posY = mc.getMainWindow().getHeight() / (mc.gameSettings.guiScale != 0 ? mc.gameSettings.guiScale : mc.getMainWindow().getHeight() / 240) - IMAGE_RESIZED;
		posX = mc.getMainWindow().getWidth() / (mc.gameSettings.guiScale != 0 ? mc.gameSettings.guiScale : mc.getMainWindow().getWidth() / 240) - IMAGE_RESIZED;
		posX = posX * 5 / 100;
		posY = posY - posY * 5 / 100;
		
		if(next && spellPos >= 3 && i == 10) {
			spellPos = 0;
		} else if(next && i == 10) {
			spellPos++;
		}
		
		if(previous && spellPos <= 0 && i == 10) {
			spellPos = 3;
		} else if(previous && i == 10) {
			spellPos--;
		}
		
		mc.getTextureManager().bindTexture(SPELL1.getIconHUD());
		switch(spellPos) {
		case 0: {renderPos0(posX, posY);selectedSpell = SPELL1;break;}
		case 1: {renderPos1(posX, posY);break;}
		case 2: {renderPos2(posX, posY);break;}
		case 3: {renderPos3(posX, posY);break;}
		default: break;
		}
		
		mc.getTextureManager().bindTexture(SPELL2.getIconHUD());
		switch(spellPos) {
		case 0: {renderPos1(posX, posY);break;}
		case 1: {renderPos2(posX, posY);break;}
		case 2: {renderPos3(posX, posY);break;}
		case 3: {renderPos0(posX, posY);selectedSpell = SPELL2;break;}
		default: break;
		}
		
		mc.getTextureManager().bindTexture(SPELL3.getIconHUD());
		switch(spellPos) {
		case 0: {renderPos2(posX, posY);break;}
		case 1: {renderPos3(posX, posY);break;}
		case 2: {renderPos0(posX, posY);selectedSpell = SPELL3;break;}
		case 3: {renderPos1(posX, posY);break;}
		default: break;
		}
		
		mc.getTextureManager().bindTexture(SPELL4.getIconHUD());
		switch(spellPos) {
		case 0: {renderPos3(posX, posY);break;}
		case 1: {renderPos0(posX, posY);selectedSpell = SPELL4;break;}
		case 2: {renderPos1(posX, posY);break;}
		case 3: {renderPos2(posX, posY);break;}
		default: break;
		}
		
		mc.getTextureManager().bindTexture(BACKGROUND_TEXTURE);	
			AbstractGui.blit(posX, posY, 0, IMAGE_RESIZED * j, IMAGE_RESIZED * i, IMAGE_RESIZED, IMAGE_RESIZED, IMAGE_RESIZED*20, IMAGE_RESIZED*4);
		
		if(next) {
			i++;
			nr++;
		}
		
		if(previous) {
			i--;
			nr++;
		}
		
		if(nr > 19) {
			next = false;
			previous = false;
			nr = 0;
		}
		
		if(i > 19) {
			i = 0;
			j++;
		}
		
		if(j > 3) {
			j = 0;
		}
		
		if(i < 0) {
			i = 19;
			j--;
		}
		
		if(j < 0){
			j = 3;
		}
	}
	
	public static void renderPos0(int posX, int posY) {
		AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 4), posY + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 2), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
	}
	
	public static void renderPos1(int posX, int posY) {
		AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 3), posY + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 4), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
	}
	
	public static void renderPos2(int posX, int posY) {
		AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 3), posY + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 3), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
	}

	public static void renderPos3(int posX, int posY) {
		AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 2), posY + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 2), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
	}
	
}
