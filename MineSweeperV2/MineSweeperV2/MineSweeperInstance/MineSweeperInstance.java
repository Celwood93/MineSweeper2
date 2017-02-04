import java.util.*;
import java.io.*;
import java.awt.Point;
public class MineSweeperInstance{
	private CellHold [][] map;
	private MSData instance;
	

	public MineSweeperInstance(MSData instance){
		map = MSMap.makeMap(instance);
		this.instance = instance;
		
	}
	
	public void run(){ //might return instance if need be.
		boolean notLost = true;
		String errorMessage = "";
		CellHold guiPoint = new CellHold(0);
		while(notLost && instance.getBombs() != 0){
			guiPoint = Gui.promptUserInput(this.map, errorMessage, instance);
			errorMessage = checkPoint(guiPoint);
			//if error message != "" then dont update map** use for properly running code / debugging purposes.
			if(errorMessage == ""){
				notLost = updateMap(guiPoint);
			}
		}
		
		instance.setfMap(map);
		if(instance.getBombs() == 0){
			instance.setWon(true);
		}
		
	}
	public String printFMap(){
		File file;
		String newString = "";
		newString = newString + "\r\n{{";
		for(int i = 0; i < map.length; i++){
			if(i!=0){
				newString = newString + ",\r\n{";
			}
			for(int j = 0; j < map[0].length-1; j++){
				newString = newString + map[i][j].getCellStatus() + ",";
			}
			newString = newString + map[i][map[0].length-1].getCellStatus();
			newString = newString + "}";
		}
		newString = newString + "}";
		try{
			PrintStream output = new PrintStream(file = new File("copyMe.txt"));
			output.print(newString);
			output.close();
       }catch(FileNotFoundException fnfe){
           return "-1";
       }
       
		return newString;
	}
	public MSData getInstance(){
		return instance;
	}
	
	public CellHold [][] getMapCellHold(){
		return map;
	}
	
	public int [][] getMapInt(){
		return MSMap.makeMapInt(map);
	}
	
	public String checkPoint(CellHold point){
		String message = "";
		//checks to see that the point isnt already selected or some other error. if it has, it returns an error message, if not, it returns and empty string. reword later.
		if((map[point.getX()][point.getY()].getCellStatus() == 0 && (point.getCellStatus() == 1|point.getCellStatus() == 2))|
			(map[point.getX()][point.getY()].getCellStatus() == 2 && point.getCellStatus() == 3)){
				//short cut
			return message;
		}
		 if(map[point.getX()][point.getY()].getCellStatus() == 1 && point.getCellStatus() == 1){
			message = "1 -> 1";
		}else if(map[point.getX()][point.getY()].getCellStatus() == 0 && point.getCellStatus() == 3){
			message = "0 -> 0";
		}else if(map[point.getX()][point.getY()].getCellStatus() == 1 && point.getCellStatus() == 3){
			message = "1 -> 0";
		}else if(map[point.getX()][point.getY()].getCellStatus() == 1 && point.getCellStatus() == 2){
			message = "1 -> 2";
		}else if(map[point.getX()][point.getY()].getCellStatus() == 2 && point.getCellStatus() == 1){
			message = "2 -> 1";
		}else if(map[point.getX()][point.getY()].getCellStatus() == 2 && point.getCellStatus() == 2){
			message = "2 -> 2";
		}else if(point.getCellStatus() > 3){
			message = "DNE+";
		}else if(point.getCellStatus() < 0){
			message = "DNE-";
		}
		return message;
	}
	public boolean updateMap(CellHold guiPoint){
		if(guiPoint.getCellStatus() == 3){ //unknown flag shouldent be able to be on a visited spot (status of 1). should error handle before if so.
			if(map[guiPoint.getX()][guiPoint.getY()].getCellStatus() == 2){
				instance.incBombCounter(1); //display bomb count incremented
					if(map[guiPoint.getX()][guiPoint.getY()].getValue() == 9){ //is it really a bomb?
						instance.incBomb(1);//actual bomb count incremented
					}
			}
			map[guiPoint.getX()][guiPoint.getY()].setCellStatus(0); //set as univisited
			return true;
		}
		map[guiPoint.getX()][guiPoint.getY()].setCellStatus(guiPoint.getCellStatus());
		if(map[guiPoint.getX()][guiPoint.getY()].getValue() == 9 && map[guiPoint.getX()][guiPoint.getY()].getCellStatus() == 1){ //9 = bomb hit
			instance.setfMap(map);
			return false;
		}else if(map[guiPoint.getX()][guiPoint.getY()].getValue() == 0 && map[guiPoint.getX()][guiPoint.getY()].getCellStatus() == 1){ // empty hit
			updateMapRecursion(guiPoint.getX(), guiPoint.getY());
		}else if(map[guiPoint.getX()][guiPoint.getY()].getCellStatus() == 2){ //bomb flag
				instance.incBombCounter(-1); //display bomb counter decremented
					if(map[guiPoint.getX()][guiPoint.getY()].getValue() == 9){ //is it really a bomb?
						instance.incBomb(-1);//actual bomb counter decremented
					}
		}
		return true;
	}
	private void updateMapRecursion(int x, int y){
		for(int i = -1; i < 2; i++){
			for (int j = -1; j < 2; j++){
				try{
					if((map[x+i][y+j].getValue() < 9 && map[x+i][y+j].getCellStatus() == 0)){ //if value isnt a bomb and isnt visited
						map[x+i][y+j].setCellStatus(1); //visited
						if(map[x+i][y+j].getValue() == 0){ //if its empty recurse
							updateMapRecursion(x+i, y+j);
						}
					}
				}catch(ArrayIndexOutOfBoundsException aioobe){
					continue;
				}
			}
		}
	}
	public void resetMap(){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				map[i][j].setCellStatus(0);
			}
		}
	}
	
}