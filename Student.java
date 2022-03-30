import java.util.Date;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Student{
    private String studentID;
    private String name;
    private Date birthDate;
    private ArrayList<Course> courseList;

    // constructor 
    Student() {
        
        this.studentID = "123";
        this.name = "jack";
        this.courseList = new ArrayList<Course>();
        
    }
    Student(String studentID,String name,String birthDate) throws ParseException{
        this.studentID = studentID;
        this.name = name;
        this.birthDate = new SimpleDateFormat("MM/dd/yyyy").parse(birthDate);
        this.courseList = new ArrayList<Course>();
    }   

    // getter and setters 
    protected void setStudentID(String studentID){
        this.studentID = studentID;
    }
    protected String getStudentId(){
        return this.studentID;
    }
    protected void setName(String name){
        this.name = name;
    }
    protected String getName(){
        return this.name;
    }
   
    // methods
    
  

    
}