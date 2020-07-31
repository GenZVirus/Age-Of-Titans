package com.GenZVirus.AgeOfTitans.Client.Container;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.GenZVirus.AgeOfTitans.Common.Init.ModContainerTypes;
import com.GenZVirus.AgeOfTitans.Common.TileEntity.TileEntityInventoryBasic;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;

/**
 * User: brandon3055 Date: 06/01/2015
 *
 * The container is used to link the client side gui to the server side
 * inventory and it is where you add the slots to your gui. It can also be used
 * to sync server side data with the client but that will be covered in a later
 * tutorial
 *
 * Vanilla Containers use IInventory to communicate with the parent TileEntity:
 * markDirty(); isUsableByPlayer(); openInventory(); closeInventory(); For this
 * example, we only need markDirty() and isUsableByPlayer(). I've chosen to
 * implement these as callback functions (lambdas) because I think it's clearer
 * than providing an Optional<TileEntityBasic>, but it could easily be done that
 * way as well, because the functions are only needed on the server side, when
 * the TileEntity is available. On the client side, there is no TileEntity
 * available.
 */
public class ContainerBasic extends Container {
	public static ContainerBasic createContainerServerSide(int windowID, PlayerInventory playerInventory,
			ChestContents chestContents) {
		return new ContainerBasic(windowID, playerInventory, chestContents);
	}

	public static ContainerBasic createContainerClientSide(int windowID, PlayerInventory playerInventory,
			net.minecraft.network.PacketBuffer extraData) {
		// don't need extraData for this example; if you want you can use it to provide
		// extra information from the server, that you can use
		// when creating the client container
		// eg String detailedDescription = extraData.readString(128);
		ChestContents chestContents = ChestContents
				.createForClientSideContainer(TileEntityInventoryBasic.NUMBER_OF_SLOTS);

		// on the client side there is no parent TileEntity to communicate with, so we:
		// 1) use a dummy inventory
		// 2) use "do nothing" lambda functions for canPlayerAccessInventory and
		// markDirty
		return new ContainerBasic(windowID, playerInventory, chestContents);
	}

	// must assign a slot number to each of the slots used by the GUI.
	// For this container, we can see both the tile inventory's slots as well as the
	// player inventory slots and the hotbar.
	// Each time we add a Slot to the container, it automatically increases the
	// slotIndex, which means
	// 0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 -
	// 8)
	// 9 - 35 = player inventory slots (which map to the InventoryPlayer slot
	// numbers 9 - 35)
	// 36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 -
	// 8)

	private static final int HOTBAR_SLOT_COUNT = 9;
	private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
	private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
	private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
	private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

	private static final int VANILLA_FIRST_SLOT_INDEX = 0;
	private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
	private static final int TE_INVENTORY_SLOT_COUNT = TileEntityInventoryBasic.NUMBER_OF_SLOTS; // must match
																									// TileEntityInventoryBasic.NUMBER_OF_SLOTS

	public static final int TILE_INVENTORY_YPOS = 18; // the ContainerScreenBasic needs to know these so it can tell
														// where to draw the Titles
	public static final int PLAYER_INVENTORY_YPOS = 122;

	/**
	 * Creates a container suitable for server side or client side
	 * 
	 * @param windowID        ID of the container
	 * @param playerInventory the inventory of the player
	 * @param chestContents   the inventory stored in the chest
	 */
	private ContainerBasic(int windowID, PlayerInventory playerInventory, ChestContents chestContents) {
		super(ModContainerTypes.BLACK_HOLE.get(), windowID);
		if (ModContainerTypes.BLACK_HOLE.get() == null)
			throw new IllegalStateException(
					"Must initialise containerBasicContainerType before constructing a ContainerBasic!");

		PlayerInvWrapper playerInventoryForge = new PlayerInvWrapper(playerInventory); // wrap the IInventory in a Forge
																						// IItemHandler.
		// Not actually necessary - can use Slot(playerInventory) instead of
		// SlotItemHandler(playerInventoryForge)
		this.chestContents = chestContents;

		final int SLOT_X_SPACING = 18;
		final int SLOT_Y_SPACING = 18;
		final int HOTBAR_XPOS = 9;
		final int HOTBAR_YPOS = 180;
		// Add the players hotbar to the gui - the [xpos, ypos] location of each item
		for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
			int slotNumber = x;
			addSlot(new SlotItemHandler(playerInventoryForge, slotNumber, HOTBAR_XPOS + SLOT_X_SPACING * x,
					HOTBAR_YPOS));
		}

