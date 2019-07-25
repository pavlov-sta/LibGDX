package ru.geekbrans.pool;

import ru.geekbrans.base.SpritesPool;
import ru.geekbrans.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
