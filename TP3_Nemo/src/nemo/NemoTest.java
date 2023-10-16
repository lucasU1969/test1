package nemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class NemoTest {

	private String north = "North";

	@Test public void test00NemoIsOnTheSurfaceWhenCreated() {
		assertTrue(new Nemo(0,0, north).isOnTheSurface());
	}

	@Test public void test01CordinatesAreCorrectWhenNemoIsCreated() {
		assertEquals(0, new Nemo(0,0, north).getXCoordinate());
		assertEquals(0, new Nemo(0,0, north).getYCoordinate());
	}

	@Test public void test02NemoFacesTheCorrectDirectionWhenCreatedFacingNorth() {
		assertEquals("North", new Nemo(0,0, north).getDirection());
	}

	@Test public void test03NemoFacesTheCorrectDirectionWhenCreatedFacingSouth() {
		assertEquals("South", new Nemo(0,0, "South").getDirection());
	}

	@Test public void test04NemoFAcesTheCorrectDirectionWhenCreatedFacingEast(){
		assertEquals("East", new Nemo(0,0, "East").getDirection());
	}

	@Test public void test05NemoFacesTheCorrectDirectionWhenCreatedFacingWest(){
		assertEquals("West", new Nemo(0,0, "West").getDirection());
	}

	@Test public void test06NemoDoesntMoveWithAVoidCommand() {
		Nemo nemo = new Nemo(0,0, north);
		nemo.command("");
		assertEquals(0, nemo.getXCoordinate());
		assertEquals(0, nemo.getYCoordinate());
		assertEquals(0, nemo.getZCoordinate());
	}

	@Test public void test07NemoMovesDownwardsCorrectly() {
		Nemo nemo = new Nemo(0,0, north);
		nemo.command("d");
		assertEquals(-1, nemo.getZCoordinate());
	}

	@Test public void test08MovingDownwardsOnlyChangesZCoordinate() {
		Nemo nemo = new Nemo(0,0, north);
		nemo.command("d");
		assertEquals(0, nemo.getXCoordinate());
		assertEquals(0, nemo.getYCoordinate());
	}

	@Test public void test09NemoMovesUpwardCorrectly() {
		Nemo nemo = new Nemo(0,0, north);
		nemo.command("du");
		assertEquals(0, nemo.getZCoordinate());
	}

	@Test public void test10MovingUpwardsONlyChangesZCoordinate() {
		Nemo nemo = new Nemo(0,0, north);
		nemo.command("du");
		assertEquals(0, nemo.getXCoordinate());
		assertEquals(0, nemo.getYCoordinate());
	}

	@Test public void test11NemoCannotFly() {
		Nemo nemo = new Nemo(0,0, north);
		nemo.command("u");
		assertEquals(0,nemo.getZCoordinate());
	}

	@Test public void test12NemoCanGoToTheSurfaceAndReturn() {
		Nemo nemo = new Nemo(0, 0, north);
		nemo.command("dudu");
		assertTrue(nemo.isOnTheSurface());
	}
	
	@Test public void test13NemoFacingNorthMovesForwardsCorrectly() {
		Nemo nemo = new Nemo(0,0, north);
		nemo.command("f");
		assertEquals(1, nemo.getYCoordinate());
	}

	@Test public void test14NemoFacingSouthMovesForwardsCorrectly() {
		Nemo nemo = new Nemo(0,0, "South");
		nemo.command("f");
		assertEquals(-1, nemo.getYCoordinate());
	}

	@Test public void test15NemoFacingEastMovesForwardsCorrectly() {
		Nemo nemo = new Nemo(0,0, "East");
		nemo.command("f");
		assertEquals(1, nemo.getXCoordinate());
	}

	@Test public void test16NemoFacingWestMovesForwardsCorrectly() {
		Nemo nemo = new Nemo(0,0, "West");
		nemo.command("f");
		assertEquals(-1, nemo.getXCoordinate());
	}
	
	@Test public void test17NemoFacingNorthTurnsRightCorrectly() {
		Nemo nemo = new Nemo(0,0, north);
		nemo.command("r");
		assertEquals("East", nemo.getDirection());
	}

	@Test public void test18NemoFacingSouthTurnsRightCorrectly() {
		Nemo nemo = new Nemo(0,0, "South");
		nemo.command("r");
		assertEquals("West", nemo.getDirection());
	}

	@Test public void test19NemoFacingEastTurnsRightCorrectly() {
		Nemo nemo = new Nemo(0,0, "East");
		nemo.command("r");
		assertEquals("South", nemo.getDirection());
	}

	@Test public void test20NemoFacingWestTurnsRightCorrectly() {
		Nemo nemo = new Nemo(0,0, "West");
		nemo.command("r");
		assertEquals("North", nemo.getDirection());
	}

	@Test public void test21NemoDoesntMoveWhenTurningRight() {
		Nemo nemo = new Nemo(0, 0, north);
		nemo.command("r");
		assertEquals(0, nemo.getXCoordinate());
		assertEquals(0, nemo.getYCoordinate());
		assertEquals(0, nemo.getZCoordinate());
	}

	@Test public void test22NemoFacingNorthTurnsLeftCorrectly() {
		Nemo nemo = new Nemo(0,0, north);
		nemo.command("l");
		assertEquals("West", nemo.getDirection());
	}

	@Test public void test23NemoFacingSouthTurnsLeftCorrectly() {
		Nemo nemo = new Nemo(0,0, "South");
		nemo.command("l");
		assertEquals("East", nemo.getDirection());
	}

	@Test public void test24NemoFacingEastTurnsLeftCorrectly() {
		Nemo nemo = new Nemo(0,0, "East");
		nemo.command("l");
		assertEquals("North", nemo.getDirection());
	}

	@Test public void test25NemoFacingWestTurnsLeftCorrectly() {
		Nemo nemo = new Nemo(0,0, "West");
		nemo.command("l");
		assertEquals("South", nemo.getDirection());
	}

	@Test public void test26NemoDoesntMoveWhenTurningLeft() {
		Nemo nemo = new Nemo(0, 0, north);
		nemo.command("l");
		assertEquals(0, nemo.getXCoordinate());
		assertEquals(0, nemo.getYCoordinate());
		assertEquals(0, nemo.getZCoordinate());
	}

	@Test public void test27NemoLaunchesTehCapsuleCorrectly() {
		Nemo nemo = new Nemo(0, 0, north);
		nemo.command("m");
		capsuleHasBeenlaunch(nemo);
	}

	@Test public void test28NemoCantLaunchCapsuleBelowSurface(){
		Nemo nemo = new Nemo(0, 0, north);
		nemo.command("dd");
		assertThrowsLike("Nemo cannot launch the capsule this deep.", () -> nemo.command("m") );
		assertTrue(nemo.isCapsuleLauncherLoaded());
	}


	@Test public void test29NemoCantLaunchCapsuleTwice(){
		Nemo nemo = new Nemo(0, 0, north);
		nemo.command("m");
		assertThrowsLike("Nemo cannot launch the capsule twice.", () -> nemo.command("m"));
		capsuleHasBeenlaunch(nemo);
	}

	
// aca hay q hacer pruebas más complejas.

	private void assertThrowsLike(String message, Executable ex) {
		assertEquals(message, assertThrows(RuntimeException.class, ex).getMessage());
	}
	
	private void capsuleHasBeenlaunch(Nemo nemo) {
		assertFalse(nemo.isCapsuleLauncherLoaded());
	}
}
