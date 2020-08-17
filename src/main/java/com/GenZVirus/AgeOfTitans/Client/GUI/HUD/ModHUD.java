package com.GenZVirus.AgeOfTitans.Client.GUI.HUD;

import java.util.Random;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Config.AOTConfig;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.ChatVisibility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.ForgeIngameGui;

@OnlyIn(Dist.CLIENT)
public class ModHUD {

	public static ResourceLocation SPELL_HUD_TEXTURE = new ResourceLocation(AgeOfTitans.MOD_ID,"textures/gui/hud.png");
	public static ResourceLocation BAR = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/bar.png");
	public static ResourceLocation RAGE_BAR_FILL = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/rage_bar_fill.png");
	public static ResourceLocation HEALTH_BAR_FILL = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/health_bar_fill.png");
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
	public static int offset = 0;

	public static void renderHUD() {
		if (mc.world == null) {
			return;
		}
		
		assert mc.player != null;

		if (!Minecraft.isGuiEnabled()) {
			return;
		}

		if (mc.gameSettings.showDebugInfo) {
			return;
		}
		System.out.println(mc.getFrameTimer());
		if(offset >= 178) offset = 0;
		offset++;
		
		if(mc.currentScreen != null && mc.gameSettings.chatVisibility != ChatVisibility.HIDDEN) {
			return;
		}
		
		if(locked || Spell.APPLES_EATEN == 0) {
			selectedSpell = Spell.SPELL_LIST.get(0);
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
		
		posY = mc.getMainWindow().getScaledHeight() - 80;
		posX = mc.getMainWindow().getScaledWidth() / 2 - 91 ;
		mc.getTextureManager().bindTexture(BAR);
		AbstractGui.blit(posX, posY, 0, 0, 0, 182, 16, 16, 182);
		int percentage = (int) (178 * mc.player.getHealth() / mc.player.getMaxHealth());
		mc.getTextureManager().bindTexture(RAGE_BAR_FILL);
		AbstractGui.blit(posX + 2, posY + 2, 0, offset, 0, percentage, 12, 12, 178);
		
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
	
	public static void renderHealth() {
		mc.getProfiler().startSection("AOThealth");
		RenderSystem.scalef(1.0F, 1.0F, 1.0F);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		int posX;
		int posY;
		
		// Health Bar
		
		posY = mc.getMainWindow().getScaledHeight() - ForgeIngameGui.left_height;
		posX = mc.getMainWindow().getScaledWidth() / 2 - 91 ;
		mc.getTextureManager().bindTexture(BAR);
		AbstractGui.blit(posX, posY, 0, 0, 0, 182, 16, 16, 182);
		int percentage = (int) (178 * mc.player.getHealth() / mc.player.getMaxHealth());
		mc.getTextureManager().bindTexture(HEALTH_BAR_FILL);
		AbstractGui.blit(posX + 2, posY + 2, 0, offset, 0, percentage, 12, 12, 178);
		
		// Health Text
		
		final int zLevel = 300;
		IRenderTypeBuffer.Impl renderType = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
		MatrixStack textStack = new MatrixStack();
		textStack.translate(0.0D, 0.0D, (double)zLevel);
		Matrix4f textLocation = textStack.getLast().getMatrix();
		String health = Integer.toString((int)mc.player.getHealth()) + " / " + Integer.toString((int)mc.player.getMaxHealth());
		int stringWidth = mc.fontRenderer.getStringWidth(health);
		mc.fontRenderer.renderString(health, posX + 91 - stringWidth / 2, posY + 5, 0xFFFFFFFF, true, textLocation, renderType, false, 0, 15728880);
		renderType.finish();
		
		mc.getProfiler().endSection();
	}
	
	 public static void renderFood()
	    {
	        mc.getProfiler().startSection("AOTfood");
	        mc.getTextureManager().bindTexture(Screen.GUI_ICONS_LOCATION);
	        Random rand = new Random();
	        PlayerEntity player = (PlayerEntity) mc.getRenderViewEntity();
	        RenderSystem.enableBlend();
	        int left = mc.getMainWindow().getScaledWidth() / 2 + 91;
	        int top = mc.getMainWindow().getScaledHeight() - ForgeIngameGui.right_height - 10;
	        boolean unused = false;// Unused flag in vanilla, seems to be part of a 'fade out' mechanic

	        FoodStats stats = mc.player.getFoodStats();
	        int level = stats.getFoodLevel();

	        for (int i = 0; i < 10; ++i)
	        {
	            int idx = i * 2 + 1;
	            int x = left - i * 8 - 9;
	            int y = top;
	            int icon = 16;
	            byte background = 0;

	            if (mc.player.isPotionActive(Effects.HUNGER))
	            {
	                icon += 36;
	                background = 13;
	            }
	            if (unused) background = 1; //Probably should be a += 1 but vanilla never uses this

	            if (player.getFoodStats().getSaturationLevel() <= 0.0F && mc.ingameGUI.getTicks() % (level * 3 + 1) == 0)
	            {
	                y = top + (rand.nextInt(3) - 1);
	            }

	            AbstractGui.blit(x, y, 16 + background * 9, 27, 9, 9, 256, 256);

	            if (idx < level)
	            	AbstractGui.blit(x, y, icon + 36, 27, 9, 9, 256, 256);
	            else if (idx == level)
	            	AbstractGui.blit(x, y, icon + 45, 27, 9, 9, 256, 256);
	        }
	        RenderSystem.disableBlend();
	        mc.getProfiler().endSection();
	    }
	
	 public static void renderArmor()
	    {
	        mc.getProfiler().startSection("AOTarmor");
	        mc.getTextureManager().bindTexture(Screen.GUI_ICONS_LOCATION);
	        RenderSystem.enableBlend();
	        int left = mc.getMainWindow().getScaledWidth() / 2 - 91;
	        int top = mc.getMainWindow().getScaledHeight() - ForgeIngameGui.left_height - 10;

	        int level = mc.player.getTotalArmorValue();
	        for (int i = 1; level > 0 && i < 20; i += 2)
	        {
	            if (i < level)
	            {
	            	AbstractGui.blit(left, top, 34, 9, 9, 9, 256, 256);
	            }
	            else if (i == level)
	            {
	            	AbstractGui.blit(left, top, 25, 9, 9, 9, 256, 256);
	            }
	            else if (i > level)
	            {
	            	AbstractGui.blit(left, top, 16, 9, 9, 9, 256, 256);
	            }
	            left += 8;
	        }

	        RenderSystem.disableBlend();
	        mc.getProfiler().endSection();
	    }
	 
}
