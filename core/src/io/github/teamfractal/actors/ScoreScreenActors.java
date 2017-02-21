/*
    www-users.york.ac.uk/~jwa509/Ass3/RoboticonColony.jar
    This class was added as part of assessment 3 to display a winner and a scoreboard at the end of the game.
 */
package io.github.teamfractal.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import io.github.teamfractal.RoboticonQuest;

public class ScoreScreenActors extends Table{
    private RoboticonQuest game;

    public ScoreScreenActors(final RoboticonQuest game) {
        this.game = game;

        //UI elements
        final Label lblGameEnded = new Label("THE GAME HAS ENDED", this.game.skin);
        final Label lblWinnerName = new Label("THE WINNER IS" + this.game.getWinningPlayer().getName() + "!", this.game.skin);
        final TextButton exitButton = new TextButton("Exit ->", game.skin);

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.dispose();
                Gdx.app.exit();
            }
        });

        align(Align.center);

        int span = 4; //This is the largest number of columns on any given row

        //Row 1
        add(lblGameEnded).colspan(span);

        row();

        //Row 2
        add(lblWinnerName);

        //Add some padding between the winner's name and the scoreboard
        row();
        add().height(20);
        row();

        //Iterate through players, and display their score - creating a scoreboard.
        for(int i = 0; i < this.game.playerList.size(); i++) {
            add(new Label(this.game.playerList.get(i).getName() + " had score " + Integer.toString(this.game.playerList.get(i).getScore()), this.game.skin));
            row();
        }

        //Row 11
        add(exitButton).colspan(span);
    }
}
