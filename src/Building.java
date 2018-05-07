import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Buildings that can be built on the main screen of the game.
 * @author Riley
 *
 */
public class Building {
	private int cost;
	private BufferedImage image;
	private int qualityNeeded;
	private String name;
	private String filename;
	/**
	 * Creates an instance of a Building.
	 * @param cost The cost of the building.
	 * @param quality The maximum pollution level allowed to build the building.
	 * @param name The name of the building.
	 * @param filename The name of the image file for the building.
	 */
	public Building(int cost, int quality, String name, String filename) {
		this.cost = cost;
		this.qualityNeeded = quality;
		this.name = name;
		this.filename = filename;
		this.image = loadImage();
	}
	/**
	 * Loads the images for the Building based off of the file name.
	 * @return A BufferedImage of the building's image.
	 */
	public BufferedImage loadImage() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("assets/main-screen/buildings/"+this.filename+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	/**
	 * Returns the name of the building.
	 * @return The name of the building.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns the cost of the building.
	 * @return The cost of the building.
	 */
	public int getCost() {
		return cost;
	}
	/**
	 * Returns the image of the building.
	 * @return The image of the building.
	 */
	public BufferedImage getImage() {
		return image;
	}
	/**
	 * Sets the image of the building.
	 * @param image The image to be set.
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	/**
	 * Returns the minimum pollution level to place the building.
	 * @return the minimum pollution level to place the building.
	 */
	public int getQualityNeeded() {
		return qualityNeeded;
	}
	/**
	 * Overrides Object's equal method, sees if two Buildings are equal. Based on the name.
	 * @param obj The Object to check equality against
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		Building other = (Building) obj;
		return name.equals(other.name);
	}
}
