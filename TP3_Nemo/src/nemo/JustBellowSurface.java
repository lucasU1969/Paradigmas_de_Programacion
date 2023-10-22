package nemo;

public class JustBellowSurface extends DepthState {

	public DepthState moveDownwards() {
		return new Deep(-2);
	}
	
	public int getDepth() {
		return -1;
	}
}
