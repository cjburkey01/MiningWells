package com.cjburkey.miningwells;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cjburkey.miningwells.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = ModInfo.NAME, modid = ModInfo.MODID, version = ModInfo.VERSION, useMetadata = false, clientSideOnly = false, serverSideOnly = false,
	acceptedMinecraftVersions = ModInfo.MC_VERSIONS, modLanguage = "java", canBeDeactivated = false, dependencies = "required-after:burcore@[1.0.3,]")
public class MiningWells {
	
	public static final Logger LOG = LogManager.getLogger(ModInfo.MODID);
	
	@Instance(ModInfo.MODID)
	public static MiningWells instance;
	
	@SidedProxy(clientSide = ModInfo.CLIENT_PROXY, serverSide = ModInfo.SERVER_PROXY)
	public static CommonProxy proxy;
	
	private long start;
	
	@EventHandler
	public void construct(FMLConstructionEvent e) {
		startTimer();
		proxy.construct(e);
		LogUtils.info("Construction event took " + endTimer() + " ms.");
	}
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent e) {
		startTimer();
		proxy.preinit(e);
		LogUtils.info("PreInitialization event took " + endTimer() + " ms.");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		startTimer();
		proxy.init(e);
		LogUtils.info("Initialization event took " + endTimer() + " ms.");
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent e) {
		startTimer();
		proxy.postinit(e);
		LogUtils.info("PostInitialization event took " + endTimer() + " ms.");
	}
	
	private void startTimer() {
		start = System.nanoTime();
	}
	
	private long endTimer() {
		return (System.nanoTime() - start) / 1000000;
	}
	
}