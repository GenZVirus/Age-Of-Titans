package com.GenZVirus.AgeOfTitans.Common.Events;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Init.BlockInit;
import com.GenZVirus.AgeOfTitans.Common.Init.ItemInit;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
import com.GenZVirus.AgeOfTitans.Common.Network.ReadElementPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;
import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class ForgeEvents {
	
	@SubscribeEvent
	public static void AOTLavaImmunity(ItemTossEvent event) {
		if (event.getEntityItem().getItem().getItem().equals(ItemInit.FRUIT_OF_THE_GODS.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.ORB_OF_DISLOCATION.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.ORB_OF_EDEN.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.ORB_OF_END.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.ORB_OF_NETHER.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.ORB_OF_STORAGE.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.ORB_OF_SUMMONING.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_AXE.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_PICKAXE.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_HOE.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_SHOVEL.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_SWORD.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_HELMET.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_CHESTPLATE.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_LEGGINGS.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_BOOTS.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_INGOT.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(BlockInit.TITANIUM_ORE.get().asItem())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(BlockInit.TITANIUM_BLOCK.get().asItem())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if (event.getEntityItem().getItem().getItem().equals(BlockInit.TITAN_LOCKER.get().asItem())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		}
	}

	@SubscribeEvent
	public static void AOTServerChat(ServerChatEvent event) {
		if (Integer.parseInt(XMLFileJava.readElement(event.getPlayer().getUniqueID(), "ApplesEaten")) > 0)
			event.setComponent(new StringTextComponent("\u00A7f" + "[" + "\u00A74" + XMLFileJava.readElement(event.getPlayer().getUniqueID(), "PlayerLevel") + "\u00A7r" + "\u00A7f" + "] " + "\u00A7b" + "\u00A7l" + event.getUsername() + "\u00A7f" + "\u00A7l" + ": " + "\u00A7r" + "\u00A7e" + "\u00A7o" + event.getMessage()));
		else
			event.setComponent(new StringTextComponent("\u00A7f" + "[" + XMLFileJava.readElement(event.getPlayer().getUniqueID(), "PlayerLevel") + "] " + event.getUsername() + "\u00A7l" + ": " + "\u00A7r" + event.getMessage()));
	}

	@SubscribeEvent
	public static void AOTOnApplesEaten(LivingEntityUseItemEvent.Finish event) {
		if (event.getEntityLiving().world.isRemote)
			return;
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;
		if (event.getItem().getItem().equals(ItemInit.FRUIT_OF_THE_GODS.get())) {
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			player.setAbsorptionAmount(player.getAbsorptionAmount() + 20);
			player.setHealth(player.getMaxHealth());
			for (PlayerEntity playerMSG : ForgeEventBusSubscriber.players) {
				playerMSG.sendMessage(new TranslationTextComponent(player.getName().getFormattedText() + " has eaten the Fruit of the Gods"));
			}
			XMLFileJava.editElement(player.getUniqueID(), "ApplesEaten", Integer.toString((Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "ApplesEaten")) + 1)));
			PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(player.getUniqueID(), "ApplesEaten", Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "ApplesEaten"))), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
			XMLFileJava.editElement(player.getUniqueID(), "PlayerLevel", Integer.toString((Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerLevel")) + 1)));
			PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(player.getUniqueID(), "PlayerLevels", Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerLevel"))), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
			XMLFileJava.editElement(player.getUniqueID(), "PlayerPoints", Integer.toString((Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerPoints")) + 1)));
			PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(player.getUniqueID(), "PlayerPoints", Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerPoints"))), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}
}
