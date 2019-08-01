package ru.geekbrans.sprite;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrans.base.ScaledTouchUpButton;
import ru.geekbrans.math.Rect;
import ru.geekbrans.screen.GameScreen;

public class NewGame extends ScaledTouchUpButton {

    private GameScreen gameScreen;

    public NewGame(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.08f);
        setBottom(worldBounds.getBottom() + 0.3f);
    }

    @Override
    public void action() {
        gameScreen.restart();
    }
}
