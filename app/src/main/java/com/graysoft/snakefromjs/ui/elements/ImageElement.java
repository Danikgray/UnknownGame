package com.graysoft.snakefromjs.ui.elements;

import com.badlogic.gdx.graphics.Texture;

public class ImageElement extends BaseElement{
    
    private Texture baseImage;
    
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
  
}
