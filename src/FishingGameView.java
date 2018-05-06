import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


/* 
 * Joel Turk
 */

/**
 * Fishing game view loads all sprites and animations into BufferedImages as well as
 * draws the Model's objects onto a custom JPanel
 * @author Joel
 */
public class FishingGameView extends View{

	final static int WIDTH = 786;//1100;//786;//
	final static int HEIGHT = 500;//500;//
	public static BufferedImage bg; //background image
	public static BufferedImage hook_sprite; //the fishing hook
	public BufferedImage[][] fish_sprites;
	public BufferedImage[] object_sprites; //trash and fish
	public int imgWidths[];
	public int imgHeights[];
	private Fish[] fishes;
	private Hook hook;
	private int frameCount = 0;
	private DrawPanel panel;// = new DrawPanel();
	
	/**
	 * Creates a new FishingGameView with a custom JPanel
	 */
	public FishingGameView(){
		panel = new DrawPanel();
		fishes = new Fish[1];
		fishes[0] = new Fish(Fish.Species.STURGEON, 1, 1, Direction.EAST);
		hook = new Hook();
		loadSprites();
		frame = new JFrame();
		frame.getContentPane().add(panel);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
	}
	
	public class DrawPanel extends JPanel{
		/**
		 * Overrides the getPreferredSize() in JPanel
		 * @return a Dimension for the Frame to initially open
		 */
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(WIDTH, HEIGHT);
		}
		/**
		 * paints all sprites on the screen including fish, trash, hook, background, and fishing line
		 * @param g the Graphics object to paint with
		 */
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(bg, 0, -22, Color.GRAY, this);
			g.drawImage(hook_sprite, hook.getXPos(), hook.getYPos(), this);
			g.setColor(Color.WHITE);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(3));
			int rodX = (int) (((double) 643.0/1100.0) * FishingGameView.getWidth());
			int rodY = (int) (((double)64.0/700.0) * FishingGameView.getHeight());
			int hookX = hook.getXPos()+51;
			int hookY = hook.getYPos()+55;
			int waterSurfaceY = (int) (365.0/700.0 * FishingGameView.HEIGHT);
			int x = 0, y = 0, width = 0, height = 0, startAngle = 0, arcAngle = 0;
			if(hookX < rodX){//hook is left of rod
				x = hookX - (rodX - hookX);
				width = 2 * (rodX - hookX);
				startAngle = 0;
				arcAngle = -90;
			} else {//hook is right of rod
				x = rodX;
				width = 2 * (hookX - rodX);
				startAngle = 180;
				arcAngle = 90;
			}
			//hookY > rodY always**
			y = rodY - (waterSurfaceY - rodY);
			height = 2 * (waterSurfaceY - rodY);
			g2d.drawArc(x, y, width, height, startAngle, arcAngle);//draw arc to water height
			g2d.drawLine(hookX, waterSurfaceY, hookX, hookY);//draw line down to hook
			//g2d.drawLine(rodX, rodY, hookX , hookY);
			for(int i = 0; i < fishes.length; i++){
				Fish f = fishes[i];
				if(f.getDirection().equals(Direction.WEST))
					g.drawImage(fish_sprites[f.getSpecies().ordinal()*2][frameCount], f.getXPos(), f.getYPos(), this);
				else
					g.drawImage(fish_sprites[f.getSpecies().ordinal()*2+1][frameCount], f.getXPos(), f.getYPos(), this);
			}
		}
	}
	
	/**
	 * creates a copy of a BufferedImage resized with new dimensions
	 * @param img the old image
	 * @param newW the new width
	 * @param newH the new height
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
	 * @return the width in pixels
	 */
	public static int getWidth(){return WIDTH;}
	/**
	 * gets the height of the frame
	 * @return the height in pixels
	 */
	public static int getHeight(){return HEIGHT;}
	
	/**
	 * gets the DrawPanel used
	 * @return the DrawPanel panel
	 */
	public DrawPanel getPanel(){return this.panel;}
	
	/**
	 * reads .png files of all needed objects/background into BufferedImages
	 */
	private void loadSprites() {
		int speciesNum = Fish.Species.values().length;
		int dirNum = 2; //Fish can only be East or West
		try {
			bg = ImageIO.read(new File("assets/fishing-game/fishing-background-1.png"));
			bg = resize(bg, FishingGameView.WIDTH, FishingGameView.HEIGHT);
			hook_sprite = ImageIO.read(new File("assets/fishing-game/hook-1.png"));
			hook_sprite = resize(hook_sprite, 100, 100);
			Scanner infoScanner = new Scanner(new File("assets/fishing-game/info.txt"));
			imgWidths = new int[speciesNum];
			imgHeights = new int[speciesNum];
			fish_sprites = new BufferedImage[speciesNum*dirNum][4];
			String nextInfo = "";
			for(int i = 0; i < speciesNum; i++){
				while(!nextInfo.contains(Fish.Species.values()[i].getName())){
					nextInfo = infoScanner.next();
					if(nextInfo.contains(Fish.Species.values()[i].getName())){						
						imgWidths[i] = Integer.parseInt(infoScanner.next());
						imgHeights[i] = Integer.parseInt(infoScanner.next());
					}
				}
				for(int j = 0; j < dirNum; j++){
					loadFishSheet(Fish.Species.values()[i], i, j);
					int counter = 0;
					for(BufferedImage img : fish_sprites[i*dirNum + j]){
						fish_sprites[i*dirNum+j][counter] = resize(img, 100, 100);
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
	 * Puts a fish animation sheet into a row of a 2D array. 
	 * Each row is a different animation and 
	 * each column is a frame in a specific animation
	 * @param species species of fish to load
	 * @param index row of the array to fill
	 * @param eastFlag either a 0 - westward or 1 - eastward facing fish
	 * @throws IOException if the .png file is not able to load properly.
	 */
	private void loadFishSheet(Fish.Species species, int index, int eastFlag) throws IOException{
		String filepath = "assets/fishing-game/fish/"+species.getName();
		if(eastFlag > 0)
			filepath+="-east";
		filepath+="-sheet.png";
		BufferedImage fishSheet = ImageIO.read(new File(filepath));
		fish_sprites[index*2+eastFlag] = new BufferedImage[4];
		for(int i = 0; i < 4; i++){
			fish_sprites[index*2+eastFlag][i] = fishSheet.getSubimage(imgWidths[index] * i, 0, imgWidths[index], imgHeights[index]);
		}
	}
	
	/**
	 * Calls repaint on the custom JPanel and updates which frame of the animations to show
	 * @param fArr array of Fish to display
	 * @param h the current Hook
	 */
	public void update(Fish[] fArr, Hook h) {
		fishes = fArr;
		hook = h;
		frameCount = (frameCount + 1) % 4;
		panel.repaint();
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update() {
	}

}
