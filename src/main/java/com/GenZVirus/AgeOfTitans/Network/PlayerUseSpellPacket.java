package com.GenZVirus.AgeOfTitans.Network;

import java.util.UUID;
import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;
import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlayerUseSpellPacket {

	public int spellID;
	public UUID uuid;
	
	public PlayerUseSpellPacket(int spellID, UUID uuid) {
		this.spellID = spellID;
		this.uuid = uuid;
	}
	
	public static void encode(PlayerUseSpellPacket pkt, PacketBuffer buf) {
		buf.writeInt(pkt.spellID);
		buf.writeUniqueId(pkt.uuid);
	}
	
	public static PlayerUseSpellPacket decode(PacketBuffer buf) {
		return new PlayerUseSpellPacket(buf.readInt(), buf.readUniqueId());
	}
	
	public static void handle(PlayerUseSpellPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		
		ctx.get().enqueueWork(() ->{
			if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
				for (PlayerEntity player : ForgeEventBusSubscriber.players) {
					if (player.getUniqueID().toString().contentEquals(pkt.uuid.toString())) {
						Spell.SPELL_LIST.get(pkt.spellID).effect(player.world, player);
					}
				}
			}
		});
		
		ctx.get().setPacketHandled(true);
	}
	
}
