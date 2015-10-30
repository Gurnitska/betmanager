package com.gurnitskaya.bmanager.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gurnitskaya.bmanager.beans.Bet;
import com.gurnitskaya.bmanager.beans.Type;
import com.gurnitskaya.bmanager.dao.AbstractHibernateHelper;
import com.gurnitskaya.bmanager.dao.TypeDAO;

public class TypeImplDAO extends AbstractHibernateHelper implements TypeDAO {

	@Override
	public void addType(Type type) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		Integer typeID = null;
		try {
			tx = session.beginTransaction();
			typeID = (Integer) session.save(type);
			System.out.println(typeID);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public void updateType(Type type) {
		// TODO Auto-generated method stub

	}

	@Override
	public Type getTypeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Type> getAllTypes() {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<Type> types = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Type");
			types = (List<Type>) query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if(session.isOpen()){
				session.close();
			}
		}
		return types;
	}

	@Override
	public void deleteType(Type type) {
		// TODO Auto-generated method stub

	}

}
