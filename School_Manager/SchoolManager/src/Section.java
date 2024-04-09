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
            resultSet = stm.executeQuery("Select*from sections WHERE id >=1");

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
            System.out.println(Arrays.deepToString(dataArray));
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


    public JTable getStudentTable() {
        return sectionTable;
    }

    public JTable addSection(String teacher, String course) throws SQLException {
        stm.executeUpdate("INSERT INTO sections(teacher_name, course_name) VALUES('"+teacher+"','"+course+"');");
        sectionTable=buildTable(stm.executeQuery("Select*from sections WHERE id >=1"));
        return sectionTable;
    }



    public JTable purgeSection() throws SQLException {
        stm.execute("DROP TABLE IF EXISTS sections;");
        stm.execute("CREATE TABLE IF NOT EXISTS sections(id INTEGER NOT NULL AUTO_INCREMENT, teacher_name TEXT,course_name TEXT, PRIMARY KEY(id))");
        sectionTable=buildTable(stm.executeQuery("Select*from sections"));
        return sectionTable;
    }
}
