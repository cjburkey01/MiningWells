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

@Mod(name = ModInfo.NAME, modid = ModInfo.MODID, version = ModInfo.VERSION, useMetadata = false, clientSideOnly = false, serverSideOnly = false, acceptedMinecraftVersions = ModInfo.MC_VERSIONS, modLanguage = "java", canBeDeactivated = false)
public class MiningWells {
	
	public static final Logger LOG = LogManager.getLogger(ModInfo.MODID);
	
	@Instance(ModInfo.MODID)
	public static MiningWells instance;
	
	@SidedProxy(clientSide = ModInfo.CLIENT_PROXY, serverSide = ModInfo.SERVER_PROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public void construct(FMLConstructionEvent e) {
		proxy.construct(e);
	}
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent e) {
		proxy.preinit(e);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent e) {
		proxy.postinit(e);
	}
	
}