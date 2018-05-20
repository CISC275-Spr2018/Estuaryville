
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * This is a class to hold all the test cases for the BirdWatchingGameModel.
 * 
 * @author Tyler
 */
public class BirdWatchingGameModelTests {
	BirdWatchingGameView testView = new BirdWatchingGameView();
	BirdWatchingGameModel testModel = new BirdWatchingGameModel(testView.getScreenWidth(), testView.getScreenHeight());

	@Test
	public void getCameraTest() {
		assertEquals(testModel.getCamera(), testModel.getCamera());
	}

	@Test
	public void getBirdsTest() {
		assertEquals(testModel.getBirds(), testModel.getBirds());
	}

	// BIRDS EAST TESTS
	@Test
	public void eastRightBoundTest() {
		testModel.getBirds().get(0).setDirection(BDirection.EAST);
		testModel.getBirds().get(0).setXPos(testView.scaledBackground.getWidth() - 49); // outside
																						// of
																						// right
																						// boundary
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(0, testModel.getBirds().get(0).getXPos());
	}

	@Test
	public void eastInBoundsCheck() {
		testModel.getBirds().get(0).setDirection(BDirection.EAST);
		testModel.getBirds().get(0).setXPos(testView.background.getWidth() - 51); // inside
																					// of
																					// boundaries
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(testView.background.getWidth() - 51, testModel.getBirds().get(0).getXPos());
	}

	// BIRDS WEST TESTS
	@Test
	public void westLeftBoundTest() {
		testModel.getBirds().get(0).setDirection(BDirection.WEST);
		testModel.getBirds().get(0).setXPos(-51); // outside of left boundary
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(testView.scaledBackground.getWidth() - 50, testModel.getBirds().get(0).getXPos());
	}

	@Test
	public void westInBoundsCheck() {
		testModel.getBirds().get(0).setDirection(BDirection.WEST);
		testModel.getBirds().get(0).setXPos(-49); // inside of boundaries
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(-49, testModel.getBirds().get(0).getXPos());
	}

	// BIRDS NORTHEAST TESTS
	@Test
	public void northeastRightBoundTest() {
		testModel.getBirds().get(0).setDirection(BDirection.NORTHEAST);
		testModel.getBirds().get(0).setXPos(testView.scaledBackground.getWidth() - 49); // outside
																						// of
																						// right
																						// boundary
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(0, testModel.getBirds().get(0).getXPos());
	}

	@Test
	public void northeastTopBoundTest() {
		testModel.getBirds().get(0).setDirection(BDirection.NORTHEAST);
		testModel.getBirds().get(0).setYPos(-51); // outside of top boundary
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(testView.getScreenHeight() + 50, testModel.getBirds().get(0).getYPos());
	}

	@Test
	public void northeastInHeightBoundsCheck() {
		testModel.getBirds().get(0).setDirection(BDirection.NORTHEAST);
		testModel.getBirds().get(0).setYPos(-50); // inside of height boundaries
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(-50, testModel.getBirds().get(0).getYPos());
	}

	@Test
	public void northeastInWidthBoundsCheck() {
		testModel.getBirds().get(0).setDirection(BDirection.NORTHEAST);
		testModel.getBirds().get(0).setXPos(testView.getScreenWidth() - 51); // inside
																				// of
																				// width
																				// boundaries
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(testView.getScreenWidth() - 51, testModel.getBirds().get(0).getXPos());
	}

	// BIRDS NORTHWEST TESTS
	@Test
	public void northwestLeftBoundTest() {
		testModel.getBirds().get(0).setDirection(BDirection.NORTHWEST);
		testModel.getBirds().get(0).setXPos(-51); // outside of left boundary
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(testView.scaledBackground.getWidth() - 50, testModel.getBirds().get(0).getXPos());
	}

	@Test
	public void northwestTopBoundTest() {
		testModel.getBirds().get(0).setDirection(BDirection.NORTHWEST);
		testModel.getBirds().get(0).setYPos(-51); // outside of top boundary
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(testView.getScreenHeight() + 50, testModel.getBirds().get(0).getYPos());
	}

