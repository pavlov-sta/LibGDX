package ru.geekbrans.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrans.base.Sprite;
import ru.geekbrans.math.Rect;

public class Background extends Sprite {

    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }
}
