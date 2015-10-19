package com.gurnitskaya.bmanager.beans;


import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "League_Command")
@AssociationOverrides({
	@AssociationOverride(name = "id.league", 
		joinColumns = @JoinColumn(name = "league_id")),
	@AssociationOverride(name = "id.command", 
		joinColumns = @JoinColumn(name = "command_id")) })

public class League_Command {
	
	private LeagueCommandId id = new LeagueCommandId();
	@EmbeddedId
	public LeagueCommandId getId() {
		return id;
	}
	public void setId(LeagueCommandId id) {
		this.id = id;
	}
	public League_Command(){
		
	}
	public League_Command(LeagueCommandId id){
		this.id = id;
	}
	
}
