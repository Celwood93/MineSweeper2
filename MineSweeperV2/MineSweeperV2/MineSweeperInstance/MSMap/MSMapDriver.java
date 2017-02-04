import java.util.*;
import java.io.*;

public class MSMapDriver{
	
	public static CellHold [][] mapDriver(MSData instance){
			CellHold [][] map;
			map = new CellHold[0][0];
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
		return map;
	}
	
}