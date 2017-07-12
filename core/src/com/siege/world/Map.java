package com.siege.world;

import java.util.Random;

import com.badlogic.gdx.Gdx;

public class Map {
	//rendering settings for map
	public static int ratio = 2;                   //scale ratio of grid
	public static int tileXSize = 29 * ratio;
	public static int tileYSize = 32 * ratio;
	public static int xStep = tileXSize + ratio;   // hexagonal sprite width size
	public static int yStep = 24 * ratio + ratio;  // distance between 0,0 pixel in sprite and begin corner of hexagonal
	public static int xChange = -tileXSize/2;
	public static int yChange = -tileYSize/2;
	//map settings
	public static int mapSize = 65;
	public static double[][] map = new double[mapSize][mapSize]; // tablica mapy
	private static int fixedMapSize = mapSize - 1;
	private static double rangeMin = -25.0;
	private static double rangeMax = 25.0; // zakres wartoœci mapy <rangeMin, rangeMax>
	private static double heightChange; // jak szybko ma zmieniac sie wartosc
	private static double density = 3; //rozrzut generowanych punktów im wieksza tym wiekszy
	
	private static Random r = new Random();
	
	public static void generator() {
		heightChange = map.length;
	    setSeeds();
		generateMap();
	}
	
	private static void setSeeds() {
		// rogi mapy okreœla funkcja randomseed
		map[0][0] = randomSeed();
		map[0][mapSize - 1] = randomSeed();
		map[mapSize - 1][0] = randomSeed();
		map[mapSize - 1][mapSize - 1] = randomSeed();

	}

	private static double randomSeed() {
		// generuje losowo z przedzia³u rangeMin do 10;
		//return r.nextDouble() * (rangeMax - rangeMin) + rangeMin;
		return -10;
	}
	
	private static void generateMap() {
		for (int i = 1; i <= 7; ++i) {
			calculate(i);
		}
	}
	
	private static void calculate(int iteration) {
		int step = fixedMapSize;
		int x, y;
		for (int i = 1; i < iteration; ++i) {
			step /= 2;
		}
		//System.out.println("Krok: " + fixedMapSize/step);
		//ten for ustala wartosc srodka danego kwadratu (etap DIAMOND)
		for (x = step/2; x <= fixedMapSize; x+=step) {
			for (y = step/2; y <= fixedMapSize; y+=step) {
				diamond(x, y, step/2);
			}
		}
		//ten for ustala wartoœci cornerów kwadratów (etap SQUARE)
		for (x = step/2; x <= fixedMapSize; x+=step) {
			for (y = step/2; y <= fixedMapSize; y+=step) {
				//System.out.println(x+", "+y+ ",L: "+step/2);
				squares(x, y , step/2);
			}
		}
		heightChange /= 2;
		
	}
	
	private static void diamond(int x, int y, int length) {
		double value = 0;
		int minX = x - length; // wartoœci - cornery - aktualnego kwadratu
		int maxX = x + length;
		int minY = y - length;
		int maxY = y + length;
		//zczytywanie wartosci i kumulowanie je w zmiennej value
		value+=map[minX][minY]; //lewy górny
		value+=map[maxX][minY]; // prawy górny 
		value+=map[minX][maxY]; // lewy dolny
		value+=map[maxX][maxY]; // prawy dolny
		value /=4; // dzielenie wartosci przez ilosc punktów
		
		//randomowa zmiana i dodanie jej do value;
		double change = heightChange * (r.nextDouble() * 2 -1) * density;
		value += change;
		savePoint(x, y, value);
		
	}
	
	private static void squares(int x, int y, int length) {
		int minX = x - length; // wartoœci - cornery - aktualnego kwadratu
		int maxX = x + length;
		int minY = y - length;
		int maxY = y + length;
		if (minY == 0) {
			thisSquare(x, minY, length);
		}
		thisSquare(maxX, y, length);
		thisSquare(x, maxY, length);
		if (minX == 0) {
			thisSquare(minX, y, length);
		}
	}

	private static void thisSquare(int x, int y, int length) {
		double value = 0;
		int minX = x - length; // wartoœci - cornery - aktualnego kwadratu
		int maxX = x + length;
		int minY = y - length;
		int maxY = y + length;
		int count = 0;
		if (minY >= 0) {
			++count;
			value+=map[x][minY];
		}
		if (maxY < mapSize) {
			++count;
			value+=map[x][maxY];
		}
		if (minX >= 0) {
			++count;
			value+=map[minX][y];
		}
		if (maxX < mapSize) {
			++count;
			value+=map[maxX][y];
		}
		
		value/=count;
		savePoint(x, y, value);
	}
	private static void savePoint(int x, int y, double value) {
		if (value > rangeMax) {
			value = rangeMax;
		}
		if (value < rangeMin) {
			value = rangeMin;
		}
		
		map[x][y] = value;
	}
	
	public static void setXStep(boolean direction) {
		if (!direction) {
			xChange += -7;
		}
		if (direction) {
			xChange += 7;
		}
	}
	
	public static void setYStep(boolean direction) {
		if (!direction) {
			yChange += -7;
		}
		if (direction) {
			yChange += 7;
		}
	}
}
