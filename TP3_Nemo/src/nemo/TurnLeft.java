package nemo;

public class TurnLeft extends Commands {

	public char getCommandAsChar() {
		return 'l';
	}

	public void exeucuteAction(Nemo submarine) {
		submarine.turnLeft();
	}

}
