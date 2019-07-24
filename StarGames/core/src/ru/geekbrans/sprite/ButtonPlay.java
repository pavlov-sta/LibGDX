package ru.geekbrans.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrans.base.ScaledTouchUpButton;
import ru.geekbrans.math.Rect;
import ru.geekbrans.screen.GameScreen;

public class ButtonPlay extends ScaledTouchUpButton {

    private Game game;

    public ButtonPlay(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay"));
        this.game = game;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.25f);
        setBottom(worldBounds.getBottom() + 0.04f);
        setLeft(worldBounds.getLeft() + 0.04f);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen());
    }
}
