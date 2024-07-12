package com.graysoft.snakefromjs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.graysoft.snakefromjs.ui.elements.Button;

public class MainGame extends ApplicationAdapter implements InputProcessor {
    private SpriteBatch batch;
	OrthographicCamera camera;//will fixe the resizing in the future
	Viewport view;
	private Button testbtn;
	private Texture butnImage, unpresssed;
	@Override
	public void create () {
	    batch = new SpriteBatch();
		camera = new OrthographicCamera();
		view = new ExtendViewport(800,480,camera);
		butnImage = new Texture("test.png");
		unpresssed = new Texture("testg.png");
		testbtn = new Button(butnImage,unpresssed,batch);
        testbtn.setX(800/2);
        testbtn.setY(480/2);
        testbtn.setWidth(100);
        testbtn.setHeight(100);
		Gdx.input.setInputProcessor(this);
	}
	
    //Input calls at first and then its call the render method
	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		view.apply();
		batch.setProjectionMatrix(camera.combined);
		//TODO: here test with libgdx coordinate system? because libgdx have inverted y axis so i need
		//TODO: somehow synhronize my ui coordinates with graphical
        batch.begin();
		testbtn.render();
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
	
	@Override public void pause () {}
	@Override public void resume () {}
	@Override public void resize (int width, int height) {
		view.update(width,height,true);
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
		System.out.println(cord);
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		testbtn.touchUp();
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointers) {
        testbtn.touchDragged(x,y);
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
