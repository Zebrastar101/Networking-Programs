public class Teacher {
    private int teacherID;
    private String teacherFirst_name;
    private String teacherlast_name;
    public Teacher(int id, String first_name, String last_name){
        this.teacherID=id;
        this.teacherFirst_name=first_name;
        this.teacherlast_name=last_name;
    }
    public int getTeacherID(){
        return this.teacherID;
    }

    public String getTeacherFirst_name(){
        return this.teacherFirst_name;
    }

    public String getTeacherLast_name() {
        return teacherlast_name;
    }

    public void setTeacherFirst_name(String first_name) {
        this.teacherFirst_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.teacherlast_name = last_name;
    }
}
