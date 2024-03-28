public class Student {
    private int studentID;
    private String studentFirst_name;
    private String studentLast_name;
    public Student(int id, String first_name, String last_name){
        this.studentID=id;
        this.studentFirst_name=first_name;
        this.studentLast_name=last_name;
    }
    public int getStudentId(){
        return this.studentID;
    }

    public String getStudentFirst_name(){
       return this.studentFirst_name;
    }

    public String getStudentLast_name() {
        return studentLast_name;
    }

    public void setStudentFirst_name(String first_name) {
        this.studentFirst_name = first_name;
    }

    public void setStudentLast_name(String last_name) {
        this.studentLast_name = last_name;
    }
}
