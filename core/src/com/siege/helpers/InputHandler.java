package com.siege.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.siege.world.Map;

public class InputHandler implements InputProcessor {
	private int xChange;
	private int yChange;

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//Map.generator();
		xChange = screenX;
		yChange = screenY;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		xChange=0;
		yChange=0;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		if (xChange - screenX > 20) {
			Map.setXStep(false);
		}
		if (xChange - screenX < -20) {
			Map.setXStep(true);
		}
		
		if (yChange -screenY > 20) {
			Map.setYStep(false);
		}
		if (yChange - screenY < -20) {
			Map.setYStep(true);
		}
		
		Gdx.app.log("screenx", xChange+"");
		Gdx.app.log("screeny", screenY+"");
		Gdx.app.log("pointer", pointer+"");
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