		final int PLAYER_INVENTORY_XPOS = 9;
		// Add the rest of the player's inventory to the gui
		for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
			for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
				int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
				int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
				int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
				addSlot(new SlotItemHandler(playerInventoryForge, slotNumber, xpos, ypos));
			}
		}

//		if (TE_INVENTORY_SLOT_COUNT != chestContents.getSizeInventory()) {
//			LOGGER.warn("Mismatched slot count in ContainerBasic(" + TE_INVENTORY_SLOT_COUNT + ") and TileInventory ("
//					+ chestContents.getSizeInventory() + ")");
//		}
		final int TILE_INVENTORY_XPOS = 9;
		int TILE_INVENTORY_YPOS_OFFSET = 0;
		int tile_inventory_xpos_offset = 0;
		// Add the tile inventory container to the gui
		for (int x = 0; x < TE_INVENTORY_SLOT_COUNT; x++) {
			int slotNumber = x;
			addSlot(new Slot(chestContents, slotNumber,
					TILE_INVENTORY_XPOS + SLOT_X_SPACING * tile_inventory_xpos_offset,
					TILE_INVENTORY_YPOS + TILE_INVENTORY_YPOS_OFFSET));
			if ((x + 1) % 9 == 0 && x != 0) {
				TILE_INVENTORY_YPOS_OFFSET += SLOT_Y_SPACING;
				tile_inventory_xpos_offset = 0;
			} else {
				tile_inventory_xpos_offset++;
			}
		}
	}

	// Vanilla calls this method every tick to make sure the player is still able to
	// access the inventory, and if not closes the gui
	// Called on the SERVER side only
	@Override
	public boolean canInteractWith(PlayerEntity playerEntity) {
		// This is typically a check that the player is within 8 blocks of the
		// container.
		// Some containers perform it using just the block placement:
		// return isWithinUsableDistance(this.iWorldPosCallable, playerIn,
		// Blocks.MYBLOCK); eg see BeaconContainer
		// where iWorldPosCallable is a lambda that retrieves the blockstate at a
		// particular world blockpos
		// for other containers, it defers to the IInventory provided to the Container
		// (i.e. the TileEntity) which does the same
		// calculation
		// return this.furnaceInventory.isUsableByPlayer(playerEntity);
		// Sometimes it perform an additional check (eg for EnderChests - the player
		// owns the chest)

		return true;
	}

	// This is where you specify what happens when a player shift clicks a slot in
	// the gui
	// (when you shift click a slot in the TileEntity Inventory, it moves it to the
	// first available position in the hotbar and/or
	// player inventory. When you you shift-click a hotbar or player inventory item,
	// it moves it to the first available
	// position in the TileEntity inventory)
	// At the very least you must override this and return ItemStack.EMPTY or the
	// game will crash when the player shift clicks a slot
	// returns ItemStack.EMPTY if the source slot is empty, or if none of the the
	// source slot item could be moved
	// otherwise, returns a copy of the source stack
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerEntity, int sourceSlotIndex) {
		Slot sourceSlot = inventorySlots.get(sourceSlotIndex);
		if (sourceSlot == null || !sourceSlot.getHasStack())
			return ItemStack.EMPTY; // EMPTY_ITEM
		ItemStack sourceStack = sourceSlot.getStack();
		ItemStack copyOfSourceStack = sourceStack.copy();

		// Check if the slot clicked is one of the vanilla container slots
		if (sourceSlotIndex >= VANILLA_FIRST_SLOT_INDEX
				&& sourceSlotIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
			// This is a vanilla container slot so merge the stack into the tile inventory
			if (!mergeItemStack(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX,
					TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT, false)) {
				return ItemStack.EMPTY; // EMPTY_ITEM
			}
		} else if (sourceSlotIndex >= TE_INVENTORY_FIRST_SLOT_INDEX
				&& sourceSlotIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
			// This is a TE slot so merge the stack into the players inventory
			if (!mergeItemStack(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT,
					false)) {
				return ItemStack.EMPTY;
			}
		} else {
			LOGGER.warn("Invalid slotIndex:" + sourceSlotIndex);
			return ItemStack.EMPTY;
		}

		// If stack size == 0 (the entire stack was moved) set slot contents to null
		if (sourceStack.getCount() == 0) {
			sourceSlot.putStack(ItemStack.EMPTY);
		} else {
			sourceSlot.onSlotChanged();
		}

		sourceSlot.onTake(playerEntity, sourceStack);
		this.sort();
		return copyOfSourceStack;
	}
	
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player) {

		ItemStack itemstack = ItemStack.EMPTY;
	      PlayerInventory playerinventory = player.inventory;
	      if (clickTypeIn == ClickType.QUICK_CRAFT) {
	         int j1 = this.dragEvent;
	         this.dragEvent = getDragEvent(dragType);
	         if ((j1 != 1 || this.dragEvent != 2) && j1 != this.dragEvent) {
	            this.resetDrag();
	         } else if (playerinventory.getItemStack().isEmpty()) {
	            this.resetDrag();
	         } else if (this.dragEvent == 0) {
	            this.dragMode = extractDragMode(dragType);
	            if (isValidDragMode(this.dragMode, player)) {
	               this.dragEvent = 1;
	               this.dragSlots.clear();
	            } else {
	               this.resetDrag();
	            }
	         } else if (this.dragEvent == 1) {
	            Slot slot7 = this.inventorySlots.get(slotId);
	            ItemStack itemstack12 = playerinventory.getItemStack();
	            if (slot7 != null && canAddItemToSlot(slot7, itemstack12, true) && slot7.isItemValid(itemstack12) && (this.dragMode == 2 || itemstack12.getCount() > this.dragSlots.size()) && this.canDragIntoSlot(slot7)) {
	               this.dragSlots.add(slot7);
	            }
	         } else if (this.dragEvent == 2) {
	            if (!this.dragSlots.isEmpty()) {
	               ItemStack itemstack9 = playerinventory.getItemStack().copy();
	               int k1 = playerinventory.getItemStack().getCount();
	               for(Slot slot8 : this.dragSlots) {
	                  ItemStack itemstack13 = playerinventory.getItemStack();
	                  if (slot8 != null && canAddItemToSlot(slot8, itemstack13, true) && slot8.isItemValid(itemstack13) && (this.dragMode == 2 || itemstack13.getCount() >= this.dragSlots.size()) && this.canDragIntoSlot(slot8)) {
	                     ItemStack itemstack14 = itemstack9.copy();
	                     int j3 = slot8.getHasStack() ? slot8.getStack().getCount() : 0;
	                     computeStackSize(this.dragSlots, this.dragMode, itemstack14, j3);
	                     int k3 = Math.min(itemstack14.getMaxStackSize(), slot8.getItemStackLimit(itemstack14));
	                     if (itemstack14.getCount() > k3) {
	                        itemstack14.setCount(k3);
	                     }

	                     k1 -= itemstack14.getCount() - j3;
	                     slot8.putStack(itemstack14);
	                  }
	               }

	               itemstack9.setCount(k1);
	               playerinventory.setItemStack(itemstack9);
	            }

	            this.resetDrag();
	         } else {
	            this.resetDrag();
	         }
	      } else if (this.dragEvent != 0) {
	         this.resetDrag();
	      } else if ((clickTypeIn == ClickType.PICKUP || clickTypeIn == ClickType.QUICK_MOVE) && (dragType == 0 || dragType == 1)) {
	         if (slotId == -999) {
	            if (!playerinventory.getItemStack().isEmpty()) {
	               if (dragType == 0) {
	                  player.dropItem(playerinventory.getItemStack(), true);
	                  playerinventory.setItemStack(ItemStack.EMPTY);
	               }

	               if (dragType == 1) {
	                  player.dropItem(playerinventory.getItemStack().split(1), true);
	               }
	            }
	         } else if (clickTypeIn == ClickType.QUICK_MOVE) {
	            if (slotId < 0) {
	               return ItemStack.EMPTY;
	            }

	            Slot slot5 = this.inventorySlots.get(slotId);
	            if (slot5 == null || !slot5.canTakeStack(player)) {
	               return ItemStack.EMPTY;
	            }

	            for(ItemStack itemstack7 = this.transferStackInSlot(player, slotId); !itemstack7.isEmpty() && ItemStack.areItemsEqual(slot5.getStack(), itemstack7); itemstack7 = this.transferStackInSlot(player, slotId)) {
	               itemstack = itemstack7.copy();
	            }
	         } else {
	            if (slotId < 0) {
	               return ItemStack.EMPTY;
	            }

	            Slot slot6 = this.inventorySlots.get(slotId);
	            if (slot6 != null) {
	               ItemStack itemstack8 = slot6.getStack();
	               ItemStack itemstack11 = playerinventory.getItemStack();
	               if (!itemstack8.isEmpty()) {
	                  itemstack = itemstack8.copy();
	               }

	               if (itemstack8.isEmpty()) {
	                  if (!itemstack11.isEmpty() && slot6.isItemValid(itemstack11)) {
	                     int j2 = dragType == 0 ? itemstack11.getCount() : 1;
	                     if (j2 > slot6.getItemStackLimit(itemstack11)) {
	                        j2 = slot6.getItemStackLimit(itemstack11);
	                     }

	                     slot6.putStack(itemstack11.split(j2));
	                  }
	               } else if (slot6.canTakeStack(player)) {
	                  if (itemstack11.isEmpty()) {
	                     if (itemstack8.isEmpty()) {
	                        slot6.putStack(ItemStack.EMPTY);
	                        playerinventory.setItemStack(ItemStack.EMPTY);
	                     } else {
	                        int k2 = dragType == 0 ? itemstack8.getCount() : (itemstack8.getCount() + 1) / 2;
	                        playerinventory.setItemStack(slot6.decrStackSize(k2));
	                        if (itemstack8.isEmpty()) {
	                           slot6.putStack(ItemStack.EMPTY);
	                        }

	                        slot6.onTake(player, playerinventory.getItemStack());
	                     }
	                  } else if (slot6.isItemValid(itemstack11)) {
	                     if (areItemsAndTagsEqual(itemstack8, itemstack11)) {
	                        int l2 = dragType == 0 ? itemstack11.getCount() : 1;
	                        if (l2 > slot6.getItemStackLimit(itemstack11) - itemstack8.getCount()) {
	                           l2 = slot6.getItemStackLimit(itemstack11) - itemstack8.getCount();
	                        }

	                        if (l2 > itemstack11.getMaxStackSize() - itemstack8.getCount()) {
	                           l2 = itemstack11.getMaxStackSize() - itemstack8.getCount();
	                        }

	                        itemstack8.grow(itemstack11.count);
	                        itemstack11.shrink(itemstack11.count);
	                     } else if (itemstack11.getCount() <= slot6.getItemStackLimit(itemstack11)) {
	                        slot6.putStack(itemstack11);
	                        playerinventory.setItemStack(itemstack8);
	                     }
	                  } else if (itemstack11.getMaxStackSize() > 1 && areItemsAndTagsEqual(itemstack8, itemstack11) && !itemstack8.isEmpty()) {
	                     int i3 = itemstack8.getCount();
	                     if (i3 + itemstack11.getCount() <= itemstack11.getMaxStackSize()) {
	                        itemstack11.grow(i3);
	                        itemstack8 = slot6.decrStackSize(i3);
	                        if (itemstack8.isEmpty()) {
	                           slot6.putStack(ItemStack.EMPTY);
	                        }
	                        slot6.onTake(player, playerinventory.getItemStack());
	                     }
	                  }
	               }

	               slot6.onSlotChanged();
	            }
	         }
	      } else if (clickTypeIn == ClickType.SWAP && dragType >= 0 && dragType < 9) {
	         Slot slot4 = this.inventorySlots.get(slotId);
	         ItemStack itemstack6 = playerinventory.getStackInSlot(dragType);
	         ItemStack itemstack10 = slot4.getStack();
	         if (!itemstack6.isEmpty() || !itemstack10.isEmpty()) {
	            if (itemstack6.isEmpty()) {
	               if (slot4.canTakeStack(player)) {
	                  playerinventory.setInventorySlotContents(dragType, itemstack10);
//	                  slot4.onSwapCraft(itemstack10.getCount());
	                  slot4.putStack(ItemStack.EMPTY);
	                  slot4.onTake(player, itemstack10);
	               }
	            } else if (itemstack10.isEmpty()) {
	               if (slot4.isItemValid(itemstack6)) {
	                  int l1 = slot4.getItemStackLimit(itemstack6);
	                  if (itemstack6.getCount() > l1) {
	                     slot4.putStack(itemstack6.split(l1));
	                  } else {
	                     slot4.putStack(itemstack6);
	                     playerinventory.setInventorySlotContents(dragType, ItemStack.EMPTY);
	                  }
	               }
	            } 
	            else if (slot4.canTakeStack(player) && slot4.isItemValid(itemstack6)) {
	               int i2 = slot4.getItemStackLimit(itemstack6);
	               if (itemstack6.getCount() > i2) {
	                  slot4.putStack(itemstack6.split(i2));
	                  slot4.onTake(player, itemstack10);
	                  if (!playerinventory.addItemStackToInventory(itemstack10)) {
	                     player.dropItem(itemstack10, true);
	                  }
	               } else {
	                  slot4.putStack(itemstack6);
	                  playerinventory.setInventorySlotContents(dragType, itemstack10);
	                  slot4.onTake(player, itemstack10);
	               }
	            }
	         }
	      } else if (clickTypeIn == ClickType.CLONE && player.abilities.isCreativeMode && playerinventory.getItemStack().isEmpty() && slotId >= 0) {
	         Slot slot3 = this.inventorySlots.get(slotId);
	         if (slot3 != null && slot3.getHasStack()) {
	            ItemStack itemstack5 = slot3.getStack().copy();
	            itemstack5.setCount(itemstack5.getMaxStackSize());
	            playerinventory.setItemStack(itemstack5);
	         }
	      } else if (clickTypeIn == ClickType.THROW && playerinventory.getItemStack().isEmpty() && slotId >= 0) {
	         Slot slot2 = this.inventorySlots.get(slotId);
	         if (slot2 != null && slot2.getHasStack() && slot2.canTakeStack(player)) {
	            ItemStack itemstack4 = slot2.decrStackSize(dragType == 0 ? 1 : slot2.getStack().getCount());
	            slot2.onTake(player, itemstack4);
	            player.dropItem(itemstack4, true);
	         }
	      } else if (clickTypeIn == ClickType.PICKUP_ALL && slotId >= 0) {
	         Slot slot = this.inventorySlots.get(slotId);
	         ItemStack itemstack1 = playerinventory.getItemStack();
	         if (!itemstack1.isEmpty() && (slot == null || !slot.getHasStack() || !slot.canTakeStack(player))) {
	            int i = dragType == 0 ? 0 : this.inventorySlots.size() - 1;
	            int j = dragType == 0 ? 1 : -1;

	            for(int k = 0; k < 2; ++k) {
	               for(int l = i; l >= 0 && l < this.inventorySlots.size() && itemstack1.getCount() < itemstack1.getMaxStackSize(); l += j) {
	                  Slot slot1 = this.inventorySlots.get(l);
	                  if (slot1.getHasStack() && canAddItemToSlot(slot1, itemstack1, true) && slot1.canTakeStack(player) && this.canMergeSlot(itemstack1, slot1)) {
	                     ItemStack itemstack2 = slot1.getStack();
	                     if (k != 0 || itemstack2.getCount() != itemstack2.getMaxStackSize()) {
	                        int i1 = Math.min(itemstack1.getMaxStackSize() - itemstack1.getCount(), itemstack2.getCount());
	                        ItemStack itemstack3 = slot1.decrStackSize(i1);
	                        itemstack1.grow(i1);
	                        if (itemstack3.isEmpty()) {
	                           slot1.putStack(ItemStack.EMPTY);
	                        }

	                        slot1.onTake(player, itemstack3);
	                     }
	                  }
	               }
	            }
	         }
	         this.detectAndSendChanges();
	      }
			this.sort();
	      return itemstack;
	}

	@Override
	public void putStackInSlot(int slotID, ItemStack stack) {
		super.putStackInSlot(slotID, stack);
//		this.sort();
	}
	
	// pass the close container message to the parent inventory (not strictly needed
	// for this example)
	// see ContainerChest and TileEntityChest - used to animate the lid when no
	// players are accessing the chest any more
	@Override
	public void onContainerClosed(PlayerEntity playerIn) {
		super.onContainerClosed(playerIn);
	}
	
	public void sort() {
		for(int i = 0; i < chestContents.getSizeInventory() - 1; i++) {
			
			ItemStack stack = chestContents.getStackInSlot(i);
			
			//Check for the first item that's not AIR
			
			if(stack.getItem().equals(Items.AIR)) {			
				for(int j = i + 1; j < chestContents.getSizeInventory(); j++) {
					
					ItemStack stack2 = chestContents.getStackInSlot(j);
					
					if(!chestContents.getStackInSlot(j).getItem().equals(Items.AIR)) {
						chestContents.setInventorySlotContents(i, stack2);
						chestContents.setInventorySlotContents(j, new ItemStack(Items.AIR));
						break;
					}
				}	
			}
			
			//Grouping
			
			for(int j = chestContents.getSizeInventory() - 1; j > i; j--) {
				
				ItemStack stack2 = chestContents.getStackInSlot(j);
				if(!(stack2.getItem().equals(Items.AIR)))
				if(stack.getTag() == null) {
					if(stack.getItem().equals(stack2.getItem())) {
						stack.setCount(stack.getCount() + stack2.getCount());
						chestContents.setInventorySlotContents(j, new ItemStack(Items.AIR));
					}
				} else {
					if(stack.getItem().equals(stack2.getItem()) && stack.getTag().equals(stack2.getTag())) {
						stack.setCount(stack.getCount() + stack2.getCount());
						chestContents.setInventorySlotContents(j, new ItemStack(Items.AIR));
					}
				}
			}
		}
		
		for(int i = 0; i < chestContents.getSizeInventory() - 1; i++) {
			ItemStack stack = chestContents.getStackInSlot(i);
			if(!(stack.getItem().equals(Items.AIR)))
				for(int j = i + 1; j < chestContents.getSizeInventory(); j++) {
					ItemStack stack2 = chestContents.getStackInSlot(j);
					if(!(stack2.getItem().equals(Items.AIR)))
					if(stack.getDisplayName().getFormattedText().compareTo(stack2.getDisplayName().getFormattedText()) > 0) {
						ItemStack stack3 = stack2.copy();
						chestContents.setInventorySlotContents(j, stack);
						chestContents.setInventorySlotContents(i, stack3);
						System.out.println("Changed");
					}
				}
		}
	}

	private ChestContents chestContents;
	private static final Logger LOGGER = LogManager.getLogger();
}