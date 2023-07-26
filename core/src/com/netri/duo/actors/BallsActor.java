package com.netri.duo.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.netri.duo.Main;

public class BallsActor extends Actor {
    private float aX;
    private float aY;

    private Image image;
    private Circle circle;
    private float speed = 100;
    public float radius;

    private final Vector3 touchPos = new Vector3();


    public BallsActor(Image image, Vector2 containerVector, float x, float y, float radius, float oX, float oY) {
        super();

        aX = containerVector.x;
        aY = containerVector.y;

        this.image = image;
        circle = new Circle();

        setPosition(aX + x, aY + y);
        setSize(radius * 2, radius * 2);
        setOrigin(oX, oY); // is relative to the position

        this.radius = radius;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        image.setX(getX());
        image.setY(getY());
        image.setOrigin(getOriginX(), getOriginY());
        image.setRotation(getRotation());
        image.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            getStage().getViewport().unproject(touchPos);
            if (touchPos.x > aX + Main.SCREEN_WIDTH / 2) {
                rotateBy(-delta * speed);
            } else {
                rotateBy(delta * speed);
            }
        }
    }

    public Circle getCircle() {
        circle.x = getX() + radius;
        circle.y = getY() + radius;
        circle.radius = radius;
        return circle;
    }

}

