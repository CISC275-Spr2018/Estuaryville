import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MapSpot {
	JButton button;
	Building b;
	ImageIcon background;
	Icon showImage;
	TerrainState tState;
	
	public Icon getShowImage() {
		return showImage;
	}

	public void setShowImage(Icon showImage) {
		this.showImage = showImage;
		button.setIcon(showImage);
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public Building getB() {
		return b;
	}

	public void setB(Building b) {
		this.b = b;
	}

	public ImageIcon getBackground() {
		return background;
	}

	public void setBackground(ImageIcon background) {
		this.background = background;
	}

	public TerrainState gettState() {
		return tState;
	}

	public void settState(TerrainState tState) {
		this.tState = tState;
	}

	public MapSpot(JButton button, TerrainState ts, ImageIcon img) {
		this.button = button;
		this.tState = ts;
		this.background = img;
		this.showImage = img;
		button.setIcon(showImage);
	}
	
	public MapSpot() {
		this.tState = TerrainState.NORMAL;
	}
	
	public boolean isValid(Building structure) {
		if(structure.getName().equals("Factory")) {
			return ((tState == TerrainState.RIVER || tState == TerrainState.NORMAL || tState == TerrainState.FOREST || tState == TerrainState.BEACH) && b == null);
		}
		else if(structure.getName().equals("Bird Watching Tower")) {
			return ((tState == TerrainState.RIVER || tState == TerrainState.NORMAL || tState == TerrainState.FOREST || tState == TerrainState.BEACH) && b == null);
		}
		else if(structure.getName().equals("Fishing Pier")) {
			return (tState == TerrainState.BEACH && b == null);
		}
		else if(structure.getName().equals("Research Station")) {
			return ((tState == TerrainState.RIVER || tState == TerrainState.NORMAL || tState == TerrainState.FOREST || tState == TerrainState.BEACH) && b == null);
		}
		else if(structure.getName().equals("Port")) {
			return (tState == TerrainState.BEACH && b == null);
		}
		return b == null;
	}
	
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		MapSpot other = (MapSpot) obj;
		
		if(this.getB() == null && other.getB() == null) 
			return other.gettState().equals(this.tState);
		else if(this.getB() == null || other.getB() == null)
			return false;
		else 
			return other.getB().equals(this.b) && other.gettState().equals(this.tState);
	}
	
}
