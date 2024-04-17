import java.sql.*;
import javax.swing.*;

public class Main {
    public static Connection myConn;
    public static void main(String[] args) throws SQLException {
        String url="jdbc:mysql://localhost:3306/managerschool";
        String user="root";
        String password="password";
        try{
            myConn=DriverManager.getConnection(url, user, password);
            Statement stm= myConn.createStatement();
            stm.execute("CREATE DATABASE IF NOT EXISTS managerschool");
            stm.execute("USE managerschool");
            stm.execute("CREATE TABLE IF NOT EXISTS students(id INTEGER NOT NULL AUTO_INCREMENT, first_name TEXT,last_name TEXT, PRIMARY KEY(id))");
            //stm.executeUpdate("INSERT INTO students(first_name, last_name) VALUES('jim','smith');");
            stm.execute("CREATE TABLE IF NOT EXISTS teacher(teacher_id INTEGER NOT NULL AUTO_INCREMENT, first_name TEXT,last_name TEXT, PRIMARY KEY(teacher_id))");
            stm.execute("CREATE TABLE IF NOT EXISTS course(course_id INTEGER NOT NULL AUTO_INCREMENT, title TEXT,type TEXT, PRIMARY KEY(course_id))");
            stm.execute("CREATE TABLE IF NOT EXISTS sections(id INTEGER NOT NULL AUTO_INCREMENT, teacher_name TEXT,course_name TEXT, PRIMARY KEY(id))");

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        new SMFrame(myConn);

    }

}