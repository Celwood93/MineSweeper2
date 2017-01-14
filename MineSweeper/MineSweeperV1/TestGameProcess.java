import java.util.*;
import java.io.*;
import java.lang.*;
public class TestGameProcess{
    //public static String[][] gameProcess(String[][]arrMine, String[][]arrFile, int[] xValue, int[] yValue, 
    // int[] mCount, int[] win, Boolean[] cantPlay, Boolean[] gameOver){
    
    public static void main(String[]args){
        
        String[][]filler = testGameProcess();
        
    }
    
    
    
    public static String[][] testGameProcess(){
        
         int [] xValue = new int [1];
        xValue[0] = 10;
        
         int [] mCount = new int [1];
       mCount[0] = 15;
       
        int [] yValue = new int [1];
        yValue[0] = 10;
        
        int [] win = new int [1];
        win[0] = 15;
        
        Boolean [] gameOver = new Boolean [1];
            gameOver[0] = false;
        
        Boolean [] cantPlay = new Boolean [1];
            cantPlay[0] = false;
            
        String[][] arrFile = new String [yValue[0]][xValue[0]];
        for(int b = 0; b<yValue[0];b++){
            for(int c = 0; c<xValue[0];c++){
                if(b==6 && c == 5){
                    arrFile[b][c] = "";
                    arrFile[b][c] = "[?X]";
                }else{
                arrFile[b][c] = "";
                arrFile[b][c] = "[?]";
                }
            }
        }
        String[][] arrMine = makeArrMine();
         
        //real code starts here
        
        
        String[][] returnProcess = new String [yValue[0]][xValue[0]];
        String[][] arrMineExpanded = new String[yValue[0]+2][xValue[0]+2];
        String[][] returnProcessExpanded = new String [yValue[0]+2][xValue[0]+2];
        int i = 0;
        int j = 0;
        //path is set to 10 for no specific reason - it will be changed
        int path = 10;
        int yPoint = 0;
        int xPoint = 0;
        //this is where we set the return process to be equal to what the arrFile is
        //this is done because what is returned will be based off of what has already been done
        for( i = 0; i<yValue[0];i++){
            for( j = 0; j<xValue[0];j++){
                returnProcess[i][j] = arrFile[i][j];
            }
        }
        //this is to create a boarder of *'s around the mine mask, which will make sure that the boundaries are not
        //exceeded during the testing process
        for( i = 0; i<yValue[0]+2;i++){
            for( j = 0; j<xValue[0]+2;j++){
                if(i==0 || j==0 || i== yValue[0]+1 || j==xValue[0]+1){
                    arrMineExpanded[i][j] = "*";
                }else{
                    arrMineExpanded[i][j] = arrMine[i-1][j-1];
                }
            }
        }
        //this is testing to see where the point of action in the document will take place
        //the loops look for either an x an ! or an ? after the values of ? or !.
        //this decideds what will be done to the point of selection. 
        //X is to trigger a click on the location, ! is to label a location as a mine spot, and ? is to reset a marked
        //spot as an unknown.
        for(i=0; i<yValue[0];i++){
            for(j=0; j<xValue[0]; j++){
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
                    }
                }
            }
        }
        if(path == 10){
          cantPlay[0] = true;
          return arrFile;   
        }
        //this part of the code implents that "paths" that were defined just before.
        if(arrMine[yPoint][xPoint] == "m" && path == 1){
            cantPlay[0] = true;
            gameOver[0] = true;
            arrMine[yPoint][xPoint] = "Boom";
             //if the game is over the mask is given back with [] put in, and the game is over       
             for(int k = 0; k<yValue[0];k++){
                
                 for(int l = 0; l<xValue[0];l++){
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
                
                for( i = 0; i<yValue[0]+2;i++){
                    for( j = 0; j<xValue[0]+2;j++){
                        if(i==0 || j==0 || i== yValue[0]+1 || j==xValue[0]+1){
                            returnProcessExpanded[i][j] = "*";
                        }else{
                            returnProcessExpanded[i][j] =  returnProcess[i-1][j-1];
                        }
                    }
                }
                
                xPoint = xPoint+1;
                yPoint = yPoint+1;
                loopRecursion(xPoint, yPoint, arrMineExpanded, arrFile, returnProcessExpanded);
                xPoint = xPoint-1;
                yPoint = yPoint-1;
                
                for( i = 0; i<yValue[0]+2;i++){
                    for( j = 0; j<xValue[0]+2;j++){
                        if(!(i==0 || j==0 || i== yValue[0]+1 || j==xValue[0]+1)){
                           
                        returnProcess[i-1][j-1] = returnProcessExpanded[i][j];
                           
                        }
                    }
                }
                
            }else {
                returnProcess[yPoint][xPoint] = "[" + arrMine[yPoint][xPoint] + "]";
            
            }
        }
        for( i = 0; i<yValue[0];i++){
            System.out.println("");
            
                    for( j = 0; j<xValue[0];j++){
                        System.out.print(returnProcess[i][j]);
                    }
        }
        return returnProcess;
        
    
        
    }

        
        
    
        
   
        
        
   //this is the recursion function, it is used only to help identify the numbers surrounding whitespace on the game board
    public static void loopRecursion(int xPoint, int yPoint, String [][]arrMineExpanded, String [][]arrFile, String [][]returnProcessExpanded){
        //the idea here is that the point where i and j are both zero is already defined as "[ ]" so that it never goes
        //into an infinite recursion loop and so that it goes in a 9 large box that goes from -1 0 1 three high.
       
            
        for(int i=(-1); i<2; i++){
            for(int j=(-1); j<2; j++){
                if((arrMineExpanded[yPoint+i][xPoint+j] == " ")&& returnProcessExpanded[yPoint+i][xPoint+j] == "[?]"){
                    returnProcessExpanded[yPoint+i][xPoint+j] = "[ ]";
                    loopRecursion(xPoint+j, yPoint+i, arrMineExpanded, arrFile, returnProcessExpanded);
                    //here we check to make sure that the value isnt m or * and that the return process hasnt been 
                    //checked yet
                }else if(arrMineExpanded[yPoint+i][xPoint+j]!="m" && 
                         arrMineExpanded[yPoint+i][xPoint+j]!="*" && 
                         returnProcessExpanded[yPoint+i][xPoint+j] == "[?]" ){
                    
                    returnProcessExpanded[yPoint+i][xPoint+j] = "[" + arrMineExpanded[yPoint+i][xPoint+j] + "]";
                
                
                }
            }
        }
    }      
        
    public static String[][] makeArrMine(){
        String[][] arrMine = new String [10][10];
        arrMine[0][0] = " ";
        arrMine[0][1] = " ";
        arrMine[0][2] = " ";
        arrMine[0][3] = " ";
        arrMine[0][4] = "1";
        arrMine[0][5] = "m";
        arrMine[0][6] = "1";
        arrMine[0][7] = " ";
        arrMine[0][8] = "1";
        arrMine[0][9] = "m";
        
         arrMine[1][0] = " ";
        arrMine[1][1] = "1";
        arrMine[1][2] = "1";
        arrMine[1][3] = "1";
        arrMine[1][4] = "1";
        arrMine[1][5] = "2";
        arrMine[1][6] = "3";
        arrMine[1][7] = "2";
        arrMine[1][8] = "2";
        arrMine[1][9] = "1";
        
         arrMine[2][0] = " ";
        arrMine[2][1] = "2";
        arrMine[2][2] = "m";
        arrMine[2][3] = "2";
        arrMine[2][4] = "1";
        arrMine[2][5] = "2";
        arrMine[2][6] = "m";
        arrMine[2][7] = "m";
        arrMine[2][8] = "1";
        arrMine[2][9] = " ";
        
         arrMine[3][0] = " ";
        arrMine[3][1] = "2";
        arrMine[3][2] = "m";
        arrMine[3][3] = "2";
        arrMine[3][4] = "1";
        arrMine[3][5] = "m";
        arrMine[3][6] = "4";
        arrMine[3][7] = "3";
        arrMine[3][8] = "2";
        arrMine[3][9] = " ";
        
         arrMine[4][0] = "1";
        arrMine[4][1] = "2";
        arrMine[4][2] = "1";
        arrMine[4][3] = "1";
        arrMine[4][4] = "1";
        arrMine[4][5] = "1";
        arrMine[4][6] = "2";
        arrMine[4][7] = "m";
        arrMine[4][8] = "1";
        arrMine[4][9] = " ";
        
         arrMine[5][0] = "m";
        arrMine[5][1] = "2";
        arrMine[5][2] = "2";
        arrMine[5][3] = "1";
        arrMine[5][4] = "1";
        arrMine[5][5] = " ";
        arrMine[5][6] = "1";
        arrMine[5][7] = "1";
        arrMine[5][8] = "1";
        arrMine[5][9] = " ";
        
         arrMine[6][0] = "2";
        arrMine[6][1] = "m";
        arrMine[6][2] = "2";
        arrMine[6][3] = "m";
        arrMine[6][4] = "1";
        arrMine[6][5] = " ";
        arrMine[6][6] = " ";
        arrMine[6][7] = " ";
        arrMine[6][8] = "1";
        arrMine[6][9] = "1";
        
         arrMine[7][0] = "1";
        arrMine[7][1] = "1";
        arrMine[7][2] = "2";
        arrMine[7][3] = "2";
        arrMine[7][4] = "2";
        arrMine[7][5] = "1";
        arrMine[7][6] = " ";
        arrMine[7][7] = "1";
        arrMine[7][8] = "2";
        arrMine[7][9] = "m";
        
         arrMine[8][0] = " ";
        arrMine[8][1] = " ";
        arrMine[8][2] = " ";
        arrMine[8][3] = "1";
        arrMine[8][4] = "m";
        arrMine[8][5] = "1";
        arrMine[8][6] = " ";
        arrMine[8][7] = "1";
        arrMine[8][8] = "m";
        arrMine[8][9] = "3";
        
         arrMine[9][0] = " ";
        arrMine[9][1] = " ";
        arrMine[9][2] = " ";
        arrMine[9][3] = "1";
        arrMine[9][4] = "1";
        arrMine[9][5] = "1";
        arrMine[9][6] = " ";
        arrMine[9][7] = "1";
        arrMine[9][8] = "2";
        arrMine[9][9] = "m";
        
        return arrMine;
    }
    
    
    
    
}