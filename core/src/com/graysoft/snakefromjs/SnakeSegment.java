package com.graysoft.snakefromjs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SnakeSegment extends Vector2 {

    private boolean IsHead = false;
    private Texture HeadTexture, SegmentTexture;

    public SnakeSegment(float X, float Y){
        super(X,Y);
        HeadTexture = new Texture("testg.png");
        SegmentTexture = new Texture("test.png");
    }

    public void render(SpriteBatch batch,int gridSize){
        batch.draw(IsHead ? HeadTexture : SegmentTexture,
                x * gridSize + 1,
                y * gridSize + 1,
                gridSize - 2,
                gridSize - 2);
    }

    public Texture getHeadTexture(){
        return HeadTexture;
    }

    public Texture getSegmentTexture(){
        return SegmentTexture;
    }

    public void setHead(boolean isHead){
        IsHead = isHead;
    }
}
