package com.graysoft.snakefromjs.ui.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.graysoft.snakefromjs.ui.elements.Area;
import com.graysoft.snakefromjs.ui.elements.Button;
import com.graysoft.snakefromjs.ui.elements.ImageElement;
import com.graysoft.snakefromjs.ui.elements.TouchableElement;
import java.util.ArrayList;

public class BaseScene {
    
    protected SpriteBatch SceneBatch;
    
    protected ArrayList<TouchableElement> touchElements;
    protected ArrayList<ImageElement> renderElements;
    
    public BaseScene(SpriteBatch batch){
        SceneBatch = batch;
        touchElements = new ArrayList<TouchableElement>();
        renderElements = new ArrayList<ImageElement>();
        
    }
    
    protected void addElement(Area element){
        /*if(element instanceof Button){
            touchElements.add(((Button)element).touchAria);
            renderElements.add((Button)element);
        }*/
    }
    
    //hehehe shitcoding on the work(sorry)
    public void render(){
        if(renderElements==null || touchElements == null){
          return;
        }else if(renderElements.size() <1 || touchElements.size()<1){
            return;
        }
        
        for(ImageElement element : renderElements){
            element.render(SceneBatch);
        }
    }

    public void touchDown(int x, int y, int pointers){
        for(TouchableElement element : touchElements){
            element.touchDown();
        }
    }

    public void touchUp(int x, int y, int pointers){
        for(TouchableElement element : touchElements){
            element.touchUp();
        }
    }
    public void touchDragged(int x, int y, int pointers){

    }
}
