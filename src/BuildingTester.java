import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BuildingTester {
	@Test
	public void gettersAndSettersTest() {
		Building b = new Building(0,0,BuildingName.FACTORY,"factory");
		assertEquals(b.getCost(),0);
		assertEquals(b.getQualityNeeded(),0);
		assertEquals(b.getName(),BuildingName.FACTORY);
		assertEquals(b.getFileName(),"factory");
	}
	@Test
	public void equalsTest() {
		Building b1 = new Building(0,0,BuildingName.FACTORY,"factory");
		Building b2 = new Building(0,0,BuildingName.FISH,"pier");
		assertEquals(b1.equals(b2),false);
	}
	@Test
	public void equalsTest2() {
		Building b1 = new Building(0,0,BuildingName.FACTORY,"factory");
		Building b2 = new Building(0,0,BuildingName.FACTORY,"factory");
		assertEquals(b1.equals(b2),true);
	}
	@Test
	public void equalsTest3() {
		Building b1 = null;
		Building b2 = new Building(0,0,BuildingName.FACTORY,"factory");
		assertEquals(b2.equals(b1),false);
	}
	
	@Test
	public void loadImageTest() {
		//assertThrows(IOException.class, () -> new Building(0,0,"",""));
	}
}
