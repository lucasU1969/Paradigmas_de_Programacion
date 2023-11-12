package linea;

public class GameModeA extends GameMode{

    public boolean applies( char mode ) {
        return mode == 'A';
    }

    public boolean checkWinningCondition(Linea line, char player) {
        return line.wonVertically(player) || line.wonHorizontally(player);
    }

}
