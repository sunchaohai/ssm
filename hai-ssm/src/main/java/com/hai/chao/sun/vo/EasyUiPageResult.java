package com.hai.chao.sun.vo;

import java.util.List;

public class EasyUiPageResult {
	/**
	 * 返回总数
	 */
	private Long total;
	/**
	 * 数据列表
	 */
	private List<?> rows;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
}
