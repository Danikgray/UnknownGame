package com.graysoft.snakefromjs.ui.elements;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Button extends ImageElement{
    
    public TouchableElement touchAria;
    public ImageElement pressed;
  
//TO-DO:
    public Button(){
        pressed = new ImageElement();
        touchAria = new TouchableElement();
    }
    public Button(Texture image,float x,float y){
        super(image,x,y);
        
    }
    
    public Button(Texture image,float x,float y,float height,float width){
        super(image,x,y,height,width);
        
    }
    
    public void touchUp(){
        
    }
    
    public void touchDown(){
        
    }
    
    public void touchDragged(){
        
    }
}
