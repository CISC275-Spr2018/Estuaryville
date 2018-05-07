import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.junit.jupiter.api.Test;

public class MapSpotTester {
	@Test
	public void equalTest() {
		MapSpot ms1 = new MapSpot();
		MapSpot ms2 = new MapSpot();
		assertEquals(ms1.equals(ms2),true);
	}
	
	@Test
	public void equalTest2() {
		MapSpot ms1 = new MapSpot();
		ms1.setB(new Building(0,0,"Factory","factory"));
		MapSpot ms2 = new MapSpot();
		ms2.setB(new Building(0,0,"Research Station","research"));
		assertEquals(ms1.equals(ms2),false);
	}
	
	@Test
	public void equalTest3() {
		MapSpot ms1 = new MapSpot();
		MapSpot ms2 = new MapSpot();
		ms2.setB(new Building(0,0,"Research Station","research"));
		assertEquals(ms1.equals(ms2),false);
	}
	
	@Test
	public void equalTest4() {
		MapSpot ms1 = new MapSpot();
		ms1.setB(new Building(0,0,"Factory","factory"));
		MapSpot ms2 = new MapSpot();
		assertEquals(ms1.equals(ms2),false);
	}
	
	@Test
	public void equalTest5() {
		MapSpot ms1 = new MapSpot();
		ms1.setB(new Building(0,0,"Factory","factory"));
		assertEquals(ms1.equals(null),false);
	}
	
	@Test
	public void equalTest6() {
		MapSpot ms1 = new MapSpot();
		ms1.setB(new Building(0,0,"Factory","factory"));
		MapSpot ms2 = new MapSpot();
		ms2.setB(new Building(0,0,"Factory","factory"));
		assertEquals(ms1.equals(ms2),true);
	}
	
	@Test
	public void equalTest7() {
		MapSpot ms1 = new MapSpot();
		ms1.setB(new Building(0,0,"Factory","factory"));
		MapSpot ms2 = new MapSpot();
		ms2.setB(new Building(0,0,"Factory","factory"));
		ms2.settState(TerrainState.BEACH);
		assertEquals(ms1.equals(ms2),false);
	}
	
	@Test
	public void gettersAndSettersTest() {
		ImageIcon background = new ImageIcon();
		JButton button = new JButton();
		MapSpot ms = new MapSpot(button, TerrainState.NORMAL, background);
		assertEquals(ms.getBackground(), background);
		assertEquals(ms.getButton(), button);
		
		ImageIcon background2 = new ImageIcon();
		JButton button2 = new JButton();
		ImageIcon img = new ImageIcon();
		ms.setBackground(background2);
		ms.setButton(button2);
		ms.setShowImage(img);
		
		assertEquals(ms.getBackground(), background2);
		assertEquals(ms.getButton(), button2);
		assertEquals(ms.getShowImage(), img);
	}
	
	@Test
	public void isValidTest() {
		MapSpot ms = new MapSpot();
		assertEquals(ms.isValid(new Building(0,0,"Factory","factory")),true);
	}
	
	@Test
	public void isValidTest2() {
		MapSpot ms = new MapSpot();
		ms.settState(TerrainState.OTHER);
		assertEquals(ms.isValid(new Building(0,0,"Factory","factory")),false);
	}
	
	@Test
	public void isValidTest3() {
		MapSpot ms = new MapSpot();
		ms.setB(new Building(0,0,"Factory","factory"));
		assertEquals(ms.isValid(new Building(0,0,"Factory","factory")),false);
	}
}
