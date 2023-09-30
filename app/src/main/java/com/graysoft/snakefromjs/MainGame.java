package com.graysoft.snakefromjs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.graysoft.snakefromjs.entity.BaseObject;

public class MainGame extends ApplicationAdapter {

    private SpriteBatch batch;
    
	@Override
	public void create () {
	    batch = new SpriteBatch();
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
       // batch.draw(tmp,obj.getY(),-(obj.getX())+Gdx.graphics.getHeight());
        batch.end();
	}

	@Override
	public void dispose () {
		
	}
	
	@Override public void pause () {}
	@Override public void resume () {}
	@Override public void resize (int width, int height) {}
}
