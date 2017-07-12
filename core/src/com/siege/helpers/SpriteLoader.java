package com.siege.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteLoader {
	public static Texture tileMapTexture;
	public static TextureRegion tileMap;
	public static Texture tileMapTexture2;
	public static TextureRegion tileMap2;
	public static Texture tileMapTexture3;
	public static TextureRegion tileMap3;
	public static Texture tileMapTexture4;
	public static TextureRegion tileMap4;

	public static void load() {
		tileMapTexture = new Texture(Gdx.files.internal("sprites/tilemaprev.png"));
		tileMap = new TextureRegion(tileMapTexture);
		tileMap.flip(false, true);
		
		tileMapTexture2 = new Texture(Gdx.files.internal("sprites/tilemaprev2.png"));
		tileMap2 = new TextureRegion(tileMapTexture2);
		tileMap2.flip(false, true);
		
		tileMapTexture3 = new Texture(Gdx.files.internal("sprites/tilemaprev3.png"));
		tileMap3 = new TextureRegion(tileMapTexture3);
		tileMap3.flip(false, true);
		
		tileMapTexture4 = new Texture(Gdx.files.internal("sprites/tilemaprev4.png"));
		tileMap4 = new TextureRegion(tileMapTexture4);
		tileMap4.flip(false, true);
	}

}
