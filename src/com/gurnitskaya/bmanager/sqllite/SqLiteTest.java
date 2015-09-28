package com.gurnitskaya.bmanager.sqllite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SqLiteTest {

//	public static void main(String[] args) {
//		Connection c = null;
//	    Statement stmt = null;
//	    try {
//	      Class.forName("org.sqlite.JDBC");
//	      c = DriverManager.getConnection("jdbc:sqlite:src//database//mongo.db");
//	      System.out.println("Opened database successfully");
//
//	      stmt = c.createStatement();
//	      String sql = "CREATE TABLE Bet " +
//	                   "(ID INT PRIMARY KEY     NOT NULL," +
//	                   " date           DATE    NOT NULL, " + 
//	                   " homeCommand    TEXT    NOT NULL, " + 
//	                   " guestCommand    TEXT    NOT NULL, " + 
//	                   " type    TEXT    NOT NULL, " +
//	                   " value    INTEGER    NOT NULL, " +
//	                   " koef    DOUBLE    NOT NULL, " +
//	                   " result    DOUBLE    NOT NULL, " +
//	                   " gameResult    TEXT    NOT NULL) ";
//
//	      stmt.executeUpdate(sql);
//	      stmt.close();
//	      c.close();
//	    } catch ( Exception e ) {
//	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//	      System.exit(0);
//	    }
//	    System.out.println("Table created successfully");
//	}

}
