package com.GenZVirus.AgeOfTitans.Common.Network;

import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SendPlayerSpellDetailsPacket {

	public int ID;
	public int cooldown;
	public double ratio;
	public double damage;
	
	public SendPlayerSpellDetailsPacket(int ID, int cooldown, double ratio, double damage) {
		this.ID = ID;
		this.cooldown = cooldown;
		this.ratio = ratio;
		this.damage = damage;
	}
	
	public static void encode(SendPlayerSpellDetailsPacket pkt, PacketBuffer buf) {
		buf.writeInt(pkt.ID);
		buf.writeInt(pkt.cooldown);
		buf.writeDouble(pkt.ratio);
		buf.writeDouble(pkt.damage);
	}
	
	public static SendPlayerSpellDetailsPacket decode(PacketBuffer buf) {
		return new SendPlayerSpellDetailsPacket(buf.readInt(), buf.readInt(), buf.readDouble(), buf.readDouble());
	}
	
	public static void handle(SendPlayerSpellDetailsPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		
		ctx.get().enqueueWork(() ->{
			if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
				Spell.SPELL_LIST.get(pkt.ID).cooldown = pkt.cooldown;
				Spell.SPELL_LIST.get(pkt.ID).ratio = pkt.ratio;
				Spell.SPELL_LIST.get(pkt.ID).damage = pkt.damage;
			}
		});
		
		ctx.get().setPacketHandled(true);
	}
	
}
