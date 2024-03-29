package com.GenZVirus.AgeOfTitans.Common.Events.Client;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
import com.GenZVirus.AgeOfTitans.Common.Network.SendPlayerHandPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent.ClickInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class DualWieldingEventsHandler {

	public static Boolean left = false;
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void swingLeftHand(ClickInputEvent event) {
		if(!event.isAttack()) return;
		if(!(Minecraft.getInstance().player.getHeldItemOffhand().getItem() instanceof SwordItem) || !(Minecraft.getInstance().player.getHeldItemMainhand().getItem() instanceof SwordItem)) {
			return;
		}
		PlayerEntity player = Minecraft.getInstance().player;
		if(left) {
			event.setSwingHand(false);
			player.swingArm(Hand.OFF_HAND);
			PacketHandlerCommon.INSTANCE.sendToServer(new SendPlayerHandPacket(player.getUniqueID(), 1));
			left = false;
		} else {
			PacketHandlerCommon.INSTANCE.sendToServer(new SendPlayerHandPacket(player.getUniqueID(), 0));
			left = true;
		}
	}	
}
