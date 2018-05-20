
/**
 * This represents all the moving objects on the screen at a time and holds
 * their attributes of position, speed, and direction.
 * 
 * @author Joel
 *
 */
public class Mover {
	int xPos;
	int yPos;
	int xSpeed;
	int ySpeed;
	Direction direction;

	/* GETTERS & SETTERS */

	/**
	 * Gets the X Position of the Mover
	 * 
	 * @return an int, the number of pixels to the right of the left edge of the
	 *         panel
	 */
	public int getXPos() {
		return xPos;
	}

	/**
	 * Updates the Mover's X position
	 * 
	 * @param xp
	 *            an int, the new X position
	 */
	public void setXPos(int xp) {
		xPos = xp;
	}

	/**
	 * Gets the Y Position of the Mover
	 * 
	 * @return an int, the number of pixels below the top edge of the panel
	 */
	public int getYPos() {
		return yPos;
	}

	/**
	 * Updates the Mover's Y position
	 * 
	 * @param yp
	 *            an int, the new Y position
	 */
	public void setYPos(int yp) {
		yPos = yp;
	}

	/**
	 * Gets the X speed of the Mover
	 * 
	 * @return an int, number of pixels to move to the right when updated
	 */
	public int getXSpeed() {
		return xSpeed;
	}

	/**
	 * Updates the Mover's X speed
	 * 
	 * @param xs
	 *            an int, new X speed
	 */
	public void setXSpeed(int xs) {
		xSpeed = xs;
	}

	/**
	 * Gets the Y speed of the Mover
	 * 
	 * @return an int, number of pixels to move down when updated
	 */
	public int getYSpeed() {
		return ySpeed;
	}

	/**
	 * Updates the Mover's Y speed
	 * 
	 * @param ys
	 *            an int, new Y speed
	 */
	public void setYSpeed(int ys) {
		ySpeed = ys;
	}

	/**
	 * Gets the direction this Mover is moving in
	 * 
	 * @return the current Direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Sets this Mover's Direction
	 * 
	 * @param dir
	 *            the new Direction
	 */
	public void setDirection(Direction dir) {
		direction = dir;
	}

}
