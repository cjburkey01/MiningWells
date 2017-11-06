package com.cjburkey.miningwells.item;

import com.cjburkey.miningwells.ModInfo;
import com.cjburkey.miningwells.tab.ModTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item {
	
	public ItemBase(String unlocName) {
		setUnlocalizedName(unlocName);
		setRegistryName(ModInfo.MODID, unlocName);
		setCreativeTab(ModTabs.tabItems);
	}
	
}