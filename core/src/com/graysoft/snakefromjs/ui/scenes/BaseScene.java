package com.graysoft.snakefromjs.ui.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.graysoft.snakefromjs.ui.elements.Button;
import java.util.ArrayList;

public class BaseScene {

    protected ArrayList<Button> Elements;
    //protected static OrthographicCamera camera;//will fix resizing in the future

    //protected static Viewport view;
    protected static SpriteBatch SceneBatch;

    private static BaseScene INSTANCE;
    private Button movingButton;

    protected BaseScene(){
        Elements = new ArrayList<>();
        movingButton = new Button(SceneBatch);
    }

    public static void FirstInit(){
        SceneBatch = new SpriteBatch();
        //camera = new OrthographicCamera();
        //view = new ExtendViewport(1280,720,camera);
        INSTANCE = new MainMenuScene();
    }

    public static BaseScene getScene(){
        if(INSTANCE == null)
            FirstInit();
        return INSTANCE;
    }

    public static void setScene(BaseScene scene) {
        INSTANCE = scene;
    }

    protected void addElement(Button element){
            Elements.add(element);
    }
    
    //hehehe shitcoding on the work(sorry)
    public void render(){
         if(Elements == null || Elements.isEmpty())
             return;
        //camera.update();
        //view.apply();
        //SceneBatch.setProjectionMatrix(//camera.combined);
        //TODO: here test with libgdx coordinate system? because libgdx have inverted y axis so i need
        //TODO: somehow synhronize my ui coordinates with graphical
        SceneBatch.begin();
        movingButton.render();
        for(Button element : Elements){
            element.render();
        }
        SceneBatch.end();
    }
    
    public void resize (int width, int height) {
        //view = new ExtendViewport(width,height,camera);
        //view.update(width,height,true);
       // c//amera.update();
        Gdx.graphics.;
        movingButton.setPos(width,250);
	}
    
    public void dispose () {
        SceneBatch.dispose();
        //TODO: put other variables there
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
        //Vector2 cord = view.unproject(new Vector2(screenX, screenY));
        for(Button element : Elements){
            //element.touchDown(cord.x,cord.y);
        }
        System.out.println("RealX: " + screenX + ", RealY: " + -(screenY - Gdx.graphics.getHeight()));
        //System.out.println(cord);
        //System.out.println(camera.getPickRay(screenX,screenY));
        //System.out.println(view.getPickRay(screenX,screenY));
        return true;
    }

	public boolean touchUp(int x, int y, int pointer) {
        //Looks kinda shit but idkk how to do it better so far
        //Vector2 cord = view.unproject(new Vector2(x, y));
		for(Button element : Elements){
            //element.touchUp(cord.x, cord.y);
        }
		return true;
	}

	
	public boolean touchDragged(int x, int y, int pointers) {
        //Vector2 cord = view.unproject(new Vector2(x, y));
        for(Button element : Elements){
            //element.touchDragged(cord.x, cord.y);
        }
		return true;
	}

    
    public boolean mouseMoved(int screenX, int screenY) {
        //Vector2 cord = view.unproject(new Vector2(screenX, screenY));
        for(Button element : Elements){
           // element.mouseMoved(cord.x, cord.y);
        }
        return true;
    }

    
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
