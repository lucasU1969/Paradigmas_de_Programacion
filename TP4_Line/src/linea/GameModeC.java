package linea;

public class GameModeC extends GameMode {
    protected char modeCharacter = 'C';
    public boolean applies( char mode ) {
        return mode == modeCharacter;
    }

    public boolean checkWinningCondition(Linea line, char player) {
        return line.wonVertically(player) || line.wonHorizontally(player) || line.wonDiagonally(player) || line.wonDiagonallyOpposite(player);
    }


}