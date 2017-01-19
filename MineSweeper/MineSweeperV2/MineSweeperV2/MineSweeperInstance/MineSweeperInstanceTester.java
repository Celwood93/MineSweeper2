import org.junit.*;
import java.util.*;
import java.io.*;

public class MineSweeperInstanceTester{
//testing constructor, run, updateMap and 
	String filePath = "C:\\Users\\Cameron\\Desktop\\Projects\\MineSweeper\\MineSweeperV2\\MineSweeperV2\\MineSweeperTests\\batchFiles\\testTextFiles";
	String data= "";
	Scanner console;
	static String [] names;
	File file;
	
	@BeforeClass
	public static void setUp(){
		File f;
		Scanner reader;
		String filePass = "C:\\Users\\Cameron\\Desktop\\Projects\\MineSweeper\\MineSweeperV2\\MineSweeperV2\\MineSweeperTests\\batchFiles\\testTextFiles";
		try{
			String testFileName = filePass+"\\tests.txt";
			reader = new Scanner(f = new File(testFileName));
			int n = Integer.parseInt(reader.nextLine());
			names = new String[n];
			for(int i = 0; i < n; i++){
				names[i] = reader.nextLine();
			}
		}catch(FileNotFoundException fnfe){
			System.out.println("tests.txt file not found");
		}
	}
	public MSData inputAssist(int index){
		String input = filePath + "\\" +names[index];
		try{
			console = new Scanner(file = new File(input));	
			data = console.nextLine() + input;
		}catch(FileNotFoundException fnfe){
			System.out.println("error" + input);
		}
		MSData info = new MSData(data);
		return info;
		
	}
	public CellHold [][] copyCellArray(CellHold [][] arr){
		CellHold [][] arrRet = new CellHold[arr.length][arr[0].length];
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr.length; j++){
				arrRet[i][j] = new CellHold(arr[i][j].getValue(), arr[i][j].getCellStatus());
			}
		}
		return arrRet;	
	}
	public void updateControl(int [] locationChanges, CellHold [][] control, int state){
		for(int i = 0; i < locationChanges.length; i= i+2){
			control[locationChanges[i]][locationChanges[i+1]].setCellStatus(state);
		}
	}
	@Test
	public void testUpdateMap(){
		//incase needed
		//might clean this up if needed... kinda messy. could probably add a function that does alot of this.
		//System.out.println(MSMap.toStringCell(test1.getMapCellHold()));
		//System.out.println(MSMap.toStringCell(control));
		
		
		MSData instance = inputAssist(3);
		instance.setTestMapName(filePath+"\\"+names[3]); 
		MineSweeperInstance test1 = new MineSweeperInstance(instance);
		CellHold [][] control = copyCellArray(test1.getMapCellHold());
		int [][] controlState = MSMap.getStatusMap(control);
		int [][] testState = MSMap.getStatusMap(test1.getMapCellHold());
		boolean result = true;
		//testing the control values
		Assert.assertEquals(testState, controlState);
		Assert.assertEquals(MSMap.toString(test1.getMapCellHold()),MSMap.toString(control));
		Assert.assertEquals(result, true);
		
		//testing the recursive visit fill when a space of value 0 has been visited(1)
		CellHold point = new CellHold(0,3,1);
		result = test1.updateMap(point);
		int [][] testStateChanged = MSMap.getStatusMap(test1.getMapCellHold());
		//setting the control array to be equal to the expected recursive visit fill
		int [] locationChanges = {0,3,0,4,0,5,1,3,2,3,3,0,3,1,3,2,3,3,4,0,4,1,4,2,4,3,4,4,4,5,5,0,5,1,5,2,5,3,5,4,5,5,6,0,6,1,6,2,6,3,6,4,7,0,7,1,7,2,7,3,7,4,7,5,6,5,6,6,5,6,4,6,3,4,3,5,3,6,2,0,2,1,2,2,2,4,1,4,1,5,1,6,0,6,0,2,1,2};
		updateControl(locationChanges, control, 1);
		
		controlState = MSMap.getStatusMap(control);
		Assert.assertEquals(testStateChanged, controlState);
		Assert.assertEquals(MSMap.toString(test1.getMapCellHold()),MSMap.toString(control));
		Assert.assertEquals(result, true);
		
		//visiting(1) a different point in the same group of empty(value==0) cells, expecting same results
		test1.resetMap();
		point = new CellHold(5,1,1);
		result = test1.updateMap(point);
		testStateChanged = MSMap.getStatusMap(test1.getMapCellHold());
		
		Assert.assertEquals(testStateChanged, controlState);
		Assert.assertEquals(MSMap.toString(test1.getMapCellHold()),MSMap.toString(control));
		Assert.assertEquals(result, true);
		
		//same as last test
		test1.resetMap();
		point = new CellHold(6,4,1);
		result = test1.updateMap(point);
		testStateChanged = MSMap.getStatusMap(test1.getMapCellHold());
		
		Assert.assertEquals(testStateChanged, controlState);
		Assert.assertEquals(MSMap.toString(test1.getMapCellHold()),MSMap.toString(control));
		Assert.assertEquals(result, true);
		
		//same as last test
		test1.resetMap();
		point = new CellHold(4,4,1);
		result = test1.updateMap(point);
		testStateChanged = MSMap.getStatusMap(test1.getMapCellHold());
		
		Assert.assertEquals(testStateChanged, controlState);
		Assert.assertEquals(MSMap.toString(test1.getMapCellHold()),MSMap.toString(control));
		Assert.assertEquals(result, true);
		
		//testing to see if 1<value<9 works when visited(1)
		test1.resetMap();
		point = new CellHold(0,0,1);
		locationChanges = new int[] {0,0};
		control = copyCellArray(test1.getMapCellHold());
		updateControl(locationChanges, control, 1);
		controlState = MSMap.getStatusMap(control);
		result = test1.updateMap(point);
		testStateChanged = MSMap.getStatusMap(test1.getMapCellHold());
		
		Assert.assertEquals(testStateChanged, controlState);
		Assert.assertEquals(MSMap.toString(test1.getMapCellHold()),MSMap.toString(control));
		Assert.assertEquals(result, true);
		
		//testing to see if value == 9 (mine) is working when visited(1)
		test1.resetMap();
		point = new CellHold(1,1,1);
		locationChanges = new int[] {1,1};
		control = copyCellArray(test1.getMapCellHold());
		updateControl(locationChanges, control, 1);
		controlState = MSMap.getStatusMap(control);
		result = test1.updateMap(point);
		testStateChanged = MSMap.getStatusMap(test1.getMapCellHold());
		
		Assert.assertEquals(testStateChanged, controlState);
		Assert.assertEquals(MSMap.toString(test1.getMapCellHold()),MSMap.toString(control));
		Assert.assertEquals(test1.getInstance().getBombs(), test1.getInstance().getBombCounter());
		Assert.assertEquals(result, false);
		
		//testing to see if value == 9 is working when flagged(2)
		test1.resetMap();
		point = new CellHold(1,1,2);
		locationChanges = new int[] {1,1};
		control = copyCellArray(test1.getMapCellHold());
		updateControl(locationChanges, control, 2);
		controlState = MSMap.getStatusMap(control);
		result = test1.updateMap(point);
		testStateChanged = MSMap.getStatusMap(test1.getMapCellHold());
		
		Assert.assertEquals(testStateChanged, controlState);
		Assert.assertEquals(MSMap.toString(test1.getMapCellHold()),MSMap.toString(control));
		Assert.assertEquals(test1.getInstance().getBombs(), test1.getInstance().getBombCounter());
		Assert.assertEquals(result, true);
		
		//testing to see if value == 9 that is flagged(2) then unflagged(3) is working 
		point = new CellHold(1,1,3);
		locationChanges = new int[] {1,1};
		control = copyCellArray(test1.getMapCellHold());
		updateControl(locationChanges, control, 0);
		controlState = MSMap.getStatusMap(control);
		result = test1.updateMap(point);
		testStateChanged = MSMap.getStatusMap(test1.getMapCellHold());
		
		Assert.assertEquals(testStateChanged, controlState);
		Assert.assertEquals(MSMap.toString(test1.getMapCellHold()),MSMap.toString(control));
		Assert.assertEquals(test1.getInstance().getBombs(), test1.getInstance().getBombCounter());
		Assert.assertEquals(result, true);
		
		//testing to see that an incorrectly mine flagged(2) cell will only decremement the bombCounter, not the bombs variable
		test1.resetMap();
		point = new CellHold(1,2,2);
		locationChanges = new int[] {1,2};
		control = copyCellArray(test1.getMapCellHold());
		updateControl(locationChanges, control, 2);
		controlState = MSMap.getStatusMap(control);
		result = test1.updateMap(point);
		testStateChanged = MSMap.getStatusMap(test1.getMapCellHold());
		
		Assert.assertEquals(testStateChanged, controlState);
		Assert.assertEquals(MSMap.toString(test1.getMapCellHold()),MSMap.toString(control));
		Assert.assertEquals(test1.getInstance().getBombs(), test1.getInstance().getBombCounter()+1);
		Assert.assertEquals(result, true);
		
		
		
		
	}
	@Test
	public void test0(){
		String input = filePath + "\\" +"testDriverInput.txt";
		try{
			console = new Scanner(file = new File(input));	
			data = console.nextLine() + input;
		}catch(FileNotFoundException fnfe){
			System.out.println("error" + input);
		}
		MSData instance = new MSData(data);
		MineSweeperInstance test1 = new MineSweeperInstance(instance);
		test1.run();
		int [][] test1Expected = {{1,1,0,0,0},{1,1,0,0,0},{1,1,1,0,0},{1,1,1,0,0},{1,1,0,0,0}};
		Assert.assertEquals(MSMap.getStatusMap(test1.getMapCellHold()), test1Expected);
	}
	@Test
	public void test1(){
		MSData instance = inputAssist(0);
		MineSweeperInstance test1 = new MineSweeperInstance(instance);
		test1.run();
		int [][] testExpected ={{0,0,0,0,2,2,0,2,0},
								{2,0,2,1,1,1,1,1,1},
								{1,1,1,1,1,1,1,1,1},
								{1,1,1,1,1,1,1,1,1},
								{1,1,1,1,1,2,1,1,1},
								{0,2,1,1,1,1,1,1,1},
								{0,0,0,0,2,1,1,1,1},
								{0,2,1,1,1,1,1,1,1},
								{0,0,1,1,1,1,1,1,1}};
		Assert.assertEquals(MSMap.getStatusMap(test1.getMapCellHold()), testExpected);
		Assert.assertEquals(test1.getInstance().won(), true);
		Assert.assertEquals(test1.getInstance().getBombs(),0);
		Assert.assertEquals(test1.getInstance().getBombCounter(),0);
	}
	@Test
	public void test2(){
		MSData instance = inputAssist(1);
		MineSweeperInstance test1 = new MineSweeperInstance(instance);
		test1.run();
		int [][] testExpected ={{0,0,0,1,1,1,1,1,2},
								{2,1,0,1,1,1,1,1,1},
								{1,0,1,1,1,1,1,1,1},
								{2,0,0,1,1,1,1,1,1},
								{0,0,0,0,0,1,1,1,1},
								{1,1,0,1,1,1,1,1,1},
								{1,1,1,1,1,1,1,1,1},
								{1,1,1,1,1,1,1,1,1},
								{1,1,1,1,1,1,1,1,1}};
		Assert.assertEquals(MSMap.getStatusMap(test1.getMapCellHold()), testExpected);
		Assert.assertEquals(test1.getInstance().won(), false);
		Assert.assertEquals(test1.getInstance().getBombs(), 7);
		Assert.assertEquals(test1.getInstance().getBombCounter(),7);
	}
	
	@Test
	public void test3(){
		MSData instance = inputAssist(2);
		MineSweeperInstance test1 = new MineSweeperInstance(instance);
		test1.run();
		int [][] testExpected ={{0,0,0,0,0,0,1,1,1},
								{1,1,1,0,0,0,1,1,1},
								{1,1,1,0,1,1,1,1,1},
								{1,1,1,2,1,1,1,1,1},
								{1,1,1,0,1,1,1,1,1},
								{1,1,1,0,1,1,1,1,1},
								{0,0,0,0,0,0,1,1,1},
								{1,1,1,1,1,0,1,1,1},
								{1,1,1,1,1,2,0,1,0}};
		Assert.assertEquals(MSMap.getStatusMap(test1.getMapCellHold()), testExpected);
		Assert.assertEquals(test1.getInstance().won(), false);
		Assert.assertEquals(test1.getInstance().getBombs(), 8);
		Assert.assertEquals(test1.getInstance().getBombCounter(),8);
	}
	@Test
	public void test4(){
		MSData instance = inputAssist(3);
		MineSweeperInstance test1 = new MineSweeperInstance(instance);
		test1.run();
		int [][] testExpected ={{0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
								{0,2,1,1,1,1,1,2,2,0,0,0,2,0,0,0},
								{1,1,1,1,1,2,0,2,0,0,0,0,0,0,2,0},
								{1,1,1,1,1,1,1,2,0,2,2,0,0,0,0,0},
								{1,1,1,1,1,1,1,0,0,0,0,0,2,0,0,0},
								{1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
								{1,1,1,1,1,1,1,2,0,0,2,0,2,2,0,0},
								{1,1,1,1,1,1,2,0,0,0,0,0,2,0,0,2},
								{0,2,0,2,0,0,2,2,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0},
								{0,0,0,0,2,0,0,0,0,0,0,0,0,2,0,0},
								{0,0,0,0,0,0,2,0,0,0,2,0,0,0,0,0},
								{2,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0},
								{0,0,2,0,0,0,0,0,0,0,2,0,0,2,0,2},
								{0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0},
								{0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		Assert.assertEquals(MSMap.getStatusMap(test1.getMapCellHold()), testExpected);
		Assert.assertEquals(test1.getInstance().won(), true);
		Assert.assertEquals(test1.getInstance().getBombs(),0);
		Assert.assertEquals(test1.getInstance().getBombCounter(),0);
	}
	@Test
	public void test5(){
		MSData instance = inputAssist(4);
		MineSweeperInstance test1 = new MineSweeperInstance(instance);
		test1.run();
		int [][] testExpected ={{2,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1},
								{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1},
								{0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,1},
								{0,0,0,1,1,1,2,1,1,1,0,0,2,1,1,1},
								{0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,1},
								{0,0,0,0,1,1,1,1,1,1,0,0,0,2,1,1},
								{0,0,0,0,1,1,1,1,1,1,0,1,1,1,1,1},
								{0,0,0,0,1,1,1,0,0,0,1,1,1,1,1,1},
								{1,1,1,0,1,1,1,0,0,0,1,1,1,1,1,1},
								{1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,2},
								{1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1},
								{1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1},
								{1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1},
								{1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1},
								{1,1,1,2,1,0,0,0,0,0,0,0,0,0,1,1},
								{1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1}};
		Assert.assertEquals(MSMap.getStatusMap(test1.getMapCellHold()), testExpected);
		Assert.assertEquals(test1.getInstance().won(), false);
		Assert.assertEquals(test1.getInstance().getBombs(),35);
		Assert.assertEquals(test1.getInstance().getBombCounter(),34);
	}
	@Test
	public void test6(){
		MSData instance = inputAssist(5);
		MineSweeperInstance test1 = new MineSweeperInstance(instance);
		test1.run();
		int [][] testExpected ={{1,1,1,1,2,1,1,1,1,1,1,0,0,2,1,1},
								{1,2,1,1,1,1,1,1,1,1,1,0,0,1,1,1},
								{1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1},
								{1,1,1,1,1,1,1,1,1,1,2,0,0,1,1,1},
								{1,1,1,1,0,1,1,1,1,1,0,0,0,0,0,0},
								{0,0,0,0,0,1,1,1,1,1,0,1,2,1,1,1},
								{1,1,2,0,0,1,1,1,1,0,0,1,1,1,1,1},
								{1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1},
								{1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1},
								{1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1},
								{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
								{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
								{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
								{1,0,0,1,1,1,0,0,0,0,0,1,2,1,1,1},
								{0,0,0,1,1,1,0,0,0,0,1,1,1,1,1,1},
								{0,0,0,1,1,1,0,0,2,0,1,1,1,1,1,1}};
		Assert.assertEquals(MSMap.getStatusMap(test1.getMapCellHold()), testExpected);
		Assert.assertEquals(test1.getInstance().won(), false);
		Assert.assertEquals(test1.getInstance().getBombs(),33);
		Assert.assertEquals(test1.getInstance().getBombCounter(),32);
	}
	@Test
	public void test7(){
		MSData instance = inputAssist(6);
		MineSweeperInstance test1 = new MineSweeperInstance(instance);
		test1.run();
		int [][] testExpected ={{0,2,0,0,0,2,0,0,1,1,1,1,1,0,0,0,0,2,0,0,0,0,0,2,0},
								{0,0,0,0,0,0,0,2,1,1,1,1,1,2,0,0,0,0,0,0,0,1,1,1,1},
								{0,2,0,0,0,0,0,0,1,1,1,1,1,1,1,0,2,0,0,0,2,1,1,1,1},
								{0,0,2,2,0,0,0,0,0,2,1,1,1,1,1,2,0,0,0,2,0,1,1,1,1},
								{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,1,1,1,2,2,1,1},
								{0,0,0,0,2,0,0,0,0,0,2,1,1,1,1,1,2,2,1,1,1,1,1,1,1},
								{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
								{0,0,0,0,0,0,0,0,0,0,2,0,2,1,1,1,1,1,1,1,1,1,1,1,1},
								{0,0,2,0,0,0,2,0,2,2,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
								{0,0,0,0,0,0,0,0,2,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
								{0,0,0,0,0,0,0,0,0,0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
								{2,0,2,0,2,0,0,2,0,0,2,1,1,1,1,1,1,1,1,2,1,1,1,1,0},
								{2,2,0,0,0,0,2,1,1,1,1,1,0,2,1,1,1,1,1,0,1,1,1,1,2},
								{2,0,0,0,0,2,0,1,1,1,1,1,2,1,1,1,1,1,1,0,2,1,1,1,1},
								{0,0,0,0,0,2,0,1,1,1,1,1,1,1,1,1,1,2,0,0,2,1,1,1,1},
								{0,2,0,0,0,0,2,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1},
								{0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,2,2,0},
								{0,2,0,0,0,0,0,1,1,1,2,0,0,2,1,1,1,2,0,0,0,0,0,0,0},
								{0,0,0,2,0,0,0,2,0,2,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0},
								{0,2,2,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0},
								{0,0,0,2,0,0,2,0,0,0,2,0,2,0,0,0,2,2,0,0,0,0,0,0,0},
								{0,0,0,2,0,0,0,0,2,2,0,0,2,0,2,0,0,0,2,0,0,2,0,0,0},
								{0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0},
								{0,0,0,0,0,0,0,2,0,2,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0}};
		Assert.assertEquals(MSMap.getStatusMap(test1.getMapCellHold()), testExpected);
		Assert.assertEquals(test1.getInstance().won(), true);
		Assert.assertEquals(test1.getInstance().getBombs(),0);
		Assert.assertEquals(test1.getInstance().getBombCounter(),0);
	}
	@Test
	public void test8(){
		MSData instance = inputAssist(7);
		MineSweeperInstance test1 = new MineSweeperInstance(instance);
		test1.run();
		int [][] testExpected ={{1,1,1,1,1,1,0,0,0,1,1,1,1,1,2,0,1,1,1,1,1,1,1,1,1},
								{1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1},
								{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,2},
								{0,1,1,1,1,2,1,1,1,1,2,1,1,1,1,0,0,2,1,1,1,1,1,1,1},
								{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,0,0,0,0},
								{0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
								{0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,1,1,1,2,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
								{0,0,0,0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
								{0,0,1,1,1,1,1,1,1,2,2,1,1,1,1,0,1,1,1,1,1,1,0,0,0},
								{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,0,0},
								{1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,0,0},
								{1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,0,0},
								{1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,0,1,1,1,1,0,0,0,0,0},
								{1,1,1,1,1,1,1,1,1,2,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0},
								{1,1,1,1,1,1,2,1,1,1,1,0,0,0,0,0,1,1,1,1,0,0,0,0,0},
								{0,0,1,1,1,1,1,1,1,1,1,2,0,1,1,1,1,1,1,1,0,0,0,0,0},
								{0,0,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,0,0,0,0,0},
								{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0},
								{0,1,1,1,1,1,1,0,1,1,1,1,1,2,0,0,0,0,1,1,1,1,0,0,0},
								{0,1,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,0,1,1,1,1,0,0,0},
								{1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0},
								{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2}};
		Assert.assertEquals(MSMap.getStatusMap(test1.getMapCellHold()), testExpected);
		Assert.assertEquals(test1.getInstance().won(), false);
		Assert.assertEquals(test1.getInstance().getBombs(),71);
		Assert.assertEquals(test1.getInstance().getBombCounter(),70);
	}
	@Test
	public void test9(){
		MSData instance = inputAssist(8);
		MineSweeperInstance test1 = new MineSweeperInstance(instance);
		test1.run();
		int [][] testExpected ={{1,1,1,1,2,1,1,1,1,0,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1},
								{1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,0,0,0,2,1,1,1,1},
								{1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1},
								{1,1,2,1,1,1,1,1,1,0,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2},
								{1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
								{1,1,1,0,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,0,0},
								{1,1,1,0,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,0,0},
								{1,1,1,0,0,0,1,1,1,1,1,1,1,0,2,1,1,1,1,1,1,1,0,0,0},
								{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},
								{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
								{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
								{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,2,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
								{0,0,0,0,0,0,0,0,0,1,1,1,1,2,1,1,1,1,1,1,1,1,0,0,0},
								{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
								{1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,2,1,1,1,0},
								{1,1,1,2,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0},
								{1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,2,1,1,1,1,1,1,1,2},
								{1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,2,0,1,1,1,1,1,1,1,1},
								{1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
								{1,1,1,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
								{1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
								{1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,1},
								{1,1,1,1,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1}};
		Assert.assertEquals(MSMap.getStatusMap(test1.getMapCellHold()), testExpected);
		Assert.assertEquals(test1.getInstance().won(), false);
		Assert.assertEquals(test1.getInstance().getBombs(),71);
		Assert.assertEquals(test1.getInstance().getBombCounter(),70);
	}
	@Test
	public void test10(){
		MSData instance = inputAssist(9);
		MineSweeperInstance test1 = new MineSweeperInstance(instance);
		test1.run();
		int [][] testExpected ={{2,2,1,1},
								{1,1,1,2},
								{1,2,2,1},
								{1,1,1,2},
								{2,1,1,1},
								{1,1,1,1}};
		Assert.assertEquals(MSMap.getStatusMap(test1.getMapCellHold()), testExpected);
		Assert.assertEquals(test1.getInstance().won(), true);
		Assert.assertEquals(test1.getInstance().getBombs(),0);
		Assert.assertEquals(test1.getInstance().getBombCounter(),0);
	}
	@Test
	public void test11(){
		MSData instance = inputAssist(10);
		MineSweeperInstance test1 = new MineSweeperInstance(instance);
		test1.run();
		int [][] testExpected ={{1,0,0,0,0,1,0,0,0,0},
								{0,1,0,0,0,0,0,0,0,0},
								{0,0,0,1,1,1,2,1,1,1},
								{0,0,0,1,2,1,1,1,1,2},
								{0,0,0,1,1,1,1,1,1,2}};
		Assert.assertEquals(MSMap.getStatusMap(test1.getMapCellHold()), testExpected);
		Assert.assertEquals(test1.getInstance().won(), false);
		Assert.assertEquals(test1.getInstance().getBombs(),9);
		Assert.assertEquals(test1.getInstance().getBombCounter(),9);
	}
	@Test
	public void test12(){
		MSData instance = inputAssist(11);
		MineSweeperInstance test1 = new MineSweeperInstance(instance);
		test1.run();
		int [][] testExpected ={{0,1,0,0,0,0,0,0},
								{0,2,2,0,0,0,1,1},
								{0,0,0,0,0,0,1,1},
								{0,0,0,2,0,0,1,1},
								{0,0,0,0,0,0,1,1},
								{0,0,0,0,0,0,1,1},
								{0,0,0,0,0,0,1,1},
								{1,1,0,0,0,0,2,2},
								{1,1,2,0,0,2,2,2},
								{1,1,1,0,0,2,1,2},
								{1,1,1,0,0,2,2,2},
								{1,1,1,0,0,0,0,0}};
		Assert.assertEquals(MSMap.getStatusMap(test1.getMapCellHold()), testExpected);
		Assert.assertEquals(test1.getInstance().won(), false);
		Assert.assertEquals(test1.getInstance().getBombs(),11);
		Assert.assertEquals(test1.getInstance().getBombCounter(),11);
	}
	@Test
	public void testCheckPoint(){
		String input = filePath + "\\" +"testDriverInput.txt";
		String message = "";
		try{
			console = new Scanner(file = new File(input));	
			data = console.nextLine() + input;
		}catch(FileNotFoundException fnfe){
			System.out.println("error" + input);
		}
		//setting up a sample MineSweeperInstance to test on.
		MSData instance = new MSData(data);
		MineSweeperInstance test1 = new MineSweeperInstance(instance);
		CellHold point = new CellHold(0,0,1);
		message = test1.checkPoint(point);
		Assert.assertEquals("", message);
		
		//first error, trying to visit a spot already visited
		test1.updateMap(point);
		point = new CellHold(1,0,1);
		message = test1.checkPoint(point);
		Assert.assertEquals("1 -> 1", message);
		
		//second error, trying to unvisit a spot that is unvisited
		point = new CellHold(1,2,3);
		message = test1.checkPoint(point);
		Assert.assertEquals("0 -> 0", message);
		
		//third error, trying to unvisit a spot that is visited
		point = new CellHold(1,0,3);
		message = test1.checkPoint(point);
		Assert.assertEquals("1 -> 0", message);
		
		//marking a spot as a mine flag
		point = new CellHold(1,2,2);
		message = test1.checkPoint(point);
		Assert.assertEquals("", message);
		test1.updateMap(point);
		
		//unmarking a mine flag
		point = new CellHold(1,2,3);
		message = test1.checkPoint(point);
		Assert.assertEquals("", message);
		
		//error four, mine flagging a spot that is already visited
		point = new CellHold(1,1,2);
		message = test1.checkPoint(point);
		Assert.assertEquals("1 -> 2", message);
		
		//error 5, trying to visit a spot that is flagged as a mine (must unflag first)
		point = new CellHold(1,2,1);
		message = test1.checkPoint(point);
		Assert.assertEquals("2 -> 1", message);
		
		//error 6, trying to flag a spot that is already flagged
		point = new CellHold(1,2,2);
		message = test1.checkPoint(point);
		Assert.assertEquals("2 -> 2", message);
		
		//error 6, trying to use a flag that doesnt exist: value>3
		point = new CellHold(1,2,5);
		message = test1.checkPoint(point);
		Assert.assertEquals("DNE+", message);
		//error 7, trying to use a flag that doesnt exist value < 0
		point = new CellHold(1,2,-1);
		message = test1.checkPoint(point);
		Assert.assertEquals("DNE-", message);
		
		//i think thats all of the possibilities...
		
	}


}