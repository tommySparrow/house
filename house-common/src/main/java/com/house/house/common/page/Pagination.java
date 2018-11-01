package com.house.house.common.page;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @ Author jmy
 * @ Description 分页参数(可直接传递给前端展示)//TODO User
 * @ Date 2018/10/31
 * @ Param
 * @ return
 **/
public class Pagination {
	private int pageNum;
	private int pageSize;
	private long totalCount;//总数
	private List<Integer> pages = Lists.newArrayList();//页数

	public Pagination(Integer pageSize, Integer pageNum, Long totalCount) {
	   this.totalCount = totalCount;
	   this.pageNum = pageNum;
	   this.pageSize = pageSize;
	   for(int i=1;i<=pageNum;i++){
		   pages.add(i);
	   }
	   //totalCount:总的条数
		//totalCount % pageSize != 0 时,总页数加1
	   Long pageCount = totalCount/pageSize + ((totalCount % pageSize == 0 ) ? 0: 1);
	   if (pageCount > pageNum) {
		  for(int i= pageNum + 1; i<= pageCount ;i ++){
			  pages.add(i);
		  }
	   }
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<Integer> getPages() {
		return pages;
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}
}
