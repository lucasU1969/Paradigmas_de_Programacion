package nemo;

public class MoveUpwards extends Commands {

	public char getCommandAsChar() {
		return 'u';
	}

	public void exeucuteAction(Nemo submarine) {
		submarine.moveUpwards();
		
	}

}
