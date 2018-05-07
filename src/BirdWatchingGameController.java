
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

/**
 * This is a controller for the Bird Watching Game. This class will handle
 * the interaction between the model and the view within the game. 
 * @author Tyler
 *
 */
public class BirdWatchingGameController {

	private BirdWatchingGameModel birdModel;
	private BirdWatchingGameView birdView;
	private Action drawAction;

	/**
	 * Creates an instance of the BirdWatchingGameController class. This method
	 * creates a BirdWatchingGameView and a BirdWatchingGameModel. This method
	 * also uses a KeyListener to handle KeyEvents.
	 */
	@SuppressWarnings("serial")
	public BirdWatchingGameController() {
		birdView = new BirdWatchingGameView();
		birdModel = new BirdWatchingGameModel(birdView.getScreenWidth(), birdView.getScreenHeight());
		birdView.setSearchingFor(birdModel.getSearchingFor());
		birdView.getPanel().addKeyListener(new KeyListener() {
			/**
			 * Overrides the keyPressed method from KeyListener. This method
			 * looks for keys to be pressed within the Bird Watching game and
			 * performs actions accordingly.
			 */
			@Override
			public void keyPressed(KeyEvent ke) {
				switch (ke.getKeyCode()) {
				case KeyEvent.VK_UP:
					if (birdModel.getToDisplayInfo() == null && !birdModel.getGameOver())
						birdModel.getCamera().setYSpeed(birdModel.getScaledHeight(-15));
					break;
				case KeyEvent.VK_DOWN:
					if (birdModel.getToDisplayInfo() == null && !birdModel.getGameOver())
						birdModel.getCamera().setYSpeed(birdModel.getScaledHeight(15));
					break;
				case KeyEvent.VK_LEFT:
					if (birdModel.getToDisplayInfo() == null && !birdModel.getGameOver())
						birdModel.getCamera().setXSpeed(birdModel.getScaledWidth(-15));
					break;
				case KeyEvent.VK_RIGHT:
					if (birdModel.getToDisplayInfo() == null && !birdModel.getGameOver())
						birdModel.getCamera().setXSpeed(birdModel.getScaledWidth(15));
					break;
				case KeyEvent.VK_SPACE:
					birdModel.setWrongBird(false);
					birdModel.setTryAgain(false);
					if (birdModel.getToDisplayInfo() == null) {
						birdModel.takePicture(birdModel.getSearchingFor(), birdModel.getBirds());
						birdView.setSearchingFor(birdModel.getSearchingFor());
					}
					break;
				case KeyEvent.VK_ENTER:
					if (birdView.getToDisplayInfo() != null) {
						birdView.setToDisplayInfo(null);
						birdModel.setToDisplayInfo(null);
						//birdView.pauseGame = false;
						//birdView.repaintCount = 0;
					}
					else if (birdView.getGameOver()) {
						birdView.getPanel().setVisible(false);
					}
					
				}
				birdView.setGameOver(birdModel.getGameOver());	
				birdView.setWrongBird(birdModel.getWrongBird());
				birdView.setTryAgain(birdModel.getTryAgain());
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
					birdModel.getCamera().setYSpeed(0);
					break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_RIGHT:
					birdModel.getCamera().setXSpeed(0);
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
		
		BirdWatchingGameController c = this;
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				c.redraw();
			}
		};
	}

	// run the simulation 
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Timer t = new Timer(10, drawAction);
				t.start();
			}
		});
		// increment the x and y coordinates, alter direction if necessary
		// update the view
	}

	public void redraw() {
		birdModel.update();
		birdView.update(birdModel.getBirds(), birdModel.getCamera(), birdModel.isFlash(), birdModel.getToDisplayInfo());
	}

}
