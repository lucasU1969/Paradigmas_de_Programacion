package linea;

public class GameModeB extends GameMode {
    public boolean applies( char mode ) {
        return mode == 'B';
    }

    public boolean checkWinningCondition(Linea line, char player){
        return line.wonDiagonally(player) || line.wonDiagonallyOpposite(player);
    }

}