	@Test
	public void northwestInHeightBoundsCheck() {
		testModel.getBirds().get(0).setDirection(BDirection.NORTHWEST);
		testModel.getBirds().get(0).setYPos(-50); // inside of height boundaries
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(-50, testModel.getBirds().get(0).getYPos());
	}

	@Test
	public void northwestInWidthBoundsCheck() {
		testModel.getBirds().get(0).setDirection(BDirection.NORTHWEST);
		testModel.getBirds().get(0).setXPos(testView.getScreenWidth() - 51); // inside
																				// of
																				// width
																				// boundaries
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(testView.getScreenWidth() - 51, testModel.getBirds().get(0).getXPos());
	}

	// BIRDS SOUTHEAST TESTS
	@Test
	public void southeastRightBoundTest() {
		testModel.getBirds().get(0).setDirection(BDirection.SOUTHEAST);
		testModel.getBirds().get(0).setXPos(testView.scaledBackground.getWidth() - 49); // outside
																						// of
																						// right
																						// boundary
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(0, testModel.getBirds().get(0).getXPos());
	}

	@Test
	public void southeastBottomBoundTest() {
		testModel.getBirds().get(0).setDirection(BDirection.SOUTHEAST);
		testModel.getBirds().get(0).setYPos(testView.getScreenHeight() - 49); // outside
																				// of
																				// bottom
																				// boundary
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(0, testModel.getBirds().get(0).getYPos());
	}

	@Test
	public void southeastInHeightBoundsCheck() {
		testModel.getBirds().get(0).setDirection(BDirection.SOUTHEAST);
		testModel.getBirds().get(0).setYPos(-50); // inside of height boundaries
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(-50, testModel.getBirds().get(0).getYPos());
	}

	@Test
	public void southeastInWidthBoundsCheck() {
		testModel.getBirds().get(0).setDirection(BDirection.SOUTHEAST);
		testModel.getBirds().get(0).setXPos(testView.getScreenWidth() - 51); // inside
																				// of
																				// width
																				// boundaries
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(testView.getScreenWidth() - 51, testModel.getBirds().get(0).getXPos());
	}

	// BIRDS SOUTHWEST TESTS
	@Test
	public void southwestLeftBoundTest() {
		testModel.getBirds().get(0).setDirection(BDirection.SOUTHWEST);
		testModel.getBirds().get(0).setXPos(-51); // outside of left boundary
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(testView.getScreenWidth() - 50, testModel.getBirds().get(0).getXPos());
	}

	@Test
	public void southwestBottomBoundTest() {
		testModel.getBirds().get(0).setDirection(BDirection.SOUTHWEST);
		testModel.getBirds().get(0).setYPos(testView.getScreenHeight() - 49); // outside
																				// of
																				// bottom
																				// boundary
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(0, testModel.getBirds().get(0).getYPos());
	}

	@Test
	public void southwestInHeightBoundsCheck() {
		testModel.getBirds().get(0).setDirection(BDirection.SOUTHWEST);
		testModel.getBirds().get(0).setYPos(-50); // inside of height boundaries
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(-50, testModel.getBirds().get(0).getYPos());
	}

	@Test
	public void southwestInWidthBoundsCheck() {
		testModel.getBirds().get(0).setDirection(BDirection.SOUTHWEST);
		testModel.getBirds().get(0).setXPos(testView.getScreenWidth() - 51); // inside
																				// of
																				// width
																				// boundaries
		testModel.checkBounds(testModel.getBirds().get(0));
		assertEquals(testView.getScreenWidth() - 51, testModel.getBirds().get(0).getXPos());
	}

	// SANDPIPER TESTS
	@Test
	public void sandpiperEastTest() {
		Bird sandpiper = testModel.getBirds().get(1);
		sandpiper.setDirection(BDirection.EAST);
		sandpiper.setXPos(testView.getScaledWidth(900)); // inside right bounds
		testModel.checkBounds(sandpiper);
		assertEquals(testView.getScaledWidth(900) + sandpiper.getXSpeed(), sandpiper.getXPos());
		sandpiper.setXPos(testView.getScaledWidth(931)); // outside right bounds
		testModel.checkBounds(sandpiper);
		assertNotEquals(BDirection.EAST, sandpiper);
	}

