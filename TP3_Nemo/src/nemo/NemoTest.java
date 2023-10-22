package nemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class NemoTest {
	String launchCapsule;
	String turnLeft;
	String turnRight;
	String moveForward;
	String moveUpwards;
	String moveDownwards;
	String emptyCommand;
	String west;
	String east;
	String south;
	String north;
	Nemo nemoInTheOriginFacingNorth;
	Nemo nemoInTheOriginFacingSouth;
	Nemo nemoInTheOriginFacingEast;
	Nemo nemoInTheOriginFacingWest;
	
	@BeforeEach void setUp() {
		north = "North";
		south = "South";
		east = "East";
		west = "West";
		nemoInTheOriginFacingNorth = new Nemo(0,0, north);
		nemoInTheOriginFacingSouth = new Nemo(0,0, south);
		nemoInTheOriginFacingEast = new Nemo(0,0, east);
		nemoInTheOriginFacingWest = new Nemo(0,0, west);
		emptyCommand = "";
		moveDownwards = "d";
		moveUpwards = "u";
		moveForward = "f";
		turnLeft = "l";
		turnRight = "r";
		launchCapsule = "m";
	}

	@Test public void test00NemoIsOnTheSurfaceWhenIsCreated() {
		assertTrue(nemoInTheOriginFacingNorth.isOnTheSurface());
	}

	@Test public void test01CordinatesAreCorrectWhenNemoIsCreated() {
		assertIsOnTheOrigin(nemoInTheOriginFacingNorth);
	}

	@Test public void test02NemoFacesTheCorrectDirectionWhenIsCreatedFacingNorth() {
		assertEquals(north, nemoInTheOriginFacingNorth.getDirection());
	}

	@Test public void test03NemoFacesTheCorrectDirectionWhenCreatedFacingSouth() {
		assertEquals(south, nemoInTheOriginFacingSouth.getDirection());
	}

	@Test public void test04NemoFAcesTheCorrectDirectionWhenCreatedFacingEast(){
		assertEquals(east, nemoInTheOriginFacingEast.getDirection());
	}

	@Test public void test05NemoFacesTheCorrectDirectionWhenCreatedFacingWest(){
		assertEquals(west, nemoInTheOriginFacingWest.getDirection());
	}

	@Test public void test06NemoDoesntMoveWithAnEmptyCommand() {
		nemoInTheOriginFacingNorth.runStringOfCommands(emptyCommand);
		assertIsOnTheOrigin(nemoInTheOriginFacingNorth);
	}

	@Test public void test07NemoMovesDownwardsCorrectly() {
		nemoInTheOriginFacingNorth.runStringOfCommands(moveDownwards);
		assertPosition(nemoInTheOriginFacingNorth, 0, 0, -1);
	}

	@Test public void test08NemoMovesUpwardCorrectly() {
		nemoInTheOriginFacingNorth.runStringOfCommands(moveDownwards + moveUpwards);
		assertIsOnTheOrigin(nemoInTheOriginFacingNorth);
	}

	@Test public void test09NemoCantFly() {
		nemoInTheOriginFacingNorth.runStringOfCommands(moveUpwards);
		assertIsOnTheOrigin(nemoInTheOriginFacingNorth);
	}

	@Test public void test10NemoCanGoToTheSurfaceAndReturn() {
		nemoInTheOriginFacingNorth.runStringOfCommands(moveDownwards + moveUpwards + moveDownwards + moveUpwards);
		assertIsOnTheOrigin(nemoInTheOriginFacingNorth);
	}
	
	@Test public void test11NemoFacingNorthMovesForwardsCorrectly() {
		nemoInTheOriginFacingNorth.runStringOfCommands(moveForward);
		assertPosition(nemoInTheOriginFacingNorth, 0, 1, 0);
	}

	@Test public void test12NemoFacingSouthMovesForwardsCorrectly() {
		nemoInTheOriginFacingSouth.runStringOfCommands(moveForward);
		assertPosition(nemoInTheOriginFacingSouth, 0, -1, 0);
	}

	@Test public void test13NemoFacingEastMovesForwardsCorrectly() {
		nemoInTheOriginFacingEast.runStringOfCommands(moveForward);
		assertPosition(nemoInTheOriginFacingEast, 1, 0, 0);
	}

	@Test public void test14NemoFacingWestMovesForwardsCorrectly() {
		nemoInTheOriginFacingWest.runStringOfCommands(moveForward);
		assertPosition(nemoInTheOriginFacingWest, -1, 0, 0);
	}
	
	@Test public void test15NemoFacingNorthTurnsRightCorrectly() {
		nemoInTheOriginFacingNorth.runStringOfCommands(turnRight);
		assertEquals(east, nemoInTheOriginFacingNorth.getDirection());
	}

	@Test public void test16NemoFacingSouthTurnsRightCorrectly() {
		nemoInTheOriginFacingSouth.runStringOfCommands(turnRight);
		assertEquals(west, nemoInTheOriginFacingSouth.getDirection());
	}

	@Test public void test17NemoFacingEastTurnsRightCorrectly() {
		nemoInTheOriginFacingEast.runStringOfCommands(turnRight);
		assertEquals(south, nemoInTheOriginFacingEast.getDirection());
	}

	@Test public void test18NemoFacingWestTurnsRightCorrectly() {
		nemoInTheOriginFacingWest.runStringOfCommands(turnRight);
		assertEquals(north, nemoInTheOriginFacingWest.getDirection());
	}

	@Test public void test19NemoDoesntMoveWhenTurningRight() {
		nemoInTheOriginFacingNorth.runStringOfCommands(turnRight);
		assertIsOnTheOrigin(nemoInTheOriginFacingNorth);
	}

	@Test public void test20NemoFacingNorthTurnsLeftCorrectly() {
		nemoInTheOriginFacingNorth.runStringOfCommands(turnLeft);
		assertEquals(west, nemoInTheOriginFacingNorth.getDirection());
	}

	@Test public void test21NemoFacingSouthTurnsLeftCorrectly() {
		nemoInTheOriginFacingSouth.runStringOfCommands(turnLeft);
		assertEquals(east, nemoInTheOriginFacingSouth.getDirection());
	}

	@Test public void test22NemoFacingEastTurnsLeftCorrectly() {
		nemoInTheOriginFacingEast.runStringOfCommands(turnLeft);
		assertEquals(north, nemoInTheOriginFacingEast.getDirection());
	}

	@Test public void test23NemoFacingWestTurnsLeftCorrectly() {
		nemoInTheOriginFacingWest.runStringOfCommands(turnLeft);
		assertEquals(south, nemoInTheOriginFacingWest.getDirection());
	}

	@Test public void test24NemoDoesntMoveWhenTurningLeft() {
		nemoInTheOriginFacingNorth.runStringOfCommands(turnLeft);
		assertIsOnTheOrigin(nemoInTheOriginFacingNorth);
	}
	
	@Test public void test25LaunchingTheCapsuleInSurfaceDoesNotChangeDirection() {
		nemoInTheOriginFacingNorth.runStringOfCommands(launchCapsule);
		assertEquals(north, nemoInTheOriginFacingNorth.getDirection());
	}
	
	@Test public void test26LaunchingTheCapsuleInSurfaceDoesNotCangePosition() {
		nemoInTheOriginFacingNorth.runStringOfCommands(launchCapsule);
		assertIsOnTheOrigin(nemoInTheOriginFacingNorth);
	}
	
	@Test public void test27LaunchingTheCapsuleJustBellowSurfaceDoesNotChangeDirection() {
		nemoInTheOriginFacingNorth.runStringOfCommands(moveDownwards + launchCapsule);
		assertEquals(north, nemoInTheOriginFacingNorth.getDirection());
	}
	
	@Test public void test28LaunchingTheCapsuleJustBellowSurfaceDoesNotChangePosiiton() {
		nemoInTheOriginFacingNorth.runStringOfCommands(moveDownwards + launchCapsule);
		assertPosition(nemoInTheOriginFacingNorth, 0, 0, -1);
	}

	@Test public void test29NemoCantLaunchCapsuleDeep(){
		nemoInTheOriginFacingNorth.runStringOfCommands(moveDownwards + moveDownwards);
		assertThrowsLike(Deep.NemoCannotLaunchTheCapsuleThisDeep, () -> nemoInTheOriginFacingNorth.runStringOfCommands(launchCapsule));
		
	}
	private void assertThrowsLike(String message, Executable ex) {
		assertEquals(message, assertThrows(RuntimeException.class, ex).getMessage());}
	private void assertPosition(Nemo submarine, int xCoordinate, int yCoordinate, int zCoordinate) {
		assertEquals(xCoordinate, submarine.getXCoordinate());
		assertEquals(yCoordinate, submarine.getYCoordinate());
		assertEquals(zCoordinate, submarine.getZCoordinate());}
	private void assertIsOnTheOrigin(Nemo submarine) {
		assertPosition(submarine, 0, 0, 0);}
}
