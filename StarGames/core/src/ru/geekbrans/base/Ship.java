package ru.geekbrans.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrans.math.Rect;
import ru.geekbrans.pool.BulletPool;
import ru.geekbrans.pool.ExplosionPool;
import ru.geekbrans.sprite.Bullet;
import ru.geekbrans.sprite.Explosion;

public abstract class Ship extends Sprite {

    protected TextureRegion bulletRegion;
    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected ExplosionPool explosionPool;

    protected Vector2 v;
    protected Vector2 v0;
    protected Vector2 v2;
    protected Vector2 bulletV;

    protected float reloadInterval;
    protected float reloadTimer;
    protected float bulletHeight;

    protected Sound shootSound;

    protected int damage;
    protected int hp;

    private final float damageAnimateInterval = 0.1f;
    private float damageAnimateTimer = damageAnimateInterval;

    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void update(float delta) {
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= damageAnimateInterval) {
            frame = 0;
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    @Override
    public void dispose() {
        shootSound.dispose();
    }

    public void damage(int damage) {
        frame = 1;
        damageAnimateTimer = 0f;
        hp -= damage;
        if (hp <= 0) {
            destroy();
        }
    }

    @Override
    public void destroy() {
        hp = 0;
        boom();
        super.destroy();
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, damage);
        shootSound.play();
    }

    private void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(), pos);
    }
}
