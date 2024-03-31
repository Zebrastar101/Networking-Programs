import javax.swing.*;
import java.sql.*;

public class Student {
    Connection con;
    Statement stm;
    public JTable studentTable;
    public Student(Connection c){
        con =c;
        studentTable=new JTable();

    }
    

}
