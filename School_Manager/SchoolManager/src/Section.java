import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Section {
    Connection con;
    Statement stm;
    public JTable sectionTable;
    ResultSet resultSet;

    public Section() {
        con = Main.myConn;
        //studentTable=new JTable();
        try {
            stm = con.createStatement();
            resultSet = stm.executeQuery("Select*from section WHERE section_id >=1");

            //the below while loop checks if there's elements in the resultSet
            sectionTable = buildTable(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public JTable buildTable(ResultSet rs) throws SQLException {
        con = Main.myConn;
        try{
            Statement stm2 = con.createStatement();
            Statement stm3 = con.createStatement();
            ResultSet teachResultSet;
            ResultSet courseResultSet;
            //make columns
            int colNum = rs.getMetaData().getColumnCount();
            ArrayList<Object> perRow = new ArrayList<>();
            ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();

            /*  for(int x=1; x<=colNum;x++){
            colN.add((String) rs.getObject(x));
            }
            //make data*/

            while (rs != null && rs.next()) {
                teachResultSet = stm2.executeQuery("Select*from teacher WHERE teacher_id >=1");
                courseResultSet = stm3.executeQuery("Select*from course WHERE course_id >=1");
                for (int z = 1; z <= colNum; z++) {
                    if(z==1){
                        perRow.add(rs.getObject(z));
                    }
                    if(z==2){
                        int courseID= (int) rs.getObject(z);
                        while(courseResultSet != null && courseResultSet.next()){
                            if((int)courseResultSet.getObject(1) == courseID){
                                String course = String.valueOf(courseResultSet.getObject(2))+" ("+courseResultSet.getObject(1)+") ";
                                //System.out.println(course);
                                perRow.add(course);
                            }
                        }
                    }
                    if(z==3){
                        int teacherID= (int) rs.getObject(z);
                        while(teachResultSet != null && teachResultSet.next()){
                            if((int)teachResultSet.getObject(1) == teacherID){
                                String teacher = teachResultSet.getObject(2) + " " + teachResultSet.getObject(3)+ "("+teachResultSet.getObject(1)+")";
                                //System.out.println(teacher);
                                perRow.add(teacher);
                            }
                        }
                    }
                }
                data.add(perRow);



                perRow = new ArrayList<>();
            }
            if (data.size() != 0) {
                Object[][] dataArray = new Object[data.size()][data.get(0).size()];
                for (int r = 0; r < dataArray.length; r++) {
                    for (int c = 0; c < dataArray[0].length; c++) {
                        dataArray[r] = data.get(r).toArray();
                        //dataArray[r][c]=data.get(r).get(c);

                    }
                }
                System.out.println("data for Section table"+Arrays.deepToString(dataArray));

                return makeJTable(dataArray);
            }


            return makeJTable(new Object[0][0]);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public JTable makeJTable(Object[][] dataArray) {
        DefaultTableModel tableModel = new DefaultTableModel(dataArray, new String[]{"Section ID", "Course Name", "Teacher Name"}) {

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


    public JTable getSectionTable() {
        return sectionTable;
    }

    public JTable addSection(String teacher, String course) throws SQLException {
        int teacherID= Integer.parseInt(teacher.substring(teacher.indexOf('(')+1,teacher.indexOf(')')));
        int courseID= Integer.parseInt(course.substring(course.indexOf('(')+1,course.indexOf(')')));
        stm.executeUpdate("INSERT INTO section(course_id, teacher_id) VALUES('"+courseID+"','"+teacherID+"');");
        sectionTable=buildTable(stm.executeQuery("Select*from section WHERE section_id >=1"));
        return sectionTable;
    }

    public JTable saveSection(String teacher, String course, int id) throws SQLException {
        int teacherID= Integer.parseInt(teacher.substring(teacher.indexOf('(')+1,teacher.indexOf(')')));
        int courseID= Integer.parseInt(course.substring(course.indexOf('(')+1,course.indexOf(')')));
        stm.executeUpdate("UPDATE section SET teacher_id='"+teacherID+"' WHERE section_id="+id+";");
        stm.executeUpdate("UPDATE section SET course_id='"+courseID+"' WHERE section_id="+id+";");
        sectionTable=buildTable(stm.executeQuery("Select*from section WHERE section_id >=1"));
        return sectionTable;
    }

    public JTable deleteSection(int id) throws SQLException{
        stm.executeUpdate("DELETE FROM section WHERE section_id="+id+";");
        sectionTable=buildTable(stm.executeQuery("Select*from section WHERE section_id >=1"));
        return sectionTable;
    }

    public JTable importFile(Scanner sc) throws SQLException {
        stm.execute("CREATE TABLE IF NOT EXISTS section(section_id INTEGER NOT NULL AUTO_INCREMENT, course_id INTEGER, teacher_id INTEGER, PRIMARY KEY(section_id), FOREIGN KEY(course_id) REFERENCES course(course_id), FOREIGN KEY(teacher_id) REFERENCES teacher(teacher_id))");
        String s = sc.nextLine();
        while(!s.equals("SECTIONS:")){
            s = sc.nextLine();
        }
        while (sc.hasNextLine()){
            s = sc.nextLine();

            if(!s.isEmpty()){
                String[] parts=s.split(",");
                stm.executeUpdate("INSERT INTO section(course_id, teacher_id) VALUES('"+parts[1]+"','"+parts[2]+"');");
            }
        }
        System.out.println("building table");
        sectionTable=buildTable(stm.executeQuery("Select*from section WHERE section_id >=1"));
        return sectionTable;
    }

}