	@Test
	public void sandpiperWestTest() {
		Bird sandpiper = testModel.getBirds().get(1);
		sandpiper.setDirection(BDirection.WEST);
		sandpiper.setXPos(testView.getScaledWidth(776)); // inside left bounds
		testModel.checkBounds(sandpiper);
		assertEquals(testView.getScaledWidth(776) - sandpiper.getXSpeed(), sandpiper.getXPos());
		sandpiper.setXPos(testView.getScaledWidth(700)); // outside left bounds
		testModel.checkBounds(sandpiper);
		assertNotEquals(BDirection.WEST, sandpiper);
	}

	@Test
	public void sandpiperNortheastRightTest() {
		Bird sandpiper = testModel.getBirds().get(1);
		sandpiper.setDirection(BDirection.NORTHEAST);
		sandpiper.setXPos(testView.getScaledWidth(900)); // inside right bounds
		testModel.checkBounds(sandpiper);
		assertEquals(testView.getScaledWidth(900) + sandpiper.getXSpeed(), sandpiper.getXPos());
		sandpiper.setXPos(testView.getScaledWidth(1000)); // outside right
															// bounds
		testModel.checkBounds(sandpiper);
		assertNotEquals(BDirection.NORTHEAST, sandpiper);
	}

	@Test
	public void sandpiperNortheastTopTest() {
		Bird sandpiper = testModel.getBirds().get(1);
		sandpiper.setDirection(BDirection.NORTHEAST);
		sandpiper.setYPos(testView.getScaledHeight(500)); // inside top bounds
		testModel.checkBounds(sandpiper);
		assertEquals(testView.getScaledHeight(500) - sandpiper.getYSpeed(), sandpiper.getYPos());
		sandpiper.setYPos(testView.getScaledHeight(400)); // outside top bounds
		testModel.checkBounds(sandpiper);
		assertNotEquals(BDirection.NORTHEAST, sandpiper);
	}

	@Test
	public void sandpiperNorthwestLeftTest() {
		Bird sandpiper = testModel.getBirds().get(1);
		sandpiper.setDirection(BDirection.NORTHWEST);
		sandpiper.setXPos(testView.getScaledWidth(800)); // inside left bounds
		testModel.checkBounds(sandpiper);
		assertEquals(testView.getScaledWidth(800) - sandpiper.getXSpeed(), sandpiper.getXPos());
		sandpiper.setXPos(testView.getScaledWidth(700)); // outside left bounds
		testModel.checkBounds(sandpiper);
		assertNotEquals(BDirection.NORTHWEST, sandpiper);
	}

	@Test
	public void sandpiperNorthwestTopTest() {
		Bird sandpiper = testModel.getBirds().get(1);
		sandpiper.setDirection(BDirection.NORTHWEST);
		sandpiper.setYPos(testView.getScaledHeight(500)); // inside top bounds
		testModel.checkBounds(sandpiper);
		assertEquals(testView.getScaledHeight(500) - sandpiper.getYSpeed(), sandpiper.getYPos());
		sandpiper.setYPos(testView.getScaledHeight(400)); // outside top bounds
		testModel.checkBounds(sandpiper);
		assertNotEquals(BDirection.NORTHWEST, sandpiper);
	}

	@Test
	public void sandpiperSoutheastRightTest() {
		Bird sandpiper = testModel.getBirds().get(1);
		sandpiper.setDirection(BDirection.SOUTHEAST);
		sandpiper.setXPos(testView.getScaledWidth(900)); // inside right bounds
		testModel.checkBounds(sandpiper);
		assertEquals(testView.getScaledWidth(900) + sandpiper.getXSpeed(), sandpiper.getXPos());
		sandpiper.setXPos(testView.getScaledWidth(950)); // outside right bounds
		testModel.checkBounds(sandpiper);
		assertNotEquals(BDirection.SOUTHEAST, sandpiper);
	}

