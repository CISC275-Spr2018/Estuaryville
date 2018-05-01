import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.junit.jupiter.api.Test;

public class MainModelTester {
	@Test
	public void getBuildingTypesTest() {
		MainModel model = new MainModel();
		HashMap<String,Building> buildingTypes = new HashMap<String,Building>();
		buildingTypes.put("Bird",new Building(0,10000,"Bird Watching Tower","bird-tower"));
		buildingTypes.put("Research",new Building(0,10000,"Research Center","research"));
		buildingTypes.put("Fish",new Building(0,10000,"Fishing Pier","pier"));
		buildingTypes.put("Factory",new Building(0,10000,"Factory","factory"));
		buildingTypes.put("Port",new Building(0,10000,"Port","port"));
		String MESSAGE = "BUILDING TYPES ERROR";
		
		assertEquals(buildingTypes, model.getBuildingTypes(),MESSAGE);
	}
	
	@Test
	public void updateTest() {
		MainModel model = new MainModel();
		model.update();
		final int NEW_MONEY = 101;
		final int NEW_POLL = 2;
		String MESSAGE = "UPDATE ERROR";
		
		assertEquals(NEW_MONEY,model.getMoney(),MESSAGE);
		assertEquals(NEW_POLL,model.getPollution(),MESSAGE);
	}
	
	@Test
	public void buildTestBird() {
		MainModel model = new MainModel();
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.BIRD);
		model.build(new Building(0,10000,"Bird Watching Tower","bird-tower"),xPos,yPos);
		MapSpot mapTest[][] = new MapSpot[10][10];
		mapTest[xPos][yPos] = new MapSpot();
		mapTest[xPos][yPos].setB(new Building(0,0,"Bird Watching Tower","bird-tower"));
		String MESSAGE = "BUILD ERROR";
		assertEquals(model.getMap()[xPos][yPos],mapTest[xPos][yPos],MESSAGE);
	}
	@Test
	public void buildTestFish() {
		MainModel model = new MainModel();
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.FISH);
		model.build(new Building(0,10000,"Fishing Pier","pier"),xPos,yPos);
		MapSpot mapTest[][] = new MapSpot[10][10];
		mapTest[xPos][yPos] = new MapSpot();
		mapTest[xPos][yPos].setB(new Building(0,0,"Fishing Pier","pier"));
		String MESSAGE = "BUILD ERROR";
		assertEquals(model.getMap()[xPos][yPos],mapTest[xPos][yPos],MESSAGE);
	}
	@Test
	public void buildTestResearch() {
		MainModel model = new MainModel();
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.RESEARCH);
		model.build(new Building(0,10000,"Research Station","research"),xPos,yPos);
		MapSpot mapTest[][] = new MapSpot[10][10];
		mapTest[xPos][yPos] = new MapSpot();
		mapTest[xPos][yPos].setB(new Building(0,0,"Research Station","research"));
		String MESSAGE = "BUILD ERROR";
		assertEquals(model.getMap()[xPos][yPos],mapTest[xPos][yPos],MESSAGE);
	}
	@Test
	public void buildTestPort() {
		MainModel model = new MainModel();
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.PORT);
		model.build(new Building(0,10000,"Port","port"),xPos,yPos);
		MapSpot mapTest[][] = new MapSpot[10][10];
		mapTest[xPos][yPos] = new MapSpot();
		mapTest[xPos][yPos].setB(new Building(0,0,"Port","port"));
		String MESSAGE = "BUILD ERROR";
		assertEquals(model.getMap()[xPos][yPos],mapTest[xPos][yPos],MESSAGE);
	}
	@Test
	public void buildTestFactory() {
		MainModel model = new MainModel();
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.FACTORY);
		model.build(new Building(0,10000,"Factory","factory"),xPos,yPos);
		MapSpot mapTest[][] = new MapSpot[10][10];
		mapTest[xPos][yPos] = new MapSpot();
		mapTest[xPos][yPos].setB(new Building(0,0,"Factory","factory"));
		String MESSAGE = "BUILD ERROR";
		assertEquals(model.getMap()[xPos][yPos],mapTest[xPos][yPos],MESSAGE);
	}
	@Test
	public void buildRemoveTest() {
		MainModel model = new MainModel();
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.FACTORY);
		model.build(new Building(0,10000,"Factory","factory"),xPos,yPos);
		model.removeBuilding(xPos, yPos);
		MapSpot testSpot = new MapSpot();
		testSpot.setB(null);
		String MESSAGE = "REMOVE ERROR";
		assertEquals(model.getMap()[xPos][yPos],testSpot,MESSAGE);
	}
	@Test
	public void gettersAndSettersTest() {
		MainModel model = new MainModel();
		assertEquals(model.getPollIncr(),1);
		assertEquals(model.getMoneyIncr(),1);
		assertEquals(model.getBuild(),BuildState.NONE);
		assertEquals(model.getPollution(), 1);
		assertEquals(model.getMoney(), 100);
		
		model.setMap(null);
		model.setMoneyIncr(0);
		model.setMoney(0);
		model.setPollIncr(0);
		model.setPollution(0);
		model.setBuild(BuildState.REMOVE);
		
		assertEquals(model.getPollIncr(),0);
		assertEquals(model.getMoneyIncr(),0);
		assertEquals(model.getBuild(),BuildState.REMOVE);
		assertEquals(model.getPollution(), 0);
		assertEquals(model.getMoney(), 0);
		assertEquals(model.getMap(), null);
	}
	@Test
	public void gameOverTestFalse() {
		MainModel model = new MainModel();
		assertEquals(model.gameOver(), false);
	}
	@Test
	public void gameOverTestMoney() {
		MainModel model = new MainModel();
		model.setMoney(model.MONEY_MAX);
		assertEquals(model.gameOver(), true);
	}
	@Test
	public void gameOverTestPollution() {
		MainModel model = new MainModel();
		model.setPollution(model.POLLUTION_MAX);
		assertEquals(model.gameOver(), true);
	}
	@Test
	public void isConstructableTestPollution() {
		MainModel model = new MainModel();
		assertEquals(model.isConstructable(new Building(0,-10,"Factory","factory")),false);
	}
	@Test
	public void isConstructableTestMoney() {
		MainModel model = new MainModel();
		assertEquals(model.isConstructable(new Building(model.MONEY_MAX+10,0,"Factory","factory")),false);
	}
	@Test
	public void TerrainStateTest() {
		MapSpot spot = new MapSpot();
		spot.settState(TerrainState.BEACH);
		spot.settState(TerrainState.NORMAL);
		spot.settState(TerrainState.SEA);
		spot.settState(TerrainState.OTHER);
		spot.settState(TerrainState.RIVER);
		spot.settState(TerrainState.FOREST);
	}
	@Test
	public void BuildState() {
		MainModel model = new MainModel();
		model.setBuild(BuildState.FISH);
		model.setBuild(BuildState.BIRD);
		model.setBuild(BuildState.RESEARCH);
		model.setBuild(BuildState.REMOVE);
		model.setBuild(BuildState.NONE);
		model.setBuild(BuildState.FACTORY);
		model.setBuild(BuildState.PORT);
	}
}
