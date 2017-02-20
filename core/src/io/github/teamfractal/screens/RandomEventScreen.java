package io.github.teamfractal.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.github.teamfractal.RoboticonQuest;
import io.github.teamfractal.actors.RandomEventActors;
import io.github.teamfractal.entity.RandomEvent;


public class RandomEventScreen implements Screen {
    final RoboticonQuest game;
    final Stage stage;
    final Table table;
    private final RandomEventActors eventActors;

    public RandomEventScreen(final RoboticonQuest game, RandomEvent event) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.table = new Table();
        table.setFillParent(true);
        
        eventActors = new RandomEventActors(game, this, event); // generates actors for the screen
        table.add(eventActors);
        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {


    }

    @Override
    public void hide() {


    }

    @Override
    public void dispose() {
        stage.dispose();

    }
    public Stage getStage(){
        return this.stage;
    }
}
