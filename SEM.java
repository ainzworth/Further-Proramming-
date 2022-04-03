import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;


public class SEM implements studentEnrolmentManagement {
    private static ArrayList<studentEnrolment> studentEnrolmentsList = new ArrayList<studentEnrolment>(); 
    
    private static ArrayList<Student> studentList = new ArrayList<Student>();
    private static ArrayList<Course> courseList = new ArrayList<Course>();
    private static String printMessage;
 private static SEM instance;

 

    public static SEM getInstance() {
        if (instance == null) {
            
            instance = new SEM();
        }
        
        return instance;
    }
   SEM(){
         
   }
   // add student to system
   public boolean addS(Student student){
  
    boolean studentExist = false;
    for(Student s : studentList){
        if(s !=null && s.getStudentId().equals(student.getStudentId())){
        
            studentExist = true;// student does not exist, add to the system
            break;
        }
    }
    if(studentExist){
        //student exist, return false because the system cant added the student

        return false; 
    }else{
        //student exist, return true because the system can add the student
      
        return true;
    }
   }

   // add course to system
   public boolean addC(Course course){
          boolean courseExist = false;
    for(Course c : courseList){
        if(c !=null && c.getcourseID().equals(course.getcourseID())){
        
            courseExist = true;// student does not exist, add to the system
            break;
        }
    }
    if(courseExist){
        //student exist, return false because the system cant added the student

        return false; 
    }else{
        //student exist, return true because the system can add the student
        return true;
    }
        
   }
   // add enrollment to system
   //get student from system with id
   public Student getStudent(String studentID){
       for(Student i : studentList){
           if(i.getStudentId().equals(studentID)){
               return i;
            }
        }
        return null;
    }
    // get course from system with id
    public Course getCourse(String courseID){
        for(Course i : courseList){
            if(i.getcourseID().equals(courseID)){
                return i;
            }
        }
        return null;
    }
    // for updating student
    public ArrayList<studentEnrolment> getStudentEnrollment(){
        return studentEnrolmentsList;
    }
    //check if student exist using sudentID input from user
   public boolean checkID(String studentID){
       for(Student s : studentList){
         if(s != null & s.getStudentId().equals(studentID)){
             return true;
         }
       }
       return false;
   }
    //check if course exist using courseID input from user
   public boolean checkCourse(String courseID){
        for(Course c : courseList){
         if(c != null & c.getcourseID().equals(courseID)){
             return true;
         }
       }
       return false;
   }
  


// add enrollment to the system 
// this will run after file is choosed 
   public void add(studentEnrolment studentEnrolment){
       Student student= studentEnrolment.getStuddent();
       Course course= studentEnrolment.getCourse();
       // add student to the system
       if(addS(student)){
             studentList.add(student);
        System.out.println("student with id:"+student.getStudentId()+" added to the system");
       }else{
        System.out.println("student existed");
       }
       // add courrse to the system
         if( addC(course)){
               courseList.add(course);
        System.out.println("course with id:"+course.getcourseID()+" added to the system");
       }else{
           System.out.println("course existed");
       }
      

       studentEnrolmentsList.add(studentEnrolment);
       enrollmentAddMessage(studentEnrolment);
     
   }
   // take student id using scanner and check if student exist in the system
   public String addStudentScan(){
       String studentID = null;

       while(studentID==null){
           System.out.println("Enter student ID");
           Scanner scanner = new Scanner(System.in);
           studentID = scanner.nextLine();

        if(!checkID(studentID)){

            System.out.println("student with id "+studentID+" does not exist");
            studentID = null;
           
        }
    }
    return studentID;
   }
    // take course id using scanner and check if course exist in the system
     public String addCourseScan(){
       String courseID = null;

       while(courseID==null){
           System.out.println("Enter course ID");
           Scanner scanner = new Scanner(System.in);
           courseID = scanner.nextLine();

        if(!checkCourse(courseID)){
         
            System.out.println("course with id "+courseID+" does not exist");
            courseID = null;
           
        }
    }
    return courseID;
   }
    // take Semester using scanner and check if user enter valid semester
   public String addSemScan(){
       String semester = null;
       while(semester == null){
           Scanner scanner = new Scanner(System.in);
           System.out.println("Enter semester");
           semester = scanner.nextLine();
           if(semester.length() != 5 ){
              semester = null;
              System.out.println("invalid semester");
           }
       }
       return semester;
   }
   // get student ENROLLMENT through input
   public studentEnrolment getEnrollment(){
       String studentID = addStudentScan();
       String courseId = addCourseScan();
       String semester = addSemScan();
       Student student = getStudent(studentID);
       Course course = getCourse(courseId);
       studentEnrolment studentEnrolment = new studentEnrolment(student,course,semester);
      
       return studentEnrolment;
   }

