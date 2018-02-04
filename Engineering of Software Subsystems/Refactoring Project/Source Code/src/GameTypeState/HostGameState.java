package GameTypeState;

import GUIElements.SecondScreen;
import Game.Facade;
import Players.Player;

public class HostGameState implements GameState {
    private GameStateContext gameStateContext;

    public HostGameState(GameStateContext gameStateContext) {
        this.gameStateContext = gameStateContext;
    }

    @Override
    public void doAction() {
        //set up to host a game
        Facade facade = gameStateContext.getFirstScreen().getTheFacade();

        facade.getTheDriver().setGameMode(facade.getHOSTGAME());
        facade.getTheDriver().createPlayer(1, Player.NETWORKPLAYER, gameStateContext.DEFAULT_PLAYER_NAME);
        facade.getTheDriver().createPlayer(2, Player.NETWORKPLAYER, gameStateContext.DEFAULT_PLAYER_NAME);

        //hide the FirstScreen, make the SecondScreen and show it
        gameStateContext.getFirstScreen().hide();
        SecondScreen next = new SecondScreen(facade, gameStateContext.getFirstScreen(), facade.getHOSTGAME());
        next.show();
    }

    @Override
    public void setIPField() {
        gameStateContext.getFirstScreen().getIPField().setEnabled(false);
    }
}
