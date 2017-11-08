package com.cjburkey.miningwells.packet;

import com.cjburkey.miningwells.LogUtils;
import com.cjburkey.miningwells.gui.GuiWell;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketWellToClient implements IMessage  {
	
	private int energy;
	private int maxEnergy;
	private boolean working;
	
	public PacketWellToClient() {
	}
	
	public PacketWellToClient(int energy, int maxEnergy, boolean working) {
		this.energy = energy;
		this.maxEnergy = maxEnergy;
		this.working = working;
	}
	
	public void fromBytes(ByteBuf buf) {
		String s = ByteBufUtils.readUTF8String(buf);
		if (s == null) {
			return;
		}
		String[] split = s.split(";");
		if (split.length != 3) {
			return;
		}
		try {
			energy = Integer.parseInt(split[0]);
			maxEnergy = Integer.parseInt(split[1]);
			working = Boolean.parseBoolean(split[2]);
		} catch(Exception e) {
			LogUtils.info("Failed to convert client packet info: \"" + s + "\"");
		}
	}
	
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, energy + ";" + maxEnergy + ";" + working);
	}
	
	public static class Handler implements IMessageHandler<PacketWellToClient, IMessage> {
		
		public IMessage onMessage(PacketWellToClient msg, MessageContext ctx) {
			if (msg != null) {
				GuiWell.energy = msg.energy;
				GuiWell.maxEnergy = msg.maxEnergy;
				GuiWell.working = msg.working;
			}
			return null;
		}
		
	}
	
}