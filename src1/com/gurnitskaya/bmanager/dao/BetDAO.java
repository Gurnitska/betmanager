package com.gurnitskaya.bmanager.dao;

import java.sql.SQLException;
import java.util.List;

import com.gurnitskaya.bmanager.beans.Bet;

public interface BetDAO {
	 public void addBet(Bet bet) throws SQLException;   //добавить ставку
	    public void updateBet(Bet bet) throws SQLException;//обновить ставку
	    public Bet getBetById(Long id) throws SQLException;    //получить ставку по id
	    public List<Bet> getAllBets() throws SQLException;              //получить все ставки
	    public void deleteBet(Bet bet) throws SQLException;//удалить ставку

}
