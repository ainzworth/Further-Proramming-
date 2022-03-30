import java.sql.Date;
public class Course {
    private String courseID;
    private String name;
    private int  credit;

    // constructor 
    Course(){
        this.courseID = "123";
        this.name = "jack";
        
    }
    Course(String courseID,String name,int credit){
        this.courseID = courseID;
        this.name = name;
        this.credit = credit;
        // add birthdate later plssssss
    }   

    // getter and setters 
    protected void setcourseID(String courseID){
        this.courseID = courseID;
    }
    protected String getcourseID(){
        return this.courseID;
    }
    protected void setName(String name){
        this.name = name;
    }
    protected String getName(){
        return this.name;
    }
    // add getter and setter for daysss plssss
       
}
