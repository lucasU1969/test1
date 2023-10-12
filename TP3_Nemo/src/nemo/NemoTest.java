package nemo;

//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.*;

public class NemoTest {

    @Test public void test00NemoIsOnTheSurfaceWhenCreated() {
        assertTrue(new Nemo(0,0, Directions.north()).isOnTheSurface());
    }

    @Test public void test01CordinatesAreCorrectWhenNemoIsCreated() {
        assertEquals(0, new Nemo(0,0, Directions.north()).getXCoordinate());
        assertEquals(0, new Nemo(0,0, Directions.north()).getYCoordinate());
    }

    @Test public void test02NemoFacesTheCorrectDirectionWhenCreated() {
        assertEquals("North", new Nemo(0,0, Directions.north()).getDirection().toString());
    }

    @Test public void test03NemoDoesntMoveWithNoCommands() {
        Nemo nemo = new Nemo(0,0, Directions.north());
        nemo.command("");
        assertEquals(0, nemo.getXCoordinate());
        assertEquals(0, nemo.getYCoordinate());
    }

    @Test public void test04NemoMovesDownwardsCorrectly() {
        Nemo nemo = new Nemo(0,0, Directions.north());
        nemo.command("d");
        nemo.command("d");
        assertEquals(-2, -(nemo.depth.size()-1));
    }
    @Test public void test05NemoMovesUpwardCorrectly() {
        Nemo nemo = new Nemo(0,0, Directions.north());
        nemo.command("uu");
        assertEquals(0, nemo.depth.size()-1);
    }

    @Test public void test06NemoMovesForwardsCorrectly() {
        Nemo nemo = new Nemo(0,0, Directions.north());
        nemo.command("f");
        assertEquals(1,  nemo.position[1]);
    }

    @Test public void test07NemoTurnsRightCorrectly() {
        Nemo nemo = new Nemo(0,0, Directions.north());
        nemo.command("r");
        assertEquals("East", nemo.getDirection().toString());
    }

    @Test public void test08NemoTurnsLeftCorrectly() {
        Nemo nemo = new Nemo(0,0, Directions.north());
        nemo.command("l");
        assertEquals("West", nemo.getDirection().toString());
    }

    @Test public void test09NemoDoesntMoveWhenChangingDirection() {
        Nemo nemo = new Nemo(0, 0, Directions.north());
        nemo.command("l");
        assertEquals(0, nemo.position[0]);
        assertEquals(0, nemo.position[1]);
    }

    @Test public void test10NemoCannotMoveUpwardInTheSurface() {
        Nemo nemo = new Nemo(0, 0, Directions.north());
        nemo.command("u");
        assertEquals(0, nemo.getZCoordinate());

    }

//    @Test public void test11NemoLaunchCapsuleCorrectly() {
//        Nemo nemo = new Nemo(0, 0, Directions.north());
//        nemo.command("dm");
//        assertTrue(nemo.launchCapsule());
//    }
//
//    @Test public void test12NemoCantLaunchCapsuleBelowSurface(){
//        Nemo nemo = new Nemo(0, 0, Directions.north());
//        nemo.command("dd");
//        assertEquals("Nemo cannot launch the capsule this deep.", assertThrows(RuntimeException.class, () -> nemo.command("m")).getMessage());
//    }
//    @Test public void test13NemoCantLaunchCapsuleTwice(){
//        Nemo nemo = new Nemo(0, 0, Directions.north());
//        nemo.command("dm");
//        assertFalse(nemo.launchCapsule());
//        assertFalse(nemo.isCapsuleInNemo);
//    }
}

