package com.test.model;
/**
 * 分页信息记录实体
 * @author FengJianBo
 * 2014年3月21日 下午3:40:02
 */
public class PageBean {
	public PageBean(){}
	public PageBean(int page, int pageSize){
		this.page = page;
		this.pageSize = pageSize;
	}
	/**
	 * 当前页
	 */
	private int page;
	/**
	 * 每页条数
	 */
	private int pageSize;

	/**
	 * 总数
	 */
	private Long totalCount;
	/**
	 * 得到取数据的结束条数
	 * @author FengJianBo
	 * @return
	 * 2014年3月21日 下午3:45:28
	 */
	public int getOffset(){
		return pageSize;
	}
	/**
	 * 得到当前页所在开始条数
	 * @author FengJianBo
	 * @return
	 * 2014年3月21日 下午3:45:58
	 */
	public int getLimit(){
		return page * pageSize - pageSize;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * 获取总页数
	 * @author FengJianBo
	 * @return
	 * 2014年3月21日 上午10:44:24
	 */
	public Long getTotalPage() {
		if(totalCount%pageSize == 0){
			return totalCount / pageSize;
		} else {
			return totalCount / pageSize +1;
		}
	}

	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public static boolean isEmpty(PageBean pageBean){
		if(pageBean == null){
			return true;
		}
		return false;
	}
	public String toString(){
		return "page:"+this.page+",pageSize:"+this.pageSize+",totalCount:"+this.totalCount;
	}
}
