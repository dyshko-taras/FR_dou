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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
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

public class AchievScreen implements Screen {
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
    private Label labelAchiev;

    private ProgressBar progressBar1;
    private Label labelProgbar1num;
    private Label labelProgbar1text;

    private ProgressBar progressBar2;
    private Label labelProgbar2num;
    private Label labelProgbar2text;

    private ProgressBar progressBar3;
    private Label labelProgbar3num;
    private Label labelProgbar3text;

    private Image settingButton;
    private Image achievementsButton;


    public AchievScreen(Main main) {
        this.main = main;
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
        labelAchiev = new Label("ACHIEVEMENTS", skin, "label32");
        labelAchiev.setAlignment(Align.top);
        table.add(labelAchiev).padTop(30.0f).expandX().align(Align.top).minWidth(6.0f).colspan(2);

        table.row();
        Container container1 = new Container();
        container1.fill(true);
        container1.minWidth(305.0f);
        container1.minHeight(45.0f);
        container1.maxWidth(305.0f);
        container1.maxHeight(45.0f);

        Stack stack = new Stack();

        progressBar1 = new ProgressBar(-1.0f, 10.0f, 1.0f, false, skin);
        progressBar1.setValue(0);
        stack.addActor(progressBar1);

        Table table2 = new Table();

        labelProgbar1num = new Label("10/10", skin, "label24");
        labelProgbar1num.setAlignment(Align.center);
        table2.add(labelProgbar1num).padLeft(18.0f).padBottom(6.0f).expand().align(Align.bottomLeft);

        labelProgbar1text = new Label("PLAY 10 TIMES", skin, "label16");
        labelProgbar1text.setAlignment(Align.right);
        table2.add(labelProgbar1text).padRight(30.0f).padBottom(11.0f).expand().align(Align.bottomRight);
        stack.addActor(table2);

        container1.setActor(stack);
        table.add(container1).padLeft(29.0f).padTop(128.0f).expandX().align(Align.topLeft).colspan(2);

        table.row();
        container1 = new Container();
        container1.fill(true);
        container1.minWidth(305.0f);
        container1.minHeight(45.0f);
        container1.maxWidth(305.0f);
        container1.maxHeight(45.0f);

        stack = new Stack();

        progressBar2 = new ProgressBar(-2.0f, 20.0f, 1.0f, false, skin);
        progressBar2.setValue(0);
        stack.addActor(progressBar2);

        table2 = new Table();

        labelProgbar2num = new Label("25/20", skin, "label24");
        labelProgbar2num.setAlignment(Align.center);
        table2.add(labelProgbar2num).padLeft(18.0f).padBottom(6.0f).expand().align(Align.bottomLeft);

        labelProgbar2text = new Label("GET 20 SCORE", skin, "label16");
        labelProgbar2text.setAlignment(Align.right);
        table2.add(labelProgbar2text).padRight(30.0f).padBottom(11.0f).expand().align(Align.bottomRight);
        stack.addActor(table2);
        container1.setActor(stack);
        table.add(container1).padLeft(29.0f).padTop(59.0f).expandX().align(Align.topLeft).colspan(2);

        table.row();
        container1 = new Container();
        container1.fill(true);
        container1.minWidth(305.0f);
        container1.minHeight(45.0f);
        container1.maxWidth(305.0f);
        container1.maxHeight(45.0f);

        stack = new Stack();

        progressBar3 = new ProgressBar(-8.0f, 60.0f, 1.0f, false, skin);
        progressBar3.setValue(0);
        stack.addActor(progressBar3);

        table2 = new Table();

        labelProgbar3num = new Label("25/60", skin, "label24");
        labelProgbar3num.setAlignment(Align.center);
        table2.add(labelProgbar3num).padLeft(18.0f).padBottom(6.0f).expand().align(Align.bottomLeft);

        labelProgbar3text = new Label("GET 60 SCORE", skin, "label16");
        labelProgbar3text.setAlignment(Align.right);
        table2.add(labelProgbar3text).padRight(30.0f).padBottom(11.0f).expand().align(Align.bottomRight);
        stack.addActor(table2);
        container1.setActor(stack);
        table.add(container1).padLeft(29.0f).padTop(59.0f).expandX().align(Align.topLeft).colspan(2);

        table.row();
        settingButton = new Image(skin, "setting");
        settingButton.setScaling(Scaling.fit);
        table.add(settingButton).padLeft(24.0f).padBottom(34.0f).expand().align(Align.bottomLeft);

        achievementsButton = new Image(skin, "achievements");
        achievementsButton.setScaling(Scaling.fit);
        table.add(achievementsButton).padRight(28.0f).padBottom(28.0f).expand().align(Align.bottomRight);

        setProgressBarData();

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
        labelAchiev.setText(Loc.getLoc(Loc.ACHIEVEMENTS));
        labelProgbar1text.setText(Loc.getLoc(Loc.PLAY_10_TIMES));
        labelProgbar2text.setText(Loc.getLoc(Loc.GET_20_SCORE));
        labelProgbar3text.setText(Loc.getLoc(Loc.GET_60_SCORE));
    }

    private void setProgressBarData() {
        progressBar1.setValue(GameSettings.getPlayGameTimes());
        labelProgbar1num.setText(GameSettings.getPlayGameTimes() + "/10");

        progressBar2.setValue(GameSettings.getScore());
        labelProgbar2num.setText(GameSettings.getScore() + "/20");

        progressBar3.setValue(GameSettings.getScore());
        labelProgbar3num.setText(GameSettings.getScore() + "/60");
    }
}
