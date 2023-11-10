package linea;

public class BlueTurn extends TurnHandler{

    public TurnHandler playRed() { throw new RuntimeException(PlayerCanOnlyParticipateInTheirTurn);}

    public TurnHandler playBlue() { return new RedTurn();}
}
