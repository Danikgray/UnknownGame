package com.graysoft.snakefromjs.ui.scenes;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.graysoft.snakefromjs.ui.elements.Area;
import com.graysoft.snakefromjs.ui.elements.Button;
import com.graysoft.snakefromjs.ui.elements.ImageElement;
import com.graysoft.snakefromjs.ui.elements.TouchableElement;
import java.util.ArrayList;

public class BaseScene implements InputProcessor {

    OrthographicCamera camera;//will fix resizing in the future

    private Viewport view;
    protected SpriteBatch SceneBatch;
    private static Button testbtn, secondBtn;

    protected ArrayList<TouchableElement> touchElements;
    protected ArrayList<ImageElement> renderElements;
    
    public BaseScene(SpriteBatch batch){
        SceneBatch = batch;
        touchElements = new ArrayList<TouchableElement>();
        renderElements = new ArrayList<ImageElement>();
        camera = new OrthographicCamera();
        view = new ExtendViewport(800,480,camera);
        testbtn = new Button(new Texture("test.png"),new Texture("testg.png"),batch);
        testbtn.setX(-800/2);
        testbtn.setY(480/2);
        testbtn.setWidth(100);
        testbtn.setHeight(100);
        secondBtn = new Button(new Texture("test.png"),new Texture("testg.png"),batch);
        secondBtn.setX(800/4);
        secondBtn.setY(480/4);
        secondBtn.setWidth(100);
        secondBtn.setHeight(100);
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
        batch.setProjectionMatrix(camera.combined);
        //TODO: here test with libgdx coordinate system? because libgdx have inverted y axis so i need
        //TODO: somehow synhronize my ui coordinates with graphical
        batch.begin();
        testbtn.render();
        secondBtn.render();
        batch.end();
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
    public void dispose () {
        //TODO: put other variables there
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int x, int y, int pointers){
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
