package com.GenZVirus.AgeOfTitans.Client.Entity.Render;

import com.GenZVirus.AgeOfTitans.Entities.SwordSlashEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;

public class SwordSlashRender extends EntityRenderer<SwordSlashEntity>{
	
	public SwordSlashRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}
	
	@Override
	public void render(SwordSlashEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,IRenderTypeBuffer bufferIn, int packedLightIn) {
		double x = entityIn.getPosX();
		double y = entityIn.getPosY();
		double z = entityIn.getPosZ();
		double k = 0.3D;
		for(double i = 0.1D; i <= 0.5D; i += 0.1D) {
			for(double j = 0.1D; j <= 0.5D; j += 0.1D) {
		entityIn.world.addParticle(ParticleTypes.FLAME, true, x + k - j, y + i, z, 0, 0, 0);
		entityIn.world.addParticle(ParticleTypes.FLAME, true, x + k - j, y - i, z, 0, 0, 0);
			}
		}
		for(double j = 0.1D; j <= 0.5D; j += 0.1D)
			entityIn.world.addParticle(ParticleTypes.FLAME, true, x + 0.3D - j, y , z, 0, 0, 0);
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(SwordSlashEntity entity) {
		return null;
	}
	
}
