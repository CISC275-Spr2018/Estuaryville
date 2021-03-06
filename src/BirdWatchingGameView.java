

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This is a view for the Bird Watching Game. This view is responsible for
 * displaying a visualization of all objects in the model within the user
 * interface.
 * 
 * @author Tyler
 */
public class BirdWatchingGameView {

	int frameCount = 0;
	boolean flash = false;
	public static BufferedImage background, scaledBackground;
	Camera camera;
	public static BufferedImage camera_sprite, arrow_keys, spacebar, p_key, title, tutorial_complete_screen;
	public BufferedImage[] bird_info_screens;
	public BufferedImage[][] bird_sprites;
	public int imgWidths[];
	public int imgHeights[];
	public static int screenWidth, screenHeight;
	public static ArrayList<Bird> birds;
	JFrame frame;
	DrawPanel panel = new DrawPanel();
	Bird searchingFor;
	boolean gameOver;
	boolean wrongBird = false;
	boolean tryAgain = false;
	boolean tutorial = true;
	Bird toDisplayInfo = null;
	Bird tutorialBird = null;
	Camera tutorialCamera = null;

	/**
	 * Creates an instance of the BirdWatchingGameView class. This constructor
	 * creates an array of Birds, creates an instance of a Camera, creates an
	 * instance of a JFrame, and loads the sprites into the view.
	 * 
	 */
	// constructor
	public BirdWatchingGameView() {
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		screenWidth = d.width;
		screenHeight = d.height;
		loadSprites();
		camera = new Camera(screenWidth, screenHeight);
		birds = new ArrayList<Bird>();
		searchingFor = new Bird(Bird.Species.BLUE_HERON, screenWidth, screenHeight);
		tutorialBird = new Bird(Bird.Species.BLUE_HERON, screenWidth, screenHeight);
		tutorialCamera = new Camera(screenWidth, screenHeight);
	}

