package GameTypeState;

import GUIElements.FirstScreen;
import GUIElements.SecondScreen;
import Game.Facade;
import Players.Player;

public class LocalGameState implements GameState {
    private GameStateContext gameStateContext;

    public LocalGameState(GameStateContext gameStateContext) {
        this.gameStateContext = gameStateContext;
    }

    @Override
    public void doAction() {
        Facade facade = gameStateContext.getFirstScreen().getTheFacade();

        facade.getTheDriver().setGameMode(facade.getLOCALGAME());
        facade.getTheDriver().createPlayer(1, Player.LOCALPLAYER, gameStateContext.DEFAULT_PLAYER_NAME);
        facade.getTheDriver().createPlayer(2, Player.LOCALPLAYER, gameStateContext.DEFAULT_PLAYER_NAME);

        //hide the FirstScreen, make a SecondScreen and show it
        gameStateContext.getFirstScreen().hide();
        SecondScreen next = new SecondScreen(facade, gameStateContext.getFirstScreen(), facade.getLOCALGAME());
        next.show();
    }

    @Override
    public void setIPField() {
        gameStateContext.getFirstScreen().getIPField().setEnabled(false);
    }
}
