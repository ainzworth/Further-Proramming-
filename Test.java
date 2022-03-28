
import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.text.html.HTMLDocument.RunElement;

public class Test {
    public static String fileName;
    public static String[] options1 = {"1- get file","0- exit program"};
    public static String[] options2 = {"1- add enrollment","0- exit program"};
    public static void main(String[] arg){
        Scanner scanner = new Scanner(System.in);
        int option = 1;
      
            while(option != 0){
                printMenu(options1);
                try{
                option = scanner.nextInt();
               
                switch(option){
                    case 0: break;
                    case 1: getFileName();
                }
                }   catch (Exception ex){
                System.out.println("Please enter an integer value between 0 and " + options1.length);
                scanner.next();
                
            }
            }
     
                // printMenu(options1);
                // option = scanner.nextInt();
                // switch(option){
                //     case 0: break;
                //     case 1: getFileName();
                //     case 2: break;
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
        File file = new File(
            "E:\\project\\futher programming\\assignment\\assignment 1\\"+ fileName);
        if(!file.isFile()){
            System.out.println("file does not exist");
            return -1;
        }
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
   
        }catch(IOException ex){
            System.out.println(ex);
            
        }
        return 0;
    }
}
