import java.util.*;
import java.io.*;
import java.awt.*;
import java.lang.*;
import java.nio.file.Files;

public class MineSweeper{
    
    public static void main(String [] args){
        
        gameFlowProcess();
        
    }
    public static void userPrompt(int [] xValue, int [] yValue, int [] win, int [] mCount, String [] advantageMode){
        //prompts
        String response = "";
        int canIGo = 0; 
        Scanner console = new Scanner(System.in);
        System.out.println("Would you like to play a game of minesweepers? Type yes to continue.");
        response = console.nextLine();
        System.out.println("To play mineSweepers, type and X after a ? in a  [ ]. \n Then save the file and it will ");
                              
        while(!(response.equalsIgnoreCase("yes"))){
            System.out.println("Are you sure you want to quit? Type yes to quit");
                response = console.nextLine();
                if(response.equalsIgnoreCase("yes")){
                    System.exit(-1);
                }else {
                    System.out.println("Would you like to play a game of minesweepers? Type yes to continue.");
                    response = console.nextLine();
                }
        }
        
        response = "no";
        while((!response.equalsIgnoreCase("yes")) || canIGo == 0){
            try{
                if(!response.equalsIgnoreCase("yes") || canIGo == 0){
                    System.out.println("How wide do you want the game board? Please type an integer value");
                    xValue[0] = console.nextInt();
                    canIGo = 1;
                }
            }catch(InputMismatchException imme){
                canIGo = 0;
                System.out.println("Please type an integer value");
                console.nextLine();
            
            }
       
            
            if(canIGo == 1){
                console.nextLine();
                System.out.println("You typed " +xValue[0] + " for your width. Is this the number you wanted? Type YES if it is");
            
                response = console.nextLine();
            
            }
        }
       response = "no";
       canIGo = 0;
       while((!response.equalsIgnoreCase("yes")) || canIGo == 0){
           try{
               if(!response.equalsIgnoreCase("yes") || canIGo == 0){
                   System.out.println("How tall do you want the game board? Please type an integer value");
                   yValue[0] = console.nextInt();
                   canIGo = 1;
               }
           }catch(InputMismatchException imme){
               canIGo = 0;
               System.out.println("Please type an integer value");
               console.nextLine();
               
           }
       
            
           if(canIGo == 1){
               console.nextLine();
               System.out.println("You typed " +yValue[0] + " for your height. Is this the number you wanted? Type YES if it is");
            
               response = console.nextLine();
            
           }
       }
        
       response = "no";
       canIGo = 0;
       while((!response.equalsIgnoreCase("yes")) || canIGo == 0){
           try{
               if(!response.equalsIgnoreCase("yes") || canIGo == 0){
                   System.out.println("How many mines do you want on the board?\nPlease type an integer value that is less then " + (xValue[0]*yValue[0] / 2) + ".");
                   mCount[0] = console.nextInt();
                   win[0] = mCount[0];
                   
                   while(mCount[0] > (xValue[0]*yValue[0] / 2)){
                  //     System.out.println("yValue[0]: " + yValue[0]);
                       System.out.println("You typed "  +mCount[0] + " which is too big.\n Please type an integer value that is lower then "+(xValue[0]*yValue[0] / 2) + "." );
                       mCount[0] = console.nextInt();
                       win[0] = mCount[0];
                   }
                   canIGo = 1;
               }
           }catch(InputMismatchException imme){
             //  System.out.println("yValue[0]: " + yValue[0]);
               canIGo = 0;
               System.out.println("Please type an integer value");
               console.nextLine();
               
           }
       
            
           if(canIGo == 1){
               console.nextLine();
               System.out.println("You typed " +mCount[0] + " for your mine count. Is this the number you wanted? Type YES if it is");
            
               response = console.nextLine();
            //System.out.println("yValue[0]: " + yValue[0]);
           }
       }
        
        
        if(xValue[0] > 8 && yValue[0] > 8 && (mCount[0] < (xValue[0]*yValue[0] / 2))){
        advantageMode[0] = "null";
        while(!advantageMode[0].equalsIgnoreCase("yes") && !advantageMode[0].equalsIgnoreCase("no")){
            System.out.println("Would you like to play on advantage mode?\n This guarantees that your first move will reveal atleast 1 empty space.");
            advantageMode[0] = console.nextLine();
            if(advantageMode[0].equalsIgnoreCase("yes") || advantageMode[0].equalsIgnoreCase("no")){
                System.out.println("you have selected " + advantageMode[0] + ".");
            }
            }
        }
        
        
        
    }    
    //this function takes in variables with specifications for the game board, and primarily takes in an array
    //the array is a set of strings that will make up the meat of the minesweepers.
    //after the array has been proccessed it is sent here to be turned back into a file.
    //also controls the format of the file
    public static File fileProcess(int [] xValue, int [] yValue, int [] mCount, String [] advantageMode, String [][] arr, String name){
        //here we are creating a file for the game board to be printed in
       
       File fileNew = new File(name);
        try{
            
        fileNew.createNewFile();
            
           }catch(IOException ioe){
            System.exit(-1);
        }
           try{
        PrintStream output = new PrintStream(fileNew);
           //the title and the mine count are preset - without spaces before or after them they are 26 characters long always
        String title = "MineSweepers";
        //defining a counter variable
        int i = 0;
        //the mine string is part of the title string, it includes the number of mines counted
        String mine = " Mine Count: " + mCount[0];
        //the array for the boxes is created here. 
       // String [][] boxes = new String [yValue[0]][xValue[0]];
       // output.print( title.length() + mine.length());
        
        //initialization of the variable that defines the spaces inbetween the title
        int midSection = 0;
        //intialization of the variable that defines the counter of spaces before the boxes are put in
        int frontSection = 0;
        //this decides if the board needs to be centered around a ? or a ][ space based on if the x length is even or odd
        if(xValue[0] %2 == 1){
            midSection = 0;
        }else {
            midSection = 1;
        }
        //this decides if the format is decided based on the title or the xvalue length - we x3 because its 3 characters
        //per xValue
        if(title.length() + mine.length() > xValue[0]*3){
            output.print(title);
            frontSection = 12 + midSection - (xValue[0]*3 /2); 
            for(i=0; i<midSection; i++){
                output.print(" ");
            }
            output.println(mine);
            
        for(i = 0; i<yValue[0]; i++){
            output.println("");
            for(int k = 0; k<frontSection; k++){
            output.print(" ");
            }
          //  int num =(xValue[0]/2)*3+1;
           // for(int c=0; c<num; c++){
           //   output.print(" ");
           // }
            for(int j = 0; j<xValue[0]; j++){
                
              //  boxes[i][j] = arr[i][j];
                output.print(arr[i][j]);
                
                    
                }
        }
         //  output.print("\n"); 
          
            
        }else {
          //  System.out.println("xValue[0]: " + xValue[0]);
          //  System.out.println("yValue[0]: " + yValue[0]);
            frontSection = 1+(((xValue[0]*3) -(title.length()+mine.length()+midSection))/2);
          //  System.out.println("fronSection: " + frontSection);
            for(i = 0; i<frontSection; i++){
                output.print(" ");
            }
            output.print(title);
            for(i = 0; i<midSection; i++){
                output.print(" ");
            }
            output.println(mine);
            //this is where i need to put if the length is greater then the title, so i need to reconfigure the 
            //if statement up above. 
                 for(i = 0; i<yValue[0]; i++){
            output.println("");
          //  int num =(xValue[0]/2)*3+1;
           // for(int c=0; c<num; c++){
           //   output.print(" ");
           // }
            for(int j = 0; j<xValue[0]; j++){
                
                //boxes[i][j] = arr[i][j];
                output.print(arr[i][j]);
                
                    
                }
        }
         //  output.print("\n"); 
        }
       
        
        output.close();
        }catch(FileNotFoundException fnfe){
               System.exit(-1);
           }
        
         return fileNew;
    
    }
    //this function will output random numbers for an xvalue and a yvalue within their ranges
     public static int randomMine(Random generator, int xValue[], int yValue[], Boolean isY){
         int i = 0;
         
        if(isY == false){
            i = generator.nextInt(xValue[0]);
            return i;
        } else {
            i = generator.nextInt(yValue[0]);
            return i;
        }
     }
    
