
import java.awt.Rectangle;
import java.util.Random;

/**
 * This is a class used to represent birds in the Bird Watching Game. 
 * @author Tyler
 *
 */
public class Bird {
	int xSpd, ySpd;
	int xPos, yPos;
	BDirection direction;
	Species species;
	Rectangle rect;
	
	/**
	 * Creates an instance of the Bird class. This constructor assigns an x and y
	 * position, an x and y speed, and the direction of a Bird based on the species
	 * of the Bird. 
	 * @param sp The Species of Bird to create an instance of.
	 */
	public Bird(Species sp) {
		this.species = sp;
		Random rand = new Random();
		if (sp == Bird.Species.BLUE_HERON) {
			this.xSpd = 0; //Blue Heron doesn't move
			this.ySpd = 0; //Blue Heron doesn't move
			this.xPos = 150 + rand.nextInt(540); //keeps Blue Heron on grass
			this.yPos = 350 + rand.nextInt(200); //keeps Blue Heron on grass
			this.direction = BDirection.values()[rand.nextInt(6)];
			rect = new Rectangle(xPos, yPos, 160, 160);
		}
		else if (sp == Bird.Species.SANDPIPER) {
			this.xSpd = rand.nextInt(25) + 15;
			this.ySpd = rand.nextInt(25) + 15;
			this.xPos = 150 + rand.nextInt(540); 
			this.yPos = 350 + rand.nextInt(200); 
			this.direction = BDirection.values()[rand.nextInt(6)];
			rect = new Rectangle(xPos, yPos, 80, 80);
		}
		else {
			this.xSpd = rand.nextInt(25) + 15;
			this.ySpd = rand.nextInt(25) + 15;
			this.xPos = rand.nextInt(BirdWatchingGameView.getWidth() - 160); //keeps inside screen bounds
			this.yPos = rand.nextInt(BirdWatchingGameView.getHeight() - 175); // keeps inside screen bounds
			this.direction = BDirection.values()[rand.nextInt(6)];
			rect = new Rectangle(xPos, yPos, 160, 175);
		}
	}
	
	/**
	 * This enumeration is used to represent species of Birds.
	 * @author Tyler
	 *
	 */
	public enum Species{		
		BLUE_HERON("great-blue-heron"),
		SANDPIPER("least-sandpiper"),
		OSPREY("osprey");
		
		/**
		 * Creates an instance of the Species enumeration. This constructor assigns
		 * a name to the Species of Bird. 
		 * @param n The name to assign to the Species of Bird. 
		 */
		String name;
		int width;
		int height;
		private Species(String n) {
			this.name = n;
		}
	}
	
	// GETTERS & SETTERS
	/**
	 * Gets the x speed of the Bird.
	 * @return The current x speed of the Bird.
	 */
	public int getXSpeed(){return xSpd;}
	
	/**
	 * Sets the x speed attribute of the Bird. 
	 * @param xs The speed to assign to the x speed attribute of the Bird.
	 */
	public void setXSpeed(int xs){xSpd = xs;}
	
	/**
	 * Gets the y speed of the Bird.
	 * @return The current y speed of the Bird.
	 */
	public int getYSpeed(){return ySpd;}
	
	/**
	 * Sets the y speed attribute of the Bird.
	 * @param ys The speed to assign to the y speed attribute of the Bird.
	 */
	public void setYSpeed(int ys){ySpd = ys;}
	
	/**
	 * Gets the direction of the Bird.
	 * @return The current direction of the Bird.
	 */
	public BDirection getDirection(){return direction;}
	/**
	 * Sets the direction attribute of the Bird. 
	 * @param dir The BDirection to assign to the direction attribute of the Bird. 
	 */
	public void setDirection(BDirection dir){direction = dir;}
	
	/**
	 * Gets the Species of the Bird.
	 * @return The Species of the Bird.
	 */
	public Species getspecies(){return species;}
	
	/**
	 * Sets the species attribute of a Bird.
	 * @param sp The Species to assign to the species attribute of the Bird. 
	 */
	public void setSpecies(Species sp){species = sp;}
	
	/**
	 * Gets the x position of the Bird.
	 * @return The x position of the Bird.
	 */
	public int getXPos(){return xPos;}
	
	/**
	 * Sets the x position attribute of the Bird.
	 * @param xp The position to assign to the x position attribute of the Bird.
	 */
	public void setXPos(int xp){xPos = xp;}
	
	/**
	 * Gets the y position of the Bird.
	 * @return The y position of the Bird.
	 */
	public int getYPos(){return yPos;}
	
	/**
	 * Sets the y position of the Bird. 
	 * @param yp The position to assign to the y position attribute of the Bird. 
	 */
	public void setYPos(int yp){yPos = yp;}
}
