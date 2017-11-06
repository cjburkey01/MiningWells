package com.cjburkey.miningwells.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TabItems extends CreativeTabs {
	
	public TabItems() {
		super("tab_miningwells_items");
	}
	
	public ItemStack getTabIconItem() {
		return new ItemStack(Items.APPLE, 1);
	}
	
}