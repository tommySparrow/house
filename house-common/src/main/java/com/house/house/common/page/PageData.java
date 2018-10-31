package com.house.house.common.page;

import java.util.List;

/**
 * @ Author jmy
 * @ Description 数据返回接收类//TODO User
 * @ Date 2018/10/31
 * @ Param 
 * @ return
 **/
public class PageData<T> {

	private List<T> list;
	private Pagination pagination;//pageNum,pageSize,totalCount,pages
	
	public PageData(Pagination pagination, List<T> list){
		this.pagination = pagination;
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public static  <T> PageData<T> buildPage(List<T> list,Long count,Integer pageSize,Integer pageNum){
		Pagination _pagination = new Pagination(pageSize, pageNum, count);
		return new PageData<>(_pagination, list);
	}
	
}
