package com.netri.duo.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.netri.duo.Main;


public class BlockActor extends Actor {
    private float aX;
    private float aY;

    private Image image;
    private Rectangle rect;
    public float speed;

    public float width;
    public float height;

    public BlockActor(Image image, Vector2 containerVector, float width, float height, float speed) {
        super();
        aX = containerVector.x;
        aY = containerVector.y;

        this.image = image;
        rect = new Rectangle();
        this.speed = speed;

        setX(aX + MathUtils.random(0, Main.SCREEN_WIDTH - width));
        setY(aY + Main.SCREEN_HEIGHT);
        setSize(width, height);

        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        image.setX(getX());
        image.setY(getY());
        image.setOrigin(getOriginX(), getOriginY());
        image.setRotation(getRotation());
        image.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(0, -speed * delta);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    public Rectangle getRect() {
        rect.x = getX();
        rect.y = getY();
        rect.width = getWidth();
        rect.height = getHeight();
        return rect;
    }
}

