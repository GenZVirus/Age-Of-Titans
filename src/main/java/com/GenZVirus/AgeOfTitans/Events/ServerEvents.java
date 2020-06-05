package com.GenZVirus.AgeOfTitans.Events;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Network.SpellPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.FileSystem;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class ServerEvents {

	
	@SubscribeEvent
	public static void serverEvent(LivingJumpEvent event) {
//		double offset = 1.0D;
//		double pitch = event.getEntity().getPitchYaw().x;
//		double yaw   = event.getEntity().getPitchYaw().y;
//		double pitchRadian = pitch * (Math.PI / 180); // X rotation
//		double yawRadian   = yaw   * (Math.PI / 180); // Y rotation 
//		double newPosX = offset * -Math.sin( yawRadian ) * Math.cos( pitchRadian );
//		double newPosY = offset * -Math.sin( pitchRadian );
//		double newPosZ = offset *  Math.cos( yawRadian ) * Math.cos( pitchRadian );
//		ShockwaveEntity witherskullentity = new ShockwaveEntity(event.getEntity().world, event.getEntityLiving(), newPosX, newPosY, newPosZ);
//	      double d0 = (double)MathHelper.sqrt(newPosX * newPosX + newPosY * newPosY + newPosZ * newPosZ);
//	      witherskullentity.accelerationX =  newPosX / d0 * 0.1D;
//	      witherskullentity.accelerationY =  newPosY / d0 * 0.1D;
//	      witherskullentity.accelerationZ =  newPosZ / d0 * 0.1D;
//	      witherskullentity.setRawPosition(event.getEntity().getPosX(), 1.6 + event.getEntity().getPosY(), event.getEntity().getPosZ());
//	      event.getEntity().world.addEntity(witherskullentity);
	}

	@SubscribeEvent
	public static void onPlayerLoggin(PlayerLoggedInEvent event) {
		List<Integer> list = FileSystem.readOrWrite(event.getPlayer().getUniqueID().toString(), 0, 0, 0, 0);
		PacketHandler.INSTANCE.sendTo(new SpellPacket(list.get(0), list.get(1), list.get(2), list.get(3), event.getPlayer().getUniqueID(), false), ((ServerPlayerEntity)event.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);

	}
	
}
