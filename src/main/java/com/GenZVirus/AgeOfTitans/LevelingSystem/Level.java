package com.GenZVirus.AgeOfTitans.LevelingSystem;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class Level {
	
	@SubscribeEvent
	public static void levelUp(AdvancementEvent event) {
		PlayerEntity player = event.getPlayer();

		if(Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerExp")) == 100) {
		XMLFileJava.editElement(player.getUniqueID(), "PlayerLevel", Integer.toString((Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerLevel")) + 1)));
		XMLFileJava.editElement(player.getUniqueID(), "PlayerExp", "0");
		} else {
			XMLFileJava.editElement(player.getUniqueID(), "PlayerExp", Integer.toString((Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerExp")) + 10)));
		}
	}

}
