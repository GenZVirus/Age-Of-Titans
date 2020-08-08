package com.GenZVirus.AgeOfTitans.Client.Entity.Render;

import com.GenZVirus.AgeOfTitans.Common.Entities.SwordSlashEntity;
import com.GenZVirus.AgeOfTitans.Common.Particles.SwordSlashParticle;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;

public class SwordSlashRender extends EntityRenderer<SwordSlashEntity> {

	public SwordSlashRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void render(SwordSlashEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

		double offset = 1.0D;

		double pitch = entityIn.getPitchYaw().x;
		double yaw = entityIn.getPitchYaw().y;

		double pitchRadian = pitch * (Math.PI / 180); // X rotation
		double yawRadian = yaw * (Math.PI / 180); // Y rotation

		double newPosX;
		double newPosY;
		double newPosZ;

		double x = entityIn.getPosX();
		double y = entityIn.getPosY() + 1.0D;
		double z = entityIn.getPosZ();

		double nr = 0.0D;
		double pitchTop = pitchRadian;
		double pitchBottom = pitchRadian;

		while (nr <= 0.3D) {

			double offset2 = offset - nr;
			pitchTop = pitchRadian;
			pitchBottom = pitchRadian;

			for (double i = 0.1D; i <= 0.5D - nr; i += 0.1D) {
				pitchBottom += i;
				newPosX = offset2 * -Math.sin(yawRadian) * Math.cos(pitchBottom);
				newPosY = offset2 * -Math.sin(pitchBottom);
				newPosZ = offset2 * Math.cos(yawRadian) * Math.cos(pitchBottom);

				entityIn.world.addParticle(new SwordSlashParticle.SwordSlashParticleData(1.0f, 1.0f, 1.0f, 1.0f), true,
						x + newPosX, y + newPosY, z + newPosZ, 0, 0, 0);

				pitchTop -= i;
				newPosX = offset2 * -Math.sin(yawRadian) * Math.cos(pitchTop);
				newPosY = offset2 * -Math.sin(pitchTop);
				newPosZ = offset2 * Math.cos(yawRadian) * Math.cos(pitchTop);

				entityIn.world.addParticle(new SwordSlashParticle.SwordSlashParticleData(1.0f, 1.0f, 1.0f, 1.0f), true,
						x + newPosX, y + newPosY, z + newPosZ, 0, 0, 0);
			}

			nr += 0.1D;

		}

		for (int i = 0; i < 10; i++) {
			pitchTop = pitchRadian - 1.5D + 0.1D * i;
			pitchBottom = pitchRadian + 1.5D - 0.1D * i;
			offset -= 0.1D * i;

			newPosX = offset * -Math.sin(yawRadian) * Math.cos(pitchBottom);
			newPosY = offset * -Math.sin(pitchBottom);
			newPosZ = offset * Math.cos(yawRadian) * Math.cos(pitchBottom);

			entityIn.world.addParticle(ParticleTypes.FLAME, true, x + newPosX, y + newPosY, z + newPosZ, 0, 0, 0);

			newPosX = offset * -Math.sin(yawRadian) * Math.cos(pitchTop);
			newPosY = offset * -Math.sin(pitchTop);
			newPosZ = offset * Math.cos(yawRadian) * Math.cos(pitchTop);

			entityIn.world.addParticle(ParticleTypes.FLAME, true, x + newPosX, y + newPosY, z + newPosZ, 0, 0, 0);
		}
	}

	@Override
	public ResourceLocation getEntityTexture(SwordSlashEntity entity) {
		return null;
	}

}
