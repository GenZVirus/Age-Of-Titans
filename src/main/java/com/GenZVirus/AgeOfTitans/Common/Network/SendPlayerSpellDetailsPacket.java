package com.GenZVirus.AgeOfTitans.Common.Network;

import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.SpellSystem.ActiveAbility;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SendPlayerSpellDetailsPacket {

	public int ID;
	public int cooldown;
	public int cost;
	public double ratio;
	public double baseAmount;
	
	public SendPlayerSpellDetailsPacket(int ID, int cooldown, int cost, double ratio, double damage) {
		this.ID = ID;
		this.cooldown = cooldown;
		this.cost = cost;
		this.ratio = ratio;
		this.baseAmount = damage;
	}
	
	public static void encode(SendPlayerSpellDetailsPacket pkt, PacketBuffer buf) {
		buf.writeInt(pkt.ID);
		buf.writeInt(pkt.cooldown);
		buf.writeInt(pkt.cost);
		buf.writeDouble(pkt.ratio);
		buf.writeDouble(pkt.baseAmount);
	}
	
	public static SendPlayerSpellDetailsPacket decode(PacketBuffer buf) {
		return new SendPlayerSpellDetailsPacket(buf.readInt(), buf.readInt(), buf.readInt(), buf.readDouble(), buf.readDouble());
	}
	
	public static void handle(SendPlayerSpellDetailsPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		
		ctx.get().enqueueWork(() ->{
			if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
				ActiveAbility.getList().get(pkt.ID).setCooldown(pkt.cooldown);
				ActiveAbility.getList().get(pkt.ID).setCost(pkt.cost);
				ActiveAbility.getList().get(pkt.ID).setRatio(pkt.ratio);
				ActiveAbility.getList().get(pkt.ID).setBaseAmount(pkt.baseAmount);
			}
		});
		
		ctx.get().setPacketHandled(true);
	}
	
}
