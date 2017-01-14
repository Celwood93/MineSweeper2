import java.util.*;
import java.io.*;
import java.lang.*;
public class TestOpenCloseSave{
    
    public static void openFilePlease(){
        Scanner console = new Scanner(System.in);
        Timer timer = new Timer();
        
        File file = new File("MineSweeper2.txt");
        try{
        file.createNewFile();
        }catch(IOException ioe){
                         
                         System.out.println("exit 0");
                         System.exit(-1);
                     }
        //ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "Test.txt");
       // pb.start();
        try{
        Runtime run = Runtime.getRuntime(); 
        Process pp=run.exec("Notepad.exe"+" MineSweeper2.txt");
        long fileLength = file.length();
        
        while(file.length() == fileLength){
            try{
                System.out.println("pass");
            Thread.sleep(4000);
            }catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
            if(file.length() != fileLength){
                    pp.destroy();
            }
            
            
        }
        
        
        
        
        
        
        
        
        
        
        
        /*
        while(file.length() == fileLength){
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                System.out.println("Stuff0");
                if(file.length() != fileLength){
                    pp.destroy();
                    System.out.println("Stuff");
                }
                System.out.println("Stuff1");
            }
            
        }, 4*1000, 4*1000);
        }
        */
        System.out.println("Stuff3");
       }catch(IOException ioe){
            System.exit(-1);
       }
        
       System.out.println("Stuff4");
    }
    
    
    
    
    
    public static void main(String [] args){
        
        
        //openFilePlease();
        
        //test for returning after the game is lost
        String[]arr = new String [1];
        arr[0] = "B";
       // System.out.println("arr[0] before: " + arr[0]);
        arr[0] = "["+arr[0] +"]";
       // System.out.println("arr[0] after: " + arr[0]);
       // System.out.println(arr[0].length());
        String hi = "   test   ";
        String hm = "  [ ] test ";
        String hmm = "[?]";
        System.out.println(hmm.substring(0,1));
        while(!(hmm.substring(0,1).equals("["))){
            
        System.out.println(hmm.substring(0,1));
        }
    }
}