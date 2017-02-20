/*
    www-users.york.ac.uk/~jwa509/Ass3/RoboticonColony.jar
    This class was added for assessment 3 to implement the casino functionality.
 */

package io.github.teamfractal.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import io.github.teamfractal.MiniGame;
import io.github.teamfractal.RoboticonQuest;
import io.github.teamfractal.screens.CasinoScreen;
import io.github.teamfractal.screens.MarketScreen;

public class CasinoActors extends Table {
    private RoboticonQuest game;
    private MiniGame miniGame;

    private TextButton returnButton;
    private final Label resultAmountText;
    private Label playerStats;

    private final int BET_COST = 300;

    public CasinoActors(final RoboticonQuest game, CasinoScreen screen, final MarketScreen marketScreen) {
        center();
        Skin skin = game.skin;
        this.game = game;
        this.miniGame = new MiniGame();

        // Create UI Components
        playerStats = new Label("Your Resources\n\n\n\n\n", game.skin); //Pad out the initial string with new lines as the label width property does not correctly update

        Label casinoTitleLbl = new Label("Casino", skin);
        Label instructionLblGuess = new Label("Guess a number between 1 and 3. A bet costs 300", skin);
        resultAmountText = new Label("Your winnings will appear here", skin);

        TextButton button1 = new TextButton("1", skin);
        TextButton button2 = new TextButton("2", skin);
        TextButton button3 = new TextButton("3", skin);

        returnButton = new TextButton("Back to the Market Menu", skin);
        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(marketScreen);
            }
        });

        button1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.getPlayer().addMoney(-BET_COST);
                int winningAmount = miniGame.getPrice(miniGame.WinGame(1));
                resultAmountText.setText("You won: " + Integer.toString(winningAmount));
                game.getPlayer().addMoney(winningAmount);
                widgetUpdate();
            }
        });
        button2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.getPlayer().addMoney(-BET_COST);
                int winningAmount = miniGame.getPrice(miniGame.WinGame(2));
                resultAmountText.setText("You won: " + Integer.toString(winningAmount));
                game.getPlayer().addMoney(winningAmount);
                widgetUpdate();
            }
        });
        button3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.getPlayer().addMoney(-BET_COST);
                int winningAmount = miniGame.getPrice(miniGame.WinGame(3));
                resultAmountText.setText("You won: " + Integer.toString(winningAmount));
                game.getPlayer().addMoney(winningAmount);
                widgetUpdate();
            }
        });

        // Adjust properties.
        casinoTitleLbl.setAlignment(Align.left);

        int maxCols = 5;

        add(playerStats).colspan(maxCols);
        row();
        add(casinoTitleLbl).colspan(maxCols);
        row();
        add(instructionLblGuess).colspan(maxCols);
        row();
        add(button1);
        add().width(10);
        add(button2);
        add().width(10);
        add(button3);
        row();
        add(resultAmountText).colspan(maxCols);
        row();
        add(returnButton).colspan(maxCols);
        row();
        pad(20);

        widgetUpdate();
    }

    public void widgetUpdate() {
        String statText = "Your resources:\n\n" +
                " Ore: "    + game.getPlayer().getOre()    + "\n" +
                " Energy: " + game.getPlayer().getEnergy() + "\n" +
                " Food: "   + game.getPlayer().getFood()   + "\n" +
                " Money: "  + game.getPlayer().getMoney()  + "\n" ;

        playerStats.setText(statText);
    }

}