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
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainView {
	//width/height constants
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final static int FRAME_WIDTH =  screenSize.width;
	final static int FRAME_HEIGHT = screenSize.height;
	//static int FRAME_WIDTH = 1900;
	//static int FRAME_HEIGHT = 1000;
	final static int SIDEBAR_WIDTH = (int) (FRAME_WIDTH*0.2);
	final static int SIDEBAR_HEIGHT = FRAME_HEIGHT;
	final static int MAP_WIDTH = FRAME_WIDTH-SIDEBAR_WIDTH;
	final static int MAP_HEIGHT = FRAME_HEIGHT;
	final static int BAR_WIDTH = (int) (FRAME_WIDTH*0.01);
	final static int BAR_HEIGHT = (int) (FRAME_HEIGHT*0.35);
	//sidebar button constants
	final static int BUILDING_BUTTON_HEIGHT = (int) (SIDEBAR_HEIGHT*0.1);
	final static int BUILDING_BUTTON_WIDTH = (int) (SIDEBAR_WIDTH*0.5);
	final static int BUILDING_BUTTON_X = (int) (FRAME_WIDTH-SIDEBAR_WIDTH+(BUILDING_BUTTON_WIDTH*0.75));
	final static int BUILDING_BUTTON_Y = (int) (SIDEBAR_HEIGHT-(SIDEBAR_HEIGHT*0.95));
	final static int BUILDING_BUTTON_Y_OFFSET = (int) (BUILDING_BUTTON_HEIGHT * 1.25);
	final static int BUILDING_ICON_WIDTH = (int) (SIDEBAR_HEIGHT*0.05);
	final static int BUILDING_ICON_HEIGHT = BUILDING_ICON_WIDTH;
	//map button constants
	final static int NUM_MAP_BUTTONS_X = 10;
	final static int NUM_MAP_BUTTONS_Y_CALC = 11;//FOR WIDTH/HEIGHT CALCULATIONS ONLY
	final static int NUM_MAP_BUTTONS_Y = 10;
	final static int MAP_BUTTON_WIDTH = MAP_WIDTH/NUM_MAP_BUTTONS_X;
	final static int MAP_BUTTON_HEIGHT = MAP_HEIGHT/NUM_MAP_BUTTONS_Y_CALC;
	final static int BORDER_WIDTH_X = MAP_BUTTON_WIDTH/2;
	final static int BORDER_WIDTH_Y = MAP_BUTTON_HEIGHT/4;
	final static int MAP_BUTTON_X = BORDER_WIDTH_X;
	final static int MAP_BUTTON_Y = BORDER_WIDTH_Y;
	final static int MAP_BUTTON_X_OFFSET = MAP_BUTTON_WIDTH;
	final static int MAP_BUTTON_Y_OFFSET = MAP_BUTTON_HEIGHT;
	//Pollution/Money Bar constants
	final static int BAR_X = (int) (FRAME_WIDTH*0.01);
	final static int BAR_Y = (int) (FRAME_HEIGHT*0.075);
	final static int BAR_Y_OFFSET = (int) (BAR_HEIGHT+FRAME_HEIGHT*0.1);
	final static int BAR_ROUND = 20;
	//other constants
	//globals
	private JFrame frame;
	private DrawPanel panel;
	private JLayeredPane mainPanel;
	private boolean gameOver;
	final String[] buildingNames = {"Port","Bird Watching Tower","Factory","Research Station","Fishing Pier"};
	private MapSpot[][] board = new MapSpot[NUM_MAP_BUTTONS_X][NUM_MAP_BUTTONS_Y];
	private HashMap<String,JButton> sidebarButtons = new HashMap<String,JButton>();
	
	//setters/getters
	/**
	 * Returns the JFrame being displayed.
	 * @return the JFrame being displayed.
	 */
	public JFrame getFrame() {
		return frame;
	}
	/**
	 * Returns the LayeredPanel every JPanel is added to.
	 * @return the main LayeredPanel.
	 */
	public JLayeredPane getMainPanel() {
		return mainPanel;
	}
	/**
	 * Returns the MainScreen Panel.
	 * @return the MainScreen Panel.
	 */
	public DrawPanel getPanel() {
		return panel;
	}
	/**
	 * Returns the names of all the buildings.
	 * @return the names of all the buildings.
	 */
	public String[] getBuildingNames() {
		return buildingNames;
	}
	/**
	 * Returns the grid of MapSpots.
	 * @return the grid of MapSpots.
	 */
	public MapSpot[][] getBoard() {
		return board;
	}
	/**
	 * Returns the buttons on the side bar.
	 * @return the buttons on the side bar.
	 */
	public HashMap<String, JButton> getSidebarButtons() {
		return sidebarButtons;
	}
	
	/**
	 * The panel that draws the everything onto the Frame
	 * @author Riley
	 *
	 */
	@SuppressWarnings("serial")
	public class DrawPanel extends JPanel{
		private int xPos;
		private int yPos;
		private int yOffset;
		
		private double moneyRatio = 0;
		private double pollutionRatio = 0;
		/**
		 * Sets the moneyRatio 
		 * @param moneyRatio the ratio to set.
		 */
		public void setMoneyRatio(double moneyRatio) {
			if(moneyRatio <= 1)
				this.moneyRatio = moneyRatio;
		}
		/**
		 * Sets the pollution Ratio
		 * @param pollutionRatio the ratio to set.
		 */
		public void setPollutionRatio(double pollutionRatio) {
			if(pollutionRatio <= 1)
				this.pollutionRatio = pollutionRatio;
		}
		/**
		 * Creates a new instance of DrawPanel
		 * @param x the x position of DrawPanel
		 * @param y the y position of DrawPanel
		 * @param yOffset the vertical Offset to have a border
		 */
		public DrawPanel(int x, int y, int yOffset) {
			xPos = x;
			yPos = y;
			this.yOffset = yOffset;
		}
		
		/**
		 * Drawing the information on Frame.
		 * @param g The Graphics used to draw.
		 */
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			setBackground(new Color(100, 153, 239));
			
			Graphics2D g2d = (Graphics2D) g;
			float thickness = 2;
			g2d.setStroke(new BasicStroke(thickness));
			g2d.setColor(Color.BLACK);
			g2d.drawRoundRect(xPos, yPos, BAR_WIDTH, BAR_HEIGHT,BAR_ROUND,BAR_ROUND);
			g2d.drawRoundRect(xPos, yPos+yOffset, BAR_WIDTH, BAR_HEIGHT,BAR_ROUND,BAR_ROUND);
			g2d.setColor(Color.YELLOW);
			g2d.fillRoundRect(xPos, (int) (yPos+(BAR_HEIGHT*(1-moneyRatio))), BAR_WIDTH, (int) (BAR_HEIGHT - (BAR_HEIGHT*(1-moneyRatio))),BAR_ROUND,BAR_ROUND);
			g2d.setColor(Color.GREEN);
			g2d.fillRoundRect(xPos, (int) (yPos+yOffset+(BAR_HEIGHT*(1-pollutionRatio))), BAR_WIDTH, (int) (BAR_HEIGHT - (BAR_HEIGHT*(1-pollutionRatio))),BAR_ROUND,BAR_ROUND);
			
			g2d.setColor(Color.BLACK);
			g2d.drawString(String.format("$%.2f",moneyRatio*1000), BAR_X, BAR_Y-25);
			g2d.drawString("Pollution:\n", BAR_X, BAR_Y+BAR_Y_OFFSET-25);
			g2d.drawString(String.format("%.2f",pollutionRatio*10000), BAR_X, BAR_Y+BAR_Y_OFFSET-10);			
		}
	}
	/**
	 * Creates a new instance of MainView.
	 */
	public MainView(){
		frame = new JFrame();
		
		panel = new DrawPanel(BAR_X, BAR_Y, BAR_Y_OFFSET);
		panel.setLayout(null);
		panel.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
		mainPanel = new JLayeredPane();
		mainPanel.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
		
		addSidebar();
		addMainMap();
		
		frame.setTitle("EstuaryVille");
		frame.getContentPane().add(mainPanel);
		frame.setBackground(new Color(100, 153, 239));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
		frame.setResizable(false);
		gameOver = false;
	}
	/**
	 * Creates side bar buttons and adds them to the panel.
	 */
	public void addSidebar() {
		ImageIcon[] sidebarImages = loadSidebarImages();
		for(int i = 0; i < buildingNames.length; i++) {
			JButton button = new JButton(buildingNames[i]);//new JButton(buildingNames[i]);
			button.setBounds(BUILDING_BUTTON_X,BUILDING_BUTTON_Y+(i*BUILDING_BUTTON_Y_OFFSET), BUILDING_BUTTON_WIDTH, BUILDING_BUTTON_HEIGHT);

			button.setIcon(sidebarImages[i]);
			button.setVerticalTextPosition(SwingConstants.BOTTOM);
			button.setHorizontalTextPosition(SwingConstants.CENTER);
			button.setRolloverEnabled(false);
			panel.add(button);
			sidebarButtons.put(buildingNames[i], button);
		}
		JButton button = new JButton("Remove");
		button.setBounds(BUILDING_BUTTON_X,BUILDING_BUTTON_Y+(buildingNames.length*BUILDING_BUTTON_Y_OFFSET)+BUILDING_BUTTON_Y_OFFSET, BUILDING_BUTTON_WIDTH, BUILDING_BUTTON_HEIGHT);
		button.setRolloverEnabled(false);
		panel.add(button);
		sidebarButtons.put("Remove", button);
	}
	
	/**
	 * Loads the images for the side bar buttons.
	 * @return An array of Images to use for the side bar buttons.
	 */
	public ImageIcon[] loadSidebarImages() {
		BufferedImage[] bImgs = new BufferedImage[buildingNames.length];
		ImageIcon[] imgs = new ImageIcon[buildingNames.length];
				
		try {
			bImgs[0] = ImageIO.read(new File("assets/main-screen/buildings/port.png"));
			bImgs[1] = ImageIO.read(new File("assets/main-screen/buildings/bird-tower.png"));
			bImgs[2] = ImageIO.read(new File("assets/main-screen/buildings/factory.png"));
			bImgs[3] = ImageIO.read(new File("assets/main-screen/buildings/research.png"));
			bImgs[4] = ImageIO.read(new File("assets/main-screen/buildings/pier.png"));
			
			imgs[0] = new ImageIcon(resize(bImgs[0],BUILDING_ICON_WIDTH,BUILDING_ICON_HEIGHT));
			imgs[1] = new ImageIcon(resize(bImgs[1],BUILDING_ICON_WIDTH,BUILDING_ICON_HEIGHT));
			imgs[2] = new ImageIcon(resize(bImgs[2],BUILDING_ICON_WIDTH,BUILDING_ICON_HEIGHT));
			imgs[3] = new ImageIcon(resize(bImgs[3],BUILDING_ICON_WIDTH,BUILDING_ICON_HEIGHT));
			imgs[4] = new ImageIcon(resize(bImgs[4],BUILDING_ICON_WIDTH,BUILDING_ICON_HEIGHT));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgs;
	}
	/**
	 * Resizes button images to a new width and height.
	 * @param img the image to resize.
	 * @param newW the new width of the image.
	 * @param newH the new height of the image.
	 * @return
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
	 * Creates and adds the main grid of MapSpots to the panel.
	 */
	public void addMainMap() {
		ImageIcon[][] backgroundImages = loadBackgroundImages();
		for(int i = 0; i < NUM_MAP_BUTTONS_X; i++) {
			for(int j = 0; j < NUM_MAP_BUTTONS_Y; j++) {
				JButton button = new JButton();
				button.setBounds(MAP_BUTTON_X+(i*MAP_BUTTON_X_OFFSET), MAP_BUTTON_Y+(j*MAP_BUTTON_Y_OFFSET), MAP_BUTTON_WIDTH, MAP_BUTTON_HEIGHT);
				button.setHorizontalTextPosition(SwingConstants.CENTER);
				button.setRolloverEnabled(false);
				panel.add(button);
				TerrainState state = TerrainState.OTHER;
				switch(i) {
				case 0:
					if(0 <= j && j <= 3) {
						state = TerrainState.OCEAN;
					}
					else if (4 <= j && j <= 8) {
						state = TerrainState.BEACH;
					}
					else {
						state = TerrainState.FOREST;
					}
					break;
				case 1:
					if(0 <= j && j <= 1) {
						state = TerrainState.OCEAN;
					}
					else if (2 <= j && j <= 3) {
						state = TerrainState.BEACH;
					}
					else if (4 <= j && j <= 7) {
						state = TerrainState.NORMAL;
					}
					else {
						state = TerrainState.FOREST;
					}
					break;
				case 2:
					if(0 <= j && j <= 1) {
						state = TerrainState.OCEAN;
					}
					else if (2 <= j && j <= 6) {
						state = TerrainState.BEACH;
					}
					else {
						state = TerrainState.FOREST;
					}
					break;
				case 3:
					if(0 == j) {
						state = TerrainState.BEACH;
					}
					else if (j == 1) {
						state = TerrainState.RIVER;
					}
					else {
						state = TerrainState.NORMAL;
					}
					break;
				case 4:
				case 5:
					if (j == 1) {
						state = TerrainState.RIVER;
					}
					else {
						state = TerrainState.NORMAL;
					}
					break;
				case 6:
				case 7:
					if (j == 2) {
						state = TerrainState.RIVER;
					}
					else {
						state = TerrainState.NORMAL;
					}
					break;
				case 8:
					if (j == 3 || j == 4) {
						state = TerrainState.RIVER;
					}
					else {
						state = TerrainState.NORMAL;
					}
					break;
				case 9:
					if(j == 0) {
						state = TerrainState.FOREST;
					}
					else if (j == 3) {
						state = TerrainState.RIVER;
					}
					else {
						state = TerrainState.NORMAL;
					}
					break;
				default:
					break;
				}
				board[i][j] = new MapSpot(button, state, backgroundImages[i][j]);
			}
		}
	}
	/**
	 * Loads the background images for the MapSpots for the main grid.
	 * @return A 2D array of Images for the MapSpots of the main grid.
	 */
	public ImageIcon[][] loadBackgroundImages() {
		ImageIcon[][] bgImages = new ImageIcon[NUM_MAP_BUTTONS_X][NUM_MAP_BUTTONS_Y];
		BufferedImage[][] bgBImages = new BufferedImage[NUM_MAP_BUTTONS_X][NUM_MAP_BUTTONS_Y];
		try {
			BufferedImage background = ImageIO.read(new File("assets/main-screen/mainscreen.png"));
			for(int i = 0; i < NUM_MAP_BUTTONS_X; i++) {
				for(int j = 0; j < NUM_MAP_BUTTONS_Y; j++) {
					bgBImages[i][j] = background.getSubimage(i*(background.getWidth()/NUM_MAP_BUTTONS_X), j*(background.getHeight()/NUM_MAP_BUTTONS_Y), background.getWidth()/NUM_MAP_BUTTONS_X, background.getHeight()/NUM_MAP_BUTTONS_Y);
					bgImages[i][j] = new ImageIcon(resize(bgBImages[i][j],MAP_BUTTON_WIDTH,MAP_BUTTON_HEIGHT));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bgImages;
	}
	/**
	 * Updates the image to reflect the money and pollution levels
	 * @param money The current money amount.
	 * @param poll The current pollution level.
	 * @param map The current state of the main grid.
	 */
	public void update(double money, double poll, MapSpot[][] map, boolean gOver) {
		updateBoard();
		updateBars(money, poll);
		this.board = map;
		gameOver = gOver;
		panel.repaint();
	}
	/**
	 * Displays image of building if one exists or deletes Image if it is removed from a Spot.
	 */
	public void updateBoard() {
		for(MapSpot[] ms_arr : board) {
			for(MapSpot ms : ms_arr) {
				if(ms.getB() != null) {
					BufferedImage bImage = resize(ms.getB().getImage(),MAP_BUTTON_HEIGHT,MAP_BUTTON_HEIGHT);
					BuildingImage bi = new BuildingImage(ms.getBackground(),new ImageIcon(bImage));
					ms.setShowImage(bi);
				}
				else {
					ms.setShowImage(ms.getBackground());
				}
			}
		}
	}
	/**
	 * Update the Money and Pollution Bars on the left side of the screen
	 * @param moneyRatio the ratio of current money to max money.
	 * @param pollutionRatio the ratio of current pollution to max pollution.
	 */
	public void updateBars(double moneyRatio, double pollutionRatio) {
		panel.setMoneyRatio(moneyRatio);
		panel.setPollutionRatio(pollutionRatio);
	}
}