	@Test
	public void sandpiperSoutheastBottomTest() {
		Bird sandpiper = testModel.getBirds().get(1);
		sandpiper.setDirection(BDirection.SOUTHEAST);
		sandpiper.setYPos(testView.getScaledHeight(500)); // inside bottom
															// bounds
		testModel.checkBounds(sandpiper);
		assertEquals(testView.getScaledHeight(500) + sandpiper.getYSpeed(), sandpiper.getYPos());
		sandpiper.setYPos(testView.getScaledHeight(600)); // outside bottom
															// bounds
		testModel.checkBounds(sandpiper);
		assertNotEquals(BDirection.SOUTHEAST, sandpiper);
	}

	@Test
	public void sandpiperSouthwestLeftTest() {
		Bird sandpiper = testModel.getBirds().get(1);
		sandpiper.setDirection(BDirection.SOUTHWEST);
		sandpiper.setXPos(testView.getScaledWidth(776)); // inside left bounds
		testModel.checkBounds(sandpiper);
		assertEquals(testView.getScaledWidth(776) - sandpiper.getXSpeed(), sandpiper.getXPos());
		sandpiper.setXPos(testView.getScaledWidth(700)); // outside left bounds
		testModel.checkBounds(sandpiper);
		assertNotEquals(BDirection.SOUTHWEST, sandpiper);
	}

	@Test
	public void sandpiperSouthwestBottomTest() {
		Bird sandpiper = testModel.getBirds().get(1);
		sandpiper.setDirection(BDirection.SOUTHWEST);
		sandpiper.setYPos(testView.getScaledHeight(500)); // inside bottom
															// bounds
		testModel.checkBounds(sandpiper);
		assertEquals(testView.getScaledHeight(500) + sandpiper.getYSpeed(), sandpiper.getYPos());
		sandpiper.setYPos(testView.getScaledHeight(600)); // outside bottom
															// bounds
		testModel.checkBounds(sandpiper);
		assertNotEquals(BDirection.SOUTHWEST, sandpiper);
	}

	// CAMERA BOUNDS TESTS
	// HEIGHT TESTS
	@Test
	public void cameraTopBoundCheck() {
		testModel.getCamera().setYPos(-61); // outside of top of screen
		testModel.checkBounds(testModel.getCamera());
		assertEquals(-61, testModel.getCamera().getYPos());
	}

	@Test
	public void cameraBottomBoundCheck() {
		testModel.getCamera().setYPos(testView.getScreenHeight() - 349); // outside
																			// of
																			// bottom
																			// of
																			// screen
		testModel.checkBounds(testModel.getCamera());
		assertEquals(testView.getScreenHeight() - 349, testModel.getCamera().getYPos());
	}

	@Test
	public void cameraInHeightCheck() {
		testModel.getCamera().setYPos(-59); // inside of height
		testModel.checkBounds(testModel.getCamera());
		assertEquals(-59 + testModel.getCamera().getYSpeed(), testModel.getCamera().getYPos());
	}

	// WIDTH TESTS
	@Test
	public void cameraRightBoundCheck() {
		testModel.getCamera().setXPos(testView.getScreenWidth() - 339); // outside
																		// of
																		// right
																		// of
																		// screen
		testModel.checkBounds(testModel.getCamera());
		assertEquals(testView.getScreenWidth() - 339, testModel.getCamera().getXPos());
	}

	@Test
	public void cameraLeftBoundCheck() {
		testModel.getCamera().setXPos(-1); // outside of left of screen
		testModel.checkBounds(testModel.getCamera());
		assertEquals(-1, testModel.getCamera().getXPos());
	}

	@Test
	public void cameraInWidthCheck() {
		testModel.getCamera().setXPos(1); // inside of width
		testModel.checkBounds(testModel.getCamera());
		assertEquals(1 + testModel.getCamera().getXSpeed(), testModel.getCamera().getXPos());
	}

	// TAKE PICTURE TESTS
	@Test
	public void gameOverTest() {
		testModel.getBirds().remove(testModel.getBirds().get(2));
		testModel.getBirds().remove(testModel.getBirds().get(1));
		testModel.getBirds().get(0).getRect().setLocation(500, 500);
		testModel.getCamera().getRect().setLocation(500, 500);
		testModel.setTutorial(false);
		testModel.takePicture(testModel.getBirds().get(0), testModel.getBirds());
		assertEquals(true, testModel.getGameOver());
	}

