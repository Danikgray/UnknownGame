package com.graysoft.snakefromjs.ui.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
//TO-DO: create initilazier that will sets wigth and height varitables
public class BaseElement{
    
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    
    public BaseElement setRect(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        
        return this;
    }
    
    public BaseElement setPos( float x, float y ) {
		this.x = x;
		this.y = y;
        
        return this;
	}
	
	public BaseElement setSize( float width, float height ) {
		this.width = width;
		this.height = height;
        
        return this;
	}
    
    public BaseElement setX(float val){
        x = val;
        
        return this;
    }
    
    public BaseElement setY(float val){
        y = val;
        
        return this;
    }
    
    public BaseElement setHeight(float val){
        height = val;
        
        return this;
    }
    
    public BaseElement setWidth(float val){
        width = val;
        
        return this;
    }
}
