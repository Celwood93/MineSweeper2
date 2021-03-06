import org.junit.*;
import java.util.*;
import java.io.*;

public class MSMapTesting{
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
	
	public boolean isCorrect(int [][] arr){
		boolean works = true;
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[0].length; j++){
				if(arr[i][j] < 9){
					int value = arr[i][j];
					for(int k = -1; k<2; k++){
						for(int h = -1; h < 2; h++){
							try{
								if(arr[i+k][j+h] == 9){
									value--;
								}
							}catch(IndexOutOfBoundsException ioobe){
						
							}
						}
					}
					if(value != 0){
						works = false;
						System.out.println("at index ["+i+"]["+j+"] the value was wrong.");
					}
				}
			}
		}
		return works;
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
	
	@Test
	public void testReal(){
		console = new Scanner(System.in);
		MSData info = new MSData("10 10 20 0 2 ?");
		MSMap.makeMap(info);
		int [][] tester = MSMap.getMapAsInt();
		Assert.assertEquals(true, isCorrect(tester));
	}
	
	@Test
	public void testReal2(){
		console = new Scanner(System.in);
		MSData info = new MSData("20 20 45 0 3 ?");
		MSMap.makeMap(info);
		int [][] tester = MSMap.getMapAsInt();
		Assert.assertEquals(true, isCorrect(tester));
	}
	
	@Test
	public void testReal3(){
		console = new Scanner(System.in);
		MSData info = new MSData("6 6 10 0 1 ?");
		MSMap.makeMap(info);
		int [][] tester = MSMap.getMapAsInt();
		Assert.assertEquals(true, isCorrect(tester));
	}
	
	@Test
	public void testReal4(){
		console = new Scanner(System.in);
		MSData info = new MSData("16 6 15 0 2 ?");
		MSMap.makeMap(info);
		int [][] tester = MSMap.getMapAsInt();
		Assert.assertEquals(true, isCorrect(tester));
	}
	
	@Test
	public void testReal5(){
		console = new Scanner(System.in);
		MSData info = new MSData("6 16 15 0 2 ?");
		MSMap.makeMap(info);
		int [][] tester = MSMap.getMapAsInt();
		Assert.assertEquals(true, isCorrect(tester));
	}
	
	@Test
	public void testInput0(){
		
		MSMap.makeMap(inputAssist(0));
		int [][] testArr0 ={{1,2,1,2,9,9,2,9,1},
							{9,2,9,2,2,2,2,1,1},
							{1,2,1,1,0,0,0,0,0},
							{0,0,0,0,1,1,1,0,0},
							{1,1,1,0,1,9,1,0,0},
							{1,9,1,1,2,2,1,0,0},
							{2,2,2,1,9,1,0,0,0},
							{1,9,1,1,1,1,0,0,0},
							{1,1,1,0,0,0,0,0,0}};
		Assert.assertEquals(testArr0, MSMap.getMapAsInt());
		Assert.assertEquals(true, isCorrect(testArr0));
	}
	
	@Test
	public void testInput1(){
		MSMap.makeMap(inputAssist(1));
		int [][] testArr1 ={{1,3,9,2,0,0,0,1,9},
							{9,4,9,2,0,0,0,1,1},
							{9,4,1,1,0,0,0,0,0},
							{9,3,2,2,2,1,0,0,0},
							{2,9,3,9,9,1,0,0,0},
							{1,2,9,3,2,1,0,0,0},
							{0,1,1,1,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0}};
		Assert.assertEquals(testArr1, MSMap.getMapAsInt());
		Assert.assertEquals(true, isCorrect(testArr1));
	}
	@Test
	public void testInput2(){
		MSMap.makeMap(inputAssist(2));
		int [][] testArr2 ={{9,2,9,1,1,1,1,0,0},
							{1,2,1,1,1,9,1,0,0},
							{0,0,1,1,2,1,1,0,0},
							{0,0,1,9,1,0,0,0,0},
							{0,0,1,1,1,0,0,0,0},
							{1,1,2,1,2,1,1,0,0},
							{1,9,2,9,2,9,1,0,0},
							{1,1,2,1,3,2,3,1,1},
							{0,0,0,0,1,9,2,9,1}};
		Assert.assertEquals(testArr2, MSMap.getMapAsInt());
		Assert.assertEquals(true, isCorrect(testArr2));
		
	}
	@Test
	public void testInput3(){
		MSMap.makeMap(inputAssist(3));
		int [][] testArr3 ={{1,1,1,0,0,0,1,2,2,1,0,1,1,1,0,0},
							{1,9,1,0,1,1,3,9,9,1,0,1,9,2,1,1},
							{1,1,1,0,1,9,4,9,5,3,2,2,1,2,9,1},
							{0,0,0,0,1,1,3,9,3,9,9,2,1,2,1,1},
							{0,0,0,0,0,0,1,1,2,2,2,2,9,1,0,0},
							{0,0,0,0,0,0,1,1,1,1,1,3,3,3,1,0},
							{0,0,0,0,0,1,2,9,1,1,9,3,9,9,2,1},
							{1,1,2,1,1,2,9,4,2,1,1,3,9,3,2,9},
							{1,9,2,9,1,2,9,9,1,0,0,2,2,2,1,1},
							{1,1,2,2,2,2,2,2,1,0,0,1,9,2,1,0},
							{0,0,0,1,9,2,1,1,0,1,1,2,2,9,1,0},
							{1,1,0,1,1,2,9,2,1,1,9,1,1,1,1,0},
							{9,2,1,1,0,1,2,9,1,2,2,2,1,1,2,1},
							{1,2,9,1,0,0,1,2,3,3,9,1,1,9,2,9},
							{1,2,2,1,0,0,0,1,9,9,2,1,1,1,2,1},
							{1,9,1,0,0,0,0,1,2,2,1,0,0,0,0,0}};
							
		Assert.assertEquals(testArr3, MSMap.getMapAsInt());
		Assert.assertEquals(true, isCorrect(testArr3));
	}
	@Test
	public void testInput4(){
		MSMap.makeMap(inputAssist(4));
		int [][] testArr4 ={{1,1,1,9,3,9,2,0,0,1,9,1,0,0,0,0},
							{9,2,2,2,3,9,3,1,2,2,2,1,0,0,0,0},
							{1,2,9,1,1,2,3,9,2,9,1,1,1,1,0,0},
							{0,1,1,1,0,1,9,2,2,1,1,1,9,1,0,0},
							{1,1,1,1,1,1,1,1,0,1,1,2,2,2,1,0},
							{9,1,2,9,2,0,0,0,0,2,9,2,1,9,1,0},
							{2,2,4,9,3,0,1,1,1,2,9,2,1,1,1,0},
							{1,9,3,9,2,0,1,9,2,2,1,1,0,0,0,0},
							{1,1,3,2,3,2,3,3,9,2,1,0,0,0,1,1},
							{0,0,2,9,4,9,9,3,2,9,1,0,0,0,1,9},
							{0,0,2,9,4,9,9,2,2,2,2,0,0,0,1,1},
							{0,0,1,1,2,2,2,1,2,9,2,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,2,9,2,0,0,0,0,0},
							{0,0,1,1,1,1,1,2,2,2,1,1,2,2,1,0},
							{0,0,1,9,1,1,9,3,9,3,1,2,9,9,1,0},
							{0,0,1,1,1,1,1,3,9,3,9,2,2,2,1,0}};
		Assert.assertEquals(testArr4, MSMap.getMapAsInt());
		Assert.assertEquals(true, isCorrect(testArr4));
	}
	@Test
	public void testInput5(){
		MSMap.makeMap(inputAssist(5));
		int [][] testArr5 ={{1,1,1,1,9,1,0,0,0,0,1,9,2,9,1,0},
							{1,9,1,1,1,1,0,0,0,0,1,2,3,2,1,0},
							{1,1,1,0,0,0,0,0,0,1,1,2,9,1,0,0},
							{0,0,0,1,1,1,0,0,0,1,9,2,1,2,1,1},
							{1,1,1,1,9,1,0,0,0,1,1,2,1,2,9,1},
							{1,9,2,2,1,1,0,0,1,1,1,1,9,2,1,1},
							{1,2,9,3,2,1,1,1,2,9,2,2,1,1,0,0},
							{0,1,3,9,9,3,3,9,3,4,9,2,0,0,0,0},
							{0,0,3,9,5,9,9,3,9,3,9,2,0,0,0,0},
							{1,2,4,9,3,3,4,4,2,3,3,3,1,0,0,0},
							{1,9,9,2,1,2,9,9,1,1,9,9,1,0,0,0},
							{1,2,2,2,1,3,9,3,1,1,3,3,2,0,0,0},
							{2,2,1,1,9,3,3,3,1,1,2,9,2,1,0,0},
							{9,9,2,2,1,2,9,9,1,1,9,3,9,1,0,0},
							{2,3,9,1,0,2,3,3,2,2,2,2,1,1,0,0},
							{0,1,1,1,0,1,9,1,1,9,1,0,0,0,0,0}};
		Assert.assertEquals(testArr5, MSMap.getMapAsInt());
		Assert.assertEquals(true, isCorrect(testArr5));
	}
	@Test
	public void testInput6(){
		MSMap.makeMap(inputAssist(6));
		int [][] testArr6 ={{1,9,1,0,1,9,2,1,1,0,0,0,1,1,1,0,1,9,1,0,0,0,1,9,1},
							{2,2,2,0,1,1,2,9,1,0,0,0,1,9,1,1,2,2,1,1,1,1,1,1,1},
							{1,9,3,2,1,0,1,1,2,1,1,0,1,1,2,2,9,1,1,2,9,1,0,0,0},
							{1,2,9,9,1,0,0,0,1,9,1,0,0,0,1,9,2,1,1,9,3,3,2,1,0},
							{0,1,2,3,2,1,0,0,1,2,2,1,0,0,1,2,3,2,2,1,2,9,9,1,0},
							{0,0,0,1,9,1,0,0,0,1,9,1,0,0,0,1,9,9,1,0,1,2,2,1,0},
							{0,0,0,1,1,1,0,0,0,2,2,3,1,1,0,1,2,2,1,0,0,0,0,0,0},
							{0,1,1,1,0,1,1,2,2,3,9,2,9,1,0,0,0,0,0,0,0,0,0,0,0},
							{0,1,9,1,0,1,9,3,9,9,2,2,1,1,0,0,0,0,0,0,0,0,0,1,1},
							{0,1,1,1,0,1,1,3,9,4,2,1,0,0,0,0,0,0,0,0,0,0,0,1,9},
							{1,2,1,2,1,1,1,2,2,3,9,2,0,0,0,0,0,0,1,1,1,0,0,1,1},
							{9,4,9,2,9,2,2,9,1,2,9,2,1,1,1,0,0,0,1,9,1,0,0,1,1},
							{9,9,2,2,2,3,9,2,1,1,1,2,2,9,1,0,0,0,1,2,2,1,0,1,9},
							{9,3,1,0,2,9,3,1,0,0,0,1,9,2,1,0,1,1,1,2,9,2,0,1,1},
							{2,2,1,0,2,9,3,1,0,0,0,1,1,1,0,0,1,9,1,2,9,2,0,0,0},
							{1,9,1,0,1,2,9,1,0,0,0,0,0,0,0,0,1,1,1,1,1,2,2,2,1},
							{2,2,2,0,0,1,1,1,0,1,1,1,1,1,1,0,1,1,1,0,0,1,9,9,1},
							{1,9,2,1,1,0,1,1,2,2,9,1,1,9,1,1,2,9,2,1,1,1,2,2,1},
							{2,3,4,9,1,1,2,9,2,9,2,1,1,1,1,1,9,2,2,9,1,0,0,0,0},
							{1,9,9,2,1,1,9,2,3,2,2,0,0,0,1,2,2,1,1,1,1,0,0,0,0},
							{1,2,3,2,1,2,2,2,1,9,2,2,1,1,1,9,3,2,1,0,0,0,0,0,0},
							{0,0,2,9,2,1,9,2,3,4,9,3,9,3,2,3,9,9,2,1,1,1,1,0,0},
							{1,1,3,9,2,1,1,2,9,9,2,3,9,3,9,2,2,3,9,1,1,9,2,1,0},
							{1,9,2,1,1,0,1,2,4,3,2,1,1,3,2,2,0,1,1,1,1,2,9,1,0},
							{1,1,1,0,0,0,1,9,2,9,1,0,0,1,9,1,0,0,0,0,0,1,1,1,0}};
		Assert.assertEquals(testArr6, MSMap.getMapAsInt());
		Assert.assertEquals(true, isCorrect(testArr6));
	}
	@Test
	public void testInput7(){
		MSMap.makeMap(inputAssist(7));
		int [][] testArr7 ={{0,0,0,0,0,1,9,3,9,2,0,0,0,1,9,9,1,0,0,0,0,0,0,0,0},
							{1,1,0,0,0,1,1,3,9,2,0,0,0,1,3,3,2,0,0,0,0,0,0,1,1},
							{9,1,0,0,1,1,1,1,1,2,1,1,0,0,2,9,3,1,1,0,0,0,0,1,9},
							{1,1,0,0,1,9,1,0,0,1,9,1,0,0,2,9,3,9,1,0,1,1,1,2,2},
							{1,1,1,0,1,1,1,0,0,1,1,1,0,1,2,3,3,3,2,1,1,9,2,2,9},
							{1,9,1,0,0,0,0,0,0,0,0,0,0,1,9,2,9,2,9,2,3,2,4,9,3},
							{2,2,3,2,2,1,0,1,1,1,0,1,1,3,2,4,3,4,3,9,2,9,3,9,2},
							{1,9,2,9,9,1,1,3,9,2,0,1,9,2,9,2,9,9,2,2,3,3,3,2,1},
							{1,1,2,2,2,1,2,9,9,3,0,1,1,2,1,2,2,2,1,1,9,2,9,1,0},
							{1,1,1,2,2,1,2,9,9,2,0,0,0,0,0,0,0,0,0,1,1,3,2,2,0},
							{9,2,2,9,9,1,1,2,3,3,2,1,0,0,1,1,1,0,0,0,0,1,9,1,0},
							{2,9,2,2,2,1,0,0,1,9,9,1,0,0,1,9,1,0,0,0,0,2,2,2,0},
							{1,1,1,0,0,0,1,1,2,2,2,1,0,0,1,1,1,0,0,0,0,1,9,1,0},
							{0,1,1,1,0,0,1,9,1,0,0,0,0,0,1,1,1,0,0,0,0,1,1,2,1},
							{0,1,9,1,0,0,1,1,1,0,1,1,1,0,1,9,1,0,0,1,1,2,2,3,9},
							{0,1,1,1,0,0,0,0,1,1,2,9,1,1,2,2,1,0,0,1,9,2,9,9,3},
							{0,0,0,0,0,1,1,1,1,9,2,1,2,2,9,2,1,0,0,1,1,2,3,9,2},
							{1,1,1,0,0,1,9,1,1,1,2,1,2,9,3,9,1,0,0,1,1,1,2,2,2},
							{1,9,1,0,0,1,1,1,0,0,1,9,3,2,2,1,1,0,0,1,9,1,1,9,1},
							{2,2,2,0,0,0,0,0,0,0,1,2,9,1,0,0,0,1,1,3,2,3,2,2,1},
							{1,9,1,0,0,0,1,1,1,0,0,1,2,3,2,1,1,2,9,2,9,3,9,3,1},
							{1,1,1,0,0,0,1,9,1,0,0,0,1,9,9,1,2,9,3,2,1,4,9,4,9},
							{1,1,0,0,0,1,2,2,1,1,1,1,2,4,4,2,2,9,2,0,0,2,9,4,2},
							{9,1,1,1,1,1,9,1,0,1,9,2,2,9,9,2,2,2,2,1,1,1,2,9,1},
							{1,1,1,9,1,1,1,1,0,1,2,9,2,2,2,2,9,1,1,9,1,0,1,1,1}};
		Assert.assertEquals(testArr7, MSMap.getMapAsInt());
		Assert.assertEquals(true, isCorrect(testArr7));
	}
	@Test
	public void testInput8(){
		MSMap.makeMap(inputAssist(8));
		int [][] testArr8 ={{0,0,0,1,9,1,0,0,2,9,2,0,1,9,1,1,2,2,1,1,1,1,0,0,0},
							{0,0,0,1,1,1,0,0,2,9,2,0,1,1,1,1,9,9,1,1,9,1,0,0,0},
							{0,1,1,1,0,0,0,0,1,1,1,0,0,0,0,1,3,3,2,1,1,1,0,1,1},
							{0,1,9,1,0,0,0,0,1,1,1,0,0,0,0,0,1,9,1,0,0,0,0,1,9},
							{0,1,1,2,1,1,0,0,1,9,1,0,1,1,1,0,1,1,1,0,0,1,1,3,2},
							{0,0,1,2,9,1,0,0,1,1,1,0,1,9,1,0,0,0,0,0,0,1,9,3,9},
							{0,0,1,9,3,2,1,0,0,0,0,0,1,2,2,1,0,0,0,0,0,2,3,9,2},
							{1,1,3,2,3,9,1,1,1,1,1,1,1,1,9,1,0,0,0,0,0,1,9,3,2},
							{1,9,2,9,2,1,1,1,9,3,3,9,1,1,1,1,0,0,0,0,1,2,3,9,1},
							{1,2,3,2,1,1,1,2,2,9,9,2,1,0,0,0,0,0,0,0,1,9,2,1,1},
							{0,1,9,2,1,3,9,2,1,2,2,1,0,0,0,0,1,2,2,1,1,1,2,1,1},
							{1,2,2,3,9,5,9,3,2,2,1,0,0,0,0,0,1,9,9,2,1,0,1,9,1},
							{1,9,2,3,9,9,2,3,9,9,1,0,0,0,0,0,1,2,3,9,1,1,2,3,2},
							{1,1,2,9,3,2,2,3,9,3,1,0,1,1,1,0,0,0,1,1,1,2,9,3,9},
							{2,2,2,1,1,1,2,9,2,1,0,0,1,9,1,0,0,0,0,0,0,2,9,4,2},
							{9,9,1,0,0,1,9,2,2,1,2,1,2,1,1,0,0,0,0,1,1,2,2,9,1},
							{2,2,2,2,2,3,2,2,1,9,2,9,1,0,0,0,0,0,0,1,9,1,1,1,1},
							{0,0,1,9,9,3,9,3,3,2,4,2,2,0,0,1,1,1,0,1,1,1,0,1,1},
							{0,0,1,2,2,4,9,9,3,9,2,9,1,0,1,2,9,1,0,0,0,0,0,1,9},
							{0,0,0,0,0,2,9,9,3,1,2,1,2,2,3,9,2,1,0,0,0,0,0,1,1},
							{0,0,0,0,0,2,3,3,1,0,0,0,1,9,9,2,2,1,1,0,0,0,0,0,0},
							{0,0,0,0,0,1,9,1,1,1,1,0,1,2,2,1,1,9,1,0,0,0,0,0,0},
							{0,0,0,0,0,1,2,2,3,9,2,0,0,0,1,2,3,2,1,1,1,1,1,1,1},
							{0,0,0,0,0,0,1,9,5,9,4,1,1,0,2,9,9,1,0,1,9,1,1,9,1},
							{0,0,0,0,0,0,1,2,9,9,3,9,1,0,2,9,3,1,0,1,1,1,1,1,1}};
		Assert.assertEquals(testArr8, MSMap.getMapAsInt());
		Assert.assertEquals(true, isCorrect(testArr8));
	}
	@Test
	public void testInput9(){
		MSMap.makeMap(inputAssist(9));
		int [][] testArr9 ={{9,9,2,1},
							{3,4,4,9},
							{1,9,9,3},
							{2,3,3,9},
							{9,1,1,1},
							{1,1,0,0}};
		Assert.assertEquals(testArr9, MSMap.getMapAsInt());
		Assert.assertEquals(true, isCorrect(testArr9));
	}
	@Test
	public void testInput10(){
		MSMap.makeMap(inputAssist(10));
		int [][] testArr10={{9,2,9,2,2,1,1,2,9,2},
							{2,3,3,9,2,9,2,3,9,2},
							{2,9,3,2,3,3,9,2,2,2},
							{2,9,3,2,9,2,1,1,2,9},
							{1,2,9,2,1,1,0,0,2,9}};
		Assert.assertEquals(testArr10, MSMap.getMapAsInt());
		Assert.assertEquals(true, isCorrect(testArr10));
	}
	@Test
	public void testInput11(){
		MSMap.makeMap(inputAssist(11));
		int [][] testArr11={{2,9,3,1,1,1,2,9},
							{2,9,9,1,1,9,2,1},
							{3,4,4,3,3,2,1,0},
							{9,9,3,9,9,2,1,0},
							{2,3,9,3,3,9,1,0},
							{1,2,2,2,2,1,1,0},
							{9,1,1,9,1,1,2,2},
							{1,2,2,2,2,3,9,9},
							{0,1,9,1,2,9,9,9},
							{0,1,2,2,4,9,8,9},
							{0,0,1,9,3,9,9,9},
							{0,0,1,1,2,2,3,2}};
		Assert.assertEquals(testArr11, MSMap.getMapAsInt());
		Assert.assertEquals(true, isCorrect(testArr11));
	}
	
	
	@After
	public void after(){
		console.close();
	}
	
	
}