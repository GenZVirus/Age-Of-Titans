package com.GenZVirus.AgeOfTitans.Common.Commands;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

public class HealCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("heal").requires(context -> {
			return context.hasPermissionLevel(3);
		}).executes(source ->{
			return heal(source.getSource(), source.getSource().asPlayer());
		}).then(Commands.argument("target", EntityArgument.player()).executes(source ->{
			return heal(source.getSource(), EntityArgument.getPlayer(source, "target"));
		})));
	}

	private static int heal(CommandSource source, PlayerEntity player) {
		player.setHealth(player.getMaxHealth());
		return 1;
	}
}
