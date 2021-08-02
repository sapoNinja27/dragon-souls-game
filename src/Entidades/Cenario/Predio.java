package Entidades.Cenario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.lang.module.Configuration;

import Configuration.Configuracoes;
import Entidades.Entity;
import Main.Game;
import World.Camera;
import enums.Texturas;

public class Predio extends Entity{
	Porta porta;
	private int distanciaX,distanciaY;
	BufferedImage paredes[]= new BufferedImage[4];
	public Predio(int x, int y, int width, int height,  Texturas estilo) {
		super(x, y,width,height);
		this.width=width;
		this.distanciaX= this.getWidth()*Configuracoes.TILE_SIZE+Configuracoes.TILE_SIZE*2+27;
		this.distanciaY= this.getHeight()*Configuracoes.TILE_SIZE+Configuracoes.TILE_SIZE;
		for(int i = 0;i<paredes.length;i++) {
			paredes[i]=Game.cenario.getSprite(2*Configuracoes.TILE_SIZE,(5+i)*Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE);
		}
	}
	public void generateEstruturas() {
		porta=new Porta(getX()+100, getY(), Color.white, 0);
	}
	public void tick() {
		porta.tick();
	}
	public void render(Graphics g) {
		if (this.distanciaX((int) x, Game.player.getX()) < distanciaX
				&& this.distanciaX((int) y, Game.player.getY()) < distanciaY) {
			g.setColor(Color.darkGray);
			for (int i=0;i<height;i++) {
				g.drawImage(paredes[3],this.getX()-Camera.x+(Configuracoes.TILE_SIZE*(width-1)),this.getY()-Camera.y-(Configuracoes.TILE_SIZE*(i*2)),Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,null);
				g.drawImage(paredes[2],this.getX()-Camera.x+(Configuracoes.TILE_SIZE*(width-1)),this.getY()-Camera.y-(Configuracoes.TILE_SIZE*(i*2+1)),Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,null);
				g.drawImage(Entity.inverter(paredes[3]),this.getX()-Camera.x,this.getY()-Camera.y-(Configuracoes.TILE_SIZE*(i*2)),Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,null);
				g.drawImage(Entity.inverter(paredes[2]),this.getX()-Camera.x,this.getY()-Camera.y-(Configuracoes.TILE_SIZE*(i*2+1)),Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,null);
				for (int j=0;j<width-2;j++) {
					g.drawImage(paredes[1],this.getX()-Camera.x+(Configuracoes.TILE_SIZE*j)+Configuracoes.TILE_SIZE,this.getY()-Camera.y-(Configuracoes.TILE_SIZE*(i*2)),Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,null);
					g.drawImage(paredes[0],this.getX()-Camera.x+(Configuracoes.TILE_SIZE*j)+Configuracoes.TILE_SIZE,this.getY()-Camera.y-(Configuracoes.TILE_SIZE*(i*2+1)),Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,null);
				}
			}
			porta.render(g);
		}
	}
}
