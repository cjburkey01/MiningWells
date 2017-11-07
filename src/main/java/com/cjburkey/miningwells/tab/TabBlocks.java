package com.cjburkey.miningwells.tab;

import com.cjburkey.miningwells.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TabBlocks extends CreativeTabs {
	
	public TabBlocks() {
		super("tab_miningwells_blocks");
	}
	
	public ItemStack getTabIconItem() {
		return new ItemStack(ModBlocks.blockMiningWell, 1);
	}
	
}