package com.graysoft.snakefromjs.ui.scenes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BaseScene {
    
    protected SpriteBatch SceneBatch;
    private Base controls;
    
    public BaseScene(SpriteBatch batch){
        SceneBatch = batch;
        controls = new ControlsLayoutScene(SceneBatch);
    }
    
    public void render(){
        
    }
}
