package com.GenZVirus.AgeOfTitans.Common.Effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class ModEffect extends Effect {

	private AOTType type;
	
	public ModEffect(AOTType type,EffectType typeIn, int liquidColorIn) {
		super(typeIn, liquidColorIn);
		this.type = type;
	}

	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
	}
	
	public AOTType getType() {
		return type;
	}
	
	public enum AOTType{
		TITAN,
		BIOME,
		SPELL
	}
	
}
