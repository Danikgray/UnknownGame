package com.graysoft.snakefromjs.ui.scenes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BaseScene {
    
    protected SpriteBatch SceneBatch;
    
    public BaseScene(SpriteBatch batch){
        SceneBatch = batch;

    }
    
    public void render(){

    }

    public void touchDown(int x, int y, int pointers){

    }

    public void touchUp(int x, int y, int pointers){

    }
    public void touchDragged(int x, int y, int pointers){

    }
}
