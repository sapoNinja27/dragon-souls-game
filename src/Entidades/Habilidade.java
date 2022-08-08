package Entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Configuration.Configuracoes;
import Graficos.Spritesheet;
import Main.Game;
import World.Camera;

public class Habilidade {
	private BufferedImage sprite[];
	private Spritesheet chama;
	public Habilidade() {
		sprite= new BufferedImage[24];
		chama=new Spritesheet("/menus/Menu.png");
		for(int i = 0; i<6; i++){
			sprite[i]=chama.getSprite(i * 128,0,128,128);
		}

	}

	public void tick(){
		
	}

	public void render(Graphics g) {
		g.drawImage(sprite[0], 120, 180 + 40, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, null);
		g.drawImage(sprite[1], 120 + 353, 180 + 40, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, null);
		g.drawImage(sprite[2], 120 + 666 + 20, 180 + 40, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, null);

		g.drawImage(sprite[3], 120, 180 + 200 + 40, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, null);
		g.drawImage(sprite[4], 120 + 250 + 20, 180 + 200 + 40, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, null);
		g.drawImage(sprite[5], 120 + 500 + 40, 180 + 200 + 40, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, null);
		g.drawImage(sprite[5], 120 + 750 + 60, 180 + 200 + 40, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, null);
	}
}
