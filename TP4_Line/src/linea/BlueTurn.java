package linea;

public class
BlueTurn extends TurnHandler{

    @Override
    public String getEndGameDetail() {
        return "El Rojo ha ganado";
    }

    public TurnHandler playRed() { throw new RuntimeException(PlayerCanOnlyParticipateInTheirTurn);}

    public TurnHandler playBlue() { return new RedTurn();}



}
