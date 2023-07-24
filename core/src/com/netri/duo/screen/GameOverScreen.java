package com.netri.duo.screen;

import static com.badlogic.gdx.scenes.scene2d.Touchable.disabled;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.netri.duo.Main;
import com.netri.duo.tools.GameSettings;
import com.netri.duo.tools.Loc;

public class GameOverScreen implements Screen {

    public static final float SCREEN_WIDTH = Main.SCREEN_WIDTH;
    public static final float SCREEN_HEIGHT = Main.SCREEN_HEIGHT;

    private final Main main;
    private Viewport viewport;
    private OrthographicCamera camera;

    private Skin skin;
    private Stage stage;

    private Table mainTable;
    private Container container;
    private Table table;

    private Image returnButton;
    private Image ballsIcon;
    private Label labelGameOver;
    private Label labelScore;
    private ImageTextButton playAgainButton;
    private Image settingButton;
    private Image achievementsButton;

    private int score;

    public GameOverScreen(Main main, int score) {
        this.main = main;
        this.score = score;
        setMaximumScore();
    }

    @Override
    public void show() {
        setCamera();


        skin = new Skin(Gdx.files.internal("skin.json"));
        Gdx.input.setInputProcessor(stage);

        mainTable = new Table();
        mainTable.setFillParent(true);

        container = new Container();
        container.minWidth(360.0f);
        container.minHeight(800.0f);
        container.maxWidth(360.0f);
        container.maxHeight(800.0f);

        table = new Table();

        returnButton = new Image(skin, "arrow left");
        returnButton.setScaling(Scaling.fit);
        table.add(returnButton).padLeft(24.0f).padTop(32.0f).expandX().align(Align.topLeft).minSize(48.0f);

        ballsIcon = new Image(skin, "Balls");
        ballsIcon.setTouchable(disabled);
        ballsIcon.setScaling(Scaling.fit);
        table.add(ballsIcon).padRight(108.0f).padTop(42.0f).expandX().align(Align.topRight).minWidth(144.0f).minHeight(97.0f).colspan(2);

        table.row();
        labelGameOver = new Label("GAME OVER", skin, "label32");
        labelGameOver.setAlignment(Align.top);
        table.add(labelGameOver).padTop(30.0f).expandX().align(Align.top).minWidth(6.0f).colspan(2);

        table.row();
        labelScore = new Label("SCORE: 22", skin, "label32");
        labelScore.setAlignment(Align.top);
        table.add(labelScore).padTop(69.0f).expandX().align(Align.top).minWidth(6.0f).colspan(2);

        table.row();
        playAgainButton = new ImageTextButton("PLAY AGAIN", skin);
        table.add(playAgainButton).padTop(137.0f).expandX().colspan(2);

        table.row();
        settingButton = new Image(skin, "setting");
        settingButton.setScaling(Scaling.fit);
        table.add(settingButton).padLeft(24.0f).padBottom(34.0f).expand().align(Align.bottomLeft);

        achievementsButton = new Image(skin, "achievements");
        achievementsButton.setScaling(Scaling.fit);
        table.add(achievementsButton).padRight(28.0f).padBottom(28.0f).expand().align(Align.bottomRight);

        setClickListeners();
        container.setActor(table);
        mainTable.add(container);
        stage.addActor(mainTable);
    }

    private void setClickListeners() {
        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new MainMenuScreen(main));
            }
        });

        settingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new SettingScreen(main));
            }
        });

        achievementsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new AchievScreen(main));
            }
        });

        playAgainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new GameScreen(main,GameScreen.difficulty));
            }
        });
    }

    @Override
    public void render(float delta) {
        updateCamera();
        initLocalizedUI();

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
        viewport = new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
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

    private void initLocalizedUI() {
        labelGameOver.setText(Loc.getLoc(Loc.GAME_OVER));
        labelScore.setText(Loc.getLoc(Loc.SCORE) + score);
        playAgainButton.setText(Loc.getLoc(Loc.PLAY_AGAIN));
    }

    private void setMaximumScore() {
        if (GameSettings.getScore() < score) {
            GameSettings.setScore(score);
        }
    }
}
