package com.gurnitskaya.bmanager.model;

import java.sql.Date;
import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.gurnitskaya.bmanager.beans.Bet;
import com.gurnitskaya.bmanager.util.LocalDateAdapter;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Bet.
 *
 * @author Olga gurn
 */

public class BetWrapper {

	private final IntegerProperty id;
	private final ObjectProperty<LocalDate> date;
	private final StringProperty league;
    private final StringProperty homeCommand;
    private final StringProperty guestCommand;
    private final StringProperty type;
    private final IntegerProperty value;
    private final DoubleProperty koef;
    private final DoubleProperty result;
    private final StringProperty score;
    private final Bet bet;
    /**
     * Default constructor.
     */
	public BetWrapper() {
		this(0, null, null, null, null, null, 0, 0, 0, null);
	}
    /**
     * Constructor with initial data.
     * 
     * @param localDate
     * @param league
     * @param homeCommand
     * @param guestCommand
     * @param type
     * @param value
     * @param koef
     * @param result
     * @param score
     */
	public BetWrapper(int id,LocalDate localDate, String league,
			String homeCommand, String guestCommand,
			String type, int value, double koef, double result, String score) {
		super();
		Date date = null;
		if(localDate != null){
			date = Date.valueOf(localDate.toString());;
		}
		this.bet = new Bet(id, date, league, homeCommand, guestCommand, type, value, koef, result, score);
		this.id = new SimpleIntegerProperty(value);
		this.date = new SimpleObjectProperty<LocalDate>(localDate);
		this.league = new SimpleStringProperty(league);
		this.homeCommand = new SimpleStringProperty(homeCommand);
		this.guestCommand = new SimpleStringProperty(guestCommand);
		this.type = new SimpleStringProperty(type);
		this.value = new SimpleIntegerProperty(value);
		this.koef = new SimpleDoubleProperty(koef);
		this.result = new SimpleDoubleProperty(result);
		this.score = new SimpleStringProperty(score);
	}
	/**
     * Constructor with initial data.
     * 
     * @param bet object
     */
	public BetWrapper(Bet bet) {
		super();
		this.bet = bet;
		this.id = new SimpleIntegerProperty(bet.getValue());
		this.date = new SimpleObjectProperty<LocalDate>(bet.getDateAsLocalDate());
		this.league = new SimpleStringProperty(bet.getLeague());
		this.homeCommand = new SimpleStringProperty(bet.getHomeCommand());
		this.guestCommand = new SimpleStringProperty(bet.getGuestCommand());
		this.type = new SimpleStringProperty(bet.getType());
		this.value = new SimpleIntegerProperty(bet.getValue());
		this.koef = new SimpleDoubleProperty(bet.getKoef());
		this.result = new SimpleDoubleProperty(bet.getResult());
		this.score = new SimpleStringProperty(bet.getScore());
	}
	
	public Integer getId() {
		return id.get();
	}
	public void setId(int result) {
		this.bet.setId(result);
		this.id.set(result);
	}
	public Double getResult() {
		return result.get();
	}
	public void setResult(double result) {
		this.bet.setResult(result);
		this.result.set(result);
	}
	public String getScore() {
		return score.get();
	}
	public void setScore(String gameResult) {
		this.bet.setScore(gameResult);
		this.score.set(gameResult);
	}
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getDate() {
		return date.get();
	}
    public void setDate(LocalDate date) {
    	this.bet.setDateAsLocalDate(date);
		this.date.set(date);
	}
	public String getLeague() {
		return league.get();
	}
	public void setLeague(String league) {
		this.bet.setLeague(league);
		this.league.set(league);
	}
	public String getHomeCommand() {
		return homeCommand.get();
	}
	public void setHomeCommand(String homeCommand) {
		this.bet.setHomeCommand(homeCommand);
		this.homeCommand.set(homeCommand);
	}
	public String getGuestCommand() {
		return guestCommand.get();
	}
	public void setGuestCommand(String guestCommand) {
		this.bet.setGuestCommand(guestCommand);
		this.guestCommand.set(guestCommand);
	}
	public String getType() {
		return type.get();
	}
	public void setType(String type) {
		this.bet.setType(type);
		this.type.set(type);
	}
	public Integer getValue() {
		return value.get();
	}
	public void setValue(int value) {
		this.bet.setValue(value);
		this.value.set(value);
	}
	public Double getKoef() {
		return koef.get();
	}
	public void setKoef(double koef) {
		this.bet.setKoef(koef);
		this.koef.set(koef);
	}
	public Bet getBet() {
		return bet;
	}
	@Override
	public String toString() {
		return "BetWrapper [id=" + id + ", date=" + date + ", league=" + league
				+ ", homeCommand=" + homeCommand + ", guestCommand="
				+ guestCommand + ", type=" + type + ", value=" + value
				+ ", koef=" + koef + ", result=" + result + ", score=" + score
				+ ", bet=" + bet + "]";
	}
    
}
