import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.ArrayList;

public class Student {
    /*
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
           studentTable.setModel((TableModel) buildTable(resultSet));
        }catch(SQLException e){
            throw  new RuntimeException(e);

        }

    }
    public JTable buildTable(ResultSet rs) throws SQLException {
        //make colums
        int colNum=rs.getMetaData().getColumnCount();
        ArrayList<String> colN= new ArrayList<>();
        for(int x=1; x<=colNum;x++){
            colN.add((String) rs.getObject(x));
        }
        //make data
        ArrayList<ArrayList<Object>> data= new ArrayList<ArrayList<Object>>();
        while(rs.next()){
            ArrayList<Object> perRow=new ArrayList<>();
            for(int z=1; z<=colNum; z++){
                perRow.add(rs.getObject(z));
            }
            data.add(perRow);
        }
        return new JTable((TableModel) data, (TableColumnModel) colN);
    }

    public JTable getStudentTable() {
        return studentTable;
    }

     */

}
