package com.graysoft.snakefromjs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.graysoft.snakefromjs.ui.elements.Button;
import java.util.Random;

public class MainGame extends ApplicationAdapter implements InputProcessor {
    private SpriteBatch batch;

	OrthographicCamera camera;
	private Button testbtn;
	private Texture butnImage, unpresssed;
    Random rand;
	@Override
	public void create () {
	    batch = new SpriteBatch();
        rand = new Random();
		camera = new OrthographicCamera();
		butnImage = new Texture("test.png");
		unpresssed = new Texture("testg.png");
		testbtn = new Button(butnImage,unpresssed){
			@Override
			public void action(){
				System.out.println("clicked!");
                this.setX(rand.nextInt(10));
			}
		};
        testbtn.setX(Gdx.graphics.getWidth()/2);
		//testbtn.setY(Gdx.graphics.getHeight()/2);
        testbtn.setY(-((Gdx.graphics.getHeight()/2)-Gdx.graphics.getHeight()));
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
		//batch.setProjectionMatrix(camera.combined);
		//TODO: here test with libgdx coordinate system? because libgdx have inverted y axis so i need
		//TODO: somehow synhronize my ui coordinates with graphical
      //  testbtn.setX(Gdx.input.getX());
	//	testbtn.setY(-(Gdx.input.getY()-Gdx.graphics.getHeight()));

        batch.begin();
		testbtn.render(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
	
	@Override public void pause () {}
	@Override public void resume () {}
	@Override public void resize (int width, int height) {
		//camera.
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
		testbtn.touchDown(x, -(y-Gdx.graphics.getHeight()));
		//testbtn.touchDown(x,y);
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		testbtn.touchUp();
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
