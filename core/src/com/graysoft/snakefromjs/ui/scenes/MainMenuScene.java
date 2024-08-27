package com.graysoft.snakefromjs.ui.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.graysoft.snakefromjs.ui.elements.Button;

public class MainMenuScene extends BaseScene {

    private static Button testbtn, secondBtn;

    public MainMenuScene() {
        super();
        testbtn = new Button(new Texture("test.png"), new Texture("testg.png"), SceneBatch);
        testbtn.setX(-800 / 2);
        testbtn.setY(480 / 2);
        testbtn.setWidth(100);
        testbtn.setHeight(100);
        addElement(testbtn);

        secondBtn = new Button(new Texture("test.png"), new Texture("testg.png"), SceneBatch);
        secondBtn.setX(800 / 4);
        secondBtn.setY(480 / 4);
        secondBtn.setWidth(100);
        secondBtn.setHeight(100);
        addElement(secondBtn);
    }

    public void render() {
        super.render();
    }

    public void resize(int width, int height) {
        super.resize(width, height);
        testbtn.setX(view.getScreenWidth() / 2);
        testbtn.setY(view.getScreenHeight() / 2);
    }

    public boolean touchDown(int x, int y, int points) {
        super.touchDown(x, y, points);
        return true;
    }

    public boolean touchUp(int x, int y, int points) {
        super.touchUp(x, y, points);
        return true;
    }

    public boolean touchDragged(int x, int y, int point) {
        super.touchDragged(x, y, point);
        return true;
    }
}