         //this function will return an array with bombs and numbers put down.
     // it will be used with the userfile array to forward gameplay
     //it takes in the standard user variables as well as a random generator
        public static String[][] mineProcess(int [] xValue, int [] yValue, int [] mCount, Random generator, String [] advantageMode, String [][] arr){
            
            //Variables being declared with a full scope
            Point mCountArray[] = new Point [mCount[0]+1];
            int mineNumber = 0;
            int k = 0;
            int j = 0;
            int i = 0;
            int n = 0;
            int p = 0;
            int q = 0;
            int pOne=0;
            int qOne=0;
            int counter = 0; 
            //Creating the return string that will store the meat of the game (Mines and numbers)
            
            String[][]fillTable = new String[yValue[0]][xValue[0]];
          //   System.out.println("test5");
          
            // This loop is for i, which is the y axis of the game board
            
             for(i=0;i<yValue[0];i++){
                 
                 //this loop is for j, which is the x acis of the game board
                 
                 for(j=0;j<xValue[0];j++){
                     
                     //will go through the whole table, looking for X and filling anything in with ? if it cant find
                     //and X. When it finds the X it saves its location, so that the advantageMode can be done.
                     
                     if((!arr[i][j].equals("[?X]")) && !(arr[i][j].equals("[?x]"))){
                         fillTable[i][j]="?";
                        // System.out.println("i and j: " + i + " " + j);
                     } else {
                         fillTable[i][j] ="?";
                         mCountArray[mCount[0]] = new Point();
                         mCountArray[mCount[0]].x =  j;
                         mCountArray[mCount[0]].y =  i;
                     }
                 }
             }
            // System.out.println("test6");
             
             //This loop will create mine locations which is based on the value of mCount, which is a user input.
           //  System.out.println("here");
            for(i=0;i<mCount[0];i++){
                //random function being called to produce a value
                 k = randomMine(generator, xValue, yValue, true);
             //    System.out.print(k);
                 
                 n = randomMine(generator, xValue, yValue, false);
              //   System.out.print(n);
                 
                 //Test for statement to see if a mine already exists at the random location
                 
                 if(fillTable[k][n].equals("?") && (!(arr[k][n].equals("[?X]") && !(arr[k][n].equals("[?x]"))))){
                   
                    fillTable[k][n] = "m";
                    mCountArray[i] = new Point();
                    mCountArray[i].x = n;
                    mCountArray[i].y = k;
                    if(advantageMode[0].equalsIgnoreCase("yes")){
                        if(!((Math.abs(mCountArray[mCount[0]].x - n) >= 2) && (Math.abs(mCountArray[mCount[0]].y - j) >= 2 ))){
                           i--;
                           fillTable[k][n] = "?";
                        }
                        
                    }
                 }else {
                     i--;
                 }
            }
            //this loop will go through the x-axis of the table, checking to see how many mines surround each location
            // System.out.println("here");
            for(i=0;i<yValue[0];i++){
             //   System.out.println("testI"+i);
                //This loop is the y-axis location of the table, together with the x-axis it checks for all mines in the
                //surrounding area
                for(n=0;n<xValue[0];n++){
                  //  System.out.println("testN"+n);
                  //  fillTable[0][1] = "m";
                 //   fillTable[0][3] = "m";
                //   fillTable[2][1] = "m";
                //   fillTable[3][1] = "m";
                    //h is the variable that defines what the number in each square will be. if its 0 there is no value
                    //and it can go to eight. it is defined here because its scope is within the y-axis loop
                    int h =0;
                    //each of these checks are for when the values of the array exceed the scope of the area (-1 
                    // or greater then length-1)
                     
                    int check =  0;
                    int check2 = 0;
                    int check3 = 0;
                    int check4 = 0;
                    //this if loop checks to make sure that the location that is to be tested doesnt have a mine in it
                    //if it does have a mine it just skips it
                   
                    if(!(fillTable[i][n].equals("m"))){
                      //  System.out.println("test3");
                    
                    //these test are for when i is 0 or n is 0 and becomes -1 or when the values become greater then the
                    //length of the array
                    p = i-1;
                    pOne = i+1;
                    qOne = n+1;
                    q = n-1;
                    
                    //these if loops all change the situations to become either length -1 for when it exceeds (goes past) the bounds
                    //of the array or 0 if it falls off (goes below) the bounds of the array. 
                    
                    if(pOne==yValue[0]){
                        pOne = yValue[0] - 1;
                      
                        check = 1;
                    }
                    if(qOne == xValue[0]){
                        qOne = xValue[0] - 1;
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
                  //  System.out.println("test4");
                    
                         
                     //this tests to see if there is a mine at the spot at one of the 8 places around the target location
                    //if there is a mine spot it will increase the integer value at that location by 1. it also checks
                    //to make sure that the tests for p q pone and qone have all been passed
                    
                         if(fillTable[pOne][n].equals("m") && check!=1){
                             h++;
                         }
                         if(fillTable[pOne][q].equals("m") && check!=1 && check4!=1 ){
                             h++;
                         }
                         if(fillTable[p][q].equals("m") && check3!=1 && check4!=1){
                             h++;
                         }
                         if(fillTable[p][qOne].equals("m") && check3!=1 && check2!=1){
                             h++;
                         }
                         if(fillTable[pOne][qOne].equals("m") && check!=1 && check2!=1){
                             h++;
                         }
                         if(fillTable[p][n].equals("m") && check3!=1){
                             h++;
                         }
                         if(fillTable[i][qOne].equals("m") && check2!=1){
                             h++;
                         }
                         if(fillTable[i][q].equals("m") && check4!=1){
                             h++;
                         }
                     
                    //this just fills the table with a space. it needs to fill it because the else statment for +h would
                    // make the secions without any nearby mines zero instead of a space, which would look stupid.
                         if(h==0){
                             fillTable[i][n] = " ";
                         }else{
                         fillTable[i][n] = ""+h;
                         
                      //   System.out.println("none mine: " +h);
                         }
                    }
                }
            } 
            /*
            for( i = 0; i<yValue[0];i++){
            System.out.println("");
            
                    for( j = 0; j<xValue[0];j++){
                        System.out.print(fillTable[i][j]);
                    }
        }
        */
            return fillTable;
        }
        
        
         //this function takes in a file name and 2 integer values and will scan a file and return an array that 
    //will contain the [ ] and their interiors of the gameboard.
    
    //make sure to change the int values to int arrays in the main document
    public static String [][] fileToStringArray(String file, int[] xValue, int []yValue, Boolean[] cantPlay){
        //this is where the array is created
        String [][] arr = new String [yValue[0]][xValue[0]];
        //we call this irrelevant because it felt fitting because what is in there beforehand is irrelevant as long
        //as it isnt "["
        String board = "irrelevant";
        //this breaks a loop - which needs to also cause the file to not go through - edit this eventually!****
       
        
        int f = 0;
        int k = 0;
        int g = 0;
        int y = 0;
        int n = 0;
        try{
            Scanner console = new Scanner(new File(file));
            
        //this makes sure that the only thing that is scanned has to do with the game board and not the title
       //     while(board.substring(n,n+1).equals(" "){
          //      n++;
         //   }
            
            int nCount = 0;
            while(!(board.substring(nCount,nCount+1).equals("["))){
                // System.out.println("substring at"+nCount+"isnt [ so going into while loop");
                if(board.substring(nCount,nCount+1).equals(" ")){
                  //  System.out.println("substring at"+nCount+ "is a space so ncount ++");
                    nCount++;
                }else{
                  //   System.out.println("going to next line after [ wasnt found but it wasnt a space at "+nCount);
                board = "";
                board = console.nextLine();
                
                if(board.length() <2){
                    board = console.nextLine();
                    
                }
               nCount = 0; 
            }
                   
                   }
            for(int i = 0; i<yValue[0]; i++){
                y=0;
                //if there are two points where there are two chracters in [ ] then the program is suppose to 
                //send an error message to the user - WIP
                if(cantPlay[0] == true){
                  //  System.out.print("happend");
                    return arr;
                    
                }
                //to make the scanner move to the next row
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
                    
                    while(board.charAt(nCount+g+((j*3))) != ']'){
                       // System.out.println("charAT value: " + board.charAt(g+((j*3))));
                        arr[i][j] += board.charAt(nCount+g+((j*3)));
                        k++;
                        g++;
                        if(k>2){
                          //  System.out.println("at i,j: "+i+", "+j);
                            f++;
                            y++;
                        }
                    }
                    if(f==2){
                      //  System.out.print("happend");
                        cantPlay[0] = true;
                    }
                    if(g==1){
                        arr[i][j] = "[ ]";
                    }else{
                        arr[i][j] += board.charAt(nCount+g+((j*3)));
                    }
                }
            }
        }catch(FileNotFoundException fnfe){
            System.exit(1);
        }
        /*
         for(int i = 0; i<yValue[0];i++){
            System.out.println("");
            
                    for( int j = 0; j<xValue[0];j++){
                        System.out.print(arr[i][j]);
                    }
         }
        
        */
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
    public static String[][] gameProcess(String[][]arrMine, String[][]arrFile, int[] xValue, int[] yValue, int[] mCount, int[] win, Boolean[] cantPlay, Boolean[] gameOver){
        //this is where the variables for the function are initially defined
     //   System.out.println("entered gameProcess");
      // System.out.println(Arrays.deepToString(arrFile));
        String[][] returnProcess = new String [yValue[0]][xValue[0]];
        String[][] arrMineExpanded = new String[yValue[0]+2][xValue[0]+2];
        String[][] returnProcessExpanded = new String [yValue[0]+2][xValue[0]+2];
        int i = 0;
        int j = 0;
       
        int path=10;
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
        /*
         for( i = 0; i<yValue[0];i++){
            System.out.println("");
            
                    for( j = 0; j<xValue[0];j++){
                        System.out.print(arrFile[i][j]);
                    }
         }
         
         */
        for(i=0; i<yValue[0];i++){
            for(j=0; j<xValue[0]; j++){
                if(arrFile[i][j].length() == 4){
                    if(arrFile[i][j].equals("[?x]") || arrFile[i][j].equals("[?X]")){
                        path = 1;
                         yPoint = i;
                         xPoint = j;
                    }else if(arrFile[i][j].equals("[?!]")){
                        path = 2;
                         yPoint = i;
                         xPoint = j;
                    }else if(arrFile[i][j].equals("[!?]")){
                        path = 3;
                         yPoint = i;
                         xPoint = j;
                    
                    }
                }
            }
            
        }
        if(path ==10){
                cantPlay[0] = true;
               // System.out.println("this is where its going true 1");
               // System.out.println("returned after path still was 10");
                 return arrFile;
            }
        //this part of the code implents that "paths" that were defined just before.
        if(arrMine[yPoint][xPoint].equals("m") && path == 1){
             gameOver[0] = true;
            arrMine[yPoint][xPoint] = "B";
             //if the game is over the mask is given back with [] put in, and the game is over       
             for(int k = 0; k<yValue[0];k++){
              
                 for(int l = 0; l<xValue[0];l++){
                     returnProcess[k][l] = "[" + arrMine[k][l] + "]";
                 }
             }
             return returnProcess;
       }else if(path == 2){
            mCount[0]--;
            if(arrMine[yPoint][xPoint].equals("m")){
            win[0]--;
            }
            returnProcess[yPoint][xPoint] = "[!]";
            if(win[0] == 0){
               for(int k = 0; k<yValue[0];k++){
              
                 for(int l = 0; l<xValue[0];l++){
                     returnProcess[k][l] = "[" + arrMine[k][l] + "]";
                 }
             }
             return returnProcess; 
            }
           // System.out.println("returned cause of something that shouldent happen");
            return returnProcess;
            
        }else if(path == 3){
            mCount[0]++;
            if(arrMine[yPoint][xPoint].equals("m")){
            win[0]++;
            }
            returnProcess[yPoint][xPoint] = "[?]";
        }
        //this is where the code deals with [ ] where it needs to keep searching for numbers around [].
        //we use a recursive method to let this process have no limit on how many times it can repeat
         if(path == 1 && cantPlay[0] == false){
            if(arrMine[yPoint][xPoint].equals(" ")){
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
         /*
         for( i = 0; i<yValue[0];i++){
            System.out.println("");
            
                    for( j = 0; j<xValue[0];j++){
                        System.out.print(returnProcess[i][j]);
                    }
        }
        */
        return returnProcess;
    
        
    }
    
        
    
public static void loopRecursion(int xPoint, int yPoint, String [][]arrMineExpanded, String [][]arrFile, String [][]returnProcessExpanded){
        //the idea here is that the point where i and j are both zero is already defined as "[ ]" so that it never goes
        //into an infinite recursion loop. It goes in a 9 large box that goes from -1 0 1 three high.
       
            
        for(int i=(-1); i<2; i++){
            for(int j=(-1); j<2; j++){
                if((arrMineExpanded[yPoint+i][xPoint+j].equals(" "))&& returnProcessExpanded[yPoint+i][xPoint+j].equals("[?]")){
                    returnProcessExpanded[yPoint+i][xPoint+j] = "[ ]";
                    loopRecursion(xPoint+j, yPoint+i, arrMineExpanded, arrFile, returnProcessExpanded);
                    //here we check to make sure that the value isnt m or * and that the return process hasnt been 
                    //checked yet
                }else if(arrMineExpanded[yPoint+i][xPoint+j]!="m" && 
                         arrMineExpanded[yPoint+i][xPoint+j]!="*" && 
                         returnProcessExpanded[yPoint+i][xPoint+j].equals("[?]")){
                    
                    returnProcessExpanded[yPoint+i][xPoint+j] = "[" + arrMineExpanded[yPoint+i][xPoint+j] + "]";
                
                
                }
            }
        }
    } 

    public static void gameFlowProcess(){
            Random generator = new Random();
            Scanner console = new Scanner(System.in);
            
            
            //here we will declare all the global variables that are sent through and/or changed during most of the functions
            //these are set as arrays so that the changes made to them in one function will be applied to the variable
            //accross all functions without having to return it.
            
            //file
            File file = new File("MineSweeper.txt");
            
            //int array variables
           
            int [] xValue = new int [1];
            xValue[0] = 0;
            int [] yValue = new int [1];
            yValue[0] = 0;
            int [] mCount = new int [1];
            mCount[0] = 1;
            int [] win = new int [1];
            win[0] = 1;
            int gameCounter = 0;
            
            //Boolean array variables
            Boolean [] cantPlay = new Boolean [1];
            cantPlay[0] = false;
            Boolean[] gameOver = new Boolean [1];
            gameOver[0] = false;
            
            //String array variables
            //not 100% sure if this will be implemented
            String [] advantageMode = new String[1];
            advantageMode[0] = "null";
            
            
            userPrompt(xValue, yValue, win, mCount, advantageMode);
            //the array thats in the file
            String [][] fileArray = new String [yValue[0]][xValue[0]];
            
            String [][] backUpFileArray = new String [yValue[0]][xValue[0]];
            //the mask array
            String [][] mineArray = new String [yValue[0]][xValue[0]];
            //the proccessed array that returns to the file
            String [][] arrFlow = new String [yValue[0]][xValue[0]];
            
            for(int i = 0; i<yValue[0]; i++){
                for(int j = 0; j<xValue[0]; j++){
                    arrFlow[i][j] = "[?]";
                }
            }
                   
              //System.out.println("check 1");  
            
            while(gameOver[0] != true && win[0] != 0){
                
                String mineSweeper = "MineSweeper.txt";
              
                do{
                   
                
                //    System.out.println("check 2"); 
                    //this is where problems are
                    /*
                    if(cantPlay[0] == true){
                       count++;
                       if(count %2 != 0){
                           String hold = mineSweeper;
                           mineSweeper = mineSweeperBackUp;
                           mineSweeperBackUp = hold;
                       }else {
                           String hold = mineSweeperBackUp;
                           mineSweeperBackUp = mineSweeper;
                           mineSweeper = hold;
                       }
                    }
                    File fileTwo = fileProcess(xValue, yValue, mCount, advantageMode, arrFlow, mineSweeper);
                    */
                    file =  fileProcess(xValue, yValue, mCount, advantageMode, arrFlow, mineSweeper);
                    if(cantPlay[0] == true && gameOver[0] == false){
                        
                 //   System.out.println("check 3");
                       // System.out.println("1cantPlay[0]: "+cantPlay[0]+ " gameOver[0]: "+gameOver[0]);
                        System.out.println("Please follow the rules, start again, from your last turn");
                        file = fileProcess(xValue, yValue, mCount, advantageMode, backUpFileArray, mineSweeper);
                        cantPlay[0] = false;
                    }
                    backUpFileArray = fileToStringArray("MineSweeper.txt", xValue, yValue, cantPlay);
                  // System.out.println("2cantPlay[0]: "+cantPlay[0]+ " gameOver[0]: "+gameOver[0]);
                    /*
                    try{ 
                      //  fileTwo.createNewFile();
                         file.createNewFile();
                    }catch(IOException ioe){
                         
                         System.out.println("exit 0");
                         System.exit(-1);
                     }
                    //need to fix everything above this    
                    */
                    if(gameOver[0] == true){
                        
                 //   System.out.println("check 4");
                     break;   
                    }
                     try{
                         int sentinal = 0;   
                         Runtime run = Runtime.getRuntime(); 
                         Process pp=run.exec("Notepad.exe"+" " +mineSweeper);

                         long fileLength = file.length();
                         
                         while(sentinal == 0){
                             try{
                              //   System.out.println("pass");
                                 Thread.sleep(2000);
                             }catch(InterruptedException ex){
                                 Thread.currentThread().interrupt();
                             }
                             if(file.length() != fileLength){
                                 pp.destroy();
                                 sentinal = 1;
                             }
            
            
                         }
                         
                     }catch(IOException ioe){
                         
                         System.out.println("exit 1");
                         System.exit(-1);
                     }
                     
                     fileArray = fileToStringArray("MineSweeper.txt", xValue, yValue, cantPlay);
                 //    System.out.println(" 3cantPlay[0]: "+cantPlay[0]+ " gameOver[0]: "+gameOver[0]);
                 } while(cantPlay[0] == true);
                 
                 gameCounter++;
                 if(gameCounter == 1){
                     mineArray = mineProcess(xValue, yValue, mCount, generator, advantageMode, fileArray);
                //      System.out.println(" 4cantPlay[0]: "+cantPlay[0]+ " gameOver[0]: "+gameOver[0]);
                 }
            //     System.out.println("past mineArray");
                 
                     String [][] hold = gameProcess(mineArray, fileArray, xValue, yValue, mCount, win, cantPlay, gameOver); 
                    //  System.out.println(" 5cantPlay[0]: "+cantPlay[0]+ " gameOver[0]: "+gameOver[0]);
                 for( int i = 0; i<yValue[0];i++){
                    // System.out.println("");
                     
                     for( int j = 0; j<xValue[0];j++){
                         arrFlow[i][j] = hold[i][j];
                     }
                 }
                 
             //    System.out.println("past arrflow Array");
                }
                
                
                file =  fileProcess(xValue, yValue, mCount, advantageMode, arrFlow, "MineSweeper.txt");
                if(win[0] == 0){
                    try{
                        Runtime run = Runtime.getRuntime(); 
                        Process pp=run.exec("Notepad.exe"+ " "+file);
                    }
                    catch(IOException ioe){
                        System.out.println("exit 2");
                        System.exit(-1);
                    }
                    System.out.print("You won! it took you: " + (gameCounter-1) + " turns to win");  
                    
                }else {
                    try{
                        Runtime run = Runtime.getRuntime(); 
                        Process pp=run.exec("Notepad.exe"+" " +file);
                    }
                    catch(IOException ioe){
                        System.exit(-1);
                      
                    }
                    System.out.print("You lose. Game lasted " + (gameCounter-1) + " turns before you lost. Good luck next time");
                    
                }
    }      
    }