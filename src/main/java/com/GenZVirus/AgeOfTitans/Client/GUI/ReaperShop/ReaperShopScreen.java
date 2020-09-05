package com.GenZVirus.AgeOfTitans.Client.GUI.ReaperShop;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Entities.ReaperEntity;
import com.GenZVirus.AgeOfTitans.Common.Network.EditElementPacket;
import com.GenZVirus.AgeOfTitans.Common.Network.GivePlayerItemsPacket;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
import com.GenZVirus.AgeOfTitans.Common.Network.ReaperInteractionPacket;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.PricedItem;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ReaperShopScreen extends Screen {

	// Initializing the class so it is unique
	public static final ReaperShopScreen SCREEN = new ReaperShopScreen();

	public ResourceLocation BACKGROUND = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/reaper_shop_screen.png");

	public ShopItemButton item1_button, item2_button, item3_button;
	public DealButton deal;
	public ReaperEntity reaper;

	public int balance, total;

	// The size of the GUI

	public int xSize = 320;
	public int ySize = 256;

	public int windowXPos = (this.width - this.xSize) / 2;
	public int windowYPos = (this.height - this.ySize) / 2;

	public ReaperShopScreen(ITextComponent titleIn) {
		super(titleIn);
	}

	public ReaperShopScreen() {
		this(getDefaultName());
	}

	public static ITextComponent getDefaultName() {
		return new TranslationTextComponent("screen.reaper_shop");
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	public void resize(Minecraft p_resize_1_, int p_resize_2_, int p_resize_3_) {
		super.resize(p_resize_1_, p_resize_2_, p_resize_3_);
	}

	@Override
	public void init() {
		this.windowXPos = (this.width - this.xSize) / 2;
		this.windowYPos = (this.height - this.ySize) / 2;

		item1_button = new ShopItemButton(windowXPos, windowYPos + 20, (PricedItem) reaper.item1);
		item2_button = new ShopItemButton(windowXPos + 105, windowYPos + 20, (PricedItem) reaper.item2);
		item3_button = new ShopItemButton(windowXPos + 210, windowYPos + 20, (PricedItem) reaper.item3);

		this.deal = new DealButton(windowXPos + 114, windowYPos + 211) {
			@SuppressWarnings("resource")
			@Override
			public void onPress() {
				if (balance >= total) {
					PacketHandlerCommon.INSTANCE.sendToServer(new EditElementPacket(Minecraft.getInstance().player.getUniqueID(), "Balance", -total));
					if (item1_button.active) {
						PacketHandlerCommon.INSTANCE.sendToServer(new GivePlayerItemsPacket(Minecraft.getInstance().player.getUniqueID(), new ItemStack(reaper.item1)));
						reaper.outofstock = true;
					}
					if (item2_button.active) {
						PacketHandlerCommon.INSTANCE.sendToServer(new GivePlayerItemsPacket(Minecraft.getInstance().player.getUniqueID(), new ItemStack(reaper.item2)));
						reaper.outofstock = true;
					}
					if (item3_button.active) {
						PacketHandlerCommon.INSTANCE.sendToServer(new GivePlayerItemsPacket(Minecraft.getInstance().player.getUniqueID(), new ItemStack(reaper.item3)));
						reaper.outofstock = true;
					}
					onClose();
				} else {

				}
				super.onPress();
			}
		};

		this.addButtons();
	}

	public void addButtons() {
		this.buttons.clear();
		this.buttons.add(item1_button);
		this.buttons.add(item2_button);
		this.buttons.add(item3_button);
		this.buttons.add(deal);
	}

	@SuppressWarnings("resource")
	@Override
	public void onClose() {
		reaper.occupied = false;
		PacketHandlerCommon.INSTANCE.sendToServer(new ReaperInteractionPacket(Minecraft.getInstance().player.getUniqueID(), reaper.getEntityId(), reaper.occupied, reaper.outofstock, new ItemStack(reaper.item1), new ItemStack(reaper.item2), new ItemStack(reaper.item3)));
		super.onClose();
	}

	@Override
	public void tick() {
		this.total = 0;
		if (item1_button.active) {
			total += item1_button.item.getPrice();
		}
		if (item2_button.active) {
			total += item2_button.item.getPrice();
		}
		if (item3_button.active) {
			total += item3_button.item.getPrice();
		}
		super.tick();
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.windowXPos = (this.width - this.xSize) / 2;
		this.windowYPos = (this.height - this.ySize) / 2;
		this.renderBackground();

		this.minecraft.getTextureManager().bindTexture(this.BACKGROUND);

		/*
		 * First param is the X position Second param is the y position Third param is
		 * the blit offset Fourth param is the X position from where it starts to
		 * display pixels in the texture image Fifth param is the Y position from where
		 * it starts to display pixels in the texture image Sixth param is the Width of
		 * the image that we want to display Seventh param is the Height of the image
		 * that we want to display Eighth param is the Height of the texture image Ninth
		 * param is the Width of the texture image
		 */

		// Add background

		AbstractGui.blit(this.windowXPos, this.windowYPos, 0, 0, 0, this.xSize, this.ySize, this.ySize, this.xSize);
		final int zLevel = 300;
		IRenderTypeBuffer.Impl renderType = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
		MatrixStack textStack = new MatrixStack();
		textStack.translate(0.0D, 0.0D, (double) zLevel);
		Matrix4f textLocation = textStack.getLast().getMatrix();
		int stringWidth = font.getStringWidth("Balance");
		font.renderString("Balance", this.windowXPos + 248 - stringWidth, this.windowYPos + 232, 0xFFFFFFFF, true, textLocation, renderType, false, 0, 15728880);
		stringWidth = font.getStringWidth(Integer.toString(balance));
		font.renderString(Integer.toString(balance), this.windowXPos + 314 - stringWidth, this.windowYPos + 232, 0xFFFFFFFF, true, textLocation, renderType, false, 0, 15728880);
		stringWidth = font.getStringWidth("Balance");
		font.renderString("Total", this.windowXPos + 248 - stringWidth, this.windowYPos + 208, 0xFFFFFFFF, true, textLocation, renderType, false, 0, 15728880);
		stringWidth = font.getStringWidth(Integer.toString(total));
		font.renderString(Integer.toString(total), this.windowXPos + 314 - stringWidth, this.windowYPos + 208, 0xFFFFFFFF, true, textLocation, renderType, false, 0, 15728880);
		stringWidth = font.getStringWidth("Reaper's Stash");
		font.renderString("Reaper's Stash", this.windowXPos + 160 - stringWidth / 2, this.windowYPos + 4, 0xFFFFFFFF, true, textLocation, renderType, false, 0, 15728880);
		renderType.finish();
		super.render(mouseX, mouseY, partialTicks);
	}

	public List<Widget> getButtons() {
		return this.buttons;
	}

}
