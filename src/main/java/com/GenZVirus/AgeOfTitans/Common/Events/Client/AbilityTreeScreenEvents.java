package com.GenZVirus.AgeOfTitans.Common.Events.Client;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Client.GUI.HUD.ModHUD;
import com.GenZVirus.AgeOfTitans.Client.GUI.SpellTree.ActiveSkillButton;
import com.GenZVirus.AgeOfTitans.Client.GUI.SpellTree.ModButton;
import com.GenZVirus.AgeOfTitans.Client.GUI.SpellTree.ModButtonSmall;
import com.GenZVirus.AgeOfTitans.Client.GUI.SpellTree.AbilityTreeScreen;
import com.GenZVirus.AgeOfTitans.Client.GUI.SpellTree.AbilitySlot;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
import com.GenZVirus.AgeOfTitans.Common.Network.PlayerUseSpellPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.ActiveAbility;
import com.GenZVirus.AgeOfTitans.SpellSystem.PlayerStats;
import com.GenZVirus.AgeOfTitans.Util.Helpers.KeyboardHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.player.ChatVisibility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent.MouseClickedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class AbilityTreeScreenEvents {

	private static int cooldown_SwordSlash = 0;
	private static int cooldown_ShieldBash = 0;
	private static int cooldown_Berserker = 0;
	private static int cooldown_Chain = 0;
	private static int cooldown_TimeBomb = 0;
	private static int cooldown_Revitalise = 0;
	
	@SubscribeEvent(receiveCanceled = true)
	public static void onHealthBar(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
			event.setCanceled(true);
			ModHUD.renderHealth();
		}
		
		if (event.getType() == RenderGameOverlayEvent.ElementType.FOOD) {
			event.setCanceled(true);
			ModHUD.renderFood();
		}
		
		if (event.getType() == RenderGameOverlayEvent.ElementType.ARMOR) {
			event.setCanceled(true);
			ModHUD.renderArmor();
		}
		
		if (event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
			event.setCanceled(true);
			ModHUD.renderExpBar();
			if(!ModHUD.locked) {
				ModHUD.renderRage();
			}
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
		if(cooldown_TimeBomb > 0) {
			cooldown_TimeBomb--;
		}
		if(cooldown_Revitalise > 0) {
			cooldown_Revitalise--;
		}
		PlayerEntity player = Minecraft.getInstance().player;
		if(player == null) return;
		if(ModHUD.selectedSpell.getId() == 1 && player.getHeldItemMainhand().getItem() instanceof SwordItem && cooldown_SwordSlash == 0 && PlayerStats.RAGE_POINTS >= ActiveAbility.getList().get(1).getCost()) {
			if(mc.mouseHelper.isLeftDown()) {
				cooldown_SwordSlash = ActiveAbility.getList().get(1).getCooldown();
				PacketHandlerCommon.INSTANCE.sendToServer(new PlayerUseSpellPacket(1, player.getUniqueID())); 
			}
		}
		
		if(ModHUD.selectedSpell.getId() == 2 && cooldown_ShieldBash == 0 && PlayerStats.RAGE_POINTS >= ActiveAbility.getList().get(2).getCost()) {
			if(player.getActiveHand() == Hand.OFF_HAND && player.getActiveItemStack().getItem() instanceof ShieldItem) {
				cooldown_ShieldBash = ActiveAbility.getList().get(2).getCooldown();
				PacketHandlerCommon.INSTANCE.sendToServer(new PlayerUseSpellPacket(2, player.getUniqueID()));
			}
		}
		if(ModHUD.selectedSpell.getId() == 3 && cooldown_Berserker == 0 && PlayerStats.RAGE_POINTS >= ActiveAbility.getList().get(3).getCost()) {
			if(KeyboardHelper.isFireAbilityKeyDown()) {
				cooldown_Berserker = ActiveAbility.getList().get(3).getCooldown();
				PacketHandlerCommon.INSTANCE.sendToServer(new PlayerUseSpellPacket(3, player.getUniqueID()));
			}
		}
		if(ModHUD.selectedSpell.getId() == 4 && cooldown_Chain == 0 && PlayerStats.RAGE_POINTS >= ActiveAbility.getList().get(4).getCost()) {
			if(KeyboardHelper.isFireAbilityKeyDown()) {
				cooldown_Chain = ActiveAbility.getList().get(4).getCooldown();
				PacketHandlerCommon.INSTANCE.sendToServer(new PlayerUseSpellPacket(4, player.getUniqueID()));
			}
		}
		
		if(ModHUD.selectedSpell.getId() == 5 && cooldown_TimeBomb == 0 && PlayerStats.RAGE_POINTS >= ActiveAbility.getList().get(5).getCost()) {
			if(KeyboardHelper.isFireAbilityKeyDown()) {
				cooldown_TimeBomb = ActiveAbility.getList().get(5).getCooldown();
				PacketHandlerCommon.INSTANCE.sendToServer(new PlayerUseSpellPacket(5, player.getUniqueID()));
			}
		}
		
		if(ModHUD.selectedSpell.getId() == 6 && cooldown_TimeBomb == 0 && PlayerStats.RAGE_POINTS >= ActiveAbility.getList().get(6).getCost()) {
			if(KeyboardHelper.isFireAbilityKeyDown()) {
				cooldown_TimeBomb = ActiveAbility.getList().get(6).getCooldown();
				PacketHandlerCommon.INSTANCE.sendToServer(new PlayerUseSpellPacket(6, player.getUniqueID()));
			}
		}
		
	}
	
	
	@SubscribeEvent
	public static void skillSection(MouseClickedEvent.Post event) {
		if (event.getGui() instanceof AbilityTreeScreen) {
			List<Widget> buttons = AbilityTreeScreen.SCREEN.getButtons();
			for (Widget button : buttons) {
				int widthIn = button.x;
				int heightIn = button.y;
				int width = button.getWidth();
				int height = button.getHeight();
				int x = (int) event.getMouseX();
				int y = (int) event.getMouseY();
				if (x >= widthIn && x < widthIn + width && y >= heightIn && y < heightIn + height) {
					if(button instanceof ModButtonSmall) {
						((ModButtonSmall) button).onPress();
					} else if (button instanceof ModButton) {
						((ModButton) button).onPress();
					} else if (button instanceof ActiveSkillButton) {
						((ActiveSkillButton) button).onPress();
					} else if (button instanceof AbilitySlot) {
						((AbilitySlot) button).onPress();
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
