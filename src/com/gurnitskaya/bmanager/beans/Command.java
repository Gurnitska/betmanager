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
@Table(name = "Command")
public class Command {

	@Id
	@Column(name= "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "id.command", cascade=CascadeType.ALL)
	private Set<League_Command> league_command;

	public Command() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Command(String name) {
		super();
		this.name = name;
	}

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
		return "Command [id=" + id + ", name=" + name + ", leagues=" + league_command
				+ "]";
	}
	
}
