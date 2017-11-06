package com.cjburkey.miningwells;

public class LogUtils {
	
	private static final String NULL = "null";
	
	public static final void info(Object msg) {
		MiningWells.LOG.info(objToMsg(msg));
	}
	
	public static final void warn(Object msg) {
		MiningWells.LOG.warn(objToMsg(msg));
	}
	
	public static final void error(Object msg) {
		MiningWells.LOG.error(objToMsg(msg));
	}
	
	private static final String objToMsg(Object msg) {
		return (msg == null) ? NULL : msg.toString();
	}
	
}