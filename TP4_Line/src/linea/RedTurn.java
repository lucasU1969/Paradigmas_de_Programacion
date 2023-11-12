package linea;

public class RedTurn extends TurnHandler {

    @Override
    public String getEndGameDetail() {
        return "El Azul ha ganado";
    }

    public TurnHandler playRed() {return new BlueTurn();}

    public TurnHandler playBlue() { throw new RuntimeException(PlayerCanOnlyParticipateInTheirTurn);}

}
