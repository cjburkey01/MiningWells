package com.cjburkey.miningwells.proxy;

import com.cjburkey.miningwells.LogUtils;
import com.cjburkey.miningwells.MiningWells;
import com.cjburkey.miningwells.config.ModConfig;
import com.cjburkey.miningwells.gui.ModGuiHandler;
import com.cjburkey.miningwells.packet.ModPackets;
import com.cjburkey.miningwells.reg.ModOreDictionary;
import com.cjburkey.miningwells.tab.ModTabs;
import com.cjburkey.miningwells.tile.ModTiles;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	
	public void construct(FMLConstructionEvent e) {
	}
	
	public void preinit(FMLPreInitializationEvent e) {
		ModPackets.commonPreinit();
		ModConfig.commonPreinit(e);
		ModTiles.commonPreinit();
		ModTabs.commonPreinit();
	}
	
	public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(MiningWells.instance, new ModGuiHandler());
		ModOreDictionary.commonInit();
	}
	
	public void postinit(FMLPostInitializationEvent e) {
	}
	
}