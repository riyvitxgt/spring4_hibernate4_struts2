package com.test.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.test.dao.BaseDao;
import com.test.model.PageBean;
import com.test.model.PageList;

/**
 * @author FengJianBo
 * 2014年3月18日 上午11:49:03
 */
@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void add(T t) throws Exception {
		hibernateTemplate.save(t);
	}

	@Override
	public void update(T t) throws Exception {
		hibernateTemplate.update(t);
	}

	@Override
	public void delete(T t) throws Exception {
		hibernateTemplate.delete(t);
	}

	@Override
	public void deleteById(Serializable id) throws Exception {
		String hql = "delete " + getEntityClassName() + " where "+getPkColunmName()+"=?";
		executeUpdate(hql,id);
	}
	
	@Override
	public List<T> findAll() throws Exception {
		String hql = "from "+this.getEntityClassName();
		return this.queryForList(hql, null).getResultList();
		
	}
	@Override
	public PageList<T> findAll(PageBean pageBean) throws Exception {
		String hql = "from "+this.getEntityClassName();
		return (PageList<T>)this.queryForList(hql, pageBean);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public T findById(Serializable id) throws Exception {
		return (T)hibernateTemplate.get(getEntityClassName(), id);
		
	}
	public T findForObject(final T entity){
		PageList<T> pageList = findForList(entity, null);
		if(pageList != null && pageList.getResultList() != null && !pageList.getResultList().isEmpty()){
			return pageList.get(0);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public PageList<T> findForList(final T entity, final PageBean pageBean){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity.getClass()).add(Example.create(entity));
		List<?> list = null;
		if(!PageBean.isEmpty(pageBean)) {
			list = hibernateTemplate.findByCriteria(detachedCriteria, pageBean.getLimit(), pageBean.getOffset());
			Criteria criteria = detachedCriteria.getExecutableCriteria(hibernateTemplate.getSessionFactory().getCurrentSession());
			Long totalCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			pageBean.setTotalCount(totalCount);
		} else {
			list =hibernateTemplate.findByCriteria(detachedCriteria);
		}
		return new PageList<T>((List<T>)list, pageBean);
	}
	
	// 
	
	/**
	 * 单对象查询
	 * @author FengJianBo
	 * @param hql
	 * @param objects
	 * @return
	 * 2014年3月21日 下午2:08:26
	 */
	protected T queryForObject(final String hql, final Object ... objects){
		PageList<T> pageList = this.queryForList(hql, null, null, objects);
		if(pageList != null && pageList.isEmpty()){
			return pageList.get(0);
		}
		return null;
	}

	/**
	 * 执行查询结果集
	 * @author FengJianBo
	 * @param hql
	 * @param page
	 * @param pageSize
	 * @param objects
	 * @return
	 * 2014年3月21日 下午2:08:09
	 */
	protected PageList<T> queryForList(final String hql, final PageBean pageBean, final Object ... objects ){
		return hibernateTemplate.execute(new HibernateCallback<PageList<T>>() {
			@SuppressWarnings("unchecked")
			@Override
			public PageList<T> doInHibernate(Session session) throws HibernateException {
				Query createQuery = createQuery(session, hql, objects);
				List<T> list = null;
				if(pageBean != null){
					createQuery.setFirstResult(pageBean.getLimit());   
					createQuery.setMaxResults(pageBean.getOffset());  
					list = createQuery.list();
					String countHql = null;
					if(StringUtils.containsIgnoreCase(hql, "from")){
						countHql = "select count(0) "+StringUtils.substring(hql, StringUtils.indexOfIgnoreCase(hql, "from"));
					}
					Query countQuery = createQuery(session, countHql, objects);
					List<Long> countList = countQuery.list();
					Long totalCount = 0L;
					if(countList != null && countList.size() > 0){
						totalCount =countList.get(0);
					}
					pageBean.setTotalCount(totalCount);;
				} else {
					list = createQuery.list();
				}
				return new PageList<T>(list, pageBean);
			}
			
		});
	}

	/**
	 * 执行查询以外的操作
	 * @author FengJianBo
	 * @param hql
	 * @param objs
	 * 2014年3月21日 上午11:42:19
	 */
	protected void executeUpdate(final String hql, final Object ... objects){
		hibernateTemplate.execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				Query createQuery = createQuery(session, hql, objects);
				return createQuery.executeUpdate();
			}
		});
	}
	/**
	 * 得到泛型中的实体类型
	 * @author FengJianBo
	 * @return
	 * 2014年3月18日 下午2:32:35
	 */
	protected Class<T> getEntityClass(){
		@SuppressWarnings("unchecked")
		Class<T> entityClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return entityClass;
	}
	/**
	 * @Author: Charles
	 * @Description: 获取表主键类型
	 * @param clazz
	 * @return Type:
	 */
	public Type getPkType() {
		ClassMetadata meta = hibernateTemplate.getSessionFactory().getClassMetadata(getEntityClass());
		return meta.getIdentifierType();
	}
	/**
	 * 获取主键名
	 * @author FengJianBo
	 * @return
	 * 2014年3月21日 下午2:42:49
	 */
	public String getPkColunmName(){
		ClassMetadata meta = hibernateTemplate.getSessionFactory().getClassMetadata(getEntityClass());
		return meta.getIdentifierPropertyName();
	}
	
	
	/**
	 * 获取实体类型名
	 * @author FengJianBo
	 * @return
	 * 2014年3月18日 下午2:33:01
	 */
	protected String getEntityClassName() {
		ClassMetadata meta = hibernateTemplate.getSessionFactory().getClassMetadata(getEntityClass());
		return meta.getEntityName();
	}
	/**
	 * 返回设置好参数的查询对象
	 * @author FengJianBo
	 * @param query
	 * @param objects
	 * 2014年3月21日 下午2:07:56
	 */
	private Query createQuery(Session session,String hql, Object ... objects) {
		System.out.println(hql);
		Query query = session.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		return query;
	}
}
