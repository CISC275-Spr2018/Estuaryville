/**
 * This abstract class is a template for all the mini game Models. It has the
 * basics - a width and height.
 * 
 * @author Joel
 */
public abstract class Model {
	final int scrWidth;
	final int scrHeight;

	/**
	 * Constructs a model that sets screen Width and Height to w and h
	 * respectively.
	 * 
	 * @param w
	 * @param h
	 */
	public Model(int w, int h) {
		this.scrWidth = w;
		this.scrHeight = h;
	}

	/**
	 * This will be called every frame to update the model
	 */
	public abstract void update();
}
