package com.graysoft.snakefromjs;

import com.badlogic.gdx.math.Vector2;

public class SnakeSegment extends Vector2 {
    private boolean IsHead = false;
    public SnakeSegment(float X, float Y){
        super(X,Y);
    }

}
