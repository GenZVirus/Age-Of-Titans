package com.GenZVirus.AgeOfTitans.LevelingSystem;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Config.AOTConfig;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
import com.GenZVirus.AgeOfTitans.Common.Network.ReadElementPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class Level {
	
	@SubscribeEvent
	public static void levelUp(AdvancementEvent event) {
		PlayerEntity player = event.getPlayer();
		if(!AOTConfig.COMMON.leveling_from_advancements.get()) return;
		XMLFileJava.checkFileAndMake(player.getUniqueID(), player.getName().getFormattedText());
		if(Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerExp")) == AOTConfig.COMMON.exp_level_up.get()) {
		XMLFileJava.editElement(player.getUniqueID(), "PlayerLevel", Integer.toString((Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerLevel")) + 1)));
		XMLFileJava.editElement(player.getUniqueID(), "PlayerPoints", Integer.toString((Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerPoints")) + 1)));
		XMLFileJava.editElement(player.getUniqueID(), "PlayerExp", "0");
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(player.getUniqueID(), "PlayerLevel", Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerLevel"))),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		
		} else {
			XMLFileJava.editElement(player.getUniqueID(), "PlayerExp", Integer.toString((Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerExp")) + AOTConfig.COMMON.exp_per_advancement.get())));
		}
	}

}
