package com.GenZVirus.AgeOfTitans.Network;

import java.util.UUID;
import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.Capabilities.SpellCapability;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class SpellPacket {

	public int slot1, slot2, slot3, slot4;
	private UUID playerUuid;

	public SpellPacket(int slot1ID, int slot2ID, int slot3ID, int slot4ID, UUID playerUuid) {
		this.slot1 = slot1ID;
		this.slot2 = slot2ID;
		this.slot3 = slot3ID;
		this.slot4 = slot4ID;
		this.playerUuid = playerUuid;
	}
	
	public static void encode(SpellPacket pkt, PacketBuffer buf) {
		buf.writeInt(pkt.slot1);
		buf.writeInt(pkt.slot2);
		buf.writeInt(pkt.slot3);
		buf.writeInt(pkt.slot4);
		buf.writeUniqueId(pkt.playerUuid);
	}

	public static SpellPacket decode(PacketBuffer buf) {
		return new SpellPacket(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt(), buf.readUniqueId());
	}

	public static void handle(SpellPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		
			ctx.get().enqueueWork(() -> {
				PlayerEntity player = Minecraft.getInstance().player.world.getPlayerByUuid(pkt.playerUuid);
				if(player != null) {
					player.getCapability(SpellCapability.SPELL_CAPABILITY).ifPresent(cap ->{
						cap.setSpell(pkt.slot1, pkt.slot2, pkt.slot3, pkt.slot4);
						cap.setPlayerUuid(player.getUniqueID());
					});
				}
			});
			ctx.get().setPacketHandled(true);
		
	}
}
