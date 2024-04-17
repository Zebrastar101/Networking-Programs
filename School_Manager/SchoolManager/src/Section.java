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

    public Section(Connection c) {
        con = c;
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
        //make colums
        int colNum = rs.getMetaData().getColumnCount();
        ArrayList<Object> perRow = new ArrayList<>();
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
        ArrayList<String> colN = new ArrayList<>();

      /*  for(int x=1; x<=colNum;x++){
            colN.add((String) rs.getObject(x));
        }
        //make data*/

        while (rs != null && rs.next()) {
            for (int z = 1; z <= colNum; z++) {
                perRow.add(rs.getObject(z));
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

    public JTable makeJTable(Object[][] dataArray) {
        DefaultTableModel tableModel = new DefaultTableModel(dataArray, new String[]{"Section ID", "Teacher Name", "Course Name"}) {

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
        stm.executeUpdate("UPDATE section SET teacher_name='"+teacher+"' WHERE section_id="+id+";");
        stm.executeUpdate("UPDATE section SET course_name='"+course+"' WHERE section_id="+id+";");
        sectionTable=buildTable(stm.executeQuery("Select*from section WHERE section_id >=1"));
        return sectionTable;
    }

    public JTable deleteSection(int id) throws SQLException{
        stm.executeUpdate("DELETE FROM section WHERE section_id="+id+";");
        sectionTable=buildTable(stm.executeQuery("Select*from section WHERE section_id >=1"));
        return sectionTable;
    }



    public JTable purgeSection() throws SQLException {
        stm.execute("DROP TABLE IF EXISTS section;");
        stm.execute("CREATE TABLE IF NOT EXISTS section(section_id INTEGER NOT NULL AUTO_INCREMENT, teacher_name TEXT,course_name TEXT, PRIMARY KEY(section_id))");
        sectionTable=buildTable(stm.executeQuery("Select*from section WHERE section_id >=1"));
        return sectionTable;
    }

    public JTable importFile(Scanner sc) throws SQLException {
        stm.execute("DROP TABLE IF EXISTS section;");
        stm.execute("CREATE TABLE IF NOT EXISTS section(section_id INTEGER NOT NULL AUTO_INCREMENT, teacher_name TEXT,course_name TEXT, PRIMARY KEY(section_id))");
        String s = sc.nextLine();
        while(!s.equals("SECTIONS:")){
            s = sc.nextLine();
        }
        while (sc.hasNextLine()){
            s = sc.nextLine();

            if(!s.isEmpty()){
                String[] parts=s.split(",");
                stm.executeUpdate("INSERT INTO section(teacher_name, course_name) VALUES('"+parts[1]+"','"+parts[2]+"');");
            }
        }
        System.out.println("building table");
        sectionTable=buildTable(stm.executeQuery("Select*from section WHERE section_id >=1"));
        return sectionTable;
    }

}
