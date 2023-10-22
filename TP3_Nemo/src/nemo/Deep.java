package nemo;

public class Deep extends DepthState {
	
	private int depth;
	public static String NemoExplodedAfterReleasingTheCapsuleBellowTheSafeDepth = "Nemo exploded after releasing the capsule bellow the safe depth";

	public Deep(int newDepth) {
		depth = newDepth;
	}

	public DepthState moveDownwards() {
		return new Deep(depth - 1);
	}

	public void launchCapsule() {
		throw new RuntimeException(NemoExplodedAfterReleasingTheCapsuleBellowTheSafeDepth);
	}
	
	public int getDepth() {
		return depth;
	}
}
