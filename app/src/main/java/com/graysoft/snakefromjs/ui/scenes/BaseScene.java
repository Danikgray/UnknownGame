package com.graysoft.snakefromjs.ui.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.graysoft.snakefromjs.ui.elements.ImageElement;
import com.graysoft.snakefromjs.ui.elements.TouchableElement;
import java.util.ArrayList;

public class BaseScene {
    
    protected SpriteBatch SceneBatch;
    
    protected ArrayList<TouchableElement> touchElements;
    protected ArrayList<ImageElement> renderElements;
    
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
