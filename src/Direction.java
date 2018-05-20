/**
 * This enum is a description of which direction an object is facing and
 * therefore, where it should move. Used by fish which only go east or west
 */
public enum Direction {
	EAST("_east"), WEST("_west");

	private String name = null;

	/**
	 * Creates a new Direction with a name
	 * 
	 * @param s
	 *            a String which is a partial directory of the Direction (for
	 *            loading images)
	 */
	private Direction(String s) {
		name = s;
	}

	/**
	 * returns the name of a Direction
	 * 
	 * @return a String, the name
	 */
	public String getName() {
		return name;
	}

}
