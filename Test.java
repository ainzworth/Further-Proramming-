
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
    public static ArrayList<studentEnrolment> currentUpdatingEnrollment = new ArrayList<studentEnrolment>();
    public static ArrayList<Course> currentCourseList = new ArrayList<Course>();
    public static String CurrentstudentID;
    public static String Currentsemester;
    public static String[] options1 = {"1- get file","0- exit program"};
    // public static String[] options2 = {"1- read file","2- make list","0- Choose new file"};
    public static String[] options2 = {"1- Add Enrollment","2- Update Enrollment","3- print options","0- return to choose file menu"};
    public static String[] UpdateOption = {"1-Add course","2- delete course","0- return to enrollment menu"};
    public static String[] printOption = {"1- print all course for 1 student in 1 semester","2- print all students of 1 course in 1 semester",
                                        "3- print all course offer in 1 semester","0- return to enrollment menu"};
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
                        case 1: getFileName();if(fileName != null){prevOption = 1;}; makeList(); break;
    
                    }
                    }   catch (Exception ex){
                    System.out.println("Please enter an integer value between 0 and " + options1.length);
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
                        // "1- Add Enrollments","2- Update Enrollment","3- print options","0- return to choose file menu"
                        switch(option){
                            case 0: prevOption = 0;fileName = null; break; //return to file menu
                            case 1: sem.addEnrollmentScan();break; // add more enrollment into the system
                            case 2: prevOption = 2;break;  // update enrollment 
                            case 3: prevOption = 3;break;// delete enrollment 
                            case 4: sem.getAll(); break;//show all enrollment in the system
                            case 5: sem.getOne();break;// get one enrollment 
                        
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
                        // "3- print all course offer in 1 semester","0- return to enrollment menu"};
                        switch(option){
                            case 0: prevOption = 1;break; //return to file menu
                            case 1: sem.printAllCourseForOneStudentInOneSemester();break;
                            case 2: sem.printStudentsOfOneSem();break;  
                            case 3: sem.printAllCoursesInOneSemester();break;
                           
                        
                        }
                    } catch(Exception ex){
                        System.out.println(ex);
                        System.out.println("Please enter an integer value between 0 and " + options1.length);
                        scanner.next();
                    }
                }else if(prevOption == 2){
                    
                    addToCurrentUpdatingList();

                    printMenu(UpdateOption);
                    // print all course of that student
                       try{
                        option = scanner.nextInt();
                        //{"1-Add course","2- delete course","0- return to enrollment menu"};
    
                        switch(option){
                            case 0: prevOption = 0;fileName = null; break; //return to file menu
                            case 1: ;break; // 
                            case 2: sem.printAllCoursesInOneSemester();break;  // update enrollment 
                            case 3: sem.delete();break;// delete enrollment 
                            case 4: sem.getAll(); break;//show all enrollment in the system
                            case 5: sem.getOne();break;// get one enrollment 
                        
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
      static void addToCurrentUpdatingList(){
                    CurrentstudentID = sem.addStudentScan();
                    Currentsemester = sem.addSemScan();
                    // clear array
                    if(!currentUpdatingEnrollment.isEmpty()){
                        currentUpdatingEnrollment.clear();
                    }
                    for(studentEnrolment i : sem.getStudentEnrollment()){
                        if(i.getStuddent().getStudentId() == CurrentstudentID && i.getSem() == Currentsemester){
                            currentUpdatingEnrollment.add(i);
                        }
                    }
                    printCoursesOfOneStudentInSem(CurrentstudentID,Currentsemester);
                    
    }
 // delete enrollment when know studentID and semester
    static void getCourse(){
        boolean exist = false;
        do{
            System.out.println("Enter course to delete");
        Scanner scanner = new Scanner(System.in);
        String courseID = scanner.nextLine();
        for(studentEnrolment i : currentUpdatingEnrollment){
            if(i.getCourse().getcourseID() != courseID){
                
                break;
            }
        }
    }while(!exist);

    }
  
}
