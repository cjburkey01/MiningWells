package com.cjburkey.miningwells.tab;

import net.minecraft.creativetab.CreativeTabs;

public class ModTabs {
	
	public static CreativeTabs tabItems;
	public static CreativeTabs tabBlocks;
	
	public static final void commonPreinit() {
		tabItems = new TabItems();
		tabBlocks = new TabBlocks();
	}
	
}