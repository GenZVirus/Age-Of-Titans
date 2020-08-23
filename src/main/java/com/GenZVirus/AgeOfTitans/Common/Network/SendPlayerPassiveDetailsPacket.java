package com.GenZVirus.AgeOfTitans.Common.Network;

import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.SpellSystem.PassiveAbility;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SendPlayerPassiveDetailsPacket {

	public int ID;
	public int cooldown;
	public int cost;
	public double ratio;
	public double baseAmount;
	
	public SendPlayerPassiveDetailsPacket(int ID, int cooldown, int cost, double ratio, double damage) {
		this.ID = ID;
		this.cooldown = cooldown;
		this.cost = cost;
		this.ratio = ratio;
		this.baseAmount = damage;
	}
	
	public static void encode(SendPlayerPassiveDetailsPacket pkt, PacketBuffer buf) {
		buf.writeInt(pkt.ID);
		buf.writeInt(pkt.cooldown);
		buf.writeInt(pkt.cost);
		buf.writeDouble(pkt.ratio);
		buf.writeDouble(pkt.baseAmount);
	}
	
	public static SendPlayerPassiveDetailsPacket decode(PacketBuffer buf) {
		return new SendPlayerPassiveDetailsPacket(buf.readInt(), buf.readInt(), buf.readInt(), buf.readDouble(), buf.readDouble());
	}
	
	public static void handle(SendPlayerPassiveDetailsPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		
		ctx.get().enqueueWork(() ->{
			if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
				PassiveAbility.getList().get(pkt.ID).setCooldown(pkt.cooldown);
				PassiveAbility.getList().get(pkt.ID).setCost(pkt.cost);
				PassiveAbility.getList().get(pkt.ID).setRatio(pkt.ratio);
				PassiveAbility.getList().get(pkt.ID).setBaseAmount(pkt.baseAmount);
			}
		});
		
		ctx.get().setPacketHandled(true);
	}
	
}
