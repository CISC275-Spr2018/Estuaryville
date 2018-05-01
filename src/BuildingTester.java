import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class BuildingTester {
	@Test
	public void gettersAndSettersTest() {
		Building b = new Building(0,0,"Factory","factory");
		assertEquals(b.getCost(),0);
		assertEquals(b.getQualityNeeded(),0);
		assertEquals(b.getName(),"Factory");
		
		BufferedImage img = new BufferedImage(1,1,1);
		b.setCost(1);
		b.setImage(img);
		b.setName("");
		b.setQualityNeeded(1);
		
		assertEquals(b.getCost(),1);
		assertEquals(b.getQualityNeeded(),1);
		assertEquals(b.getName(),"");
		assertEquals(b.getImage(),img);
	}
	@Test
	public void equalsTest() {
		Building b1 = new Building(0,0,"Factory","factory");
		Building b2 = new Building(0,0,"Pier","pier");
		assertEquals(b1.equals(b2),false);
	}
	@Test
	public void equalsTest2() {
		Building b1 = new Building(0,0,"Factory","factory");
		Building b2 = new Building(0,0,"Factory","factory");
		assertEquals(b1.equals(b2),true);
	}
	
	@Test
	public void loadImageTest() {
		//assertThrows(IOException.class, () -> new Building(0,0,"",""));
	}
}
