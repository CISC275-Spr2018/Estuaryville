import java.util.HashMap;
/**
 * The model of the main screen. Contains all information about the state of the main screen.
 * @author Riley
 *
 */
public class MainModel {
	public final int POLLUTION_MAX = 10000;
	public final int MONEY_MAX = 1000;
	private int pollution = 1;
	private int money = 100;
	private final int MAP_HEIGHT = 10;
	private final int MAP_WIDTH = 10;
	private MapSpot[][] map = new MapSpot[MAP_HEIGHT][MAP_WIDTH];
	private HashMap<String,Building> buildingTypes;
	private int pollIncr = 5;
	private int moneyIncr = 1;
	private BuildState build = BuildState.NONE;
	private boolean[] placedBuildings = new boolean[BuildState.values().length];

	/**
	 * Returns a Map with the keys being the Building's name and the Value being the Building.
	 * @return a Map with the keys being the Building's name and the Value being the Building.
	 */
	public HashMap<String,Building> getBuildingTypes() {
		return buildingTypes;
	}
	/**
	 * Returns the pollution incrementing value.
	 * @return the pollution incrementing value.
	 */
	public int getPollIncr() {
		return pollIncr;
	}
	/**
	 * Set the pollution incrementing value.
	 * @param pollIncr the value to be set.
	 */
	public void setPollIncr(int pollIncr) {
		this.pollIncr = pollIncr;
	}
	/**
	 * Returns the money incrementing value.
	 * @return the money incrementing value.
	 */
	public int getMoneyIncr() {
		return moneyIncr;
	}
	/**
	 * Set the money incrementing value.
	 * @param moneyIncr the value to be set. 
	 */
	public void setMoneyIncr(int moneyIncr) {
		this.moneyIncr = moneyIncr;
	}
	/**
	 * Returns the current BuildState.
	 * @return the current BuildState.
	 */
	public BuildState getBuild() {
		return build;
	}
	/**
	 * Sets the BuildState.
	 * @param build the value to be set.
	 */
	public void setBuild(BuildState build) {
		this.build = build;
	}
	/**
	 * Returns the current pollution level.
	 * @return the current pollution level.
	 */
	public int getPollution() {
		return pollution;
	}
	/**
	 * Sets the current pollution level.
	 * @param pollution the value to be set.
	 */
	public void setPollution(int pollution) {
		this.pollution = pollution;
	}
	/**
	 * Returns the current money.
	 * @return the current money.
	 */
	public int getMoney() {
		return money;
	}
	/**
	 * Sets the money amount.
	 * @param money the value to be set.
	 */
	public void setMoney(int money) {
		this.money = money;
	}
	/**
	 * Returns the grid of MapSpots on main screen
	 * @return the grid of MapSpots on main screen
	 */
	public MapSpot[][] getMap() {
		return map;
	}
	/**
	 * Creates a new instance of MainModel
	 * @param map the grid of MapSpots which forms the board displayed.
	 */
	public MainModel(MapSpot[][] map) {
		this.map = map;
		buildingTypes = loadBuildingTypes();
	}
	/**
	 * Creates the different Buildings to use through game.
	 * @return the different Buildings to use through game.
	 */
	public HashMap<String,Building> loadBuildingTypes() {
		HashMap<String,Building> types = new HashMap<String,Building>();
		types.put("Bird",new Building(150,8000,"Bird Watching Tower","bird-tower"));
		types.put("Research",new Building(400,90000,"Research Center","research"));
		types.put("Fish",new Building(250,10000,"Fishing Pier","pier"));
		types.put("Factory",new Building(100,10000,"Factory","factory"));
		types.put("Port",new Building(50,10000,"Port","port"));
		return types;
	}
	/**
	 * Builds a Building at a MapSpot if it is valid.
	 * @param structure The Building to build.
	 * @param xPos The x location to build.
	 * @param yPos The y location to build. 
	 * @return
	 */
	public Active build(Building structure, int xPos, int yPos) {
		if(!placedBuildings[build.ordinal()] && isValidPlacement(xPos, yPos, structure) && isConstructable(structure)) {
			placedBuildings[build.ordinal()] = true;
			map[xPos][yPos].setB(structure);
			switch(build) {
			case FISH:
				return Active.FISH;
			case BIRD:
				return Active.BIRD;
			case RESEARCH:
				pollIncr -= 5;
				moneyIncr -= 1;
				return Active.RESEARCH;
			case FACTORY:
				pollIncr += 10;
				moneyIncr += 1;
				break;
			case PORT:
				pollIncr += 5;
				money += 50;
				break;
			default:
				return Active.MAIN;
			}
		}
		build = BuildState.NONE;
		return Active.MAIN;
	}
	/**
	 * Removes a building.
	 * @param xPos The x position to remove building from.
	 * @param yPos The y position to remove building from.
	 */
	public void removeBuilding(int xPos, int yPos) {
		map[xPos][yPos].setB(null);
		build = BuildState.NONE;
	}
	/**
	 * Checks to see if a building can be built with current money and pollution levels.
	 * @param structure The building to be built
	 * @return if the building can be built
	 */
	public boolean isConstructable(Building structure) {
		if ((structure.getCost() <= money) && (structure.getQualityNeeded() >= pollution)){
			money -= structure.getCost();
			return true;
		}
		return false;
	}
	/**
	 * Checks if a building can be built at a MapSpot based on terrain.
	 * @param xPos The x position to build building.
	 * @param yPos The x position to build building.
	 * @param structure The building to be built.
	 * @return if the building can be built at that MapSpot.
	 */
	public boolean isValidPlacement(int xPos, int yPos, Building structure) {
		return map[yPos][xPos].isValid(structure);
	}
	/**
	 * Checks if the game is over.
	 * @return if the game is over.
	 */
	public boolean gameOver() {
		return this.pollution == POLLUTION_MAX || this.money == MONEY_MAX;
	}
	/**
	 * Updates the state of the main model by changing pollution and money.
	 */
	public void update() {
		if(!gameOver()) {
			if(pollution >= 0)
				pollution += pollIncr;
			money += moneyIncr;
		}
	}
}