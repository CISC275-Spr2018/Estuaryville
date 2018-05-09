import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 *   <h1>ResearchGameModel Class</h1> Going by the MVC design pattern, this is the Model class. This is more or less the brain of the ResearchGame. Everything that is needs to be 
 *   updated is calcualted here and sent over to the view to paint. This class is fed by the controller, with each key press of the game controls the players direction is updated in conrtoller and
 *   sent here to this class to do the game logic. The main methods are multiple collision detections like player with crabs and player with out-of-bounds, and the update location which updates the position of everything that
 *   moves and sent to View.<p> 
 *   
 *   @author mattstack
 *   @version beta
 */
public class ResearchGameModel{
	final int xIncr = 15;
    final int yIncr = 14; //originally 14
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	final int frameWidth = (int) screenSize.getWidth();
	final int frameHeight = (int) screenSize.getHeight();
	
	final int playerStartingX = (int)screenSize.getWidth() / 8;
	final int playerStartingY = (int)screenSize.getHeight() / 3;
	final int startingLives = 3;
	final int imgWidth;
	final int imgHeight;
	final int width;
	final int height;
	final RDirection startDir = RDirection.IDLE;
	Researcher player;
	Crab crab1;
	Crab crab2;
	Crab crab3;
	Crab crab4;
	Crab crab5;
	Crab crab6;
	Crab crab7;
	Crab crab8;
	Crab crab9;
	Crab crab10;
	Crab crab11;
	Crab crab12;
	Crab crab13;
	Crab crab14;
	Crab crab15;
	Crab crab16;
	Crab crab17;
	Crab crab18;
	Crab crab19;
	Crab crab20;
	Crab crab21;
	Crab crab22;
	Crab crab23;
	Crab crab24;
	Crab crab25;
	public Crab[] crabs;
	
	Rectangle rect1;
	Rectangle rect2;
	Rectangle rect3;
	Rectangle rect4;
	Rectangle rect5;
	Rectangle rect6;
	Rectangle rect7;
	Rectangle rect8;
	Rectangle rect9;
	public Rectangle[] rects;	

