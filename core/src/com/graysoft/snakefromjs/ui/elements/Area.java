package com.graysoft.snakefromjs.ui.elements;

//TO-DO: create initilazier that will sets wigth and height varitables
public class Area {
    
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    
    public Area setRect(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        
        return this;
    }
    
    public Area setPos(float x, float y ) {
		this.x = x;
		this.y = y;
        
        return this;
	}
	
	public Area setSize(float width, float height ) {
		this.width = width;
		this.height = height;
        
        return this;
	}
    
    public Area setX(float val){
        x = val;
        
        return this;
    }
    
    public Area setY(float val){
        y = val;
        
        return this;
    }
    
    public Area setHeight(float val){
        height = val;
        
        return this;
    }
    
    public Area setWidth(float val){
        width = val;
        
        return this;
    }
}
