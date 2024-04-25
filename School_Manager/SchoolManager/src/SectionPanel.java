import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class SectionPanel extends JPanel{

    JLabel panelTitleLabel = new JLabel("Sections");
    JLabel teacherLabel = new JLabel("Teacher: ");
    ArrayList<ArrayList<Object>> fullData;
    ArrayList<String>tableList;
    JLabel courseLabel = new JLabel("Course: ");
    JScrollPane jscrollEnroll;

    JComboBox<String> teachersDropDown = new JComboBox<String>();
    JComboBox<String> coursesDropDown = new JComboBox<String>();
    JComboBox<String> studentsDropDown = new JComboBox<String>();

    JTable sectionTable;

    JScrollPane jScrollPane;
    ArrayList<String> dropList;

    JButton newButton = new JButton("New");
    JButton saveButton = new JButton("Save");
    JButton deleteButton = new JButton("Delete");
    JButton addStudentButton = new JButton("Add Student");
    JButton removeStudentButton = new JButton("Remove Student");

    Section sec;

    Connection con;
    Statement stm;
    ResultSet teacherResultSet;
    ResultSet courseResultSet;
    JTable enrollment=new JTable();
    JLabel studentLab= new JLabel("Student: ");

    JLabel rosterLab= new JLabel("Roster");

    

    public SectionPanel() throws SQLException {


        setLayout(null);
        setBounds(15, 40, 950, 630);
        setBorder(BorderFactory.createLineBorder(Color.black));

        panelTitleLabel.setBounds(15, 5, 100, 35);
        panelTitleLabel.setFont(new Font("Calibri", Font.BOLD, 23));
        add(panelTitleLabel);

        teacherLabel.setBounds(120, 80, 250, 20);
        teacherLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        add(teacherLabel);

        teachersDropDown.setBounds(260, 80, 230, 20);
        add(teachersDropDown);

        courseLabel.setBounds(120, 110, 250, 20);
        courseLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        add(courseLabel);


        rosterLab.setBounds(725, 40, 100,35);
        rosterLab.setFont(new Font("Calibri", Font.BOLD, 20));
        add(rosterLab);

        studentLab.setBounds(630, 310, 70,20);
        studentLab.setFont(new Font("Calibri", Font.BOLD, 15));
        add(studentLab);

        coursesDropDown.setBounds(260, 110, 230, 20);
        add(coursesDropDown);

        studentsDropDown.setBounds(720, 310, 150, 20);
        add(studentsDropDown);



        //buttons




        newButton.setBounds(120, 140, 70, 20);
        newButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(newButton);
        newButton.addActionListener(e -> {
            try {
                newSection((String) teachersDropDown.getSelectedItem(), (String) coursesDropDown.getSelectedItem());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            fullData=new ArrayList<ArrayList<Object>>();
            try {
                fullData=makeFullData(fullData);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
           
            ArrayList<String> blank = new ArrayList<>();
            enrollment=buildEnrollMentTable(blank);
            jscrollEnroll= new JScrollPane(enrollment);
            jscrollEnroll.setBounds(630,80,250, 200);
            add(jscrollEnroll);


        });

        saveButton.setBounds(270, 140, 70, 20);
        saveButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(saveButton);
        saveButton.addActionListener(e -> {
            try {
                if(!sectionTable.getSelectionModel().isSelectionEmpty()){
                    saveSectionChanges((String) teachersDropDown.getSelectedItem(), (String) coursesDropDown.getSelectedItem(), (Integer) sectionTable.getValueAt(sectionTable.getSelectedRow() , 0));
                }
                else{
                    int errorMessage = JOptionPane.showConfirmDialog(null, "No section was selected", "Error", JOptionPane.OK_CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        deleteButton.setBounds(420, 140, 70, 20);
        deleteButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(deleteButton);
        deleteButton.addActionListener(e-> {
            try {
                if(!sectionTable.getSelectionModel().isSelectionEmpty()){
                    delSection((Integer) sectionTable.getValueAt(sectionTable.getSelectedRow() , 0));
                }
                else{
                    int errorMessage = JOptionPane.showConfirmDialog(null, "No section was selected", "Error", JOptionPane.OK_CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        addStudentButton.setBounds(630, 340, 110, 20);
        addStudentButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(addStudentButton);
        addStudentButton.addActionListener(e -> {
            try {
                String v=(String) studentsDropDown.getSelectedItem();
                String turn ="";
                if(v!=null&&!sectionTable.getSelectionModel().isSelectionEmpty()){
                    for(int x=0; x<v.length(); x++){
                        if(v.charAt(x)=='('){
                            for(int z=0; z<v.length(); z++){
                                if(v.charAt(z+1)!=')'){
                                    turn+=v.charAt(z+1);
                                }
                                else{
                                    break;
                                }
                            }
                        }
                        else{
                            break;
                        }
                    }

                    addStudent(Integer.parseInt(turn),(int) sectionTable.getValueAt(sectionTable.getSelectedRow(), 0));
                }
                else{
                    int errorMessage = JOptionPane.showConfirmDialog(null, "No students to add or no selected section", "Error", JOptionPane.OK_CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        removeStudentButton.setBounds(760, 340, 110, 20);
        removeStudentButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(removeStudentButton);
        removeStudentButton.addActionListener(e -> {

                if(!enrollment.getSelectionModel().isSelectionEmpty()){
                    try {
                        String v=(String) enrollment.getValueAt(enrollment.getSelectedRow(), 0);
                        String turn ="";
                        for(int x=0; x<v.length(); x++){
                            if(v.charAt(x)=='('){
                                for(int z=0; z<v.length(); z++){
                                    if(v.charAt(z+1)!=')'){
                                    turn+=v.charAt(z+1);
                                    }
                                    else{
                                        break;
                                    }
                                }
                            }
                            else{
                                break;
                            }
                        }

                    deleteStudent(Integer.parseInt(turn),(int) sectionTable.getValueAt(sectionTable.getSelectedRow(), 0));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                }
                else{
                    int errorMessage = JOptionPane.showConfirmDialog(null, "No student was selected", "Error", JOptionPane.OK_CANCEL_OPTION);
                }

        });





        sec = new Section();
        sectionTable=sec.getSectionTable();
        //below from https://www.tabnine.com/code/java/methods/javax.swing.JTable/getSelectedRow
        fullData=new ArrayList<ArrayList<Object>>();
        fullData=makeFullData(fullData);
        sectionTable.addMouseListener(new MouseAdapter() {
            //Idk how to get the selected values to pop up for this one
            public void mouseClicked(MouseEvent e) {

                teachersDropDown.setSelectedItem((String) sectionTable.getValueAt(sectionTable.getSelectedRow(), 1));
                coursesDropDown.setSelectedItem((String) sectionTable.getValueAt(sectionTable.getSelectedRow(), 2));
                int secID = (int) sectionTable.getValueAt(sectionTable.getSelectedRow(), 0);

                ArrayList<String> tb = new ArrayList<>();
                ArrayList<String> idList = new ArrayList<>();
                try {
                    fullData = makeFullData(fullData);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                int track = 0;
                for(int z=0; z<fullData.size();z++){
                    if(secID==Integer.parseInt(fullData.get(z).get(0).toString())){
                        track+=1;
                        break;
                    }
                }
                if(track!=0){
                    if (fullData.size() != 0) {
                        for (int x = 0; x < fullData.size(); x++) {

                            if (secID == (int) fullData.get(x).get(0)) {
                                for (int z = 1; z < fullData.get(x).size(); z++) {
                                    try {
                                        tb.add(findStudent((int) fullData.get(x).get(z)));
                                        idList.add( fullData.get(x).get(z).toString());
                                    } catch (SQLException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                enrollment = buildEnrollMentTable(tb);
                reloadStudentsTable(idList);
                jscrollEnroll.setViewportView(enrollment);


            }
        });
        jScrollPane = new JScrollPane(sectionTable);
        jScrollPane.setBounds(50,190,500, 400);
        add(jScrollPane);

        ArrayList<String> blank = new ArrayList<>();
        enrollment=buildEnrollMentTable(blank);
        jscrollEnroll= new JScrollPane(enrollment);
        jscrollEnroll.setBounds(630,80,250, 200);
        add(jscrollEnroll);


    }

    public void reload()
    {
        con = Main.myConn;
        try{
            stm=con.createStatement();
            teachersDropDown.removeAllItems();
            coursesDropDown.removeAllItems();
            teacherResultSet=stm.executeQuery("Select*from teacher WHERE teacher_id >=1");
            while(teacherResultSet!=null && teacherResultSet.next()){
                String teacher = teacherResultSet.getObject(2) + " " + teacherResultSet.getObject(3)+ "("+teacherResultSet.getObject(1)+")";
                teachersDropDown.addItem(teacher);
            }
            courseResultSet=stm.executeQuery("Select*from course WHERE course_id >=1");
            while(courseResultSet!=null && courseResultSet.next()){
                String course = String.valueOf(courseResultSet.getObject(2))+" ("+courseResultSet.getObject(1)+") ";
                coursesDropDown.addItem(course);
            }

        }catch(SQLException e){
            e.printStackTrace();

        }

    }


    public void newSection(String teacher, String course) throws SQLException {
        sectionTable=sec.addSection(teacher, course);
        jScrollPane.setViewportView(sectionTable);
        sectionTable.addMouseListener(new MouseAdapter() {
            //Idk how to get the selected values to pop up for this one
            public void mouseClicked(MouseEvent e) {

                teachersDropDown.setSelectedItem((String) sectionTable.getValueAt(sectionTable.getSelectedRow(), 1));
                coursesDropDown.setSelectedItem((String) sectionTable.getValueAt(sectionTable.getSelectedRow(), 2));
                int secID = (int) sectionTable.getValueAt(sectionTable.getSelectedRow(), 0);

                ArrayList<String> tb = new ArrayList<>();
                ArrayList<String> idList = new ArrayList<>();
                try {
                    fullData = makeFullData(fullData);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                int track = 0;
                for(int z=0; z<fullData.size();z++){
                    if(secID==Integer.parseInt(fullData.get(z).get(0).toString())){
                        track+=1;
                        break;
                    }
                }
                if(track!=0){
                    if (fullData.size() != 0) {
                        for (int x = 0; x < fullData.size(); x++) {

                            if (secID == (int) fullData.get(x).get(0)) {
                                for (int z = 1; z < fullData.get(x).size(); z++) {
                                    try {
                                        tb.add(findStudent((int) fullData.get(x).get(z)));
                                        idList.add( fullData.get(x).get(z).toString());
                                    } catch (SQLException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                enrollment = buildEnrollMentTable(tb);
                reloadStudentsTable(idList);
                jscrollEnroll.setViewportView(enrollment);


            }});
    }


    public void saveSectionChanges(String teacher, String course, int id) throws SQLException {
        sectionTable=sec.saveSection(teacher, course, id);
        jScrollPane.setViewportView(sectionTable);
    }

    public void delSection(int id) throws SQLException {
        sectionTable=sec.deleteSection(id);
        jScrollPane.setViewportView(sectionTable);
        sectionTable.addMouseListener(new MouseAdapter() {
                                          //Idk how to get the selected values to pop up for this one
                                          public void mouseClicked(MouseEvent e) {

                                              teachersDropDown.setSelectedItem((String) sectionTable.getValueAt(sectionTable.getSelectedRow(), 1));
                                              coursesDropDown.setSelectedItem((String) sectionTable.getValueAt(sectionTable.getSelectedRow(), 2));
                                              int secID = (int) sectionTable.getValueAt(sectionTable.getSelectedRow(), 0);

                                              ArrayList<String> tb = new ArrayList<>();
                                              ArrayList<String> idList = new ArrayList<>();
                                              try {
                                                  fullData = makeFullData(fullData);
                                              } catch (SQLException ex) {
                                                  throw new RuntimeException(ex);
                                              }
                                              int track = 0;
                                              for(int z=0; z<fullData.size();z++){
                                                  if(secID==Integer.parseInt(fullData.get(z).get(0).toString())){
                                                      track+=1;
                                                      break;
                                                  }
                                              }
                                              if(track!=0){
                                                  if (fullData.size() != 0) {
                                                      for (int x = 0; x < fullData.size(); x++) {

                                                          if (secID == (int) fullData.get(x).get(0)) {
                                                              for (int z = 1; z < fullData.get(x).size(); z++) {
                                                                  try {
                                                                      tb.add(findStudent((int) fullData.get(x).get(z)));
                                                                      idList.add( fullData.get(x).get(z).toString());
                                                                  } catch (SQLException ex) {
                                                                      throw new RuntimeException(ex);
                                                                  }
                                                              }
                                                              break;
                                                          }
                                                      }
                                                  }
                                              }
                                              enrollment = buildEnrollMentTable(tb);
                                              reloadStudentsTable(idList);
                                              jscrollEnroll.setViewportView(enrollment);


                                          }});
    }

    public void deleteTeacher(int id) throws SQLException {
        sectionTable=sec.deletedTeacher(id);
        jScrollPane.setViewportView(sectionTable);
    }



    public void reloadSectionTable() throws SQLException {
        ResultSet sectionRS=stm.executeQuery("Select*from section WHERE section_id >=1");
        sectionTable=sec.buildTable(sectionRS);
        jScrollPane.setViewportView(sectionTable);
    }

    //ALLL THE ENROLLMENT STUFF
    public void reloadStudentsTable(ArrayList<String> tb)
    {
        con = Main.myConn;

        try{
            stm=con.createStatement();
            studentsDropDown.removeAllItems();
            ResultSet studentRS=stm.executeQuery("Select*from student WHERE student_id >=1");
            ArrayList<Object> studs=new ArrayList<>();
            while(studentRS!=null && studentRS.next()){
                studs.add(studentRS.getObject(1));

            }
            //Collections.sort(studs);
            //Collections.sort(tb);

            dropList=new ArrayList<>();
            int same=0;
            for(int x=0; x<studs.size();x++){
                String val= studs.get(x).toString();
                same=0;
                for(int z=0; z<tb.size(); z++){
                    //System.out.println(tb);
                    if(val.equals(tb.get(z))){
                        
                        same+=1;
                    }
                }
                if(same==0){
                    dropList.add(val);
                }

            }

            for(int c=0; c<dropList.size(); c++){
                ResultSet sRS=stm.executeQuery("Select*from student WHERE student_id >=1");
                while(sRS!=null && sRS.next()){

                    String v=sRS.getObject(1).toString();

                    if(v.equals(dropList.get(c)) ){

                        String student = "("+sRS.getObject(1)+")"+sRS.getObject(2) + " " + sRS.getObject(3);

                        studentsDropDown.addItem(student);
                    }

                }
            }





        }catch(SQLException e){
            e.printStackTrace();

        }
    }
    public void addStudent(int student, int sectionID) throws SQLException {
        con = Main.myConn;
        try{
            stm=con.createStatement();
            //insert into enrollment table
            ArrayList<String> tb=new ArrayList<>();
            ArrayList<String> idList=new ArrayList<>();
            ResultSet enrollRs= stm.executeQuery("Select*from enrollment WHERE section_id >=1");
           /* while(enrollRs.next()){
                if(Integer.parseInt(String.valueOf(enrollRs.getObject(1)))==sectionID){
                    System.out.println(" Reached sectionId="+ sectionID+"      studentID="+student);
                    stm.executeUpdate("INSERT INTO enrollment(section_id, student_id) VALUES('"+sectionID+"','"+student+"');");
                    break;
                }
            }*/
            stm.executeUpdate("INSERT INTO enrollment(section_id, student_id) VALUES('"+sectionID+"','"+student+"');");
            fullData=makeFullData(fullData);
            //tb=getTableData(enrollment);
            for(int x=0; x<fullData.size();x++){
                if(sectionID==Integer.parseInt(fullData.get(x).get(0).toString()) ){
                    for(int z=1; z<fullData.get(x).size(); z++){
                        /*if(student==Integer.valueOf(fullData.get(x).get(z).toString())){
                            tb.add(findStudent((int) fullData.get(x).get(z)));
                            idList.add( fullData.get(x).get(z).toString());
                            break;
                        }*/
                        tb.add(findStudent((int) fullData.get(x).get(z)));
                        idList.add( fullData.get(x).get(z).toString());

                    }

                }
            }
            enrollment=buildEnrollMentTable(tb);
            reloadStudentsTable(idList);
            jscrollEnroll.setViewportView(enrollment);
        }
        catch(SQLException e){
            e.printStackTrace();

        }
    }
    public void deleteStudent(int student, int sectionID) throws SQLException {
        ArrayList<String> tb=new ArrayList<>();
        ArrayList<String> idList=new ArrayList<>();
        stm.executeUpdate("DELETE FROM enrollment WHERE section_id='"+sectionID+"' AND student_id='"+student+"';");

        for(int x=0; x<fullData.size();x++){
            if(sectionID==(int) fullData.get(x).get(0)){
                for(int z=1; z<fullData.get(x).size();z++){
                    if(student==Integer.parseInt(fullData.get(x).get(z).toString())){
                        fullData.get(x).remove(z);
                    }
                }
                for(int z=1; z<fullData.get(x).size(); z++){
                    tb.add(findStudent((int) fullData.get(x).get(z)));
                    idList.add( fullData.get(x).get(z).toString());
                }
                break;
            }
        }

        enrollment=buildEnrollMentTable(tb);
        reloadStudentsTable(idList);
        jscrollEnroll.setViewportView(enrollment);
    }
    
    public JTable buildEnrollMentTable( ArrayList<String> tb){
        String[][] dataArray= new String[tb.size()][1];
        String[] colNames={"Students"};
            for(int c=0; c<dataArray.length;c++){
                dataArray[c][0] = tb.get(c);
                //dataArray[r][c]=data.get(r).get(c);

            }




        JTable table = new JTable(dataArray,colNames);


        return table;


    }
    public ArrayList<ArrayList<Object>> makeFullData(ArrayList<ArrayList<Object>> fd) throws SQLException {
        con = Main.myConn;
         stm=con.createStatement();
        ResultSet sectionRS=stm.executeQuery("Select*from enrollment WHERE section_id >=1");
        ArrayList<Object> perRow = new ArrayList<>();
        int same=0;
        while (sectionRS != null && sectionRS.next()) {


                same=0;
                for(int x=0; x<fd.size();x++){
                        if(sectionRS.getObject(1)==fd.get(x).get(0)){
                            same+=1;
                        }
                }

                if(same==0){
                    perRow.add(sectionRS.getObject(1));
                    fd.add(perRow);
                }



            perRow = new ArrayList<>();
        }
        ResultSet sRS=stm.executeQuery("Select*from enrollment WHERE section_id >=1");
        while(sRS!=null&&sRS.next()){

                for(int x=0; x<fd.size();x++){
                    if(sRS.getObject(1).equals(fd.get(x).get(0))){
                        int s=0;
                        for( int b=1; b<fd.get(x).size(); b++){
                            if(sRS.getObject(2)==fd.get(x).get(b)){
                                s+=1;
                            }
                        }
                        if(s==0){
                            fd.get(x).add(sRS.getObject(2));

                        }

                    }
                }

        }
        return fd;
    }

    public String findStudent(int id) throws SQLException {
        stm=con.createStatement();
        ResultSet studentRS=stm.executeQuery("Select*from student WHERE student_id >=1");
        while (studentRS!=null && studentRS.next()){
            if(id==(int)studentRS.getObject(1)){
                return "("+studentRS.getObject(1)+")"+studentRS.getObject(2) + " " + studentRS.getObject(3);
            }
        }
        return null;
    }
    public ArrayList<String> getTableData(JTable table){
        int nRow = table.getRowCount();
        ArrayList<String> tableData=new ArrayList<>();
        for (int j = 0 ; j < nRow ; j++){
                tableData.add(String.valueOf(table.getValueAt(j,0)));}
        return tableData;

    }

    public void fileImport(Scanner sc) throws SQLException {
        sectionTable=sec.importFile(sc);
        jScrollPane.setViewportView(sectionTable);
    }

    public void importFileEnrollment(Scanner sc) throws SQLException {
        stm.execute("CREATE TABLE IF NOT EXISTS enrollment(section_id INTEGER, student_id INTEGER,FOREIGN KEY(section_id) REFERENCES section(section_id) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(student_id) REFERENCES student(student_id) ON DELETE CASCADE ON UPDATE CASCADE) ;");
        String s = sc.nextLine();
        while(!s.equals("ENROLLMENT:")){
            s = sc.nextLine();
        }
        while (sc.hasNextLine()){
            s = sc.nextLine();

            if(!s.isEmpty()){
                System.out.println(s);
                String[] parts=s.split(",");
                System.out.println(parts[0]);
                System.out.println(parts[1]);
                stm.executeUpdate("INSERT INTO enrollment(section_id, student_id) VALUES('"+parts[0]+"','"+parts[1]+"');");
            }
            else {
                sectionTable.addMouseListener(new MouseAdapter() {
                    //Idk how to get the selected values to pop up for this one
                    public void mouseClicked(MouseEvent e) {

                        teachersDropDown.setSelectedItem((String) sectionTable.getValueAt(sectionTable.getSelectedRow(), 1));
                        coursesDropDown.setSelectedItem((String) sectionTable.getValueAt(sectionTable.getSelectedRow(), 2));
                        int secID = (int) sectionTable.getValueAt(sectionTable.getSelectedRow(), 0);

                        ArrayList<String> tb = new ArrayList<>();
                        ArrayList<String> idList = new ArrayList<>();
                        try {
                            fullData = makeFullData(fullData);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        int track = 0;
                        for(int z=0; z<fullData.size();z++){
                            if(secID==Integer.parseInt(fullData.get(z).get(0).toString())){
                                track+=1;
                                break;
                            }
                        }
                        if(track!=0){
                            if (fullData.size() != 0) {
                                for (int x = 0; x < fullData.size(); x++) {

                                    if (secID == (int) fullData.get(x).get(0)) {
                                        for (int z = 1; z < fullData.get(x).size(); z++) {
                                            try {
                                                tb.add(findStudent((int) fullData.get(x).get(z)));
                                                idList.add( fullData.get(x).get(z).toString());
                                            } catch (SQLException ex) {
                                                throw new RuntimeException(ex);
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        enrollment = buildEnrollMentTable(tb);
                        reloadStudentsTable(idList);
                        jscrollEnroll.setViewportView(enrollment);


                    }
                });
                ArrayList<String> tb = new ArrayList<>();
                ArrayList<String> idList = new ArrayList<>();
                try {
                    fullData = makeFullData(fullData);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                enrollment = buildEnrollMentTable(tb);
                reloadStudentsTable(idList);
                jscrollEnroll.setViewportView(enrollment);
                break;
            }
        }
    }

    public void changeFullData(){
        try{
        fullData=makeFullData(fullData);}
        catch(SQLException e){
            e.printStackTrace();

        }
        sectionTable.addMouseListener(new MouseAdapter() {
            //Idk how to get the selected values to pop up for this one
            public void mouseClicked(MouseEvent e) {

                teachersDropDown.setSelectedItem((String) sectionTable.getValueAt(sectionTable.getSelectedRow(), 1));
                coursesDropDown.setSelectedItem((String) sectionTable.getValueAt(sectionTable.getSelectedRow(), 2));
                int secID = (int) sectionTable.getValueAt(sectionTable.getSelectedRow(), 0);

                ArrayList<String> tb = new ArrayList<>();
                ArrayList<String> idList = new ArrayList<>();
                try {
                    fullData = makeFullData(fullData);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                int track = 0;
                for(int z=0; z<fullData.size();z++){
                    if(secID==Integer.parseInt(fullData.get(z).get(0).toString())){
                        track+=1;
                        break;
                    }
                }
                if(track!=0){
                    if (fullData.size() != 0) {
                        for (int x = 0; x < fullData.size(); x++) {

                            if (secID == (int) fullData.get(x).get(0)) {
                                for (int z = 1; z < fullData.get(x).size(); z++) {
                                    try {
                                        tb.add(findStudent((int) fullData.get(x).get(z)));
                                        idList.add( fullData.get(x).get(z).toString());
                                    } catch (SQLException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                enrollment = buildEnrollMentTable(tb);
                reloadStudentsTable(idList);
                jscrollEnroll.setViewportView(enrollment);


            }});
        ArrayList<String> tb = new ArrayList<>();
        ArrayList<String> idList = new ArrayList<>();

        enrollment = buildEnrollMentTable(tb);
        reloadStudentsTable(idList);
        jscrollEnroll.setViewportView(enrollment);
    }

}

