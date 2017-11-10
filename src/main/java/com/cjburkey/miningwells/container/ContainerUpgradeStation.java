package com.cjburkey.miningwells.container;

import com.cjburkey.miningwells.tile.TileEntityUpgradeStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerUpgradeStation extends ContainerBase {
	
	private final int size = 5;
	
	public ContainerUpgradeStation(TileEntityUpgradeStation te, InventoryPlayer plyInv) {
		super(te, plyInv);
		addPlayerInventory();
		addInvSlots();
	}
	
	public ItemStack transferStackInSlot(EntityPlayer ply, int prevSlot) {
		ItemStack out = ItemStack.EMPTY;
		Slot prev = inventorySlots.get(prevSlot);
		if (prev != null && prev.getHasStack()) {
			ItemStack prevStack = prev.getStack();
			out = prevStack.copy();
			if (prevSlot < size) {
				if (!mergeItemStack(prevStack, size, inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!mergeItemStack(prevStack, 0, size, false)) {
				return ItemStack.EMPTY;
			}
			if (prevStack.isEmpty()) {
				prev.putStack(ItemStack.EMPTY);
			} else {
				prev.onSlotChanged();
			}
		}
		return out;
	}
	
	private void addInvSlots() {
		int i = 0;
		addSlotToContainer(new Slot(te, i ++, 62, 35));
		for (int y = 0; y < 3; y ++) {
			addSlotToContainer(new Slot(te, i ++, 80, 17 + y * 18));
		}
		addSlotToContainer(new Slot(te, i, 98, 35));
	}
	
}