package com.netri.duo.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class AchievScreen extends ApplicationAdapter {
    private Skin skin;

    private Stage stage;

    public void create() {
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin.json"));
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);

        Container container = new Container();
        container.minWidth(360.0f);
        container.minHeight(800.0f);
        container.maxWidth(360.0f);
        container.maxHeight(800.0f);

        Table table1 = new Table();

        Image image = new Image(skin, "arrow left");
        image.setScaling(Scaling.fit);
        table1.add(image).padLeft(24.0f).padTop(32.0f).expandX().align(Align.topLeft).minSize(48.0f);

        image = new Image(skin, "Balls");
        image.setTouchable(disabled);
        image.setScaling(Scaling.fit);
        table1.add(image).padRight(108.0f).padTop(42.0f).expandX().align(Align.topRight).minWidth(144.0f).minHeight(97.0f).colspan(2);

        table1.row();
        Label label = new Label("ACHIEVEMENTS", skin, "label32");
        label.setAlignment(Align.top);
        table1.add(label).padTop(30.0f).expandX().align(Align.top).minWidth(6.0f).colspan(2);

        table1.row();
        Container container1 = new Container();
        container1.fill(true);
        container1.minWidth(305.0f);
        container1.minHeight(45.0f);
        container1.maxWidth(305.0f);
        container1.maxHeight(45.0f);

        Stack stack = new Stack();

        ProgressBar progressBar = new ProgressBar(-2.0f, 10.0f, 1.0f, false, skin);
        progressBar.setValue(8.0f);
        stack.addActor(progressBar);

        Table table2 = new Table();

        label = new Label("10/10", skin, "label24");
        label.setAlignment(Align.center);
        table2.add(label).padLeft(18.0f).padBottom(6.0f).expand().align(Align.bottomLeft);

        label = new Label("PLAY 10 TIMES", skin, "label16");
        label.setAlignment(Align.right);
        table2.add(label).padRight(30.0f).padBottom(11.0f).expand().align(Align.bottomRight);
        stack.addActor(table2);
        container1.setActor(stack);
        table1.add(container1).padLeft(29.0f).padTop(128.0f).expandX().align(Align.topLeft).colspan(2);

        table1.row();
        container1 = new Container();
        container1.fill(true);
        container1.minWidth(305.0f);
        container1.minHeight(45.0f);
        container1.maxWidth(305.0f);
        container1.maxHeight(45.0f);

        stack = new Stack();

        progressBar = new ProgressBar(-2.0f, 10.0f, 1.0f, false, skin);
        progressBar.setValue(8.0f);
        stack.addActor(progressBar);

        table2 = new Table();

        label = new Label("25/20", skin, "label24");
        label.setAlignment(Align.center);
        table2.add(label).padLeft(18.0f).padBottom(6.0f).expand().align(Align.bottomLeft);

        label = new Label("GET 20 SCORE", skin, "label16");
        label.setAlignment(Align.right);
        table2.add(label).padRight(30.0f).padBottom(11.0f).expand().align(Align.bottomRight);
        stack.addActor(table2);
        container1.setActor(stack);
        table1.add(container1).padLeft(29.0f).padTop(59.0f).expandX().align(Align.topLeft).colspan(2);

        table1.row();
        container1 = new Container();
        container1.fill(true);
        container1.minWidth(305.0f);
        container1.minHeight(45.0f);
        container1.maxWidth(305.0f);
        container1.maxHeight(45.0f);

        stack = new Stack();

        progressBar = new ProgressBar(-4.0f, 20.0f, 1.0f, false, skin);
        stack.addActor(progressBar);

        table2 = new Table();

        label = new Label("25/60", skin, "label24");
        label.setAlignment(Align.center);
        table2.add(label).padLeft(18.0f).padBottom(6.0f).expand().align(Align.bottomLeft);

        label = new Label("GET 60 SCORE", skin, "label16");
        label.setAlignment(Align.right);
        table2.add(label).padRight(30.0f).padBottom(11.0f).expand().align(Align.bottomRight);
        stack.addActor(table2);
        container1.setActor(stack);
        table1.add(container1).padLeft(29.0f).padTop(59.0f).expandX().align(Align.topLeft).colspan(2);

        table1.row();
        image = new Image(skin, "setting");
        image.setScaling(Scaling.fit);
        table1.add(image).padLeft(24.0f).padBottom(34.0f).expand().align(Align.bottomLeft);

        image = new Image(skin, "achievements");
        image.setScaling(Scaling.fit);
        table1.add(image).padRight(28.0f).padBottom(28.0f).expand().align(Align.bottomRight);
        container.setActor(table1);
        table.add(container);
        stage.addActor(table);
    }

    public void render() {
        Gdx.gl.glClearColor(0.5568628f, 0.7647059f, 0.24705882f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
