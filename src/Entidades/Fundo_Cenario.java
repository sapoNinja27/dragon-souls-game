package Entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class Fundo_Cenario extends Entity{

	public Fundo_Cenario(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	public void tick() {
		
	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.RED);
//		g.setColor(Color.YELLOW);
//		g2.draw(rect2);
//		g.setColor(Color.BLUE);
//		g2.draw(rect3);
//		g.setColor(Color.GREEN);
//		g2.draw(rect4);
	}
}

