package com.cjburkey.miningwells.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerBase extends Container {
	
	protected IInventory te;
	protected InventoryPlayer plyInv;
	
	public ContainerBase(IInventory te, InventoryPlayer plyInv) {
		this.te = te;
		this.plyInv = plyInv;
	}
	
	protected void addPlayerInventory() {
		int i = 0;
		for(int x = 0; x < 9; ++x) {
			addSlotToContainer(new Slot(plyInv, i ++, 8 + x * 18, 142));
		}
		for(int y = 0; y < 3; ++y) {
			for(int x = 0; x < 9; ++x) {
				addSlotToContainer(new Slot(plyInv, i ++, 8 + x * 18, 84 + y * 18));
			}
		}
	}
	
	public boolean canInteractWith(EntityPlayer ply) {
		return te.isUsableByPlayer(ply);
	}
	
}