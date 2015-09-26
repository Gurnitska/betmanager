package com.gurn.bmanager.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gurn.bmanager.beans.Bet;
import com.gurn.bmanager.dao.AbstractHibernateHelper;
import com.gurn.bmanager.dao.BetDAO;

public class BetImplDAO extends AbstractHibernateHelper implements BetDAO{

	@Override
	public void addBet(Bet bet){
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
	public void updateBet(Bet bet) {
		// TODO Auto-generated method stub
		
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
