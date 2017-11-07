package com.cjburkey.miningwells.tile;

import com.cjburkey.miningwells.LogUtils;
import com.cjburkey.miningwells.block.BlockMiningWell;
import com.cjburkey.miningwells.config.ModConfig;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityMiningWell extends TileEntity implements ITickable, IEnergyStorage {
	
	private int energy = 0;
	
	public TileEntityMiningWell(BlockMiningWell b) {
		this.blockType = b;
	}
	
	public boolean canRenderBreaking() {
		return false;
	}
	
	public boolean hasFastRenderer() {
		return false;
	}

	public void update() {
		
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
	
}