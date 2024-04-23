import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Teacher  {
    Connection con;
    Statement stm;
    public JTable teacherTable;
    ResultSet resultSet;
    public Teacher(){
        con =Main.myConn;
        //studentTable=new JTable();
        try{
            stm=con.createStatement();
            resultSet=stm.executeQuery("Select*from teacher WHERE teacher_id >=1");

            //the below while loop checks if there's elements in the resultSet
            teacherTable=buildTable(resultSet);

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
            System.out.println("data for Teacher table"+Arrays.deepToString(dataArray));
            return makeJTable(dataArray);
        }


        return makeJTable(new Object[0][0]);
    }

    public JTable makeJTable(Object[][] dataArray){
        DefaultTableModel tableModel = new DefaultTableModel(dataArray, new String[]{"Teacher ID","First Name", "Last Name"}) {

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




    public JTable getTeacherTable() {
        return teacherTable;
    }
    public JTable addTeacher(String fn, String ln) throws SQLException {
        stm.executeUpdate("INSERT INTO teacher(first_name, last_name) VALUES('"+fn+"','"+ln+"');");
        teacherTable=buildTable(stm.executeQuery("Select*from teacher WHERE teacher_id >=1"));
        return teacherTable;

    }


    public JTable deleteTeacher(int id) throws SQLException{
        stm.executeUpdate("DELETE FROM teacher WHERE teacher_id='"+id+"';");

        teacherTable=buildTable(stm.executeQuery("Select*from teacher WHERE teacher_id >=1"));
        return teacherTable;
    }

    public JTable saveTeacher(String fn, String ln, int id) throws SQLException {
        stm.executeUpdate("UPDATE teacher SET first_name='"+fn+"' WHERE teacher_id="+id+";");
        stm.executeUpdate("UPDATE teacher SET last_name='"+ln+"' WHERE teacher_id="+id+";");
        teacherTable=buildTable(stm.executeQuery("Select*from teacher WHERE teacher_id >=1"));
        return teacherTable;
    }



    public JTable importFile(Scanner sc) throws SQLException {
        stm.execute("CREATE TABLE IF NOT EXISTS teacher(teacher_id INTEGER NOT NULL AUTO_INCREMENT, first_name TEXT,last_name TEXT, PRIMARY KEY(teacher_id))");
        stm.executeUpdate("INSERT INTO teacher(teacher_id, first_name, last_name) VALUES('-1','no teacher','assigned');");
        String s = sc.nextLine();
        while(!s.equals("TEACHERS:")){
            s = sc.nextLine();
        }
        while (sc.hasNextLine()){
            s = sc.nextLine();

            if(!s.isEmpty()){
                String[] parts=s.split(",");
                stm.executeUpdate("INSERT INTO teacher(first_name, last_name) VALUES('"+parts[1]+"','"+parts[2]+"');");
            }
            else {
                teacherTable=buildTable(stm.executeQuery("Select*from teacher WHERE teacher_id >=1"));
                return teacherTable;
            }
        }
        return null;
    }







}
