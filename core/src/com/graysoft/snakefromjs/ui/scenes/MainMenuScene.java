package com.graysoft.snakefromjs.ui.scenes;

import com.graysoft.snakefromjs.MainGame;
import com.graysoft.snakefromjs.ui.elements.Button;

public class MainMenuScene extends BaseScene {

    private Button testbtn, secondBtn;

    public MainMenuScene() {
        super();
        testbtn = new Button(SceneBatch)
        {
            @Override
            public void action() {
                setScene(new SceneExample());
            }
        }
                .setPos(800 / 2, 480 / 2)
                .setSize(100, 100);
        addElement(testbtn);

        secondBtn = new Button(SceneBatch)
                .setPos(800 / 4, 480 / 4)
                .setSize(100, 100);
        addElement(secondBtn);
    }
}
