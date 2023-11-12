package linea;

public abstract class TurnHandler {
    public abstract String getEndGameDetail();

    public static String PlayerCanOnlyParticipateInTheirTurn = "Player can only participate in their turn";
    public static String ItIsNotPossibleToPlayAfterTheGameIsFinished = "It is not possible to play after the game is finished";

    public abstract TurnHandler playRed();
    public abstract TurnHandler playBlue();
    public String turnDetail() {
        return "";
    }

}