package ru.geekbrans.sprite;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrans.base.Sprite;
import ru.geekbrans.math.Rect;

public class Ship extends Sprite {

    private boolean pressedLeft = false;
    private boolean pressedRight = false;
    private boolean pressedUP = false;
    private boolean pressedDOWN = false;

    private Vector2 v = new Vector2();
    private Vector2 v1 = new Vector2(0.5f, 0);
    private Vector2 v2 = new Vector2(0, 0.5f);

    public Ship(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(0.15f);
        setBottom(worldBounds.getBottom() + 0.05f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
    }


    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
            case Input.Keys.A:
                moveLeft();
                pressedLeft = true;
                break;

            case Input.Keys.RIGHT:
            case Input.Keys.D:
                moveRight();
                pressedRight = true;
                break;

            case Input.Keys.UP:
            case Input.Keys.W:
                moveUp();
                pressedRight = true;
                break;

            case Input.Keys.DOWN:
            case Input.Keys.S:
                moveDown();
                pressedRight = true;
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
            case Input.Keys.A:
                if (!pressedRight) {
                    stop();
                }
                pressedLeft = false;
                break;

            case Input.Keys.RIGHT:
            case Input.Keys.D:
                if (!pressedLeft) {
                    stop();
                }
                pressedRight = false;
                break;

            case Input.Keys.UP:
            case Input.Keys.W:
                if (!pressedUP) {
                    stop();
                }
                pressedRight = false;
                break;

            case Input.Keys.DOWN:
            case Input.Keys.S:
                if (!pressedUP) {
                    stop();
                }
                pressedRight = false;
                break;

        }
        return false;
    }

    private void moveRight() {
        v.set(v1);
    }

    private void moveLeft() {
        v.set(v1).rotate(180);
    }
    private void moveUp() {
        v.set(v2);
    }
    private void moveDown() {
        v.set(v2).rotate(180);
    }
    private void stop() {
        v.setZero();
    }
}
