package com.GenZVirus.AgeOfTitans.Client.Container;

import java.awt.Color;

import javax.annotation.Nullable;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Common.Network.sendTileEntityDataPacket;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

/**
 * User: brandon3055 Date: 06/01/2015
 *
 * GuiInventoryBasic is a simple gui that does nothing but draw a background
 * image and a line of text on the screen. everything else is handled by the
 * vanilla container code.
 *
 * The Screen is drawn in several layers, most importantly:
 *
 * Background - renderBackground() - eg a grey fill Background texture -
 * drawGuiContainerBackgroundLayer() (eg the frames for the slots) Foreground
 * layer - typically text labels
 *
 */
public class ContainerScreenBasic extends ContainerScreen<ContainerBasic> {

	public TextFieldWidget search;
	private boolean isScrolling;
	private float currentScroll;
	private static final ResourceLocation SCROLL = new ResourceLocation(AgeOfTitans.MOD_ID,"textures/gui/scroll.png");
	public static BlockPos POS;
	public static int ID;

	public ContainerScreenBasic(ContainerBasic containerBasic, PlayerInventory playerInventory, ITextComponent title) {
		super(containerBasic, playerInventory, title);
		// Set the width and height of the gui. Should match the size of the texture!
		xSize = 195;
		ySize = 204;
	}

	@Override
	protected void init() {
		super.init();
		this.search = new TextFieldWidget(this.font, this.guiLeft + 82, this.guiTop + 6, 80, 9,
				I18n.format("itemGroup.search"));
		this.search.setMaxStringLength(50);
		this.search.setEnableBackgroundDrawing(false);
		this.search.setVisible(true);
		this.search.setTextColor(16777215);
		this.search.setText("");
		this.currentScroll = 0.0F;
		if(POS != null)
	    PacketHandler.INSTANCE.sendToServer(new sendTileEntityDataPacket(POS.getX(), POS.getY(), POS.getZ(), ID, 0, this.search.getText()));

	}

