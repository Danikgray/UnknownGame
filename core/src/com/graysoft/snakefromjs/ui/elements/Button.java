package com.graysoft.snakefromjs.ui.elements;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {
    protected SpriteBatch localBatch;
    protected float x = 0;
    protected float y = 0;
    private float height = 100;
    private float width = 100;
    public Texture PressedTexture, IdleTexture, SelectedTexture;

    private boolean pressed = false, selected = false;

    public Button(SpriteBatch batch){
        localBatch = batch;
        PressedTexture = new Texture("UI/PressedButton.png");
        IdleTexture = new Texture("UI/IdleButton.png");
        SelectedTexture = new Texture("UI/SelectedButton.png");
    }


    public Button(Texture pressed,Texture idle,SpriteBatch batch){
        localBatch = batch;
        PressedTexture = pressed;
        IdleTexture = idle;
    }

    public void render(float pointerX, float pointerY){
        if ((pointerX > x && pointerX < (x + width)) &&
                (pointerY > y && pointerY < (y + height)))
            selected = true;
        else
            selected = false;
        if(localBatch.isDrawing())
            localBatch.draw(pressed ? PressedTexture : (selected ? SelectedTexture : IdleTexture), x, y, width, height);
    }

    public Button setPos(float X, float Y){
        x = X;
        y = Y;
        return this;
    }

    public Button setX(float value){
        x = value;
        return this;
    }
    
    public Button setY(float value){
        y = value;
        return this;
    }

    public Button setSize(float Width, float Height){
        width = Width;
        height = Height;
        return this;
    }
    
    public Button setHeight(float value){
        height = value;
        return this;
    }

    public Button setWidth(float value){
        width = value;
        return this;
    }

    public void actionUp(){}
    public void actionDown(){}
    //Huh? why
    public void actionDrag(float pX,float pY){}

    public void touchUp(float pointerX, float pointerY){
        pressed = false;
        if ((pointerX > x && pointerX < (x + width)) &&
                (pointerY > y && pointerY < (y + height)))
        {
            actionUp();
        }
    }

    public void touchDown(float pointerX, float pointerY){
        if ((pointerX > x && pointerX < (x + width)) &&
                (pointerY > y && pointerY < (y + height)))
        {
            pressed = true;
            actionDown();
        }

    }

    public void touchDragged(float pointerX, float pointerY){
        if (pressed) {
            actionDrag(pointerX, pointerY);
        }
    }
}
