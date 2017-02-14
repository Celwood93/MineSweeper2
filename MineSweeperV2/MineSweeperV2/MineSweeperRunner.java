
public class MineSweeperRunner{
	
	public static void main(String [] args){
		boolean playing = true;
		MSData instance;
		String [] scores;
		MineSweeperInstance game;
		while(playing){
			instance = new MSData();
			scores = getHighScores();
			Gui.promptGameData(instance, scores);
			game = new MineSweeperInstance(instance);
			//right here, set instance.setTime = clock time at the moment
			game.run();
			if(instance.won()){
				updateHighScores(instance);
			}
			playing = Gui.displayResult(instance);
		}
		
		
	}
	//might make this another function - outside of here, for testing purposes. easy to add, so we will see.
	public static String[] getHighScores(){
		
		String [] initialTest = {"Cameron Elwood 0m 10s", "Cameron Elwood 0m 20s","Cameron Elwood 0m 30s","Cameron Elwood 0m 40s","Cameron Elwood 1m 00s"};
		return initialTest;
	}
	public static void updateHighScores(MSData instance){
		System.out.println("winner winner chicken dinner");
		
	}
	

	
}