package com.siege.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.siege.helpers.InputHandler;
import com.siege.world.Map;
import com.siege.world.SiegeRenderer;
import com.siege.world.SiegeWorld;

public class MainScreen implements Screen {
	private static final int VWIDTH = 3840;
	private static final int VHEIGHT = 2160;
	private OrthographicCamera cam;
	private SiegeWorld siegeWorld;
	private SiegeRenderer siegeRenderer;
	
	private float runTime;

	public MainScreen() {
		siegeWorld = new SiegeWorld(VWIDTH, VHEIGHT);
		cam = new OrthographicCamera();
		cam.setToOrtho(true, VWIDTH, VHEIGHT);
		siegeRenderer = new SiegeRenderer(siegeWorld, cam, VWIDTH, VHEIGHT);
		Gdx.input.setInputProcessor(new InputHandler());
		Map.generator();
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		siegeWorld.update(delta);
		cam.update();
		siegeRenderer.render(runTime);

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
