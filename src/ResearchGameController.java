import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

/**
 *   <h1>ResearchGameController Class</h1> Going by the MVC design pattern, this is the Controller class. This is where the player interacts and sends data and inputs to the 
 *   Model. this class has a listener for the keyboard which is the only input (the arrow keys). It also constantly pushes the Model to update the data with redraw() from an "action",
 *   (a timer is set as an abstract action basically)
 *   <p> 
 *   
 *   @author mattstack
 *   @version beta
 */
public class ResearchGameController{

	private ResearchGameModel model;
	private ResearchGameView view;
	private Action drawAction;
	
	/**
	 * <h1>ResearchGameContrller Constructor</h1> This constructos makes a new instance of the ResearchGameView and listens for the keystrokes its designed to listen for (arrow keys)
	 */
	public ResearchGameController(){
		view = new ResearchGameView();
		view.getPanel().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent ke) {
				switch(ke.getKeyCode()) {
					case KeyEvent.VK_UP:
						model.player.setDirection(RDirection.NORTHEAST);
						break;
					case KeyEvent.VK_DOWN:
						model.player.setDirection(RDirection.SOUTHEAST);
						break;
					case KeyEvent.VK_LEFT:
						model.player.setDirection(RDirection.IDLE);
						break;
					case KeyEvent.VK_RIGHT:
						model.player.setDirection(RDirection.EAST);
						break;
				}
			}
			@Override
			public void keyReleased(KeyEvent ke) {

			}
			@Override
			public void keyTyped(KeyEvent ke) {

			}});
		
		model = new ResearchGameModel(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		ResearchGameController c = this;
		drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
    			c.redraw();
    		}
    	};
	}
	
	/**
	 * <h1>start</h1> this starts the timer to update redraw() and send information to the Model
	 */
	public void start(){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Timer t = new Timer(30, drawAction);
				t.start();
			}
		});
	}
	
	/**
	 * <h1>redraw</h1> this is the method that feeds the Model with the freshest information about the current state of every object the Model needs to know about and nothing more.
	 */
	public void redraw(){
		model.updateLocationAndDirection();
		model.crabCollisionChecker();
		model.boundsCollisionChecker();
		model.endCheck();
		view.update(model.getPlayer(), model.getCrabs(), model.getRects());
		if (model.lifeCheck()) {
			model = new ResearchGameModel(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		}
		if (model.endCheck()) {
			model = new ResearchGameModel(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		}
	}

}
