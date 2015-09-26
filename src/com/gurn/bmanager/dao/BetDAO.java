package com.gurn.bmanager.dao;

import java.util.List;

import com.gurn.bmanager.beans.Bet;

public interface BetDAO {
	 public void addBet(Bet bet);   //�������� ������
	    public void updateBet(Bet bet);//�������� ������
	    public Bet getBetById(Long id);    //�������� ������ �� id
	    public List<Bet> getAllBets();              //�������� ��� ������
	    public void deleteBet(Bet bet);//������� ������

}
