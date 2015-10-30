package com.gurnitskaya.bmanager.impl;

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
	public void addBet(Bet bet){
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		Integer betID = null;
		try {
			tx = session.beginTransaction();
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
	public void updateBet(Bet bet) {
		System.out.println(bet);
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("update Bet "
					+ "set date = :date,"
					+ "score = :score,"
					+ "homeCommand = :homeCommand,"
					+ "guestCommand = :guestCommand,"
					+ "koef = :koef,"
					+ "league = :league,"
					+ "result = :result,"
					+ "type = :type,"
					+ "value = :value"
					+ " where id = :id ");
			query.setParameter("id",bet.getId());
			query.setParameter("date", bet.getDate());
			query.setParameter("score", bet.getScore());
			query.setParameter("homeCommand", bet.getHomeCommand());
			query.setParameter("guestCommand", bet.getGuestCommand());
			query.setParameter("koef", bet.getKoef());
			query.setParameter("league", bet.getLeague());
			query.setParameter("result", bet.getResult());
			query.setParameter("type", bet.getType());
			query.setParameter("value", bet.getValue());
			
			int result = query.executeUpdate();
			System.out.println(result);
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
	}

	@Override
	public Bet getBetById(Long id){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bet> getAllBets(){
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
						+ bet.getHomeCommand() + "\", " + bet.getScore() + bet.getDate().getClass());
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
	public void deleteBet(Bet bet){
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(bet);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			if(session.isOpen()){
				session.close();
			}
		}
		
	}

}
