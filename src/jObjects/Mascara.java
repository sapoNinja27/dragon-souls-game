package jObjects;

import jObjects.Mouse.Mouse;

import java.awt.Color;
import java.awt.Graphics;

public class Mascara {
	private int x, y, w, h;
	private boolean mouseOver, mousePressed, mouseOut, clicked;
	private int mx, my;

	public Mascara(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public void reset() {
		clicked = false;
		mouseOut = true;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public boolean isMouseOut() {
		return mouseOut;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void tick() {
		mx = Mouse.getX();
		my = Mouse.getY();
		if (Mouse.pressed) {
			clicked = false;
			if (mx > x && mx < x + w && my > y && my < y + h) {
				mousePressed = true;
				mouseOut = false;
			} else {
				mouseOver = false;
				mouseOut = true;
			}
		} else if (Mouse.released) {
			mousePressed = false;
			if (mx > x && mx < x + w && my > y && my < y + h) {
				clicked = true;
				mouseOver = true;
				mouseOut = false;
			} else {
				mouseOver = false;
				clicked = false;
				mouseOut = true;
			}
		} else {
			mousePressed = false;
			clicked = false;
			if (mx > x && mx < x + w && my > y && my < y + h) {
				mouseOver = true;
				mouseOut = false;
			} else {
				mouseOver = false;
				mouseOut = true;
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(x, y, w, h);

	}

}
