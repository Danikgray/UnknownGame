package com.graysoft.snakefromjs.ui.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.graysoft.snakefromjs.MainGame;
import com.graysoft.snakefromjs.ui.elements.Button;

public class MainMenuScene extends BaseScene {

    private Button testbtn, secondBtn;

    public MainMenuScene() {
        super();
       // this.view = view;
        testbtn = new Button(SceneBatch)
        {
            @Override
            public void action() {
                MainGame.switchScene(null);
            }
        };
        testbtn.setX(800 / 2);
        testbtn.setY(480 / 2);
        testbtn.setWidth(100);
        testbtn.setHeight(100);
        addElement(testbtn);

        secondBtn = new Button(SceneBatch);
        secondBtn.setX(800 / 4);
        secondBtn.setY(480 / 4);
        secondBtn.setWidth(100);
        secondBtn.setHeight(100);
        addElement(secondBtn);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        testbtn.setX(view.getScreenWidth() / 3);
        testbtn.setY(view.getScreenHeight() / 3);
    }

    @Override
    public boolean touchDown(int x, int y, int points) {
        super.touchDown(x, y, points);
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int points) {
        super.touchUp(x, y, points);
        return true;
    }

    @Override
    public boolean touchDragged(int x, int y, int point) {
        super.touchDragged(x, y, point);
        return true;
    }
}
