package com.GenZVirus.AgeOfTitans.Capabilities.Instances;

import java.util.UUID;

import javax.annotation.Nullable;

import com.GenZVirus.AgeOfTitans.Capabilities.Interfaces.ISpell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class SpellInstance implements ISpell {

	private int id, slot;
	private PlayerEntity player;
	private UUID playerUuid;

	public SpellInstance() {
		this.id = 0;
		this.slot = 0;
		this.player = null;
		this.playerUuid = null;
	}

	public SpellInstance(PlayerEntity player, int id, int slot) {
		this.id = id;
		this.slot = slot;
		this.player = player;
		this.playerUuid = this.player.getUniqueID();
	}

	@Override
	public int getSpellID() {
		return this.id;
	}

	@Override
	public int getSpellSlot() {
		return this.slot;
	}

	@Override
	public void setSpellID(int id) {
		this.id = id;
	}

	@Override
	public void setSpellSlot(int slot) {
		this.slot = slot;
	}

	@Override
	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

	@Override
	public void setSpell(int id, int slot) {
		this.id = id;
		this.slot = slot;
	}

	public static class Storage implements Capability.IStorage<ISpell> {
		@Nullable
		@Override
		public INBT writeNBT(Capability<ISpell> capability, ISpell instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putInt("SpellID", instance.getSpellID());
			nbt.putInt("SpellSlot", instance.getSpellSlot());
			return nbt;
		}

		@Override
		public void readNBT(Capability<ISpell> capability, ISpell instance, Direction side, INBT nbt) {
			CompoundNBT nbt1 = (CompoundNBT) nbt;
			instance.setSpell(nbt1.getInt("SpellID"), nbt1.getInt("SpellSlot"));
		}
	}

	@Override
	public UUID getPlayerUuid() {
		return this.playerUuid;
	}

}
