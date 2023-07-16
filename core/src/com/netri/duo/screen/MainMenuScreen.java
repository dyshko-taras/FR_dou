package com.netri.duo.screen;

import static com.badlogic.gdx.scenes.scene2d.Touchable.disabled;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.netri.duo.Main;

public class MainMenuScreen implements Screen {


    public static final float SCREEN_WIDTH = Main.SCREEN_WIDTH;
    public static final float SCREEN_HEIGHT = Main.SCREEN_HEIGHT;

    private final Main main;
    private Viewport viewport;
    private OrthographicCamera camera;

    private Skin skin;
    private Stage stage;

    private Image ballsIcon;
    private TextField textField;
    private ImageTextButton playButton;
    private Image settingButton;
    private Image achievementsButton;

    public MainMenuScreen(Main main) {
        this.main = main;
    }


    @Override
    public void show() {
        setCamera();

        skin = new Skin(Gdx.files.internal("skin.json"));
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);

        ballsIcon = new Image(skin, "Balls");
        ballsIcon.setTouchable(disabled);
        ballsIcon.setScaling(Scaling.fit);
        table.add(ballsIcon).padTop(68.0f).minWidth(330.0f).minHeight(223.0f).colspan(2);

        table.row();
        textField = new TextField("DOU", skin);
        textField.setAlignment(Align.center);
        table.add(textField).padTop(32.0f).colspan(2);

        table.row();
        playButton = new ImageTextButton("PLAY", skin);
        table.add(playButton).padTop(93.0f).colspan(2);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new SelectDiffScreen(main));
            }
        });


        table.row();
        settingButton = new Image(skin, "setting");
        settingButton.setScaling(Scaling.fit);
        table.add(settingButton).padLeft(24.0f).padBottom(34f).align(Align.bottomLeft);

        achievementsButton = new Image(skin, "achievements");
        achievementsButton.setScaling(Scaling.fit);
        table.add(achievementsButton).padRight(28.0f).padBottom(28).align(Align.bottomRight).minWidth(28.0f).minHeight(48.0f);


        stage.addActor(table);
    }


    @Override
    public void render(float delta) {
        updateCamera();

        drawBackground(new Texture("background.png"));

        stage.act();
        stage.draw();
    }

    public void resize(int width, int height) {
        resizeCamera(width, height);
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

    public void dispose() {
        stage.dispose();
        skin.dispose();
        main.batch.dispose();
    }


    private void setCamera() {
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT,camera);
        stage = new Stage(viewport);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        Gdx.input.setInputProcessor(stage);
    }



    private void updateCamera() {
        ScreenUtils.clear(1, 1, 1, 1);
        viewport.apply();
        main.batch.setProjectionMatrix(viewport.getCamera().combined);
    }

    private void resizeCamera(int width, int height) {
        viewport.update(width, height, true);
        stage.getViewport().update(width, height, true);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    private void drawBackground(Texture texture) {
        float aspectRatio = (float) texture.getWidth() / texture.getHeight();
        float imageHeight = camera.viewportHeight;
        float imageWidth = imageHeight * aspectRatio;
        float x = camera.viewportWidth / 2 - imageWidth / 2;
        float y = 0;

        main.batch.begin();
        main.batch.draw(texture, x, y, imageWidth, imageHeight);
        main.batch.end();
    }


}
