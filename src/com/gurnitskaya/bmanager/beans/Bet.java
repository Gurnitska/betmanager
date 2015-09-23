package com.gurnitskaya.bmanager.beans;


import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Bet")
public class Bet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7240278230065235713L;

	@Id
	@Column(name= "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "date")
	@Temporal(value=TemporalType.DATE)
	private Date date;
	private String league;
	private String homeCommand;
	private String guestCommand;
	private String type;
	private int value;
	private double koef;
	private double result;
	private String gameResult;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}
	public LocalDate getDateAsLocalDate(){
		Instant instant = date.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		return zdt.toLocalDate();
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getHomeCommand() {
		return homeCommand;
	}

	public void setHomeCommand(String homeCommand) {
		this.homeCommand = homeCommand;
	}

	public String getGuestCommand() {
		return guestCommand;
	}

	public void setGuestCommand(String guestCommand) {
		this.guestCommand = guestCommand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public double getKoef() {
		return koef;
	}

	public void setKoef(double koef) {
		this.koef = koef;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public String getGameResult() {
		return gameResult;
	}

	public void setGameResult(String gameResult) {
		this.gameResult = gameResult;
	}

	public Bet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bet(Date date, String league, String homeCommand,
			String guestCommand, String type, int value, double koef,
			double result, String gameResult) {
		super();
		this.date = date;
		this.league = league;
		this.homeCommand = homeCommand;
		this.guestCommand = guestCommand;
		this.type = type;
		this.value = value;
		this.koef = koef;
		this.result = result;
		this.gameResult = gameResult;
	}

	public Bet(int id, Date date, String league, String homeCommand,
			String guestCommand, String type, int value, double koef,
			double result, String gameResult) {
		super();
		this.id = id;
		this.date = date;
		this.league = league;
		this.homeCommand = homeCommand;
		this.guestCommand = guestCommand;
		this.type = type;
		this.value = value;
		this.koef = koef;
		this.result = result;
		this.gameResult = gameResult;
	}
	
	
}
