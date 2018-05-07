import java.awt.BasicStroke;
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

/* 
 * Joel Turk
 */

/**
 * Fishing game view loads all sprites and animations into BufferedImages as
 * well as draws the Model's objects onto a custom JPanel
 * 
 * @author Joel
 */
public class FishingGameView{
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final static int WIDTH = screenSize.width;// 1100;//786;//
	final static int HEIGHT = screenSize.height; // 500;//
	public static BufferedImage bg; // background image
	public static BufferedImage hook_sprite; // the fishing hook
	final static double SCALE = (FishingGameView.WIDTH / 1280.0);
	final static double SCALE_Y = (FishingGameView.HEIGHT / 800.0);
	final static int HOOK_WIDTH = (int) (15 * SCALE);
	final static int HOOK_HEIGHT = (int) (21 * SCALE);
	final static int FISH_WIDTH = (int) (100 * SCALE);
	final static int FISH_HEIGHT = (int) (100 * SCALE_Y);
	final static int ROD_X = (int) (748 * SCALE);
	final static int ROD_Y = (int) (73 * SCALE_Y);
	public BufferedImage[][] fish_sprites;
	public BufferedImage[] object_sprites; // trash and fish
	public int imgWidths[];
	public int imgHeights[];
	private ArrayList<Fish> fishes;
	private Hook hook;
	private int frameCount = 0;
	private DrawPanel panel;// = new DrawPanel();
	boolean reeling;
	boolean gameOver;
	boolean displayCatch;
	Fish recentlyCaught;

	/**
	 * Creates a new FishingGameView with a custom JPanel
	 */
	public FishingGameView() {
		panel = new DrawPanel();
		fishes = new ArrayList<Fish>();
		fishes.add(new Fish(Fish.Species.STURGEON, 1, 1, Direction.EAST));
		hook = new Hook();
		loadSprites();
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		reeling = false;
		gameOver = false;
	}

	@SuppressWarnings("serial")
	public class DrawPanel extends JPanel {
		/**
		 * Overrides the getPreferredSize() in JPanel
		 * 
		 * @return a Dimension for the Frame to initially open
		 */
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(WIDTH, HEIGHT);
		}

		/**
		 * paints all sprites on the screen including fish, trash, hook,
		 * background, and fishing line
		 * 
		 * @param g
		 *            the Graphics object to paint with
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bg, 0, (int) (-22.0 / 700.0 * FishingGameView.HEIGHT), Color.GRAY, this);
			g.drawImage(hook_sprite, hook.getXPos(), hook.getYPos(), this);
			g.setColor(Color.WHITE);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(3));
			int hookX = hook.getXPos() + (HOOK_WIDTH * 2 / 3);
			int hookY = hook.getYPos();
			int waterSurfaceY = (int) (365.0 / 700.0 * FishingGameView.HEIGHT);
			int x = 0, y = 0, width = 0, height = 0, startAngle = 0, arcAngle = 0;
			if (!reeling) {
				if (displayCatch) {
					g.setFont(new Font("Futura", Font.BOLD, 50));
					String catchPhrase = "You caught a";
					if(recentlyCaught.getSpecies().getName().toLowerCase().startsWith("a"))
						catchPhrase += "n ";
					else 
						catchPhrase += " ";
					catchPhrase += capitalize(recentlyCaught.getSpecies().getName().toLowerCase().replaceAll("-", " "))
					+ "!";
					g.drawString(catchPhrase,
								FishingGameView.WIDTH / 2 - 350, FishingGameView.HEIGHT / 2);
					g2d.drawLine(ROD_X, ROD_Y, hookX, hookY);
				} else {
					if (hookX < ROD_X) {/* hook is left of rod */

