package com.gurnitskaya.bmanager.dao;

import java.sql.SQLException;
import java.util.List;

import com.gurnitskaya.bmanager.beans.League;


public interface LeagueDAO {

	public void addLeague(League league); 

	public void updateLeague(League league);

	public League getLeagueById(Long id);
	
	public League getLeagueByName(String name); 

	public List<League> getAllLeagues(); 

	public void deleteLeague(League league);
}
