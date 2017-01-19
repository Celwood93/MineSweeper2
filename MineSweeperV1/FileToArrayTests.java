import java.util.*;
import java.io.*;
import java.awt.*;

public class FileToArrayTests{
    
    public static void main(String [] args){
        Boolean [] cantPlay = new Boolean [1];
        cantPlay[0] = false;
        Boolean [] gameOver = new Boolean[1];
        gameOver[0] = false;
            
        
        File test = new File( "testthree.txt");
        
        
       // String [][] arr = 
       // System.out.println(Arrays.deepToString(arr));
      //  System.out.print(Arrays.toString(test)); 
    }
    //this function takes in a file name and 2 integer values and will scan a file and return an array that 
    //will contain the [ ] and their interiors of the gameboard.
    
    //make sure to change the int values to int arrays in the main document
    public static String [][] fileToStringArray(File file, int[] xLength, int []yLength, Boolean[] cantPlay){
        //this is where the array is created
        String [][] arr = new String [yLength[0]][xLength[0]];
        //we call this irrelevant because it felt fitting because what is in there beforehand is irrelevant as long
        //as it isnt [
        String board = "irrelevant";
        //this breaks a loop - which needs to also cause the file to not go through - edit this eventually!****
       
        //this is set to 2 because there are 3 characters per element in an array - which is a count of 2
        int f = 2;
        try{
            Scanner console = new Scanner((file));
            
        //this makes sure that the only thing that is scanned has to do with the game board and not the title
            
            while(board.charAt(0) != '['){
                //this will grab an entire row of the board, one row at a time
                board = console.next();
            }
            for(int i = 0; i<yLength[0]; i++){
                //if there are two points where there are two chracters in [ ] then the program is suppose to 
                //send an error message to the user - WIP
                if(cantPlay[0] == true){
                    break;
                }
                //to make the scanner move to the next row
                if(i>0){
                    board = console.next();
                }
                for(int j = 0; j<xLength[0]; j++){
                    arr[i][j] = "";
                    // System.out.println("TU");
                    //System.out.println("f: " +f);
                    //this makes it so that if f is >2 then it will be greater then 3 if it has another
                    //situation where there are two characters in [ ]
                    f = f - 2;
                    // we increment it by f+j*3 so that it goes through each character in each row
                    while(board.charAt(f+((j*3))) != ']'){
                        arr[i][j] += board.charAt(f+((j*3)));
                        f++;
                       
                    }
                    if(f>3){
                        cantPlay[0] = true;
                    }
                    //this should always snag the ] that was missed in the while loop for each element of the array
                    arr[i][j] += board.charAt(f+((j*3)));
                }
            }
        }catch(FileNotFoundException fnfe){
            System.exit(1);
        }
        return arr;
    }
    //i felt the name array process was fitting because both the other functions were file and mine process
    
    //i just realized it take in the array from file to array so im wrong and sad now - switched it to game process
    
    //it takes in 2 arrays and processes them to make 1 array with the user alterations taken into account.
    
    //make sure to change the int values to int arrays in the main document
    //the 2 booleans are to make sure that the game ends if it needs to. it is 1 dimensional, 1 of the booleans is
    //to test for if the player can keep playing, true or false, then, if its because the player lost,
    //the second boolean will be checked(gameOver), to see if its because the player lost which is default false, assuming there
    //was a player error in their last turn (not following the rules)
    // if the player cant play, it means they need to redo the last turn, which will send the input
    //array from arrfile straight back into the fileprocess function
    
