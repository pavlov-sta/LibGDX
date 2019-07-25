package ru.geekbrans.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


import ru.geekbrans.base.BaseScreen;
import ru.geekbrans.pool.BulletPool;
import ru.geekbrans.sprite.Background;
import ru.geekbrans.sprite.MainShip;

import ru.geekbrans.sprite.Star;
import ru.geekbrans.math.Rect;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;

    private TextureAtlas atlas;
    private Texture bg;
    private Background background;

    private BulletPool bulletPool;

    private Star[] starArray;
    private MainShip ship;


    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        bg = new Texture("textures/bg.png");
        background = new Background(new TextureRegion(bg));
        starArray = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            starArray[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        ship = new MainShip(atlas, bulletPool);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star:starArray) {
            star.resize(worldBounds);
        }
        freeAllDestroyedActiveSprites();
        ship.resize(worldBounds);
    }

    @Override
    public void dispose() {
        atlas.dispose();
        bg.dispose();
        bulletPool.dispose();
        super.dispose();
    }

    private void update(float delta) {
        for (Star star:starArray) {
            star.update(delta);
        }
        bulletPool.updateActiveSprites(delta);
        ship.update(delta);
    }

    private void freeAllDestroyedActiveSprites() {
        bulletPool.freeAllDestroyedActiveSprites();
    }

    private void draw() {
        Gdx.gl.glClearColor(0.26f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star:starArray) {
            star.draw(batch);
        }
        ship.draw(batch);
        bulletPool.drawActiveSprites(batch);
        batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        ship.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        ship.keyUp(keycode);
        return false;
    }
}