   // get enrollment of a student in a specific semester
     public studentEnrolment getEnrollment1(String studentID,Course course,String semester){
     
       Student student = getStudent(studentID);
       studentEnrolment studentEnrolment = new studentEnrolment(student,course,semester);
      
       return studentEnrolment;
   }
 
   // check if enrollment is in the system
   public boolean enrollmentCheck(studentEnrolment checkStudentEnrolment){

        for(studentEnrolment i : studentEnrolmentsList ){
            if(checkStudentEnrolment.getCourse().getcourseID().equals(i.getCourse().getcourseID()) &&
               checkStudentEnrolment.getStuddent().getStudentId().equals(i.getStuddent().getStudentId()) &&
               checkStudentEnrolment.getSem().equals(i.getSem())){
                // enrollment exist
                return true;
            }
        }
        
        // enrollment does not exist
        return false;
   }
   // this function will be put into the menu to take input and check if student
   // enrollment exist then add then print i have no idea wtf
   public void addEnrollmentScan(){
    // take user input
       studentEnrolment studentEnrolment = getEnrollment();
       // check if enrollment exist
       if(!enrollmentCheck(studentEnrolment)){
           // if not add to the system
           studentEnrolmentsList.add(studentEnrolment);
           enrollmentAddMessage(studentEnrolment);
       }else{
           // print error if already exist
           System.out.println("enrollment already existed");
       }
       
      
     
   }
   // prompt user input to update
   public int update(){
    return -1;
   }
   // DELETE enrollment
    public void delete(){
    studentEnrolment studentEnrolment = getEnrollment();
    if(!enrollmentCheck(studentEnrolment)){
           // if not print out error
           System.out.println("Error enrollment does not exist");
    }else{
           // if already exist delete enrollment
        
            Iterator<studentEnrolment> iterator = studentEnrolmentsList.iterator();
            studentEnrolment i = new studentEnrolment(); //enrollment
            while (iterator.hasNext()){
                i =(studentEnrolment) iterator.next();
                if(studentEnrolment.getCourse().getcourseID().equals(i.getCourse().getcourseID()) &&
                   studentEnrolment.getStuddent().getStudentId().equals(i.getStuddent().getStudentId()) &&
                   studentEnrolment.getSem().equals(i.getSem())){
                   // enrollment exist

                   iterator.remove();
                   System.out.println("enrollment removed");
                   
             }
        }
    }
    
    
}
 public void delete1(String studentID,String semester,Course course){
    studentEnrolment studentEnrolment = getEnrollment1(studentID,course,semester);
    if(!enrollmentCheck(studentEnrolment)){
           // if not print out error
           System.out.println("Error enrollment does not exist");
    }else{
           // if already exist delete enrollment
        
            Iterator<studentEnrolment> iterator = studentEnrolmentsList.iterator();
            studentEnrolment i = new studentEnrolment(); //enrollment
            while (iterator.hasNext()){
                i =(studentEnrolment) iterator.next();
                if(studentEnrolment.getCourse().getcourseID().equals(i.getCourse().getcourseID()) &&
                   studentEnrolment.getStuddent().getStudentId().equals(i.getStuddent().getStudentId()) &&
                   studentEnrolment.getSem().equals(i.getSem())){
                   // enrollment exist

                   iterator.remove();
                   System.out.println("enrollment removed");
                   
             }
        }
    }
    
    
}

