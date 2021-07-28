package Entidades.Cenario.ObjetosComMovimento;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Configuration.Configuracoes;
import Main.Game;
import World.Camera;

public class LixoEsgoto extends ObjetosComMovimento{
	private int speed=10;
	private BufferedImage img;
	public LixoEsgoto(int x, int y) {
		super(x, y);
		img=Game.cenario.getSprite(Configuracoes.TILE_SIZE+(32*Game.rand.nextInt(2)),6*Configuracoes.TILE_SIZE+32*Game.rand.nextInt(2),32,32);
	}
	public void setSpeed(int speed){
		this.speed=speed;
	}
	public void tick() {
		x-=speed+1;
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
		g.drawImage(img,this.getX()-Camera.x+20+(20),this.getY()-Camera.y+10,32,32,null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.7f));
		g.drawImage(Sombra(img),this.getX()-Camera.x+20+(20),this.getY()-Camera.y+10,32,32,null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
	}
						
	
}