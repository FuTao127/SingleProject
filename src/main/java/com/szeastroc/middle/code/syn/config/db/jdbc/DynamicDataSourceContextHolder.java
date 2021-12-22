package com.szeastroc.middle.code.syn.config.db.jdbc;

public class DynamicDataSourceContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	// 设置数据源
	public static void setDataSourceType(String dataSourceType) {
	    contextHolder.set(dataSourceType);
	}

	// 获取数据源
	public static String getDataSourceType() {
		
		
		return contextHolder.get();
		
	}

	// 获取数据源
	public static void clear() {
		contextHolder.remove();
	}
}
