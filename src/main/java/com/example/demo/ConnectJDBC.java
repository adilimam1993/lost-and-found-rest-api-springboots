package com.example.demo;
import java.sql.*;


/**
 * Created by Adil Imam on 8/12/2017.
 */
public class ConnectJDBC {

  public static Connection getConnection(){

      String JDBC_DRIVER = "com.mysql.jdbc.Driver";
      String DB_URL = "jdbc:mysql://34.239.227.198:3306/lost_and_found?autoReconnect=true";
      //  Database credentials
      String USER = "adil";
      String PASS = "rafa2012";
      Connection conn = null;

      try{
          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection(DB_URL, USER, PASS);
      }
      catch(SQLException se){
          se.printStackTrace();
      }
      catch(Exception e){
          e.printStackTrace();
      }

      return conn;
  }

}
