package com.GenZVirus.AgeOfTitans.Network;

import java.util.UUID;
import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;
import com.GenZVirus.AgeOfTitans.Util.Helpers.HalfSphereShape;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class berserkerBlockBreakerPacket {

	public UUID uuid;
	
	public berserkerBlockBreakerPacket(UUID uuid) {
		this.uuid = uuid;
	}
	
	public static void encode(berserkerBlockBreakerPacket pkt, PacketBuffer buf) {
		buf.writeUniqueId(pkt.uuid);
	}
	
	public static berserkerBlockBreakerPacket decode(PacketBuffer buf) {
		return new berserkerBlockBreakerPacket(buf.readUniqueId());
	}
	
public static void handle(berserkerBlockBreakerPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		
		ctx.get().enqueueWork(() ->{
			if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
				for (PlayerEntity player : ForgeEventBusSubscriber.players) {
					if (player.getUniqueID().toString().contentEquals(pkt.uuid.toString())) {
						double offset = 3.0D;
						double pitch = player.getPitchYaw().x;
						double yaw   = player.getPitchYaw().y;
						Vec3d pos_offset = new Vec3d(player.getPosition()).add(0, 1.6D, 0);		
						double pitchRadian = pitch * (Math.PI / 180); // X rotation
						double yawRadian = yaw * (Math.PI / 180); // Y rotation 
						double newPosX = offset * -Math.sin( yawRadian ) * Math.cos( pitchRadian );
						double newPosY = offset * -Math.sin( pitchRadian );
						double newPosZ = offset *  Math.cos( yawRadian ) * Math.cos( pitchRadian );
						HalfSphereShape halfSphereShape = new HalfSphereShape(pos_offset.add(newPosX, newPosY, newPosZ), pos_offset);
						for(double k = 0.0D; k <= 2 * offset; k += 1.0D) {
							for(double j = 0.0D; j <= 2 * offset; j += 1.0D) {
								for(double i = 0.0D; i <= 2 * offset; i += 1.0D) {
									BlockPos pos = new BlockPos(new Vec3d(player.getPosX() - offset + i, player.getPosY() - offset + k, player.getPosZ() - offset + j));
									if(halfSphereShape.containsPoint(pos.getX(), pos.getY(), pos.getZ()) && player.world.getBlockState(pos).getBlockHardness(player.world, pos) != -1)
											player.world.destroyBlock(pos, true);														
								}
							}
						}
						break;
					}
				}
			}
		});
		
		ctx.get().setPacketHandled(true);
	}
	
}
