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

    private Texture snakeTexture,wallsTexture, appleTexture;
    private BitmapFont font;


    static final int INITIAL_TAIL = 400;
    //boolean fixedTail = false;

    static int tileCount = 6;
    int gridSize = 400 / tileCount;

    static final Vector2 INITIAL_PLAYER = new Vector2(tileCount / 2, tileCount / 2);

    static Vector2 velocity = new Vector2();
    static Vector2 player = new Vector2();

    static boolean walls = false;

    static Vector2 fruit = new Vector2(1, 1);
    MainGame main;

    static List<Vector2> trail = new ArrayList<Vector2>();
    static int tail = INITIAL_TAIL;

    static float reward = 0;
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

    public SpriteBatch localPaint;

    public Snake() {
        reset();
    }

    @Override
    public void create() {
        main = new MainGame();
        main.create();
        font = new BitmapFont();
        localPaint = new SpriteBatch();
        snakeTexture = new Texture("test.png");
        wallsTexture = new Texture("testg.png");
        appleTexture = new Texture("apple.png");
   //     Gdx.input.setInputProcessor(this);
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
            //  ctx.fillRect(trail[i].x * gridSize+1, trail[i].y * gridSize+1, gridSize-2,
            // gridSize-2);

            // console.debug(i + ' => player:' + player.x, player.y + ', trail:' + trail[i].x,
            // trail[i].y);x
            //  ctx.fillStyle = 'lime';
        }

        localPaint.draw(snakeTexture,trail.get(trail.size() - 1).x * gridSize + 1,trail.get(trail.size() - 1).y * gridSize + 1,gridSize - 2,gridSize - 2);
        //  ctx.fillRect(trail[trail.length-1].x * gridSize+1, trail[trail.length-1].y * gridSize+1,
        // gridSize-2, gridSize-2);

        //   ctx.fillStyle = 'red';
        localPaint.draw(appleTexture,fruit.x * gridSize + 1,fruit.y * gridSize + 1,gridSize - 2,gridSize - 2);
        // ctx.fillRect(fruit.x * gridSize+1, fruit.y * gridSize+1, gridSize-2, gridSize-2);

        if (stopped) {
            font.setColor(1, 1, 1, 0.8f);
            //  ctx.fillStyle = 'rgba(250,250,250,0.8)';
            //     ctx.font = "small-caps bold 14px Helvetica";
            font.draw(localPaint,"press ARROW KEYS to START...", 24, 374);
            //   ctx.fillText("press ARROW KEYS to START...", 24, 374);
        }
        font.setColor(Color.WHITE);
        font.draw(localPaint,"points: " + points, 248, 40);
        font.draw(localPaint,"top: " + pointsMax, 252, 60);

       /* ctx.fillStyle = 'white';
        ctx.font = "bold small-caps 16px Helvetica";
        ctx.fillText("points: " + points, 288, 40);
        ctx.fillText("top: " + pointsMax, 292, 60);*/

        //      log(canvas);
      /*  Paint p = new Paint();
        p.setColor(Color.WHITE);
        canvas.drawRect(50, 50, 50, 100, p)*/;
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
        player.x = INITIAL_PLAYER.x;
        player.y = INITIAL_PLAYER.y;

        reward = -1;

        lastAction = ActionEnum.none;

        trail = new ArrayList<Vector2>();
        Vector2 p = new Vector2(player.x, player.y);
        trail.add(p);
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

   /* void log(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        canvas.drawText("====================", 150, 200, p);
        canvas.drawText("x" + player.x + ", y:" + player.y, 150, 240, p);
        canvas.drawText("tail:" + tail + ", trail.length:" + trail.size(), 150, 280, p);
        for (int i = 0; trail.size() > i; i++)
            canvas.drawText(
                    i + " trails x and y's = " + trail.get(i).x + "  " + trail.get(i).y,
                    150,
                    300 + (20 * i),
                    p);
    }*/

    public float update() {
        if(Gdx.graphics.getFrameId()%60 == 0) {
            reward = -0.1f;

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
            // game.log();
            if (!stopped) {
                Vector2 p = new Vector2(player.x, player.y);
                trail.add(p);
                while (trail.size() > tail) trail.remove(0);
            }
            for (int i = 0; i < trail.size() - 1; i++) {

                // console.debug(i + ' => player:' + player.x, player.y + ', trail:' + trail[i].x,
                // trail[i].y);
                if (!stopped && trail.get(i).x == player.x && trail.get(i).y == player.y) {
                    reset();
                }
            }
            if (player.x == fruit.x && player.y == fruit.y) {
                // if (!fixedTail)
                tail++;
                points++;
                if (points > pointsMax) pointsMax = points;
                reward = 1;
                RandomFruit();
                // make sure new fruit didn't spawn in snake tail
                while (checkFruitInSnake()) ;
            }
        }
        return reward;
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
                // pause();
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