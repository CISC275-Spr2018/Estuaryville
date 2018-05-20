import java.awt.Rectangle;

/**
 * Represents a Fish that moves around in the fishing mini game
 * 
 * @author Joel
 * 
 */
public class Fish extends Mover {

	boolean hooked;
	Rectangle mouth;
	int hookedFor;

	/**
	 * Species of fish in our game have different sprites, sizes, and animations
	 * 
	 * @author Joel
	 * 
	 */
	public enum Species {
		AMERICAN_SHAD("american-shad"), STURGEON("sturgeon"), SUMMER_FLOUNDER("summer-flounder");

		private String name = null;

		/**
		 * This holds a species' sprite filepath in a String.
		 * 
		 * @param s
		 *            A string containing name of the species (same as its
		 *            filepath).
		 */
		private Species(String s) {
			name = s;
		}

		/**
		 * Gets a species formal name.
		 * 
		 * @return A String containing the filepath of the species' sprite.
		 */
		public String getName() {
			return name;
		}
	}

	Species species;

	/* GETTERS & SETTERS */

	/**
	 * Gets the Species of this Fish
	 * 
	 * @return an instance of Fish.Species
	 */
	public Species getSpecies() {
		return species;
	}

	/**
	 * Sets this Fish's Species type
	 * 
	 * @param sp
	 *            the Species of this Fish
	 */
	public void setSpecies(Species sp) {
		species = sp;
	}

	/**
	 * Gets the Rectangle hit box for this Fish's mouth
	 * 
	 * @return a Rectangle
	 */
	public Rectangle getMouth() {
		return this.mouth;
	}

	/**
	 * Updates the location of this Fish's mouth
	 * 
	 * @param x
	 *            new x location
	 * @param y
	 *            new y location
	 */
	public void setMouth(int x, int y, Direction dir) {
		if (dir == Direction.EAST) {
			mouth.setLocation(x + (int) (.70 * FishingGameView.FISH_WIDTH),
					y + (int) (.4 * FishingGameView.FISH_HEIGHT));
		} else if (dir == Direction.WEST) {
			mouth.setLocation(x + (int) (.12 * FishingGameView.FISH_WIDTH),
					y + (int) (.4 * FishingGameView.FISH_HEIGHT));
		}
	}

	/**
	 * Creates a fish based off of the specifications given. X and Y locations
	 * are randomized.
	 * 
	 * @param s
	 *            Species of fish
	 * @param xS
	 *            an int, Horizontal speed
	 * @param yS
	 *            an int, Vertical speed
	 * @param dir
	 *            Starting Direction for the fish to move in
	 */
	public Fish(Species s, int xS, int yS, Direction dir) {
		species = s;
		xSpeed = xS;
		ySpeed = yS;
		direction = dir;
		hooked = false;
		xPos = (int) (Math.random() * FishingGameView.getWidth());
		yPos = (int) (Math.random()
				* (FishingGameModel.WATER_BOTTOM - FishingGameModel.WATER_TOP - FishingGameView.FISH_HEIGHT)
				+ FishingGameModel.WATER_TOP);
		if (dir == Direction.EAST) {
			mouth = new Rectangle(xPos + (int) (.70 * FishingGameView.FISH_WIDTH),
					yPos + (int) (.4 * FishingGameView.FISH_HEIGHT), FishingGameView.FISH_WIDTH / 5,
					FishingGameView.FISH_HEIGHT / 5);
		} else {
			mouth = new Rectangle(xPos + (int) (.12 * FishingGameView.FISH_WIDTH),
					yPos + (int) (.4 * FishingGameView.FISH_HEIGHT), FishingGameView.FISH_WIDTH / 5,
					FishingGameView.FISH_HEIGHT / 5);
		}
		hookedFor = 0;
	}

	/**
	 * Sets the fish hooked attribute to true or false
	 * 
	 * @param b
	 *            a boolean representing if the fish is hooked
	 */
	public void setHooked(boolean b) {
		hooked = b;
	}

	/**
	 * Tells whether this Fish is hooked or not
	 * 
	 * @return a Boolean, isHooked
	 */
	public boolean getHooked() {
		return hooked;
	}
}
