package com.GenZVirus.AgeOfTitans.Capabilities.Instances;

import java.util.UUID;

import javax.annotation.Nullable;

import com.GenZVirus.AgeOfTitans.Capabilities.Interfaces.ISpell;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class SpellInstance implements ISpell {

	public int slot1, slot2, slot3, slot4;
	public UUID playerUuid;

	public SpellInstance() {
		this(0, 0, 0, 0, null);
	}

	public SpellInstance(int slot1ID,int slot2ID, int slot3ID, int slot4ID, UUID id) {
		slot1 = slot1ID;
		slot2 = slot2ID;
		slot3 = slot3ID;
		slot4 = slot4ID;
		playerUuid = id;
	}
	
	@Override
	public UUID getPlayerUuid() {
		return playerUuid;
	}

	@Override
	public int getSpellSlotbyID(int id) {
		if(id == 1) {
			return slot1;
		} else if(id == 2) {
			return slot2;
		} else if(id == 3) {
			return slot3;
		} else return slot4;
	}
	
	@Override
	public Spell getSpell(int slot) {
		if(slot == 1) {
			return Spell.SPELL_LIST.get(slot1);
		} else if(slot == 2) {
			return Spell.SPELL_LIST.get(slot2);
		} else if(slot == 3) {
			return Spell.SPELL_LIST.get(slot3);
		} else {
			return Spell.SPELL_LIST.get(slot4);
		}
	}

	
	@Override
	public void setPlayerUuid(UUID id) {
		playerUuid = id;
	}
	
	@Override
	public void setSpellSlotbyID(int spellID, int slotID) {
		if(slotID == 1) {
			slot1 = spellID;
		} else if(slotID == 2) {
			slot2 = spellID;
		} else if(slotID == 3) {
			slot3 = spellID;
		} else slot4 = spellID;
	}

	@Override
	public void setSpell(int slot1ID, int slot2ID, int slot3ID, int slot4ID) {
		slot1 = slot1ID;
		slot2 = slot2ID;
		slot3 = slot3ID;
		slot4 = slot4ID;
	}

	public static class Storage implements Capability.IStorage<ISpell> {
		@Nullable
		@Override
		public INBT writeNBT(Capability<ISpell> capability, ISpell instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putInt("SpellSlot1", instance.getSpellSlotbyID(1));
			nbt.putInt("SpellSlot2", instance.getSpellSlotbyID(2));
			nbt.putInt("SpellSlot3", instance.getSpellSlotbyID(3));
			nbt.putInt("SpellSlot4", instance.getSpellSlotbyID(4));
			return nbt;
		}

		@Override
		public void readNBT(Capability<ISpell> capability, ISpell instance, Direction side, INBT nbt) {
			CompoundNBT nbt1 = (CompoundNBT) nbt;
//			SpellInstance.slot1 = nbt1.getInt("SpellSlot1");
//			SpellInstance.slot2 = nbt1.getInt("SpellSlot2");
//			SpellInstance.slot3 = nbt1.getInt("SpellSlot3");
//			SpellInstance.slot4 = nbt1.getInt("SpellSlot4");
//			SpellInstance.playerUuid = NBTUtil.readUniqueId(nbt1);
			instance.setSpell(nbt1.getInt("SpellSlot1"), nbt1.getInt("SpellSlot2"), nbt1.getInt("SpellSlot3"), nbt1.getInt("SpellSlot4"));
			instance.setPlayerUuid(NBTUtil.readUniqueId(nbt1));
		}
	}



}
