package ru.geekbrans.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrans.base.ScaledTouchUpButton;
import ru.geekbrans.math.Rect;

public class ButtonExit extends ScaledTouchUpButton {

    public ButtonExit(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.2f);
        setBottom(worldBounds.getBottom() + 0.04f);
        setRight(worldBounds.getRight() - 0.04f);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
