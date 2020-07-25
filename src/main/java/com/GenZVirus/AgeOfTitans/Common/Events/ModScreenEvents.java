package com.GenZVirus.AgeOfTitans.Common.Events;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Client.Container.ContainerScreenBasic;
import com.GenZVirus.AgeOfTitans.Client.GUI.HUD.ModHUD;
import com.GenZVirus.AgeOfTitans.Client.GUI.SpellTree.ModButton;
import com.GenZVirus.AgeOfTitans.Client.GUI.SpellTree.ModScreen;
import com.GenZVirus.AgeOfTitans.Client.GUI.SpellTree.ModSkillButton;
import com.GenZVirus.AgeOfTitans.Client.GUI.SpellTree.ModSkillSlot;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Common.Network.PlayerUseSpellPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.player.ChatVisibility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent.MouseClickedEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ModScreenEvents {

	private static int cooldown_SwordSlash = 0;
	private static int cooldown_ShieldBash = 0;
	private static int cooldown_Berserker = 0;
	private static int cooldown_Chain = 0;

	@SubscribeEvent
	public static void PlayerClosedContainer(ClientTickEvent event) {
		if(event.phase == Phase.START) {
			return;
		}
		
		Minecraft mc = Minecraft.getInstance();
		if (mc.currentScreen instanceof ContainerScreenBasic && mc.world != null) {
//			System.out.println("YES");
		}
		
	}
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void PlayerSpellUseEvent(ClientTickEvent event) {
		if(event.phase == Phase.START) {
			return;
		}
		Minecraft mc = Minecraft.getInstance();
		if ((mc.currentScreen != null && mc.gameSettings.chatVisibility != ChatVisibility.HIDDEN) || mc.world == null) {
			return;
		}
		
		if(cooldown_SwordSlash > 0) {
			cooldown_SwordSlash--;
		}
		if(cooldown_ShieldBash > 0) {
			cooldown_ShieldBash--;
		}
		if(cooldown_Berserker > 0) {
			cooldown_Berserker--;
		}
		if(cooldown_Chain > 0) {
			cooldown_Chain--;
		}
		PlayerEntity player = Minecraft.getInstance().player;
		if(player == null) return;
		if(ModHUD.selectedSpell.getId() == 1 && player.getHeldItemMainhand().getItem() instanceof SwordItem && cooldown_SwordSlash == 0 && Spell.ragePoints >= Spell.SPELL_LIST.get(1).cost) {
			if(mc.mouseHelper.isLeftDown()) {
				cooldown_SwordSlash = 20;
				PacketHandler.INSTANCE.sendToServer(new PlayerUseSpellPacket(1, player.getUniqueID())); 
			}
		}
		
		if(ModHUD.selectedSpell.getId() == 2 && cooldown_ShieldBash == 0 && Spell.ragePoints >= Spell.SPELL_LIST.get(2).cost) {
			if(player.getActiveHand() == Hand.OFF_HAND && player.getActiveItemStack().getItem() instanceof ShieldItem) {
				cooldown_ShieldBash = 20;
				PacketHandler.INSTANCE.sendToServer(new PlayerUseSpellPacket(2, player.getUniqueID()));
			}
		}
		if(ModHUD.selectedSpell.getId() == 3 && cooldown_Berserker == 0 && Spell.ragePoints >= Spell.SPELL_LIST.get(3).cost) {
			if(mc.mouseHelper.isRightDown()) {
				cooldown_Berserker = 20;
				PacketHandler.INSTANCE.sendToServer(new PlayerUseSpellPacket(3, player.getUniqueID()));
			}
		}
		if(ModHUD.selectedSpell.getId() == 4 && cooldown_Chain == 0 && Spell.ragePoints >= Spell.SPELL_LIST.get(4).cost) {
			if(mc.mouseHelper.isRightDown()) {
				cooldown_Chain = 20;
				PacketHandler.INSTANCE.sendToServer(new PlayerUseSpellPacket(4, player.getUniqueID()));
			}
		}
	}
	
	
	@SubscribeEvent
	public static void skillSection(MouseClickedEvent.Post event) {
		if (event.getGui() instanceof ModScreen) {
			List<Widget> buttons = ModScreen.SCREEN.getButtons();
			for (Widget button : buttons) {
				int widthIn = button.x;
				int heightIn = button.y;
				int width = button.getWidth();
				int height = button.getHeight();
				int x = (int) event.getMouseX();
				int y = (int) event.getMouseY();
				if (x >= widthIn && x < widthIn + width && y >= heightIn && y < heightIn + height) {
					if (button instanceof ModButton) {
						((ModButton) button).onPress();
					} else if (button instanceof ModSkillButton) {
						((ModSkillButton) button).onPress();
					} else if (button instanceof ModSkillSlot) {
						((ModSkillSlot) button).onPress();
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void HUD(TickEvent.RenderTickEvent event) {
		ModHUD.renderHUD();
	}

}
