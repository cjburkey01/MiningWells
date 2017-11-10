package com.cjburkey.miningwells.container;

import com.cjburkey.miningwells.LogUtils;
import com.cjburkey.miningwells.item.upgrade.EnumUpgradeType;
import com.cjburkey.miningwells.tile.TileEntityUpgradeStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerUpgradeStation extends ContainerBase {
	
	private final int size = 3;
	
	public ContainerUpgradeStation(TileEntityUpgradeStation te, InventoryPlayer plyInv) {
		super(te, plyInv);
		addInvSlots();
		addPlayerInventory();
	}
	
	public ItemStack transferStackInSlot(EntityPlayer ply, int prevSlot) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(prevSlot);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (prevSlot < size) {
				if (!mergeItemStack(itemstack1, size, inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!mergeItemStack(itemstack1, 0, size, false)) {
				return ItemStack.EMPTY;
			}
			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}
	
	private void addInvSlots() {
		int i = 0;
		addSlotToContainer(new SlotUpgrade(te, i ++, 62, 35, EnumUpgradeType.BLOCKS_PER_OPERATION));
		addSlotToContainer(new SlotUpgrade(te, i ++, 80, 35, EnumUpgradeType.FORTUNE));
		addSlotToContainer(new SlotUpgrade(te, i, 98, 35, EnumUpgradeType.SILK_TOUCH));
	}
	
}