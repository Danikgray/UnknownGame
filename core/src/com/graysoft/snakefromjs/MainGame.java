package com.graysoft.snakefromjs;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.graysoft.snakefromjs.ui.scenes.BaseScene;

public class MainGame implements InputProcessor, ApplicationListener {
    
	public void create () {
		Gdx.input.setInputProcessor(this);
	}
	
    //Tip(?):Input calls at first and then its call the render method
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//TODO: snake render here
        BaseScene.getScene().render();
	}

	public void dispose () {
        BaseScene.getScene().dispose();
		//TODO: put other variables there
	}
	public void pause () {}
	public void resume () {}
	public void resize (int width, int height) {
		BaseScene.getScene().resize(width,height);
	}

	//Input management

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
		BaseScene.getScene().touchDown(x, y, pointer);
        return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		BaseScene.getScene().touchUp(x,y,pointer);
        return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointers) {
		BaseScene.getScene().touchDragged(x,y,pointers);
        return true;
	}

	@Override
	public boolean mouseMoved(int x, int y) {
		BaseScene.getScene().mouseMoved(x, y);
		return true;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

}
