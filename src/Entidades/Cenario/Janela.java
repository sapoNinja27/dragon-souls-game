package Entidades.Cenario;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Configuration.Configuracoes;
import Entidades.Entity;
import Main.Game;
import World.Camera;
import World.Tile;

public class Janela extends Entity{
	private BufferedImage janela;
	public Janela(int x, int y) {
		super(x, y, 0, 0);
	}
	public void setCor(Color cor) {
		janela=Tile.colorir(Game.cenario.getSprite((4)*Configuracoes.TILE_SIZE,(2)*Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE),cor);
		
	}
	public void tick() {
		depth=1;
		setMask(0,0-25,-20,46,80);
		

	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(janela,this.getX()-Camera.x-Configuracoes.TILE_SIZE,this.getY()-Camera.y-Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE*2,Configuracoes.TILE_SIZE*2,null);
	}
						
	
}