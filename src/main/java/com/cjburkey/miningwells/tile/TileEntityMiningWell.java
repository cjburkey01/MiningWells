package com.cjburkey.miningwells.tile;

import java.util.List;
import com.cjburkey.core.inventory.InventoryUtils;
import com.cjburkey.core.misc.Utils;
import com.cjburkey.miningwells.LogUtils;
import com.cjburkey.miningwells.block.BlockMiningWell;
import com.cjburkey.miningwells.block.ModBlocks;
import com.cjburkey.miningwells.config.ModConfig;
import com.cjburkey.miningwells.player.FakePlayerHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.ForgeEventFactory;

public class TileEntityMiningWell extends TileEntity implements ITickable, IEnergyStorage, IInventory {
	
	private int timer = 0;
	private int energy = 0;
	
	public TileEntityMiningWell() {
	}
	
	public TileEntityMiningWell(BlockMiningWell b) {
		blockType = b;
	}

	public void update() {
		if (!world.isRemote && !receivingRedstoneSignal()) {
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
			if (!getWorld().isAirBlock(pos) && blockState.getBlockHardness(getWorld(), pos) >= 0.0f) {
				mineBlock(pos);
			}
			if (blockState.getBlockHardness(getWorld(), pos) >= 0.0f || getWorld().isAirBlock(pos)) {
				getWorld().setBlockState(pos, ModBlocks.blockWellExtension.getDefaultState());
			}
		}
	}
	
	private int getEnergyRequired(BlockPos pos, IBlockState state) {
		float hardness = Float.min(state.getBlockHardness(getWorld(), pos), 10.0f);
		return (int) (Math.ceil(ModConfig.energyPerOperation * (hardness + 1.0)));
	}
	
	private void mineBlock(BlockPos pos) {
		IBlockState block = getWorld().getBlockState(pos);
		handleDrops(block, pos);
	}
	
	private void handleDrops(IBlockState state, BlockPos pos) {
		List<ItemStack> drops = Utils.getBlockDrops(pos, getWorld());
		ForgeEventFactory.fireBlockHarvesting(drops, getWorld(), pos, state, 0, 1.0f, false, FakePlayerHandler.getFakePlayer());
		AxisAlignedBB bb = new AxisAlignedBB(getPos().west().down(2).north(), new BlockPos(getPos().getX() + 2, 0, getPos().getZ() + 2));
		List<EntityItem> ents = getWorld().getEntitiesWithinAABB(EntityItem.class, bb);
		for (EntityItem ent : ents) {
			drops.add(ent.getItem());
			getWorld().removeEntity(ent);
		}
		ents.clear();
		for (ItemStack stack : drops) {
			if (!addToAdjacentInventory(stack)) {
				dropStack(stack);
			}
		}
		drops.clear();
	}
	
	private boolean receivingRedstoneSignal() {
		return getWorld().isBlockPowered(getPos());
	}
	
	private boolean addToAdjacentInventory(ItemStack stack) {
		if (attemptToAddStackToBlock(getPos().up(), stack)) {
			return true;
		}
		if (attemptToAddStackToBlock(getPos().east(), stack)) {
			return true;
		}
		if (attemptToAddStackToBlock(getPos().west(), stack)) {
			return true;
		}
		if (attemptToAddStackToBlock(getPos().north(), stack)) {
			return true;
		}
		if (attemptToAddStackToBlock(getPos().south(), stack)) {
			return true;
		}
		return false;
	}
	
	private boolean attemptToAddStackToBlock(BlockPos pos, ItemStack stack) {
		TileEntity ent = getWorld().getTileEntity(pos);
		if (ent != null && ent instanceof IInventory) {
			IInventory inv = (IInventory) ent;
			return InventoryUtils.addStackToInventory(stack, inv);
		}
		return false;
	}
	
	private void dropStack(ItemStack stack) {
		EntityItem item = new EntityItem(getWorld(), getPos().getX() + 0.5f, getPos().getY() + 1.0f, getPos().getZ() + 0.5f, stack);
		getWorld().spawnEntity(item);
	}
	
	// Begin Energy

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
	
	public boolean isWorking() {
		return true;
	}
	
	// Begin Inventory
	
	public String getName() {
		return "tile.block_mining_well.name";
	}

	public boolean hasCustomName() {
		return false;
	}
	
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation(getName());
	}

	public int getSizeInventory() {
		return 0;
	}

	public boolean isEmpty() {
		return false;
	}

	public ItemStack getStackInSlot(int index) {
		return ItemStack.EMPTY;
	}

	public ItemStack decrStackSize(int index, int count) {
		return ItemStack.EMPTY;
	}

	public ItemStack removeStackFromSlot(int index) {
		return ItemStack.EMPTY;
	}

	public void setInventorySlotContents(int index, ItemStack stack) {
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean isUsableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(pos.add(0.5f, 0.5f, 0.5f)) <= 64;
	}

	public void openInventory(EntityPlayer player) {
	}

	public void closeInventory(EntityPlayer player) {
	}

	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	public int getField(int id) {
		return 0;
	}

	public void setField(int id, int value) {
	}

	public int getFieldCount() {
		return 0;
	}

	public void clear() {
	}
	
	// Begin NBT, capabilities, rendering, etc
	
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