package ru.geekbrans.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrans.base.ScaledTouchUpButton;
import ru.geekbrans.math.Rect;

public class MessageGameOver extends ScaledTouchUpButton {

    public MessageGameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));

    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.09f);
    }

    @Override
    public void action() {

    }
}