package com.GenZVirus.AgeOfTitans.Init;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Entities.ArrowEntity;
import com.GenZVirus.AgeOfTitans.Entities.ReaperEntity;
import com.GenZVirus.AgeOfTitans.Entities.ShockwaveEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES,
			AgeOfTitans.MOD_ID);

	public static final RegistryObject<EntityType<ReaperEntity>> REAPER = ENTITY_TYPES.register("reaper",
			() -> EntityType.Builder.<ReaperEntity>create(ReaperEntity::new, EntityClassification.CREATURE)
					.size(1.0F, 2.0F)
					.build(new ResourceLocation(AgeOfTitans.MOD_ID, "reaper").toString()));
	
	public static final RegistryObject<EntityType<ShockwaveEntity>> SHOCKWAVE = ENTITY_TYPES.register("shockwave",
			() -> EntityType.Builder.<ShockwaveEntity>create(ShockwaveEntity::new, EntityClassification.CREATURE)
					.size(1.0F, 1.0F)
					.build(new ResourceLocation(AgeOfTitans.MOD_ID, "shockwave").toString()));
	
	public static final RegistryObject<EntityType<ArrowEntity>> ARROW = ENTITY_TYPES.register("arrow",
			() -> EntityType.Builder.<ArrowEntity>create(ArrowEntity::new, EntityClassification.MISC)
					.build(new ResourceLocation(AgeOfTitans.MOD_ID, "arrow").toString()));
	
}
