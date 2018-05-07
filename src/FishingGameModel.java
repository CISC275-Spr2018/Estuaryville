import java.util.ArrayList;

/*
 * Joel Turk
 */

/**
 * This is a model of the fishing game that holds all objects that should be
 * presented during the game. This model is responsible for movement, collision,
 * and all other dynamic aspects of the fishing game.
 * 
 * @author Joel
 */
public class FishingGameModel extends Model {

	public Hook hook;
	public ArrayList<Fish> fishes;
	public ArrayList<Trash> trashAL;
	final static int WATER_TOP = (int) (.52 * FishingGameView.getHeight());
	final static int WATER_BOTTOM = (int) (FishingGameView.getHeight() - 50);
	int piecesOfTrash;
	final static int MAX_TRASH = 8;
	Mover caught;
	int timeSinceCatch;
	boolean displayCatch;
	boolean gameOver;
	// GETTERS
	/**
	 * returns the Mover that is on the hook currently
	 * @return the caught Mover
	 */
	public Mover getCaught(){return caught;}
	/**
	 * a boolean telling whether the mini game is done
	 * @return true if game is over, false if not
	 */
	public boolean getGameOver(){return gameOver;}
	/**
	 * a boolean telling whether a fish has been reeled in
	 * @return true if a fish has been caught, false if not
	 */
	public boolean getDisplayCatch(){return displayCatch;}
	
	public void setPollutionLevel(double pl){
		piecesOfTrash = (int) (pl * MAX_TRASH);
		for(int i = 0; i < piecesOfTrash; i++){
			trashAL.add(new Trash(Trash.Type.values()[i%3]));
		}
	}
	
	
	/**
	 * Creates a new instance of FishingGameModel Adds an interactive hook and
	 * fish to the screen.
	 * 
	 * @param w
	 *            width of the game's JFrame
	 * @param h
	 *            height of the game's JFrame
	 */
	public FishingGameModel(int w, int h) {
		super(w, h);
		fishes = new ArrayList<Fish>();
		trashAL = new ArrayList<Trash>();
		for (int i = 0; i < 12; i++) {
			fishes.add(new Fish(Fish.Species.values()[i % 3], 9, 2, (i % 2 == 0 ? Direction.EAST : Direction.WEST)));
		}
		hook = new Hook();
		hook.setXSpeed(0);
		hook.setYSpeed(0);
		caught = null;
		gameOver = false;
		timeSinceCatch = -1;
	}

	/**
	 * Gets all Fish currently in the game's Model
	 * 
	 * @return an array of Fish objects
	 */
	public ArrayList<Fish> getFish() {
		return fishes;
	}
	
	/**
	 * Gets all Trash currently in the game's Model
	 * 
	 * @return an array of Fish objects
	 */
	public ArrayList<Trash> getTrash() {
		return trashAL;
	}

	/**
	 * Gets the Hook object that the user may move
	 * 
	 * @return the current Hook
	 *
	 */
	public Hook getHook() {
		return hook;
	}

	/**
	 * Scaffolding method to update every object's movement and check for
	 * collisions
	 */
	@Override
	public void update() {
		boolean reeling = false;
		Fish f;
		Trash t;
		int i = 0;
		if(fishes.size() == 0)
			gameOver = true;
		while (!reeling && i < fishes.size() + trashAL.size()) {
			if(i < fishes.size() && fishCaught(fishes.get(i))) {
				caught = fishes.get(i);
				reelItIn(fishes.get(i));
				reeling = true;
			}else if(i >= fishes.size() && trashCaught(trashAL.get(i - fishes.size()))){
				caught = trashAL.get(i - fishes.size());
				reelItIn(trashAL.get(i - fishes.size()));
				reeling = true;
			}
			i++;
		}
		if (!reeling) {
			caught = null;
			checkBounds(); // checks the hook
		}
		if(timeSinceCatch >= 0){
			displayCatch = true;
			timeSinceCatch++;
		}
		if(timeSinceCatch > 30){
			displayCatch = false;
			hook.setXPos((int) (500.0/1100.0 * FishingGameView.getWidth()));
			hook.setYPos((int) (450.0/700.0 * FishingGameView.getHeight()));
			timeSinceCatch = -1;
		}
		hook.setHitbox(hook.getXPos(), hook.getYPos());

		for (i = 0; i < fishes.size(); i++) {
			f = fishes.get(i);
			if (!f.getHooked())
				checkBounds(f);
			f.setMouth(f.getXPos(), f.getYPos(), f.getDirection());
		}
		for(i = 0; i < trashAL.size(); i++){
			t = trashAL.get(i);
			if(!t.getHooked())
				t.move();
			t.setHitbox(t.getXPos(), t.getYPos());
		}

	}
/**
 * Reels in the hook and whatever Mover is caught
 * @param m a Mover that is caught on the hook
 */
	public void reelItIn(Mover m) {
		int xDiff = (FishingGameView.ROD_X - hook.getXPos()) / 7;
		int yDiff = (FishingGameView.ROD_Y - hook.getYPos()) / 7;
		m.setXPos(m.getXPos() + xDiff);
		m.setYPos(m.getYPos() + yDiff);
		hook.setXPos(hook.getXPos() + xDiff);
		hook.setYPos(hook.getYPos() + yDiff);
		if(m instanceof Fish)
		{
			Fish f = (Fish) m;
			if(Math.abs(hook.getXPos() - FishingGameView.ROD_X) < 7 && Math.abs(hook.getYPos() - FishingGameView.ROD_Y) < 7){
				fishes.remove(f);
				timeSinceCatch = 0;
			}
		}else{
			Trash t = (Trash) m;
			if(Math.abs(hook.getXPos() - FishingGameView.ROD_X) < 7 && Math.abs(hook.getYPos() - FishingGameView.ROD_Y) < 7){
				trashAL.remove(t);
				timeSinceCatch = 0;
			}
		}
	}

