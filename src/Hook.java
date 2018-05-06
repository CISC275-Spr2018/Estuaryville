/*
 * Joel Turk
 */

/**
 * Represents the hook that the user moves around to catch fish with
 * @author Joel
 *
 */
public class Hook extends Mover{
	final int hookRadius = 10;
	
	/**
	 * Creates a new hook with (x,y) = (500, 450)
	 */
	public Hook(){
		super();
		super.setXPos((int) (500.0/1100.0 * FishingGameView.getWidth()));
		super.setYPos((int) (450.0/700.0 * FishingGameView.getHeight()));
	}
	
}
