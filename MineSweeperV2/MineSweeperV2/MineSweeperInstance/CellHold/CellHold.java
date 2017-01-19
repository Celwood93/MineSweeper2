
public class CellHold{
	private int value;
	private int cellStatus;
	private int x;
	private int y;
	CellHold(int value){
		this.value = value;
		cellStatus = 0;
	}
	CellHold(int value, int cellStatus){
		this.value = value;
		this.cellStatus = cellStatus;
	}
	//this is the return cellhold from gui -> just to make this easier and faster
	CellHold(int x, int y, int cellStatus){
		this.x = x;
		this.y = y;
		this.cellStatus = cellStatus;
	}
	public int getValue(){
		return value;
	}
	public void setValue(int value){
		this.value = value;
	}
	//0 = unvisited 1 = visited 2 = flag as bomb 3 = reset to 0
	public int getCellStatus(){
		return cellStatus;
	}
	public void setCellStatus(int status){
		this.cellStatus = status;
	}
	public void setX(int x){
		this.x = x;
	}
	public int getX(){
		return x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getY(){
		return y;
	}
	
}