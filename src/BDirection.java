
/** 
 * This enum is a description of which direction a bird object is facing
 * and therefore, where it should move.
 */

public enum BDirection {
	EAST("_east"),
	NORTHEAST("_northeast"),
	SOUTHEAST("_southeast"),
	WEST("_west"),
	NORTHWEST("_northwest"),
	SOUTHWEST("_southwest");
	
	private String name = null;
	
	private BDirection(String s){
		name = s;
	}
	public String getName() {
		return name;
	}


}
