
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is a model for the Bird Watching Game that contains all objects in the game.
 * This Model is responsible for updating the movement of the objects, collision detection, 
 * and updating other attributes of objects within the model.  
 * @author Tyler
 * @see #Bird
 */
public class BirdWatchingGameModel extends Model {
	
	static Camera camera;
	ArrayList<Bird> birds;
	Bird searchingFor;
	int screenWidth;
	int screenHeight;
	static int takePicFrame = -1;
	static int numBirdsNotFound;
	boolean gameOver = false;
	boolean wrongBird = false;
	boolean tryAgain = false;
	Bird toDisplayInfo = null;
	
	/**
	 * Creates an instance of the BirdWatchingGameModel class.
	 * This constructor creates an array to hold each of the three birds of the Bird class.
	 * The constructor also chooses a random bird to assign to the variable
	 * searchingFor and creates an instance of a Camera.  
	 * @param w the width of the screen
	 * @param h the height of the screen
	 */
	public BirdWatchingGameModel(int w, int h) {
		super(w, h);
		this.screenWidth = w;
		this.screenHeight = h;
		birds = new ArrayList<Bird>();
		for (int i = 0; i < Bird.Species.values().length; i++) {
			birds.add(new Bird(Bird.Species.values()[i], w, h));
		}
		numBirdsNotFound = birds.size();
		camera = new Camera(w, h);
		Random rand = new Random();
		searchingFor = birds.get(rand.nextInt(3));
	}
	
	/**
	 * Updates the movement of the objects in the model by detecting collisions. 
	 */
	@Override
	public void update() {
		Bird b;
		if(takePicFrame > 0 && takePicFrame < 2)
			takePicFrame ++;
		else if(takePicFrame >= 2)
			takePicFrame = -1;
		for (int i = 0; i < birds.size(); i++) {
			b = birds.get(i);
			checkBounds(b);
			b.getRect().setLocation(b.getXPos(), b.getYPos());
		}
		checkBounds(camera);
		camera.rect.setLocation(camera.getXPos(), camera.getYPos());
	}
	/**
	 * Checks to see if a given bird is within the bounds of the screen depending on the 
	 * direction that the bird is moving.
	 * @param b The bird to check the position of
	 */
	public void checkBounds(Bird b){
		Random rand = new Random();
		if (b.getSpecies() == Bird.Species.SANDPIPER) {
			switch(b.getDirection()) {
			case EAST:
				if(b.getXPos() + b.getXSpeed() > getScaledWidth(930)) {
					b.setDirection(BDirection.values()[3 + rand.nextInt(3)]);
				}
				else
					b.setXPos(b.getXPos() + b.getXSpeed());
				break;
			case WEST:
				if(b.getXPos() + b.getXSpeed() < getScaledWidth(775)) {
					b.setDirection(BDirection.values()[rand.nextInt(3)]);
				}
				else
					b.setXPos(b.getXPos() - b.getXSpeed());
				break;
			case NORTHEAST:
				if(b.getYPos() - b.getYSpeed() < getScaledHeight(430)) {
					b.setDirection(BDirection.values()[2 + rand.nextInt(2)]);
				}
				else
					b.setYPos(b.getYPos() - b.getYSpeed());
				if(b.getXPos() + b.getXSpeed() > getScaledWidth(930)) {
					b.setDirection(BDirection.values()[3 + rand.nextInt(3)]);
				}
				else
					b.setXPos(b.getXPos() + b.getXSpeed());
				break;
			case NORTHWEST:
				if(b.getYPos() - b.getYSpeed() < getScaledHeight(430)) {
					b.setDirection(BDirection.values()[2 + rand.nextInt(2)]);
				}
				else
					b.setYPos(b.getYPos() - b.getYSpeed());
				if(b.getXPos() + b.getXSpeed() < getScaledWidth(775)) {
					b.setDirection(BDirection.values()[rand.nextInt(3)]);
				}
				else
					b.setXPos(b.getXPos() - b.getXSpeed());
				break;
			case SOUTHEAST:
				if(b.getYPos() - b.getYSpeed() > getScaledHeight(570)) {
					b.setDirection(BDirection.values()[3 + rand.nextInt(2)]);
				}
				else
					b.setYPos(b.getYPos() + b.getYSpeed());
				if(b.getXPos() + b.getXSpeed() > getScaledWidth(930)) {
					b.setDirection(BDirection.values()[3 + rand.nextInt(3)]);
				}
				else
					b.setXPos(b.getXPos() + b.getXSpeed());
				break;
			case SOUTHWEST:
			default:
				if(b.getYPos() - b.getYSpeed() > getScaledHeight(570)) {
					b.setDirection(BDirection.values()[3 + rand.nextInt(2)]);
				}
				else
					b.setYPos(b.getYPos() + b.getYSpeed());
				if(b.getXPos() + b.getXSpeed() < getScaledWidth(775)) {
					b.setDirection(BDirection.values()[rand.nextInt(3)]);
				}
				else
					b.setXPos(b.getXPos() - b.getXSpeed());
				break;
			}
		}
		else {
			switch(b.getDirection()){
			case EAST:
				if(b.getXPos() + b.getXSpeed() > screenWidth - 50)
					b.setXPos(0);
				else
					b.setXPos(b.getXPos() + b.getXSpeed());
				break;
			case WEST:
				if(b.getXPos() - b.getXSpeed() < -50)
					b.setXPos(screenWidth - 50);
				else
					b.setXPos(b.getXPos() - b.getXSpeed());
				break;
			case NORTHEAST:
				if(b.getYPos() - b.getYSpeed() < - 50)
					b.setYPos(screenHeight + 50);
				else
					b.setYPos(b.getYPos() - b.getYSpeed());
				if(b.getXPos() + b.getXSpeed() > screenWidth - 50)
					b.setXPos(0);
				else
					b.setXPos(b.getXPos() + b.getXSpeed());
				break;
			case NORTHWEST:
				if(b.getYPos() - b.getYSpeed() < - 50)
					b.setYPos(screenHeight + 50);
				else
					b.setYPos(b.getYPos() - b.getYSpeed());
				if(b.getXPos() - b.getXSpeed() < -50)
					b.setXPos(screenWidth - 50);
				else
					b.setXPos(b.getXPos() - b.getXSpeed());
				break;
			case SOUTHEAST:
				if(b.getYPos() + b.getYSpeed() > screenHeight - 50)
					b.setYPos(0);
				else
					b.setYPos(b.getYPos() + b.getYSpeed());
				if(b.getXPos() + b.getXSpeed() > screenWidth - 50)
					b.setXPos(0);
				else
					b.setXPos(b.getXPos() + b.getXSpeed());
				break;
			case SOUTHWEST:
			default:
				if(b.getYPos() + b.getYSpeed() > screenHeight - 50)
					b.setYPos(0);
				else
					b.setYPos(b.getYPos() + b.getYSpeed());
				if(b.getXPos() - b.getXSpeed() < -50)
					b.setXPos(screenWidth - 50);
				else
					b.setXPos(b.getXPos() - b.getXSpeed());
				break;
			}
		}
	}
	
