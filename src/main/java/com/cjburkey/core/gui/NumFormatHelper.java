package com.cjburkey.core.gui;

import java.util.Locale;
import com.cjburkey.miningwells.LogUtils;
import com.ibm.icu.text.NumberFormat;

public class NumFormatHelper {
	
	private static NumberFormat nf = null;
	
	public static final void commonPreinit() {
		try {
			nf = NumberFormat.getInstance(new Locale("en"));
		} catch(Exception e) {
			LogUtils.error("Unable to construct a number formatter; numbers may look ugly. Exception: " + e.getMessage());
			LogUtils.warn("This may be a deobf bug...you'll likely never see this in game.");
		}
	}
	
	public static final boolean hasFormat() {
		return nf != null;
	}
	
	public static final NumberFormat getFormat() {
		return nf;
	}
	
	public static final String format(int format) {
		if (hasFormat()) {
			return nf.format(format);
		}
		return null;
	}
	
}