package com.graysoft.snakefromjs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor{

    @Override
    public boolean keyDown(int arg0) {
        return false;
    }
    

    @Override
    public boolean keyUp(int arg0) {
        return false;
    }
    

    @Override
    public boolean keyTyped(char arg0) {
        return false;
    }
    

    @Override
    public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
        System.out.println("Touchdown!!"+ Gdx.graphics.getFrameId());
        return false;
    }
    

    @Override
    public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
        return false;
    }
    

    @Override
    public boolean touchDragged(int arg0, int arg1, int arg2) {
        return false;
    }
    

    @Override
    public boolean mouseMoved(int arg0, int arg1) {
        return false;
    }
    

    @Override
    public boolean scrolled(float arg0, float arg1) {
        return false;
    }
    
}
