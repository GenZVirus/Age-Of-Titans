package com.GenZVirus.AgeOfTitans.Network;

import java.util.UUID;
import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.Capabilities.SpellCapability;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlayerSpellSlotPacket {

	public int spellID;
	public int spellSlot;
	private UUID playerUuid;

	public PlayerSpellSlotPacket(int id, int slot, UUID playerUuid) {
		this.spellID = id;
		this.spellSlot = slot;
		this.playerUuid = playerUuid;
	}
	
	public static void encode(PlayerSpellSlotPacket pkt, PacketBuffer buf) {
		buf.writeInt(pkt.spellID);
		buf.writeInt(pkt.spellSlot);
	}

	public static PlayerSpellSlotPacket decode(PacketBuffer buf) {
		return new PlayerSpellSlotPacket(buf.readInt(), buf.readInt(), buf.readUniqueId());
	}

	public static void handle(PlayerSpellSlotPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		
			ctx.get().enqueueWork(() -> {
				PlayerEntity player = Minecraft.getInstance().player.world.getPlayerByUuid(pkt.playerUuid);
				player.getCapability(SpellCapability.SPELL_CAPABILITY).ifPresent(cap ->{
					cap.setSpell(pkt.spellID, pkt.spellSlot);
					cap.setPlayer(player);
				});
			});
			ctx.get().setPacketHandled(true);
		
	}
}
