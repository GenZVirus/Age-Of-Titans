package com.GenZVirus.AgeOfTitans.Capabilities;

import javax.annotation.Nonnull;

import com.GenZVirus.AgeOfTitans.Capabilities.Interfaces.ISpell;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class SpellCapability implements ICapabilitySerializable<CompoundNBT> {

	@CapabilityInject(ISpell.class)
	public static Capability<ISpell> SPELL_CAPABILITY = null;
	
	private LazyOptional<ISpell> instance = LazyOptional.of(SPELL_CAPABILITY::getDefaultInstance);

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nonnull Direction side) {
		return SPELL_CAPABILITY.orEmpty(cap, instance);
	}

	@Nonnull
	@Override
	public CompoundNBT serializeNBT() {
		System.out.println("serilizeNBT was called");
		return (CompoundNBT) SPELL_CAPABILITY.getStorage().writeNBT(SPELL_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null); 

	}

	@Nonnull
	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		System.out.println("deserilizeNBT was called");
		SPELL_CAPABILITY.getStorage().readNBT(SPELL_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null, nbt);
	}

}
