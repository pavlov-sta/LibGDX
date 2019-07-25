package ru.geekbrans;


import com.badlogic.gdx.Game;

import ru.geekbrans.screen.MenuScreen;


public class StarGames extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}
}