						x = hookX - (ROD_X - hookX);
						width = 2 * (ROD_X - hookX);
						startAngle = 0;
						arcAngle = -90;
					} else {/* hook is right of rod */
						x = ROD_X;
						width = 2 * (hookX - ROD_X);
						startAngle = 180;
						arcAngle = 90;
					}
					// hookY > rodY always**
					y = ROD_Y - (waterSurfaceY - ROD_Y);
					height = 2 * (waterSurfaceY - ROD_Y);
					g2d.drawArc(x, y, width, height, startAngle,
							arcAngle); /* draw arc to water height */
					g2d.drawLine(hookX, waterSurfaceY, hookX,
							hookY);/* draw line down to hook */
				}
			} else {
				g2d.drawLine(ROD_X, ROD_Y, hookX, hookY);
			}
			for (int i = 0; i < fishes.size(); i++) {
				Fish f = fishes.get(i);
				if (f.getHooked()) {
					System.out.println("The " + f.getSpecies().getName() + " is HOOKED");
					recentlyCaught = f;
				}
				 //g.drawRect(f.getMouth().x, f.getMouth().y,
				 //f.getMouth().width, f.getMouth().height);
				 //g.drawRect(hook.getHitbox().x, hook.getHitbox().y,
				 //hook.getHitbox().width, hook.getHitbox().height);
				if (f.getDirection().equals(Direction.WEST))
					g.drawImage(fish_sprites[f.getSpecies().ordinal() * 2][frameCount], f.getXPos(), f.getYPos(), this);
				else
					g.drawImage(fish_sprites[f.getSpecies().ordinal() * 2 + 1][frameCount], f.getXPos(), f.getYPos(),
							this);
			}
			if(!reeling && displayCatch)
				g.drawImage(fish_sprites[recentlyCaught.getSpecies().ordinal() * 2 + 1][0],
						FishingGameView.WIDTH / 2 - 100, FishingGameView.HEIGHT / 2, 200, 200, this);
			if (gameOver && !displayCatch) {
				g.setFont(new Font("Futura", Font.BOLD, 50));
				g.drawString("You've caught all the fish. Press Enter to continue", 125, FishingGameView.HEIGHT / 2);
			}
		}
	}

	/**
	 * creates a copy of a BufferedImage resized with new dimensions
	 * 
	 * @param img
	 *            the old image
	 * @param newW
	 *            the new width
	 * @param newH
	 *            the new height
	 * @return a BufferedImage of width: newW and height: newH
	 */
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	/**
	 * gets the width of the frame
	 * 
	 * @return the width in pixels
	 */
	public static int getWidth() {
		return FishingGameView.WIDTH;
	}

	/**
	 * gets the height of the frame
	 * 
	 * @return the height in pixels
	 */
	public static int getHeight() {
		return FishingGameView.HEIGHT;
	}

	/**
	 * gets the DrawPanel used
	 * 
	 * @return the DrawPanel panel
	 */
	public DrawPanel getPanel() {
		return this.panel;
	}

	/**
	 * reads .png files of all needed objects/background into BufferedImages
	 */
	private void loadSprites() {
		int speciesNum = Fish.Species.values().length;
		int dirNum = 2; // Fish can only be East or West
		try {
			bg = ImageIO.read(new File("assets/fishing-game/fishing-background-1.png"));
			bg = resize(bg, FishingGameView.WIDTH, FishingGameView.HEIGHT);
			hook_sprite = ImageIO.read(new File("assets/fishing-game/hook.png"));
			hook_sprite = resize(hook_sprite, (int) (HOOK_WIDTH * SCALE), (int) (HOOK_HEIGHT * SCALE_Y));
			Scanner infoScanner = new Scanner(new File("assets/fishing-game/info.txt"));
			imgWidths = new int[speciesNum];
			imgHeights = new int[speciesNum];
			fish_sprites = new BufferedImage[speciesNum * dirNum][4];
			String nextInfo = "";
			for (int i = 0; i < speciesNum; i++) {
				while (!nextInfo.contains(Fish.Species.values()[i].getName())) {
					nextInfo = infoScanner.next();
					if (nextInfo.contains(Fish.Species.values()[i].getName())) {
						imgWidths[i] = Integer.parseInt(infoScanner.next());
						imgHeights[i] = Integer.parseInt(infoScanner.next());
					}
				}
				for (int j = 0; j < dirNum; j++) {
					loadFishSheet(Fish.Species.values()[i], i, j);
					int counter = 0;
					for (BufferedImage img : fish_sprites[i * dirNum + j]) {
						fish_sprites[i * dirNum + j][counter] = resize(img, FISH_WIDTH, FISH_HEIGHT);
						counter++;
					}
				}
			}
			infoScanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Puts a fish animation sheet into a row of a 2D array. Each row is a
	 * different animation and each column is a frame in a specific animation
	 * 
	 * @param species
	 *            species of fish to load
	 * @param index
	 *            row of the array to fill
	 * @param eastFlag
	 *            either a 0 - westward or 1 - eastward facing fish
	 * @throws IOException
	 *             if the .png file is not able to load properly.
	 */
	private void loadFishSheet(Fish.Species species, int index, int eastFlag) throws IOException {
		String filepath = "assets/fishing-game/fish/" + species.getName();
		if (eastFlag > 0)
			filepath += "-east";
		filepath += "-sheet.png";
		BufferedImage fishSheet = ImageIO.read(new File(filepath));
		fish_sprites[index * 2 + eastFlag] = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			fish_sprites[index * 2 + eastFlag][i] = fishSheet.getSubimage(imgWidths[index] * i, 0, imgWidths[index],
					imgHeights[index]);
		}
	}

	/**
	 * Calls repaint on the custom JPanel and updates which frame of the
	 * animations to show
	 * 
	 * @param fArr
	 *            array of Fish to display
	 * @param h
	 *            the current Hook
	 */
	public void update(ArrayList<Fish> fish, Hook h, Fish hooked, boolean done, boolean show) {
		fishes = fish;
		hook = h;
		if (hooked != null)
			reeling = true;
		else
			reeling = false;
		gameOver = done;
		displayCatch = show;
		frameCount = (frameCount + 1) % 4;
		panel.repaint();
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Takes a string a capitalizes every space-separated word
	 * i.e. "a man with a plan."	-->		"A Man With A Plan."
	 * @param str the initial string
	 * @return a String with each word capitalized
	 */
	private String capitalize(String str){
		String [] line = str.split(" ");
		String result = "";
		int count = 0;
		for(String s : line){
			result += s.substring(0,1).toUpperCase() + s.substring(1);
			count++;
			if(count < line.length)
				result += " ";
		}
		return result;
	}

}
