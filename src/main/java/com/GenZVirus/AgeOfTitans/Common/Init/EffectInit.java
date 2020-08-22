package com.GenZVirus.AgeOfTitans.Common.Init;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Config.AOTConfig;
import com.GenZVirus.AgeOfTitans.Common.Effects.ModEffect;
import com.GenZVirus.AgeOfTitans.Common.Effects.ModRenderlessEffect;
import com.GenZVirus.AgeOfTitans.Common.Effects.ModTitanEffect;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInit {

	public static final DeferredRegister<Effect> MOD_EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, AgeOfTitans.MOD_ID);

	public static final RegistryObject<Effect> TITAN = MOD_EFFECTS.register("titan", () -> new ModTitanEffect(ModEffect.AOTType.TITAN, EffectType.BENEFICIAL, 0).addAttributesModifier(SharedMonsterAttributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE9900", 10.0D, AttributeModifier.Operation.ADDITION).addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD700", 1.0D, AttributeModifier.Operation.ADDITION));
	
	public static final RegistryObject<Effect> MONSTER_EASY = MOD_EFFECTS.register("monster_easy", () -> new ModRenderlessEffect(ModEffect.AOTType.TITAN, EffectType.BENEFICIAL, 0).addAttributesModifier(SharedMonsterAttributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE9901", 5.0D, AttributeModifier.Operation.ADDITION).addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD701", 0.5D, AttributeModifier.Operation.ADDITION));
	public static final RegistryObject<Effect> MONSTER_NORMAL = MOD_EFFECTS.register("monster_normal", () -> new ModRenderlessEffect(ModEffect.AOTType.TITAN, EffectType.BENEFICIAL, 0).addAttributesModifier(SharedMonsterAttributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE9901", 10.0D, AttributeModifier.Operation.ADDITION).addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD701", 1.0D, AttributeModifier.Operation.ADDITION));
	public static final RegistryObject<Effect> MONSTER_HARD = MOD_EFFECTS.register("monster_hard", () -> new ModRenderlessEffect(ModEffect.AOTType.TITAN, EffectType.BENEFICIAL, 0).addAttributesModifier(SharedMonsterAttributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE9901", 15.0D, AttributeModifier.Operation.ADDITION).addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD701", 1.5D, AttributeModifier.Operation.ADDITION));
	
	public static final RegistryObject<Effect> TAMEABLE_EASY = MOD_EFFECTS.register("tamable_easy", () -> new ModRenderlessEffect(ModEffect.AOTType.TITAN, EffectType.BENEFICIAL, 0).addAttributesModifier(SharedMonsterAttributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE9901", 15.0D, AttributeModifier.Operation.ADDITION).addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD701", 1.5D, AttributeModifier.Operation.ADDITION));
	public static final RegistryObject<Effect> TAMEABLE_NORMAL = MOD_EFFECTS.register("tamable_normal", () -> new ModRenderlessEffect(ModEffect.AOTType.TITAN, EffectType.BENEFICIAL, 0).addAttributesModifier(SharedMonsterAttributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE9901", 10.0D, AttributeModifier.Operation.ADDITION).addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD701", 1.0D, AttributeModifier.Operation.ADDITION));
	public static final RegistryObject<Effect> TAMEABLE_HARD = MOD_EFFECTS.register("tamable_hard", () -> new ModRenderlessEffect(ModEffect.AOTType.TITAN, EffectType.BENEFICIAL, 0).addAttributesModifier(SharedMonsterAttributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE9901", 5.0D, AttributeModifier.Operation.ADDITION).addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD701", 0.5D, AttributeModifier.Operation.ADDITION));	
	
	public static final RegistryObject<Effect> BERSERKER = MOD_EFFECTS.register("berserker", () -> new ModEffect(ModEffect.AOTType.SPELL, EffectType.BENEFICIAL, 0).addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070631", 1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL).addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", AOTConfig.COMMON.berserker_punch_damage.get(), AttributeModifier.Operation.ADDITION));
	public static final RegistryObject<Effect> GRAVITY_FIELD = MOD_EFFECTS.register("gravity_field", () -> new ModEffect(ModEffect.AOTType.SPELL, EffectType.HARMFUL, 0));
	public static final RegistryObject<Effect> REVITALISE = MOD_EFFECTS.register("revitalise", () -> new ModRenderlessEffect(ModEffect.AOTType.SPELL, EffectType.BENEFICIAL, 0));

	public static final RegistryObject<Effect> HOLY_PLAINS = MOD_EFFECTS.register("holy_plains", () -> new ModEffect(ModEffect.AOTType.BIOME, EffectType.BENEFICIAL, 0).addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070632", 0.2D, AttributeModifier.Operation.MULTIPLY_TOTAL));
	public static final RegistryObject<Effect> HOLY_HILLS = MOD_EFFECTS.register("holy_hills", () -> new ModEffect(ModEffect.AOTType.BIOME, EffectType.BENEFICIAL, 0).addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070633", 0.2D, AttributeModifier.Operation.MULTIPLY_TOTAL));
	public static final RegistryObject<Effect> HOLY_MOUNTAINS = MOD_EFFECTS.register("holy_mountains", () -> new ModEffect(ModEffect.AOTType.BIOME, EffectType.BENEFICIAL, 0).addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070634", 0.2D, AttributeModifier.Operation.MULTIPLY_TOTAL));

}