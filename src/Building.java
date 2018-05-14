import java.io.Serializable;
/**
 * Buildings that can be built on the main screen of the game.
 * @author Riley
 *
 */
public class Building implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 78168390982917751L;
	private int cost;
	private int qualityNeeded;
	private BuildingName name;
	private String filename;
	/**
	 * Creates an instance of a Building.
	 * @param cost The cost of the building.
	 * @param quality The maximum pollution level allowed to build the building.
	 * @param name The name of the building.
	 * @param filename The name of the image file for the building.
	 */
	public Building(int cost, int quality, BuildingName name, String filename) {
		this.cost = cost;
		this.qualityNeeded = quality;
		this.name = name;
		this.filename = filename;
	}
	public String getFileName() {
		return filename;
	}
	/**
	 * Returns the name of the building.
	 * @return The name of the building.
	 */
	public BuildingName getName() {
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
