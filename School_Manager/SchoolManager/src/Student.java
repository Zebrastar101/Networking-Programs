import javax.swing.*;
import java.sql.*;

public class Student {
    Connection con;
    Statement stm;
    public JTable studentTable;
    ResultSet resultSet;
    public Student(Connection c){
        con =c;
        studentTable=new JTable();
        try{
            stm=con.createStatement();
            stm.execute("USE managerschool");
            resultSet=stm.executeQuery("Select*from students");
        }catch(SQLException e){
            throw new RuntimeException(e);

        }

    }


}
