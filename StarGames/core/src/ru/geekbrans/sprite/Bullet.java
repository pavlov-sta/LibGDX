package ru.geekbrans.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrans.base.Sprite;
import ru.geekbrans.math.Rect;

public class Bullet extends Sprite {

    private Rect worldBounds; // граница мира
    private Vector2 v = new Vector2(); // скорость пули
    private int damage; // дамаг пули
    private Object owner; // владелец пули

    public Bullet() {
        regions = new TextureRegion[1];
    }

    public void set(
            Object owner,
            TextureRegion region,
            Vector2 pos0, // начальная позиция пули
            Vector2 v0, // скорость пуль
            float height, // размер пули
            Rect worldBounds,
            int damage
    ) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos0);
        this.v.set(v0);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) { // проверка пули на экрони/нет
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public Object getOwner() {
        return owner;
    }
}