	/**
	 * updates movement and keeps fish within frame bounds
	 * 
	 * @param f
	 *            a Fish object to update
	 *
	 */
	public void checkBounds(Fish f) {
		if (caught instanceof Fish && f.getSpecies() == ((Fish) caught).getSpecies()) {
			//System.out.println((f.getDirection().ordinal()*2 - 4) * 1);
			switch(f.getDirection()){
			case EAST:
				f.setXPos(f.getXPos() + (int)(5*f.getXSpeed()));
				break;
			case WEST:
				default:
				f.setXPos(f.getXPos() - (int)(5*f.getXSpeed()));
				break;
			}
			if (f.getXPos() < (-1 * FishingGameView.FISH_WIDTH)
					|| f.getXPos() > (FishingGameView.FISH_WIDTH + FishingGameView.WIDTH)) {
				fishes.remove(f);
			}
		} else {
			switch (f.getDirection()) {
			/*case NORTH:
				if (f.getYPos() - f.getYSpeed() < 300)
					f.setDirection(Direction.SOUTH);
				else {
					f.setYPos(f.getYPos() - f.getYSpeed());
					f.setMouth(f.getXPos(), f.getYPos(), f.getDirection());
				}
				break;*/
			case EAST:
				if (f.getXPos() + f.getXSpeed() > FishingGameView.getWidth() - 50)
					f.setDirection(Direction.WEST);
				else {
					f.setXPos(f.getXPos() + f.getXSpeed());
					f.setMouth(f.getXPos(), f.getYPos(), f.getDirection());
				}
				break;
			case WEST:
				default:
				if (f.getXPos() - f.getXSpeed() < -50)
					f.setDirection(Direction.EAST);
				else {
					f.setXPos(f.getXPos() - f.getXSpeed());
					f.setMouth(f.getXPos(), f.getYPos(), f.getDirection());
				}
				break;
			/*case SOUTH:
				if (f.getYPos() + f.getYSpeed() > FishingGameView.getHeight() - 100)
					f.setDirection(Direction.NORTH);
				else {
					f.setYPos(f.getYPos() + f.getYSpeed());
					f.setMouth(f.getXPos(), f.getYPos(), f.getDirection());
				}
				break;
			default:
				break;*/
			}
		}
	}

	/**
	 * Updates hook position and keeps it within frame bounds
	 */
	public void checkBounds() {
		if (hook.getYPos() + hook.getYSpeed() < WATER_TOP
				|| hook.getYPos() + hook.getYSpeed() + FishingGameView.HOOK_HEIGHT > WATER_BOTTOM) {
			// don't change y
		} else {
			hook.setYPos(hook.getYPos() + hook.getYSpeed());
			hook.setHitbox(hook.getXPos(), hook.getYPos());
		}
		if (hook.getXPos() + hook.getXSpeed() < 0
				|| hook.getXPos() + hook.getXSpeed() + FishingGameView.HOOK_WIDTH > FishingGameView.getWidth()) {
			// don't change x
		} else {
			hook.setXPos(hook.getXPos() + hook.getXSpeed());
			hook.setHitbox(hook.getXPos(), hook.getYPos());
		}
	}

	/**
	 * checks the Trash with the hook
	 * @param t the Trash
	 * @return a boolean, if the Trash is hooked
	 */
	public boolean trashCaught(Trash t) {
		if (t.getHitbox().intersects(hook.getHitbox())) {
			//System.out.println("The " + f.getSpecies().getName() + " is HOOKED");
			t.setHooked(true);
			return true;
		} else {
			t.setHooked(false);
		}
		return false;
	}
	
	/**
	 * checks the Fish with the hook
	 * @param f the Fish
	 * @return a boolean, if the fish is hooked
	 */
	public boolean fishCaught(Fish f) {
		if (f.getMouth().intersects(hook.getHitbox())) {
			System.out.println("The " + f.getSpecies().getName() + " is HOOKED");
			f.setHooked(true);
			return true;
		} else {
			f.setHooked(false);
		}
		return false;
	}
}
