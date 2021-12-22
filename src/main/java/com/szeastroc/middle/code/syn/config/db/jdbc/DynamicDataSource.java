package com.szeastroc.middle.code.syn.config.db.jdbc;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource  extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
	
		String dataSourceKey =  DynamicDataSourceContextHolder.getDataSourceType();
	    return dataSourceKey;
	}

}
