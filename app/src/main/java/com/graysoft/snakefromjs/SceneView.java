package com.graysoft.snakefromjs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public class SceneView extends ApplicationAdapter{

    static final int INITIAL_TAIL = 400;
    boolean fixedTail = false;

    static int tileCount = 4;
    int gridSize = 400 / tileCount;

    static final Vector2 INITIAL_PLAYER = new Vector2(tileCount / 2, tileCount / 2);

    static Vector2 velocity = new Vector2();
    static Vector2 player = new Vector2();

    static boolean walls = false;

    static Vector2 fruit = new Vector2(1, 1);

    static List<Vector2> trail = new ArrayList<Vector2>();
    static int tail = INITIAL_TAIL;

    static float reward = 0;
    static int points = 0;
    int pointsMax = 0;

    static int fps = 1;
    public boolean runned = false;

    enum ActionEnum {
        none,
        up,
        down,
        right,
        left
    }

    static ActionEnum lastAction = ActionEnum.none;

   /* static Paint localPaint;

    public SceneView(Context context, AttributeSet attr) {
        super(context, attr);
        start(fps);
        reset();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        boolean stopped = velocity.x == 0 && velocity.y == 0;

        canvas.drawColor(Color.argb(120, 40, 40, 40));

        if (walls) {
            localPaint.setColor(Color.LTGRAY);
            canvas.drawRect(0, 0, gridSize - 1, getHeight(), localPaint);
            canvas.drawRect(0, 0, getWidth(), gridSize - 1, localPaint);
            canvas.drawRect(getWidth() - gridSize + 1, 0, gridSize, getHeight(), localPaint);
            canvas.drawRect(0, getHeight() - gridSize + 1, getWidth(), gridSize, localPaint);
        }
        if (!stopped) {

            localPaint.setARGB(51, 200, 200, 200);
            canvas.drawText("(esc) reset", 24, 356, localPaint);
            canvas.drawText("(space) pause", 24, 374, localPaint);
        }
        localPaint.setColor(Color.rgb(0, 255, 111));

        for (int i = 0; i < trail.size() - 1; i++) {
            Rect r = new Rect(0, 0, gridSize - 2, gridSize - 2);
            r.offset(trail.get(i).x * gridSize + 1, trail.get(i).y * gridSize + 1);
            canvas.drawRect(r, localPaint);
            //  ctx.fillRect(trail[i].x * gridSize+1, trail[i].y * gridSize+1, gridSize-2,
            // gridSize-2);

            // console.debug(i + ' => player:' + player.x, player.y + ', trail:' + trail[i].x,
            // trail[i].y);
            localPaint.setColor(Color.GREEN);
            //  ctx.fillStyle = 'lime';
        }

        Rect r = new Rect(0, 0, gridSize - 2, gridSize - 2);
        r.offset(
                trail.get(trail.size() - 1).x * gridSize + 1,
                trail.get(trail.size() - 1).y * gridSize + 1);
        canvas.drawRect(r, localPaint);
        //  ctx.fillRect(trail[trail.length-1].x * gridSize+1, trail[trail.length-1].y * gridSize+1,
        // gridSize-2, gridSize-2);

        localPaint.setColor(Color.RED);
        //   ctx.fillStyle = 'red';
        Rect red = new Rect(0, 0, gridSize - 2, gridSize - 2);
        red.offset(fruit.x * gridSize + 1, fruit.y * gridSize + 1);
        canvas.drawRect(red, localPaint);
        // ctx.fillRect(fruit.x * gridSize+1, fruit.y * gridSize+1, gridSize-2, gridSize-2);

        if (stopped) {
            localPaint.setColor(Color.argb(200, 250, 250, 250));
            //  ctx.fillStyle = 'rgba(250,250,250,0.8)';
            //     ctx.font = "small-caps bold 14px Helvetica";
            canvas.drawText("press ARROW KEYS to START...", 24, 374, localPaint);
            //   ctx.fillText("press ARROW KEYS to START...", 24, 374);
        }
        localPaint.setColor(Color.WHITE);
        canvas.drawText("points: " + points, 248, 40, localPaint);
        canvas.drawText("top: " + pointsMax, 252, 60, localPaint);
        /*
        ctx.fillStyle = 'white';
        ctx.font = "bold small-caps 16px Helvetica";
        ctx.fillText("points: " + points, 288, 40);
        ctx.fillText("top: " + pointsMax, 292, 60);
        *
        //      log(canvas);
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        canvas.drawRect(50, 50, 50, 100, p);
    }

    void start(int fps) {
        this.fps = fps;
        new Thread(this).start();
    }

    void stop() {
        runned = false;
    }

    static void reset() {
        localPaint = new Paint();
        localPaint.setColor(Color.LTGRAY);
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

    void log(Canvas canvas) {
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
    }

    float loop() {

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
            localPaint.setColor(Color.LTGRAY);
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
            if (!fixedTail) tail++;
            points++;
            if (points > pointsMax) pointsMax = points;
            reward = 1;
            RandomFruit();
            // make sure new fruit didn't spawn in snake tail
            while (checkFruitInSnake()) {}
        }
        postInvalidate();
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

    static void pause() {
        velocity.x = 0;
        velocity.y = 0;
    }

    void keyPush(KeyEvent evt) {
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
    }

    @Override
    public void run() {
        runned = true;
        while (runned) {
            loop();
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void ButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.up:
                action(ActionEnum.up);
                break;
            case R.id.down:
                action(ActionEnum.down);
                break;
            case R.id.left:
                action(ActionEnum.left);
                break;
            case R.id.right:
                action(ActionEnum.right);
                break;
            case R.id.spawnFruit:
                RandomFruit();
                break; /*
                       case R.id.reset:
                           reset();
                           break;
                       case R.id.pause:
                          // pause();
                           break;*
            default:
                return;
        }
    }*/
}
