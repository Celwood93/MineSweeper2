import java.util.*;
import java.io.*;

public class Gui{
	
	public static void promptGameData(MSData instance, String [] scores){
		instance.setMode(51);
		instance.setBombs(10);
		instance.setX(15);
		instance.setY(15);
	}
	
	public static boolean displayResult(MSData instance){
		return false;
		
	}
	
	public static CellHold promptUserInput(CellHold [][] map, String errorMessage, MSData instance){
		if(instance.getTestingMap()){
			return TextFileGuiDriver.promptUserInputDriver(map, errorMessage, instance);
		}
		CellHold here = new CellHold(0);
		return here;
		
	}
	//all methods above here HAVE to be in ever gui. Ideally these would be abstract and each GUI would be an extension of a class called AbstractGui
	
	
	
}