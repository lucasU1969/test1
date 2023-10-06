package nemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NemoTest {
	@Test public void test00NemoIsCreatedCorrectly() {
		Nemo nemo = new Nemo(0, 0, 0, "N");
		assertEquals(0, nemo.getXCoordinate());
		assertEquals(0, nemo.getYCoordinate());
		assertEquals(0, nemo.getZCoordinate());
		assertEquals("N", nemo.getDirection());
	}

	@Test public void test01NemoMovesUpwardCorrectly() {
		
	}

	@Test public void test02NemoMovesDownwardsCorrectly() {
		
	}
	
	@Test public void test03NemoMovesForwardsCorrectly() {

	}
	
	@Test public void test04NemoFacesTheCorrectDirection() {
		
	}
	
	@Test public void test05NemoCannotMoveUpwardInTheSurface() {
		
	}
	
	@Test public void test06NemoChangesDirectionCorrectly() {

	}

	@Test public void test07NemoCantLaunchCapsuleBelowSurface(){

	}
}
