import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *   <h1>ResearchGameView Class</h1> Going by the MVC design pattern, this is the View class. It handles all the printing and output to the screen, while getting
 *   everything it needs from outside of this class. This class does not do calculations explicitly, it just prints everything relavent that is happening in the Model.
 *   Includes Animation of Researcher and Crabs <p> 
 *   
 *   @author mattstack
 *   @version beta
 */
public class ResearchGameView{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int frameCount;
	int picNum = 0;
	int CpicNum = 0;
	
	Crab[] crabs;
	
	Crab crab1 = new Crab(-700, -400, 25); //normal
	Crab crab2 = new Crab(-2500, -800, 25); //normal
	Crab crab3 = new Crab(-3500, -800, 20); //slow
	Crab crab4 = new Crab(-2700, -900, 25); //normal
	Crab crab5 = new Crab(-3900, -750, 28); //speedy

	Rectangle[] rects;
	BufferedImage[] pics, images;
	BufferedImage[] Cpics, Cimages;
	BufferedImage bg;
	BufferedImage[][] animation;
	BufferedImage[][] Canimation;
	Researcher player = new Researcher(10, 10, RDirection.IDLE, 3); // intitalizing so doesnt crash until loaded from model
	
	final int playerFixedX = (int)screenSize.getWidth() / 8;
	final int playerFixedY = (int)screenSize.getHeight() / 3;
	
	final int frameWidth = (int) screenSize.getWidth();
	final int frameHeight = (int) screenSize.getHeight();
	final static int imgWidth = 180;
	final static int imgHeight = 180;
	JFrame frame;
	DrawPanel panel = new DrawPanel();
	
	/**
	 * <h1>ResearchGameView Constructor</h1> Creates the frame and loads the panel. Sets the frame size to the automatically generated Dimensions form java toolkit.
	 */
	public ResearchGameView(){
	
		crabs = new Crab[5];
		crabs[0] = crab1;
		crabs[1] = crab2;
		crabs[2] = crab3;
		crabs[3] = crab4;
		crabs[4] = crab5;
		frame = new JFrame();
		loadImages();		
		frame.getContentPane().add(panel);	
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		frame.setBackground(Color.white);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize.getWidth();
		screenSize.getHeight();
		frame.setSize(frameWidth, frameHeight);
//		frame.setSize(1440, 900);
		frame.setVisible(true);	
	}
	
	/**
	 * <h1>getWidth</h1> Returns the width of the frame, set by java toolkit getScreenSize
	 * 
	 * @return frame width
	 */
	public int getWidth(){
		return frameWidth;
	}
	
	/**
	 * <h1>getHeight</h1> Returns the height of the frame, set by java toolkit getScreenSize
	 * 
	 * @return frame height
	 */
	public int getHeight(){
		return frameHeight;
	}
	
	/**
	 * <h1>getImageWidth</h1> Returns the width of the sprites, total size 180 by 180
	 * 
	 * @return image width (180)
	 */
	public int getImageWidth() {
		return imgWidth;
	}
	
	/**
	 * <h1>getImageHeight</h1> Returns the height of the sprites, total size 180 by 180
	 * 
	 * @return image height (180)
	 */
	public int getImageHeight() {
		return imgHeight;
	}
	
	/**
	 * <h1>getFrame</h1> Returns the JFrame frame
	 * 
	 * @return frame
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * <h1>DrawPanel Class</h1> The DrawPanel private class draws all the images and animated sprites to the panel
	 * 
	 * @author mattstack
	 * @version beta
	 */
	@SuppressWarnings("serial")
	private class DrawPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
				g.drawImage(bg, -player.getxPos(), -player.getyPos(), 7000, 2500, this); //background
				
				for (Crab c : crabs) {
					g.drawImage(Cpics[CpicNum], -c.getCrabXPos(), -c.getCrabYPos(), this); //crabs
//					g.drawRect((int)c.getCrabRect().getX(), (int)c.getCrabRect().getY(), (int)c.getCrabRect().getWidth(), (int)c.getCrabRect().getHeight());//crab rects
			}
				g.drawImage(pics[picNum], playerFixedX, playerFixedY, this); //player
//				g.drawRect((int)player.getPlayerRect().getX(), (int)player.getPlayerRect().getY(), (int)player.getPlayerRect().getWidth(), (int)player.getPlayerRect().getHeight());
//				for (Rectangle re : rects) {
//					g.drawRect((int)re.getX(), (int)re.getY(),(int) re.getWidth(), (int)re.getHeight());
//				}

