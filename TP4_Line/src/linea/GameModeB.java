package linea;

public class GameModeB extends GameMode {
    private char modeCharacter = 'B';
    public boolean applies( char mode ) {
        return mode == modeCharacter;
    }

    public boolean checkWinningCondition(Linea line, char player){
        return line.wonDiagonally(player) || line.wonDiagonallyOpposite(player);
    }

}
