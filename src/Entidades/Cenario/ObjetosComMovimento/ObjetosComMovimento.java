package Entidades.Cenario.ObjetosComMovimento;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entidades.Entity;
import Main.Game;
import World.Camera;
import World.Tile;

public class ObjetosComMovimento extends Entity{
	private int speed=10;
	private BufferedImage img;
	public ObjetosComMovimento(int x, int y) {
		super(x, y,0,0);
	}
	public void setSpeed(int speed){
		this.speed=speed;
	}
}