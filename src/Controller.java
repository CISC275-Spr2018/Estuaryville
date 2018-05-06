import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class Controller {
	static Active activePanel = Active.MAIN;
	

	MainView mView = new MainView();
	MainModel mMod = new MainModel(mView.getBoard());

	BirdWatchingGameView bView = new BirdWatchingGameView();
	BirdWatchingGameModel bMod = new BirdWatchingGameModel(MainView.FRAME_WIDTH, MainView.FRAME_HEIGHT);

	FishingGameView fView = new FishingGameView();
	FishingGameModel fMod = new FishingGameModel(MainView.FRAME_WIDTH, MainView.FRAME_HEIGHT);
	
	ResearchGameView rView = new ResearchGameView();
	ResearchGameModel rMod = new ResearchGameModel(MainView.FRAME_WIDTH, MainView.FRAME_HEIGHT,0,0);

	public Action drawAction;

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
		
			bView.getPanel().addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(KeyEvent ke) {
					switch (ke.getKeyCode()) {
					case KeyEvent.VK_UP:
						bMod.camera.setYSpeed(-15);
						break;
					case KeyEvent.VK_DOWN:
						bMod.camera.setYSpeed(15);
						break;
					case KeyEvent.VK_LEFT:
						bMod.camera.setXSpeed(-15);
						break;
					case KeyEvent.VK_RIGHT:
						bMod.camera.setXSpeed(15);
						break;
					case KeyEvent.VK_T:
						BirdWatchingGameModel.takePicture();
						break;
					}
					
				}

				@Override
				public void keyReleased(KeyEvent ke) {
					switch(ke.getKeyCode()){
					case KeyEvent.VK_UP:
					case KeyEvent.VK_DOWN:
						bMod.camera.setYSpeed(0);
						break;
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_RIGHT:
						bMod.camera.setXSpeed(0);
						break;
					}
				}

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
	
	public static void generateMapListeners(MainModel model, MainView view) {
		for(int i = 0; i < view.getBoard().length; i++) {
			for(int j = 0; j < view.getBoard()[0].length; j++) {
				final int x = i;
				final int y = j;
				view.getBoard()[i][j].getButton().addActionListener(new ActionListener() {
					//Way to simplify?? using enum string etc?
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("MAP["+x+"]["+y+"]: "+model.getMap()[x][y].tState);
						switch(model.getBuild()) {
						case PORT:
							model.build(model.getBuildingTypes().get("Port"), x, y);
							break;
						case BIRD:
							activePanel = model.build(model.getBuildingTypes().get("Bird"), x, y);
							break;
						case FACTORY:
							model.build(model.getBuildingTypes().get("Factory"), x, y);
							break;
						case RESEARCH:
							activePanel = model.build(model.getBuildingTypes().get("Research"), x, y);
							break;
						case FISH:
							activePanel = model.build(model.getBuildingTypes().get("Fish"), x, y);
							break;
						case REMOVE:
							model.removeBuilding(x, y);
						default:
							break;
						}
					}
				});
			}
		}
	}
	
	public static void generateSidebarListeners(MainModel model, MainView view) {//
		view.getSidebarButtons().get("Port").setMnemonic(KeyEvent.VK_P);
		view.getSidebarButtons().get("Port").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setBuild(BuildState.PORT);
			}
		});
		
		view.getSidebarButtons().get("Bird Watching Tower").setMnemonic(KeyEvent.VK_B);
		view.getSidebarButtons().get("Bird Watching Tower").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setBuild(BuildState.BIRD);
			}
		});
		
		view.getSidebarButtons().get("Factory").setMnemonic(KeyEvent.VK_F);
		view.getSidebarButtons().get("Factory").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setBuild(BuildState.FACTORY);
			}
		});
		
		view.getSidebarButtons().get("Research Station").setMnemonic(KeyEvent.VK_S);
		view.getSidebarButtons().get("Research Station").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setBuild(BuildState.RESEARCH);
			}
		});
		
		view.getSidebarButtons().get("Fishing Pier").setMnemonic(KeyEvent.VK_I);
		view.getSidebarButtons().get("Fishing Pier").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setBuild(BuildState.FISH);
			}
		});
		
		view.getSidebarButtons().get("Remove").setMnemonic(KeyEvent.VK_R);
		view.getSidebarButtons().get("Remove").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setBuild(BuildState.REMOVE);
			}
		});
	}

	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Timer t = new Timer(30, drawAction);
				t.start();
			}
		});
	}

	public void redraw() {
		switch(activePanel) {
			case MAIN:
				activePanel = Active.MAIN;
				mView.getPanel().requestFocusInWindow();
				mMod.update();
				mView.update((double) (mMod.getMoney())/(double) (mMod.MONEY_MAX), (double) (mMod.getPollution()) /(double) (mMod.POLLUTION_MAX), mMod.getMap());
				break;
			case BIRD:
				activePanel = Active.BIRD;
				bView.getPanel().requestFocusInWindow();
				bMod.update();
				bView.update(bMod.getBirds(),bMod.getCamera());
				break;
			case FISH:
				activePanel = Active.FISH;
				fView.getPanel().requestFocusInWindow();
				fMod.update();
				fView.update(fMod.getFish(),fMod.getHook());
				break;
			case RESEARCH:
				activePanel = Active.RESEARCH;
				rView.getPanel().requestFocusInWindow();
				rMod.updateLocationAndDirection();
				rView.update(rMod);
				break;
			default:
				break;

		}
	}
}