    //changed to two variables for clarity
    //mCount is to control the counter, which also affects the int win, which if is 0 when mCount is 0 means the user wins.
    public static String[][] gameProcess(String[][]arrMine, String[][]arrFile, int[] xLength, int[] yLength, int[] mCount, int[] win, Boolean[] cantPlay, Boolean[] gameOver){
        //this is where the variables for the function are initially defined
        
        String[][] returnProcess = new String [yLength[0]][xLength[0]];
        String[][] arrMineExpanded = new String[yLength[0]+2][xLength[0]+2];
        int i = 0;
        int j = 0;
        //path is set to 10, it is irrelevant what number it is.
        int path = 10;
        int yPoint = 0;
        int xPoint = 0;
        //this is where we set the return process to be equal to what the arrFile is
        //this is done because what is returned will be based off of what has already been done
        for( i = 0; i<yLength[0];i++){
            for( j = 0; j<xLength[0];i++){
                returnProcess[i][j] = arrFile[i][j];
            }
        }
        //this is to create a boarder of *'s around the mine mask, which will make sure that the boundaries are not
        //exceeded during the testing process
        for( i = 0; i<yLength[0]+2;i++){
            for( j = 0; j<xLength[0]+2;i++){
                if(i==0 || j==0 || i== yLength[0]+2 || j==xLength[0]+2){
                    arrMineExpanded[i][j] = "*";
                }else{
                    arrMineExpanded[i][j] = arrMine[i][j];
                }
            }
        }
        //this is testing to see where the point of action in the document will take place
        //the loops look for either an x an ! or an ? after the values of ? or !.
        //this decideds what will be done to the point of selection. 
        //X is to trigger a click on the location, ! is to label a location as a mine spot, and ? is to reset a marked
        //spot as an unknown.
        for(i=0; i<yLength[0];i++){
            for(j=0; j<xLength[0]; j++){
                if(arrFile[i][j].length() == 4){
                    if(arrFile[i][j] == "[?x]" || arrFile[i][j] == "[?X]"){
                        path = 1;
                         yPoint = i;
                         xPoint = j;
                    }else if(arrFile[i][j] == "[?!]"){
                        path = 2;
                         yPoint = i;
                         xPoint = j;
                    }else if(arrFile[i][j] == "[!?]"){
                        path = 3;
                         yPoint = i;
                         xPoint = j;
                    }else{
                        //these two booleans control if the game can be played and why (if it cant be played)
                        cantPlay[0] = true;
                        gameOver[0] = true;
                        return arrFile;
                    }
                }
            }
        }
        //this part of the code implents that "paths" that were defined just before.
        if(arrMine[yPoint][xPoint] == "m" && path == 1){
            cantPlay[0] = true;
            gameOver[0] = true;
            arrMine[yPoint][xPoint] = "Boom";
             //if the game is over the mask is given back with [] put in, and the game is over       
             for(int k = 0; k<yLength[0];k++){
                 for(int l = 0; l<xLength[0];l++){
                     returnProcess[k][l] = "[" + arrMine[k][l] + "]";
                 }
             }
            
        }else if(path == 2){
            mCount[0]--;
            if(arrMine[yPoint][xPoint]== "m"){
            win[0]--;
            }
            returnProcess[yPoint][xPoint] = "[!]";
            return returnProcess;
            
        }else if(path == 3){
            mCount[0]++;
            if(arrMine[yPoint][xPoint]== "m"){
            win[0]++;
            }
            returnProcess[yPoint][xPoint] = "[?]";
        }
        //this is where the code deals with [ ] where it needs to keep searching for numbers around [].
        //we use a recursive method to let this process have no limit on how many times it can repeat
        if(path == 1 && cantPlay[0] == false){
            if(arrMine[yPoint][xPoint]== " "){
                returnProcess[yPoint][xPoint] = "[ ]";
                loopRecursion(xPoint, yPoint, arrMineExpanded, arrFile, returnProcess);
            }else {
                returnProcess[yPoint][xPoint] = "[" + arrMine[yPoint][xPoint] + "]";
            
            }
        }
        return returnProcess;
    
        
    }
//this is the recursion function, it is used only to help identify the numbers surrounding whitespace on the game board
    public static void loopRecursion(int xPoint, int yPoint, String [][]arrMineExpanded, String [][]arrFile, String [][]returnProcess){
        //the idea here is that the point where i and j are both zero is already defined as "[ ]" so that it never goes
        //into an infinite recursion loop and so that it goes in a 9 large box that goes from -1 0 1 three high.
        for(int i=(-1); i<2; i++){
            for(int j=(-1); j<2; j++){
                
                if((arrMineExpanded[yPoint+i][xPoint+j] == " ")&& returnProcess[yPoint+i][xPoint+j] == "[?]"){
                    returnProcess[yPoint+i][xPoint+j] = "[ ]";
                    loopRecursion(j, i, arrMineExpanded, arrFile, returnProcess);
                    //here we check to make sure that the value isnt m or * and that the return process hasnt been 
                    //checked yet
                }else if(arrMineExpanded[yPoint+i][xPoint+j]!="m" && 
                         arrMineExpanded[yPoint+i][xPoint+j]!="*" && 
                         returnProcess[yPoint+i][xPoint+j] == "[?]" ){
                    
                    returnProcess[yPoint+i][xPoint+j] = "[" + arrMineExpanded[yPoint+i][xPoint+j] + "]";
                
                
                }
            }
        }
    }

      
    
    
    
    
    
    
    
    
    
}