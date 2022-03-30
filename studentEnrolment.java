import java.util.ArrayList;

public class studentEnrolment{
    private Student student;
    private Course course; 
    private String semester;


    studentEnrolment(){
    
    }
    studentEnrolment(Student student,Course course, String semester){
        this.student = student;
        this.course = course;
        this.semester = semester;


    }
    // getter and setter

    public void setStudent(Student student){
        this.student = student;
    }
    public Student getStuddent(){
        return this.student;
    }

    public void setCourse(Course course){
        this.course = course;
    }
    public Course getCourse(){
        return this.course;
    }

    public void setSem(String sem){
        this.semester = sem;
    }
    public String getSem(){
        return this.semester;
    }
  
  
      
}