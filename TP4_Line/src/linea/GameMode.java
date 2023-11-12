package linea;

import java.util.List;

public abstract class GameMode {

    public static GameMode gameModeFor( char mode ) {
        return List.of( new GameModeA(), new GameModeB(), new GameModeC()).stream().filter(gameMode -> gameMode.applies( mode )).findAny().get();
    }

    public abstract boolean applies( char mode );
    public abstract boolean checkWinningCondition(Linea line, char player);
}