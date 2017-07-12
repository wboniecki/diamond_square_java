package com.siege;

import java.util.Random;

public class MapGenPrev {
	static Random r = new Random();
	static int mapSize = 33; // 2^n+1
	static int fixedMapSize = mapSize - 1;
	static double[][] map = new double[mapSize][mapSize]; // tablica mapy
	static double rangeMin = -25.0;
	static double rangeMax = 25.0; // zakres warto�ci mapy <rangeMin, rangeMax>
	static double heightChange = 30; // jak szybko ma zmieniac sie wartosc
	static double density = 0.5; //rozrzut generowanych punkt�w im wieksza tym wiekszy

	static void setSeeds() {
		// rogi mapy okre�la funkcja randomseed
		map[0][0] = randomSeed();
		map[0][mapSize - 1] = randomSeed();
		map[mapSize - 1][0] = randomSeed();
		map[mapSize - 1][mapSize - 1] = randomSeed();

	}

	static double randomSeed() {
		// generuje losowo z przedzia�u rangeMin do 10;
		//return r.nextDouble() * (rangeMax - rangeMin) + rangeMin;
		return 10;
	}
	
	static void generateMap() {
		for (int i = 1; i <= 5; ++i) {
			calculate(i);
		}
	}
	
	static void calculate(int iteration) {
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
		//ten for ustala warto�ci corner�w kwadrat�w (etap SQUARE)
		for (x = step/2; x <= fixedMapSize; x+=step) {
			for (y = step/2; y <= fixedMapSize; y+=step) {
				//System.out.println(x+", "+y+ ",L: "+step/2);
				squares(x, y , step/2);
			}
		}
		
	}
	
	static void diamond(int x, int y, int length) {
		double value = 0;
		int minX = x - length; // warto�ci - cornery - aktualnego kwadratu
		int maxX = x + length;
		int minY = y - length;
		int maxY = y + length;
		//zczytywanie wartosci i kumulowanie je w zmiennej value
		value+=map[minX][minY]; //lewy g�rny
		value+=map[maxX][minY]; // prawy g�rny 
		value+=map[minX][maxY]; // lewy dolny
		value+=map[maxX][maxY]; // prawy dolny
		value /=4; // dzielenie wartosci przez ilosc punkt�w
		
		//randomowa zmiana i dodanie jej do value;
		double change = heightChange * (r.nextDouble() * 2 -1) * density;
		value += change;
		savePoint(x, y, value);
		
	}
	
	static void squares(int x, int y, int length) {
		int minX = x - length; // warto�ci - cornery - aktualnego kwadratu
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

	static void thisSquare(int x, int y, int length) {
		double value = 0;
		int minX = x - length; // warto�ci - cornery - aktualnego kwadratu
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
	static void savePoint(int x, int y, double value) {
		if (value > rangeMax) {
			value = rangeMax;
		}
		if (value < rangeMin) {
			value = rangeMin;
		}
		
		map[x][y] = value;
	}

	public static void main(String[] args) {
	    setSeeds();
		generateMap();
		for (double[] row : map) {
			for (double d : row) {
				if (d > 0 && d < 16) {
					System.out.print("0 ");
				}
				else if (d < 0){
					System.out.print("  ");
				}
				else if (d >=16) {
					System.out.print("X ");
				}
				//System.out.printf("%8.0f ", d);
			}
			System.out.println();
		}
		// System.out.println(50*(r.nextDouble()*2-1)* 0.2);
	}

}
