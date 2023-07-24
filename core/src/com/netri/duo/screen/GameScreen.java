package com.netri.duo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.netri.duo.Main;
import com.netri.duo.actor.BallsActor;
import com.netri.duo.actor.BlockActor;

import java.util.Iterator;

public class GameScreen implements Screen {
    public static final float SCREEN_WIDTH = Main.SCREEN_WIDTH;
    public static final float SCREEN_HEIGHT = Main.SCREEN_HEIGHT;

    private final Main main;
    private Viewport viewport;

    public static int difficulty = 1;

    private Skin skin;
    private Stage stage;

    private Table mainTable;
    private Container container;
    private Table table;

    private Vector2 containerVector;
    public float aX = 0;
    private float aY = 0;

    private Image returnButton;
    private Label labelScore;
    private Image ring;
    //    private Image redBall;
//    private Image blueBall;
    private Image settingButton;
    private Image achievementsButton;
    private HorizontalGroup horizontalGroupBalls;

    // My actors
    private boolean isActorAdded = false;
    private Group ballsGroup;
    private BallsActor ballRed;
    private BallsActor ballBlue;
    private final Vector3 touchPos = new Vector3();
    private float speedBalls = 100;

    private Array<BlockActor> blocks = new Array<>();
    private Group blocksGroup;
    private long lastDropTime = 0;
    private float deltaTimeMillis;
    private float speedBlock;


    public int score = 0;

    public GameScreen(Main main, int difficulty) {
        this.main = main;
        GameScreen.difficulty = difficulty;
        setDifficulty();
    }

    @Override
    public void show() {
        setCameraAndStage();
        skin = new Skin(Gdx.files.internal("skin.json"));

        mainTable = new Table();
        mainTable.setFillParent(true);

        Stack stackMain = new Stack();

        Texture texture = new Texture("background.png");
        Image image = new Image(texture);
        image.setScaling(Scaling.fillY);
        stackMain.addActor(image);

        container = new Container();
        container.minWidth(360.0f);
        container.minHeight(800.0f);
        container.maxWidth(360.0f);
        container.maxHeight(800.0f);


        table = new Table();

        returnButton = new Image(skin, "arrow left");
        returnButton.setScaling(Scaling.fit);
        table.add(returnButton).padLeft(24.0f).padTop(32.0f).expandX().align(Align.topLeft).minSize(48.0f).colspan(2);

        table.row();
        labelScore = new Label("0", skin);
        table.add(labelScore).padTop(170.0f).expandX().colspan(2);

        table.row();
        Stack stack = new Stack();

        ring = new Image(skin, "ring");
        ring.setScaling(Scaling.fit);
        stack.addActor(ring);

        horizontalGroupBalls = new HorizontalGroup();
        horizontalGroupBalls.space(184.0f);

//        redBall = new Image(skin, "ball_red");
//        redBall.setScaling(Scaling.fit);
//        horizontalGroupBalls.addActor(redBall);
//
//
//        blueBall = new Image(skin, "ball_green");
//        redBall.setScaling(Scaling.fit);
//        horizontalGroupBalls.addActor(blueBall);

        stack.addActor(horizontalGroupBalls);
        table.add(stack).expand().align(Align.bottom).colspan(2);

        table.row();
        settingButton = new Image(skin, "setting");
        settingButton.setScaling(Scaling.fit);
        table.add(settingButton).padLeft(24.0f).padBottom(34.0f).expandX().align(Align.bottomLeft);

        achievementsButton = new Image(skin, "achievements");
        achievementsButton.setScaling(Scaling.fit);
        table.add(achievementsButton).padRight(28.0f).padBottom(28.0f).expandX().align(Align.bottomRight);


        setClickListeners();

        container.setActor(table);
        stackMain.addActor(container);
        mainTable.add(stackMain);
        stage.addActor(mainTable);

        blocksGroup = new Group();
        stage.addActor(blocksGroup);

        ballsGroup = new Group();
        stage.addActor(ballsGroup);
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
    }


    @Override
    public void render(float delta) {
        updateCamera();


        stage.act();
        stage.draw();

        update();
    }

    private void update() {
        setContainerPosition();
        addMyActors();
        spawnBlocks();
        removeBlocks();
        intersectionBlocksAndBalls();
        setScore();
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

    private void setCameraAndStage() {
        viewport = new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(viewport);
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
    }

    private void setContainerPosition() {
        containerVector = container.localToStageCoordinates(new Vector2(0, 0));

        if (aX != containerVector.x) {
            aX = containerVector.x;
        }

        if (aY != containerVector.y) {
            aY = containerVector.y;
        }
    }

    private void setDifficulty() {
        if (GameScreen.difficulty == 1) {
            speedBlock = 80;
            deltaTimeMillis = 4000;
        } else {
            speedBlock = 120;
            deltaTimeMillis = 3000;
        }
    }

    private void addMyActors() {
        if (!isActorAdded) {
            ballRed = new BallsActor(new Image(skin, "ball_red"), containerVector, 25, 178, 30, 155, 30);
            stage.addActor(ballRed);

            ballBlue = new BallsActor(new Image(skin, "ball_green"), containerVector, 275, 178, 30, -95, 30);
            stage.addActor(ballBlue);

            isActorAdded = true;
            System.out.println("added!!!!");
        }
    }


    private void spawnBlocks() {
        if (TimeUtils.millis() - lastDropTime > deltaTimeMillis) {
            final BlockActor block = new BlockActor(new Image(skin, "rectangle_black"),
                    containerVector,
                    127,
                    47,
                    speedBlock);
            blocks.add(block);
            blocksGroup.addActor(block);
            lastDropTime = TimeUtils.millis();
        }
    }


    private void removeBlocks() {
        for (Iterator<BlockActor> iterator = blocks.iterator(); iterator.hasNext(); ) {
            BlockActor block = iterator.next();
            if (block.getY() + block.height < 0) {
                score++;
                iterator.remove();
                blocksGroup.removeActor(block);
            }
        }
    }

    private void intersectionBlocksAndBalls() {
        for (Iterator<BlockActor> iterator = blocks.iterator(); iterator.hasNext(); ) {
            BlockActor block = iterator.next();


            if (Intersector.overlaps(ballRed.getCircle(), block.getRect()) || Intersector.overlaps(ballBlue.getCircle(), block.getRect())) {
                System.out.println(ballRed.getCircle().y + " " + ballBlue.getCircle().y);
                System.out.println(block.getRect().y);

                main.setScreen(new GameOverScreen(main, score));
            }
        }
    }

    private void setScore() {
        labelScore.setText(score);
    }


}
