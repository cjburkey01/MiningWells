package com.cjburkey.miningwells.packet;

import com.cjburkey.miningwells.LogUtils;
import com.cjburkey.miningwells.ModInfo;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModPackets {
	
	private static SimpleNetworkWrapper network;
	
	public static final void commonPreinit() {
		network = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MODID);
		
		network.registerMessage(PacketWellToServer.Handler.class, PacketWellToServer.class, 0, Side.SERVER);
		network.registerMessage(PacketWellToClient.Handler.class, PacketWellToClient.class, 1, Side.CLIENT);
		
		LogUtils.info("Registered mining well info packets");
	}
	
	public static final SimpleNetworkWrapper getNetwork() {
		return network;
	}
	
}