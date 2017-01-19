import java.util.*;
import java.io.*;
import java.lang.*;
public class TestFileToStringArray{
    // public static String [][] fileToStringArray(String file, int[] xValue, int []yValue, Boolean[] cantPlay)
    public static String[][] testFileToStringArray(){
       // File file = new File("MineSweeper2.txt");
        int [] xValue = new int [1];
        xValue[0] = 5;
        int [] yValue = new int [1];
        yValue[0] = 5;
        Boolean [] cantPlay = new Boolean [1];
            cantPlay[0] = false;
            
         String [][] arr = new String [yValue[0]][xValue[0]];
        
        String board = "irrelevant";
        int f = 0;
        int k = 2;
        int y = 0;
        int g = 0;
        
        try{
            Scanner console = new Scanner(new File("MineSweeper2.txt"));
            
           /*
            while(!(board.substring(0,1).equals("["))){
                board = "";
                board = console.nextLine();
                if(board.length() <2){
                board = console.nextLine();
                }
                System.out.println("board: " +board);
              System.out.println("boardsub: "+board.substring(0,1));
            }
            */
            int nCount = 0;
            while(!(board.substring(nCount,nCount+1).equals("["))){
                 System.out.println("substring at"+nCount+"isnt [ so going into while loop");
                if(board.substring(nCount,nCount+1).equals(" ")){
                    System.out.println("substring at"+nCount+ "is a space so ncount ++");
                    nCount++;
                }else{
                     System.out.println("going to next line after [ wasnt found but it wasnt a space at "+nCount);
                board = "";
                board = console.nextLine();
                
                if(board.length() <2){
                    board = console.nextLine();
                    
                }
               nCount = 0; 
            }
                   
                   }
            
            
           
            // System.out.println("here");
            for(int i = 0; i<yValue[0]; i++){
               //  System.out.println("");
                y=0;
               if(cantPlay[0] == true){
                   
                    break;
                    
                }
               if(i>0){
                    board = console.nextLine();
                }
                
                for(int j = 0; j<xValue[0]; j++){
                    arr[i][j] = "";
                   
                      k=0;
                      if(y==1){
                          g = y;
                      }else {
                          g=k;
                      }
                    System.out.println("nCount: " +(nCount));
                    System.out.println("j: " +j);
                    System.out.println("g: " +g);
                    
                    while(board.charAt((nCount)+g+((j*3))) != ']'){
                        System.out.println(board);
                       System.out.println("charAt value: " + board.charAt((nCount)+g+((j*3))));
                        
                        arr[i][j] += board.charAt((nCount)+g+((j*3)));
                        k++;
                        g++;
                        
                        if(k>2){
                          //  System.out.println("at i,j: "+i+", "+j);
                            f++;
                            y++;
                        }
                      //  if(g==1 && board.charAt(g+((j*3)) == ']')){
                            
                        
                    }
                    if(f==2){
                      //  System.out.print("happend");
                        cantPlay[0] = true;
                    }
                    if(g == 1){
                        arr[i][j] = "[ ]";
                    }else{
                    arr[i][j] += board.charAt((nCount)+g+((j*3)));
                    }
                    
                    
                }
                    
            }
                
        }catch(FileNotFoundException fnfe){
            System.exit(1);
        }
        
        return arr;
    
        
        
        
        
        
        
        
        
    }
    
    
    
    public static void main(String[]args){
         System.out.println(Arrays.deepToString(testFileToStringArray()));
    }
}