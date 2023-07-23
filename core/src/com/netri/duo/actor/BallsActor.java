package com.netri.duo.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BallsActor extends Actor {
    private Image image;
    private Circle circle;

    private float aX;
    private float aY;


    public BallsActor(Image image, Vector2 containerVector, float x, float y, float radius) {
        super();
        this.image = image;

        aX = containerVector.x;
        aY = containerVector.y;

        circle = new Circle();
        circle.x = aX + x;
        circle.y = aY + y;
        circle.radius = radius;

        setPosition(circle.x, circle.y);
        setSize(circle.radius * 2, circle.radius * 2);
//        setOrigin(getWidth()/2, getWidth()/2);
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

//    @Override
//    public void act(float delta) {
//        super.act(delta);
//        rotateBy( delta * speed);
//        circle.x = getX();
//        circle.y = getY();
//    }
}
