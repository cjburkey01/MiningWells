package com.cjburkey.miningwells.gui;

import java.awt.Rectangle;
import java.util.List;
import java.util.Locale;
import com.cjburkey.core.gui.NumFormatHelper;
import com.cjburkey.core.gui.tooltip.GuiToolTip;
import com.cjburkey.core.gui.tooltip.ToolTipManager;
import com.cjburkey.core.gui.tooltip.ToolTipManager.ToolTipRenderer;
import com.cjburkey.miningwells.LogUtils;
import com.cjburkey.miningwells.ModInfo;
import com.cjburkey.miningwells.container.ContainerMiningWell;
import com.cjburkey.miningwells.packet.ModPackets;
import com.cjburkey.miningwells.packet.PacketWellToServer;
import com.cjburkey.miningwells.tile.TileEntityMiningWell;
import com.ibm.icu.text.NumberFormat;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiMiningWell extends GuiContainer implements ToolTipRenderer {
	
	public static int energy;
	public static int maxEnergy;
	public static boolean working;
	
	public static final int progX = 80;
	public static final int progY = 22;
	public static final int progW = 16;
	public static final int progH = 41;
	
	private ToolTipManager ttm = new ToolTipManager();
	
	private InventoryPlayer plyInv;
	private TileEntityMiningWell te;

	public GuiMiningWell(EntityPlayer player, TileEntityMiningWell te) {
		super(new ContainerMiningWell(player.inventory, te));
		
		plyInv = player.inventory;
		this.te = te;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
		ttm.drawTooltips(this, mouseX, mouseY);
	}
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		ModPackets.getNetwork().sendToServer(new PacketWellToServer(te.getPos()));
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(new ResourceLocation(ModInfo.MODID, "textures/gui/gui_well.png"));
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		super.drawGuiContainerForegroundLayer(x, y);
		String s = te.getDisplayName().getUnformattedText();
		getFontRenderer().drawString(s, 88 - getFontRenderer().getStringWidth(s) / 2, 6, 0xBFBFBF);
		getFontRenderer().drawString(plyInv.getDisplayName().getUnformattedText(), 8, 72, 0xBFBFBF);
		drawEnergyBar();
	}
	
	private void drawEnergyBar() {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(ModInfo.MODID, "textures/gui/gui_bar.png"));
		float percent = 0;
		if (energy != 0 && maxEnergy != 0) {
			percent = ((float) energy / (float) maxEnergy);
		}
		int fh = (int) (percent * (float) progH) + 1;
		int fy = progY - (int) (percent * (float) progH) + progH;
		drawTexturedModalRect(progX, fy, 0, progH - fh + 1, progW, (percent == 0) ? 0 : fh);
		addToolTips(progX - 1, progY - 1, progW + 3, progH + 3);
	}
	
	private void addToolTips(int x, int y, int w, int h) {
		String e = ((!NumFormatHelper.hasFormat()) ? (energy + "") : (NumFormatHelper.format(energy)));
		String m = ((!NumFormatHelper.hasFormat()) ? (maxEnergy + "") : (NumFormatHelper.format(maxEnergy)));
		ttm.clear();
		ttm.addToolTip(new GuiToolTip(new Rectangle(x, y, w, h), "Energy", e + " FE / " + m + " FE"));
	}

	public FontRenderer getFontRenderer() {
		return fontRenderer;
	}

	public void drawTextS(List<String> strings, int i1, int i2, FontRenderer font) {
		this.drawHoveringText(strings, i1, i2, font);
	}
	
}