	/**
	 * This is a class that extends JPanel used to handle painting the objects
	 * and background on the scene of the JPanel
	 * 
	 * @author Tyler
	 */
	@SuppressWarnings("serial")
	public class DrawPanel extends JPanel {
		/**
		 * Overrides the paintComponent method of JPanel. This method paints the
		 * birds, camera, and background onto the JPanel.
		 * 
		 * @param g
		 *            The graphics object used to draw the images
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(scaledBackground, 0, 0, Color.gray, this);
			if (tutorial) {
				// draw instructions
				g.setFont(new Font("Futura", Font.BOLD, getScaledWidth(40)));
				g.drawImage(title, getScaledWidth(700), 0, this);
				g.drawString("Try to take a picture", getScaledWidth(150), getScaledHeight(100));
				g.drawString("of the blue heron below", getScaledWidth(150), getScaledHeight(140));
				g.setFont(new Font("Futura", Font.BOLD, getScaledWidth(30)));
				g.drawImage(arrow_keys, getScaledWidth(800), getScaledHeight(400), this);
				g.drawString("Use arrow keys to move", getScaledWidth(1000), getScaledHeight(500));
				g.drawImage(spacebar, getScaledWidth(775), getScaledHeight(575), this);
				g.drawString("Use spacebar to take a picture", getScaledWidth(1000), getScaledHeight(625));
				g.drawImage(p_key, getScaledWidth(800), getScaledHeight(675), this);
				g.drawString("Use P to pause the game", getScaledWidth(900), getScaledHeight(725));
				// draw tutorial bird
				if (tutorialBird.getDirection().name().contains("WEST"))
					g.drawImage(bird_sprites[tutorialBird.getSpecies().ordinal() * 2][frameCount],
							tutorialBird.getXPos(), tutorialBird.getYPos(), this);
				else
					g.drawImage(bird_sprites[tutorialBird.getSpecies().ordinal() * 2 + 1][frameCount],
							tutorialBird.getXPos(), tutorialBird.getYPos(), this);
				// draw tutorial camera
				g.drawImage(camera_sprite, tutorialCamera.getXPos(), tutorialCamera.getYPos(), this);
				// take picture flash
				if (flash) {
					g.setColor(Color.WHITE);
					g.fillRect(0, 0, scaledBackground.getWidth(), scaledBackground.getHeight());
				}
				if (toDisplayInfo != null) {
					g.drawImage(tutorial_complete_screen, (screenWidth / 2) - (getScaledWidth(900) / 2),
							(screenHeight / 2) - (getScaledHeight(400) / 2), this);
				}
			}

			// tutorial is completed
			else {
				for (int i = 0; i < birds.size(); i++) {
					Bird b = birds.get(i);
					if (b.getDirection().name().contains("WEST"))
						g.drawImage(bird_sprites[b.getSpecies().ordinal() * 2][frameCount], b.getXPos(), b.getYPos(),
								this);
					else
						g.drawImage(bird_sprites[b.getSpecies().ordinal() * 2 + 1][frameCount], b.getXPos(),
								b.getYPos(), this);
				}
				g.drawImage(camera_sprite, camera.getXPos(), camera.getYPos(), this);
				g.setFont(new Font("Futura", Font.BOLD, getScaledWidth(50)));
				// draw target bird
				if (!gameOver) {
					g.drawString("Look for the", getScaledWidth(950), getScaledHeight(45));
					g.drawString(searchingFor.getSpecies().toString().toLowerCase().replaceAll("_", " "),
							getScaledWidth(950), getScaledHeight(95));
					g.drawImage(bird_sprites[searchingFor.getSpecies().ordinal() * 2][0], getScaledWidth(1250),
							getScaledHeight(5), this);
				}
				if (flash) {
					g.setColor(Color.WHITE);
					g.fillRect(0, 0, scaledBackground.getWidth(), scaledBackground.getHeight());
				}
				if (gameOver) {
					g.drawString("Finished!", getScaledWidth(800), getScaledHeight(300));
					g.drawString("Press Enter", getScaledWidth(800), getScaledHeight(350));
					g.drawString("To Continue!", getScaledWidth(800), getScaledHeight(400));
				}
				if (wrongBird) {
					g.drawString("Wrong Bird!", getScaledWidth(800), getScaledHeight(350));
				}
				if (tryAgain) {
					g.drawString("Try Again!", getScaledWidth(800), getScaledHeight(350));
				}
				if (toDisplayInfo != null) {
					g.drawImage(bird_info_screens[toDisplayInfo.getSpecies().ordinal()],
							(screenWidth / 2) - (getScaledWidth(900) / 2),
							(screenHeight / 2) - (getScaledHeight(400) / 2), this);
				}
			}

		}
	}

	/**
	 * Loads the sprites into the view. This method assigns the attributes of
	 * the view class by loading them in from the users directory.
	 */
	private void loadSprites() {
		int numSpecies = Bird.Species.values().length;
		int numDirections = 2; // only east or west 
		try {
			background = ImageIO.read(new File("assets/bird-game/birdwatching-background.png"));
			camera_sprite = ImageIO.read(new File("assets/bird-game/camera.png"));
			title = ImageIO.read(new File("assets/bird-game/bird-title.png"));
			arrow_keys = ImageIO.read(new File("assets/bird-game/key-bindings.png"));
			p_key = ImageIO.read(new File("assets/bird-game/pkey-pic.png"));
			spacebar = ImageIO.read(new File("assets/bird-game/space-pic.png"));
			tutorial_complete_screen = ImageIO.read(new File("assets/bird-game/tutorial-completed-screen.png"));
			Scanner scan = new Scanner(new File("assets/bird-game/info.txt"));
			imgHeights = new int[numSpecies];
			imgWidths = new int[numSpecies];
			bird_sprites = new BufferedImage[numSpecies * numDirections][16]; 
			bird_info_screens = new BufferedImage[numSpecies];
			String nextLine = "";
			for (int i = 0; i < numSpecies; i++) {
				while (!nextLine.contains(Bird.Species.values()[i].getName())) {
					nextLine = scan.next();
					if (nextLine.contains(Bird.Species.values()[i].getName())) {
						imgWidths[i] = scan.nextInt();
						imgHeights[i] = scan.nextInt();
					}
				}
				for (int j = 0; j < numDirections; j++) {
					loadBirdSheet(Bird.Species.values()[i], i, j);
				}
			}
			// load bird info screens
			for (int i = 0; i < numSpecies; i++) {
				loadBirdInfoScreen(Bird.Species.values()[i], i);
			}
			// resize bird info screens
			for (int i = 0; i < numSpecies; i++) {
				bird_info_screens[i] = resizeImg(bird_info_screens[i], getScaledWidth(900), getScaledHeight(400));
			}
			// resize camera for screen
			camera_sprite = resizeImg(camera_sprite, getScaledWidth(320), getScaledHeight(320));
			// resize background for screen
			scaledBackground = resizeImg(background, screenWidth, screenHeight);
			// resize birds for screen
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 16; j++) {
					bird_sprites[i][j] = resizeImg(bird_sprites[i][j], getScaledWidth(imgWidths[i / 2]),
							getScaledHeight(imgHeights[i / 2]));
				}
			}
			// resize tutorial pics for screen
			title = resizeImg(title, getScaledWidth(600), getScaledHeight(300));
			arrow_keys = resizeImg(arrow_keys, getScaledWidth(180), getScaledHeight(180));
			p_key = resizeImg(p_key, getScaledWidth(80), getScaledHeight(80));
			spacebar = resizeImg(spacebar, getScaledWidth(200), getScaledHeight(80));
			tutorial_complete_screen = resizeImg(tutorial_complete_screen, getScaledWidth(900), getScaledHeight(400));

			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads the sprites of a Bird into the bird_sprites array from the users
	 * directory.
	 * 
	 * @param species
	 *            The species of bird to load the sprites for
	 * @param index
	 *            The index of the array to place the bird sprites into
	 * @throws IOException
	 */
	private void loadBirdSheet(Bird.Species species, int index, int isEast) throws IOException {
		String filePath = "assets/bird-game/birds/" + species.getName();
		if (isEast > 0)
			filePath += "-east";
		filePath += "-sheet.png";
		BufferedImage birdSheet = ImageIO.read(new File(filePath));
		int numSubImages = 16;
		bird_sprites[index * 2 + isEast] = new BufferedImage[numSubImages];
		for (int i = 0; i < numSubImages; i++) {
			bird_sprites[index * 2 + isEast][i] = birdSheet.getSubimage(imgWidths[index] * i, 0, imgWidths[index],
					imgHeights[index]);
		}
	}

