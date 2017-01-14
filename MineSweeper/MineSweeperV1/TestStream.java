import java.io.*;
import java.util.*;

public class TestStream{
    
    
    public static void printStreamTest(){
        try{
     PrintStream output = new PrintStream(new File("test.txt"));
     Scanner console = new Scanner(System.in);
     System.out.println("please type something");
     String stuff = console.nextLine();
     output.println(stuff);
     output.close();
        }catch(FileNotFoundException fnfe){
            System.out.println("if this error comes up ur in big trouble");
            System.exit(-1);
        }
        
      
    }
    
    public static void main(String[]args){
        printStreamTest();
       // printFileTest();
    }
    
    
    public static void printFileTest(){
        try{
            String line;
            BufferedReader in;
            in = new BufferedReader(new FileReader("test.doc"));
            line =in.readLine();
            while(line != null){
                System.out.println(line);
                line = in.readLine();
            }
            System.out.println(line);
        
            
            
            
        }catch(IOException e){
            System.exit(-1);
        }
    }
    
}