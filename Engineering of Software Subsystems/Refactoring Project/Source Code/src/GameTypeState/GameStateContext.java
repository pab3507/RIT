package GameTypeState;

import GUIElements.FirstScreen;
import Game.Driver;

public class GameStateContext {
    private final String JOIN = "join";
    private final String LOCAL = "local";
    private final String HOST = "host";
    private final String OK = "ok";
    private final String CANCEL = "cancel";
    protected final String DEFAULT_PLAYER_NAME = "UnNamedPlayer";

    // GameState variables
    private FirstScreen firstScreen;
    private GameState gameState;
    private GameState localGameState;
    private GameState joinGameState;
    private GameState hostGameState;

    public GameStateContext(FirstScreen firstScreen) {
        this.firstScreen = firstScreen;
        localGameState = new LocalGameState(this);
        joinGameState = new JoinGameState(this);
        hostGameState = new HostGameState(this);
        gameState = localGameState;
    }

    public void doAction(String type) {
        switch (type) {
            case LOCAL:
                gameState = localGameState;
                break;
            case JOIN:
                gameState = joinGameState;
                break;
            case HOST:
                gameState = hostGameState;
                break;
            case OK:
                gameState.doAction();
                break;
            case CANCEL:
                System.exit(0);
        }
    }

    public void setIPField() {
        gameState.setIPField();
    }

    public FirstScreen getFirstScreen() {
        return firstScreen;
    }
}
