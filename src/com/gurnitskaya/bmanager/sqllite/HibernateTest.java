package com.gurnitskaya.bmanager.sqllite;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gurnitskaya.bmanager.beans.Bet;
import com.gurnitskaya.bmanager.impl.BetImplDAO;
import com.gurnitskaya.bmanager.sqllite.utils.SessionFactoryUtil;

public class HibernateTest {

	public static void main(String[] args) {
		BetImplDAO betImpl = new BetImplDAO();
		Bet bet = new Bet(new Date(), "Bundesliga", "Bayer", "Munech", "df", 45, 1.2, 12, "3-0");
		try {
			System.out.println(betImpl.getAllStudents());
			betImpl.addStudent(bet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		addBet();
//		queryBet();
//		SessionFactoryUtil.getSessionFactory().close();

	}

	private static void queryBet() {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.println("in query");
			Query query = session.createQuery("from Bet");
			List<Bet> list = (List<Bet>) query.list();
			java.util.Iterator<Bet> iter = list.iterator();
			System.out.println(list);
			while (iter.hasNext()) {
				Bet bet = iter.next();
				System.out.println("Bet: \"" + bet.getDate() + "\", "
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

	}

	/* Method to CREATE an employee in the database */
	public static Integer addBet() {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Integer betID = null;
		try {
			tx = session.beginTransaction();
			Bet bet = new Bet(new Date(), "Bundesliga", "Bayer", "Munech", "df", 45, 1.2, 12, "3-0");
			betID = (Integer) session.save(bet);
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
		return betID;
	}

	/* Method to READ all the employees */
	public void listBets(Session session) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Bet> bets = session.createSQLQuery("FROM Bets").addEntity(Bet.class).list();
			for (Iterator<Bet> iterator = bets.iterator(); iterator.hasNext();) {
				Bet bet = (Bet) iterator.next();
				System.out.println("bet " + bet);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE salary for an employee */
	public void updateBet(Session session, Integer BetID, int salary) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Bet bet = (Bet) session.get(Bet.class, BetID);
			session.update(bet);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an employee from the records */
	public void deleteBet(Session session, Integer BetID) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Bet employee = (Bet) session.get(Bet.class, BetID);
			session.delete(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
