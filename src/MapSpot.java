import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Part of the grid shown on the main screen.
 * 
 * @author Riley
 *
 */
public class MapSpot implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5749687621654165794L;
	JButton button;
	Building b;
	ImageIcon background;
	Icon showImage;
	TerrainState tState;

	/**
	 * Returns the image that is currently being displayed
	 * 
	 * @return An Icon, the showImage
	 */
	public Icon getShowImage() {
		return showImage;
	}

	/**
	 * Sets the image to display
	 * 
	 * @param showImage
	 *            An Icon, the image to display
	 */
	public void setShowImage(Icon showImage) {
		this.showImage = showImage;
		button.setIcon(showImage);
	}

	/**
	 * Returns the button associated with the map location
	 * 
	 * @return button A JButton, the button at the map location
	 */
	public JButton getButton() {
		return button;
	}

	/**
	 * sets the button at the map location
	 * 
	 * @param button
	 *            the JButton to set
	 */
	public void setButton(JButton button) {
		this.button = button;
	}

	/**
	 * returns the building at the map location
	 * 
	 * @return B A Building, the building at the map location
	 */
	public Building getB() {
		return b;
	}

	/**
	 * sets the building at the map location
	 * 
	 * @param b
	 *            the Building to set
	 */
	public void setB(Building b) {
		this.b = b;
	}

	/**
	 * returns the background at the map location
	 * 
	 * @return background An ImageIcon, the background image at that location
	 */
	public ImageIcon getBackground() {
		return background;
	}

	/**
	 * sets the background at the map location
	 * 
	 * @param background
	 *            the Image to set as the background
	 */
	public void setBackground(ImageIcon background) {
		this.background = background;
	}

	/**
	 * returns the state of the map location for building
	 * 
	 * @return tState A TerrainState, the state of the map location
	 */
	public TerrainState gettState() {
		return tState;
	}

	/**
	 * sets the terrain state at this map location
	 * 
	 * @param tState
	 *            the TerrainState for this map location
	 */
	public void settState(TerrainState tState) {
		this.tState = tState;
	}

	/**
	 * Creates a new instance of MapSpot. Contains all information about each
	 * cell of the grid on the main screen.
	 * 
	 * @param button
	 *            The JButton at that spot on the map
	 * @param ts
	 *            The TerrainState of that spot on the map
	 * @param img
	 *            The background Image of that spot on the map
	 */
	public MapSpot(JButton button, TerrainState ts, ImageIcon img) {
		this.button = button;
		this.tState = ts;
		this.background = img;
		this.showImage = img;
		button.setIcon(showImage);
	}

	/**
	 * Creates a new instance of MapSpot. Contains all information about each
	 * cell of the gird on the main screen.
	 */
	public MapSpot() {
		this.tState = TerrainState.NORMAL;
	}

	/**
	 * Checks if a Building is able to be placed on this MapSpot.
	 * 
	 * @param structure
	 *            The Building being tested
	 * @return If the Building can be placed on this MapSpot
	 */
	public boolean isValid(Building structure) {
		if (structure.getName() == BuildingName.FACTORY) {
			return ((tState == TerrainState.RIVER || tState == TerrainState.NORMAL || tState == TerrainState.FOREST
					|| tState == TerrainState.BEACH) && b == null);
		} else if (structure.getName() == BuildingName.BIRD) {
			return ((tState == TerrainState.RIVER || tState == TerrainState.NORMAL || tState == TerrainState.FOREST
					|| tState == TerrainState.BEACH) && b == null);
		} else if (structure.getName() == BuildingName.FISH) {
			return (tState == TerrainState.BEACH && b == null);
		} else if (structure.getName() == BuildingName.RESEARCH) {
			return ((tState == TerrainState.RIVER || tState == TerrainState.NORMAL || tState == TerrainState.FOREST
					|| tState == TerrainState.BEACH) && b == null);
		} else if (structure.getName() == BuildingName.PORT) {
			return ((tState == TerrainState.BEACH || tState == TerrainState.OCEAN) && b == null);
		} else {
			return b == null;
		}
	}

	/**
	 * Overrides Object's equal method, sees if two MapSpots are equal. Based on
	 * the Building or TerrainState at the MapSpot.
	 * 
	 * @param obj
	 *            The Object to check equality against
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		MapSpot other = (MapSpot) obj;

		if (this.getB() == null && other.getB() == null)
			return other.gettState().equals(this.tState);
		else if (this.getB() == null || other.getB() == null)
			return false;
		else
			return other.getB().equals(this.b) && other.gettState().equals(this.tState);
	}
}
