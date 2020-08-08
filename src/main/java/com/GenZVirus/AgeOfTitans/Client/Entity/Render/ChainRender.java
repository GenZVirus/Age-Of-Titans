package com.GenZVirus.AgeOfTitans.Client.Entity.Render;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Entities.ChainEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.HangingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LightType;

public class ChainRender extends EntityRenderer<ChainEntity>{

	public ChainRender(EntityRendererManager renderManager) {
		super(renderManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(ChainEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		if(entityIn.shooter != null)
		renderLeash(entityIn, partialTicks, matrixStackIn, bufferIn, entityIn.shooter);
	}
	
	@SuppressWarnings("unused")
	private <E extends Entity> void renderLeash(ChainEntity entityLivingIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, E leashHolder) {
	      matrixStackIn.push();
	      double d0 = (double)(MathHelper.lerp(partialTicks * 0.5F, leashHolder.rotationYaw, leashHolder.prevRotationYaw) * ((float)Math.PI / 180F));
	      double d1 = (double)(MathHelper.lerp(partialTicks * 0.5F, leashHolder.rotationPitch, leashHolder.prevRotationPitch) * ((float)Math.PI / 180F));
	      double d2 = Math.cos(d0);
	      double d3 = Math.sin(d0);
	      double d4 = Math.sin(d1);
	      if (leashHolder instanceof HangingEntity) {
	         d2 = 0.0D;
	         d3 = 0.0D;
	         d4 = -1.0D;
	      }

	      double d5 = Math.cos(d1);
	      double d6 = MathHelper.lerp((double)partialTicks, leashHolder.prevPosX, leashHolder.getPosX()) - d2 * 0.7D - d3 * 0.5D * d5;
	      double d7 = MathHelper.lerp((double)partialTicks, leashHolder.prevPosY + (double)leashHolder.getEyeHeight() * 0.7D, leashHolder.getPosY() + (double)leashHolder.getEyeHeight() * 0.7D) - d4 * 0.5D - 0.25D;
	      double d8 = MathHelper.lerp((double)partialTicks, leashHolder.prevPosZ, leashHolder.getPosZ()) - d3 * 0.7D + d2 * 0.5D * d5;
	      double d9 = (double)(MathHelper.lerp(partialTicks, entityLivingIn.renderYawOffset, entityLivingIn.prevRenderYawOffset) * ((float)Math.PI / 180F)) + (Math.PI / 2D);
	      d2 = Math.cos(d9) * (double)entityLivingIn.getWidth() * 0.4D;
	      d3 = Math.sin(d9) * (double)entityLivingIn.getWidth() * 0.4D;
	      double d10 = MathHelper.lerp((double)partialTicks, entityLivingIn.prevPosX, entityLivingIn.getPosX()) + d2;
	      double d11 = MathHelper.lerp((double)partialTicks, entityLivingIn.prevPosY, entityLivingIn.getPosY());
	      double d12 = MathHelper.lerp((double)partialTicks, entityLivingIn.prevPosZ, entityLivingIn.getPosZ()) + d3;
	      matrixStackIn.translate(d2, -(1.6D - (double)entityLivingIn.getHeight()) * 0.5D, d3);
	      float f = (float)(d6 - d10);
	      float f1 = (float)(d7 - d11);
	      float f2 = (float)(d8 - d12);
	      float f3 = 0.025F;
	      
	      Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
	      float f4 = MathHelper.fastInvSqrt(f * f + f2 * f2) * 0.025F / 2.0F;
	      float f5 = f2 * f4;
	      float f6 = f * f4;
	      int i = this.getBlockLight(entityLivingIn, partialTicks);
	      int j = this.getBlockLight((PlayerEntity) leashHolder, partialTicks);
	      int k = entityLivingIn.world.getLightFor(LightType.SKY, new BlockPos(entityLivingIn.getEyePosition(partialTicks)));
	      int l = entityLivingIn.world.getLightFor(LightType.SKY, new BlockPos(leashHolder.getEyePosition(partialTicks)));
	      renderSide(bufferIn, matrix4f, f, f1, f2, i, j, k, l, 0.325F, 0.325F, f5, f6);
//	      renderSide(ivertexbuilder, matrix4f, f, f1, f2, i, j, k, l, 0.325F, 0.0F, f5, f6);
	      matrixStackIn.pop();
	   }
	
	protected int getBlockLight(PlayerEntity entityIn, float partialTicks) {
	      return entityIn.isBurning() ? 15 : entityIn.world.getLightFor(LightType.BLOCK, new BlockPos(entityIn.getEyePosition(partialTicks)));
	   }
	
	public static void renderSide(IRenderTypeBuffer bufferIn, Matrix4f matrixIn, float p_229119_2_, float p_229119_3_, float p_229119_4_, int blockLight, int holderBlockLight, int skyLight, int holderSkyLight, float p_229119_9_, float posY, float posX, float posZ) {
	      int i = 100;
	      RenderState.TransparencyState TRANSLUCENT_TRANSPARENCY = new RenderState.TransparencyState("translucent_transparency", () -> {
   	          RenderSystem.enableBlend();
   	          RenderSystem.defaultBlendFunc();
   	       }, () -> {
   	          RenderSystem.disableBlend();
   	       });
	      for(int j = 0; j < i ; ++j) {
	         float f = (float)j / ((float) (i - 1));
	         int k = (int)MathHelper.lerp(f, (float)blockLight, (float)holderBlockLight);
	         int l = (int)MathHelper.lerp(f, (float)skyLight, (float)holderSkyLight);
	         int i1 = LightTexture.packLight(k, l);
	         if(j != 1) {
	   	      IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.makeType("chain", DefaultVertexFormats.POSITION_COLOR_TEX_LIGHTMAP, 7, 262144, RenderType.State.getBuilder().transparency(TRANSLUCENT_TRANSPARENCY).texture(new RenderState.TextureState(new ResourceLocation(AgeOfTitans.MOD_ID,"textures/blocks/chain.png"), false, true)).cull(new RenderState.CullState(false)).lightmap(new RenderState.LightmapState(true)).build(true)));
	        	 addVertexPair(ivertexbuilder, matrixIn, i1, p_229119_2_, p_229119_3_, p_229119_4_, p_229119_9_, posY, i, j, posX, posZ);
	         } else {
	   	      IVertexBuilder ivertexbuilder2 = bufferIn.getBuffer(RenderType.makeType("chain_head", DefaultVertexFormats.POSITION_COLOR_TEX_LIGHTMAP, 7, 262144, RenderType.State.getBuilder().transparency(TRANSLUCENT_TRANSPARENCY).texture(new RenderState.TextureState(new ResourceLocation(AgeOfTitans.MOD_ID,"textures/blocks/chain_head.png"), false, true)).cull(new RenderState.CullState(false)).lightmap(new RenderState.LightmapState(true)).build(true)));
	        	 addVertexPair(ivertexbuilder2, matrixIn, i1, p_229119_2_, p_229119_3_, p_229119_4_, p_229119_9_, posY, i, j, posX, posZ);
	         }
	      }

	   }

	   public static void addVertexPair(IVertexBuilder bufferIn, Matrix4f matrixIn, int packedLight, float p_229120_3_, float p_229120_4_, float p_229120_5_, float p_229120_6_, float posY, int i, int j, float posX, float posZ) {
	 		   
	      float f3 = (float)j / (float)i;
	      float f4 = p_229120_3_ * f3;
	      float f5 = p_229120_4_ * (f3 * f3 + f3) * 0.5F + ((float)i - (float)j) / ((float)i * 0.75F) + 0.025F;
	      float f6 = p_229120_5_ * f3;
	      bufferIn.pos(matrixIn, f4 - posX, f5 + posY, f6 + posZ).color(1.0F, 1.0F, 1.0F, 1.0F).tex(0, 0).lightmap(packedLight).endVertex();
	      bufferIn.pos(matrixIn, f4 + posX, f5 + p_229120_6_ - posY, f6 - posZ).color(1.0F, 1.0F, 1.0F, 1.0F).tex(0, 1).lightmap(packedLight).endVertex();
	      
	      j += 1;
	      
	      f3 = (float)j / (float)i;
	      f4 = p_229120_3_ * f3;
	      f5 = p_229120_4_ * (f3 * f3 + f3) * 0.5F + ((float)i - (float)j) / ((float)i * 0.75F) + 0.025F;
	      f6 = p_229120_5_ * f3;
	      bufferIn.pos(matrixIn, f4 + posX, f5 + p_229120_6_ - posY, f6 - posZ).color(1.0F, 1.0F, 1.0F, 1.0F).tex(1, 1).lightmap(packedLight).endVertex();
	      bufferIn.pos(matrixIn, f4 - posX, f5 + posY, f6 + posZ).color(1.0F, 1.0F, 1.0F, 1.0F).tex(1, 0).lightmap(packedLight).endVertex();

	   }
	
	@Override
	public ResourceLocation getEntityTexture(ChainEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
