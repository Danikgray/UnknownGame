package com.graysoft.snakefromjs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor{

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
    public boolean touchDown(int x, int y, int pointer, int button) {
        System.out.println("TouchDown!!!!" + Gdx.graphics.getFrameId());
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        System.out.println("TocuhUp!!!!" + Gdx.graphics.getFrameId());
        return true;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointers) {
        System.out.println("Dragged!!!!" + Gdx.graphics.getFrameId());
        return true;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
