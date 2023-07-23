package com.netri.duo.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.netri.duo.Main;

import com.badlogic.gdx.scenes.scene2d.ui.Container;


public class BlockActor extends Actor {
    private Image image;
    public Rectangle rect;
    public float speed;

    private float aX;
    private float aY;


    public BlockActor(Image image, Vector2 containerVector, float width, float height, float speed) {
        super();
        this.image = image;

        aX = containerVector.x;
        aY = containerVector.y;

        rect = new Rectangle();
        rect.x = aX + MathUtils.random(0, Main.SCREEN_WIDTH - width);
        rect.y = aY + Main.SCREEN_HEIGHT;
        rect.width = width;
        rect.height = height;

        this.speed = speed;

        setPosition(rect.x, rect.y);
        setSize(rect.width, rect.height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        image.setX(getX());
        image.setY(getY());
        image.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(0, -speed * delta);
        rect.x = getX();
        rect.y = getY();
    }
}
