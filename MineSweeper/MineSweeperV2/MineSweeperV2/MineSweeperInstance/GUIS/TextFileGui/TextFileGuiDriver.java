import java.util.*;
import java.io.*;

public class TextFileGuiDriver{
	
	public static CellHold promptUserInputDriver(CellHold [][] map, String errorMessage, MSData instance){
			Scanner console;
			File file;
			CellHold spot = new CellHold(-1,-1,-1);
			String path;
			int place;
			int max;
			int i = 0;
			String name = "";
			String newString;
			try{
				name = instance.getTestMapName().replaceFirst("\\.txt$", "pr.txt");
				console = new Scanner(file = new File(name));
				place = console.nextInt();
				max = console.nextInt();
				newString = console.nextLine();
				console = new Scanner(newString);
				while(i<place){
					spot = new CellHold(console.nextInt(), console.nextInt(), console.nextInt()); //i want to visit it, 2 be flag as bomb, 3 would be flag as unknown. 0 isnt a thing.
					i++;
				}
				if(place == max){
					place = 0;
				}
				console.close();
				newString = place+1+ " " + max + newString;
				file.delete();
				file = new File(name);
				PrintStream output = new PrintStream(file);
				output.print(newString);
				output.close();
				return spot;
				
			
			}catch(FileNotFoundException fnfe){
				System.out.println("Error reading file: " + name);
				return null;
			}
		
	}

}
