package nemo;

public class TurnRight extends Commands {

	public char getCommandAsChar() {
		return 'r';
	}

	public void exeucuteAction(Nemo submarine) {
		submarine.turnRight();
	}

}