	/**
	 * Checks to see if a given Camera is within the bounds of the screen
	 * @param c the Camera to check the position of
	 */
	public void checkBounds(Camera c){
		if(c.getYPos() + c.getYSpeed() < -60 || c.getYPos() + c.getYSpeed() > screenHeight - getScaledHeight(330)){
			//don't change y
		}
		else{
			c.setYPos(c.getYPos() + c.getYSpeed());
		}
		if(c.getXPos() + c.getXSpeed() < 0 || c.getXPos() + c.getXSpeed() > screenWidth - getScaledWidth(340)) {
			//don't change x
		}
		else{
			c.setXPos(c.getXPos() + c.getXSpeed());
		}
	}
	
	/**
	 * Takes a picture of the screen. Tells whether the player has taken a 
	 * picture of the bird they are looking for, the incorrect bird, or just
	 * the background.
	 * @param target The Bird that the player is searching for.
	 * @param birdList The list of birds within the model.
	 */
	public void takePicture(Bird target, ArrayList<Bird> birdList) {
		if (!birds.isEmpty()) {
			takePicFrame = 1;
			if (isOnTarget(target)) {
				toDisplayInfo = target;
				birds.remove(birds.indexOf(target)); //remove target bird
				if (birds.isEmpty()) {
					gameOver = true;
				}
				else {
					Random rand = new Random();
					numBirdsNotFound--;
					searchingFor = birds.get(rand.nextInt(numBirdsNotFound)); //change target bird			
				}
			}
			//else if not on target
			else {
				for (Bird b : birdList) {
					if (isOnTarget(b)) {
						//display wrong bird
						wrongBird = true;
						return;
					}
				}
				//display look again
				tryAgain = true;
			}
		}
	}
	
