package com.GenZVirus.AgeOfTitans.Capabilities.Interfaces;

import java.util.UUID;

import net.minecraft.entity.player.PlayerEntity;

public interface ISpell {

	int getSpellID();
	int getSpellSlot();
	
	void setSpellID(int id);
	void setSpellSlot(int slot);
	void setPlayer(PlayerEntity player);
	void setSpell(int id, int slot);
	UUID getPlayerUuid();
	
}
