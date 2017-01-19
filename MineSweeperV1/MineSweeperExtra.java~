import java.util.*;
import java.io.*;

public class MineSweeper{
    
    
    public static void main(String[] args){
        Random generator = new Random();
        test(generator);
    }
    
    
    public static void promptUser(){
        
        
        
        Scanner console = new Scanner(System.in);
        String ansOne = " ";
        System.out.print("Would you like to play a game of minesweeper? Type 00 if you dont\n");
        ansOne = console.nextLine();
        if(ansOne.equals("00")){
            System.exit(1);
        }
        System.out.print("Type \"small board\", \"medium board\", or \"large board.\" to choose your size");
        String ansTwo = " ";
        ansTwo = console.nextLine();
        while(!(ansTwo.equalsIgnoreCase("small board") || ansTwo.equalsIgnoreCase("medium board") || ansTwo.equalsIgnoreCase("large board"))){
            System.out.print("please type either \"small board\", \"medium board\", or \"large board.\" to choose your size");
        }
        
            
    }
    public static void makeBox(Random generator, String boxSize){
        int x = 0;
        int y = 0;
        int z = 0;
        if(boxSize.equalsIgnoreCase("small board")){
            x=5;
            y=5;
            
        }else if(boxSize.equalsIgnoreCase("medium board")){
            x=10;
            y=10;
        }else if(boxSize.equalsIgnoreCase("large board")){
            x=20;
            y=20;
        }
        
        for(int i=0;i<(x+1);i++){
          
            System.out.println("");
            if(i!=0){
                if(i<10){
                    System.out.print(i+"  ");
                }else{
             System.out.print(i+" ");
                }
            }
            for(int j=0; j<y;j++){
                if(i==0){
                    System.out.print(" ");
                    for(int k=1;k<(y+1);k++){
                        if(k<10){
                        System.out.print("   "+(k));  
                        }else{
                            System.out.print("  "+(k));
                        }
                    }
                    break;
                }
               
                System.out.print("[" + "] ");
                
            }
        }
    }
    
    
    public static int randomBomb(Random generator, int length){
        
        int i=0;
        i=generator.nextInt(length);
        return i;
    }
    
            
        public static String bombSpotsAndNumbers(int d, Random generator){
            
            //Variables being declared with a full scope
            int length;
            int bombCount;
            String bombSpots = "";
            String returnString = "";
            String printString = "";
            if(d == 1){
                length = 9;
                bombCount = 10;
            }else if(d == 2){
                length = 16;
                bombCount = 40;  
            }else if(d == 3){
                length = 25;
                bombCount = 90;
                    
            }else{
                Scanner console = new Scanner(System.in);
                System.out.println("length: ");
                length = console.nextInt();
                System.out.println("bombs");
                bombCount = console.nextInt();
                console.close();
            }
            
            int bombNumber =0;
            int k = 0;
            int j = 0;
            int i = 0;
            int n = 0;
            int p = 0;
            int q = 0;
            int pOne=0;
            int qOne=0;
           
            //Creating the return string that will store the meat of the game (Bombs and numbers)
            
            String[][]fillTable = new String[length][length];
          //   System.out.println("test5");
          
            // This loop is for i, which is the y axis of the game board
            
             for(i=0;i<length;i++){
                 
                 //this loop is for j, which is the x acis of the game board
                 
                 for(j=0;j<length;j++){
                     
                     //null was being difficult so i just made it a space
                     
                     fillTable[i][j]="0";
                 }
             }
            // System.out.println("test6");
             
             //This loop will create bomb locations based on how big the board will be. if the x-axis is 7 wide, then
             //The board will have 7 bombs.
             
            for(i=0;i<bombCount;i++){
                //random function being called to produce a value
                 k = randomBomb(generator, length);
             //    System.out.print(k);
                 
                 n = randomBomb(generator, length);
              //   System.out.print(n);
                 
                 //Test loop to see if a bomb already exists at the random location
                 
                 if(fillTable[k][n] == "0"){
                     
                    fillTable[k][n] = "9";
                    bombSpots = bombSpots + k + " " + n + " ";  
                }
            }
            //this loop will go through the x-axis of the table, checking to see how many bombs surround each location
            
            for(i=0;i<length;i++){
                for(n=0;n<length;n++){
                  
                    int h =0;
                    int check =  0;
                    int check2 = 0;
                    int check3 = 0;
                    int check4 = 0;
                    if(!(fillTable[i][n].equals("9"))){
                    j=1;
                    p = i-j;
                    pOne = i+j;
                    qOne = n+j;
                    q = n-j;
                    if(pOne==length){
                        pOne = length - 1;
                        check = 1;
                    }
                    if(qOne == length){
                        qOne = length - 1;
                        check2 = 1;
                    }
                     if(p<0){
                         check3 =1;
                        p=0;
                     }
                    if(q<0){
                        check4 =1;
                        q=0;
                     }
                    
                         if(fillTable[pOne][n].equals("9") && check!=1){
                             h++;
                         }
                         if(fillTable[pOne][q].equals("9") && check!=1 && check4!=1 ){
                             h++;
                         }
                         if(fillTable[p][q].equals("9") && check3!=1 && check4!=1){
                             h++;
                         }
                         if(fillTable[p][qOne].equals("9") && check3!=1 && check2!=1){
                             h++;
                         }
                         if(fillTable[pOne][qOne].equals("9") && check!=1 && check2!=1){
                             h++;
                         }
                         if(fillTable[p][n].equals("9") && check3!=1){
                             h++;
                         }
                         if(fillTable[i][qOne].equals("9") && check2!=1){
                             h++;
                         }
                         if(fillTable[i][q].equals("9") && check4!=1){
                             h++;
                         }
                         if(h==0){
                             fillTable[i][n] = "0";
                         }else{
                         fillTable[i][n] = ""+h;
                         
                      //   System.out.println("none bomb: " +h);
                           }
                     }
                    }
                
            //this just prints a compy of the table to make sure it works
          
        }
            returnString = length+" "+length+" "+bombCount + " 1 " + d + " \r\n" + bombSpots + "\r\n";
            printString = printString + "\n{{";
            for(int y=0;y<length;y++){
                 for(int x=0;x<length-1;x++){
                     //add another string that prints that has commas, change all the files
                   returnString = returnString + fillTable[y][x]+ " ";
                   printString = printString + fillTable[y][x] + ",";
                 }
                 returnString = returnString + fillTable[y][length-1] + "\r\n";
                 printString = printString + fillTable[y][length-1] + "},\r\n{";
            }
            returnString = returnString.substring(0, returnString.length() -2);
            printString = printString.substring(0, printString.length()-4);
            printString = printString + "}";
            System.out.println(printString);
            return returnString;
        }
            
            
    //this is a test run, where its easy to manipulate numbers without messing with the user inputs.
    public static void test(Random generator){
       String answer = "small board";
       File file;
   // makeBox(generator, answer);
       try{
           for(int i = 1; i < 4; i++){
               answer = bombSpotsAndNumbers(3,generator);
               PrintStream output = new PrintStream(file = new File("MSTxts\\testHard"+i+".txt"));
               output.print(answer);
               output.close();
           }
       }catch(FileNotFoundException fnfe){
           return;
       }
       
    }
    
    
}