	/**
	 * Loads the info screen of a Bird into the bird_info_screens array from the
	 * users directory.
	 * 
	 * @param species
	 *            The species of bird to load the screen for
	 * @param index
	 *            The index of the array to place the bird screen into
	 * @throws IOException
	 */
	private void loadBirdInfoScreen(Bird.Species species, int index) throws IOException {
		String filePath = "assets/bird-game/bird-info/" + species.getName() + "-info.png";
		BufferedImage infoScreen = ImageIO.read(new File(filePath));
		bird_info_screens[index] = infoScreen;
	}

	/**
	 * Updates the display of objects within the user interface.
	 * 
	 * @param birdList
	 *            The array of Birds used to update the view
	 * @param c
	 *            The camera used to update the view.
	 * @param f
	 *            a boolean to check for the camera flash
	 * @param b
	 *            a Bird to search for
	 */
	public void update(ArrayList<Bird> birdList, Camera c, boolean f, Bird b) {
		if (tutorial) {
			birds = birdList;
			tutorialCamera = c;
			tutorialBird = birdList.get(0);
			frameCount = (frameCount + 1) % 16;
			flash = f;
			toDisplayInfo = b;
		} else {
			birds = birdList;
			camera = c;
			frameCount = (frameCount + 1) % 16;
			flash = f;
			toDisplayInfo = b;
		}
		panel.repaint();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * creates a copy of a BufferedImage resized with new dimensions.
	 * 
	 * @param img
	 *            the old image
	 * @param newW
	 *            the new width
	 * @param newH
	 *            the new height
	 * @return a BufferedImage of width: newW and height: newH
	 */
	public static BufferedImage resizeImg(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return dimg;
	}

	/**
	 * Gets the scaled width to use for an image.
	 * 
	 * @param n
	 *            The number to use within the scale.
	 * @return An int to use as the scaled width metric.
	 */
	public int getScaledWidth(int n) {
		double number = (double) n;
		double position = (number / background.getWidth()) * screenWidth;
		return (int) position;
	}

	/**
	 * Gets the scaled height to use for an image.
	 * 
	 * @param n
	 *            The number to use within the scale.
	 * @return An int to use as the scaled height metric.
	 */
	public int getScaledHeight(int n) {
		double number = (double) n;
		double position = (number / background.getHeight()) * screenHeight;
		return (int) position;
	}

	/**
	 * Gets the panel of the View.
	 * 
	 * @return A panel.
	 */
	public DrawPanel getPanel() {
		return this.panel;
	}

	/**
	 * Gets the width of the background within the BirdWatchingGameView.
	 * 
	 * @return The width of the background in the view.
	 */
	public int getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Gets the height of the background within the BirdWatchingGameView.
	 * 
	 * @return The height of the background in the view.
	 */
	public int getScreenHeight() {
		return screenHeight;
	}

	/**
	 * Gets the gameOver variable of the View to tell if the game has ended.
	 * 
	 * @return A boolean to tell if the game has ended.
	 */
	public boolean getGameOver() {
		return gameOver;
	}

	/**
	 * Sets the gameOver variable of the View, which tells if the game has
	 * ended.
	 * 
	 * @param g
	 *            The boolean to set the gameOver variable to.
	 */
	public void setGameOver(boolean g) {
		gameOver = g;
	}

	/**
	 * Gets the wrongBird variable of the View to tell if the player has taken a
	 * picture of the wrong bird.
	 * 
	 * @return A boolean to tell if a picture of a wrong bird has been taken.
	 */
	public boolean getWrongBird() {
		return wrongBird;
	}

	/**
	 * Sets the wrongBird variable of the View, which tells if the player has
	 * taken a picture of the wrong bird.
	 * 
	 * @param w
	 *            The boolean to set the wrongBird variable to.
	 */
	public void setWrongBird(boolean w) {
		wrongBird = w;
	}

	/**
	 * Gets the tryAgain variable of the View to tell if the player has taken a
	 * picture of the background.
	 * 
	 * @return A boolean to tell if a picture of the background has been taken.
	 */
	public boolean getTryAgain() {
		return tryAgain;
	}

	/**
	 * Sets the tryAgain variable of the View, which tells if the player has
	 * taken a picture of the background.
	 * 
	 * @param t
	 *            The boolean to set the tryAgain variable to.
	 */
	public void setTryAgain(boolean t) {
		tryAgain = t;
	}

	/**
	 * Gets the searchingFor variable of the View to tell what Bird the player
	 * is searching for.
	 * 
	 * @return A boolean to tell what bird the player is searching for.
	 */
	public Bird getSearchingFor() {
		return searchingFor;
	}

	/**
	 * Sets the searchingFor variable of the View, which tells what Bird the
	 * player is searching for.
	 * 
	 * @param b
	 *            The Bird to set the searchingFor variable to.
	 */
	public void setSearchingFor(Bird b) {
		searchingFor = b;
	}

	/**
	 * Gets the toDisplayInfo variable of the View to tell what Bird to display
	 * the information screen for.
	 * 
	 * @return A boolean to tell what bird to display the information screen
	 *         for.
	 */
	public Bird getToDisplayInfo() {
		return toDisplayInfo;
	}

	/**
	 * Sets the toDisplayInfo variable of the View, which tells what Bird to
	 * display the information screen of.
	 * 
	 * @param b
	 *            The Bird to set the toDisplayInfo variable to.
	 */
	public void setToDisplayInfo(Bird b) {
		toDisplayInfo = b;
	}

	/**
	 * Gets the tutorial variable of the view to tell if the player is within
	 * the tutorial of the game or not.
	 * 
	 * @return A boolean to tell if the player is within the tutorial or not.
	 */
	public boolean getTutorial() {
		return tutorial;
	}

	/**
	 * Sets the tutorial variable of the View, which tells if the player is
	 * within the tutorial of the game or not.
	 * 
	 * @param b
	 *            The Bird to set the tutorial variable to.
	 */
	public void setTutorial(boolean b) {
		tutorial = b;
	}

}
