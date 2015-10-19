package com.gurnitskaya.bmanager.dao;

import java.sql.SQLException;
import java.util.List;

import com.gurnitskaya.bmanager.beans.Command;

public interface CommandDAO {
	public void addCommand(Command command) throws SQLException; 

	public void updateCommand(Command command) throws SQLException;

	public Command getCommandById(Long id) throws SQLException; 

	public List<Command> getAllCommands() throws SQLException; 

	public void deleteCommand(Command command) throws SQLException;
}
