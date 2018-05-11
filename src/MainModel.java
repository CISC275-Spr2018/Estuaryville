import java.io.Serializable;
import java.util.HashMap;
/**
 * The model of the main screen. Contains all information about the state of the main screen.
 * @author Riley
 *
 */
public class MainModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4361856093721815033L;
	public final int POLLUTION_MAX = 10000;
	public final int MONEY_MAX = 10000;
	private int pollution = 1;
	private int money = 250;
	private final int MAP_HEIGHT = 10;
	private final int MAP_WIDTH = 10;
	private MapSpot[][] map = new MapSpot[MAP_HEIGHT][MAP_WIDTH];
	private HashMap<BuildingName,Building> buildingTypes;
	private int pollIncr = 5;
	private int moneyIncr = 3;
	private BuildState build = BuildState.NONE;
	private boolean[] placedBuildings = new boolean[BuildState.values().length];
    private int FACTORY_MONEY_INCR  = 10;
    private int FACTORY_POLL_INCR  = 12;
    private int PORT_MONEY_INCR  = 5;
    private int PORT_POLL_INCR  = 5;
    private int RESEARCH_MONEY_INCR  = -2;
    private int RESEARCH_POLL_INCR  = -10;
    
    public void setButtons(MapSpot[][] board) {
    	for(int i = 0;i < board.length; i++) {
    		for(int j = 0; j < board[0].length; j++) {
    			map[i][j].setButton(board[i][j].getButton());
    		}
    	}
    }
	/**
	 * Returns a Map with the keys being the Building's name and the Value being the Building.
	 * @return a Map with the keys being the Building's name and the Value being the Building.
	 */
	public HashMap<BuildingName,Building> getBuildingTypes() {
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
	public HashMap<BuildingName,Building> loadBuildingTypes() {
		HashMap<BuildingName,Building> types = new HashMap<BuildingName,Building>();
		types.put(BuildingName.BIRD,new Building(150,10000,BuildingName.BIRD,"bird-tower"));
		types.put(BuildingName.RESEARCH,new Building(400,10000,BuildingName.RESEARCH,"research"));
		types.put(BuildingName.FISH,new Building(250,10000,BuildingName.FISH,"pier"));
		types.put(BuildingName.FACTORY,new Building(200,10000,BuildingName.FACTORY,"factory"));
		types.put(BuildingName.PORT,new Building(150,10000,BuildingName.PORT,"port"));
		return types;
	}
	/**
	 * Builds a Building at a MapSpot if it is valid.
	 * @param structure The Building to build.
	 * @param xPos The x location to build.
	 * @param yPos The y location to build. 
	 * @return
	 */
	public BuildReturn build(Building structure, int xPos, int yPos) {
		if(!placedBuildings[build.ordinal()] && isValidPlacement(xPos, yPos, structure) && isConstructable(structure) && map[xPos][yPos].getB() == null) {
			placedBuildings[build.ordinal()] = true;
			map[xPos][yPos].setB(structure);
			switch(build) {
			case FISH:
				return new BuildReturn(BuildError.NONE, Active.FISH);
			case BIRD:
				return new BuildReturn(BuildError.NONE,Active.BIRD);
			case RESEARCH:
				pollIncr += RESEARCH_POLL_INCR;
				moneyIncr += RESEARCH_MONEY_INCR;
				return new BuildReturn(BuildError.NONE,Active.RESEARCH);
			case FACTORY:
				pollIncr += FACTORY_POLL_INCR;
				moneyIncr += FACTORY_MONEY_INCR;
				break;
			case PORT:
				pollIncr += PORT_POLL_INCR;
				moneyIncr += PORT_MONEY_INCR;
				break;
			default:
				return new BuildReturn(BuildError.NONE,Active.MAIN);
			}
		}
		else {
			if(placedBuildings[build.ordinal()])
				return new BuildReturn(BuildError.PLACED,Active.MAIN);
			else if(!isValidPlacement(xPos,yPos,structure))
				return new BuildReturn(BuildError.SPOT,Active.MAIN);
			else if(structure.getCost() > money)
				return new BuildReturn(BuildError.COST,Active.MAIN);
			else if(structure.getQualityNeeded() < pollution)
				return new BuildReturn(BuildError.POLLUTION,Active.MAIN);
		}
		build = BuildState.NONE;
		return new BuildReturn(BuildError.NONE,Active.MAIN);
	}
	/**
	 * Removes a building.
	 * @param xPos The x position to remove building from.
	 * @param yPos The y position to remove building from.
	 */
	public void removeBuilding(int xPos, int yPos) {
		if(map[xPos][yPos].getB().getName() == BuildingName.FACTORY) {
			moneyIncr -= FACTORY_MONEY_INCR;
			pollIncr -= FACTORY_POLL_INCR;
			placedBuildings[BuildState.FACTORY.ordinal()] = false;
		}
		else if(map[xPos][yPos].getB().getName() == BuildingName.PORT) {
			moneyIncr -= PORT_MONEY_INCR;
			pollIncr -= PORT_POLL_INCR;
			placedBuildings[BuildState.PORT.ordinal()] = false;
		}
		else if(map[xPos][yPos].getB().getName() == BuildingName.RESEARCH) {
			moneyIncr -= RESEARCH_MONEY_INCR;
			pollIncr -= RESEARCH_POLL_INCR;
		}
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
		return this.pollution+pollIncr >= POLLUTION_MAX || this.money+moneyIncr >= MONEY_MAX;
	}
	/**
	 * Updates the state of the main model by changing pollution and money.
	 */
	public void update() {
		if(!gameOver()) {
			if(pollution >= 0)
				pollution += pollIncr;
			if(moneyIncr >= 0 || money > 0)
				money += moneyIncr;
		}
	}
}