package nemo;

public class LaunchCapsule extends Commands {

	public char getCommandAsChar() {
		return 'm';
	}

	public void exeucuteAction(Nemo submarine) {
		submarine.launchCapsule();
	}

}