	/**
	 * <h1>ResearcherGameModel Constructor</h1> Constructs the Model. Takes in a width, height, imageWidth, and imageHeight from the views knowledge of screen size and image (sprite) size. Outside of this is creates crabs and adds them 
	 * to the crabs array and creates rectangles for boundaries and add those to the rectangle array, both with specific placement
	 * 
	 * @param width width of the frame, passed in from View
	 * @param height height of the frame, passed in from the View
	 * @param imageWidth width of the images
	 * @param imageHeight height of the images
	 */
	public ResearchGameModel(int width, int height, int imageWidth, int imageHeight){
		player = new Researcher(playerStartingX, playerStartingY, startDir, startingLives);
		crab1 = new Crab(-700, -400, 25); //normal
		crab2 = new Crab(-2500, -800, 25); //normal
		crab3 = new Crab(-3500, -800, 20); //slow
		crab4 = new Crab(-2700, -900, 25); //normal
		crab5 = new Crab(-3900, -750, 28); //speedy
		crab6 = new Crab(-3800, -820, 20); //slow
		crab7 = new Crab(-4900, -910, 25); //normal
		crab8 = new Crab(-8550, -850, 28); //speedy
		crab9 = new Crab(-800, -840, 20); //slow
		crab10 = new Crab(-900, -820, 20); //slow
		crab11 = new Crab(-1200, -910, 25); //normal
		crab12 = new Crab(-6000, -910, 25); //normal
		crab13 = new Crab(-2500, -1380, 20); //slow
		crab14 = new Crab(-3000, -1440, 25); //normal
		crab15 = new Crab(-4000, -1460, 25); //normal
		crab16 = new Crab(-800, -840, 20); //slow
		crab17 = new Crab(-900, -820, 20); //slow
		crab18 = new Crab(-7000, -910, 25); //normal
		crab19 = new Crab(-8000, -910, 25); //normal
		crab20 = new Crab(-8000, -1300, 20); //slow
		crab21 = new Crab(-8500, -1400, 20); //slow
		crab22 = new Crab(-4550, -375, 20); //slow
		crab23 = new Crab(-4700, -390, 20); //slow
		crab24 = new Crab(-5150, -320, 20); //slow
		crab25 = new Crab(-5460, -390, 20); //slow
		crabs = new Crab[25];
		crabs[0] = crab1;
		crabs[1] = crab2;
		crabs[2] = crab3;
		crabs[3] = crab4;
		crabs[4] = crab5;
		crabs[5] = crab6;
		crabs[6] = crab7;
		crabs[7] = crab8;
		crabs[8] = crab9;
		crabs[9] = crab10;
		crabs[10] = crab11;
		crabs[11] = crab12;
		crabs[12] = crab13;
		crabs[13] = crab14;
		crabs[14] = crab15;
		crabs[15] = crab16;
		crabs[16] = crab17;
		crabs[17] = crab18;
		crabs[18] = crab19;
		crabs[19] = crab20;
		crabs[20] = crab21;
		crabs[21] = crab22;
		crabs[22] = crab23;
		crabs[23] = crab24;
		crabs[24] = crab25;
		
		rect1 = new Rectangle(0, 0, getScaledWidth(6000), getScaledHeight(330)); //locked
		rect2 = new Rectangle(0, getScaledHeight(550), getScaledWidth(550), getScaledHeight(330)); //locked
		rect3 = new Rectangle(getScaledWidth(1360), 0, getScaledWidth(1190), getScaledHeight(825)); //locked
		rect4 = new Rectangle(getScaledWidth(2180), getScaledHeight(1050), getScaledWidth(300), getScaledHeight(300)); //locked
		rect5 = new Rectangle(getScaledWidth(3260), getScaledHeight(1050), getScaledWidth(1650), getScaledHeight(300)); //locked
		rect6 = new Rectangle(0, getScaledHeight(1060), getScaledWidth(1350), getScaledHeight(300)); //locked
		rect7 = new Rectangle(getScaledWidth(4215), getScaledHeight(540), getScaledWidth(580), getScaledHeight(290)); //locked
		rect8 = new Rectangle(0, getScaledHeight(1590), getScaledWidth(6000), getScaledHeight(290)); //locked
			
		rects = new Rectangle[8];
		rects[0] = rect1;
		rects[1] = rect2;
		rects[2] = rect3;
		rects[3] = rect4;
		rects[4] = rect5;
		rects[5] = rect6;
		rects[6] = rect7;
		rects[7] = rect8;
		this.width = width;
		this.height = height;
		this.imgHeight = imageHeight;
		this.imgWidth = imageWidth;
	}
	
	/**
	 * <h1>getCrabs</h1> Returns the array of Crabs create from the Model constructor
	 * @return an array of Crabs
	 */
	public Crab[] getCrabs() {
		return crabs;
	}
	
	/**
	 * <h1>getRects</h1> Returns the array of Rectangle that represent the out-of-bounds to pass into the collision detector
	 * @return an array of Rectangles
	 */
	public Rectangle[] getRects() {
		return rects;
	}
	
	/**
	 * <h1>getPlayer</h1>Returns the Researcher player
	 * 
	 * @return the player
	 */
	public Researcher getPlayer() {
		return this.player;
	}
	
	/**
	 * <h1>crabCollisionChecker</h1> Checks if the any of the crab's rectanclges from any crab on the list is intersecting the players rectangle.
	 *  If so, then subtract a life point from player if the crab has not been steppedOn before, and set that crabs steppedOn to be true
	 */
	public void crabCollisionChecker() {
		for (Crab c : crabs){
			if (player.getPlayerRect().intersects(c.crabRect) && (c.getSteppedOn() == false)) {
				player.setLives(player.getLives() - 1);
				c.setSteppedOn(true);
			}
		}
	}
	
