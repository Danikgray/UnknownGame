package com.graysoft.snakefromjs.ui.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.graysoft.snakefromjs.ui.elements.Button;

public class MainMenuScene extends BaseScene{
    
    private static Button testbtn, secondBtn;

    public MainMenuScene(SpriteBatch batch){
        super(batch);
      testbtn = new Button(new Texture("test.png"),new Texture("testg.png"),batch);
      testbtn.setX(-800/2);
      testbtn.setY(480/2);
      testbtn.setWidth(100);
      testbtn.setHeight(100);
      secondBtn = new Button(new Texture("test.png"),new Texture("testg.png"),batch);
        secondBtn.setX(800/4);
        secondBtn.setY(480/4);
        secondBtn.setWidth(100);
        secondBtn.setHeight(100);  
    }
    @Override
    public void render(){
        super.render();
        testbtn.render();
        secondBtn.render();
        SceneBatch.end();
    }
    
    public void resize(int width, int height){
        super.resize(width,height);
        testbtn.setX(view.getScreenWidth()/2);
		testbtn.setY(view.getScreenHeight()/2);
    }
    
    public boolean touchDown(int x, int y,int points){
        Vector2 cord = view.unproject(new Vector2(x,y));
		testbtn.touchDown(cord.x, cord.y);
		secondBtn.touchDown(cord.x, cord.y);
		System.out.println(cord);
        return true;
    }
    
    public boolean touchUp(int x, int y,int points){
        testbtn.touchUp();
		secondBtn.touchUp();
        return true;
    }
    
    public boolean touchDragged(int x, int y,int point){
        testbtn.touchDragged(x,y);
		secondBtn.touchDragged(x,y);
        return true;
    }
}
