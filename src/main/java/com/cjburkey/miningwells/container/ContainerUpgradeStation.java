package com.cjburkey.miningwells.container;

import com.cjburkey.miningwells.tile.TileEntityUpgradeStation;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerUpgradeStation extends ContainerBase {
	
	public ContainerUpgradeStation(TileEntityUpgradeStation te, InventoryPlayer plyInv) {
		super(te, plyInv);
		addPlayerInventory();
		addInvSlots();
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