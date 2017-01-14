import java.util.*;
import java.nio.file.Files;
import java.io.*;
import java.awt.*;

public class CopyTest{
    
    
    public static void main(String [] args){
        
        copyFile();
    }
    
    public static void copyFile(){
        
        
   // try{
             
        
                    System.out.println("check 2"); 
                    File fileTwo = new File("MineSweeper.txt");
                    File file = new File("MineSweeperHolds.txt");
                    try{
                    file.createNewFile();
        }catch(IOException ioe){
                         System.out.println("check 3"); 
                         System.exit(-1);
                 
            }
            try{           
                       //  File.copy("MineSweeper.txt", "MineSweeperHold.txt");
                         Files.copy(fileTwo.toPath(), file.toPath());
                     }catch(IOException ioe){
                         System.out.println("check 4"); 
                         System.exit(-1);
                   //  }
    }
    }
}
    