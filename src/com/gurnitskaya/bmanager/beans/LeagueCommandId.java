package com.gurnitskaya.bmanager.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class LeagueCommandId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6855141931443147L;
	private League league;
	private Command command;
	@ManyToOne(cascade=CascadeType.ALL)
	public League getLeague() {
		return league;
	}
	public void setLeague(League league) {
		this.league = league;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	public Command getCommand() {
		return command;
	}
	public void setCommand(Command command) {
		this.command = command;
	}
	public LeagueCommandId(){
		super();
	}
	
}
