package nemo;

public class MoveDownwards extends Commands {

	public char getCommandAsChar() {
		return 'd';
	}
	
	public void exeucuteAction(Nemo submarine) {
		submarine.moveDownwards();
	}

}
