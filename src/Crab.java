import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
/**
 *   <h1>Crab Class</h1> This is the Crab class. These are specifically Blue Crab crabs. They are the 'enemies' of the game as in they subtract from the player lives, but they are not 
 *   bad guys. When the player steps on them, they dont die but they certainly are upset. There is a set number of crabs in the game and they walk along the platforms for the player to avoid at varying speeds.
 *   They are animated, and while it may apear they are walking forward, they are infact moving from side to side as blue crabs do. Crabs move from right to left on the frame<p> 
 *   
 *   @author mattstack
 *   @version beta
 */
public class Crab{
	int crabXPos;
	int crabYPos;
	Rectangle crabRect;
	Boolean steppedOn;
	int speed;
	int rectangleOffset = 70;
	int crabRectWidth = 70;
	int crabRectHeight = 40;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	final int frameWidth = (int) screenSize.getWidth();
	final int frameHeight = (int) screenSize.getHeight();
	
/**
 * <h1>Crab Constructor</h1>A Crab is made by a x position, a y position, and a speed value. Besides that they have a Rectangle used for collisions that is mapped witht eh x and y, and a boolean that always starts as false but if 
 * the player collides with the Crabs Rectangle it turns true, used so each crab can only take one life from a player. 
 * 
 * @param x the x position of the Crab
 * @param y the x position of the Crab
 */
	public Crab(int x, int y, int speed) {
		this.crabXPos = getScaledWidth(x);
		this.crabYPos = getScaledHeight(y);
		this.crabRect = new Rectangle((getScaledWidth(x) + getScaledWidth(rectangleOffset)), (getScaledHeight(y) + getScaledHeight(rectangleOffset)), (getScaledWidth(crabRectWidth)), (getScaledHeight(crabRectHeight)));
		this.speed = speed;
		steppedOn = false;
	}
	
	/**
	 * <h1>getCrabXPos</h1>Returns the Crab's current x position, used heavily in the Model and View for the Crab position
	 * 
	 * @return Crab's current x position
	 */
	public int getCrabXPos() {
		return crabXPos;
	}

	/**
	 * <h1>setCrabXPos</h1>Sets the Crab's x position, used in Model to increment the crab's x by the a set walking speed.
	 * 
	 * @param crabXPos new value for Crab's x position
	 */
	public void setCrabXPos(int crabXPos) {
		this.crabXPos = crabXPos;
	}

	/**
	 * <h1>getCrabYPos</h1>Returns the Crab's current y position, used heavily in the Model and View for the Crab position
	 * 
	 * @return Crab's current y position
	 */
	public int getCrabYPos() {
		return crabYPos;
	}

	/**
	 * <h1>setCrabYPos</h1>Sets the Crab's y position, used in Model to increment the crab's y by the a set walking speed.
	 * 
	 * @param crabYPos the new value for Crab's y
	 */
	public void setCrabYPos(int crabYPos) {
		this.crabYPos = crabYPos;
	}
	
	/**
	 * <h1>getCrabRect</h1> Returns the Crab's Rectangle, useful for checking collision in Main
	 * 
	 * @return the Crab's Rectangle
	 */
	public Rectangle getCrabRect() {
		return crabRect;
	}
	
	/**
	 * <h1>setCrabRect</h1>Sets the Crab's Rectangle, used in Model for incrementing the rectangle across the screen with the crab image
	 * 
	 * @param x the x location of the rectangle
	 * @param y the y location of the new rectangle, does not change width and height because it is not needed
	 */
	public void setCrabRect(int x, int y) {
		crabRect = new Rectangle(-(getScaledWidth(x) - getScaledHeight(rectangleOffset)), -(getScaledHeight(y) - getScaledHeight(rectangleOffset)), getScaledWidth(crabRectWidth), getScaledHeight(crabRectHeight));
	}
	
	/**
	 * <h1>getSteppedOn</h1> Returns the Crab's current steppedOn Boolean value
	 * 
	 * @return the Crab's steppedOn value
	 */
	public Boolean getSteppedOn() {
		return steppedOn;
	}
	
	/**
	 * <h1>setSteppedOn</h1>Sets the Crab's steppedOn Boolean value, used in Model to see if the crab can takes away the players life or if it already has once
	 * 
	 * @param b the new Boolean value of steppedOn value
	 */
	public void setSteppedOn(Boolean b) {
		this.steppedOn = b;
	}
	
	/**
	 * <h1>getSpeed</h1> Returns the speed of the crab, used to increment position correctly in Model
	 * @return the speed of the crab
	 */
	public int getSpeed() {
		return this.speed;
	}
	
	@Override
	public int hashCode() {
		return this.speed;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Crab) {
			Crab other = (Crab) o;
			return this.speed == other.speed && this.crabXPos == other.crabXPos;
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