package com.GenZVirus.AgeOfTitans.Client.Entity.Render;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Client.Entity.Model.ReaperEntityModel;
import com.GenZVirus.AgeOfTitans.Common.Entities.ReaperEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ReaperEntityRender extends MobRenderer<ReaperEntity, ReaperEntityModel>{

	protected static final ResourceLocation TEXTURE = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/entity/reaper_entity.png");
	
	public ReaperEntityRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ReaperEntityModel(), 0.5F);
	}
	
	@Override
	public ResourceLocation getEntityTexture(ReaperEntity entity) {
		return TEXTURE;
	}
	
	@Override
	public void render(ReaperEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
}
