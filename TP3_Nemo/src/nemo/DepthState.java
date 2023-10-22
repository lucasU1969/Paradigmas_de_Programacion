package nemo;

public abstract class DepthState {
	
	public abstract int getDepth();
    public abstract DepthState moveDownwards();
    public DepthState moveUpwards() {
    	return this;
    }
    public void launchCapsule() {}
}
