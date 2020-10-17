package com.GenZVirus.AgeOfTitans.Client.ArmorModel.LightTitaniumArmor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class LightTitaniumLeggingsModel extends BipedModel<LivingEntity> {

	private float offsetX = 4.0F;
	private float offsetY = 10.0F;
	private float offsetZ = -4.0F;
	private float offsetLeggsY = 4.0F;
	
	public LightTitaniumLeggingsModel() {
		super(0);
		textureWidth = 64;
		textureHeight = 64;

		bipedBody = new ModelRenderer(this);
		bipedBody.setRotationPoint(0.0F, 26.1F, 0.0F);
		bipedBody.setTextureOffset(0, 15).addBox(-2.9F, -2.0F + offsetY, -2.1F, 7.0F, 4.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(0, 0).addBox(-4.1F, -2.0F + offsetY, 1.1F, 7.0F, 4.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(0, 5).addBox(-2.9F, -2.0F + offsetY, 1.1F, 7.0F, 4.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(0, 10).addBox(-4.1F, -2.0F + offsetY, -2.1F, 7.0F, 4.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(32, 0).addBox(3.1F, -2.0F + offsetY, -1.5F, 1.0F, 4.0F, 3.0F, 0.0F, false);
		bipedBody.setTextureOffset(28, 17).addBox(-4.1F, -2.0F + offsetY, -1.5F, 1.0F, 4.0F, 3.0F, 0.0F, false);

		bipedLeftLeg = new ModelRenderer(this);
		bipedLeftLeg.setRotationPoint(2.5143F, 32.0F, 0.0F);
		bipedLeftLeg.setTextureOffset(31, 32).addBox(-1.4143F, -4.1F + offsetLeggsY, -2.1F, 3.0F, 8.0F, 1.0F, 0.0F, false);
		bipedLeftLeg.setTextureOffset(0, 31).addBox(-2.6143F, -4.1F + offsetLeggsY, 1.1F, 3.0F, 8.0F, 1.0F, 0.0F, false);
		bipedLeftLeg.setTextureOffset(8, 31).addBox(-1.4143F, -4.1F + offsetLeggsY, 1.1F, 3.0F, 8.0F, 1.0F, 0.0F, false);
		bipedLeftLeg.setTextureOffset(31, 8).addBox(-2.6143F, -4.1F + offsetLeggsY, -2.1F, 3.0F, 8.0F, 1.0F, 0.0F, false);
		bipedLeftLeg.setTextureOffset(8, 20).addBox(-2.6143F, -4.1F + offsetLeggsY, -1.5F, 1.0F, 8.0F, 3.0F, 0.0F, false);
		bipedLeftLeg.setTextureOffset(0, 20).addBox(0.5857F, -4.1F + offsetLeggsY, -1.5F, 1.0F, 8.0F, 3.0F, 0.0F, false);
		bipedLeftLeg.setTextureOffset(24, 33).addBox(-1.4143F, -3.3F + offsetLeggsY, -2.2F, 2.0F, 7.0F, 1.0F, 0.0F, false);
		bipedLeftLeg.setTextureOffset(39, 39).addBox(-0.9143F, -3.2F + offsetLeggsY, 1.3F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		bipedLeftLeg.setTextureOffset(32, 24).addBox(-1.4143F, -3.3F + offsetLeggsY, 1.2F, 2.0F, 7.0F, 1.0F, 0.0F, false);
		bipedLeftLeg.setTextureOffset(38, 25).addBox(-0.9143F, -3.2F + offsetLeggsY, -2.3F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		bipedLeftLeg.setTextureOffset(5, 20).addBox(0.6857F, 2.4F + offsetLeggsY, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedLeftLeg.setTextureOffset(18, 13).addBox(0.6857F, 0.4F + offsetLeggsY, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedLeftLeg.setTextureOffset(16, 11).addBox(0.6857F, -1.6F + offsetLeggsY, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedLeftLeg.setTextureOffset(15, 14).addBox(0.6857F, -3.6F + offsetLeggsY, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		bipedRightLeg = new ModelRenderer(this);
		bipedRightLeg.setRotationPoint(-2.5143F, 32.0F, 0.0F);
		bipedRightLeg.setTextureOffset(16, 27).addBox(-0.3857F, -4.1F + offsetLeggsY, -2.1F, 3.0F, 8.0F, 1.0F, 0.0F, false);
		bipedRightLeg.setTextureOffset(24, 0).addBox(-1.5857F, -4.1F + offsetLeggsY, 1.1F, 3.0F, 8.0F, 1.0F, 0.0F, false);
		bipedRightLeg.setTextureOffset(24, 24).addBox(-0.3857F, -4.1F + offsetLeggsY, 1.1F, 3.0F, 8.0F, 1.0F, 0.0F, false);
		bipedRightLeg.setTextureOffset(23, 10).addBox(-1.5857F, -4.1F + offsetLeggsY, -2.1F, 3.0F, 8.0F, 1.0F, 0.0F, false);
		bipedRightLeg.setTextureOffset(16, 0).addBox(-1.5857F, -4.1F + offsetLeggsY, -1.5F, 1.0F, 8.0F, 3.0F, 0.0F, false);
		bipedRightLeg.setTextureOffset(16, 16).addBox(1.6143F, -4.1F + offsetLeggsY, -1.5F, 1.0F, 8.0F, 3.0F, 0.0F, false);
		bipedRightLeg.setTextureOffset(37, 17).addBox(-0.5857F, -3.3F + offsetLeggsY, -2.2F, 2.0F, 7.0F, 1.0F, 0.0F, false);
		bipedRightLeg.setTextureOffset(39, 6).addBox(-0.0857F, -3.2F + offsetLeggsY, 1.3F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		bipedRightLeg.setTextureOffset(16, 36).addBox(-0.5857F, -3.3F + offsetLeggsY, 1.2F, 2.0F, 7.0F, 1.0F, 0.0F, false);
		bipedRightLeg.setTextureOffset(0, 40).addBox(-0.0857F, -3.2F + offsetLeggsY, -2.3F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		bipedRightLeg.setTextureOffset(37, 0).addBox(-1.6857F, 2.4F + offsetLeggsY, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedRightLeg.setTextureOffset(33, 17).addBox(-1.6857F, 0.4F + offsetLeggsY, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedRightLeg.setTextureOffset(24, 21).addBox(-1.6857F, -1.6F + offsetLeggsY, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedRightLeg.setTextureOffset(24, 19).addBox(-1.6857F, -3.6F + offsetLeggsY, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		bipedBody.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
		bipedLeftLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
		bipedRightLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
	}	
}
