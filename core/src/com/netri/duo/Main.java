package com.netri.duo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.netri.duo.screen.MainMenuScreen;
import com.netri.duo.screen.SettingScreen;
import com.netri.duo.tools.GameSettings;
import com.netri.duo.tools.Loc;

public class Main extends Game {

    public static float SCREEN_WIDTH = 360;
    public static float SCREEN_HEIGHT = 800;

    public SpriteBatch batch;
    public BitmapFont bitmapFont;
    public Music music;


    public void create() {
        GameSettings.init();
        initAssets();

        Loc.setLanguage(GameSettings.getLanguage());
        playMusic(GameSettings.getMusicOn());
        GameSettings.setPlayGameTimes(GameSettings.getPlayGameTimes() + 1);
        GameSettings.setScore(0);
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

    public void initAssets() {
        batch = new SpriteBatch();
        bitmapFont = new BitmapFont();
        music = Gdx.audio.newMusic(Gdx.files.internal("sound.mp3"));
    }

    public void playMusic(boolean isMusicOn) {
        if (isMusicOn) {
            music.setLooping(true);
            music.play();
        } else {
            music.stop();
        }
    }
}
