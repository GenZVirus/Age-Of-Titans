package com.GenZVirus.AgeOfTitans.Client.Entity.Render;

import com.GenZVirus.AgeOfTitans.Common.Entities.GravityBombEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class GravityBombRender extends EntityRenderer<GravityBombEntity>{

	public GravityBombRender(EntityRendererManager renderManager) {
		super(renderManager);
		
	}

	@Override
	public void render(GravityBombEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

	}
	
	@Override
	public ResourceLocation getEntityTexture(GravityBombEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