	/**
	 * <h1>boundsCollison</h1> Checks if the player rectangle is intersecting any of the out-of-bounds rectangles, and if so, then subtract all the points and make the game
	 * start over
	 */
	public void boundsCollisionChecker() {
		for (Rectangle re : rects) {
			if (player.getPlayerRect().intersects(re)) {
				player.setLives(player.getLives() - 1);
			}
		}
	}
	
	/**
	 * <h1>endCheck</h1> Checks to see if the player has gotten to the end of the game and will be sent back to the main screen, returns true if so
	 * @return boolean value of it game is over (true) and should be brought back to main screen
	 */
	public Boolean endCheck() {
		if (player.getxPos() >= getScaledWidth(5500)) {
			return true;
			//System.out.print("game over back to main");
			//add sending back to main screen
		}
		else {
			return false;
		}
	}
	
	/**
	 * <h1>lifeCheck</h1>Checks if the player has run out of lives, and if so, restart the game
	 * @return whether True if the player has greater than 0 lives and False if not
	 */
	public boolean lifeCheck() {
		if (player.getLives() <= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * <h1>updateLocationAndModel</h1> Handles all of the location changing, all depending on the direction of the player (from Controller). Updates all crabs positions and rectangle, 
	 * player position, and bounds rectangles.
	 */
	public void updateLocationAndDirection(){	
//		System.out.println("x: " + player.getxPos() + ", y: " + player.getyPos()); // print x and y of player
		if (player.direction.getName().contains("forward-east-sheet")) {
			player.setxPos(player.getxPos() + xIncr);
			
			for (Crab c : crabs) {
				c.setCrabXPos(c.getCrabXPos() + c.getSpeed());
				//c.setCrabRect(c.crabXPos, c.crabYPos);
				c.setCrabRect(c.getCrabXPos(), c.getCrabYPos());			}
			
			for (Rectangle re : rects) {
				re.setLocation((int)re.getX() - xIncr, (int)re.getY());
			}
		}

		
		if (player.direction.getName().contains("forward-southeast-sheet")) {
			player.setyPos(player.getyPos() + yIncr);
			player.setxPos(player.getxPos() + xIncr);
			
			for (Crab c : crabs) {
				c.setCrabYPos(c.getCrabYPos() + yIncr);
				c.setCrabXPos(c.getCrabXPos() + c.getSpeed());
				c.setCrabRect(c.getCrabXPos(), c.getCrabYPos());
			}
			for (Rectangle re : rects) {
				re.setLocation((int)re.getX() - xIncr, (int)re.getY() - yIncr);
			}
		}
		if (player.direction.getName().contains("forward-northeast-sheet")) {

			player.setyPos(player.getyPos() - yIncr);
			player.setxPos(player.getxPos() + xIncr);
			
			for (Crab c : crabs) {
				c.setCrabYPos(c.getCrabYPos() - yIncr);
				c.setCrabXPos(c.getCrabXPos() + c.getSpeed());
				c.setCrabRect(c.getCrabXPos(), c.getCrabYPos());			}
			
			for (Rectangle re : rects) {
				re.setLocation((int)re.getX() - xIncr, (int)re.getY() + yIncr);
			}
		}
		
		if (player.direction.getName().contains("idle-sheet")) {
			
			
			for (Crab c: crabs) {
				c.setCrabXPos(c.getCrabXPos() + (c.getSpeed() - xIncr));
				c.setCrabRect(c.getCrabXPos(), c.getCrabYPos());
			}
			
			for (Rectangle re : rects) {
				re.setLocation((int)re.getX(), (int)re.getY());
			}
		}
		
	}
	
	/**
	 * Scales the width for any screen
	 * 
	 * @param n the width that looks good on the screen
	 * @return a width that fits all screens
	 */
	public int getScaledWidth(int n) {
		double number = (double)n;
		double position = (number / 1440) * frameWidth;
		return (int) position;
	}
	
	/**
	 * Scales the height for any screen
	 * 
	 * @param n the height that looks good on the screen
	 * @return a height that fits all screens
	 */
	public int getScaledHeight(int n) {
		double number = (double)n;
		double position = (number / 900) * frameHeight;
		return (int) position;
	}
	
}
