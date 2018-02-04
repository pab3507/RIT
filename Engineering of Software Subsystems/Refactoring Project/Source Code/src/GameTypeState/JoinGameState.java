package GameTypeState;

import GUIElements.SecondScreen;
import Game.Facade;
import Players.Player;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class JoinGameState implements GameState {
    private GameStateContext gameStateContext;

    public JoinGameState(GameStateContext gameStateContext) {
        this.gameStateContext = gameStateContext;
    }

    @Override
    public void doAction() {
        //set up to join a game
        Facade facade = gameStateContext.getFirstScreen().getTheFacade();

        facade.getTheDriver().setGameMode(facade.getCLIENTGAME());
        facade.getTheDriver().createPlayer(1, Player.NETWORKPLAYER, gameStateContext.DEFAULT_PLAYER_NAME);
        facade.getTheDriver().createPlayer(2, Player.NETWORKPLAYER, gameStateContext.DEFAULT_PLAYER_NAME);

        //try to connect
        try {

            //create a URL from the IP address in the IPfield
            URL address = new URL("http://" + gameStateContext.getFirstScreen().getIPField().getText());

            //set the host
            facade.getTheDriver().setHost(address);
            //catch any exceptions
        } catch (MalformedURLException x) {
            JOptionPane.showMessageDialog(null,
                    "Invalid host address",
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE);

            //hide the FirstScreen, make and show the Second screen
            gameStateContext.getFirstScreen().hide();
            SecondScreen next = new SecondScreen(facade, gameStateContext.getFirstScreen(), facade.getCLIENTGAME());
            next.show();
        }//end of networking catch statement
    }

    @Override
    public void setIPField() {
        gameStateContext.getFirstScreen().getIPField().setEnabled(true);
    }
}
