/*
 * Joel Turk
 */

/**
 * Represents a Fish that moves around in the fishing mini game
 * @author Joel
 * 
 */
public class Fish extends Mover{
	/**
	 * Species of fish in our game have different sprites, sizes, and animations
	 * @author Joel
	 * 
	 */
	public enum Species{
		AMERICAN_SHAD("american-shad"),
		STURGEON("sturgeon"),
		SUMMER_FLOUNDER("summer-flounder");
		
		private String name = null;
		
		/**
		 * This holds a species' sprite filepath in a String.
		 * @param s A string containing name of the species (same as its filepath).
		 */
		private Species(String s){
			name = s;
		}
		/**
		 * Gets a species formal name.
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
	 * @return an instance of Fish.Species 
	 */
	public Species getSpecies(){return species;}
	/**
	 * Sets this Fish's Species type
	 * @param sp the Species of this Fish
	 */
	public void setSpecies(Species sp){species = sp;}
	
	
	
	/**
	 * Creates a fish based off of the specifications given. X and Y locations are randomized.
	 * @param s Species of fish
	 * @param xS an int, Horizontal speed
	 * @param yS an int, Vertical speed
	 * @param dir Starting Direction for the fish to move in
	 */
	public Fish(Species s, int xS, int yS, Direction dir){
		species = s;
		xSpeed = xS;
		ySpeed = yS;
		direction = dir;
		xPos = (int) (Math.random()*FishingGameView.getWidth()/2 + FishingGameView.getWidth()/4);
		yPos = (int) (Math.random()*FishingGameView.getHeight()/2 + 300);
	}
}
