package com.GenZVirus.AgeOfTitans.Common.Commands;

import java.util.UUID;

import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
import com.GenZVirus.AgeOfTitans.Common.Network.ReadElementPacket;
import com.GenZVirus.AgeOfTitans.Common.Network.SpellPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
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

public class SetLevelCommand {
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("setlevel").requires(context -> {
			return context.hasPermissionLevel(3);
		}).then(Commands.argument("level", IntegerArgumentType.integer(0, Integer.MAX_VALUE - 1)).executes(source -> {
			return setLevel(source.getSource(), source.getSource().asPlayer(), IntegerArgumentType.getInteger(source, "level"));
		}).then(Commands.argument("target", EntityArgument.player()).executes(source ->{
			return setLevel(source.getSource(), EntityArgument.getPlayer(source, "target"), IntegerArgumentType.getInteger(source, "level"));
		}))));
	}
	
	private static int setLevel(CommandSource source, PlayerEntity player, int level) {
		if(player.world.isRemote) return 1;
		UUID uuid = player.getUniqueID();
		XMLFileJava.checkFileAndMake(uuid, player.getName().getFormattedText());
		XMLFileJava.editElement(uuid, "PlayerLevel", Integer.toString(level));
		XMLFileJava.editElement(uuid, "ApplesEaten", Integer.toString(level));
		XMLFileJava.editElement(uuid, "PlayerPoints", Integer.toString(level));
		XMLFileJava.editElement(uuid, "PlayerExp", "0");
		
		XMLFileJava.editElement(uuid, "Slot1_Spell_ID", "0");
		XMLFileJava.editElement(uuid, "Slot2_Spell_ID", "0");
		XMLFileJava.editElement(uuid, "Slot3_Spell_ID", "0");
		XMLFileJava.editElement(uuid, "Slot4_Spell_ID", "0");
		
		PacketHandlerCommon.INSTANCE.sendTo(new SpellPacket(Integer.parseInt(XMLFileJava.readElement(uuid, "Slot1_Spell_ID")), 
															Integer.parseInt(XMLFileJava.readElement(uuid, "Slot2_Spell_ID")), 
															Integer.parseInt(XMLFileJava.readElement(uuid, "Slot3_Spell_ID")), 
															Integer.parseInt(XMLFileJava.readElement(uuid, "Slot4_Spell_ID")), 
															uuid, false), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "PlayerPoints", Integer.parseInt(XMLFileJava.readElement(uuid, "PlayerPoints"))),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "ApplesEaten", Integer.parseInt(XMLFileJava.readElement(uuid, "ApplesEaten"))),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "PlayerLevel", Integer.parseInt(XMLFileJava.readElement(uuid, "PlayerLevel"))),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);

		for(int i = 1; i < Spell.SPELL_LIST.size(); i++) {
			String element = "Spell" + "_Level" + i;
			XMLFileJava.editElement(uuid, element, "0");
			PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, element, Integer.parseInt(XMLFileJava.readElement(uuid, element))),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
		
		source.sendFeedback(new TranslationTextComponent("commands.setlevel", player.getDisplayName().getFormattedText(), level), true);
		return 1;
	}
	
}