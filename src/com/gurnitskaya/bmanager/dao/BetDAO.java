package com.gurnitskaya.bmanager.dao;

import java.sql.SQLException;
import java.util.List;

import com.gurnitskaya.bmanager.beans.Bet;

public interface BetDAO {
	public void addBet(Bet bet) throws SQLException; 

	public void updateBet(Bet bet) throws SQLException;

	public Bet getBetById(Long id) throws SQLException; 

	public List<Bet> getAllBets() throws SQLException; 

	public void deleteBet(Bet bet) throws SQLException;

}
