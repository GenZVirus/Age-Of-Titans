package com.GenZVirus.AgeOfTitans.Capabilities.Interfaces;

import java.util.UUID;

import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;

public interface ISpell {


	int getSpellSlotbyID(int id);
	Spell getSpell(int slot);
	UUID getPlayerUuid();
	
	void setPlayerUuid(UUID id);
	void setSpellSlotbyID(int spellID, int slotID);
	void setSpell(int slot1ID, int slot2ID, int slot3ID, int slot4ID);
	
}
