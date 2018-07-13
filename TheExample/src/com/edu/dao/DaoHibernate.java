package com.edu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


public class DaoHibernate<T> implements IBaseDao<T> {

	//@Resource
	private SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int insert(T o) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(o);
		return 1;
	}

	@Override
	public int insertList(List<T> list) {
		// TODO Auto-generated method stub
		for(T t:list){
			insert(t);
		}
		return list.size();
	}

	@Override
	public int update(T o) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(o);
		return 1;
	}

	@Override
	public int deleteList(Class<T> c, int... ids) {
		// TODO Auto-generated method stub
		for(int id:ids){
			delete(c, id);
		}
		return ids.length;
	}

	@Override
	public int delete(T o) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(o);
		return 1;
	}

	@Override
	public int delete(Class<T> c, int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.load(c, id));
		return 1;
	}

	@Override
	public T findById(Class<T> c, int id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(c, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findOne(String hql, String[] param) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query<T> query = session.createQuery(hql);
		if (param != null) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		return query.uniqueResult();
	}

	@Override
	public List<T> find(String hql, String[] param) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		List<T> list = null;
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<T> query = session.createQuery(hql);
		if (param != null) {
			for(int i = 0; i < param.length; i++){
				query.setParameter(i, param[i]);
			}
		}
		return query.list();
	}

	@Override
	public List<T> findPage(String hql, String[] param, int page, int size) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		List<T> list = null;
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<T> query = session.createQuery(hql);
		if (param != null) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		return query.list();
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getCount(String hql, String[] pras) {
		// TODO Auto-generated method stub
		int resu = 0;
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<T> query = session.createQuery(hql);
		if (pras != null) {
			for (int i = 0; i < pras.length; i++) {
				query.setString(i, pras[i]);
			}
		}
		resu = Integer.valueOf(query.iterate().next().toString());
		return resu;
	}

//	@Override
//	public List<T> findByFields(String hql, String[] fields, String condition) {
//		// TODO Auto-generated method stub
//		String findhql = hql;
//		if (fields != null && condition != null && fields.length > 0 && !condition.equals("")) {
//			findhql = findhql + "where 1=1 and (";
//			for (int i = 0; i < fields.length - 1; i++) {
//				findhql += fields[i] + "like '%" + condition + "%' or";
//			}
//			findhql += fields[fields.length - 1] + " like '%" + condition + "%')";
//		}
//		Session session = sessionFactory.getCurrentSession();
//		@SuppressWarnings("unchecked")
//		Query<T> query = session.createQuery(findhql);
//		List<T> list = query.list();
//		return list;
//	}
	

}
