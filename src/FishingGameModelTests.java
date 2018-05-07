import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

/**
 * 
 */

public class FishingGameModelTests {
	FishingGameModel fgm = new FishingGameModel(1440,900);
	
	@Test public void testGetters(){
		Fish f = new Fish(Fish.Species.AMERICAN_SHAD, 500, 500, Direction.EAST);
		fgm.caught = f;
		assertEquals(f, fgm.getCaught());
		fgm.gameOver = false;
		assertEquals(false, fgm.getGameOver());
		fgm.displayCatch = false;
		assertEquals(false, fgm.getDisplayCatch());
	}
	
	@Test
	public void testGetFish(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 500, 500, Direction.EAST));
		fgm.fishes = f;
		assertEquals(f, fgm.getFish());
	}
	@Test
	public void testGetHook(){
		Hook h = new Hook();
		fgm.hook = h;
		assertEquals(h, fgm.getHook());
	}
	
	@Test
	public void testFishCaughtTrue(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 0, 0, Direction.WEST));
		f.get(0).setMouth(500, 500, Direction.WEST);
		Hook h = new Hook();
		h.setXPos(508);
		h.setYPos(540);
		h.setHitbox(508, 540);
		fgm.hook = h;
		fgm.fishes = f;
		assertEquals(true, fgm.fishCaught(f.get(0)));
	}
	
	@Test
	public void testFishCaughtFalse(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 0, 0, Direction.WEST));
		f.get(0).setMouth(500, 500, Direction.WEST);
		Hook h = new Hook();
		h.setXPos(500);
		h.setYPos(500);
		h.setHitbox(500, 500);
		fgm.hook = h;
		fgm.fishes = f;
		assertEquals(false, fgm.fishCaught(f.get(0)));
	}
	
	@Test
	public void checkHookBoundsUpBlock(){
		Hook h = new Hook();
		fgm.hook = h;
		h.setYPos(FishingGameModel.WATER_TOP+1);
		h.setYSpeed(-6);
		int currPos = h.getYPos();
		fgm.checkBounds();
		int nextPos = h.getYPos();
		assertEquals(currPos, nextPos);
	}
	
	@Test
	public void checkHookBoundsDownBlock(){
		Hook h = new Hook();
		fgm.hook = h;
		h.setYPos(FishingGameModel.WATER_BOTTOM-1);
		h.setYSpeed(6);
		int currPos = h.getYPos();
		fgm.checkBounds();
		int nextPos = h.getYPos();
		assertEquals(currPos, nextPos);
	}
	
	@Test
	public void checkHookBoundsUpClear(){
		Hook h = new Hook();
		fgm.hook = h;
		h.setYPos(FishingGameModel.WATER_TOP+7);
		h.setYSpeed(-6);
		int currPos = h.getYPos();
		fgm.checkBounds();
		int nextPos = h.getYPos();
		assertEquals(currPos-6, nextPos);
	}
	
	@Test
	public void checkHookBoundsDownClear(){
		Hook h = new Hook();
		fgm.hook = h;
		h.setYPos(FishingGameModel.WATER_BOTTOM-FishingGameView.HOOK_HEIGHT-7);
		h.setYSpeed(6);
		int currPos = h.getYPos();
		fgm.checkBounds();
		int nextPos = h.getYPos();
		assertEquals(currPos+6, nextPos);
	}
	
	@Test
	public void checkHookBoundsLeftBlock(){
		Hook h = new Hook();
		fgm.hook = h;
		h.setXPos(1);
		h.setXSpeed(-5);
		int currPos = h.getXPos();
		fgm.checkBounds();
		int nextPos = h.getXPos();
		assertEquals(currPos, nextPos);
	}
	
	@Test
	public void checkHookBoundsRightBlock(){
		Hook h = new Hook();
		fgm.hook = h;
		h.setXPos(FishingGameView.getWidth() - FishingGameView.HOOK_WIDTH - 2);
		h.setXSpeed(6);
		int currPos = h.getXPos();
		fgm.checkBounds();
		int nextPos = h.getXPos();
		assertEquals(currPos, nextPos);
	}
	
	@Test
	public void checkHookBoundsLeftClear(){
		Hook h = new Hook();
		fgm.hook = h;
		h.setXPos(6);
		h.setXSpeed(-5);
		int currPos = h.getXPos();
		fgm.checkBounds();
		int nextPos = h.getXPos();
		assertEquals(currPos-5, nextPos);
	}
	
	@Test
	public void checkHookBoundsRightClear(){
		Hook h = new Hook();
		fgm.hook = h;
		h.setXPos(FishingGameView.getWidth() - FishingGameView.HOOK_WIDTH - 20);
		h.setXSpeed(6);
		int currPos = h.getXPos();
		fgm.checkBounds();
		int nextPos = h.getXPos();
		assertEquals(currPos+6, nextPos);
	}
	
	@Test
	public void checkFishBoundsCaughtSpeciesEast(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 0, 0, Direction.WEST));
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 5, 10, Direction.EAST));
		fgm.caught = f.get(0);
		Fish h = f.get(1);
		h.setXPos(508);
		h.setXSpeed(1);
		fgm.fishes = f;
		int currPos = h.getXPos();
		fgm.checkBounds(h);
		int nextPos = h.getXPos();
		assertEquals(currPos+5, nextPos);
	}
	@Test
	public void checkFishBoundsCaughtSpeciesWest(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 0, 0, Direction.WEST));
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 5, 10, Direction.WEST));
		fgm.caught = f.get(0);
		Fish h = f.get(1);
		h.setXPos(508);
		h.setXSpeed(1);
		fgm.fishes = f;
		int currPos = h.getXPos();
		fgm.checkBounds(h);
		int nextPos = h.getXPos();
		assertEquals(currPos-5, nextPos);
	}
	@Test
	public void checkFishBoundsCaughtSpeciesEastKill(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 0, 0, Direction.WEST));
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 5, 10, Direction.EAST));
		fgm.caught = f.get(0);
		Fish h = f.get(1);
		h.setXPos(FishingGameView.WIDTH + FishingGameView.FISH_WIDTH - 1);
		h.setXSpeed(1);
		fgm.fishes = f;
		fgm.checkBounds(h);
		assertEquals(false, f.contains(h));
	}
	@Test
	public void checkFishBoundsCaughtSpeciesWestKill(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 0, 0, Direction.WEST));
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 5, 10, Direction.WEST));
		fgm.caught = f.get(0);
		Fish h = f.get(1);
		h.setXPos(-FishingGameView.FISH_WIDTH + 1);
		h.setXSpeed(1);
		fgm.fishes = f;
		fgm.checkBounds(h);
		assertEquals(false, f.contains(h));
	}
	@Test
	public void checkFishBoundsEastClear(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 0, 0, Direction.WEST));
		f.add(new Fish(Fish.Species.STURGEON, 5, 10, Direction.EAST));
		fgm.caught = f.get(0);
		Fish h = f.get(1);
		h.setXPos(508);
		h.setXSpeed(1);
		fgm.fishes = f;
		int currPos = h.getXPos();
		fgm.checkBounds(h);
		int nextPos = h.getXPos();
		assertEquals(currPos+1, nextPos);
	}
	@Test
	public void checkFishBoundsWestClear(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 0, 0, Direction.WEST));
		f.add(new Fish(Fish.Species.STURGEON, 5, 10, Direction.WEST));
		fgm.caught = f.get(0);
		Fish h = f.get(1);
		h.setXPos(508);
		h.setXSpeed(1);
		fgm.fishes = f;
		int currPos = h.getXPos();
		fgm.checkBounds(h);
		int nextPos = h.getXPos();
		assertEquals(currPos-1, nextPos);
	}
	@Test
	public void checkFishBoundsEastBlock(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 0, 0, Direction.WEST));
		f.add(new Fish(Fish.Species.STURGEON, 5, 10, Direction.EAST));
		fgm.caught = f.get(0);
		Fish h = f.get(1);
		h.setXPos(FishingGameView.WIDTH - 51);
		h.setXSpeed(5);
		fgm.fishes = f;
		fgm.checkBounds(h);
		assertEquals(Direction.WEST, h.getDirection());
	}
	@Test
	public void checkFishBoundsWestBlock(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 0, 0, Direction.WEST));
		f.add(new Fish(Fish.Species.STURGEON, 5, 10, Direction.WEST));
		fgm.caught = f.get(0);
		Fish h = f.get(1);
		h.setXPos(-48);
		h.setXSpeed(5);
		fgm.fishes = f;
		fgm.checkBounds(h);
		assertEquals(Direction.EAST, h.getDirection());
	}
	@Test
	public void checkFishBoundsIf(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 0, 0, Direction.WEST));
		f.add(new Fish(Fish.Species.STURGEON, 5, 10, Direction.WEST));
		fgm.caught = null;
		Fish h = f.get(1);
		h.setXPos(-48);
		h.setXSpeed(5);
		fgm.fishes = f;
		fgm.checkBounds(h);
		assertEquals(Direction.EAST, h.getDirection());
	}
	@Test
	public void testReelItInKill(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 0, 0, Direction.WEST));
		f.add(new Fish(Fish.Species.STURGEON, 5, 10, Direction.WEST));
		fgm.caught = f.get(0);
		Hook h = new Hook();
		h.setXPos(FishingGameView.ROD_X - 7);
		h.setYPos(FishingGameView.ROD_Y - 7);
		fgm.hook = h;
		fgm.fishes = f;
		Fish fishy = f.get(0);
		fgm.reelItIn(fishy);
		assertEquals(false, f.contains(fishy));
	}
	@Test
	public void testReelItIn(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 0, 0, Direction.WEST));
		f.add(new Fish(Fish.Species.STURGEON, 5, 10, Direction.WEST));
		fgm.caught = f.get(0);
		Hook h = new Hook();
		h.setXPos(FishingGameView.ROD_X);
		h.setYPos(FishingGameView.ROD_Y - 150);
		fgm.hook = h;
		fgm.fishes = f;
		Fish fishy = f.get(0);
		fgm.reelItIn(fishy);
		assertEquals(true, f.contains(fishy));
		h.setXPos(FishingGameView.ROD_X - 150);
		h.setYPos(FishingGameView.ROD_Y);
		assertEquals(true, f.contains(fishy));
		h.setXPos(FishingGameView.ROD_X - 150);
		h.setYPos(FishingGameView.ROD_Y - 150);
		assertEquals(true, f.contains(fishy));
	}
	@Test
	public void testUpdate1(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		f.add(new Fish(Fish.Species.AMERICAN_SHAD, 0, 0, Direction.WEST));
		f.add(new Fish(Fish.Species.STURGEON, 0, 0, Direction.EAST));
		f.get(0).setMouth(500, 500, Direction.WEST);
		Hook h = new Hook();
		h.setXPos(508);
		h.setYPos(540);
		h.setHitbox(508, 540);
		fgm.hook = h;
		fgm.fishes = f;
		fgm.timeSinceCatch = 35;
		Fish fishy = f.get(0);
		fgm.update();
		assertEquals(true, f.contains(fishy));
	}
	@Test
	public void testUpdate2(){
		ArrayList<Fish> f = new ArrayList<Fish>();
		//f.add(new Fish(Fish.Species.AMERICAN_SHAD, 0, 0, Direction.WEST));
		//f.add(new Fish(Fish.Species.STURGEON, 0, 0, Direction.EAST));
		//f.get(0).setMouth(500, 500, Direction.WEST);
		Hook h = new Hook();
		h.setXPos(508);
		h.setYPos(540);
		h.setHitbox(508, 540);
		fgm.hook = h;
		fgm.fishes = f;
		fgm.timeSinceCatch = 35;
		Fish fishy = null;
		fgm.update();
		assertEquals(false, f.contains(fishy));
	}
}
