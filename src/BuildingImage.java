import java.awt.Component;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.Icon;
/**
 * An image with a Building's image and a MapSpot's background combined.
 * @author Riley
 *
 */
public class BuildingImage implements Icon, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4092942893403276253L;
	Icon building;
	Icon background;
	/**
	 * Creates a new instance of BuildingImage.
	 * @param building The building image.
	 * @param background The background image.
	 */
	public BuildingImage(Icon building, Icon background) {
		this.building = building;
		this.background = background;
	}
	/**
	 * Returns the height of the Icon.
	 * @return the height of the Icon.
	 */
	@Override
	public int getIconHeight() {
		return Math.max(building.getIconHeight(), background.getIconHeight());
	}
	/**
	 * Returns the width of the Icon.
	 * @return the width of the Icon.
	 */
	@Override
	public int getIconWidth() {
		return Math.max(building.getIconWidth(), background.getIconWidth());
	}
	/**
	 * Paints the new Image
	 * @param c The Component to paint Icon onto.
	 * @param g The Grahic to use to paint Icon.
	 * @param x The x position to paint Icon.
	 * @param y The y position to paint Icon.
	 */
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		building.paintIcon(c, g, x, y);
		background.paintIcon(c, g, x, y);
	}
	
	
}
