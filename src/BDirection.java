
/**
 * This enum is a description of which direction a Bird object is facing and
 * therefore, where it should move.
 */
public enum BDirection {
	EAST("_east"), NORTHEAST("_northeast"), SOUTHEAST("_southeast"), WEST("_west"), NORTHWEST("_northwest"), SOUTHWEST(
			"_southwest");

	private String name = null;

	/**
	 * Creates an instance of the BDirection enumeration. This constructor
	 * assigns a name to the BDirection of the Bird.
	 * 
	 * @param s
	 *            The name to assign to the BDirection of the Bird.
	 */
	private BDirection(String s) {
		name = s;
	}

	/**
	 * Gets the name associated with the BDirection.
	 * 
	 * @return A string, the name associated with the BDirection.
	 */
	public String getName() {
		return name;
	}

}
