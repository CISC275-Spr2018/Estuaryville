import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Dimension;
/**
 *   <h1>Researcher Class</h1> This is the Researcher class. The researcher is the main character of the game. 
 *   She has a x position, y position, direction, number of lives, and a rectangle. The research character is actually painted statically on the panel (with walking animation)
 *   but the larger frame moves in opposite of the incrementing xPos and yPos to simulate movement. The player's rectangle is used for collision detection using java's rectangle collision.
 *   <p>Note: Anytime in the method describitions for this class, if it says 'the Researcher moves' it means that the character is still painted statically, but the characters x and y increase
 *   and decrease during the game, moving the background, until the end of the game, where the background becomes static and the player actually move across the screen, signaling the end of the game<p> 
 *   
 *   @author mattstack
 *   @version beta
 */
public class Researcher {
	int xPos;
	int yPos;
	RDirection direction;
	int lives;
	Rectangle playerRect;
	
	int xOffsetToFeet = 40;
	int yOffsetToFeet = 160;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	final int frameWidth = (int) screenSize.getWidth();
	final int frameHeight = (int) screenSize.getHeight();
	
	final int playerFixedX = (int)screenSize.getWidth() / 8;
	final int playerFixedY = (int)screenSize.getHeight() / 3;
	
/**
 * <h1>Researcher Contructor</h1>The Researcher contructor is set by a x and y position, a direction, and the lives. There is also a rectangle that is associated with the the characters feet, used for collision of crabs
 * and out of bounds
 * 
 * @param x the current x location of the Researcher incremented by the controller, does not actually move the character Researcher but moves the background inverted to 
 * simulate movement
 * @param y the current y location of the Researcher incremented by the controller, does not actually move the character Researcher but moves the background inverted to 
 * simulate movement
 * @param direction the direction of the Researcher using the RDirection Enum set, East, NorthEast, SouthEast, and Idle
 * @param lives remaining lives of the Researcher, the player will lose a life if they step on a crab because they are heartbroken over it. 
 * Going off the platforms results in lose of all 3 lives, where the game restarts
 */
	public Researcher(int x, int y, RDirection direction, int lives) {
		this.xPos = getScaledWidth(x);
		this.yPos = getScaledHeight(y);
		this.direction = direction;
		this.lives = lives;
		this.playerRect = new Rectangle(playerFixedX + getScaledWidth(xOffsetToFeet), playerFixedY + getScaledHeight(yOffsetToFeet), getScaledWidth(100), getScaledHeight(25));
	}
/**
 * <h1>SetDirection</h1>Sets the direction of the Researcher, used in the Controller to set which direction the player wants the character to be moving in.
 * 
 * @param direction this is the new direction that is set for the Researcher. RDirection is an Enum type
 */
	public void setDirection(RDirection direction) {
		this.direction = direction;
	}	
	
/**
 * <h1>getDirection</h1>Returns the current direction that is set to the Researcher, used in View to get the current direction to set the correct animation. 
 * 
 * @return Researcher's current direction, one of the RDirection Enums East, NorthEast, SouthEast, and Idle
 */
	public RDirection getDirection() {
		return direction;
	}
	
	/**
	 * <h1>setLives</h1>Sets the number of lives the Research has, base lives is 3, used heavily in the collision methods in Model to subtract a life for stepping on a crab, or all 3 
	 * for going of the platforms
	 * 
	 * @param lives new number of lives the Researcher will have 
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	/**
	 * <h1>getLives</h1>Returns the current number of lives the Researcher has left, used in Model to see whether the game should be restarted if it drops to 0 or not 
	 * 
	 * @return Researcher's current number of lives
	 */
	public int getLives() {
		return this.lives;
	}
	
	/**
	 * <h1>getxPos</h1>Returns the Researcher's current x position, used heavily in the Model and View for the player position
	 * 
	 * @return Researcher's current x position
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * <h1>setxPos</h1>Sets the Researcher's x position to a new value xPos, used in Model to increment to players x position given a constant walk speed increase
	 * 
	 * @param xPos the new value that the Researcher will have for its x position
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * <h1>getyPos</h1>Returns the Researcher's current y position, used heavily in the Model and View for the player position
	 * 
	 * @return Researcher's current y position
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * <h1>setyPos</h1>Sets the Researcher's y position to a new value yPos, used in Model to increment to players y position given a constant walk speed increase
	 * 
	 * @param yPos the new value that the Researcher will have for its y position
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * <h1>getPlayerRect</h1>Returns the Researcher's Rectangle, set at the player feet and used in Model for colision detection. The rectangle is not showed to the player but can be seen for testing. Moves along with the Researcher's x and y
	 * 
	 * @return The Researcher's current rectangle
	 */
	public Rectangle getPlayerRect() {
		return playerRect;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Researcher) {
			Researcher other = (Researcher) o;
			return (this.getxPos() == other.getxPos() && this.getyPos() == other.getyPos());
		}
		else {
			return false;
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
