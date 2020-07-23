package com.GenZVirus.AgeOfTitans.Client.GUI.HUD;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Config.AOTConfig;
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

	public static ResourceLocation SPELL_HUD_TEXTURE = new ResourceLocation(AgeOfTitans.MOD_ID,"textures/gui/hud.png");
	public static ResourceLocation RAGE_BAR = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/rage_bar.png");
	public static Spell SPELL1 = Spell.SPELL_LIST.get(0);
	public static Spell SPELL2 = Spell.SPELL_LIST.get(0);
	public static Spell SPELL3 = Spell.SPELL_LIST.get(0);
	public static Spell SPELL4 = Spell.SPELL_LIST.get(0);
	public static Spell selectedSpell = SPELL1;
	private static Minecraft mc = Minecraft.getInstance();
	private static final int ICON_SIZE = 34;
	private static final int IMAGE_SIZE = 384;
	private static final int RESIZE = 3;
	private static final int IMAGE_RESIZED = IMAGE_SIZE / RESIZE;
	private static int i = 0;
	private static int j = 0;
	private static int spellPos = j;
	public static boolean next = false;
	public static boolean previous = false;
	public static int nr = 0;
	public static boolean locked = true;

	public static void renderHUD() {

		if(locked || Spell.applesEaten == 0) {
			selectedSpell = Spell.SPELL_LIST.get(0);
			return;
		}
		
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
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		int posX = 0;
		int posY;
		if(mc.gameSettings.guiScale > mc.getMainWindow().getHeight() / 240) mc.gameSettings.guiScale = mc.getMainWindow().getHeight() / 240;
		posY = mc.getMainWindow().getScaledHeight() - IMAGE_RESIZED;
		posX = mc.getMainWindow().getScaledWidth() - IMAGE_RESIZED;
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
		case 0: {renderPos0(posX, posY, j);selectedSpell = SPELL1;break;}
		case 1: {renderPos1(posX, posY, j);break;}
		case 2: {renderPos2(posX, posY, j);break;}
		case 3: {renderPos3(posX, posY, j);break;}
		default: break;
		}
		
		mc.getTextureManager().bindTexture(SPELL2.getIconHUD());
		switch(spellPos) {
		case 0: {renderPos1(posX, posY, j);break;}
		case 1: {renderPos2(posX, posY, j);break;}
		case 2: {renderPos3(posX, posY, j);break;}
		case 3: {renderPos0(posX, posY, j);selectedSpell = SPELL2;break;}
		default: break;
		}
		
		mc.getTextureManager().bindTexture(SPELL3.getIconHUD());
		switch(spellPos) {
		case 0: {renderPos2(posX, posY, j);break;}
		case 1: {renderPos3(posX, posY, j);break;}
		case 2: {renderPos0(posX, posY, j);selectedSpell = SPELL3;break;}
		case 3: {renderPos1(posX, posY, j);break;}
		default: break;
		}
		
		mc.getTextureManager().bindTexture(SPELL4.getIconHUD());
		switch(spellPos) {
		case 0: {renderPos3(posX, posY, j);break;}
		case 1: {renderPos0(posX, posY, j);selectedSpell = SPELL4;break;}
		case 2: {renderPos1(posX, posY, j);break;}
		case 3: {renderPos2(posX, posY, j);break;}
		default: break;
		}
		
		int animationReducer = AOTConfig.COMMON.adjust_hud_animation.get(); // can't be < 1
		mc.getTextureManager().bindTexture(SPELL_HUD_TEXTURE);	
			AbstractGui.blit(posX, posY, 0, IMAGE_RESIZED * j, IMAGE_RESIZED * (i / animationReducer), IMAGE_RESIZED, IMAGE_RESIZED, IMAGE_RESIZED*20, IMAGE_RESIZED*4);
		
		if(next) {
			i++;
			nr++;
		}
		
		if(previous) {
			i--;
			nr++;
		}
		
		if(nr > 19 * animationReducer) {
			next = false;
			previous = false;
			nr = 0;
		}
		
		if(i > 19 * animationReducer) {
			i = 0;
			j++;
		}
		
		if(j > 3) {
			j = 0;
		}
		
		if(i < 0) {
			i = 19 * animationReducer;
			j--;
		}
		
		if(j < 0){
			j = 3;
		}
		
		mc.getTextureManager().bindTexture(RAGE_BAR);
		posY = mc.getMainWindow().getScaledHeight() - 80;
		posX = mc.getMainWindow().getScaledWidth() / 2 - 91 ;
		AbstractGui.blit(posX, posY, 0, 0, 0, 182, 16, 16 * 2, 182);
		int percentage = 182 * Spell.ragePoints / 1000; 
		AbstractGui.blit(posX, posY, 0, 0, 16, percentage, 16, 16 * 2, 182);
		
	}
	
	public static void renderPos0(int posX, int posY, int j) {
		switch(j) {
		case 0: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2), posY + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 2), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		case 1: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 1), posY + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 3), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		case 2: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 2), posY + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 2), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		case 3: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 1), posY + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 1), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		}
	}
	
	public static void renderPos1(int posX, int posY, int j) {
		switch(j) {
		case 0: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2), posY + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 1), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		case 1: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 1), posY + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		case 2: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 2), posY + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 1), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		case 3: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 1), posY + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 2), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		}
	}
	
	public static void renderPos2(int posX, int posY, int j) {
		switch(j) {
		case 0: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 3), posY + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 1), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		case 1: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 2), posY + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		case 2: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 1), posY + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 1), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		case 3: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 2), posY + IMAGE_RESIZED / 8 * 3 + (ICON_SIZE/2 + 2), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		}
	}

	public static void renderPos3(int posX, int posY, int j) {
		switch(j) {
		case 0: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 3), posY + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 2), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		case 1: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 2), posY + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 3), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		case 2: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 1), posY + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 2), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		case 3: {AbstractGui.blit(posX + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 2), posY + IMAGE_RESIZED / 8 * 3 - (ICON_SIZE/2 + 1), 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);break;}
		}
	}
	
}
