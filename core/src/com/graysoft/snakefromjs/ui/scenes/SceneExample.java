package com.graysoft.snakefromjs.ui.scenes;

import com.graysoft.snakefromjs.MainGame;
import com.graysoft.snakefromjs.ui.elements.Button;


public class SceneExample extends BaseScene{

private Button ExampleButton, SecondExpl;
    public SceneExample() {
        super();
        ExampleButton = new Button(SceneBatch){
          @Override
            public void action(){
                MainGame.switchScene(new MainMenuScene());
            }
        }       .setPos(100,260)
                .setSize(50,500);
        addElement(ExampleButton);

        SecondExpl = new Button(SceneBatch)
                .setPos(400,50)
                .setSize(400,150);
        addElement(SecondExpl);
    }
}
