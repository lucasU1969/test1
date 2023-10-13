package nemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class NemoTest {

	private String north = "North";

	@Test public void test00NemoIsOnTheSurfaceWhenCreated() {
		assertTrue(new Nemo(0,0, north).isOnTheSurface());
	}

	@Test public void test01CordinatesAreCorrectWhenNemoIsCreated() {
		assertEquals(0, new Nemo(0,0, north).getXCoordinate());
		assertEquals(0, new Nemo(0,0, north).getYCoordinate());
	}

	@Test public void test02NemoFacesTheCorrectDirectionWhenCreated() {
		assertEquals("North", new Nemo(0,0, north).getDirection());
	}

	@Test public void test03NemoDoesntMoveWithNoCommands() {
		Nemo nemo = new Nemo(0,0, north);
		nemo.command("");
		assertEquals(0, nemo.getXCoordinate());
		assertEquals(0, nemo.getYCoordinate());
	}

	@Test public void test04NemoMovesDownwardsCorrectly() {
		Nemo nemo = new Nemo(0,0, north);
		nemo.command("d");
		assertEquals(-1, nemo.getZCoordinate());
	}
	@Test public void test05NemoMovesUpwardCorrectly() {
		Nemo nemo = new Nemo(0,0, north);
		nemo.command("du");
		assertEquals(0, nemo.getZCoordinate());
	}
	
	@Test public void test06NemoMovesForwardsCorrectly() {
		Nemo nemo = new Nemo(0,0, north);
		nemo.command("f");
		assertEquals(1, nemo.getYCoordinate());
	}
	
	@Test public void test07NemoTurnsRightCorrectly() {
		Nemo nemo = new Nemo(0,0, north);
		nemo.command("r");
		assertEquals("East", nemo.getDirection());
	}

	@Test public void test08NemoTurnsLeftCorrectly() {
		Nemo nemo = new Nemo(0,0, north);
		nemo.command("l");
		assertEquals("West", nemo.getDirection());
	}

	@Test public void test09NemoDoesntMoveWhenChangingDirection() {
		Nemo nemo = new Nemo(0, 0, north);
		nemo.command("l");
		assertEquals(0, nemo.getXCoordinate());
		assertEquals(0, nemo.getYCoordinate());
	}
	
	@Test public void test10NemoCannotMoveUpwardInTheSurface() {
		Nemo nemo = new Nemo(0, 0, north);
		nemo.command("u");
		assertEquals(0, nemo.getZCoordinate());
	}
	
	@Test public void test11NemoLaunchesTehCapsuleCorrectly() {
		Nemo nemo = new Nemo(0, 0, north);
		nemo.command("m");
		assertFalse(nemo.isCapsuleInNemo());
	}

	@Test public void test12NemoCantLaunchCapsuleBelowSurface(){
		Nemo nemo = new Nemo(0, 0, north);
		nemo.command("dd");
		assertEquals("Nemo cannot launch the capsule this deep.", assertThrows(RuntimeException.class, () -> nemo.command("m")).getMessage());
		assertTrue(nemo.isCapsuleInNemo());
	}

	@Test public void test13NemoCantLaunchCapsuleTwice(){
		Nemo nemo = new Nemo(0, 0, north);
		nemo.command("m");
		assertEquals("Nemo cannot launch the capsule twice.", assertThrows(RuntimeException.class, () -> nemo.command("m")).getMessage());
		assertFalse(nemo.isCapsuleInNemo());
	}
	
	@Test public void test14UpAndDownWorksCorrectly() {
		Nemo nemo = new Nemo(0, 0, north);
		nemo.command("ddddddddddduuu");
		assertEquals(-8, nemo.getZCoordinate());
	}

}
