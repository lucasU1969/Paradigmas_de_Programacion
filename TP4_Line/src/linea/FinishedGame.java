package linea;

public class FinishedGame extends TurnHandler{

    private final String detail;

    public FinishedGame(String detail ) {
        this.detail = detail;
    }

    public String getEndGameDetail() {
        return null;
    }

    public TurnHandler playRed() {
        throw new RuntimeException(ItIsNotPossibleToPlayAfterTheGameIsFinished);
    }

    public TurnHandler playBlue() {
        throw new RuntimeException(ItIsNotPossibleToPlayAfterTheGameIsFinished);
    }

    public String turnDetail() {
        return detail;
    }
}
