package com.gurnitskaya.bmanager.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gurnitskaya.bmanager.beans.Command;
import com.gurnitskaya.bmanager.beans.League;
import com.gurnitskaya.bmanager.dao.AbstractHibernateHelper;
import com.gurnitskaya.bmanager.dao.CommandDAO;

public class CommandImplDAO extends AbstractHibernateHelper implements CommandDAO {

	@Override
	public void addCommand(Command command){
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		Integer commandID = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from League where name = '" + command.getName() + "'");
			List<League> leagues = (List<League>) query.list();
			System.out.println(leagues);
			if(leagues.isEmpty()){
				commandID = (Integer) session.save(command);
			}
			commandID = (Integer) session.save(command);
			System.out.println(commandID);
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
	public void updateCommand(Command command) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Command getCommandById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Command> getAllCommands() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCommand(Command command) throws SQLException {
		// TODO Auto-generated method stub

	}

}
