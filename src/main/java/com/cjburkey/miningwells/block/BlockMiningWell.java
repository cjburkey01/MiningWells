package com.cjburkey.miningwells.block;

import java.util.List;
import javax.annotation.Nullable;
import com.cjburkey.miningwells.config.ModConfig;
import com.cjburkey.miningwells.tile.TileEntityMiningWell;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMiningWell extends BlockBase implements ITileEntityProvider {

	public BlockMiningWell() {
		super(Material.IRON, "block_mining_well");
		this.setHardness(3.5f);
		this.setHarvestLevel("pickaxe", 1);
		this.setSoundType(SoundType.METAL);
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