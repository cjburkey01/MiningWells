package com.cjburkey.miningwells.item;

import com.cjburkey.miningwells.ModInfo;
import com.cjburkey.miningwells.block.BlockBase;
import com.cjburkey.miningwells.tab.ModTabs;
import net.minecraft.item.ItemBlock;

public class ItemBaseBlock extends ItemBlock {

	public ItemBaseBlock(BlockBase block) {
		super(block);
		setUnlocalizedName(block.getRegistryName().getResourcePath());
		setRegistryName(ModInfo.MODID, block.getRegistryName().getResourcePath());
		setCreativeTab(ModTabs.tabBlocks);
	}
	
}