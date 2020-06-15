package com.GenZVirus.AgeOfTitans.Init;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Effects.ModEffect;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInit {

	public static final DeferredRegister<Effect> MOD_EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, AgeOfTitans.MOD_ID);
	
	public static final RegistryObject<Effect> BERSERKER = MOD_EFFECTS.register("berserker", () -> new ModEffect(EffectType.BENEFICIAL, 0).addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", 2.0D, AttributeModifier.Operation.MULTIPLY_TOTAL)
																																		  .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 10.0D, AttributeModifier.Operation.ADDITION));
	
}