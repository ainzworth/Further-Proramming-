
import java.io.*;
import java.text.ParseException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Test {
    public static SEM sem = SEM.getInstance();
    public static String fileName;
    public static String[] options1 = {"1- get file","0- exit program"};
    public static String[] options2 = {"1- read file","2- make list","0- Choose new file"};
    public static String[] options3 = {"1- Add","2- Update","3- Delete","4- show all enrollment","5- get one enrollment","0- return to choose file menu"};
    public static String[] options4 = {"1- update student","2- update course","3- update semester","0- return to enrollment menu"};
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
                        case 1: getFileName();if(fileName != null){prevOption = 1;}; break;
    
                    }
                    }   catch (Exception ex){
                    System.out.println("Please enter an integer value between 0 and " + options1.length);
                    scanner.next();
                    
                }
                }else if(prevOption == 1){
                    printMenu(options2);
                    try{
                        option = scanner.nextInt();

                        switch(option){
                            case 0: fileName = null; System.out.println(fileName);prevOption = -1; break;
                            case 1: readFile();break;
                            case 2: System.out.println(prevOption);makeList();prevOption=2;break;// add data infile into the system
                        }
                    } catch(Exception ex){
                        System.out.println(ex);
                        System.out.println("Please enter an integer value between 0 and " + options2.length);
                        scanner.next();
                    }
                }else if(prevOption == 2){
                    printMenu(options3);
                       try{
                        option = scanner.nextInt();

                        switch(option){
                            case 0: prevOption = 1; break; //return to file menu
                            case 1: sem.addEnrollmentScan();break; // add more enrollment into the system
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

    static int getFileName(){
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
    static int createFile(){
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
  
}
