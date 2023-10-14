package com.graysoft.snakefromjs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.graysoft.snakefromjs.ui.scenes.BaseScene;

public class MainGame extends ApplicationAdapter {

    private SpriteBatch batch;

	private InputHandler inputHandler;
    private BaseScene ActiveScene;
    
	@Override
	public void create () {
	    batch = new SpriteBatch();
        ActiveScene = new BaseScene(batch);
		inputHandler = new InputHandler();
		Gdx.input.setInputProcessor(inputHandler);
	}
	
    //Input calls at first and then its call the render method
	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
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
}
