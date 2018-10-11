

import java.awt.Rectangle;
import java.util.Random;

/**
 * This is a class used to represent birds in the Bird Watching Game.
 * 
 * @author Tyler
 *
 */
public class Bird {
	int xSpd, ySpd;
	int xPos, yPos;
	int bWidth, bHeight;
	int screenWidth, screenHeight;
	BDirection direction;
	Species species;
	Rectangle rect;

	/**
	 * Creates an instance of the Bird class. This constructor assigns an x and
	 * y position, an x and y speed, and the direction of a Bird based on the
	 * species of the Bird.
	 * 
	 * @param sp
	 *            The Species of Bird to create an instance of.
	 * @param w
	 *            The width of the screen
	 * @param h
	 *            The height of the screen
	 */
	public Bird(Species sp, int w, int h) {
		screenWidth = w;
		screenHeight = h;
		this.species = sp;
		Random rand = new Random();
		if (sp == Bird.Species.BLUE_HERON) {
			this.xSpd = 0; // Blue Heron doesn't move
			this.ySpd = 0; // Blue Heron doesn't move
			this.xPos = getScaledWidth(150) + rand.nextInt(getScaledWidth(525)); // keeps Blue Heron on grass																		
			this.yPos = getScaledHeight(350) + rand.nextInt(getScaledHeight(170)); // keeps Blue Heron on grass												
			int dirToUse = rand.nextInt(2); // used to choose east or west direction
			if (dirToUse == 1)
				dirToUse = 3;
			this.direction = BDirection.values()[dirToUse]; // east or west
			this.bWidth = getScaledWidth(160);
			this.bHeight = getScaledHeight(160);
			rect = new Rectangle(xPos, yPos, bWidth, bHeight);
		} else if (sp == Bird.Species.SANDPIPER) {
			this.xSpd = rand.nextInt(15) + 15;
			this.ySpd = rand.nextInt(15) + 15;
			this.xPos = getScaledWidth(776) + rand.nextInt(getScaledWidth(153));
			this.yPos = getScaledHeight(431) + rand.nextInt(getScaledHeight(138));
			this.direction = BDirection.values()[rand.nextInt(6)];
			this.bWidth = getScaledWidth(80);
			this.bHeight = getScaledHeight(80);
			rect = new Rectangle(xPos, yPos, bWidth, bHeight);
		} else {
			this.xSpd = rand.nextInt(20) + 15;
			this.ySpd = rand.nextInt(20) + 15;
			this.xPos = rand.nextInt(screenWidth - getScaledWidth(331)); // keeps inside screen bounds
			this.yPos = rand.nextInt(getScaledHeight(330)); // keeps above grass level
			int randStartDir = rand.nextInt(2);
			if (randStartDir == 1)
				randStartDir = 3;
			this.direction = BDirection.values()[randStartDir];
			this.bWidth = getScaledWidth(160);
			this.bHeight = getScaledHeight(175);
			rect = new Rectangle(xPos, yPos, bWidth, bHeight);
		}
	}

	/**
	 * This enumeration is used to represent species of Birds.
	 * 
	 * @author Tyler
	 */
	public enum Species {
		BLUE_HERON("great-blue-heron"), SANDPIPER("least-sandpiper"), OSPREY("osprey");

		String name;
		int width;
		int height;

		/**
		 * Creates an instance of the Species enumeration. This constructor
		 * assigns a name to the Species of Bird.
		 * 
		 * @param n
		 *            The name to assign to the Species of Bird.
		 */
		private Species(String n) {
			this.name = n;
		}

		/**
		 * Gets the name associated with the Species.
		 * 
		 * @return A string, the name associated with the Species.
		 */
		public String getName() {
			return name;
		}
	}

	// GETTERS & SETTERS
	/**
	 * Gets the x speed of the Bird.
	 * 
	 * @return The current x speed of the Bird.
	 */
	public int getXSpeed() {
		return xSpd;
	}

