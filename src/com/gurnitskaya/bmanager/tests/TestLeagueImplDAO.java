package com.gurnitskaya.bmanager.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.core.IsNull;
import org.hibernate.Hibernate;
import org.junit.Test;

import com.gurnitskaya.bmanager.beans.Command;
import com.gurnitskaya.bmanager.beans.League;
import com.gurnitskaya.bmanager.beans.LeagueCommandId;
import com.gurnitskaya.bmanager.beans.League_Command;
import com.gurnitskaya.bmanager.impl.CommandImplDAO;
import com.gurnitskaya.bmanager.impl.LeagueImplDAO;

public class TestLeagueImplDAO {

//	@Test
	public void testAddLeague() {
		CommandImplDAO comImpl = new CommandImplDAO();
		League league = new League();
		Set<League_Command> leagues_commands = new HashSet<>();
		LeagueCommandId id = new LeagueCommandId();
		Command command = new Command("Gerta");
		comImpl.addCommand(command);
		id.setLeague(league);
		id.setCommand(command);
		leagues_commands.add(new League_Command(id));
		league.setName("Bundesliga");
		league.setLeague_command(leagues_commands);
		LeagueImplDAO leagImpl = new LeagueImplDAO();

		leagImpl.addLeague(league);

	}

	@Test
	public void testGetAllLeagues() {
		LeagueImplDAO leagImpl = new LeagueImplDAO();
		List<League> leagues= leagImpl.getAllLeagues();
		System.out.println(leagues.get(4).getLeague_command());
		for(League_Command lc: leagues.get(4).getLeague_command()){
			System.out.println(lc.getId().getCommand().getName());
			System.out.println(lc.getId().getLeague().getName());
		}
		assertNotNull(leagues);
	}
}
