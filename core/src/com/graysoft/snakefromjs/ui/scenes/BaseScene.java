package com.graysoft.snakefromjs.ui.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.graysoft.snakefromjs.ui.elements.Button;
import java.util.ArrayList;

public class BaseScene {
    
    protected OrthographicCamera camera;//will fix resizing in the future

    protected Viewport view;
    protected SpriteBatch SceneBatch;
    protected ArrayList<Button> Elements;
    
    public BaseScene(){
        SceneBatch = new SpriteBatch();
        Elements = new ArrayList<>();
        camera = new OrthographicCamera();
        view = new ExtendViewport(800,480,camera);
    }
    
    protected void addElement(Button element){
            Elements.add(element);
    }
    
    //hehehe shitcoding on the work(sorry)
    public void render(){
       // Instance.render();
         if(Elements ==null){
        //  return;
        }else if(Elements.isEmpty()){
         //   return;
        }
        camera.update();
        view.apply();
        SceneBatch.setProjectionMatrix(camera.combined);
        //TODO: here test with libgdx coordinate system? because libgdx have inverted y axis so i need
        //TODO: somehow synhronize my ui coordinates with graphical
        SceneBatch.begin();
        for(Button element : Elements){
            element.render();
        }
        SceneBatch.end();
    }
    
    public void resize (int width, int height) {
      //  Instance.resize(width,height);
		view.update(width,height,true);
	}
    
    public void dispose () {
        //TODO: put other variables there
     //   Instance.dispose();
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
        //I have no idea what I'm doing
   //     Instance.touchDown(screenX, screenY,pointer);
        Vector2 cord = view.unproject(new Vector2(screenX, screenY));
        for(Button element : Elements){
            element.touchDown(cord.x,cord.y);
        }
        System.out.println(cord);
        return true;
    }

	public boolean touchUp(int x, int y, int pointer) {
       // Instance.touchUp(x,y,pointer);
		for(Button element : Elements){
            element.touchUp();
        }
		return true;
	}

	
	public boolean touchDragged(int x, int y, int pointers) {
      //  Instance.touchDragged(x,y,pointers);
        for(Button element : Elements){
            element.touchDragged(x, y);
        }
		return true;
	}

    
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
