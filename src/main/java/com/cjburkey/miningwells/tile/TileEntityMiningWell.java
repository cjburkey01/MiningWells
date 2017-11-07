package com.cjburkey.miningwells.tile;

import com.cjburkey.miningwells.Utils;
import com.cjburkey.miningwells.block.BlockMiningWell;
import com.cjburkey.miningwells.block.ModBlocks;
import com.cjburkey.miningwells.config.ModConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityMiningWell extends TileEntity implements ITickable, IEnergyStorage {
	
	private int timer = 0;
	private int energy = 0;
	
	public TileEntityMiningWell() {
	}
	
	public TileEntityMiningWell(BlockMiningWell b) {
		blockType = b;
	}

	public void update() {
		if (!world.isRemote) {
			if (timer >= ModConfig.ticksBetweenMining) {
				timer = 0;
				tryToMineBlock();
			} else {
				timer ++;
			}
		}
	}
	
	private void tryToMineBlock() {
		int y = getPos().getY() - 1;
		BlockPos pos = new BlockPos(getPos().getX(), y, getPos().getZ());
		while (getWorld().getBlockState(pos).getBlock().equals(ModBlocks.blockWellExtension)) {
			pos = new BlockPos(getPos().getX(), -- y, getPos().getZ());
		}
		IBlockState blockState = getWorld().getBlockState(pos);
		int reqdEnergy = getEnergyRequired(pos, blockState);
		if (reqdEnergy == extractEnergy(reqdEnergy, true)) {
			extractEnergy(reqdEnergy, false);
			if (!blockState.getBlock().equals(Blocks.AIR) && blockState.getBlockHardness(getWorld(), pos) >= 0.0f) {
				mineBlock(pos);
			}
			getWorld().setBlockState(pos, ModBlocks.blockWellExtension.getDefaultState());
		}
	}
	
	private int getEnergyRequired(BlockPos pos, IBlockState state) {
		float hardness = Float.min(state.getBlockHardness(getWorld(), pos), 10.0f);
		return (int) (Math.ceil(ModConfig.energyPerOperation * (hardness + 1.0)));
	}
	
	private void mineBlock(BlockPos pos) {
		IBlockState block = getWorld().getBlockState(pos);
		for (ItemStack stack : Utils.getBlockDrops(pos, getWorld())) {
			if (!addToAdjacentInventory(stack)) {
				addStackToWorld(stack);
			}
		}
	}
	
	private boolean addToAdjacentInventory(ItemStack stack) {
		BlockPos above = new BlockPos(getPos().getX(), getPos().getY() + 1, getPos().getZ());
		BlockPos right = new BlockPos(getPos().getX() + 1, getPos().getY(), getPos().getZ());
		BlockPos left = new BlockPos(getPos().getX() - 1, getPos().getY(), getPos().getZ());
		BlockPos front = new BlockPos(getPos().getX(), getPos().getY(), getPos().getZ() + 1);
		BlockPos back = new BlockPos(getPos().getX(), getPos().getY(), getPos().getZ() - 1);
		if (attemptToAddStackToBlock(above, stack)) {
			return true;
		}
		if (attemptToAddStackToBlock(right, stack)) {
			return true;
		}
		if (attemptToAddStackToBlock(left, stack)) {
			return true;
		}
		if (attemptToAddStackToBlock(front, stack)) {
			return true;
		}
		if (attemptToAddStackToBlock(back, stack)) {
			return true;
		}
		return false;
	}
	
	private boolean attemptToAddStackToBlock(BlockPos pos, ItemStack stack) {
		TileEntity ent = getWorld().getTileEntity(pos);
		if (ent != null && ent instanceof IInventory) {
			IInventory inv = (IInventory) ent;
			return Utils.addStackToInv(stack, inv);
		}
		return false;
	}
	
	private void addStackToWorld(ItemStack stack) {
		EntityItem item = new EntityItem(getWorld(), getPos().getX() + 0.5f, getPos().getY() + 1.0f, getPos().getZ() + 0.5f, stack);
		item.setVelocity(0, 0.25, 0);
		getWorld().spawnEntity(item);
	}

	public int receiveEnergy(int receive, boolean simulate) {
		int ableToReceive = Math.min(ModConfig.maxEnergy - energy, receive);
		if (!simulate) {
			energy += ableToReceive;
			markDirty();
		}
		return ableToReceive;
	}

	public int extractEnergy(int extract, boolean simulate) {
		int ableToExtract = Math.min(energy, extract);
		if (!simulate) {
			energy -= ableToExtract;
			markDirty();
		}
		return ableToExtract;
	}

	public int getEnergyStored() {
		return energy;
	}

	public int getMaxEnergyStored() {
		return ModConfig.maxEnergy;
	}

	public boolean canExtract() {
		return false;
	}

	public boolean canReceive() {
		return true;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		if (nbt == null) {
			nbt = new NBTTagCompound();
		}
		nbt.setInteger("energy", energy);
		return super.writeToNBT(nbt);
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("energy")) {
			energy = nbt.getInteger("energy");
		}
		super.readFromNBT(nbt);
	}
	
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}
	
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability.equals(CapabilityEnergy.ENERGY)) {
			return (T) this;
		}
		return super.getCapability(capability, facing);
	}
	
	public boolean canRenderBreaking() {
		return false;
	}
	
	public boolean hasFastRenderer() {
		return false;
	}
	
}