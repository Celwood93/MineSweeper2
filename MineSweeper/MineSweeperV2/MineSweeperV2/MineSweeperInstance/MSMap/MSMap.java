import java.util.*;
import java.io.*;
public class MSMap{
	private static CellHold [][] map;
	public static CellHold [][] makeMap(MSData instance){
		if(instance.getTestingMap()){
			//put this into another file as a REAL driver, the DRIVER will simply be called by this method, but can easily be commented out.
			Scanner console;
			File file;
			try{
				console = new Scanner(file = new File(instance.getTestMapName()));
				console.nextLine();
				console.nextLine();
				map = new CellHold[instance.getX()][instance.getY()];
				boolean breaker = false;
				for(int i=0;i<instance.getX();i++){
					if(breaker){
						break;
					}
					for(int j=0;j<instance.getY();j++){
						if(console.hasNextInt()){
							map[i][j] = new CellHold(console.nextInt());
						}else{
							breaker = true;
							break;
						}
					}
				}
			}catch(FileNotFoundException fnfe){
				System.out.println("No File Called: " + instance.getTestMapName());
			}
		}
		return map;
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