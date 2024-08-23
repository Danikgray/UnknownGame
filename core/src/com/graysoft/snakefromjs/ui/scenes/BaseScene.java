package com.graysoft.snakefromjs.ui.scenes;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.graysoft.snakefromjs.ui.elements.Area;
import com.graysoft.snakefromjs.ui.elements.Button;
import com.graysoft.snakefromjs.ui.elements.ImageElement;
import com.graysoft.snakefromjs.ui.elements.TouchableElement;
import java.util.ArrayList;

public class BaseScene {

    protected OrthographicCamera camera;//will fix resizing in the future

    protected Viewport view;
    protected SpriteBatch SceneBatch;
   
    protected ArrayList<TouchableElement> touchElements;
    protected ArrayList<ImageElement> renderElements;
    
    public BaseScene(SpriteBatch batch){
        SceneBatch = batch;
        touchElements = new ArrayList<TouchableElement>();
        renderElements = new ArrayList<ImageElement>();
        camera = new OrthographicCamera();
        view = new ExtendViewport(800,480,camera);
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
        camera.update();
        view.apply();
        SceneBatch.setProjectionMatrix(camera.combined);
        //TODO: here test with libgdx coordinate system? because libgdx have inverted y axis so i need
        //TODO: somehow synhronize my ui coordinates with graphical
        SceneBatch.begin();
        for(ImageElement element : renderElements){
            element.render(SceneBatch);
        }
       // SceneBatch.end();
    }
    
    public void resize (int width, int height) {
		view.update(width,height,true);
	}
    
    public void dispose () {
        //TODO: put other variables there
        SceneBatch.dispose();
    }

    
    public boolean keyDown(int keycode) {
        return false;
    }

    
    public boolean keyUp(int keycode) {
        return false;
    }

    
    public boolean keyTyped(char character) {
        return false;
    }

    
    public boolean touchDown(int screenX, int screenY, int pointer) {
        for(TouchableElement element : touchElements){
            element.touchDown();
        }
        return true;
    }

	public boolean touchUp(int x, int y, int pointer) {
		for(TouchableElement element : touchElements){
            element.touchUp();
        }
		return true;
	}

	
	public boolean touchDragged(int x, int y, int pointers) {
		return true;
	}

    
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
