package nemo;

public class MoveForward extends Commands {

	public char getCommandAsChar() {
		return 'f';
	}

	public void exeucuteAction(Nemo submarine) {
		submarine.moveForward();
	}

}
