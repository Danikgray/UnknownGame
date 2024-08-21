package com.graysoft.snakefromjs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.graysoft.snakefromjs.ui.elements.Button;

public class MainGame implements InputProcessor, ApplicationListener {

	public void create () {

		Gdx.input.setInputProcessor(this);
	}
	
    //Input calls at first and then its call the render method
	public void render () {
	//	Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
	//	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	}

	public void dispose () {
		//TODO: put other variables there
	}
	public void pause () {}
	public void resume () {}
	public void resize (int width, int height) {
		view.update(width,height,true);
		testbtn.setX(view.getScreenWidth()/2);
		testbtn.setY(view.getScreenHeight()/2);
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
		Vector2 cord = view.unproject(new Vector2(x,y));
		testbtn.touchDown(cord.x, cord.y);
		secondBtn.touchDown(cord.x, cord.y);
		System.out.println(cord);
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		testbtn.touchUp();
		secondBtn.touchUp();
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointers) {
        testbtn.touchDragged(x,y);
		secondBtn.touchDragged(x,y);
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
