package com.test.dao;

import java.util.List;

import com.test.model.PageBean;
import com.test.model.PageList;

/**
 * @author FengJianBo
 * 2014年3月18日 上午11:44:14
 */
public interface BaseDao<T> {
	/**
	 * 添加
	 * @author FengJianBo
	 * @param t
	 * @throws Exception
	 * 2014年3月18日 上午11:48:02
	 */
	public void add(T t) throws Exception;
	/**
	 * 修改
	 * @author FengJianBo
	 * @param t
	 * @throws Exception
	 * 2014年3月18日 上午11:48:07
	 */
	public void update(T t) throws Exception;
	/**
	 * 按照对象删除
	 * @author FengJianBo
	 * @param t
	 * @throws Exception
	 * 2014年3月18日 上午11:48:16
	 */
	public void delete(T t) throws Exception;
	/**
	 * 按照id删除
	 * @author FengJianBo
	 * @param id
	 * @throws Exception
	 * 2014年3月18日 上午11:48:26
	 */
	public void deleteById(java.io.Serializable id) throws Exception;
	/**
	 * 查询所有
	 * @author FengJianBo
	 * @throws Exception
	 * 2014年3月18日 上午11:48:35
	 */
	public List<T> findAll() throws Exception;
	/**
	 * 查询所有
	 * @author FengJianBo
	 * @throws Exception
	 * 2014年3月18日 上午11:48:35
	 */
	public PageList<T> findAll(PageBean pageBean) throws Exception;
	/**
	 * 按照id查询
	 * @author FengJianBo
	 * @param id
	 * @throws Exception
	 * 2014年3月18日 上午11:48:41
	 */
	public T findById(java.io.Serializable id) throws Exception;
	/**
	 * 以对象作为查询条件进行查询
	 * @author FengJianBo
	 * @param entity 作为条件的对象
	 * @return
	 * 2014年3月21日 下午4:50:14
	 */
	public T findForObject(final T entity);
	/**
	 * 以对象作为查询条件进行查询
	 * @author FengJianBo
	 * @param entity 作为条件的结果集
	 * @param pageBean 分页信息对象
	 * @return
	 * 2014年3月21日 下午2:08:09
	 */
	public PageList<T> findForList(final T entity, final PageBean pageBean);
	
}
