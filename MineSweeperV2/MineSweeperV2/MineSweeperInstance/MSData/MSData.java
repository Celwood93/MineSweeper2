import java.util.*;
import java.io.*;
public class MSData{
	private int x;
	private int y;
	private int bombs;
	private int mode;
	private boolean won = false;
	private CellHold [][] fMap;
	private boolean testingMap;
	private String testMapName;
	private int bombCounter;
	
	
	public MSData(){
	}
	//inputs needs to be in the format of "x y bombs testingMap mode testMapName"
	public MSData(String inputs){
		Scanner console = new Scanner(inputs);
		String holder = "";
		try{
			holder = console.next();
			this.x = Integer.parseInt(holder);
			holder = console.next();
			this.y = Integer.parseInt(holder);
			holder = console.next();
			this.bombs = Integer.parseInt(holder);
			this.bombCounter = bombs;
			holder = console.next();
			this.testingMap = Integer.parseInt(holder) == 1;
			holder = console.next();
			this.mode = Integer.parseInt(holder);
			holder = console.next();
			this.testMapName = holder;
		}catch(NoSuchElementException nsee){
			System.out.println("error in msdata");
		}
	}
	public void setfMap(CellHold [][] fMap){
		this.fMap = fMap;	
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setBombs(int bombs){
		this.bombs = bombs;
		this.bombCounter = bombs;
	}
	public void incBomb(int inc){
		bombs = bombs + inc;
	}
	public void setMode(int mode){
		this.mode = mode;
	}
	public void setWon(boolean won){
		this.won = won;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getBombs(){
		return bombs;
	}
	public int getMode(){
		return mode;
	}
	public boolean won(){
		return won;
	}
	public CellHold [][] getfMap(){
		return fMap;
	}
	public void setTestingMap(boolean value){
		testingMap = value;
	}
	public boolean getTestingMap(){
		return testingMap;
	}
	public void setTestMapName(String name){
		testMapName = name;
	}
	public String getTestMapName(){
		return testMapName;
	}
	public void resetBombCounter(){
		bombCounter = bombs;
	}
	public void incBombCounter(int inc){
		bombCounter = bombCounter + inc;
	}
	public int getBombCounter(){
		return bombCounter;
	}
	
}