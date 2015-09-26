package com.gurn.bmanager.dao;

import java.util.List;

import com.gurn.bmanager.beans.Bet;

public interface BetDAO {
	 public void addBet(Bet bet);   //добавить ставку
	    public void updateBet(Bet bet);//обновить ставку
	    public Bet getBetById(Long id);    //получить ставку по id
	    public List<Bet> getAllBets();              //получить все ставки
	    public void deleteBet(Bet bet);//удалить ставку

}
