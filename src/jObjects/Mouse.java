package jObjects;

public class Mouse {
	private static int x, y;
	public static boolean pressed, released;

	public static int getX() {
		return x;
	}

	public static void setCordinates(int nx, int ny) {
		x = nx;
		y = ny;
	}

	public static int getY() {
		return y;
	}


}
