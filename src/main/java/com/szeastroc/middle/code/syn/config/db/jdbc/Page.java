package com.szeastroc.middle.code.syn.config.db.jdbc;

import java.util.List;

public class Page {
	/**
	 * 当前页数
	 */
	private Integer page = 1;
	/**
	 * 单页条数
	 */
	private Integer limit = 10;
	/**
	 * 总条数
	 */
	private Integer total;

	private Integer totalPage;

	private Object data;
	
	private List<Sorter> sorter;
	

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public List<Sorter> getSorter() {
		return sorter;
	}

	public void setSorter(List<Sorter> sorter) {
		this.sorter = sorter;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}	
}
