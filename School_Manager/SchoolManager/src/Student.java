import javax.swing.*;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.ArrayList;

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
           studentTable=buildTable(resultSet);
        }catch(SQLException e){
            e.printStackTrace();

        }

    }
    public JTable buildTable(ResultSet rs) throws SQLException {
        //make colums
        int colNum=rs.getMetaData().getColumnCount();
        ArrayList<Object> perRow=new ArrayList<>();
        ArrayList<ArrayList<Object>> data= new ArrayList<ArrayList<Object>>();
        ArrayList<String> colN= new ArrayList<>();

      /*  for(int x=1; x<=colNum;x++){
            colN.add((String) rs.getObject(x));
        }
        //make data*/

        while(rs.next()&&rs!=null){

            for(int z=1; z<=colNum; z++){
                perRow.add(rs.getObject(z));
            }
            data.add(perRow);
        }
        Object[][] dataArray= new Object[data.size()][data.get(0).size()];
        for(int r=0; r< dataArray.length;r++){
            for(int c=0; c<dataArray[0].length;c++){
                dataArray[r][c]=data.get(r).get(c);
                System.out.println(dataArray[r][c]);
            }
        }

        JTable jTable = new JTable(dataArray, new String[]{"Student ID","First Name", "Last Name"});
        return jTable;
    }




    public JTable getStudentTable() {
        return studentTable;
    }

}
