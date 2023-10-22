package nemo;

public class Surface extends DepthState {

	public DepthState moveDownwards() {
		return new JustBellowSurface();
	}
		
	public DepthState moveUpwards() {
		return null;
	}
	
	public int getDepth() {
		return 0;
	}
}
