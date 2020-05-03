package com.GenZVirus.AgeOfTitans.Network;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModScreen;
import com.GenZVirus.AgeOfTitans.SpellSystem.FileSystem;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class SpellPacket {

	public int slot1, slot2, slot3, slot4;
	private UUID uuid;
	
	public SpellPacket(int slot1ID, int slot2ID, int slot3ID, int slot4ID, UUID uuid) {
		this.slot1 = slot1ID;
		this.slot2 = slot2ID;
		this.slot3 = slot3ID;
		this.slot4 = slot4ID;
		this.uuid = uuid;		
	}
	
	public static void encode(SpellPacket pkt, PacketBuffer buf) {
		buf.writeInt(pkt.slot1);
		buf.writeInt(pkt.slot2);
		buf.writeInt(pkt.slot3);
		buf.writeInt(pkt.slot4);
		buf.writeUniqueId(pkt.uuid);
	}

	public static SpellPacket decode(PacketBuffer buf) {
		return new SpellPacket(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt(), buf.readUniqueId());
	}

	public static void handle(SpellPacket pkt, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
					FileSystem.editFile(pkt.uuid.toString(), pkt.slot1, pkt.slot2, pkt.slot3, pkt.slot4);
					List<Integer> list = FileSystem.readFile(pkt.uuid.toString());
					for(PlayerEntity player : ForgeEventBusSubscriber.players) {
						if(player.getUniqueID().toString().contentEquals(pkt.uuid.toString())) {	
							PacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new SpellPacket(list.get(0), list.get(1), list.get(2), list.get(3), player.getUniqueID()));
						}
					}
				}else if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
					System.out.println(Spell.SPELL_LIST.get(pkt.slot1).getId());
					System.out.println(ModScreen.SCREEN.slot1.spell);
					ModScreen.SCREEN.slot1.spell = Spell.SPELL_LIST.get(pkt.slot1);
					System.out.println(ModScreen.SCREEN.slot1.spell);
					System.out.println(ModScreen.SCREEN.slot1.spell.getIcon().toString());
					System.out.println(ModScreen.SCREEN.slot1.spell.getId());
					ModScreen.SCREEN.slot2.spell = Spell.SPELL_LIST.get(pkt.slot2);
					ModScreen.SCREEN.slot3.spell = Spell.SPELL_LIST.get(pkt.slot3);
					ModScreen.SCREEN.slot4.spell = Spell.SPELL_LIST.get(pkt.slot4);
				}
				
				//DO STUFF
			});
			ctx.get().setPacketHandled(true);
		
	}
}
