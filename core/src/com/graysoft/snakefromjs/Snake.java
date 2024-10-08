package com.graysoft.snakefromjs;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;

public class Snake extends ApplicationAdapter implements InputProcessor {

    public SpriteBatch localPaint;
    //Textures and font
    private Texture wallsTexture, appleTexture;
    private BitmapFont font;

    //UI layout
    MainGame main;
    private Viewport viewport;
    private OrthographicCamera camera;
    //Directional Wheel for android controls
    private int DirectionWheel = 0;
    private boolean DirWhelBlocker = false;
    //initial snake score/length
    static final int INITIAL_TAIL = 1;

    static int tileCount = 6;
    //Size of the grid at render
    int gridSize = 400 / tileCount;
    static Vector2 velocity = new Vector2();
    static SnakeSegment head;
    //looped grid or just walls
    static boolean walls = false;

    static Vector2 fruit = new Vector2(1, 1);

    static List<SnakeSegment> trail = new ArrayList<>();

    static int points = INITIAL_TAIL-1;
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
        camera = new OrthographicCamera();
        viewport = new FitViewport(1280,720,camera);
        wallsTexture = new Texture("testg.png");
        appleTexture = new Texture("apple.png");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {

        boolean stopped = velocity.x == 0 && velocity.y == 0;

        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        viewport.apply();
        localPaint.setProjectionMatrix(camera.combined);

        localPaint.begin();

        if (walls) {
            //walls
            localPaint.draw(wallsTexture,0, 0, gridSize - 1, Gdx.graphics.getHeight());
            localPaint.draw(wallsTexture,0, 0, Gdx.graphics.getWidth(), gridSize - 1);
            localPaint.draw(wallsTexture,Gdx.graphics.getWidth() - gridSize + 1, 0, gridSize, Gdx.graphics.getHeight());
            localPaint.draw(wallsTexture,0, Gdx.graphics.getHeight() - gridSize + 1, Gdx.graphics.getWidth(), gridSize);
        }
        //Draw snake
        for (int i = 0; i < trail.size() - 1; i++) {
            trail.get(i).render(localPaint, gridSize);
        }
        head.render(localPaint, gridSize);
        localPaint.draw(appleTexture,fruit.x * gridSize + 1,fruit.y * gridSize + 1,
                gridSize - 2,gridSize - 2);
        //some text
        if (stopped) {
            font.setColor(1, 1, 1, 0.8f);
            font.draw(localPaint,"press ARROW KEYS to START...", 24, 374);
            //if (Won) font.draw(localPaint,"YOU WON!!!.", 24, 394);
        } else {
            font.setColor(0.2f, 0.8f, 0.8f, 0.8f);
            font.draw(localPaint,"(esc) reset", 24, 356);
            font.draw(localPaint,"(space) pause", 24, 374);
        }
        font.draw(localPaint,"DirWheel: " + DirectionWheel,24, 400);
        font.draw(localPaint,"DirBlocker: " + DirWhelBlocker,24, 420);

        font.setColor(Color.WHITE);
        font.draw(localPaint,"points: " + (points - 1), 248, 40);
        font.draw(localPaint,"top: " + pointsMax, 252, 60);

        update();
        localPaint.end();
        main.render();
    }
    
    @Override
    public void resize(int a, int b){
        viewport.update(a, b, true);
        main.resize(a,b);
    }

    static void reset() {
        // reset game fields
        points = INITIAL_TAIL;
        velocity.x = 0;
        velocity.y = 0;
        head = new SnakeSegment(tileCount / 2f,tileCount / 2f);
        head.setHead(true);
        lastAction = ActionEnum.none;

        trail = new ArrayList<>();
        trail.add(new SnakeSegment(head.x, head.y));
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
        // make sure new fruit didn't spawn in snake tail
        for (int i = 0; i < trail.size(); i++) {
            if (  trail.get(i).x == fruit.x
               && trail.get(i).y == fruit.y) {
                RandomFruit();
                break;
            }
        }
    }

    public void update() {
        //tep happening only every 30th frame
        if(Gdx.graphics.getFrameId()%30 == 0) {

            boolean stopped = velocity.x == 0 && velocity.y == 0;

            head.x += velocity.x;
            head.y += velocity.y;

            if (velocity.x == 0 && velocity.y == -1) lastAction = ActionEnum.up;
            if (velocity.x == 0 && velocity.y == 1) lastAction = ActionEnum.down;
            if (velocity.x == -1 && velocity.y == 0) lastAction = ActionEnum.left;
            if (velocity.x == 1 && velocity.y == 0) lastAction = ActionEnum.right;

            if (walls) {
                if (head.x < 1) reset();
                if (head.x > tileCount - 2) reset();
                if (head.y < 1) reset();
                if (head.y > tileCount - 2) reset();
            } else {
                if (head.x < 0) head.x = tileCount - 1;
                if (head.x >= tileCount) head.x = 0;
                if (head.y < 0) head.y = tileCount - 1;
                if (head.y >= tileCount) head.y = 0;
            }
            if (!stopped) {
                trail.add(new SnakeSegment(head.x, head.y));
                while (trail.size() > points) trail.remove(0);
            }

            for (int i = 0; i < trail.size() - 1; i++) {

                if (!stopped && trail.get(i).x == head.x && trail.get(i).y == head.y) {
                    reset();
                }
            }
            if (head.x == fruit.x && head.y == fruit.y) {
                points++;
                if (points > pointsMax) pointsMax = points;
                RandomFruit();
            }
            DirWhelBlocker = false;
        }
    }

    @Override
    public void pause() {
        velocity.x = 0;
        velocity.y = 0;
    }

   @Override
   public void dispose () {
       //TODO: put other variables there
       localPaint.dispose();
   }
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
       if(!DirWhelBlocker && Gdx.app.getType() == Application.ApplicationType.Android){

        if(screenX<Gdx.graphics.getWidth()/2) 
        DirectionWheel++;
         else DirectionWheel--;

        switch(DirectionWheel){
            case -1:
              DirectionWheel = 3;
                action(ActionEnum.left);
                break;
            case 4:
              DirectionWheel = 0;
                action(ActionEnum.up);
                break;
            case 0:
              action(ActionEnum.up);
              break;
            case 1:
              action(ActionEnum.right);
              break;
            case 2:
              action(ActionEnum.down);
              break;
            case 3:
              action(ActionEnum.left);
              break;
            default:
        //      DirectionWheel = 0;
           //   action(ActionEnum.up);
              break;
            }
           DirWhelBlocker = true;
        }
        return true;
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