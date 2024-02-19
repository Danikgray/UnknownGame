package com.graysoft.snakefromjs.ui.elements;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {
    private float x = 0, y = 0,
            height = 100, width = 100;
    public TouchableElement touchAria;
    public Texture pressedTexture,idleTexture;

    private boolean pressed = false;

    public Button(){
        pressedTexture = new Texture("test.png");
        idleTexture = new Texture("   testg.png");
        touchAria = new TouchableElement();
    }

    public Button(Texture pressed,Texture idle){
        pressedTexture = pressed;
        idleTexture = idle;
        touchAria = new TouchableElement();
    }

    public void render(SpriteBatch batch){
        //kinda shit? i want to redo this one later
        if (!pressed)
            batch.draw(idleTexture, x, y, width, height);
        else
            batch.draw(pressedTexture, x, y, width, height);
    }

    //TODO: create same things for other variables
    public void setX(float value){
        x = value;
    }
    public void touchUp(){
        
    }
    
    public void touchDown(){
        
    }
    
    public void touchDragged(){
        
    }
}
