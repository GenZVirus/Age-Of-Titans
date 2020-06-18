package com.GenZVirus.AgeOfTitans.Network;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(AgeOfTitans.MOD_ID, "spell"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals);

	public static void init() {
		int id = 0;

		INSTANCE.messageBuilder(SpellPacket.class, id++)
				.encoder(SpellPacket::encode)
				.decoder(SpellPacket::decode)
				.consumer(SpellPacket::handle)
				.add();
		
		INSTANCE.messageBuilder(PlayerUseSpellPacket.class, id++)
				.encoder(PlayerUseSpellPacket::encode)
				.decoder(PlayerUseSpellPacket::decode)
				.consumer(PlayerUseSpellPacket::handle)
				.add();
		
		INSTANCE.messageBuilder(SyncPlayerMotionPacket.class, id++)
				.encoder(SyncPlayerMotionPacket::encode)
				.decoder(SyncPlayerMotionPacket::decode)
				.consumer(SyncPlayerMotionPacket::handle)
				.add();
	}
}
