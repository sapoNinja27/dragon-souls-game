package Entidades.Cenario.ObjetosComMovimento;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Configuration.Configuracoes;
import Main.Game;
import World.Camera;
import enums.TipoAmbiente;

public class Transito extends ObjetosComMovimento{
	private int speed=10;
	private BufferedImage img;
	public Transito(int x, int y) {
		super(x, y);
		img=inverter(Game.cenario.getSprite((Game.rand.nextInt(2))*Configuracoes.TILE_SIZE,(7+Game.rand.nextInt(2))*Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE));
	}
	public void setSpeed(int speed){
		this.speed=speed;
	}
	public void tick() {
		x+=speed+10;
		if(distanciaX(getX(),Game.player.getX())>1200) {
			Game.objetos.remove(this);
		}
		if(distanciaX(getY(),Game.player.getY())>1200) {
			Game.objetos.remove(this);
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
//		g.setColor(Color.red);
//		g.drawRect(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
		if(Configuracoes.local==TipoAmbiente.RUA) {
			if(Configuracoes.dia) {
				g.drawImage(img,this.getX()-Camera.x+20+(20),this.getY()-Camera.y-100,Configuracoes.TILE_SIZE*3,Configuracoes.TILE_SIZE*3,null);
			}else {
				g.drawImage(img,this.getX()-Camera.x+20+(20),this.getY()-Camera.y-100,Configuracoes.TILE_SIZE*3,Configuracoes.TILE_SIZE*3,null);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
				g.drawImage(Sombra(img),this.getX()-Camera.x+20+(20),this.getY()-Camera.y-100,Configuracoes.TILE_SIZE*3,Configuracoes.TILE_SIZE*3,null);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
				g.setColor(Color.yellow);
				g.fillPolygon(new int[]{
						this.getX()-Camera.x-Configuracoes.TILE_SIZE+3+300,
						this.getX()-Camera.x-Configuracoes.TILE_SIZE+5+295,
						this.getX()-Camera.x-Configuracoes.TILE_SIZE+8+300,
						
						this.getX()-Camera.x-Configuracoes.TILE_SIZE+40+500,
						this.getX()-Camera.x-Configuracoes.TILE_SIZE+40+500,
						this.getX()-Camera.x-Configuracoes.TILE_SIZE-40+500+70}
						,new int[]{
						this.getY()-Camera.y-Configuracoes.TILE_SIZE*3+220,
						this.getY()-Camera.y-Configuracoes.TILE_SIZE*3+230,
						this.getY()-Camera.y-Configuracoes.TILE_SIZE*3+240,
						
						this.getY()-Camera.y-Configuracoes.TILE_SIZE*3+260,
						this.getY()-Camera.y-Configuracoes.TILE_SIZE*3+230,
						this.getY()-Camera.y-Configuracoes.TILE_SIZE*3+200},
						6);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			}
		}
	}
}