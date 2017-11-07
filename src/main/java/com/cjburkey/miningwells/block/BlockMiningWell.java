package com.cjburkey.miningwells.block;

import java.util.List;
import javax.annotation.Nullable;
import com.cjburkey.miningwells.config.ModConfig;
import com.cjburkey.miningwells.tile.TileEntityMiningWell;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMiningWell extends BlockBase implements ITileEntityProvider {

	public BlockMiningWell() {
		super(Material.IRON, "block_mining_well");
		setHardness(3.5f);
		setHarvestLevel("pickaxe", 1);
		setSoundType(SoundType.METAL);
	}
	
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing dir, float x, float y, float z) {
		return true;
	}
	
	public TileEntity createNewTileEntity(World world, int data) {
		return new TileEntityMiningWell(this);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
		if (tooltip != null && ModConfig.displayMiningWellJoke) {
			tooltip.add(I18n.format("misc.mining_well_joke"));
		}
	}
	
}