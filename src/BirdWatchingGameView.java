
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
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This is a view for the Bird Watching Game. This view is responsible for 
 * displaying a visualization of all objects in the model within the
 * user interface. 
 * @author Tyler
 * @see #Bird
 */
public class BirdWatchingGameView extends View{

	int frameCount = 0;
	boolean flash = false;
	public static BufferedImage background, scaledBackground;
	Camera camera;
	public static BufferedImage camera_sprite;
	public BufferedImage[] all_sprites;
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
	
	/**
	 * Creates an instance of the BirdWatchingGameView class. This constructor creates
	 * an array of Birds, creates an instance of a Camera, creates an instance of a 
	 * JFrame, and loads the sprites into the view. 
	 * 
	 */
	//constructor
	public BirdWatchingGameView(){
		frame = new JFrame();
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension d = tk.getScreenSize();
	    screenWidth = d.width;
	    screenHeight = d.height;
	    frame.setSize(d.width, d.height);
		loadSprites();
	    //frame.setSize(background.getWidth(), background.getHeight());
		frame.setVisible(true);
		camera = new Camera(screenWidth, screenHeight); 
	    birds = new ArrayList<Bird>();
	}
	
	/**
	 * This is a class that extends JPanel used to handle painting the objects 
	 * and background on the scene of the JPanel
	 * @author Tyler
	 * @see #JPanel
	 */
	@SuppressWarnings("serial")
	public class DrawPanel extends JPanel{
		/**
		 * Paints the birds, camera, and background onto the JPanel.
		 * @param g The graphics object used to draw the images
		 * @see #Graphics
		 */
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			//resizeImg(background, frame.getWidth(), frame.getHeight());
			g.drawImage(scaledBackground, 0, 0, Color.gray, this);
			//System.out.println(frameCount);
			for (int i = 0; i < birds.size(); i++) {
				Bird b = birds.get(i);
				//if(b.getDirection().equals(Direction.WEST))
					g.drawImage(bird_sprites[b.species.ordinal()][frameCount], b.getXPos(), b.getYPos(), this);
				//else
					//g.drawImage(bird_sprites[b.species.ordinal()*2+1][frameCount], b.getXPos(), b.getYPos(), this);
			}
			//draw target bird
			g.setFont(new Font("Futura", Font.BOLD, getScaledWidth(50)));
			g.drawString("Look for the", getScaledWidth(950), getScaledHeight(45));
			g.drawString(searchingFor.species.toString().toLowerCase().replaceAll("_", " "), getScaledWidth(950), getScaledHeight(95));
			g.drawImage(bird_sprites[searchingFor.species.ordinal()][0], getScaledWidth(1250), getScaledHeight(5), this);
			g.drawImage(camera_sprite, camera.getXPos(), camera.getYPos(), this);
			if(flash) {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, scaledBackground.getWidth(), scaledBackground.getHeight());
			}
			if (gameOver) {
				g.drawString("Finished!", getScaledWidth(800), getScaledHeight(350));
				g.drawString("Press Enter to continue", screenWidth / 2, getScaledHeight(400));
			}
			if (wrongBird) {
				g.drawString("Wrong bird!", getScaledWidth(800), getScaledHeight(350));
			}
			if (tryAgain) {
				g.drawString("Try again!", getScaledWidth(800), getScaledHeight(350));
			}
			
		}
	}
	
	/**
	 * Loads the sprites into the view. This method assigns the attributes of the
	 * view class by loading them in from the users directory. 
	 */
	private void loadSprites() {
		int numSpecies = Bird.Species.values().length;
		int numDirections = 2; //only east or west //ADD EAST DIRECTIONS LATER
		try {
			background = ImageIO.read(new File("assets/bird-game/birdwatching-background.png"));
			camera_sprite = ImageIO.read(new File("assets/bird-game/camera.png"));
			Scanner scan = new Scanner(new File("assets/bird-game/info.txt"));
			imgHeights = new int[numSpecies];
			imgWidths = new int[numSpecies];
			bird_sprites = new BufferedImage[numSpecies][]; //MULTIPLY NUMSPECIES BY NUMDIR
			String nextLine = "";
			for (int i = 0; i < numSpecies; i++) {
				while (!nextLine.contains(Bird.Species.values()[i].name)) {
					nextLine = scan.next();
					if (nextLine.contains(Bird.Species.values()[i].name)) {
						imgWidths[i] = scan.nextInt();
						imgHeights[i] = scan.nextInt();
					}
				}
				loadBirdSheet(Bird.Species.values()[i], i);
			}
			//resize camera for screen
			camera_sprite = resizeImg(camera_sprite, getScaledWidth(320), getScaledHeight(320));
			//resize background for screen
			scaledBackground = resizeImg(background, screenWidth, screenHeight);
			//resize birds for screen
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 16; j++) {
				//if(b.getDirection().equals(Direction.WEST))
					bird_sprites[i][j] = resizeImg(bird_sprites[i][j], getScaledWidth(imgWidths[i]), getScaledHeight(imgHeights[i]));
				//else
					//g.drawImage(bird_sprites[b.species.ordinal()*2+1][frameCount], b.getXPos(), b.getYPos(), this);
				}
			}
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the sprites of a Bird into the bird_sprites array from the users
	 * directory. 
	 * @param species The species of bird to load the sprites for
	 * @param index The index of the array to place the bird sprites into
	 * @throws IOException
	 */
	private void loadBirdSheet(Bird.Species species, int index) throws IOException{
		BufferedImage birdSheet = ImageIO.read(new File("assets/bird-game/birds/"
					+species.name+"-sheet.png"));
		int numSubImages = 16;	
		bird_sprites[index] = new BufferedImage[numSubImages];
		for(int i = 0; i < numSubImages; i++){
			for (int j = 0; j < imgWidths.length; j++) {
			}
			//System.out.println(imgWidths[index] * i + imgWidths[index]);
			bird_sprites[index][i] = birdSheet.getSubimage(imgWidths[index] * i, 0, imgWidths[index], imgHeights[index]);
		}
	}
	
	/**
	 * Gets the array of sprites within the BirdWatchingGameView
	 * @return the array of sprites
	 */
	public BufferedImage[] getSprites(){
		return all_sprites;		
	}
	
	/**
	 * Updates the display of objects within the user interface.
	 * @param birdArray The array of Birds used to update the view
	 * @param c The camera used to update the view. 
	 */
	public void update(ArrayList<Bird> birdList, Camera c, boolean f) {
		birds = birdList;
		camera = c;
		frameCount = (frameCount + 1) % 16;
		flash = f;
		panel.repaint();
		try {
			Thread.sleep(80);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the width of the background within the BirdWatchingGameView.
	 * @return The width of the background in the view.
	 */
	public static int getScreenWidth(){
		return screenWidth;
	}
	/**
	 * Gets the height of the background within the BirdWatchingGameView.
	 * @return The height of the background in the view.
	 */
	public static int getScreenHeight(){
		return screenHeight;
	}
	/**
	 * Gets the frame being used in the BirdWatchingGameView.
	 * @return The frame in the view.
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public static BufferedImage resizeImg(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    return dimg;
	}  
	
	public int getScaledWidth(int n) {
		double number = (double)n;
		double position = (number / background.getWidth()) * screenWidth;
		return (int) position;
	}
	
	public int getScaledHeight(int n) {
		double number = (double)n;
		double position = (number / background.getHeight()) * screenHeight;
		return (int) position;
	}
	
	public DrawPanel getPanel() {
		return this.panel;
	}
}
