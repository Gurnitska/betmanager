package com.gurnitskaya.bmanager.dao;

import java.sql.SQLException;
import java.util.List;

import com.gurnitskaya.bmanager.beans.Bet;

public interface BetDAO {
	 public void addStudent(Bet bet) throws SQLException;   //добавить ставку
	    public void updateStudent(Bet bet) throws SQLException;//обновить ставку
	    public Bet getStudentById(Long id) throws SQLException;    //получить ставку по id
	    public List<Bet> getAllStudents() throws SQLException;              //получить все ставки
	    public void deleteStudent(Bet bet) throws SQLException;//удалить ставку

}
