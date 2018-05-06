/*
 * Joel Turk
 */

/**
 * This is a model of the fishing game that holds all objects that should be presented during the game.
 * This model is responsible for movement, collision, and all other dynamic aspects of the fishing game.
 * @author Joel
 */
public class FishingGameModel extends Model{
	
	public Hook hook;
	public Fish[] fishes;
	
	/**
	 * Creates a new instance of FishingGameModel
	 * Adds an interactive hook and fish to the screen.
	 * @param w width of the game's JFrame
	 * @param h height of the game's JFrame
	 */
	public FishingGameModel(int w, int h){
		super(w, h);
		fishes = new Fish[3];
		for(int i = 0; i < 3; i++){
			fishes[i] = new Fish(Fish.Species.values()[i], 9, 2, (i % 2 == 0 ? Direction.EAST : Direction.WEST));
		}
		hook = new Hook();
		hook.setXSpeed(0);
		hook.setYSpeed(0);
	}
	
	/**
	 * Gets all Fish currently in the game's Model
	 * @return an array of Fish objects
	 */
	public Fish[] getFish(){return fishes;}
	/**
	 * Gets the Hook object that the user may move
	 * @return the current Hook
	 *
	 */
	public Hook getHook(){return hook;}
	
	/**
	 * Scaffolding method to update every object's movement and check for collisions
	 */
	@Override
	public void update(){
		Fish f;
		for(int i = 0; i < fishes.length; i++){
			f = fishes[i];
			checkBounds(f);
		}
		checkBounds(hook);
	}
	
	/**
	 * updates movement and keeps fish within frame bounds
	 * @param f a Fish object to update
	 *
	 */
	public void checkBounds(Fish f){
		switch(f.getDirection()){
		case NORTH:
			if(f.getYPos() - f.getYSpeed() < 300)
				f.setDirection(Direction.SOUTH);
			else
				f.setYPos(f.getYPos() - f.getYSpeed());
			break;
		case EAST:
			if(f.getXPos() + f.getXSpeed() > FishingGameView.getWidth() - 50)
				f.setDirection(Direction.WEST);
			else
				f.setXPos(f.getXPos() + f.getXSpeed());
			break;
		case WEST:
			if(f.getXPos() - f.getXSpeed() < -50)
				f.setDirection(Direction.EAST);
			else
				f.setXPos(f.getXPos() - f.getXSpeed());
			break;
		case SOUTH:
			if(f.getYPos() + f.getYSpeed() > FishingGameView.getHeight() - 100)
				f.setDirection(Direction.NORTH);
			else
				f.setYPos(f.getYPos() + f.getYSpeed());
			break;
		default:
			break;
		}
	}
	/**
	 * Updates hook position and keeps it within frame bounds
	 * @param h the current Hook
	 */
public void checkBounds(Hook h){
		if(h.getYPos() + h.getYSpeed() < (310.0 / 700 * FishingGameView.getHeight()) || h.getYPos() + h.getYSpeed() > FishingGameView.getHeight() - 100){
			//don't change y
		}else{
			h.setYPos(h.getYPos() + h.getYSpeed());
		}
		if(h.getXPos() + h.getXSpeed() < -50 || h.getXPos() + h.getXSpeed() > FishingGameView.getWidth() - 50){
			//don't change x
		}else{
				h.setXPos(h.getXPos() + h.getXSpeed());
		}
	}
}
