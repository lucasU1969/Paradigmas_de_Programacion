package nemo;

import java.util.ArrayList;

public class Nemo {
	
	private Point2D position;
	private Directions direction;
	private ArrayList<DepthState> depthHandler = new ArrayList<DepthState>();
	
	public Nemo(int newXCoordinate, int newYCoordinate, String newDirection) {
		direction = Directions.getStringAsADirection(newDirection);
		position = new Point2D(newXCoordinate, newYCoordinate);
		depthHandler.add(new Surface());
	}

	public void moveUpwards() {
		depthHandler.remove( getCurrentDepthState().moveUpwards() );
	}
	
	public void moveDownwards(){
		depthHandler.add( getCurrentDepthState().moveDownwards() );
	}
	
	public void moveForward() {
		position = position.sum( direction.directionVector() );
	}

	public void turnLeft() {
		direction = direction.turnLeft();
	}

	public void turnRight() {
		direction = direction.turnRight();
	}
	
	public void launchCapsule() {
		getCurrentDepthState().launchCapsule();
	}

	public void runStringOfCommands(String commands) {
		commands.chars().forEach( command -> executeThisCharCommand( ( char ) command ) );
	}
	
	public void executeThisCharCommand( char commandAsChar ) {
		Commands.getCommandFor(commandAsChar).exeucuteAction(this);
	}
	
	public int getXCoordinate() {
		return position.getXCoordinate();
	}
	
	public int getYCoordinate() {
		return position.getYCoordinate();
	}
	
	public int getZCoordinate() {
		return getCurrentDepthState().getDepth();
	}
	
	public String getDirection() {
		return direction.toString();
	}
	
	public boolean isOnTheSurface() {
		return getZCoordinate() == 0;
	}

	private DepthState getCurrentDepthState() {
		return depthHandler.get( depthHandler.size() - 1 );
	}
}
