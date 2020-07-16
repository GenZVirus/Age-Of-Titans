package com.GenZVirus.AgeOfTitans.Common.Init;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Entities.ChainEntity;
import com.GenZVirus.AgeOfTitans.Common.Entities.ReaperEntity;
import com.GenZVirus.AgeOfTitans.Common.Entities.ShockwaveEntity;
import com.GenZVirus.AgeOfTitans.Common.Entities.SwordSlashEntity;

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
	
	public static final RegistryObject<EntityType<SwordSlashEntity>> SWORD_SLASH = ENTITY_TYPES.register("swordslash",
			() -> EntityType.Builder.<SwordSlashEntity>create(SwordSlashEntity::new, EntityClassification.CREATURE)
					.size(0.5F, 0.5F)
					.build(new ResourceLocation(AgeOfTitans.MOD_ID, "swordslash").toString()));
	
	public static final RegistryObject<EntityType<ShockwaveEntity>> SHOCKWAVE = ENTITY_TYPES.register("shockwave",
			() -> EntityType.Builder.<ShockwaveEntity>create(ShockwaveEntity::new, EntityClassification.CREATURE)
					.size(1.0F, 1.0F)
					.build(new ResourceLocation(AgeOfTitans.MOD_ID, "shockwave").toString()));
	
	public static final RegistryObject<EntityType<ChainEntity>> CHAIN = ENTITY_TYPES.register("chain",
			() -> EntityType.Builder.<ChainEntity>create(ChainEntity::new, EntityClassification.CREATURE)
					.size(1.0F, 1.0F)
					.build(new ResourceLocation(AgeOfTitans.MOD_ID, "chain").toString()));
	
}