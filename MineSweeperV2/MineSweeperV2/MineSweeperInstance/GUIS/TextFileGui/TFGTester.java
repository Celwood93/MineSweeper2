import org.junit.*;
import java.util.*;
import java.io.*;

public class TFGTester{
	String filePathway = "C:\\Users\\Cameron\\Desktop\\Projects\\MineSweeper\\MineSweeperV2\\MineSweeperV2\\MineSweeperTests\\batchFiles\\testTextFiles";
	String data= "";
	Scanner console;
	static String [] names;
	File file;
	
	@Test
	public void test1(){
		String filePath = "C:\\Users\\Cameron\\Desktop\\Projects\\MineSweeper\\MineSweeperV2\\MineSweeperV2\\MineSweeperTests\\batchFiles\\testTextFiles";
		String data = "5 5 2 1 1 " +filePath + "\\testDriverInput.txt";
		MSData info = new MSData(data);
		CellHold [][] map = MSMap.makeMap(info);
		CellHold result = TextFileGuiDriver.promptUserInputDriver(map, "", info);
		CellHold resultControl = new CellHold(0, 0, 1);
		Assert.assertEquals(result.getX(), resultControl.getX());
		Assert.assertEquals(result.getY(), resultControl.getY());
		Assert.assertEquals(result.getCellStatus(), resultControl.getCellStatus());
	}
	@Test
	public void test2(){
		String filePath = "C:\\Users\\Cameron\\Desktop\\Projects\\MineSweeper\\MineSweeperV2\\MineSweeperV2\\MineSweeperTests\\batchFiles\\testTextFiles";
		String data = "5 5 2 1 1 " +filePath + "\\testDriverInput.txt";
		MSData info = new MSData(data);
		CellHold [][] map = MSMap.makeMap(info);
		CellHold result = TextFileGuiDriver.promptUserInputDriver(map, "", info);
		CellHold resultControl = new CellHold(1, 1, 1);
		Assert.assertEquals(result.getX(), resultControl.getX());
		Assert.assertEquals(result.getY(), resultControl.getY());
		Assert.assertEquals(result.getCellStatus(), resultControl.getCellStatus());
	}
	@Test
	public void test3(){
		String filePath = "C:\\Users\\Cameron\\Desktop\\Projects\\MineSweeper\\MineSweeperV2\\MineSweeperV2\\MineSweeperTests\\batchFiles\\testTextFiles";
		String data = "5 5 2 1 1 " +filePath + "\\testDriverInput.txt";
		MSData info = new MSData(data);
		CellHold [][] map = MSMap.makeMap(info);
		CellHold result = TextFileGuiDriver.promptUserInputDriver(map, "", info);
		CellHold resultControl = new CellHold(2, 2, 1);
		Assert.assertEquals(result.getX(), resultControl.getX());
		Assert.assertEquals(result.getY(), resultControl.getY());
		Assert.assertEquals(result.getCellStatus(), resultControl.getCellStatus());
	}
	@Test
	public void test4(){
		String filePath = "C:\\Users\\Cameron\\Desktop\\Projects\\MineSweeper\\MineSweeperV2\\MineSweeperV2\\MineSweeperTests\\batchFiles\\testTextFiles";
		String data = "5 5 2 1 1 " +filePath + "\\testDriverInput.txt";
		MSData info = new MSData(data);
		CellHold [][] map = MSMap.makeMap(info);
		CellHold result = TextFileGuiDriver.promptUserInputDriver(map, "", info);
		CellHold resultControl = new CellHold(3, 2, 1);
		Assert.assertEquals(result.getX(), resultControl.getX());
		Assert.assertEquals(result.getY(), resultControl.getY());
		Assert.assertEquals(result.getCellStatus(), resultControl.getCellStatus());
	}
	
	
	
}