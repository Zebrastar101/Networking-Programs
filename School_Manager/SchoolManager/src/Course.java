import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Course {
    Connection con;
    Statement stm;
    public JTable courseTable;
    ResultSet resultSet;
    public Course(Connection c){
        con =c;
        //courseTable=new JTable();
        try{
            stm=con.createStatement();
            resultSet=stm.executeQuery("Select*from courses WHERE id >=1");

            //the below while loop checks if there's elements in the resultSet
            courseTable=buildTable(resultSet);

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
        DefaultTableModel tableModel = new DefaultTableModel(dataArray, new String[]{"Course ID","Course Name", "Type"}) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        JTable table = new JTable();
        table.setModel(tableModel);
        table.getTableHeader().setReorderingAllowed(false);

        return table;
    }




    public JTable getCourseTable() {
        return courseTable;
    }
    public JTable addCourse(String cn, String type) throws SQLException {
        stm.executeUpdate("INSERT INTO courses(course_name, type) VALUES('"+cn+"','"+type+"');");
        courseTable=buildTable(stm.executeQuery("Select*from courses WHERE id >=1"));
        return courseTable;

    }
    public JTable deleteStudent(String fn,String ln) throws SQLException{
        stm.executeUpdate("DELETE FROM courses WHERE first_name='"+fn+"'AND last_name='"+ln+"';");
        courseTable=buildTable(stm.executeQuery("Select*from courses"));
        return courseTable;
    }
    public JTable purgeCourse() throws SQLException {
        stm.execute("DROP TABLE IF EXISTS courses;");
        stm.execute("CREATE TABLE IF NOT EXISTS courses(id INTEGER NOT NULL AUTO_INCREMENT, course_name TEXT,type TEXT, PRIMARY KEY(id))");
        courseTable=buildTable(stm.executeQuery("Select*from courses"));
        return courseTable;
    }
    public void exportStudent(){

    }





}
