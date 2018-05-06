
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
		birdModel = new BirdWatchingGameModel(BirdWatchingGameView.getWidth(), BirdWatchingGameView.getHeight());
		birdView.searchingFor = birdModel.searchingFor;
		birdView.getPanel().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent ke) {
				switch (ke.getKeyCode()) {
				case KeyEvent.VK_UP:
					birdModel.camera.setYSpeed(-15);
					break;
				case KeyEvent.VK_DOWN:
					birdModel.camera.setYSpeed(15);
					break;
				case KeyEvent.VK_LEFT:
					birdModel.camera.setXSpeed(-15);
					break;
				case KeyEvent.VK_RIGHT:
					birdModel.camera.setXSpeed(15);
					break;
				case KeyEvent.VK_SPACE:
					birdModel.wrongBird = false;
					birdModel.tryAgain = false;
					birdModel.takePicture(birdModel.searchingFor, birdModel.birds);
					birdView.searchingFor = birdModel.searchingFor;
					break;
				case KeyEvent.VK_ENTER:
					if (birdView.gameOver) {
						birdView.frame.setVisible(false);
					}
					
				}
				birdView.gameOver = birdModel.gameOver;	
				birdView.wrongBird = birdModel.wrongBird;
				birdView.tryAgain = birdModel.tryAgain;
			}

			@Override
			public void keyReleased(KeyEvent ke) {
				switch(ke.getKeyCode()){
				case KeyEvent.VK_UP:
				case KeyEvent.VK_DOWN:
					birdModel.camera.setYSpeed(0);
					break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_RIGHT:
					birdModel.camera.setXSpeed(0);
					break;
				}
			}

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
				Timer t = new Timer(30, drawAction);
				t.start();
			}
		});
		// increment the x and y coordinates, alter direction if necessary
		// update the view
	}

	public void redraw() {
		birdModel.update();
		birdView.update(birdModel.getBirds(), birdModel.getCamera(), birdModel.isFlash());
	}

}
