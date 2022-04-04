
import java.io.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import java.util.Iterator;


public class Test {
    public static SEM sem = SEM.getInstance();
    public static String fileName;
    public static String saveString;
    public static ArrayList<studentEnrolment> currentUpdatingEnrollment = new ArrayList<studentEnrolment>();
    public static ArrayList<Course> currentCourseList = new ArrayList<Course>();
    public static String CurrentstudentID;
    public static String Currentsemester;
    public static String[] options1 = {"-----------------","1- get file","0- exit program"};
    public static String[] options2 = {"-----------------","1- Add Enrollments","2- Delete Enrollments","3- Update Enrollment",
                                            "4- print options","0- return to choose file menu"};
    public static String[] UpdateOption = {"-----------------","1- Add course","2- Delete course","0- Return to enrollment menu"};
    public static String[] printOption = {"-----------------","1- Print all course for 1 student in 1 semester","2- Print all students of 1 course in 1 semester",
                                             "3- Print all course offer in 1 semester","4- Print all available enrollments","0- return to enrollment menu"};
    public static String[] saveOption = {"-----------------","1- Yes","2- No"};
    public static File file;


    

    public static void main(String[] arg){
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        int prevOption = -1;
      
            while(prevOption != 0){
                if(fileName == null ){
                    printMenu(options1);
                    try{
                    option = scanner.nextInt();
                   
                    switch(option){
                        case 0: prevOption =0;scanner.close();break;
                        case 1: getFileName();if(fileName != null){prevOption = 1;makeList();}; break;
    
                    }
                    }   catch (Exception ex){
                   
                    scanner.next();
                    
                }
                // }else if(prevOption == 1){
                //     printMenu(options2);
                //     try{
                //         option = scanner.nextInt();

                //         switch(option){
                //             case 0: fileName = null;prevOption = -1; break;
                //             case 1: readFile();break;
                //             case 2: makeList();prevOption=2;break;// add data infile into the system
                //         }
                //     } catch(Exception ex){
                //         System.out.println(ex);
                //         System.out.println("Please enter an integer value between 0 and " + options2.length);
                //         scanner.next();
                //     }
                }else if(prevOption == 1){
                    printMenu(options2);
                       try{
                        option = scanner.nextInt();
                        // "1- Add Enrollments","2- Delete Enrollments","3- Update Enrollment",
                        //"4- print options","0- return to choose file menu"
                        switch(option){
                            case 0: fileName = null; break; //return to file menu
                            case 1: sem.addEnrollmentScan();break; // add more enrollment into the system
                            case 2: sem.delete();break; // delete enrollments
                            case 3: if(addToCurrentUpdatingList()){prevOption = 2;};break;  // update enrollment 
                            case 4: prevOption = 3;break;// print options 
                        
                        
                        }
                    } catch(Exception ex){
                        System.out.println(ex);
                        System.out.println("Please enter an integer value between 0 and " + options1.length);
                        scanner.next();
                    }
                }else if(prevOption == 3 ){
                    printMenu(printOption);
                       try{
                        option = scanner.nextInt();
               
                        //{"1- print all course for 1 student in 1 semester","2- print all students of 1 course in 1 semester",
                        // "3- print all course offer in 1 semester","4- print all available enrollments","0- return to enrollment menu"};
                        switch(option){
                            case 0: prevOption = 1;break; //return to file menu
                            case 1: saveString=sem.printAllCourseForOneStudentInOneSemester();prevOption=4;break;
                            case 2: saveString=sem.printStudentsOfOneSem();prevOption=4;break;  
                            case 3: saveString =sem.printAllCoursesInOneSemester();prevOption=4;break;
                            case 4: sem.getAll();
                           
                        
                        }
                    } catch(Exception ex){
                        System.out.println(ex);
                        System.out.println("Please enter an integer value between 0 and " + options1.length);
                        scanner.next();
                    }
                }else if(prevOption == 2){
                    printMenu(UpdateOption);
                    // print all course of that student
                    System.out.println("enrolled courses of student with id "+CurrentstudentID+" semester "+Currentsemester+":");
                    for(Course i : currentCourseList){
                        System.out.print(i.getcourseID());
                    }
                       try{
                        option = scanner.nextInt();
                        //{"1-Add course","2- delete course","0- return to enrollment menu"};
    
                        switch(option){
                            case 0: prevOption = 1; break; //return to file menu
                            case 1: add();break; // 
                            case 2: delete();break;  // update enrollment 
                  
                        
                        }
                    } catch(Exception ex){
                        System.out.println(ex);
                        System.out.println("Please enter an integer value between 0 and " + options1.length);
                        scanner.next();
                    }
                }else if(prevOption == 4){
                    printMenu(saveOption);
                       try{
                        option = scanner.nextInt();
                       
                        switch(option){
                       
                            case 1: sem.saveToFile(saveString);saveString = null;prevOption = 3;break; // save
                            case 2: prevOption = 3;break; // return to print menu
                         
                        
                        
                        }
                    } catch(Exception ex){
                        System.out.println(ex);
                        System.out.println("Please enter an integer value between 0 and " + options1.length);
                        scanner.next();
                    }
            }
        }
     
           
        }

    
       
    
    public static void printMenu(String[] options){

        System.out.println("choose your options");
        for(String option : options){
            System.out.println(option);
        }
     
    }

