package linea;

public class GameModeC extends GameMode {
    public boolean applies( char mode ) {
        return mode == 'C';
    }

    public boolean checkWinningCondition(Linea line, char player) {
        return line.wonVertically(player) || line.wonHorizontally(player) || line.wonDiagonally(player) || line.wonDiagonallyOpposite(player);
    }


}