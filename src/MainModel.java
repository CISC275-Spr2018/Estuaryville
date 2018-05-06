import java.util.HashMap;

public class MainModel {
	public final int POLLUTION_MAX = 10000;
	public final int MONEY_MAX = 1000;
	private int pollution = 1;
	private int money = 100;
	private final int MAP_HEIGHT = 10;
	private final int MAP_WIDTH = 10;
	private MapSpot[][] map = new MapSpot[MAP_HEIGHT][MAP_WIDTH];
	private HashMap<String,Building> buildingTypes;
	private int pollIncr = 1;
	private int moneyIncr = 1;
	private BuildState build = BuildState.NONE;
	private boolean[] placedBuildings = new boolean[BuildState.values().length];

	public HashMap<String,Building> getBuildingTypes() {
		return buildingTypes;
	}

	public int getPollIncr() {
		return pollIncr;
	}

	public void setPollIncr(int pollIncr) {
		this.pollIncr = pollIncr;
	}

	public int getMoneyIncr() {
		return moneyIncr;
	}

	public void setMoneyIncr(int moneyIncr) {
		this.moneyIncr = moneyIncr;
	}

	public BuildState getBuild() {
		return build;
	}

	public void setBuild(BuildState build) {
		this.build = build;
	}

	public int getPollution() {
		return pollution;
	}

	public void setPollution(int pollution) {
		this.pollution = pollution;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public MapSpot[][] getMap() {
		return map;
	}
	
	public void setMap(MapSpot[][] map) {
		this.map = map;
	}
	
	public MainModel(MapSpot[][] map) {
		this.map = map;
		buildingTypes = loadBuildingTypes();
	}
	
	public HashMap<String,Building> loadBuildingTypes() {//legal to import Images here?
		HashMap<String,Building> types = new HashMap<String,Building>();
		types.put("Bird",new Building(0,10000,"Bird Watching Tower","bird-tower"));
		types.put("Research",new Building(0,10000,"Research Center","research"));
		types.put("Fish",new Building(0,10000,"Fishing Pier","pier"));
		types.put("Factory",new Building(0,10000,"Factory","factory"));
		types.put("Port",new Building(0,10000,"Port","port"));
		return types;
	}
	
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
				return Active.RESEARCH;
			default:
				return Active.MAIN;
			}
		}
		build = BuildState.NONE;
		return Active.MAIN;
	}
	
	public void removeBuilding(int xPos, int yPos) {
		map[xPos][yPos].setB(null);
		build = BuildState.NONE;
	}
	
	public boolean isConstructable(Building structure) {
		if ((structure.getCost() <= money) && (structure.getQualityNeeded() >= pollution)){
			money -= structure.getCost();
			return true;
		}
		return false;
	}
	
	public boolean isValidPlacement(int xPos, int yPos, Building structure) {
		return map[yPos][xPos].isValid(structure);
	}
	
	public boolean gameOver() {
		return this.pollution == POLLUTION_MAX || this.money == MONEY_MAX;
	}
	
	public void update() {
		pollution += pollIncr;
		money += moneyIncr;
	}
}