	@Override
	public void removed() {
		this.search = null;
		super.removed();
	}

	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
		this.search.render(mouseX, mouseY, partialTicks);
	}

	@Override
	public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
		if (this.search.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_)) {
			return true;
		}
		if (this.mouseLocation(p_mouseClicked_1_, p_mouseClicked_3_)) {
            this.isScrolling = this.needsScrollBars();
            return true;
         }
		return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
	}

	@Override
	public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_,
			double p_mouseDragged_6_, double p_mouseDragged_8_) {
		if (this.isScrolling) {
	         int i = this.guiTop + 18;
	         int j = i + 80;
	         this.currentScroll = ((float)p_mouseDragged_3_ - (float)i - 7.5F) / ((float)(j - i) - 15.0F);
	         this.currentScroll = MathHelper.clamp(this.currentScroll, 0.0F, 1.0F);
	         this.container.scrollTo(this.currentScroll);
	         return true;
	      } else {
		return super.mouseDragged(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_5_, p_mouseDragged_6_,
				p_mouseDragged_8_);
	      }
	}
	
	@Override
	public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
		if(p_mouseReleased_5_ == 0) {
			this.isScrolling = false;
		}
		return super.mouseReleased(p_mouseReleased_1_, p_mouseReleased_3_, p_mouseReleased_5_);
	}
	
	private boolean needsScrollBars() {
	      return this.container.canScroll();
	   }
	
	@Override
	public boolean mouseScrolled(double p_mouseScrolled_1_, double p_mouseScrolled_3_, double p_mouseScrolled_5_) {
		this.currentScroll = (float)((double)this.currentScroll - p_mouseScrolled_5_);
        this.currentScroll = MathHelper.clamp(this.currentScroll, 0.0F, 1.0F);
        if(POS != null)
    	    PacketHandler.INSTANCE.sendToServer(new sendTileEntityDataPacket(POS.getX(), POS.getY(), POS.getZ(), ID, 0, this.search.getText()));
        return true;
	}
	
	@Override
	public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
		if (this.search.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_)) {
			this.updateSearch();
			return true;
		} else if (this.search.isFocused() && this.search.getVisible() && p_keyPressed_1_ != 256) {
			return true;
		}
		return super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
	}

	@Override
	public boolean charTyped(char p_charTyped_1_, int p_charTyped_2_) {
		if (this.search.charTyped(p_charTyped_1_, p_charTyped_2_)) {
			this.updateSearch();
			return true;
		}
		return super.charTyped(p_charTyped_1_, p_charTyped_2_);
	}

	public void resize(Minecraft p_resize_1_, int p_resize_2_, int p_resize_3_) {
	      String s = this.search.getText();
	      this.init(p_resize_1_, p_resize_2_, p_resize_3_);
	      this.search.setText(s);
	      if (!this.search.getText().isEmpty()) {
	         this.updateSearch();
	      }

	   }
	
	protected boolean mouseLocation(double posX, double posY) {
	      int i = this.guiLeft;
	      int j = this.guiTop;
	      int k = i + 175;
	      int l = j + 18;
	      int i1 = k + 14;
	      int j1 = l + 90;
	      return posX >= (double)k && posY >= (double)l && posX < (double)i1 && posY < (double)j1;
	   }
	
	private void updateSearch() {
		this.currentScroll = 0.0F;
		if(POS != null)
	      PacketHandler.INSTANCE.sendToServer(new sendTileEntityDataPacket(POS.getX(), POS.getY(), POS.getZ(), ID, 0, this.search.getText()));

	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the
	 * items) Taken directly from ChestScreen
	 */
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		final float LABEL_XPOS = 8;
		final float FONT_Y_SPACING = 12;
		final float CHEST_LABEL_YPOS = ContainerBasic.TILE_INVENTORY_YPOS - FONT_Y_SPACING;
		font.drawString("Search Items:", LABEL_XPOS, CHEST_LABEL_YPOS, Color.darkGray.getRGB());

		final float PLAYER_INV_LABEL_YPOS = ContainerBasic.PLAYER_INVENTORY_YPOS - FONT_Y_SPACING;
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), LABEL_XPOS,
				PLAYER_INV_LABEL_YPOS, Color.darkGray.getRGB());
	}

	/**
	 * Draws the background layer of this container (behind the items). Taken
	 * directly from ChestScreen / BeaconScreen
	 */
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);

		// width and height are the size provided to the window when initialised after
		// creation.
		// xSize, ySize are the expected size of the texture-? usually seems to be left
		// as a default.
		// The code below is typical for vanilla containers, so I've just copied that-
		// it appears to centre the texture within
		// the available window
		int edgeSpacingX = (this.width - this.xSize) / 2;
		int edgeSpacingY = (this.height - this.ySize) / 2;
		this.blit(edgeSpacingX, edgeSpacingY, 0, 0, this.xSize, this.ySize);
		int i = this.guiLeft + 175;
	    int j = this.guiTop + 18;
	    int k = j + 90;
	    this.minecraft.getTextureManager().bindTexture(SCROLL);
		this.blit(i, j + (int)((float)(k - j - 17) * this.currentScroll), 232 + (this.needsScrollBars() ? 0 : 12), 0, 12, 15);
	}

	public void drawSlot(Slot slotIn) {
	      int i = slotIn.xPos;
	      int j = slotIn.yPos;
	      ItemStack itemstack = slotIn.getStack();
	      boolean flag = false;
	      boolean flag1 = slotIn == this.clickedSlot && !this.draggedStack.isEmpty() && !this.isRightMouseClick;
	      ItemStack itemstack1 = this.minecraft.player.inventory.getItemStack();
	      String s = null;
	      if (slotIn == this.clickedSlot && !this.draggedStack.isEmpty() && this.isRightMouseClick && !itemstack.isEmpty()) {
	         itemstack = itemstack.copy();
	         itemstack.setCount(itemstack.getCount() / 2);
	      } else if (this.dragSplitting && this.dragSplittingSlots.contains(slotIn) && !itemstack1.isEmpty()) {
	         if (this.dragSplittingSlots.size() == 1) {
	            return;
	         }

	         if (Container.canAddItemToSlot(slotIn, itemstack1, true) && this.container.canDragIntoSlot(slotIn)) {
	            itemstack = itemstack1.copy();
	            flag = true;
	            Container.computeStackSize(this.dragSplittingSlots, this.dragSplittingLimit, itemstack, slotIn.getStack().isEmpty() ? 0 : slotIn.getStack().getCount());
	            int k = Math.min(itemstack.getMaxStackSize(), slotIn.getItemStackLimit(itemstack));
	            if (itemstack.getCount() > k) {
	               s = TextFormatting.YELLOW.toString() + k;
	               itemstack.setCount(k);
	            }
	         } else {
	            this.dragSplittingSlots.remove(slotIn);
	            this.updateDragSplitting();
	         }
	      }

	      this.setBlitOffset(100);
	      this.itemRenderer.zLevel = 100.0F;
	      if (itemstack.isEmpty() && slotIn.isEnabled()) {
	         Pair<ResourceLocation, ResourceLocation> pair = slotIn.getBackground();
	         if (pair != null) {
	            TextureAtlasSprite textureatlassprite = this.minecraft.getAtlasSpriteGetter(pair.getFirst()).apply(pair.getSecond());
	            this.minecraft.getTextureManager().bindTexture(textureatlassprite.getAtlasTexture().getTextureLocation());
	            blit(i, j, this.getBlitOffset(), 16, 16, textureatlassprite);
	            flag1 = true;
	         }
	      }

	      if (!flag1) {
	         if (flag) {
	            fill(i, j, i + 16, j + 16, -2130706433);
	         }

	         RenderSystem.enableDepthTest();
	         this.itemRenderer.renderItemAndEffectIntoGUI(this.minecraft.player, itemstack, i, j);
	         this.renderItemOverlayIntoGUI(this.font, itemstack, i, j, s);
	      }

	      this.itemRenderer.zLevel = 0.0F;
	      this.setBlitOffset(0);
	   }
	
	@SuppressWarnings("resource")
	public void renderItemOverlayIntoGUI(FontRenderer fr, ItemStack stack, int xPosition, int yPosition, @Nullable String text) {
	      if (!stack.isEmpty()) {
	         MatrixStack matrixstack = new MatrixStack();
	         if (stack.getCount() != 1 || text != null) {
	            String s = text == null ? String.valueOf(stack.getCount()) : text;
	            if(s.length() > 3 && s.length() <= 6) s = s.substring(0, s.length() - 3) + "K";
	            else if(s.length() > 6) s = s.substring(0, s.length() - 6) + "M";
	            matrixstack.translate(0.0D, 0.0D, (double)(this.itemRenderer.zLevel + 200.0F));
	            IRenderTypeBuffer.Impl irendertypebuffer$impl = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
	            fr.renderString(s, (float)(xPosition + 19 - 2 - fr.getStringWidth(s)), (float)(yPosition + 6 + 3), 16777215, true, matrixstack.getLast().getMatrix(), irendertypebuffer$impl, false, 0, 15728880);
	            irendertypebuffer$impl.finish();
	         }

	         if (stack.getItem().showDurabilityBar(stack)) {
	            RenderSystem.disableDepthTest();
	            RenderSystem.disableTexture();
	            RenderSystem.disableAlphaTest();
	            RenderSystem.disableBlend();
	            Tessellator tessellator = Tessellator.getInstance();
	            BufferBuilder bufferbuilder = tessellator.getBuffer();
	            double health = stack.getItem().getDurabilityForDisplay(stack);
	            int i = Math.round(13.0F - (float)health * 13.0F);
	            int j = stack.getItem().getRGBDurabilityForDisplay(stack);
	            this.itemRenderer.draw(bufferbuilder, xPosition + 2, yPosition + 13, 13, 2, 0, 0, 0, 255);
	            this.itemRenderer.draw(bufferbuilder, xPosition + 2, yPosition + 13, i, 1, j >> 16 & 255, j >> 8 & 255, j & 255, 255);
	            RenderSystem.enableBlend();
	            RenderSystem.enableAlphaTest();
	            RenderSystem.enableTexture();
	            RenderSystem.enableDepthTest();
	         }

	         ClientPlayerEntity clientplayerentity = Minecraft.getInstance().player;
	         float f3 = clientplayerentity == null ? 0.0F : clientplayerentity.getCooldownTracker().getCooldown(stack.getItem(), Minecraft.getInstance().getRenderPartialTicks());
	         if (f3 > 0.0F) {
	            RenderSystem.disableDepthTest();
	            RenderSystem.disableTexture();
	            RenderSystem.enableBlend();
	            RenderSystem.defaultBlendFunc();
	            Tessellator tessellator1 = Tessellator.getInstance();
	            BufferBuilder bufferbuilder1 = tessellator1.getBuffer();
	            this.itemRenderer.draw(bufferbuilder1, xPosition, yPosition + MathHelper.floor(16.0F * (1.0F - f3)), 16, MathHelper.ceil(16.0F * f3), 255, 255, 255, 127);
	            RenderSystem.enableTexture();
	            RenderSystem.enableDepthTest();
	         }

	      }
	   }
	
	// This is the resource location for the background image for the GUI
	private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(AgeOfTitans.MOD_ID,
			"textures/gui/orb_of_storage.png");
}