package com.graysoft.snakefromjs.ui.elements;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {
    protected SpriteBatch localBatch;
    protected float x = 0;
    protected float y = 0;
    private float height = 100;
    private float width = 100;
    public Texture pressedTexture,idleTexture;

    private boolean pressed = false;

    public Button(SpriteBatch batch){
        localBatch = batch;
        pressedTexture = new Texture("test.png");
        idleTexture = new Texture("testg.png");
    }


    public Button(Texture pressed,Texture idle,SpriteBatch batch){
        localBatch = batch;
        pressedTexture = pressed;
        idleTexture = idle;
    }

    public void render(){
        if(localBatch.isDrawing())
            localBatch.draw(pressed ? pressedTexture : idleTexture, x, y, width, height);
    }
    
    public void action(){

    }
    
    public void actionDrag(int pX,int pY){
        
    }

    public void touchUp(){
        pressed = false;
    }

    public void touchDown(int pointerX, int pointerY){
        if ((pointerX > x && pointerX < (x + width)) &&
            (pointerY > y && pointerY < (y + height)))
        {
            pressed = true;
            action();
        }

    }

    public void touchDragged(int pointerX, int pointerY){
        if (pressed) {
            actionDrag(pointerX, pointerY);
        }
    }

    public void setX(float value){
        x = value;
    }
    
    public void setY(float value){
        y = value;
    }
    
    public void setHeight(float value){
        height = value;
    }

    public void setWidth(float value){
        width = value;
    }

}
