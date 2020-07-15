package com.GenZVirus.AgeOfTitans.Client.Entity.Render;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Client.Entity.Model.ReaperEntityModel;
import com.GenZVirus.AgeOfTitans.Common.Entities.ReaperEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ReaperEntityRender extends MobRenderer<ReaperEntity, ReaperEntityModel>{

	protected static final ResourceLocation TEXTURE = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/entity/reaper_entity.png");
	
	public ReaperEntityRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ReaperEntityModel(), 0.5F);
	}
	
	@Override
	public ResourceLocation getEntityTexture(ReaperEntity entity) {
		return TEXTURE;
	}
}
