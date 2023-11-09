package linea2;

import java.util.List;

public class GameMode {

    private char modeCharacter;

    public static GameMode gameModeFor( char mode ) {
        return List.of( new GameMode( 'A' ), new GameMode( 'B' ), new GameMode( 'C' )).stream().filter(gameMode -> gameMode.applies( mode )).findAny().get();
    }

    public GameMode( char modeCharacter ) {
        this.modeCharacter = modeCharacter;
    }

    public boolean applies( char mode ) {
        return mode == modeCharacter;
    }
}