    // GET ALL STUDENTS OF 1 COURSE IN ONE SEMESTER
    public void printStudentsOfOneSem(){
        String courseID = addCourseScan();
        String sem = addSemScan();
        Iterator<studentEnrolment> enrollments = studentEnrolmentsList.iterator(); 
        studentEnrolment enrolment = new studentEnrolment();
        System.out.println("Student enrolled into course "+courseID+" in semester "+sem+":");
        while(enrollments.hasNext()){
            enrolment = enrollments.next();
            if(enrolment.getCourse().getcourseID().equals(courseID) &&
               enrolment.getSem().equals(sem)
            ){
                System.out.println(enrolment.getStuddent().getStudentId());
            }
        }
    }
    // GET ALL COURSES FOR 1 STUDENT IN 1 SEMESTER 
    public void printAllCourseForOneStudentInOneSemester(){
        String studentID = addStudentScan();
        
        String sem = addSemScan();
        Iterator<studentEnrolment> enrollments = studentEnrolmentsList.iterator(); 
        studentEnrolment enrolment = new studentEnrolment();
        String printMessage = "all courses of student with id "+studentID+" in semester "+sem+":\n" ;
        String course;
        System.out.println("all courses of student with id "+studentID+" in semester "+sem+":");
        while(enrollments.hasNext()){
            enrolment = enrollments.next();
            if(enrolment.getStuddent().getStudentId().equals(studentID) &&
            enrolment.getSem().equals(sem)
            )
            {
                course = enrolment.getCourse().getcourseID();
                System.out.println(enrolment.getCourse().getcourseID());
                printMessage += course + "\n";
            }
        }
        
        
        
    }
   
    
    // GET ALL COURSES OFFERED IN ONE SEMESTER
    public void printAllCoursesInOneSemester(){
        
        String sem = addSemScan();
        String courseID;
        Iterator<studentEnrolment> enrollments = studentEnrolmentsList.iterator(); 
        
        ArrayList<String> coursesID = new ArrayList<>();
        studentEnrolment enrolment = new studentEnrolment();
        System.out.println("all courses offer in semester "+sem+":");
        while(enrollments.hasNext()){
            enrolment = enrollments.next();
            courseID = enrolment.getCourse().getcourseID();
            if(!coursesID.contains(courseID) && enrolment.getSem().equals(sem)){
                coursesID.add(courseID);
            }
            
         
        }
        for(String i : coursesID){
            System.out.println(i);
        }
    }
   // print 
    public void enrollmentAddMessage(studentEnrolment studentEnrolment){
          System.out.println("enrollment for "+
          studentEnrolment.getStuddent().getStudentId() +" enroll into "+ 
          studentEnrolment.getCourse().getcourseID() + " of semester "+ 
          studentEnrolment.getSem() + " added to the system");
    }
     private void enrollmentMessage(studentEnrolment studentEnrolment){
          System.out.println("enrollment of "+
          studentEnrolment.getStuddent().getStudentId() +" enroll into "+ 
          studentEnrolment.getCourse().getcourseID() + " of semester "+ 
          studentEnrolment.getSem() );
    }
    // add to file
    // print all enrollments 
    public void getAll(){
        for(studentEnrolment i : studentEnrolmentsList){
            enrollmentMessage(i);
        }
    }
    public void getALLstudent(){
        for(Student i : studentList){
            System.out.println(i.getStudentId());
        }
    }
    public void getALLcourse(){
          for(Course i : courseList){
            System.out.println(i.getcourseID());
        }
    }
    public void getOne(){
        studentEnrolment enrollment = getEnrollment();
        if(studentEnrolmentsList.contains(enrollment)){
            // if enrollment exist
            enrollmentMessage(enrollment);
        }else{
            System.out.println("Enrollment does not exist");
        }
    }
}

