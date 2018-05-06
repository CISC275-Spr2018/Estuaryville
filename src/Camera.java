import java.awt.Rectangle;

/**
 * This is a class used to represent a camera in the Bird Watching Game.
 * @author Tyler
 *
 */
public class Camera {
	private int cXPos;
	private int cYPos;
	private int cXIncr;
	private int cYIncr;
	Rectangle rect;
	int screenWidth, screenHeight;
	int cWidth, cHeight;
	
	/**
	 * Creates an instance of the Camera class. This constructor assigns the x position
	 * and y position attributes of the Camera to place the camera at the center of 
	 * the screen.
	 */
	public Camera(int w, int h){
		screenWidth = w;
		screenHeight = h;
		cXIncr = 0;
		cYIncr = 0;
		cWidth = getScaledWidth(320);
		cHeight = getScaledHeight(320);
		cXPos = (screenWidth / 2) - (cWidth / 2); //camera will not be on screen for frame 1
		cYPos = (screenHeight / 2) - (cHeight / 2); //camera will not be on screen for frame 1
		rect = new Rectangle(cXPos, cYPos, cWidth, cHeight);
	}
		
	/**
	 * Gets the x position of the Camera.
	 * @return The x position of the Camera.
	 */
	public int getXPos(){return cXPos;}
	
	/**
	 * Sets the x position attribute of the Camera.
	 * @param x The position to assign to the x position attribute of the Camera.
	 */
	public void setXPos(int x){cXPos = x;}
	
	/**
	 * Gets the x position of the Camera.
	 * @return The x position of the Camera.
	 */
	public int getYPos(){return cYPos;}
	
	/**
	 * Sets the y position attribute of the Camera.
	 * @param y The position to assign to the y position attribute of the Camera.
	 */
	public void setYPos(int y){cYPos = y;}
	
	/**
	 * Gets the x speed of the Camera.
	 * @return The current x speed of the Camera.
	 */
	public int getXSpeed(){return cXIncr;}
	
	/**
	 * Sets the x speed attribute of the Camera.
	 * @param x The speed to assign to the x speed attribute of the Camera.
	 */
	public void setXSpeed(int x){cXIncr = x;}
	
	/**
	 * Gets the y speed of the Camera.
	 * @return The current y speed of the Camera.
	 */
	public int getYSpeed(){return cYIncr;}
	
	/**
	 * Sets the y speed attribute of the Camera.
	 * @param y The speed to assign to the y speed attribute of the Camera.
	 */
	public void setYSpeed(int y){cYIncr = y;}
	
	public Rectangle getRect() {return rect;}
	public void setRect(Rectangle r) {rect = r;}
	
	public int getCWidth() {return cWidth;}
	public void setCWidth(int w) {cWidth = w;}

	public int getCHeight() {return cWidth;}
	public void setCHeight(int h) {cHeight = h;}
	
	public int getScaledWidth(int n) {
		double number = (double)n;
		double position = (number / 1440) * screenWidth;
		return (int) position;
	}
	
	public int getScaledHeight(int n) {
		double number = (double)n;
		double position = (number / 900) * screenHeight;
		return (int) position;
	}

}
