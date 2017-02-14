import java.util.*;
import java.io.*;
public class MSMap{
	private static CellHold [][] map;
	
	public static CellHold [][] makeMap(MSData instance){
		if(instance.getTestingMap()){
			map = MSMapDriver.mapDriver(instance);
			return map;
		}
		map = new CellHold[instance.getX()][instance.getY()];
		int mapBombs = instance.getBombs();
		
		Random rand = new Random();
		int x;
		int y;
		while(mapBombs != 0){
			x = rand.nextInt(instance.getX());
			y = rand.nextInt(instance.getY());
			if(map[x][y] == null){
				map[x][y] = new CellHold(9);
				mapBombs--;
			}
		}
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				if(map[i][j] == null){
					int value = 0;
					for(int k = -1; k<2; k++){
						for(int h = -1; h < 2; h++){
							try{
								if(map[i+k][j+h].getValue() == 9){
									value++;
								}
							}catch(IndexOutOfBoundsException ioobe){}catch(NullPointerException npe){}
						}
					}
					map[i][j] = new CellHold(value);
				}
			}
		}
		return map;
	}
	
	public static int[][] getMapAsInt(){
		int [][] arr = new int[map.length][map[0].length];
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				if(map[i][j] != null){
					arr[i][j] = map[i][j].getValue();
				}
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
				if(map[i][j] != null){
					str = str + map[i][j].getValue();
				}else{
					str = str + "0";
				}
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