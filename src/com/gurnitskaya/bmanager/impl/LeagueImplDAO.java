package com.gurnitskaya.bmanager.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gurnitskaya.bmanager.beans.League;
import com.gurnitskaya.bmanager.dao.AbstractHibernateHelper;
import com.gurnitskaya.bmanager.dao.LeagueDAO;

public class LeagueImplDAO extends AbstractHibernateHelper implements LeagueDAO {

	@Override
	public void addLeague(League league){
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		Integer leagueID = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from League where name = '" + league.getName() + "'");
			List<League> leagues = (List<League>) query.list();
			System.out.println(leagues);
			if(leagues.isEmpty()){
				leagueID = (Integer) session.save(league);
			}
			System.out.println(leagueID);
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
	public void updateLeague(League league) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public League getLeagueById(Long id){
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<League> leagues = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from League where id = " + id);
			leagues = (List<League>) query.list();
			java.util.Iterator<League> iter = leagues.iterator();
			System.out.println(leagues);
			while (iter.hasNext()) {
				League league = iter.next();
				System.out.println("League: \"" + league.getId() + "\", " + league.getName());
			}
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
		return leagues.get(0);
	}

	@Override
	public List<League> getAllLeagues(){
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<League> leagues = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from League");
			leagues = (List<League>) query.list();
			java.util.Iterator<League> iter = leagues.iterator();
			System.out.println(leagues);
			while (iter.hasNext()) {
				League league = iter.next();
				System.out.println("League: \"" + league.getId() + "\", " + league.getName());
			}
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
		return leagues;
	}

	@Override
	public void deleteLeague(League league) throws SQLException {
		// TODO Auto-generated method stub

	}

}
