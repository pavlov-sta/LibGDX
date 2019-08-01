package ru.geekbrans.sprite;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.geekbrans.base.ScaledTouchUpButton;
import ru.geekbrans.math.Rect;


public class GameOver extends ScaledTouchUpButton {

    public GameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));

    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.09f);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
