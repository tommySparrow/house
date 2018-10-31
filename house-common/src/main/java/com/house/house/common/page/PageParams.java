package com.house.house.common.page;

/**
 * @ Author jmy
 * @ Description 封装分页需要的一些数据//TODO User
 * @ Date 2018/10/31
 * @ Param
 * @ return
 **/
public class PageParams {

//① select * from table limit 2,1;
//含义是跳过2条取出1条数据，limit后面是从第2条开始读，读取1条信息，即读取第3条数据

//② select * from table limit 2 offset 1;
//含义是从第1条（不包括）数据开始取出2条数据，limit后面跟的是2条数据，offset后面是从第1条开始读取，即读取第2,3条

	private static final Integer PAGE_SIZE = 5;//默认每页显示条数

	private Integer pageSize;
	private Integer pageNum;
	private Integer offset;
	private Integer limit;
	
	public static PageParams build(Integer pageSize,Integer pageNum){
		if (pageSize == null) {
			pageSize = PAGE_SIZE;
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		return new PageParams(pageSize, pageNum);
	}
	
	public PageParams(){
		this(PAGE_SIZE, 1);
	}
	
	public PageParams(Integer pageSize, Integer pageNum){
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.offset = pageSize * (pageNum -1);
		this.limit = pageSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
