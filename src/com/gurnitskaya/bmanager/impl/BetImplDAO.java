package com.gurnitskaya.bmanager.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gurnitskaya.bmanager.beans.Bet;
import com.gurnitskaya.bmanager.dao.AbstractHibernateHelper;
import com.gurnitskaya.bmanager.dao.BetDAO;

public class BetImplDAO extends AbstractHibernateHelper implements BetDAO{

	@Override
	public void addStudent(Bet bet) throws SQLException {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		Integer betID = null;
		try {
			tx = session.beginTransaction();
//			bet = new Bet(new Date(), "Bundesliga", "Bayer", "Munech", "df", 45, 1.2, 12, "3-0");
			betID = (Integer) session.save(bet);
			System.out.println(betID);
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
	public void updateStudent(Bet bet) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Bet getStudentById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bet> getAllStudents() throws SQLException {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<Bet> bets = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Bet");
			bets = (List<Bet>) query.list();
			java.util.Iterator<Bet> iter = bets.iterator();
			System.out.println(bets);
			while (iter.hasNext()) {
				Bet bet = iter.next();
				System.out.println("Bet: \"" + bet.getId() + "\", " + bet.getDate() + "\", "
						+ bet.getHomeCommand() + "\", " + bet.getGameResult());
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
		return bets;
	}

	@Override
	public void deleteStudent(Bet bet) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
