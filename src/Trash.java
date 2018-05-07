import java.awt.Rectangle;

/**
 * This class represents all the Trash objects in the model 
 */
public class Trash extends Mover{
	Rectangle hitbox;
	boolean hooked;
	int hookedFor;
	Type trashType;
	int originY;
	
	/**
	 * Holds all the types of trash in the fishing game
	 * @author Joel
	 *
	 */
	public enum Type{
		CAN("can"),
		PLASTIC("plastic"),
		STRAW("straw");
		private String path = null;
		
		
		/**
		 * Creates a new Type with a name
		 * @param s a String which is a partial directory of the Type (for loading images)
		 */
		private Type(String s){
			path = s;
		}
		
		/**
		 * returns the name of a Type
		 * @return a String, the name
		 */
		public String getPath() {
			return path;
		}
	}
	/**
	 * Creates a new piece of Trash with random off-screen starting x and y position and direction
	 * @param t a Type, a straw, can, or plastic
	 */
	public Trash(Type t){
		if(Math.random() > 0.5){
			super.setXPos(-25-FishingGameView.TRASH_WIDTH);
			super.setDirection(Direction.EAST);
		}else{
			super.setXPos(FishingGameView.getWidth() + 25);
			super.setDirection(Direction.WEST);
		}
		originY = (int) (Math.random()*(FishingGameModel.WATER_BOTTOM - FishingGameModel.WATER_TOP - FishingGameView.TRASH_HEIGHT - 50) + FishingGameModel.WATER_TOP + 50);
		super.setYPos(originY);
		super.setXSpeed(12);
		trashType = t;
		hooked = false;
		hookedFor = 0;
		hitbox = new Rectangle(this.getXPos(), this.getYPos(), FishingGameView.TRASH_WIDTH, FishingGameView.TRASH_HEIGHT);
	}
	/**
	 * Returns the type of this trash
	 * @return a Type of Trash
	 */
	public Type getType(){return this.trashType;}
	
	/**
	 * Returns this Trash's hit box
	 * @return a Rectangle, the box
	 */
	public Rectangle getHitbox(){return this.hitbox;}
	
	/**
	 * Sets this Trash's hit box to the given location
	 * @param x the new x location
	 * @param y the new y location
	 */
	public void setHitbox(int x, int y){this.hitbox.setLocation(x, y);}
	
	/**
	 * Sets the Trash hooked attribute to true or false
	 * @param b a boolean representing if the trash is hooked
	 */
	public void setHooked(boolean b) {hooked = b;}
	/**
	 * Tells whether this Trash is hooked or not
	 * @return a Boolean, isHooked
	 */
	public boolean getHooked(){return hooked;}
	
	/**
	 * updates 1 tick of movement for a piece of trash
	 */
	public void move(){
		switch(this.direction){
		case EAST:
			this.setYPos(originY - (int)(30 * Math.cos(this.getXPos())));
			this.setXPos(this.getXPos() + this.getXSpeed());
			if(this.getXPos() > FishingGameView.WIDTH + 25)
				this.setXPos(-25-FishingGameView.TRASH_WIDTH);
			this.setHitbox(this.getXPos(), this.getYPos());
		break;
		case WEST:
		default:
			this.setYPos(originY - (int)(30 * Math.cos(this.getXPos())));
			this.setXPos(this.getXPos() - this.getXSpeed());
			if(this.getXPos() < -25-FishingGameView.TRASH_WIDTH)
				this.setXPos(FishingGameView.WIDTH + 25);
			this.setHitbox(this.getXPos(), this.getYPos());
		break;
		}
	}
}
