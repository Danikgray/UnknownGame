package com.graysoft.snakefromjs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public class Snake extends ApplicationAdapter implements InputProcessor {

    public SpriteBatch localPaint;
    //Textures and font
    private Texture snakeTexture,wallsTexture, appleTexture;
    private BitmapFont font;

    //UI layout
    MainGame main;

    static final int INITIAL_TAIL = 1;
    //boolean fixedTail = false;

    static int tileCount = 6;
    int gridSize = 400 / tileCount;

    static final int[] INITIAL_PLAYER = {tileCount / 2, tileCount / 2};

    static Vector2 velocity = new Vector2();
    static Vector2 player = new Vector2();
    //looped grid or just walls
    static boolean walls = false;

    static Vector2 fruit = new Vector2(1, 1);

    static List<Vector2> trail = new ArrayList<>();
    static int tail = INITIAL_TAIL;

    static int points = 0;
    int pointsMax = 0;

    enum ActionEnum {
        none,
        up,
        down,
        right,
        left
    }

    static ActionEnum lastAction = ActionEnum.none;

    @Override
    public void create() {
        reset();
        main = new MainGame();
        main.create();
        font = new BitmapFont();
        localPaint = new SpriteBatch();
        snakeTexture = new Texture("test.png");
        wallsTexture = new Texture("testg.png");
        appleTexture = new Texture("apple.png");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {

        boolean stopped = velocity.x == 0 && velocity.y == 0;

        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        localPaint.begin();

        if (walls) {
            //walls
            localPaint.draw(wallsTexture,0, 0, gridSize - 1, Gdx.graphics.getHeight());
            localPaint.draw(wallsTexture,0, 0, Gdx.graphics.getWidth(), gridSize - 1);
            localPaint.draw(wallsTexture,Gdx.graphics.getWidth() - gridSize + 1, 0, gridSize, Gdx.graphics.getHeight());
            localPaint.draw(wallsTexture,0, Gdx.graphics.getHeight() - gridSize + 1, Gdx.graphics.getWidth(), gridSize);
        }
        if (!stopped) {
            //some text
            font.setColor(0.2f, 0.8f, 0.8f, 0.8f);
            font.draw(localPaint,"(esc) reset", 24, 356);
            font.draw(localPaint,"(space) pause", 24, 374);
        }
        //snake color
        for (int i = 0; i < trail.size() - 1; i++) {
            localPaint.draw(snakeTexture,trail.get(i).x * gridSize + 1,
                    trail.get(i).y * gridSize + 1,gridSize - 2,gridSize - 2);
        }

        localPaint.draw(snakeTexture,trail.get(trail.size() - 1).x * gridSize + 1,trail.get(trail.size() - 1).y * gridSize + 1,
                gridSize - 2,gridSize - 2);
        localPaint.draw(appleTexture,fruit.x * gridSize + 1,fruit.y * gridSize + 1,
                gridSize - 2,gridSize - 2);

        if (stopped) {
            font.setColor(1, 1, 1, 0.8f);
            font.draw(localPaint,"press ARROW KEYS to START...", 24, 374);
        }
        font.setColor(Color.WHITE);
        font.draw(localPaint,"points: " + points, 248, 40);
        font.draw(localPaint,"top: " + pointsMax, 252, 60);

        update();
        localPaint.end();
        main.render();
    }
    
    @Override
    public void resize(int a, int b){
        main.resize(a,b);
    }

    static void reset() {
        // reset game fields
        tail = INITIAL_TAIL;
        points = 0;
        velocity.x = 0;
        velocity.y = 0;
        player.x = INITIAL_PLAYER[0];
        player.y = INITIAL_PLAYER[1];

        lastAction = ActionEnum.none;

        trail = new ArrayList<>();
        trail.add(new Vector2(player.x, player.y));
    }

    static void action(ActionEnum action) {
        switch (action) {
            case up:
                if (lastAction != ActionEnum.down) {
                    velocity.x = 0;
                    velocity.y = -1;
                }
                break;
            case down:
                if (lastAction != ActionEnum.up) {
                    velocity.x = 0;
                    velocity.y = 1;
                }
                break;
            case left:
                if (lastAction != ActionEnum.right) {
                    velocity.x = -1;
                    velocity.y = 0;
                }
                break;
            case right:
                if (lastAction != ActionEnum.left) {
                    velocity.x = 1;
                    velocity.y = 0;
                }
                break;
            default:
                break;
        }
    }

    static void RandomFruit() {
        if (walls) {
            fruit.x = (int) (1 + Math.floor(Math.random() * (tileCount - 2)));
            fruit.y = (int) (1 + Math.floor(Math.random() * (tileCount - 2)));
        } else {
            fruit.x = (int) Math.floor(Math.random() * tileCount);
            fruit.y = (int) Math.floor(Math.random() * tileCount);
        }
    }

    public void update() {
        if(Gdx.graphics.getFrameId()%30 == 0) {

            boolean stopped = velocity.x == 0 && velocity.y == 0;

            player.x += velocity.x;
            player.y += velocity.y;

            if (velocity.x == 0 && velocity.y == -1) lastAction = ActionEnum.up;
            if (velocity.x == 0 && velocity.y == 1) lastAction = ActionEnum.down;
            if (velocity.x == -1 && velocity.y == 0) lastAction = ActionEnum.left;
            if (velocity.x == 1 && velocity.y == 0) lastAction = ActionEnum.right;

            if (walls) {
                if (player.x < 1) reset();
                if (player.x > tileCount - 2) reset();
                if (player.y < 1) reset();
                if (player.y > tileCount - 2) reset();
            } else {
                if (player.x < 0) player.x = tileCount - 1;
                if (player.x >= tileCount) player.x = 0;
                if (player.y < 0) player.y = tileCount - 1;
                if (player.y >= tileCount) player.y = 0;
            }
            if (!stopped) {
                trail.add(new Vector2(player.x, player.y));
                while (trail.size() > tail) trail.remove(0);
            }
            for (int i = 0; i < trail.size() - 1; i++) {

                if (!stopped && trail.get(i).x == player.x && trail.get(i).y == player.y) {
                    reset();
                }
            }
            if (player.x == fruit.x && player.y == fruit.y) {
                // if (!fixedTail)
                tail++;
                points++;
                if (points > pointsMax) pointsMax = points;
                RandomFruit();
                // make sure new fruit didn't spawn in snake tail
                while (checkFruitInSnake());
            }
        }
    }

    boolean checkFruitInSnake() {
        for (int i = 0; i < trail.size(); i++) {
            if (trail.get(i).x == fruit.x && trail.get(i).y == fruit.y) {
                RandomFruit();
                return true;
            }
        }
        return false;
    }

    @Override
    public void pause() {
        velocity.x = 0;
        velocity.y = 0;
    }

   /* void keyPush(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.KEYCODE_A: // left
                action(ActionEnum.left);
                break;
            case KeyEvent.KEYCODE_W: // up
                action(ActionEnum.up);
                break;

            case KeyEvent.KEYCODE_D: // right
                action(ActionEnum.right);
                break;

            case KeyEvent.KEYCODE_S: // down
                action(ActionEnum.down);
                break;

            case KeyEvent.KEYCODE_SPACE: // space
                pause();
                break;

            case KeyEvent.KEYCODE_ESCAPE: // esc
                reset();
                break;
        }
    }*/

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.DOWN:
                action(ActionEnum.up);
                break;
            case Input.Keys.UP:
                action(ActionEnum.down);
                break;
            case Input.Keys.LEFT:
                action(ActionEnum.left);
                break;
            case Input.Keys.RIGHT:
                action(ActionEnum.right);
                break;
            case Input.Keys.G:
                RandomFruit();
                break;
            case Input.Keys.ESCAPE:
                reset();
                break;
            case Input.Keys.SPACE:
                pause();
                break;
            default:
                return false;
        }
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}