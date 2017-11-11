package com.cjburkey.miningwells.packet;

import com.cjburkey.miningwells.LogUtils;
import com.cjburkey.miningwells.gui.GuiMiningWell;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketWellToClient implements IMessage  {
	
	private int energy;
	private int maxEnergy;
	private boolean working;
	private int bpo;
	private int fortune;
	private boolean silk;
	
	public PacketWellToClient() {
	}
	
	public PacketWellToClient(int energy, int maxEnergy, boolean working, int bpo, int fortune, boolean silk) {
		this.energy = energy;
		this.maxEnergy = maxEnergy;
		this.working = working;
		this.bpo = bpo;
		this.fortune = fortune;
		this.silk = silk;
	}
	
	public void fromBytes(ByteBuf buf) {
		String s = ByteBufUtils.readUTF8String(buf);
		if (s == null) {
			return;
		}
		String[] split = s.split(";");
		if (split.length != 6) {
			return;
		}
		try {
			energy = Integer.parseInt(split[0]);
			maxEnergy = Integer.parseInt(split[1]);
			working = Boolean.parseBoolean(split[2]);
			bpo = Integer.parseInt(split[3]);
			fortune = Integer.parseInt(split[4]);
			silk = Boolean.parseBoolean(split[5]);
		} catch(Exception e) {
			LogUtils.info("Failed to convert client packet info: \"" + s + "\"");
		}
	}
	
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, energy + ";" + maxEnergy + ";" + working + ";" + bpo + ";" + fortune + ";" + silk);
	}
	
	public static class Handler implements IMessageHandler<PacketWellToClient, IMessage> {
		
		public IMessage onMessage(PacketWellToClient msg, MessageContext ctx) {
			if (msg != null) {
				GuiMiningWell.energy = msg.energy;
				GuiMiningWell.maxEnergy = msg.maxEnergy;
				GuiMiningWell.working = msg.working;
				GuiMiningWell.bpo = msg.bpo;
				GuiMiningWell.fortune = msg.fortune;
				GuiMiningWell.silk = msg.silk;
			}
			return null;
		}
		
	}
	
}