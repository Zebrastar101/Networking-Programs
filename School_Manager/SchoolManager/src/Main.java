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
            Statement stm1= myConn.createStatement();
            stm.execute("CREATE DATABASE IF NOT EXISTS managerschool");
            stm.execute("USE managerschool");
            
            //stm.executeUpdate("INSERT INTO students(first_name, last_name) VALUES('jim','smith');");
            ResultSet teacherRS=stm1.executeQuery("Select*from teacher");
            int i = 0;
            stm.execute("CREATE TABLE IF NOT EXISTS teacher(teacher_id INTEGER NOT NULL AUTO_INCREMENT, first_name TEXT, last_name TEXT, PRIMARY KEY(teacher_id))");
            while(teacherRS!=null && teacherRS.next()){
                if((int) teacherRS.getObject(1)==-1){
                    i=1;
                }
            }
            if(i==0){
                stm.executeUpdate("INSERT INTO teacher(teacher_id, first_name, last_name) VALUES('-1','no teacher','assigned');");
            }

            stm.execute("CREATE TABLE IF NOT EXISTS course(course_id INTEGER NOT NULL AUTO_INCREMENT, title TEXT, type INTEGER, PRIMARY KEY(course_id))");
            stm.execute("CREATE TABLE IF NOT EXISTS student(student_id INTEGER NOT NULL AUTO_INCREMENT, first_name TEXT, last_name TEXT, PRIMARY KEY(student_id))");
            



            stm.execute("CREATE TABLE IF NOT EXISTS section(section_id INTEGER NOT NULL AUTO_INCREMENT, course_id INTEGER, teacher_id INTEGER, PRIMARY KEY(section_id), FOREIGN KEY(course_id) REFERENCES course(course_id) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(teacher_id) REFERENCES teacher(teacher_id) ON DELETE CASCADE ON UPDATE CASCADE)");
            stm.execute("CREATE TABLE IF NOT EXISTS enrollment(section_id INTEGER, student_id INTEGER,FOREIGN KEY(section_id) REFERENCES section(section_id) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(student_id) REFERENCES student(student_id) ON DELETE CASCADE ON UPDATE CASCADE) ;");

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        new SMFrame(myConn);

    }

}