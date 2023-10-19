package com.graysoft.snakefromjs.ui.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ImageElement extends BaseElement{
    
    private Texture baseImage;
    
    public ImageElement(){
        baseImage = new Texture(Gdx.files.internal("test.png"));
    }
    
    public ImageElement(Texture image){
        baseImage = image;
    }
    
    public ImageElement(Texture image,float x,float y){
      baseImage = image;
      this.x = x;
      this.y = y;
  }
  
    public ImageElement(Texture image,float x,float y,float height, float width){
      baseImage = image;
      this.x = x;
      this.y = y;
      this.height = height;
      this.width = width;
  }
    
    public void render(SpriteBatch batch){
        batch.draw(baseImage,x,y);
    }
}
