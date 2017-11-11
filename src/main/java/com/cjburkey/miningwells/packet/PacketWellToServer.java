package com.cjburkey.miningwells.packet;

import com.cjburkey.miningwells.LogUtils;
import com.cjburkey.miningwells.block.BlockMiningWell;
import com.cjburkey.miningwells.tile.TileEntityMiningWell;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketWellToServer implements IMessage  {
	
	private int x;
	private int y;
	private int z;
	
	public PacketWellToServer() {
	}
	
	public PacketWellToServer(BlockPos pos) {
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
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
			x = Integer.parseInt(split[0]);
			y = Integer.parseInt(split[1]);
			z = Integer.parseInt(split[2]);
		} catch(Exception e) {
			LogUtils.info("Failed to convert server packet info: \"" + s + "\"");
		}
	}
	
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, x + ";" + y + ";" + z);
	}
	
	public static class Handler implements IMessageHandler<PacketWellToServer, PacketWellToClient> {
		
		public PacketWellToClient onMessage(PacketWellToServer msg, MessageContext ctx) {
			if (msg != null) {
				World w = ctx.getServerHandler().player.world;
				BlockPos p = new BlockPos(msg.x, msg.y, msg.z);
				IBlockState s = w.getBlockState(p);
				if (s.getBlock() instanceof BlockMiningWell) {
					TileEntityMiningWell well = (TileEntityMiningWell) w.getTileEntity(p);
					return new PacketWellToClient(well.getEnergyStored(), well.getMaxEnergyStored(), well.isWorking(), well.getBlocksPerOperation(), well.getFortune(), well.getSilkTouch());
				}
			}
			return null;
		}
		
	}
	
}