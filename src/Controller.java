import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class Controller{
	static Active activePanel = Active.MAIN;
	static BuildError buildProblem = BuildError.NONE;

	MainView mView = new MainView();
	MainModel mMod = new MainModel(mView.getBoard());

	BirdWatchingGameView bView = new BirdWatchingGameView();
	BirdWatchingGameModel bMod = new BirdWatchingGameModel(MainView.FRAME_WIDTH, MainView.FRAME_HEIGHT);

	FishingGameView fView = new FishingGameView();
	FishingGameModel fMod = new FishingGameModel(MainView.FRAME_WIDTH, MainView.FRAME_HEIGHT);
	boolean firstFrame = true;
	
	ResearchGameView rView = new ResearchGameView();
	ResearchGameModel rMod = new ResearchGameModel(MainView.FRAME_WIDTH, MainView.FRAME_HEIGHT,0,0);

	public Action drawAction;
	private boolean paused = false;

	/**
	 * Creates an instance of Controller
	 */
	@SuppressWarnings("serial")
	public Controller() {
		
		mView.getPanel().setBounds(0,0,MainView.FRAME_WIDTH,MainView.FRAME_HEIGHT);
		bView.getPanel().setBounds(0,0,MainView.FRAME_WIDTH,MainView.FRAME_HEIGHT);
		fView.getPanel().setBounds(0,0,MainView.FRAME_WIDTH,MainView.FRAME_HEIGHT);
		rView.getPanel().setBounds(0,0,MainView.FRAME_WIDTH,MainView.FRAME_HEIGHT);
		
		mView.getPanel().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent ke) {
				switch(ke.getKeyCode()) {
				case KeyEvent.VK_P:
					paused = !paused;
					break;
				}
			}
			@Override
			public void keyReleased(KeyEvent ke) {

			}
			@Override
			public void keyTyped(KeyEvent ke) {

			}
		});
		generateMapListeners(mMod, mView);
		generateSidebarListeners(mMod, mView);
		
		bView.setSearchingFor(bMod.getSearchingFor());
		bView.getPanel().addKeyListener(new KeyListener() {
			/**
			 * Overrides the keyPressed method from KeyListener. This method
			 * looks for keys to be pressed within the Bird Watching game and
			 * performs actions accordingly.
			 */
			@Override
			public void keyPressed(KeyEvent ke) {
				switch (ke.getKeyCode()) {
				case KeyEvent.VK_UP:
					if (bMod.getToDisplayInfo() == null && !bMod.getGameOver())
						bMod.getCamera().setYSpeed(bMod.getScaledHeight(-15));
					break;
				case KeyEvent.VK_DOWN:
					if (bMod.getToDisplayInfo() == null && !bMod.getGameOver())
						bMod.getCamera().setYSpeed(bMod.getScaledHeight(15));
					break;
				case KeyEvent.VK_LEFT:
					if (bMod.getToDisplayInfo() == null && !bMod.getGameOver())
						bMod.getCamera().setXSpeed(bMod.getScaledWidth(-15));
					break;
				case KeyEvent.VK_RIGHT:
					if (bMod.getToDisplayInfo() == null && !bMod.getGameOver())
						bMod.getCamera().setXSpeed(bMod.getScaledWidth(15));
					break;
				case KeyEvent.VK_SPACE:
					bMod.setWrongBird(false);
					bMod.setTryAgain(false);
					if (bMod.getToDisplayInfo() == null) {
						bMod.takePicture(bMod.getSearchingFor(), bMod.getBirds());
						bView.setSearchingFor(bMod.getSearchingFor());
					}
					break;
				case KeyEvent.VK_ENTER:
					if (bView.getToDisplayInfo() != null) {
						bView.setToDisplayInfo(null);
						bMod.setToDisplayInfo(null);
						//bView.pauseGame = false;
						//bView.repaintCount = 0;
					}
					else if (bView.getGameOver()) {
						bView.getPanel().setVisible(false);
						activePanel = Active.MAIN;
					}
					break;
				case KeyEvent.VK_P:
					paused = !paused;
					break;
				}
				bView.setGameOver(bMod.getGameOver());	
				bView.setWrongBird(bMod.getWrongBird());
				bView.setTryAgain(bMod.getTryAgain());
			}

			/**
			 * Overrides the keyReleased method from KeyListener. This method
			 * looks for keys to be released in the Bird Watching Game and 
			 * performs actions accordingly.
			 */
			@Override
			public void keyReleased(KeyEvent ke) {
				switch(ke.getKeyCode()){
				case KeyEvent.VK_UP:
				case KeyEvent.VK_DOWN:
					bMod.getCamera().setYSpeed(0);
					break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_RIGHT:
					bMod.getCamera().setXSpeed(0);
					break;
				}
			}

			/**
			 * Overrides the keyTyped method from KeyListener.
			 */
			@Override
			public void keyTyped(KeyEvent ke) {

			}
		});
			
			fView.getPanel().addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(KeyEvent ke) {	
					switch(ke.getKeyCode()){
					case KeyEvent.VK_UP:
						fMod.hook.setYSpeed(-15);
						break;
					case KeyEvent.VK_DOWN:
						fMod.hook.setYSpeed(15);
						break;
					case KeyEvent.VK_LEFT:
						fMod.hook.setXSpeed(-15);
						break;
					case KeyEvent.VK_RIGHT:
						fMod.hook.setXSpeed(15);
						break;
					case KeyEvent.VK_ENTER:
						if (fMod.getGameOver()) {
							fView.getPanel().setVisible(false);
							activePanel = Active.MAIN;
						}
					case KeyEvent.VK_P:
						paused = !paused;
						break;
					}
				}

				@Override
				public void keyReleased(KeyEvent ke) {
					switch(ke.getKeyCode()){
					case KeyEvent.VK_UP:
					case KeyEvent.VK_DOWN:
						fMod.hook.setYSpeed(0);
						break;
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_RIGHT:
						fMod.hook.setXSpeed(0);
						break;
					}
				}

				@Override
				public void keyTyped(KeyEvent ke) {

				}
			});
			
			rView.getPanel().addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(KeyEvent ke) {
					switch(ke.getKeyCode()) {
						case KeyEvent.VK_UP:
							rMod.player.setDirection(RDirection.NORTHEAST);
							break;
						case KeyEvent.VK_DOWN:
							rMod.player.setDirection(RDirection.SOUTHEAST);
							break;
						case KeyEvent.VK_LEFT:
							rMod.player.setDirection(RDirection.IDLE);
							break;
						case KeyEvent.VK_RIGHT:
							rMod.player.setDirection(RDirection.EAST);
							break;
						case KeyEvent.VK_ENTER:
							if (rMod.endCheck()) {
								rView.getPanel().setVisible(false);
								activePanel = Active.MAIN;
							}
						case KeyEvent.VK_P:
							paused = !paused;
							break;
					}
				}
				@Override
				public void keyReleased(KeyEvent ke) {

				}
				@Override
				public void keyTyped(KeyEvent ke) {

				}});
		
		mView.getMainPanel().add(mView.getPanel(),JLayeredPane.DEFAULT_LAYER,-1);
		mView.getMainPanel().add(bView.getPanel(),JLayeredPane.DEFAULT_LAYER,-1);
		mView.getMainPanel().add(fView.getPanel(),JLayeredPane.DEFAULT_LAYER,-1);
		mView.getMainPanel().add(rView.getPanel(),JLayeredPane.DEFAULT_LAYER,-1);

		Controller c = this;
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				c.redraw();
			}
		};
	}
	/**
	 * Adds listeners to the main map of the main screen.
	 * @param model The Main Screen Model.
	 * @param view The Main Screen View.
	 */
	public static void generateMapListeners(MainModel model, MainView view) {
		for(int i = 0; i < view.getBoard().length; i++) {
			for(int j = 0; j < view.getBoard()[0].length; j++) {
				final int x = i;
				final int y = j;
				view.getBoard()[i][j].getButton().addActionListener(new ActionListener() {
					//Way to simplify?? using enum string etc?
					@Override
					public void actionPerformed(ActionEvent e) {
						BuildReturn br;
						if(activePanel == Active.MAIN) {
							switch(model.getBuild()) {
							case PORT:
								br = model.build(model.getBuildingTypes().get("Port"), x, y);
								activePanel = br.getActive();
								buildProblem = br.getBuildError();
								break;
							case BIRD:
								br = model.build(model.getBuildingTypes().get("Bird"), x, y);
								activePanel = br.getActive();
								buildProblem = br.getBuildError();
								break;
							case FACTORY:
								br = model.build(model.getBuildingTypes().get("Factory"), x, y);
								activePanel = br.getActive();
								buildProblem = br.getBuildError();
								break;
							case RESEARCH:
								br = model.build(model.getBuildingTypes().get("Research"), x, y);
								activePanel = br.getActive();
								buildProblem = br.getBuildError();
								break;
							case FISH:
								br = model.build(model.getBuildingTypes().get("Fish"), x, y);
								activePanel = br.getActive();
								buildProblem = br.getBuildError();
								break;
							case REMOVE:
								model.removeBuilding(x, y);
							default:
								break;
							}
						}
					}
				});
			}
		}
	}
	/**
	 * Generates Listeners for the sidebar.
	 * @param model The Main Screen Model.
	 * @param view The Main Screen View.
	 */
	public static void generateSidebarListeners(MainModel model, MainView view) {//
		view.getSidebarButtons().get("Port").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(activePanel == Active.MAIN)
					model.setBuild(BuildState.PORT);
			}
		});
		
		view.getSidebarButtons().get("Bird Watching Tower").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(activePanel == Active.MAIN)
					model.setBuild(BuildState.BIRD);
			}
		});
		
		view.getSidebarButtons().get("Factory").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(activePanel == Active.MAIN)
					model.setBuild(BuildState.FACTORY);
			}
		});
		
		view.getSidebarButtons().get("Research Station").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(activePanel == Active.MAIN)
					model.setBuild(BuildState.RESEARCH);
			}
		});
		
		view.getSidebarButtons().get("Fishing Pier").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(activePanel == Active.MAIN)
					model.setBuild(BuildState.FISH);
			}
		});
		
		view.getSidebarButtons().get("Remove").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(activePanel == Active.MAIN)
					model.setBuild(BuildState.REMOVE);
			}
		});
	}
	/**
	 * Runs games on 30 millisecond tick speed.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Timer t = new Timer(30, drawAction);
				t.start();
			}
		});
	}
	/**
	 * Redraws the active panel to animate and update games.
	 */
	public void redraw() {
		if(!paused) {
		switch(activePanel) {
			case MAIN:
					mView.getPanel().requestFocusInWindow();
					mMod.update();
					mView.update((double) (mMod.getMoney())/(double) (mMod.MONEY_MAX),
							(double) (mMod.getPollution()) /(double) (mMod.POLLUTION_MAX),
							mMod.getMap(),
							mMod.gameOver(),
							mMod.getMoneyIncr(),
							mMod.getPollIncr(),
							buildProblem);
					if(mMod.gameOver()) {
						try {
							Thread.sleep(1500);
							System.exit(0);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				break;
			case BIRD:
				bView.getPanel().requestFocusInWindow();
				bMod.update();
				bView.update(bMod.getBirds(),bMod.getCamera(),bMod.isFlash(), bMod.toDisplayInfo);
				break;
			case FISH:
				if(firstFrame) {
					fMod.setPollutionLevel((double) mMod.getPollution()/(double) mMod.POLLUTION_MAX);
					firstFrame = false;
				}
				fView.getPanel().requestFocusInWindow();
				fMod.update();
				fView.update(fMod.getFish(),fMod.getTrash(),fMod.getHook(),fMod.getCaught(), fMod.getGameOver(), fMod.getDisplayCatch());
				break;
			case RESEARCH:
				rView.getPanel().requestFocusInWindow();
				rMod.updateLocationAndDirection();
				rMod.crabCollisionChecker();
				rMod.boundsCollisionChecker();
				rMod.endCheck();
				rView.update(rMod.getPlayer(), rMod.getCrabs(), rMod.getRects());
				if (rMod.lifeCheck()) {
					rMod = new ResearchGameModel(rView.getWidth(), rView.getHeight(), rView.getImageWidth(), rView.getImageHeight());
				}
				if (rMod.endCheck()) {
					activePanel = Active.MAIN;
				}
				break;
			default:
				break;

		}
		}
	}
}