			g.setFont(new Font("Arial", Font.PLAIN, 40));
			g.drawString("Lives: " + player.getLives(), 100, 100);
		}
	}
	
	/**
	 * <h1>loadImages</h1> loadImages combines many methods in this class like createImage and loadImg. loadImage is the highest part of the procedure to load images
	 */
	private void loadImages(){
		images = new BufferedImage[RDirection.values().length];
		animation = new BufferedImage[RDirection.values().length][0];
		for(int i = 0; i < RDirection.values().length; i++) {
			images[i] = createImage(RDirection.values()[i]);
			animation[i] = new BufferedImage[images[i].getWidth()/imgWidth];
			loadAnimatedImg(images[i],animation[i],animation[i].length);
		}
		Cimages = new BufferedImage[5];
		Canimation = new BufferedImage[5][0];
		for(int i = 0; i < 5; i++) {
			Cimages[i] = createCrabImage();
			Canimation[i] = new BufferedImage[Cimages[i].getWidth()/imgWidth];
			loadAnimatedImg(Cimages[i],Canimation[i],2);
		}
		Cpics = Canimation[0];
		pics = animation[RDirection.IDLE.ordinal()];
	}
	
/**
 * <h1>loadAnimatedImg</h1> loadAnimatedImg loads the sub images of the sprite sheets into a buffered image array, a key componenet of sprite sheet animation
 * 
 * @param img the larger sprite sheet
 * @param an the new array with the filled with the correct number of subimages that were within the sheet
 * @param frames the number of frames for the animation, or how many sub images where inside the sheet
 */
	private void loadAnimatedImg(BufferedImage img, BufferedImage[] an ,int frames){
		for(int i = 0; i < frames; i++){
			an[i] = img.getSubimage(imgWidth * i, 0, imgWidth, imgHeight);
		}
	}
	
	/**
	 * <h1>createImage</h1> create the BufferedImage for the Researcher from the path to assets, completed by by the input direction which uses its Enum string to load all the directions.
	 * 
	 * @param direction used to complete the path to the image in the asset folder
	 * @return the bufferedImage with the newly create character image from path
	 */
	private BufferedImage createImage(RDirection direction) {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("assets/research-game/female-scientist-" + direction.getName() + ".png"));
			bg = ImageIO.read(new File("assets/research-game/research-background" + ".png"));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * <h1>createCrabImage</h1> create the BufferedImage for the crab from the path to assets
	 * @return the bufferedImage with the newly create crab image from path
	 */
	private BufferedImage createCrabImage() {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("assets/research-game/crab-sheet.png"));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * <h1>update</h1> Update is the method that is fed by the select inputs from the model. It takes everything that changes from the model and paints it time after time.
	 * Pulls in the current player, list of crabs, and list of out-of-bounds rectangles to paint. Also it calse panel.repaint() which will call the paint method as soon as the 
	 * computer is ready
	 * @param player pulls player from model, this is the most current player state with everything needed like xPos/yPos and Rectangle and everything. 
	 * @param crabs pulled in from the model for the most current state of all the crabs in the crabs list, accompanied by all the methods needed to get crabs methods
	 * @param rects pulls in from model, the most current state of the rectangle
	 */
	public void update(Researcher player, Crab[] crabs, Rectangle[] rects){
		this.player = player;
		this.crabs = crabs;	
		this.rects = rects;
		switch(player.getDirection()){
			case IDLE:	
				frameCount = animation[RDirection.IDLE.ordinal()].length;	
				pics = animation[RDirection.IDLE.ordinal()];
				break;
			case EAST:
				frameCount = animation[RDirection.EAST.ordinal()].length;	
				pics = animation[RDirection.EAST.ordinal()];
				break;
			case NORTHEAST:
				frameCount = animation[RDirection.NORTHEAST.ordinal()].length;	
				pics = animation[RDirection.NORTHEAST.ordinal()];
				break;
			case SOUTHEAST:
				frameCount = animation[RDirection.SOUTHEAST.ordinal()].length;	
				pics = animation[RDirection.SOUTHEAST.ordinal()];
				break;

		}
		picNum = (picNum + 1) % frameCount;
		CpicNum = (CpicNum + 1) % 2;
		panel.repaint();
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return panel;
	}
}
