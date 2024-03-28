public class Course {
    private int courseID;
    private String name;
    private String type;
    public Course(int id, String first_name, String last_name){
        this.courseID=id;
        this.name=first_name;
        this.type=last_name;
    }
    public int getCourseID(){
        return this.courseID;
    }

    public String getName(){
        return this.name;
    }

    public String getType() {
        return getType();
    }

    public void setName(String first_name) {
        this.name = first_name;
    }

    public void setLast_name(String last_name) {
        this.type = last_name;
    }
}
