package com.gurnitskaya.bmanager.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "League")
public class League {
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "id.league", cascade=CascadeType.ALL)
	private Set<League_Command> league_command;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "League [id=" + id + ", name=" + name + ", league_command=" + league_command
				+ "]";
	}

	public Set<League_Command> getLeague_command() {
		return league_command;
	}

	public void setLeague_command(Set<League_Command> league_command) {
		this.league_command = league_command;
	}
	
}
