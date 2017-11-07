package com.cjburkey.miningwells.reg;

import com.cjburkey.miningwells.LogUtils;
import com.cjburkey.miningwells.item.ModItems;
import net.minecraftforge.oredict.OreDictionary;

public class ModOreDictionary {
	
	public static final void commonInit() {
		OreDictionary.registerOre("gearStone", ModItems.itemStoneGear);
		OreDictionary.registerOre("gearIron", ModItems.itemIronGear);
		OreDictionary.registerOre("gearTin", ModItems.itemTinGear);
		OreDictionary.registerOre("gearCopper", ModItems.itemCopperGear);
		OreDictionary.registerOre("gearGold", ModItems.itemGoldGear);
		OreDictionary.registerOre("gearDiamond", ModItems.itemDiamondGear);
		
		LogUtils.info("Registered ore dictionary materials.");
	}
	
}