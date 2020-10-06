package com.GenZVirus.AgeOfTitans.Client.ArmorModel.HeavyTitaniumArmor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class TitaniumHelmetModel extends BipedModel<LivingEntity> {

	public TitaniumHelmetModel() {
		super(0);
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.bipedHead = new ModelRenderer(this);
		this.bipedHead.setRotationPoint(0.0F, 24.0F, 0.0F);
		bipedHead.setTextureOffset(27, 28).addBox(-4.0F, -8.0F, 4.0F, 8.0F, 8.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(28, 24).addBox(-2.0F, 0.0F, 5.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(28, 16).addBox(-3.0F, -7.0F, 5.0F, 6.0F, 7.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		bipedHead.setTextureOffset(20, 9).addBox(-3.0F, -10.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
		bipedHead.setTextureOffset(20, 16).addBox(-2.0F, -2.0F, -6.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 23).addBox(1.0F, -2.0F, -6.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(4, 14).addBox(2.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 14).addBox(-3.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(10, 12).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(4, 0).addBox(-4.0F, -8.0F, -5.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 0).addBox(3.0F, -8.0F, -5.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 9).addBox(-1.0F, -8.0F, -6.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(27, 3).addBox(-2.0F, -8.0F, -6.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(3, 26).addBox(1.0F, -8.0F, -6.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(24, 0).addBox(2.0F, -8.0F, -6.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(23, 19).addBox(-3.0F, -8.0F, -6.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(10, 15).addBox(4.0F, -8.0F, -4.0F, 1.0F, 6.0F, 8.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 9).addBox(-5.0F, -8.0F, -4.0F, 1.0F, 6.0F, 8.0F, 0.0F, false);
		bipedHead.setTextureOffset(24, 0).addBox(-5.0F, -2.0F, -3.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 23).addBox(4.0F, -2.0F, -3.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);
		bipedHead.setTextureOffset(0, 31).addBox(4.0F, -1.0F, -2.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		bipedHead.setTextureOffset(10, 29).addBox(-5.0F, -1.0F, -2.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		bipedHead.setTextureOffset(10, 10).addBox(-3.0F, -9.0F, -5.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		bipedHead.setTextureOffset(10, 14).addBox(-0.5F, -4.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		bipedHead.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
	}
	
}
