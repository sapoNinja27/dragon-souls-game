package jObjects.Mouse;


import entidades.mascaras.MascaraUtils;

import java.awt.*;

public class Mouse {
	private static int x, y;
	public static boolean pressed, released, over;

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	public static void setCordinates(int nx, int ny) {
		x = nx + 10;
		y = ny + 5;
	}

	public static boolean isOver(Shape shape) {
		Rectangle mouse = new Rectangle(x, y, 1, 1);
		return  MascaraUtils.isColliding((Polygon) shape, mouse);
	}
}
