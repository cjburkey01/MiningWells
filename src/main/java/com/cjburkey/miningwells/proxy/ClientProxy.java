package com.cjburkey.miningwells.proxy;

import com.cjburkey.core.gui.NumFormatHelper;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	public void construct(FMLConstructionEvent e) {
		super.construct(e);
	}
	
	public void preinit(FMLPreInitializationEvent e) {
		super.preinit(e);
		
		NumFormatHelper.commonPreinit();
	}
	
	public void init(FMLInitializationEvent e) {
		super.init(e);
	}
	
	public void postinit(FMLPostInitializationEvent e) {
		super.postinit(e);
	}
	
}