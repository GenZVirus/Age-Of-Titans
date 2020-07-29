package com.GenZVirus.AgeOfTitans.Common.Network;

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
		
		INSTANCE.messageBuilder(ReadElementPacket.class, id++)
				.encoder(ReadElementPacket::encode)
				.decoder(ReadElementPacket::decode)
				.consumer(ReadElementPacket::handle)
				.add();
		
		INSTANCE.messageBuilder(EditElementPacket.class, id++)
				.encoder(EditElementPacket::encode)
				.decoder(EditElementPacket::decode)
				.consumer(EditElementPacket::handle)
				.add();
		
		INSTANCE.messageBuilder(berserkerBlockBreakerPacket.class, id++)
				.encoder(berserkerBlockBreakerPacket::encode)
				.decoder(berserkerBlockBreakerPacket::decode)
				.consumer(berserkerBlockBreakerPacket::handle)
				.add();
		
		INSTANCE.messageBuilder(SendPlayerSpellDetailsPacket.class, id++)
		   		.encoder(SendPlayerSpellDetailsPacket::encode)
		   		.decoder(SendPlayerSpellDetailsPacket::decode)
		   		.consumer(SendPlayerSpellDetailsPacket::handle)
		   		.add();
		
		INSTANCE.messageBuilder(SendPlayerRagePointsPacket.class, id++)
   				.encoder(SendPlayerRagePointsPacket::encode)
   				.decoder(SendPlayerRagePointsPacket::decode)
   				.consumer(SendPlayerRagePointsPacket::handle)
   				.add();
		
		INSTANCE.messageBuilder(sendTileEntityDataPacket.class, id++)
				.encoder(sendTileEntityDataPacket::encode)
				.decoder(sendTileEntityDataPacket::decode)
				.consumer(sendTileEntityDataPacket::handle)
				.add();
	}
}
