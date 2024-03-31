import java.sql.*;
import javax.swing.*;

public class Main {
   static Connection myConn;
    public static void main(String[] args) throws SQLException {
        String url="jdbc:mysql://localhost:3306/db";
        String user="root";
        String password="password";
        try{
            myConn=DriverManager.getConnection(url, user, password);
            Statement stm= myConn.createStatement();
            stm.execute("CREATE DATABASE IF NOT EXISTS db");
            stm.execute("USE db");
            stm.execute("CREATE TABLE IF NOT EXISTS students(id INTEGER NOT NULL AUTO_INCREMENT, first_name TEXT,last_name TEXT, PRIMARY KEY(id))");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        new SMFrame();

    }
}