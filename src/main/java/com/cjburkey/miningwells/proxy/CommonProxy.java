package com.cjburkey.miningwells.proxy;

import com.cjburkey.miningwells.LogUtils;
import com.cjburkey.miningwells.tab.ModTabs;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	private long start;
	
	public void construct(FMLConstructionEvent e) {
		startTimer();
		
		LogUtils.info("Construction event took " + endTimer() + " ms.");
	}
	
	public void preinit(FMLPreInitializationEvent e) {
		startTimer();
		
		ModTabs.commonPreinit();
		
		LogUtils.info("PreInitialization event took " + endTimer() + " ms.");
	}
	
	public void init(FMLInitializationEvent e) {
		startTimer();
		
		LogUtils.info("Initialization event took " + endTimer() + " ms.");
	}
	
	public void postinit(FMLPostInitializationEvent e) {
		startTimer();
		
		LogUtils.info("PostInitialization event took " + endTimer() + " ms.");
	}
	
	private void startTimer() {
		start = System.nanoTime();
	}
	
	private long endTimer() {
		return (System.nanoTime() - start) / 1000000;
	}
	
}