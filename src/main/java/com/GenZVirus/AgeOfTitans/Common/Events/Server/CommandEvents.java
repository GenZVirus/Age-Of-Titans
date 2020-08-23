package com.GenZVirus.AgeOfTitans.Common.Events.Server;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Commands.AddLevelsCommand;
import com.GenZVirus.AgeOfTitans.Common.Commands.ResetLevelCommand;
import com.GenZVirus.AgeOfTitans.Common.Commands.SetLevelCommand;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID)
public class CommandEvents {

	@SubscribeEvent
	public static void onServerStarting(final FMLServerStartingEvent event) {
		CommandDispatcher<CommandSource> dispatcher = event.getCommandDispatcher();
		
		AddLevelsCommand.register(dispatcher);
		ResetLevelCommand.register(dispatcher);
		SetLevelCommand.register(dispatcher);
		
	}
	
}
