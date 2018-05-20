import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import org.junit.jupiter.api.Test;

class ResearchGameModelTest {
	ResearchGameModel rgm = new ResearchGameModel(1440, 900, 180, 180, false);

	@Test
	void testResearchGameModel() {
	}

	@Test
	void testGetCrabs() {
		Crab[] crabs = new Crab[25];

		assertEquals(crabs.length, rgm.getCrabs().length);
	}

	@Test
	void testGetRects() {
		Rectangle rect1 = new Rectangle(0, 0, 6000, 330); // locked
		Rectangle rect2 = new Rectangle(0, 550, 550, 330); // locked
		Rectangle rect3 = new Rectangle(1360, 0, 1190, 825); // locked
		Rectangle rect4 = new Rectangle(2180, 1050, 300, 300); // locked
		Rectangle rect5 = new Rectangle(3260, 1050, 1650, 300); // locked
		Rectangle rect6 = new Rectangle(0, 1060, 1350, 300); // locked
		Rectangle rect7 = new Rectangle(4215, 540, 580, 290); // locked
		Rectangle rect8 = new Rectangle(0, 1590, 6000, 290); // locked

		Rectangle[] rects = new Rectangle[8];
		rects[0] = rect1;
		rects[1] = rect2;
		rects[2] = rect3;
		rects[3] = rect4;
		rects[4] = rect5;
		rects[5] = rect6;
		rects[6] = rect7;
		rects[7] = rect8;

		assertArrayEquals(rects, rgm.getRects());
	}

	@Test
	void testGetPlayer() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int playerStartingX = (int) screenSize.getWidth() / 8;
		final int playerStartingY = (int) screenSize.getHeight() / 3;
		final int startingLives = 3;
		final RDirection startDir = RDirection.IDLE;
		Researcher player = new Researcher(playerStartingX, playerStartingY, startDir, startingLives);
		assertEquals(player, rgm.getPlayer());
	}

	@Test
	void testCrabCollisionChecker() {
		Crab[] crabs = rgm.getCrabs();
		Researcher player = rgm.getPlayer();
		rgm.crabCollisionChecker();

		assertEquals(3, player.getLives());

		player.getPlayerRect().setLocation((int) crabs[0].getCrabRect().getX(), (int) crabs[0].getCrabRect().getY());

		rgm.crabCollisionChecker();

		assertEquals(2, player.getLives());
	}

	@Test
	void testBoundsCollisionChecker() {
		Rectangle[] rects = rgm.getRects();
		Researcher player1 = rgm.getPlayer();
		rgm.boundsCollisionChecker();

		assertEquals(3, player1.getLives());

		player1.getPlayerRect().setLocation(0, 0);

		rgm.boundsCollisionChecker();

		assertEquals(2, player1.getLives());
	}

	@Test
	void testEndCheck() {
		Researcher player1 = rgm.getPlayer();
		player1.setxPos(7000);
		assertEquals(true, rgm.endCheck());

		player1.setxPos(300);
		assertEquals(false, rgm.endCheck());
	}

	@Test
	void testTutorialEndCheck() {
		ResearchGameModel rgm1 = new ResearchGameModel(1440, 900, 180, 180, true);
		Researcher player1 = rgm1.getPlayer();
		player1.setxPos(7000);
		assertEquals(true, rgm1.tutorialEndCheck());

		player1.setxPos(300);
		assertEquals(false, rgm1.tutorialEndCheck());
	}

	@Test
	void testLifeCheck() {
		Researcher player1 = rgm.getPlayer();
		assertEquals(false, rgm.lifeCheck());
		player1.setLives(0);
		assertEquals(true, rgm.lifeCheck());
	}

	@Test
	void testTutorialLifeCheck() {
		ResearchGameModel rgm2 = new ResearchGameModel(1440, 900, 180, 180, true);
		Researcher player1 = rgm2.getPlayer();
		assertEquals(false, rgm2.lifeCheck());
		player1.setLives(0);
		assertEquals(true, rgm2.lifeCheck());
	}

	@Test
	void testUpdateLocationAndDirection() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int playerStartingX = (180);
		final int frameWidth = (int) screenSize.getWidth();
		final int xIncr = frameWidth / 140;
		Researcher player = rgm.getPlayer();
		player.setDirection(RDirection.EAST);
		assertEquals(player.getxPos(), playerStartingX);

		rgm.updateLocationAndDirection();

		assertEquals(playerStartingX + xIncr, player.getxPos());

		player.setDirection(RDirection.NORTHEAST);

		rgm.updateLocationAndDirection();

		assertEquals((playerStartingX + xIncr + xIncr), player.getxPos());

		player.setDirection(RDirection.SOUTHEAST);

		rgm.updateLocationAndDirection();

		assertEquals((playerStartingX + xIncr + xIncr + xIncr), player.getxPos());

		player.setDirection(RDirection.IDLE);

		rgm.updateLocationAndDirection();

		assertEquals((playerStartingX + xIncr + xIncr + xIncr), player.getxPos());
	}

}
