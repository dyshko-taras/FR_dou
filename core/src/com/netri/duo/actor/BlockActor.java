package com.netri.duo.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.netri.duo.Main;


public class BlockActor extends Actor {
    private Image image;
    public Rectangle rect;
    public float speed = 100;

    public BlockActor(Image image1) {
        super();
        this.image = image1;
        Image image2 = image1;

        rect = new Rectangle();
        rect.width = image.getImageWidth();
        rect.height = image.getImageHeight();
        rect.x = MathUtils.random(0, Main.SCREEN_WIDTH - rect.width);
        rect.y = Main.SCREEN_HEIGHT;
        setPosition(rect.x, rect.y);
        setSize(rect.width, rect.height);
        System.out.println(image2.getImageWidth()+ " " + rect.height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        image.setX(rect.x);
        image.setY(rect.y);
        image.draw(batch, parentAlpha);
    }
}
