package linea2;

public class BlueTurn extends TurnHandler{

    public TurnHandler playRed() { throw new RuntimeException();}

    public TurnHandler playBlue() { return new RedTurn();}

    public boolean finished() {return false;}
}