	/**
	 * Checks whether the bird hitbox is within the camera hitbox.
	 * @param b The bird to use as a target
	 * @return A boolean to tell if the camera is over the target bird.
	 */
	public static boolean isOnTarget(Bird b) {
		//collision detection
		return camera.getRect().contains(b.getRect());
	}
	
	/**
	 * Tells whether to display a camera flash on the screen based on the value 
	 * of takePicFrame.
	 * @return True to display camera flash, false to not display flash. 
	 */
	public static boolean isFlash() {
		return takePicFrame > 0;
	}
	
	/**
	 * Gets the scaled width to use for an object. 
	 * @param n The number to use within the scale.
	 * @return An int to use as the scaled width metric.
	 */
	public int getScaledWidth(int n) {
		double number = (double)n;
		double position = (number / 1440) * screenWidth;
		return (int) position;
	}
	
	/**
	 * Gets the scaled height to use for an object. 
	 * @param n The number to use within the scale.
	 * @return An int to use as the scaled height metric.
	 */
	public int getScaledHeight(int n) {
		double number = (double)n;
		double position = (number / 900) * screenHeight;
		return (int) position;
	}
	
	/**
	 * Gets the gameOver variable of the View to tell if the game has ended.
	 * @return A boolean to tell if the game has ended. 
	 */
	public boolean getGameOver() {
		return gameOver;
	}
	
	/**
	 * Sets the gameOver variable of the View, which tells if the game has ended.
	 * @param g The boolean to set the gameOver variable to.
	 */
	public void setGameOver(boolean g) {
		gameOver = g;
	}
	
	/**
	 * Gets the wrongBird variable of the View to tell if the player has taken
	 * a picture of the wrong bird.
	 * @return A boolean to tell if a picture of a wrong bird has been taken. 
	 */
	public boolean getWrongBird() {
		return wrongBird;
	}
	
	/**
	 * Sets the wrongBird variable of the View, which tells if the player has taken
	 * a picture of the wrong bird.
	 * @param g The boolean to set the wrongBird variable to.
	 */
	public void setWrongBird(boolean w) {
		wrongBird = w;
	}
	
	/**
	 * Gets the tryAgain variable of the View to tell if the player has taken
	 * a picture of the background.
	 * @return A boolean to tell if a picture of the background has been taken. 
	 */
	public boolean getTryAgain() {
		return tryAgain;
	}
	
	/**
	 * Sets the tryAgain variable of the View, which tells if the player has taken
	 * a picture of the background.
	 * @param g The boolean to set the tryAgain variable to.
	 */
	public void setTryAgain(boolean t) {
		tryAgain = t;
	}
	
	/**
	 * Gets the searchingFor variable of the View to tell what Bird the player
	 * is searching for. 
	 * @return A boolean to tell what bird the player is searching for. 
	 */
	public Bird getSearchingFor() {
		return searchingFor;
	}
	
	/**
	 * Sets the searchingFor variable of the View, which tells what Bird the 
	 * player is searching for.
	 * @param g The Bird to set the searchingFor variable to.
	 */
	public void setSearchingFor(Bird b) {
		searchingFor = b;
	}
	
	/**
	 * Gets the toDisplayInfo variable of the View to tell what Bird to display
	 * the information screen for. 
	 * @return A boolean to tell what bird to display the information screen for. 
	 */
	public Bird getToDisplayInfo() {
		return toDisplayInfo;
	}
	
	/**
	 * Sets the toDisplayInfo variable of the View, which tells what Bird to display
	 * the information screen of. 
	 * @param g The Bird to set the toDisplayInfo variable to.
	 */
	public void setToDisplayInfo(Bird b) {
		toDisplayInfo = b;
	}
	
	/**
	 * Gets the array of birds within the BirdWatchingGameModel
	 * @return the array of Bird objects
	 */
	public ArrayList<Bird> getBirds(){
		return birds;
	}
	/**
	 * Gets the camera within the BirdWatchingGameModel
	 * @return the camera within the BirdWatchingGameModel
	 */
	public Camera getCamera(){
		return camera;
	}
}
