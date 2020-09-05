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

public class SetBalanceCommand {
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("setbalance").requires(context -> {
			return context.hasPermissionLevel(3);
		}).then(Commands.argument("souls", IntegerArgumentType.integer(0, Integer.MAX_VALUE - 1)).executes(source -> {
			return setLevel(source.getSource(), source.getSource().asPlayer(), IntegerArgumentType.getInteger(source, "souls"));
		}).then(Commands.argument("target", EntityArgument.player()).executes(source ->{
			return setLevel(source.getSource(), EntityArgument.getPlayer(source, "target"), IntegerArgumentType.getInteger(source, "souls"));
		}))));
	}
	
	private static int setLevel(CommandSource source, PlayerEntity player, int balance) {
		if(player.world.isRemote) return 1;
		UUID uuid = player.getUniqueID();
		XMLFileJava.checkFileAndMake(uuid, player.getName().getFormattedText());
		XMLFileJava.editElement(uuid, "Balance", Integer.toString(balance));
		
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "Balance", Integer.parseInt(XMLFileJava.readElement(uuid, "Balance"))),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		
		source.sendFeedback(new TranslationTextComponent("commands.setbalance", player.getDisplayName().getFormattedText(), balance), true);
		return 1;
	}
	
}