    static int getFileName() throws IOException{
        Scanner object = new Scanner(System.in);
        String input;
        System.out.println("Enter name of the file you want to import data from");
        input = object.nextLine();


        String[] arrOfStr = input.split(Pattern.quote("."),2);
        // validate file path
        if(arrOfStr.length > 1){

            if( !arrOfStr[1].equals("csv")){
                System.out.println(arrOfStr[1]);
                System.out.println("invalid file path");
               
                return -1;
            }else{
                fileName = input;
                
                createFile();
                return 0;
            }
        }else{
            System.out.println("invalid input");
         
            return -2;
        }
    }
    static int createFile() throws IOException{
           // File path is passed as parameter
        file = new File(
            "E:\\project\\futher programming\\assignment\\assignment 1\\"+ fileName);
        
        if(!file.isFile()){
            System.out.println("file does not exist");
            return -1;
        }
        
        return 0;
    }
    static void readFile(){
        try{
        // Creating an object of BufferedReader class
        BufferedReader br
            = new BufferedReader(new FileReader(file));
 
        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null)
 
            // Print the string
            System.out.println(st);
            br.close();
   
        }catch(IOException ex){
            System.out.println(ex);
            
        }
    }
    
    static void makeList() throws ParseException{
        try{
        // Creating an object of BufferedReader class
    
        BufferedReader br
            = new BufferedReader(new FileReader(file));
 
        // Declaring a string variable
        String st;
        SEM sem = SEM.getInstance();
        
        String[] splitStr = null;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null){

            splitStr = st.split(",",7);
            studentEnrolment enrolment = new studentEnrolment();
            for(int i = 0;i<splitStr.length-1;i++){
                // initialize contructor or something idk
                Student student = new Student(splitStr[0],splitStr[1],splitStr[2]);
                Course course = new Course(splitStr[3],splitStr[4],Integer.parseInt((splitStr[5])));
                enrolment = new studentEnrolment(student,course,splitStr[6]);
          
                
                
            }
            sem.add(enrolment);
            
        }
          
            br.close();
   
        }catch(IOException ex){
            System.out.println(ex);
            
        }
    }
  
     static void printCoursesOfOneStudentInSem(String studentID,String sem){

        Iterator<studentEnrolment> enrollments = currentUpdatingEnrollment.iterator(); 
        studentEnrolment enrolment = new studentEnrolment();
        String course;
        System.out.println("all courses of student with id "+studentID+" in semester "+sem+":");
        while(enrollments.hasNext()){
            enrolment = enrollments.next();
            if(enrolment.getStuddent().getStudentId().equals(studentID) &&
            enrolment.getSem().equals(sem)
            )
            {
                course = enrolment.getCourse().getcourseID();
                System.out.println(course);
            }
        }
    }
      static boolean addToCurrentUpdatingList(){
                    CurrentstudentID = sem.addStudentScan();
                    Currentsemester = sem.addSemScan();
                    // clear array
                    if(!currentUpdatingEnrollment.isEmpty()){
                        currentUpdatingEnrollment.clear();
                    }
                    if(!currentCourseList.isEmpty()){
                        currentUpdatingEnrollment.clear();
                    }
                    // add enrollments and courses to current update list
                    for(studentEnrolment i : sem.getStudentEnrollment()){
                        if(i.getStuddent().getStudentId().equals(CurrentstudentID) && i.getSem().equals(Currentsemester)){
                            currentUpdatingEnrollment.add(i);
                            if(!checkCourse(i.getCourse().getcourseID())){

                                currentCourseList.add(i.getCourse());
                            }
                            
                        }
                    }
                    
                    if(!currentUpdatingEnrollment.isEmpty()){
                        if(currentCourseList.isEmpty()){
                            System.out.println("student with id "+ CurrentstudentID 
                            +"have zero course enrolled in semseter" + Currentsemester);
                        }else{

                            System.out.println("current course list:");
                            for(Course i : currentCourseList){
                                System.out.println(i.getcourseID());
                            }
                            printCoursesOfOneStudentInSem(CurrentstudentID,Currentsemester);
                        }
                        return true;
                    }else{
                        System.out.println("student with id "+ CurrentstudentID +
                        "has not enrolled into any courses in semester "+ Currentsemester);
                        return false;
                        
                    }
                    
    }
 // delete enrollment when know studentID and semester
   static boolean checkCourse(String courseID){
        for(Course c : currentCourseList){
         if(c != null & c.getcourseID().equals(courseID)){
             return true;
             //exist
         }
       }
       return false;
       // not exist
   }
// take course input and check if course exist in the currrent student enrollment 
    static String addCourseScan(){
       String courseID = null;

       while(courseID==null){
           System.out.println("Enter course ID");
           Scanner scanner = new Scanner(System.in);
           courseID = scanner.nextLine();

        if(!checkCourse(courseID)){
         
            System.out.println("course with id "+courseID+" does not exist ");
            courseID = null;
           
        }
    }
    return courseID;
   }
  // delete
  static void delete(){
      // check if enrollment exist 
   
      String courseID = addCourseScan();
      sem.delete1(CurrentstudentID, courseID, Currentsemester);
      // delete course and enrollment in current list
             if(!currentUpdatingEnrollment.isEmpty()){
                        currentUpdatingEnrollment.clear();
                    }
                     if(!currentCourseList.isEmpty()){
                        currentCourseList.clear();
                    }
          
      for(studentEnrolment i : sem.getStudentEnrollment()){
                        if(i.getStuddent().getStudentId().equals(CurrentstudentID) && i.getSem().equals(Currentsemester)){
                            currentUpdatingEnrollment.add(i);
                                 if(!checkCourse(i.getCourse().getcourseID())){

                                currentCourseList.add(i.getCourse());
                            }
                           
                        }
                    }
                
  }
  static void add(){
        String courseID = sem.addCourseScan();
        sem.addEnrollment1(CurrentstudentID, courseID, Currentsemester);   
  }

  
}
