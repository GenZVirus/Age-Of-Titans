package com.GenZVirus.AgeOfTitans.Common.Commands;

import com.GenZVirus.AgeOfTitans.ModCompatibility.VampirismComp;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.ModList;

public class FeedCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("feed").requires(context -> {
			return context.hasPermissionLevel(3);
		}).executes(source -> {
			return feed(source.getSource(), source.getSource().asPlayer());
		}).then(Commands.argument("target", EntityArgument.player()).executes(source -> {
			return feed(source.getSource(), EntityArgument.getPlayer(source, "target"));
		})));
	}

	private static int feed(CommandSource source, PlayerEntity player) {
		if (ModList.get().isLoaded("vampirism")) {
			VampirismComp.vampireFeedCommand(player);
		} else {
			player.getFoodStats().setFoodLevel(20);
			player.getFoodStats().setFoodSaturationLevel(20);
		}
		return 1;
	}
}
