package com.cjburkey.miningwells.block;

import com.cjburkey.miningwells.ModInfo;
import com.cjburkey.miningwells.tab.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {

	public BlockBase(Material material, String unlocName) {
		super(material);
		setUnlocalizedName(unlocName);
		setRegistryName(ModInfo.MODID, unlocName);
		setCreativeTab(ModTabs.tabBlocks);
	}
	
}