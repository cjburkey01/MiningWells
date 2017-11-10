package com.cjburkey.miningwells.container;

import com.cjburkey.miningwells.tile.TileEntityMiningWell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ContainerMiningWell extends ContainerBase {
	
	public ContainerMiningWell(IInventory plyInv, TileEntityMiningWell te) {
		super(te, (InventoryPlayer) plyInv);
		addPlayerInventory();
	}
	
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		return ItemStack.EMPTY;
	}
	
	public boolean canInteractWith(EntityPlayer ply) {
		return te.isUsableByPlayer(ply);
	}
	
}