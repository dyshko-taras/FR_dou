package com.netri.duo;

import static com.badlogic.gdx.scenes.scene2d.Touchable.disabled;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.netri.duo.screen.MainMenuScreen;

public class Main extends Game {

    public static float SCREEN_WIDTH = 360;
    public static float SCREEN_HEIGHT = 800;

    public SpriteBatch batch;
    public BitmapFont bitmapFont;
    public Music music;


    public void create() {
        batch = new SpriteBatch();
        bitmapFont = new BitmapFont();
        music = Gdx.audio.newMusic(Gdx.files.internal("sound.mp3"));
//        playMusic();
        this.setScreen(new MainMenuScreen(this));
    }


    public void render() {
        super.render();
    }

    public void resize(int width, int height) {
        super.resize(width, height);
    }

    public void dispose() {
        batch.dispose();
        bitmapFont.dispose();
    }

    private void playMusic() {
        music.setLooping(true);
        music.play();
    }
}