	/**
	 * Sets the x speed attribute of the Bird.
	 * 
	 * @param xs
	 *            The speed to assign to the x speed attribute of the Bird.
	 */
	public void setXSpeed(int xs) {
		xSpd = xs;
	}

	/**
	 * Gets the y speed of the Bird.
	 * 
	 * @return The current y speed of the Bird.
	 */
	public int getYSpeed() {
		return ySpd;
	}

	/**
	 * Sets the y speed attribute of the Bird.
	 * 
	 * @param ys
	 *            The speed to assign to the y speed attribute of the Bird.
	 */
	public void setYSpeed(int ys) {
		ySpd = ys;
	}

	/**
	 * Gets the direction of the Bird.
	 * 
	 * @return The current direction of the Bird.
	 */
	public BDirection getDirection() {
		return direction;
	}

	/**
	 * Sets the direction attribute of the Bird.
	 * 
	 * @param dir
	 *            The BDirection to assign to the direction attribute of the
	 *            Bird.
	 */
	public void setDirection(BDirection dir) {
		direction = dir;
	}

	/**
	 * Gets the Species of the Bird.
	 * 
	 * @return The Species of the Bird.
	 */
	public Species getSpecies() {
		return species;
	}

	/**
	 * Sets the species attribute of a Bird.
	 * 
	 * @param sp
	 *            The Species to assign to the species attribute of the Bird.
	 */
	public void setSpecies(Species sp) {
		species = sp;
	}

	/**
	 * Gets the x position of the Bird.
	 * 
	 * @return The x position of the Bird.
	 */
	public int getXPos() {
		return xPos;
	}

	/**
	 * Sets the x position attribute of the Bird.
	 * 
	 * @param xp
	 *            The position to assign to the x position attribute of the
	 *            Bird.
	 */
	public void setXPos(int xp) {
		xPos = xp;
	}

	/**
	 * Gets the y position of the Bird.
	 * 
	 * @return The y position of the Bird.
	 */
	public int getYPos() {
		return yPos;
	}

	/**
	 * Sets the y position of the Bird.
	 * 
	 * @param yp
	 *            The position to assign to the y position attribute of the
	 *            Bird.
	 */
	public void setYPos(int yp) {
		yPos = yp;
	}

	/**
	 * Gets the Rectangle of the Bird, which represents the hitbox of the Bird.
	 * 
	 * @return The Rectangle hitbox of the bird.
	 */
	public Rectangle getRect() {
		return rect;
	}

	/**
	 * Sets the Rectangle for the bird, which represents the hitbox of the bird
	 * 
	 * @param r
	 *            The Rectangle to assign as the hitbox for the bird.
	 */
	public void setRect(Rectangle r) {
		rect = r;
	}

	/**
	 * Gets the width of the Bird.
	 * 
	 * @return The width of the Bird.
	 */
	public int getBWidth() {
		return bWidth;
	}

	/**
	 * Sets the width of the Bird.
	 * 
	 * @param w
	 *            The value to set the width of the Bird to.
	 */
	public void setBWidth(int w) {
		bWidth = w;
	}

	/**
	 * Gets the height of the Bird.
	 * 
	 * @return The height of the Bird.
	 */
	public int getBHeight() {
		return bWidth;
	}

	/**
	 * Sets the height of the Bird.
	 * 
	 * @param h
	 *            The value to set the height of the Bird to.
	 */
	public void setBHeight(int h) {
		bHeight = h;
	}

	/**
	 * Gets the scaled width to use for a Bird.
	 * 
	 * @param n
	 *            The number to use within the scale.
	 * @return An int to use as the scaled width metric.
	 */
	public int getScaledWidth(int n) {
		double number = (double) n;
		double position = (number / 1440) * screenWidth;
		return (int) position;
	}

	/**
	 * Gets the scaled height to use for a Bird.
	 * 
	 * @param n
	 *            The number to use within the scale.
	 * @return An int to use as the scaled height metric.
	 */
	public int getScaledHeight(int n) {
		double number = (double) n;
		double position = (number / 900) * screenHeight;
		return (int) position;
	}
}
