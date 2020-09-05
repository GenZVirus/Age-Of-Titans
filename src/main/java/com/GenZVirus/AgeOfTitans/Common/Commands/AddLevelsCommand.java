package com.GenZVirus.AgeOfTitans.Common.Commands;

import java.util.UUID;

import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
import com.GenZVirus.AgeOfTitans.Common.Network.ReadElementPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkDirection;

public class AddLevelsCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("addlevels").requires(context -> {
			return context.hasPermissionLevel(3);
		}).then(Commands.argument("level", IntegerArgumentType.integer(0, Integer.MAX_VALUE - 1)).executes(source -> {
			return addLevels(source.getSource(), source.getSource().asPlayer(), IntegerArgumentType.getInteger(source, "level"));
		}).then(Commands.argument("target", EntityArgument.player()).executes(source ->{
			return addLevels(source.getSource(), EntityArgument.getPlayer(source, "target"), IntegerArgumentType.getInteger(source, "level"));
		}))));
	}
	
	private static int addLevels(CommandSource source, PlayerEntity player, int level) {
		if(player.world.isRemote) return 1;
		UUID uuid = player.getUniqueID();
		XMLFileJava.checkFileAndMake(uuid, player.getName().getFormattedText());
		XMLFileJava.editElement(uuid, "PlayerLevel", Integer.toString(Integer.parseInt(XMLFileJava.readElement(uuid, "PlayerLevel")) + level));
		XMLFileJava.editElement(uuid, "ApplesEaten", Integer.toString(Integer.parseInt(XMLFileJava.readElement(uuid, "ApplesEaten")) + level));
		XMLFileJava.editElement(uuid, "PlayerPoints", Integer.toString(Integer.parseInt(XMLFileJava.readElement(uuid, "PlayerPoints")) + level));
		
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "PlayerPoints", Integer.parseInt(XMLFileJava.readElement(uuid, "PlayerPoints"))),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "ApplesEaten", Integer.parseInt(XMLFileJava.readElement(uuid, "ApplesEaten"))),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "PlayerLevel", Integer.parseInt(XMLFileJava.readElement(uuid, "PlayerLevel"))),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		
		source.sendFeedback(new TranslationTextComponent("commands.addlevels", level, player.getDisplayName().getFormattedText()), true);
		return 1;
	}
	
}
