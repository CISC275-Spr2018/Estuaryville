public class Runner {
	public static void main(String [] args){
		ResearchGameView view = new ResearchGameView();
		ResearchGameModel model = new ResearchGameModel(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		ResearchGameController myC = new ResearchGameController();
		myC.start();
	}
}


