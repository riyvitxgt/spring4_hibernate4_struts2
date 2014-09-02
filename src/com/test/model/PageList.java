package com.test.model;

import java.util.List;

/**
 * 分页处理对象
 * @author FengJianBo
 * 2014年3月21日 下午1:50:16
 */
public class PageList<T> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 存放结果集
	 */
	private List<T> resultList;
	/**
	 * 分页信息
	 */
	private PageBean pageBean;
	/**
	 * 带参构造方法
	 * @param resultList 结果集
	 * @param pageBean 分页信息
	 */
	public PageList(List<T> resultList, PageBean pageBean){
		this.resultList = resultList;
		this.pageBean = pageBean;
	}
	
	public PageList(){}
	/**
	 * 得到结果集中的元素
	 * @author FengJianBo
	 * @param index 下标
	 * @return
	 * 2014年3月21日 下午4:39:14
	 */
	public T get(int index){
		return resultList.get(index);
	}
	/**
	 * 判断结果集是否为空
	 * @author FengJianBo
	 * @return
	 * 2014年3月21日 下午4:38:22
	 */
	public boolean isEmpty(){
		if(this.resultList == null || this.resultList.isEmpty()){
			return true;
		}
		return false;
	}
	/**
	 * 获取存储的结果集
	 * @author FengJianBo
	 * @return
	 * 2014年3月21日 下午4:31:38
	 */
	public List<T> getResultList() {
		return resultList;
	}
	/**
	 * 设置存放的结果集
	 * @author FengJianBo
	 * @return
	 * 2014年3月21日 下午4:31:38
	 */
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	/**
	 * 获取分页信息
	 * @author FengJianBo
	 * @return
	 * 2014年3月21日 下午4:32:10
	 */
	public PageBean getPageBean() {
		return pageBean;
	}
	/**
	 * 设置分页信息
	 * @author FengJianBo
	 * @param pageBean
	 * 2014年3月21日 下午4:32:20
	 */
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public String toString(){
		return "pageBean:"+this.pageBean+",result:"+resultList.toString();
	}
}
