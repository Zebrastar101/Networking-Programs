import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Student {
    Connection con;
    Statement stm;
    public JTable studentTable;
    ResultSet resultSet;
    public Student(Connection c){
        con =c;
        //studentTable=new JTable();
        try{
            stm=con.createStatement();
            resultSet=stm.executeQuery("Select*from students WHERE id >=1");

            //the below while loop checks if there's elements in the resultSet
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

        while(rs!=null && rs.next()){
            for(int z=1; z<=colNum; z++){
                perRow.add(rs.getObject(z));
            }
            data.add(perRow);


            perRow=new ArrayList<>();
        }
        if(data.size()!=0){
            Object[][] dataArray= new Object[data.size()][data.get(0).size()];
            for(int r=0; r< dataArray.length;r++){
                for(int c=0; c<dataArray[0].length;c++){
                    dataArray[r] = data.get(r).toArray();
                    //dataArray[r][c]=data.get(r).get(c);

                }
            }
            System.out.println(Arrays.deepToString(dataArray));
            return makeJTable(dataArray);
        }


        return makeJTable(new Object[0][0]);
    }

    public JTable makeJTable(Object[][] dataArray){
        DefaultTableModel tableModel = new DefaultTableModel(dataArray, new String[]{"Student ID","First Name", "Last Name"}) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        JTable table = new JTable();
        table.setModel(tableModel);
        table. getTableHeader().setReorderingAllowed(false);

        return table;
    }




    public JTable getStudentTable() {
        return studentTable;
    }
    public JTable addStudent(String fn, String ln) throws SQLException {
        stm.executeUpdate("INSERT INTO students(first_name, last_name) VALUES('"+fn+"','"+ln+"');");
        studentTable=buildTable(stm.executeQuery("Select*from students WHERE id >=1"));
        return studentTable;

    }
    public JTable deleteStudent(String fn,String ln) throws SQLException{
        //Deleting Rows:
        //DELETE FROM table_name WHERE comparisons;
        //Example:
        //DELTE FROM student WHERE student_id=6 OR last_name=’Smith’;
        stm.executeUpdate("DELETE FROM students WHERE first_name='"+fn+"'AND last_name='"+ln+"';");
        studentTable=buildTable(stm.executeQuery("Select*from students"));
        return studentTable;
    }
    public JTable purgeStudent() throws SQLException {
        stm.execute("DROP TABLE IF EXISTS students;");
        stm.execute("CREATE TABLE IF NOT EXISTS students(id INTEGER NOT NULL AUTO_INCREMENT, first_name TEXT,last_name TEXT, PRIMARY KEY(id))");
        studentTable=buildTable(stm.executeQuery("Select*from students"));
        return studentTable;
    }
    public void exportStudent(){

    }





}
