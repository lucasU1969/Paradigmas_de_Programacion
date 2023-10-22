package nemo;

public class Deep extends DepthState {
	
	private int depth;
	public static String NemoCannotLaunchTheCapsuleThisDeep = "Nemo cannot launch the capsule this deep.";

	public Deep(int newDepth) {
		depth = newDepth;
	}

	public DepthState moveDownwards() {
		return new Deep(depth - 1);
	}

	public void launchCapsule() {
		throw new RuntimeException(NemoCannotLaunchTheCapsuleThisDeep);
	}
	
	public int getDepth() {
		return depth;
	}
}
