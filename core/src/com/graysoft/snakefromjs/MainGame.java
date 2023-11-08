package com.graysoft.snakefromjs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.graysoft.snakefromjs.ui.elements.ImageElement;
import com.graysoft.snakefromjs.ui.scenes.BaseScene;
import com.graysoft.snakefromjs.ui.scenes.MainMenuScene;

public class MainGame extends ApplicationAdapter implements InputProcessor {
    private SpriteBatch batch;
    private BaseScene ActiveScene;
	private Texture tex;
    
	@Override
	public void create () {
	    batch = new SpriteBatch();
		tex = new Texture(Gdx.files.internal("test.png"));
        ActiveScene = new MainMenuScene(batch);
		Gdx.input.setInputProcessor(this);
	}
	
    //Input calls at first and then its call the render method
	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
		batch.draw(tex,0,0);
        ActiveScene.render();
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
	
	@Override public void pause () {}
	@Override public void resume () {}
	@Override public void resize (int width, int height) {}

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
		ActiveScene.touchDown(x,y,pointer);
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		ActiveScene.touchUp(x,y,pointer);
		return true;
	}

	//wtf, smth new in api?
	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointers) {
		ActiveScene.touchDragged(x,y,pointers);
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
