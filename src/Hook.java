import java.awt.Rectangle;

/*
 * Joel Turk
 */

/**
 * Represents the hook that the user moves around to catch fish with
 * @author Joel
 *
 */
public class Hook extends Mover{
	Rectangle hitbox;
	boolean gotABite;
	
	/**
	 * Creates a new hook with (x,y) = (500, 450)
	 */
	public Hook(){
		super();
		super.setXPos((int) (500.0/1100.0 * FishingGameView.getWidth()));
		super.setYPos((int) (450.0/700.0 * FishingGameView.getHeight()));
		hitbox = new Rectangle(getXPos(), getYPos(), FishingGameView.HOOK_WIDTH, FishingGameView.HOOK_HEIGHT);
		gotABite = false;
	}
	
	/**
	 * Gets the Rectangle hit box for this Hook
	 * @return a Rectangle
	 */
	public Rectangle getHitbox(){return this.hitbox;}
	/**
	 * Updates the location of this Hook's hit box
	 * @param x new x location
	 * @param y new y location
	 */
	public void setHitbox(int x, int y){
		hitbox.setLocation(x, y);
	}
	
	/**
	 * Sets a boolean flag to know if there is a Fish hooked
	 * @param b a boolean, true if a fish is hooked
	 */
	public void setBite(boolean b){
		gotABite = b;
	}
	/**
	 * Returns true if a Fish is hooked, false otherwise
	 * @return a boolean
	 */
	public boolean getBite(){return gotABite;}
	
}
