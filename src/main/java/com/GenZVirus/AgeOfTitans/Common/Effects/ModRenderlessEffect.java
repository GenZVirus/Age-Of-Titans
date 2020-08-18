package com.GenZVirus.AgeOfTitans.Common.Effects;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

public class ModRenderlessEffect extends ModEffect{
	
	public ModRenderlessEffect(AOTType type, EffectType typeIn, int liquidColorIn) {
		super(type, typeIn, liquidColorIn);
	}

	@Override
	public boolean shouldRender(EffectInstance effect) {
		return false;
	}
	
	@Override
	public boolean shouldRenderHUD(EffectInstance effect) {
		return false;
	}
	
	@Override
	public boolean shouldRenderInvText(EffectInstance effect) {
		return false;
	}
	
}
