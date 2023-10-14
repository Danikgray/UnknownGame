package com.graysoft.snakefromjs;

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
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointers) {
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
