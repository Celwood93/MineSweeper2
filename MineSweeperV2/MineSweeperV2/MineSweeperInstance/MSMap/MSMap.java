import java.util.*;
import java.io.*;
public class MSMap{
	private static CellHold [][] map;
	
	public static CellHold [][] makeMap(MSData instance){
		if(instance.getTestingMap()){
			map = MSMapDriver.mapDriver(instance);
			return map;
		}
		return null;
	}
	
	public static int[][] getMapAsInt(){
		int [][] arr = new int[map.length][map[0].length];
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				arr[i][j] = map[i][j].getValue();
			}
		}
		return arr;
	}
	//returns an array of cellStatus int values.
	public static int[][] getStatusMap(CellHold [][] map){
		int [][] arr = new int[map.length][map[0].length];
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				arr[i][j] = map[i][j].getCellStatus();
			}
		}
		return arr;
	}
	public static String toString(CellHold [][] map){
		String str = "";
		for(int i = 0; i < map.length; i++){
			str = str + "\n";
			for(int j = 0; j < map[0].length; j++){
				str = str + map[i][j].getValue();
			}
		}
		return str;
	}
	public static String toStringCell(CellHold [][] map){
		String str = "";
		for(int i = 0; i < map.length; i++){
			str = str + "\n";
			for(int j = 0; j < map[0].length; j++){
				str = str + map[i][j].getCellStatus();
			}
		}
		return str;
	}
	//need to make this since I cant make it so that an empty paramater is just this.map... probably could make this better but these are both for testing
	public static int [][] makeMapInt(CellHold [][] map){
		int [][] arr = new int[map.length][map[0].length];
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				arr[i][j] = map[i][j].getValue();
			}
		}
		return arr;
	}
	
	
	
	
}