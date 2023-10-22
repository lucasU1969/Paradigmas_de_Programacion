package nemo;

import java.util.List;

public abstract class Commands {
	private static List<Commands> availableCommands = List.of( new MoveUpwards(), new MoveDownwards(), new MoveForward(), new TurnLeft(),new TurnRight(),new LaunchCapsule());
	
	public static Commands getCommandFor(char commandAsChar) {
		return availableCommands.stream()
								.filter( each -> ( each.getCommandAsChar() ) == commandAsChar )
								.findFirst()	
								.get();
	}
	public abstract char getCommandAsChar();
	public abstract void exeucuteAction(Nemo submarine);

}
