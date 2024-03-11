package com.graysoft.snakefromjs.ui.elements;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {
    private float x = 0, y = 0,
            height = 100, width = 100;
    public TouchableElement touchAria;
    public Texture pressedTexture,idleTexture;

    private boolean pressed = false;

    public Button(){
        pressedTexture = new Texture("test.png");
        idleTexture = new Texture("testg.png");
        touchAria = new TouchableElement();
    }


    public Button(Texture pressed,Texture idle){
        pressedTexture = pressed;
        idleTexture = idle;
        touchAria = new TouchableElement();
    }

    public void render(SpriteBatch batch){
            batch.draw(pressed ? pressedTexture : idleTexture, x, y, width, height);
    }
    public void action(){

    }

    public void touchUp(){
        pressed = false;
    }

    public void touchDown(int pointerX, int pointerY){
        if ( 
        (pointerX > x && pointerX < (x + width)) &&
        (pointerY > y && pointerY < (y + height))
         ) {
            pressed = true;
            action();
        }
    }

    public void touchDragged(){

    }
    //TODO: create getters and setter for every position variable?
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
