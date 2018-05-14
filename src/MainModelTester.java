import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.junit.jupiter.api.Test;

public class MainModelTester {
	@Test
	public void getBuildingTypesTest() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		HashMap<BuildingName,Building> types = new HashMap<BuildingName,Building>();
		types.put(BuildingName.BIRD,new Building(150,10000,BuildingName.BIRD,"bird-tower"));
		types.put(BuildingName.RESEARCH,new Building(400,10000,BuildingName.RESEARCH,"research"));
		types.put(BuildingName.FISH,new Building(250,10000,BuildingName.FISH,"pier"));
		types.put(BuildingName.FACTORY,new Building(200,10000,BuildingName.FACTORY,"factory"));
		types.put(BuildingName.PORT,new Building(150,10000,BuildingName.PORT,"port"));
		types.put(BuildingName.TUTORIAL, new Building(100,10000,BuildingName.TUTORIAL,"factory"));
		String MESSAGE = "BUILDING TYPES ERROR";
		
		assertEquals(types, model.getBuildingTypes(),MESSAGE);
	}
	
	@Test
	public void updateTest() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		model.update();
		final int NEW_MONEY = 250;
		final int NEW_POLL = 1;
		String MESSAGE = "UPDATE ERROR";
		
		assertEquals(NEW_MONEY,model.getMoney(),MESSAGE);
		assertEquals(NEW_POLL,model.getPollution(),MESSAGE);
	}
	@Test
	public void buildTestFail1() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.BIRD);
		model.build(new Building(0,0,BuildingName.BIRD,"bird-tower"),xPos,yPos);
		MapSpot mapTest[][] = new MapSpot[10][10];
		mapTest[xPos][yPos] = new MapSpot();
		String MESSAGE = "BUILD ERROR";
		assertEquals(model.getMap()[xPos][yPos],mapTest[xPos][yPos],MESSAGE);
	}
	@Test
	public void buildTestFail2() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.BIRD);
		model.build(new Building(10000000,10000,BuildingName.BIRD,"bird-tower"),xPos,yPos);
		MapSpot mapTest[][] = new MapSpot[10][10];
		mapTest[xPos][yPos] = new MapSpot();
		String MESSAGE = "BUILD ERROR";
		assertEquals(model.getMap()[xPos][yPos],mapTest[xPos][yPos],MESSAGE);
	}
	@Test
	public void buildTestFail3() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.BIRD);
		model.build(new Building(0,10000,BuildingName.BIRD,"bird-tower"),xPos,yPos);
		model.build(new Building(0,10000,BuildingName.BIRD,"bird-tower"),xPos+1,yPos);
		MapSpot mapTest[][] = new MapSpot[10][10];
		mapTest[xPos][yPos] = new MapSpot();
		mapTest[xPos+1][yPos] = new MapSpot();
		mapTest[xPos][yPos].setB(new Building(0,0,BuildingName.BIRD,"bird-tower"));
		String MESSAGE = "BUILD ERROR";
		assertEquals(model.getMap()[xPos+1][yPos],mapTest[xPos+1][yPos],MESSAGE);
	}
	@Test
	public void buildTestBird() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.BIRD);
		model.build(new Building(0,10000,BuildingName.BIRD,"bird-tower"),xPos,yPos);
		MapSpot mapTest[][] = new MapSpot[10][10];
		mapTest[xPos][yPos] = new MapSpot();
		mapTest[xPos][yPos].setB(new Building(0,0,BuildingName.BIRD,"bird-tower"));
		String MESSAGE = "BUILD ERROR";
		assertEquals(model.getMap()[xPos][yPos],mapTest[xPos][yPos],MESSAGE);
	}
	@Test
	public void buildTestFish() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		final int xPos = 1;
		final int yPos = 1;
		map[xPos][yPos].settState(TerrainState.BEACH);
		MainModel model = new MainModel(map);
		model.setBuild(BuildState.FISH);
		model.build(new Building(0,10000,BuildingName.FISH,"pier"),xPos,yPos);
		MapSpot mapTest[][] = new MapSpot[10][10];
		mapTest[xPos][yPos] = new MapSpot();
		mapTest[xPos][yPos].setB(new Building(0,0,BuildingName.FISH,"pier"));
		mapTest[xPos][yPos].settState(TerrainState.BEACH);
		String MESSAGE = "BUILD ERROR";
		assertEquals(model.getMap()[xPos][yPos],mapTest[xPos][yPos],MESSAGE);
	}
	@Test
	public void buildTestResearch() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.RESEARCH);
		model.build(new Building(0,10000,BuildingName.RESEARCH,"research"),xPos,yPos);
		MapSpot mapTest[][] = new MapSpot[10][10];
		mapTest[xPos][yPos] = new MapSpot();
		mapTest[xPos][yPos].setB(new Building(0,0,BuildingName.RESEARCH,"research"));
		String MESSAGE = "BUILD ERROR";
		assertEquals(model.getMap()[xPos][yPos],mapTest[xPos][yPos],MESSAGE);
	}
	@Test
	public void buildTestPort() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		final int xPos = 1;
		final int yPos = 1;
		map[xPos][yPos].settState(TerrainState.OCEAN);
		MainModel model = new MainModel(map);
		model.setBuild(BuildState.PORT);
		model.build(new Building(0,10000,BuildingName.PORT,"port"),xPos,yPos);
		MapSpot mapTest[][] = new MapSpot[10][10];
		mapTest[xPos][yPos] = new MapSpot();
		mapTest[xPos][yPos].settState(TerrainState.OCEAN);
		mapTest[xPos][yPos].setB(new Building(0,0,BuildingName.PORT,"port"));
		String MESSAGE = "BUILD ERROR";
		assertEquals(model.getMap()[xPos][yPos],mapTest[xPos][yPos],MESSAGE);
	}
	@Test
	public void buildTestFactory() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.FACTORY);
		model.build(new Building(0,10000,BuildingName.FACTORY,"factory"),xPos,yPos);
		MapSpot mapTest[][] = new MapSpot[10][10];
		mapTest[xPos][yPos] = new MapSpot();
		mapTest[xPos][yPos].setB(new Building(0,0,BuildingName.FACTORY,"factory"));
		String MESSAGE = "BUILD ERROR";
		assertEquals(model.getMap()[xPos][yPos],mapTest[xPos][yPos],MESSAGE);
	}
	@Test
	public void buildTestFailPoll() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.FACTORY);
		model.build(new Building(0,-10,BuildingName.FACTORY,"factory"),xPos,yPos);
		MapSpot mapTest[][] = new MapSpot[10][10];
		mapTest[xPos][yPos] = new MapSpot();
		mapTest[xPos][yPos].setB(null);
		String MESSAGE = "BUILD ERROR";
		assertEquals(model.getMap()[xPos][yPos],mapTest[xPos][yPos],MESSAGE);
	}
	@Test
	public void buildTestFailPlaced() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.FACTORY);
		model.build(new Building(0,10000,BuildingName.FACTORY,"factory"),xPos,yPos);
		model.setBuild(BuildState.FACTORY);
		model.build(new Building(0,10000,BuildingName.FACTORY,"factory"),xPos,yPos);
		model.removeBuilding(xPos, yPos);
		MapSpot mapTest[][] = new MapSpot[10][10];
		mapTest[xPos][yPos] = new MapSpot();
		mapTest[xPos][yPos].setB(null);
		String MESSAGE = "BUILD ERROR";
		assertEquals(model.getMap()[xPos][yPos],mapTest[xPos][yPos],MESSAGE);
	}
	@Test
	public void buildRemoveTest() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.TUTORIAL);
		model.build(new Building(0,10000,BuildingName.TUTORIAL,"factory"),xPos,yPos);
		model.removeBuilding(xPos, yPos);
		MapSpot testSpot = new MapSpot();
		testSpot.setB(null);
		String MESSAGE = "REMOVE ERROR";
		assertEquals(model.getMap()[xPos][yPos],testSpot,MESSAGE);
	}
	@Test
	public void buildRemoveTest2() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		final int xPos = 1;
		final int yPos = 1;
		model.setBuild(BuildState.RESEARCH);
		model.build(new Building(0,10000,BuildingName.RESEARCH,"research"),xPos,yPos);
		model.removeBuilding(xPos, yPos);
		MapSpot testSpot = new MapSpot();
		testSpot.setB(null);
		String MESSAGE = "REMOVE ERROR";
		assertEquals(model.getMap()[xPos][yPos],testSpot,MESSAGE);
	}
	@Test
	public void buildRemoveTest3() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		final int xPos = 0;
		final int yPos = 5;
		model.setBuild(BuildState.PORT);
		model.build(new Building(0,10000,BuildingName.PORT,"port"),xPos,yPos);
		model.setBuild(BuildState.REMOVE);
		model.removeBuilding(xPos, yPos);
		MapSpot testSpot = new MapSpot();
		testSpot.setB(null);
		String MESSAGE = "REMOVE ERROR";
		assertEquals(model.getMap()[xPos][yPos],testSpot,MESSAGE);
	}
	@Test
	public void gettersAndSettersTest() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		assertEquals(model.getPollIncr(),0);
		assertEquals(model.getMoneyIncr(),0);
		assertEquals(model.getBuild(),BuildState.NONE);
		assertEquals(model.getPollution(), 1);
		assertEquals(model.getMoney(), 250);
		assertEquals(model.getBuilt(), false);
		assertEquals(model.getTutorial(), true);
		
		model.setMoneyIncr(1);
		model.setMoney(1);
		model.setPollIncr(1);
		model.setPollution(1);
		model.setBuild(BuildState.REMOVE);
		model.setBuilt(true);
		
		assertEquals(model.getPollIncr(),1);
		assertEquals(model.getMoneyIncr(),1);
		assertEquals(model.getBuild(),BuildState.REMOVE);
		assertEquals(model.getPollution(), 1);
		assertEquals(model.getMoney(), 1);
		assertEquals(model.getBuilt(), true);
	}
	@Test
	public void resetTest() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		model.reset();
		assertEquals(model.getMoney(), 250);
		assertEquals(model.getPollution(), 1);
		assertEquals(model.getMoneyIncr(), 3);
		assertEquals(model.getPollIncr(), 5);
		model.reset();
		assertEquals(model.getTutorial(), true);
	}
	@Test
	public void setButtons() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		model.setButtons(map);
		assertEquals(model.getMap()[0][0].getButton(), map[0][0].getButton());
	}
	@Test
	public void isValidTest2() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		Building b = new Building(0,10000,BuildingName.TUTORIAL,"factory");
		assertEquals(model.isValidPlacement(5, 5, b),true);
	}
	@Test
	public void updateTest2() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		model.setPollution(model.POLLUTION_MAX+10);
		model.update();
		assertEquals(model.getMoney(), model.MONEY_START);
	}
	@Test
	public void gameOverTestFalse() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		assertEquals(model.gameOver(), false);
	}
	@Test
	public void gameOverTestMoney() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		model.setMoney(model.MONEY_MAX);
		assertEquals(model.gameOver(), true);
	}
	@Test
	public void gameOverTestPollution() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		model.setPollution(model.POLLUTION_MAX);
		assertEquals(model.gameOver(), true);
	}
	@Test
	public void isConstructableTestPollution() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		assertEquals(model.isConstructable(new Building(0,-10,BuildingName.FACTORY,"factory")),false);
	}
	@Test
	public void isConstructableTestMoney() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		assertEquals(model.isConstructable(new Building(model.MONEY_MAX+10,0,BuildingName.FACTORY,"factory")),false);
	}
	@Test
	public void TerrainStateTest() {
		MapSpot spot = new MapSpot();
		spot.settState(TerrainState.BEACH);
		spot.settState(TerrainState.NORMAL);
		spot.settState(TerrainState.OCEAN);
		spot.settState(TerrainState.OTHER);
		spot.settState(TerrainState.RIVER);
		spot.settState(TerrainState.FOREST);
	}
	@Test
	public void BuildState() {
		MapSpot[][] map = new MapSpot[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = new MapSpot();
			}
		}
		MainModel model = new MainModel(map);
		model.setBuild(BuildState.FISH);
		model.setBuild(BuildState.BIRD);
		model.setBuild(BuildState.RESEARCH);
		model.setBuild(BuildState.REMOVE);
		model.setBuild(BuildState.NONE);
		model.setBuild(BuildState.FACTORY);
		model.setBuild(BuildState.PORT);
	}
}
