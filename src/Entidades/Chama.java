package Entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Configuration.Configuracoes;
import Graficos.Spritesheet;

public class Chama extends Entidade {
	private BufferedImage sprite[];
	private int frames=0,index=0,maxIndex=15;
	private Spritesheet chama;
	public Chama(int x, int y) {
		super(x, y, 0,0);
		sprite= new BufferedImage[16];
		chama=new Spritesheet("/fogo.png");
		for(int i = 0;i<4;i++) {
			sprite[i]=chama.getSprite((i)*Configuracoes.TILE_SIZE,(0)*Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE);
			sprite[i+4]=chama.getSprite((i)*Configuracoes.TILE_SIZE,(1)*Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE);
			sprite[i+8]=chama.getSprite((i)*Configuracoes.TILE_SIZE,(2)*Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE);
			sprite[i+12]=chama.getSprite((i)*Configuracoes.TILE_SIZE,(3)*Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE);
		}
	}
	public void tick(){
		frames++;
		if(frames>=7) {
			frames=0;
			index++;
			if(index==maxIndex) {
				index=0;
			}
		}
	}
	public void render(Graphics g) {
		g.drawImage(sprite[index],this.getX(),this.getY(),Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,null);
	}
}
