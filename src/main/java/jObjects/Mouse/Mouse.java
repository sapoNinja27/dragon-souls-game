package jObjects.Mouse;

public class Mouse {
	private static int x, y;
	public static boolean pressed, released, hover;

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

//	public static boolean verifyCollyding(Botao botao){

//		if(isOver(10,10,10,10)){
//			botao.
//		}
//		if (mouseOver) {
//			Mouse.hover = true;
//		} else {
//			if (lastBotao) {
//				Mouse.hover = false;
//				lastBotao = false;
//			}
//		}
//		if (Mouse.pressed) {
//			if (mx > x && mx < x + w && my > y && my < y + h) {
//				overPressed = true;
//				mousePressed = true;
//				lastBotao = true;
//			} else {
//				overPressed = false;
//				mouseOver = false;
//			}
//		} else if (Mouse.released) {
//			mousePressed = false;
//			if (isHover(mx, my)) {
//				if (overPressed) {
//					Mouse.released = false;
//					overPressed = false;
//					Mouse.hover = false;
//					clicked = true;
//				}
//				mouseOver = true;
//			} else {
//				if (overPressed) {
//					Mouse.released = false;
//					overPressed = false;
//				}
//				mouseOver = false;
//			}
//		} else {
//			if (isHover(mx, my)) {
//				mouseOver = true;
//				lastBotao = true;
//			} else {
//				mouseOver = false;
//			}
//		}
//	}
	private static boolean isOver(int x, int y, int w, int h){
		return getX() > x && getX() < x + w && getY() > y && getY() < y + h;
	}
}
