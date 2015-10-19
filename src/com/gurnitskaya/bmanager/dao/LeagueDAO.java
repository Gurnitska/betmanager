package com.gurnitskaya.bmanager.dao;

import java.sql.SQLException;
import java.util.List;

import com.gurnitskaya.bmanager.beans.League;


public interface LeagueDAO {

	public void addLeague(League league) throws SQLException; 

	public void updateLeague(League league) throws SQLException;

	public League getLeagueById(Long id) throws SQLException; 

	public List<League> getAllLeagues() throws SQLException; 

	public void deleteLeague(League league) throws SQLException;
}
