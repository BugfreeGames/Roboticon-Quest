package io.github.teamfractal.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import io.github.teamfractal.RoboticonQuest;


public class HomeMainMenu extends Table {
	private RoboticonQuest game;

	public HomeMainMenu(final RoboticonQuest game) {
		this.game = game;

		center();

		final TextButton buttonTest = new TextButton("A button", game.skin);
		buttonTest.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				buttonTest.setText("Button clicked.");
				game.setScreen(game.mapScreen);
			}
		});
		
		final TextButton buttonTest2 = new TextButton(" button 2", game.skin);
		buttonTest.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				buttonTest2.removeActor(buttonTest2);
			}
		});
		buttonTest.pad(10, 10, 0, 10);
		add(buttonTest).pad(10);
		buttonTest2.pad(10);
		add(buttonTest2);
	}
}
