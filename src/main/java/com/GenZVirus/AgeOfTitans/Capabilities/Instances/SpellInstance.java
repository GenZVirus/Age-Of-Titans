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

	public static int slot1, slot2, slot3, slot4;
	public static UUID playerUuid;

	public SpellInstance() {
		this(1, 0, 0, 0, null);
	}

	public SpellInstance(int slot1ID,int slot2ID, int slot3ID, int slot4ID, UUID id) {
		SpellInstance.slot1 = slot1ID;
		SpellInstance.slot2 = slot2ID;
		SpellInstance.slot3 = slot3ID;
		SpellInstance.slot4 = slot4ID;
		SpellInstance.playerUuid = id;
	}
	
	@Override
	public UUID getPlayerUuid() {
		return SpellInstance.playerUuid;
	}

	@Override
	public int getSpellSlotbyID(int id) {
		if(id == 1) {
			return SpellInstance.slot1;
		} else if(id == 2) {
			return SpellInstance.slot2;
		} else if(id == 3) {
			return SpellInstance.slot3;
		} else return SpellInstance.slot4;
	}
	
	@Override
	public Spell getSpell(int slot) {
		if(slot == 1) {
			return Spell.SPELL_LIST.get(SpellInstance.slot1);
		} else if(slot == 2) {
			return Spell.SPELL_LIST.get(SpellInstance.slot2);
		} else if(slot == 3) {
			return Spell.SPELL_LIST.get(SpellInstance.slot3);
		} else {
			return Spell.SPELL_LIST.get(SpellInstance.slot4);
		}
	}

	
	@Override
	public void setPlayerUuid(UUID id) {
		SpellInstance.playerUuid = id;
	}
	
	@Override
	public void setSpellSlotbyID(int spellID, int slotID) {
		if(slotID == 1) {
			SpellInstance.slot1 = spellID;
		} else if(slotID == 2) {
			SpellInstance.slot2 = spellID;
		} else if(slotID == 3) {
			SpellInstance.slot3 = spellID;
		} else SpellInstance.slot4 = spellID;
	}

	@Override
	public void setSpell(int slot1ID, int slot2ID, int slot3ID, int slot4ID) {
		SpellInstance.slot1 = slot1ID;
		SpellInstance.slot2 = slot2ID;
		SpellInstance.slot3 = slot3ID;
		SpellInstance.slot4 = slot4ID;
	}

	public static class Storage implements Capability.IStorage<ISpell> {
		@Nullable
		@Override
		public INBT writeNBT(Capability<ISpell> capability, ISpell instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			
			System.out.println("Storage.WriteNBT:");
			System.out.println("instance.getSpellSlotbyID(1): " + SpellInstance.slot1);
			System.out.println("instance.getSpellSlotbyID(2): " + SpellInstance.slot2);
			System.out.println("instance.getSpellSlotbyID(3): " + SpellInstance.slot3);
			System.out.println("instance.getSpellSlotbyID(4): " + SpellInstance.slot4);
			nbt.putInt("SpellSlot1", SpellInstance.slot1);
			nbt.putInt("SpellSlot2", SpellInstance.slot2);
			nbt.putInt("SpellSlot3", SpellInstance.slot3);
			nbt.putInt("SpellSlot4", SpellInstance.slot4);
			
			return nbt;
		}

		@Override
		public void readNBT(Capability<ISpell> capability, ISpell instance, Direction side, INBT nbt) {
			CompoundNBT nbt1 = (CompoundNBT) nbt;
			SpellInstance.slot1 = nbt1.getInt("SpellSlot1");
			SpellInstance.slot2 = nbt1.getInt("SpellSlot2");
			SpellInstance.slot3 = nbt1.getInt("SpellSlot3");
			SpellInstance.slot4 = nbt1.getInt("SpellSlot4");
			SpellInstance.playerUuid = NBTUtil.readUniqueId(nbt1);
//			System.out.println("Storage.ReadNBT:");
//			System.out.println("Get from NBT:");
//			System.out.println("nbt1.getInt(\"SpellSlot1\"): " + nbt1.getInt("SpellSlot1"));
//			System.out.println("nbt1.getInt(\"SpellSlot2\"): " + nbt1.getInt("SpellSlot2"));
//			System.out.println("nbt1.getInt(\"SpellSlot3\"): " + nbt1.getInt("SpellSlot3"));
//			System.out.println("nbt1.getInt(\"SpellSlot4\"): " + nbt1.getInt("SpellSlot4"));
//			instance.setSpell(nbt1.getInt("SpellSlot1"), nbt1.getInt("SpellSlot2"), nbt1.getInt("SpellSlot3"), nbt1.getInt("SpellSlot4"));
//			System.out.println("After setting the instance:");
//			System.out.println("instance.getSpellSlotbyID(1): " + instance.getSpellSlotbyID(1));
//			System.out.println("instance.getSpellSlotbyID(2): " + instance.getSpellSlotbyID(2));
//			System.out.println("instance.getSpellSlotbyID(3): " + instance.getSpellSlotbyID(3));
//			System.out.println("instance.getSpellSlotbyID(4): " + instance.getSpellSlotbyID(4));
//			instance.setPlayerUuid(NBTUtil.readUniqueId(nbt1));
//			System.out.println("PlayerUuid:" + NBTUtil.readUniqueId(nbt1));
		}
	}



}
