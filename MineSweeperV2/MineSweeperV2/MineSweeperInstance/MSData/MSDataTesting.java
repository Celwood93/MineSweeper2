import org.junit.*;
public class MSDataTesting{
	//Assert.assertEquals
	@Test
	public void testCon(){
		String input = "15 15 15 1 4 easyTest.txt";
		MSData data = new MSData(input);
		Assert.assertEquals(15,data.getX());
		Assert.assertEquals(15,data.getY());
		Assert.assertEquals(15,data.getBombs());
		Assert.assertEquals(true,data.getTestingMap());
		Assert.assertEquals(4,data.getMode());
		Assert.assertEquals("easyTest.txt",data.getTestMapName());
	}


}