package com.GenZVirus.AgeOfTitans.Client.ArmorModel.LightTitaniumArmor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class LightTitaniumHoodModel extends BipedModel<LivingEntity> {

	private float offsetX = 4.0F;
	private float offsetY = -5.0F;
	private float offsetZ = -4.0F;
	
	public LightTitaniumHoodModel() {
		super(0);
		textureWidth = 64;
		textureHeight = 64;

		bipedHead = new ModelRenderer(this);
		bipedHead.setRotationPoint(0.0F, 21.0F, 0.0F);
		bipedHead.setTextureOffset(0, 27).addBox(4.0F, -3.0F + offsetY, -4.0F, 1.0F, 8.0F, 8.0F, 0.0F, false);
		bipedHead.setTextureOffset(22, 38).addBox(3.75F, -3.0F + offsetY, -5.0F, 1.0F, 8.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(18, 38).addBox(-4.75F, -3.0F + offsetY, -5.0F, 1.0F, 8.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(20, 20).addBox(-5.0F, -3.0F + offsetY, -4.0F, 1.0F, 8.0F, 8.0F, 0.0F, false);
		bipedHead.setTextureOffset(27, 0).addBox(-4.5F, -3.0F + offsetY, 4.0F, 9.0F, 8.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(30, 19).addBox(-4.0F, -2.5F + offsetY, 4.25F, 8.0F, 8.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(24, 10).addBox(-3.55F, -1.75F + offsetY, 4.5F, 7.0F, 7.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 0).addBox(-4.5F, -4.0F + offsetY, -4.5F, 9.0F, 1.0F, 9.0F, 0.0F, false);
		bipedHead.setTextureOffset(18, 36).addBox(-4.0F, -3.5F + offsetY, -5.25F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 10).addBox(-4.0F, -4.25F + offsetY, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 19).addBox(-3.5F, -4.5F + offsetY, -3.5F, 7.0F, 1.0F, 7.0F, 0.0F, false);
		bipedHead.setTextureOffset(37, 39).addBox(4.1F, -2.5F + offsetY, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 21).addBox(-5.1F, 3.5F + offsetY, 2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(21, 21).addBox(-5.1F, 3.5F + offsetY, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(21, 19).addBox(-5.1F, 3.5F + offsetY, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(3, 22).addBox(-5.1F, 1.5F + offsetY, 2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 23).addBox(-5.1F, 1.5F + offsetY, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(21, 23).addBox(-5.1F, 3.5F + offsetY, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(3, 24).addBox(-5.1F, 1.5F + offsetY, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(24, 24).addBox(-5.1F, -0.5F + offsetY, 2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(24, 20).addBox(-5.1F, -0.5F + offsetY, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(24, 22).addBox(-5.1F, -0.5F + offsetY, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 27).addBox(-5.1F, 1.5F + offsetY, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(4, 27).addBox(-5.1F, -0.5F + offsetY, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(10, 27).addBox(-5.1F, -2.5F + offsetY, 2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(14, 27).addBox(-5.1F, -2.5F + offsetY, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 29).addBox(-5.1F, -2.5F + offsetY, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(4, 29).addBox(-5.1F, -2.5F + offsetY, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(33, 39).addBox(4.1F, -2.5F + offsetY, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(29, 39).addBox(4.1F, -2.5F + offsetY, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(38, 30).addBox(4.1F, -2.5F + offsetY, 2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(38, 28).addBox(4.1F, -0.5F + offsetY, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(26, 38).addBox(4.1F, 1.5F + offsetY, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(14, 33).addBox(4.1F, -0.5F + offsetY, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(10, 33).addBox(4.1F, -0.5F + offsetY, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(4, 33).addBox(4.1F, -0.5F + offsetY, 2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 33).addBox(4.1F, 1.5F + offsetY, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(14, 31).addBox(4.1F, 3.5F + offsetY, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(10, 31).addBox(4.1F, 1.5F + offsetY, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(4, 31).addBox(4.1F, 1.5F + offsetY, 2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 31).addBox(4.1F, 3.5F + offsetY, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(14, 29).addBox(4.1F, 3.5F + offsetY, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(10, 29).addBox(4.1F, 3.5F + offsetY, 2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(32, 32).addBox(-1.0F, -4.6F + offsetY, -3.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 0).addBox(-1.0F, -1.3F + offsetY, 4.6F, 2.0F, 6.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(3, 20).addBox(-3.0F, 3.2F + offsetY, 4.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 14).addBox(2.0F, -0.8F + offsetY, 4.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(4, 14).addBox(2.0F, 1.2F + offsetY, 4.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 16).addBox(2.0F, 3.2F + offsetY, 4.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 19).addBox(-3.0F, 1.2F + offsetY, 4.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(4, 16).addBox(-3.0F, -0.8F + offsetY, 4.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(4, 12).addBox(2.0F, -4.6F + offsetY, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(5, 6).addBox(-3.0F, -4.6F + offsetY, 1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 7).addBox(-3.0F, -4.6F + offsetY, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 10).addBox(-3.0F, -4.6F + offsetY, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 12).addBox(2.0F, -4.6F + offsetY, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(4, 10).addBox(2.0F, -4.6F + offsetY, 1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		bipedHead.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
	}	
}
