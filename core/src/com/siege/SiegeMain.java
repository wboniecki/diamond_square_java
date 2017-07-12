package com.siege;

import com.badlogic.gdx.Game;
import com.siege.helpers.SpriteLoader;
import com.siege.screens.MainScreen;

public class SiegeMain extends Game {

	@Override
	public void create() {
		SpriteLoader.load();
		setScreen(new MainScreen());
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

}
