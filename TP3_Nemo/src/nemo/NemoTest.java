package nemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class NemoTest {
	
	@Test public void test00NemoIsOnTheSurfaceWhenCreated() {
		assertTrue(new Nemo(0,0, "North").isOnTheSurface());
	}

	@Test public void test01CordinatesAreCorrectWhenNemoIsCreated() {
		assertEquals(0, new Nemo(0,0, "North").getXCoordinate());
		assertEquals(0, new Nemo(0,0, "North").getYCoordinate());
	}

	@Test public void test02NemoFacesTheCorrectDirectionWhenCreated() {
		assertEquals("North", new Nemo(0,0, "North").getDirection());
	}

	@Test public void test03NemoDoesntMoveWithNoCommands() {
		Nemo nemo = new Nemo(0,0, "North");
		nemo.command("");
		assertEquals(0, nemo.getXCoordinate());
		assertEquals(0, nemo.getYCoordinate());
	}

	@Test public void test04NemoMovesDownwardsCorrectly() {
		Nemo nemo = new Nemo(0,0, "North");
		nemo.command("d");
		assertEquals(-1, nemo.getZCoordinate());
	}
	@Test public void test05NemoMovesUpwardCorrectly() {
		Nemo nemo = new Nemo(0,0, "North");
		nemo.command("du");
		assertEquals(0, nemo.getZCoordinate());
	}
	
	@Test public void test06NemoMovesForwardsCorrectly() {
		Nemo nemo = new Nemo(0,0, "North");
		nemo.command("f");
		assertEquals(1, nemo.getYCoordinate());
	}
	
	@Test public void test07NemoTurnsRightCorrectly() {
		Nemo nemo = new Nemo(0,0, "North");
		nemo.command("r");
		assertEquals("East", nemo.getDirection());
	}

	@Test public void test08NemoTurnsLeftCorrectly() {
		Nemo nemo = new Nemo(0,0, "North");
		nemo.command("l");
		assertEquals("West", nemo.getDirection());
	}

	@Test public void test09NemoDoesntMoveWhenChangingDirection() {
		Nemo nemo = new Nemo(0, 0, "North");
		nemo.command("l");
		assertEquals(0, nemo.getXCoordinate());
		assertEquals(0, nemo.getYCoordinate());
	}
	
	@Test public void test05NemoCannotMoveUpwardInTheSurface() {
		
	}
	
	@Test public void test06NemoChangesDirectionCorrectly() {

	}

	@Test public void test07NemoCantLaunchCapsuleBelowSurface(){

	}
}
