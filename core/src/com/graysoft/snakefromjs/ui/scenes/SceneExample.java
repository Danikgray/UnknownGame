package com.graysoft.snakefromjs.ui.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.graysoft.snakefromjs.MainGame;
import com.graysoft.snakefromjs.ui.elements.Button;


public class SceneExample extends BaseScene{

private Button ExampleButton, SecondExpl;
    public SceneExample(SpriteBatch batch) {
        super();
        SceneBatch = batch;
        ExampleButton = new Button(SceneBatch);
        ExampleButton.setX(100);
        ExampleButton.setY(260);
        ExampleButton.setHeight(500);
        ExampleButton.setWidth(50);
        addElement(ExampleButton);

        SecondExpl = new Button(SceneBatch);
        SecondExpl.setX(400);
        SecondExpl.setY(50);
        SecondExpl.setHeight(150);
        SecondExpl.setWidth(400);
        addElement(SecondExpl);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer) {
        MainGame.switchScene(new MainMenuScene(SceneBatch));
        return super.touchDown(screenX, screenY, pointer);
    }

    @Override
    public boolean touchUp(int x, int y, int pointer) {
        return super.touchUp(x, y, pointer);
    }

    @Override
    public boolean touchDragged(int x, int y, int pointers) {
        return super.touchDragged(x, y, pointers);
    }

}
