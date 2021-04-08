package Entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class Cenario extends Entity{
	String tipo;
	public Cenario(int x, int y, int width, int height,String tipo, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		this.tipo=tipo;
	}
	public void render(Graphics g) {


						
	}
}
