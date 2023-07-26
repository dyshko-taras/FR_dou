package com.netri.duo.tools;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;

public class RoundCircle {

    private static float red;
    private static float x;
    private static float y;


    public static Circle getCircle(float cX, float cY, float smallCircleR, float greatCircleR, float d, float a) {
        red = MathUtils.degRad * (a + d);
        x = greatCircleR * MathUtils.cos(red) + cX;
        y = greatCircleR * MathUtils.sin(red) + cY;
        return new Circle(x, y, smallCircleR);
    }


}