	@Test
	public void tutorialTakePictureTest() {
		BirdWatchingGameModel tstModel = new BirdWatchingGameModel(testView.getScreenWidth(),
				testView.getScreenHeight());
		tstModel.getBirds().get(0).getRect().setLocation(500, 500);
		tstModel.getCamera().getRect().setLocation(500, 500);
		tstModel.takePicture(tstModel.getBirds().get(0), tstModel.getBirds());
		assertEquals(false, tstModel.getTutorial());
	}

	@Test
	public void takePictureTest() {
		BirdWatchingGameModel tstModel = new BirdWatchingGameModel(testView.getScreenWidth(),
				testView.getScreenHeight());
		tstModel.setTutorial(false);
		tstModel.getBirds().get(0).getRect().setLocation(500, 500);
		tstModel.getCamera().getRect().setLocation(500, 500);
		tstModel.takePicture(tstModel.getBirds().get(0), tstModel.getBirds());
		assertEquals(2, tstModel.numBirdsNotFound);
	}

	@Test
	public void wrongBirdTest() {
		testModel.getBirds().get(0).getRect().setLocation(0, 0);
		testModel.getBirds().get(1).getRect().setLocation(500, 500);
		testModel.getCamera().getRect().setLocation(500, 500);
		testModel.setTutorial(false);
		testModel.takePicture(testModel.getBirds().get(0), testModel.getBirds());
		assertEquals(true, testModel.getWrongBird());
	}

	@Test
	public void tryAgainTest() {
		testModel.getBirds().get(0).getRect().setLocation(0, 0);
		testModel.getBirds().get(1).getRect().setLocation(0, 0);
		testModel.getBirds().get(2).getRect().setLocation(0, 0);
		testModel.getCamera().getRect().setLocation(500, 500);
		testModel.setTutorial(false);
		testModel.takePicture(testModel.getBirds().get(0), testModel.getBirds());
		assertEquals(true, testModel.getTryAgain());
	}

	// GET SCALED TESTS
	@Test
	public void getScaledWidthTest() {
		int width = 10;
		double number = (double) width;
		double expected = (number / 1440) * testModel.screenWidth;
		width = testModel.getScaledWidth(width);
		assertEquals((int) expected, width);
	}

	@Test
	public void getScaledHeightTest() {
		int height = 10;
		double number = (double) height;
		double expected = (number / 900) * testModel.screenHeight;
		height = testModel.getScaledHeight(height);
		assertEquals((int) expected, height);
	}

	// UPDATE TESTS
	@Test
	public void takePicFrameTest() {
		testModel.takePicFrame = 1;
		testModel.update();
		assertEquals(2, testModel.takePicFrame);
		testModel.update();
		assertEquals(-1, testModel.takePicFrame);
	}

	// ISONTARGET TESTS
	@Test
	public void isOnTargetTest() {
		testModel.getBirds().get(0).getRect().setLocation(500, 500);
		testModel.getCamera().getRect().setLocation(500, 500);
		assertEquals(true, testModel.isOnTarget(testModel.getBirds().get(0)));
	}

	// ISFLASH TESTS
	@Test
	public void isFlashTest() {
		testModel.takePicFrame = 1;
		assertEquals(true, testModel.isFlash());
		testModel.takePicFrame = -1;
		assertEquals(false, testModel.isFlash());
	}

	// GETTERS AND SETTERS TESTS
	@Test
	public void gettersAndSettersTests() {
		BirdWatchingGameModel model = new BirdWatchingGameModel(testView.getScreenWidth(), testView.getScreenHeight());
		model.setGameOver(true);
		assertEquals(true, model.getGameOver());
		model.setTryAgain(true);
		assertEquals(true, model.getTryAgain());
		model.setWrongBird(true);
		assertEquals(true, model.getWrongBird());
		model.setSearchingFor(model.getBirds().get(0));
		assertEquals(model.getBirds().get(0), model.getSearchingFor());
		model.setToDisplayInfo(model.getBirds().get(0));
		assertEquals(model.getBirds().get(0), model.getToDisplayInfo());
		model.setTutorial(true);
		assertEquals(true, model.getTutorial());
	}

}
