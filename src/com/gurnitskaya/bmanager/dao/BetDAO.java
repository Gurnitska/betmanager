package com.gurnitskaya.bmanager.dao;

import java.sql.SQLException;
import java.util.List;

import com.gurnitskaya.bmanager.beans.Bet;

public interface BetDAO {
	 public void addStudent(Bet bet) throws SQLException;   //�������� ������
	    public void updateStudent(Bet bet) throws SQLException;//�������� ������
	    public Bet getStudentById(Long id) throws SQLException;    //�������� ������ �� id
	    public List<Bet> getAllStudents() throws SQLException;              //�������� ��� ������
	    public void deleteStudent(Bet bet) throws SQLException;//������� ������

}
