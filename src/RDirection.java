/**
 * RDirection Enums, used in the Researcher Game
 * 
 * @author mattstack
 *
 */
public enum RDirection {

	IDLE("idle-sheet"), NORTHEAST("forward-northeast-sheet"), EAST("forward-east-sheet"), SOUTHEAST(
			"forward-southeast-sheet");

	private String name = null;

	private RDirection(String s) {
		name = s;
	}

	/**
	 * Returns the Enum name
	 * 
	 * @return Enum name
	 */
	public String getName() {
		return name;
	}

}
