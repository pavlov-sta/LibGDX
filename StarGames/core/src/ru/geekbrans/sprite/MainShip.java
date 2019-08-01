package ru.geekbrans.sprite;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrans.base.Ship;

import ru.geekbrans.math.Rect;
import ru.geekbrans.pool.BulletPool;
import ru.geekbrans.pool.ExplosionPool;

public class MainShip extends Ship {

    private static final int INVALID_POINTER = -1;

    private boolean pressedLeft = false;
    private boolean pressedRight = false;
    private boolean pressedUP = false;
    private boolean pressedDOWN = false;

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;


    public MainShip(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        bulletRegion = atlas.findRegion("bulletMainShip");
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        reloadInterval = 0.2f;
        v = new Vector2();
        v0 = new Vector2(0.5f, 0);
        v2 = new Vector2(0, 0.5f);
        bulletV = new Vector2(0f, 0.5f);
        bulletHeight = 0.01f;
        damage = 1;
        hp = 10;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        setHeightProportion(0.15f);
        setBottom(worldBounds.getBottom() + 0.05f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
        if (getLeft() > worldBounds.getRight()) {
            setLeft(worldBounds.getLeft());
        }
        if (getRight() < worldBounds.getLeft()) {
            setRight(worldBounds.getRight());
        }
        if (getTop() > worldBounds.getTop()) {
            setBottom(worldBounds.getBottom());
        }
        if (getBottom() < worldBounds.getBottom()) {
            setTop(worldBounds.getTop());
        }
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
                pressedUP = true;
                break;

            case Input.Keys.DOWN:
            case Input.Keys.S:
                moveDown();
                pressedDOWN = true;
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
            case Input.Keys.A:
                if (!pressedRight & !pressedDOWN & !pressedUP) {
                    stop();
                }
                pressedLeft = false;
                break;

            case Input.Keys.RIGHT:
            case Input.Keys.D:
                if (!pressedLeft & !pressedDOWN & !pressedUP)
                    stop();
                pressedRight = false;
                break;

            case Input.Keys.UP:
            case Input.Keys.W:
                if (!pressedLeft & !pressedDOWN & !pressedRight) {
                    stop();
                }
                pressedUP = false;
                break;

            case Input.Keys.DOWN:
            case Input.Keys.S:
                if (!pressedLeft & !pressedUP & !pressedRight)
                    stop();
                pressedDOWN = false;
                break;
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        return false;
    }

    public boolean isBulletCollision(Rect bullet) {
        return !(
                bullet.getRight() < getLeft()
                        || bullet.getLeft() > getRight()
                        || bullet.getBottom() > pos.y
                        || bullet.getTop() < getBottom()
        );
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
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
