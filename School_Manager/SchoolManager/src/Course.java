import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Course {
    Connection con;
    Statement stm;
    public JTable courseTable;
    ResultSet resultSet;
    public Course(){
        con =Main.myConn;
        //courseTable=new JTable();
        try{
            stm=con.createStatement();
            resultSet=stm.executeQuery("Select*from course WHERE course_id >=1");

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
                if(z!=3){
                    perRow.add(rs.getObject(z));
                }
                else{
                    if((int)rs.getObject(z)==0){
                        perRow.add("Academic");
                    }
                    if((int)rs.getObject(z)==1){
                        perRow.add("KAP");
                    }
                    if((int)rs.getObject(z)==2){
                        perRow.add("AP");
                    }

                }
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
            System.out.println("data for Course table"+Arrays.deepToString(dataArray));
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
    public JTable addCourse(String cn, int type) throws SQLException {
        stm.executeUpdate("INSERT INTO course(title, type) VALUES('"+cn+"','"+type+"');");
        courseTable=buildTable(stm.executeQuery("Select*from course WHERE course_id >=1"));
        return courseTable;

    }
    public JTable deleteCourse(int id) throws SQLException{
        stm.executeUpdate("DELETE FROM course WHERE course_id='"+id+"';");
        courseTable=buildTable(stm.executeQuery("Select*from course"));
        return courseTable;
    }

    public JTable saveCourse(String cn, int type, int id) throws SQLException {
        stm.executeUpdate("UPDATE course SET title='"+cn+"' WHERE course_id="+id+";");
        stm.executeUpdate("UPDATE course SET type='"+type+"' WHERE course_id="+id+";");
        courseTable=buildTable(stm.executeQuery("Select*from course"));
        return courseTable;
    }


    public JTable importFile(Scanner sc) throws SQLException {
        stm.execute("CREATE TABLE IF NOT EXISTS course(course_id INTEGER NOT NULL AUTO_INCREMENT, title TEXT,type TEXT, PRIMARY KEY(course_id))");
        String s = sc.nextLine();
        while(!s.equals("COURSES:")){
            s = sc.nextLine();
        }
        while (sc.hasNextLine()){
            s = sc.nextLine();

            if(!s.isEmpty()){
                String[] parts=s.split(",");
                stm.executeUpdate("INSERT INTO course(title, type) VALUES('"+parts[1]+"','"+parts[2]+"');");
            }
            else {
                courseTable=buildTable(stm.executeQuery("Select*from course"));
                return courseTable;
            }
        }
        return null;
    }





}
