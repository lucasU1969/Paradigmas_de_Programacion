package linea;

public class GameModeA extends GameMode{
    private char modeCharacter = 'A';

    public boolean applies( char mode ) {
        return mode == modeCharacter;
    }

    public boolean checkWinningCondition(Linea line, char player) {
        return line.wonVertically(player) || line.wonHorizontally(player);
    }

}
