package com.siege.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.siege.helpers.SpriteLoader;

public class SiegeRenderer {
	private SpriteBatch batch;
	private SiegeWorld siegeWorld;
	private OrthographicCamera cam;
	private TextureRegion tileMap;
	
	private double tileValue;

	public SiegeRenderer(SiegeWorld siegeWorld, OrthographicCamera cam,
			int width, int height) {
		this.siegeWorld = siegeWorld;
		this.cam = cam;
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		initGameObject();
		initSprites();
	}

	private void initSprites() {
		tileMap = SpriteLoader.tileMap;
	}

	private void initGameObject() {

	}

	public void render(float runTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		mapRenderer();
		batch.end();
	}

	private void mapRenderer() {
		for (int i = 0; i < Map.mapSize; i++) { // increase yStep
			for (int j = 0; j < Map.mapSize; j++) { // increase xStep
				setTile(i,j);
				if (i % 2 == 0)
					batch.draw(tileMap, j * Map.xStep + Map.xChange, i
							* Map.yStep+Map.yChange, Map.tileXSize,
							Map.tileYSize);
				else
					batch.draw(tileMap, j * Map.xStep
							+ Map.xStep / 2 + Map.xChange, i * Map.yStep + Map.yChange ,
							Map.tileXSize, Map.tileYSize);
			}
		}
	}
	
	private void setTile(int x, int y) {
		tileValue = Map.map[x][y];
		if (tileValue > -10 && tileValue < 15) {
			tileMap = SpriteLoader.tileMap;
		}
		else if (tileValue <= -10){
			tileMap = SpriteLoader.tileMap2;
		}
		else if (tileValue >=16 && tileValue < 22) {
			tileMap = SpriteLoader.tileMap3;
		} else if (tileValue >= 22) {
			tileMap = SpriteLoader.tileMap4;
		}
	}

}
