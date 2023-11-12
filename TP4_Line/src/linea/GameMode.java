package linea;

import java.util.List;

public abstract class GameMode {

    protected char modeCharacter;

    public static GameMode gameModeFor( char mode ) {
        return List.of( new GameModeA(), new GameModeB(), new GameModeC()).stream().filter(gameMode -> gameMode.applies( mode )).findAny().get();
    }

    public boolean applies( char mode ) {
        return mode == modeCharacter;
    }
    public abstract boolean checkWinningCondition(Linea line, char player);
}