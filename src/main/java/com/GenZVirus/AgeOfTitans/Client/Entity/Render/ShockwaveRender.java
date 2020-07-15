package com.GenZVirus.AgeOfTitans.Client.Entity.Render;

import java.util.Random;

import com.GenZVirus.AgeOfTitans.Common.Entities.ShockwaveEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;

public class ShockwaveRender extends EntityRenderer<ShockwaveEntity> {

	public ShockwaveRender(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public void render(ShockwaveEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
//		double offset = 1.0D;
//		double pitch = entityIn.getPitchYaw().x;
//		double yaw = entityIn.getPitchYaw().y;		
//		double pitchRadian = pitch * (Math.PI / 180); // X rotation
//		double yawRadian = yaw * (Math.PI / 180); // Y rotation 
//		double newPosX = offset * -Math.sin( yawRadian ) * Math.cos( pitchRadian );
//		double newPosY = offset * -Math.sin( pitchRadian );
//		double newPosZ = offset *  Math.cos( yawRadian ) * Math.cos( pitchRadian );
//		double x = entityIn.getPosX();
//		double y = entityIn.getPosY();
//		double z = entityIn.getPosZ();
//		for(int i = 0 ; i < 20; i++)
//		entityIn.world.addParticle(ParticleTypes.FLAME, true, x + newPosX + new Random().nextDouble(), y + newPosY + new Random().nextDouble() + 1.6D, z + new Random().nextDouble() + newPosZ, (newPosX + new Random().nextDouble()) * 5, (newPosY  + new Random().nextDouble()) * 5, (newPosZ + new Random().nextDouble()) * 5);
	}
	
	@Override
	public ResourceLocation getEntityTexture(ShockwaveEntity entity) {
		return null;
	}

}
