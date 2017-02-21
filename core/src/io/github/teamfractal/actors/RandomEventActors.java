/*
    www-users.york.ac.uk/~jwa509/Ass3/RoboticonColony.jar
 */
package io.github.teamfractal.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import io.github.teamfractal.RoboticonQuest;
import io.github.teamfractal.entity.RandomEvent;
import io.github.teamfractal.screens.*;

public class RandomEventActors extends Table {
    private RoboticonQuest game;

    public RandomEventActors(final RoboticonQuest game, RandomEventScreen screen, RandomEvent event) {
        center();
        Label eventTitle = new Label(event.getEventName(), game.skin);
        Label description = new Label(event.getEventDescription(), game.skin);
        Label screenTitle = new Label(game.getPlayer().getName() + ", an event has shocked your colony!", game.skin);
        TextButton nextBtn = new TextButton("Continue ->", game.skin);

        nextBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.nextPhase();
            }
        });

        Table descTable = new Table();
        description.setWidth(Gdx.graphics.getWidth()-100);
        description.setEllipsis(true);
        descTable.add(description);

        add(screenTitle);
        row();
        add(eventTitle);
        row();
        add(descTable);
        row();
        add(nextBtn);
    }

}