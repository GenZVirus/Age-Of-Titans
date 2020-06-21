package com.GenZVirus.AgeOfTitans.Client.GUI.Character;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModSkillButton extends Widget {

	private ResourceLocation BUTTONS_LOCATION;
	public boolean isSelected = false;
	public Spell spell;
	
	public ModSkillButton(int xIn, int yIn, int widthIn, int heightIn, String msg, Spell spell) {
		super(xIn, yIn, widthIn, heightIn, msg);
		this.BUTTONS_LOCATION = spell.getIcon();
		this.spell = spell;
	}
	@SuppressWarnings("resource")
	public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
	      Minecraft minecraft = Minecraft.getInstance();
	      minecraft.getTextureManager().bindTexture(BUTTONS_LOCATION);

	      AbstractGui.blit(this.x, this.y, 0, 0, 0, this.width, this.height, this.height, this.width);
	      	      
	      if(this.isHovered) {
	    	  List<String> stringList = spell.getDescription();
	    	  System.out.println(this.x + " / " + (int)Minecraft.getInstance().mouseHelper.getMouseX());
	    	  
	    	  this.renderTooltip(stringList, (int)Minecraft.getInstance().mouseHelper.getMouseX() / 2 - 100, this.y + 36, Minecraft.getInstance().fontRenderer);
	      }
	   }
	
	public void renderTooltip(List<String> stringList, int xIn, int yIn, FontRenderer font) {
	      drawHoveringText(stringList, xIn, yIn, Minecraft.getInstance().getMainWindow().getWidth(), Minecraft.getInstance().getMainWindow().getHeight(), -1, font);
	}
	
	public static void drawHoveringText(List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font)
    {
        if (!textLines.isEmpty())
        {
            
            RenderSystem.disableRescaleNormal();
            RenderSystem.disableDepthTest();
            int tooltipTextWidth = 0;

            for (String textLine : textLines)
            {
                int textLineWidth = font.getStringWidth(textLine);
                if (textLineWidth > tooltipTextWidth)
                    tooltipTextWidth = textLineWidth;
            }

            boolean needsWrap = false;

            int titleLinesCount = 1;
            int tooltipX = mouseX + 12;
            if (tooltipX + tooltipTextWidth + 4 > screenWidth)
            {
                tooltipX = mouseX - 16 - tooltipTextWidth;
                if (tooltipX < 4) // if the tooltip doesn't fit on the screen
                {
                    if (mouseX > screenWidth / 2)
                        tooltipTextWidth = mouseX - 12 - 8;
                    else
                        tooltipTextWidth = screenWidth - 16 - mouseX;
                    needsWrap = true;
                }
            }

            if (maxTextWidth > 0 && tooltipTextWidth > maxTextWidth)
            {
                tooltipTextWidth = maxTextWidth;
                needsWrap = true;
            }

            if (needsWrap)
            {
                int wrappedTooltipWidth = 0;
                List<String> wrappedTextLines = new ArrayList<String>();
                for (int i = 0; i < textLines.size(); i++)
                {
                    String textLine = textLines.get(i);
                    List<String> wrappedLine = font.listFormattedStringToWidth(textLine, tooltipTextWidth);
                    if (i == 0)
                        titleLinesCount = wrappedLine.size();

                    for (String line : wrappedLine)
                    {
                        int lineWidth = font.getStringWidth(line);
                        if (lineWidth > wrappedTooltipWidth)
                            wrappedTooltipWidth = lineWidth;
                        wrappedTextLines.add(line);
                    }
                }
                tooltipTextWidth = wrappedTooltipWidth;
                textLines = wrappedTextLines;

                if (mouseX > screenWidth / 2)
                    tooltipX = mouseX - 16 - tooltipTextWidth;
                else
                    tooltipX = mouseX + 12;
            }

            int tooltipY = mouseY - 12;
            int tooltipHeight = 8;

            if (textLines.size() > 1)
            {
                tooltipHeight += (textLines.size() - 1) * 10;
                if (textLines.size() > titleLinesCount)
                    tooltipHeight += 2; // gap between title lines and next lines
            }

            if (tooltipY < 4)
                tooltipY = 4;
            else if (tooltipY + tooltipHeight + 4 > screenHeight)
                tooltipY = screenHeight - tooltipHeight - 4;

            final int zLevel = 300;
            int backgroundColor = 0xF0100010;
            int borderColorStart = 0x505000FF;
            int borderColorEnd = (borderColorStart & 0xFEFEFE) >> 1 | borderColorStart & 0xFF000000;
           
            drawGradientRect(zLevel, tooltipX - 3, tooltipY - 4, tooltipX + tooltipTextWidth + 3, tooltipY - 3, backgroundColor, backgroundColor);
            drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 4, backgroundColor, backgroundColor);
            drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
            drawGradientRect(zLevel, tooltipX - 4, tooltipY - 3, tooltipX - 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
            drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 3, tooltipY - 3, tooltipX + tooltipTextWidth + 4, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
            drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
            drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 2, tooltipY - 3 + 1, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
            drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY - 3 + 1, borderColorStart, borderColorStart);
            drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 2, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, borderColorEnd, borderColorEnd);

            IRenderTypeBuffer.Impl renderType = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
            MatrixStack textStack = new MatrixStack();
            textStack.translate(0.0D, 0.0D, (double)zLevel);
            Matrix4f textLocation = textStack.getLast().getMatrix();

            for (int lineNumber = 0; lineNumber < textLines.size(); ++lineNumber)
            {
                String line = textLines.get(lineNumber);
                if (line != null)
                    font.renderString(line, (float)tooltipX, (float)tooltipY, 65280, true, textLocation, renderType, false, 0, 15728880);

                if (lineNumber + 1 == titleLinesCount)
                    tooltipY += 2;

                tooltipY += 10;
            }

            renderType.finish();

            RenderSystem.enableDepthTest();
            RenderSystem.enableRescaleNormal();
        }
    }
	
	 public static void drawGradientRect(int zLevel, int left, int top, int right, int bottom, int startColor, int endColor)
	    {
	        float startAlpha = (float)(startColor >> 24 & 255) / 255.0F;
	        float startRed   = (float)(startColor >> 16 & 255) / 255.0F;
	        float startGreen = (float)(startColor >>  8 & 255) / 255.0F;
	        float startBlue  = (float)(startColor       & 255) / 255.0F;
	        float endAlpha   = (float)(endColor   >> 24 & 255) / 255.0F;
	        float endRed     = (float)(endColor   >> 16 & 255) / 255.0F;
	        float endGreen   = (float)(endColor   >>  8 & 255) / 255.0F;
	        float endBlue    = (float)(endColor         & 255) / 255.0F;

	        RenderSystem.disableTexture();
	        RenderSystem.enableBlend();
	        RenderSystem.disableAlphaTest();
	        RenderSystem.defaultBlendFunc();
	        RenderSystem.shadeModel(GL11.GL_SMOOTH);

	        Tessellator tessellator = Tessellator.getInstance();
	        BufferBuilder buffer = tessellator.getBuffer();
	        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
	        buffer.pos(right,    top, zLevel).color(startRed, startGreen, startBlue, startAlpha).endVertex();
	        buffer.pos( left,    top, zLevel).color(startRed, startGreen, startBlue, startAlpha).endVertex();
	        buffer.pos( left, bottom, zLevel).color(  endRed,   endGreen,   endBlue,   endAlpha).endVertex();
	        buffer.pos(right, bottom, zLevel).color(  endRed,   endGreen,   endBlue,   endAlpha).endVertex();
	        tessellator.draw();

	        RenderSystem.shadeModel(GL11.GL_FLAT);
	        RenderSystem.disableBlend();
	        RenderSystem.enableAlphaTest();
	        RenderSystem.enableTexture();
	    }
	
	public void onPress() {
		this.playDownSound(Minecraft.getInstance().getSoundHandler());
		for(Widget button : ModScreen.SCREEN.getButtons()) {
			if(button instanceof ModSkillButton) {
				((ModSkillButton) button).isSelected = false;
			}
		}
		this.isSelected = true;
	}

}
