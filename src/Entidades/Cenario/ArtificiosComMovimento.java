package Entidades.Cenario;

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

public class ArtificiosComMovimento extends Entity{
	private int speed=10;
	private BufferedImage img;
	public ArtificiosComMovimento(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	public void setTipo(int tipo){
		if(tipo==1) {
			img=Game.cenario.getSprite(Game.TILE_SIZE+(32*Game.rand.nextInt(2)),6*Game.TILE_SIZE+32*Game.rand.nextInt(2),32,32);
		}else if(tipo==0) {
			img=inverter(Game.cenario.getSprite((Game.rand.nextInt(2))*Game.TILE_SIZE,(7+Game.rand.nextInt(2))*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE));
		}
	}
	public void setSpeed(int speed){
		this.speed=speed;
	}
	public void tick() {
		depth=10;
		if(Game.Ambiente=="Cidade") {
			x+=speed+10;
		}else if(Game.Ambiente=="Esgoto") {
			x-=speed+1;
		}
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
		if(Game.Ambiente=="Cidade") {
			if(Game.dia) {
				g.drawImage(img,this.getX()-Camera.x+20+(20),this.getY()-Camera.y-100,Game.TILE_SIZE*3,Game.TILE_SIZE*3,null);
			}else {
				g.drawImage(img,this.getX()-Camera.x+20+(20),this.getY()-Camera.y-100,Game.TILE_SIZE*3,Game.TILE_SIZE*3,null);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
				g.drawImage(Sombra(img),this.getX()-Camera.x+20+(20),this.getY()-Camera.y-100,Game.TILE_SIZE*3,Game.TILE_SIZE*3,null);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
				g.setColor(Color.yellow);
				g.fillPolygon(new int[]{
						this.getX()-Camera.x-Game.TILE_SIZE+3+300,
						this.getX()-Camera.x-Game.TILE_SIZE+5+295,
						this.getX()-Camera.x-Game.TILE_SIZE+8+300,
						
						this.getX()-Camera.x-Game.TILE_SIZE+40+500,
						this.getX()-Camera.x-Game.TILE_SIZE+40+500,
						this.getX()-Camera.x-Game.TILE_SIZE-40+500+70}
						,new int[]{
						this.getY()-Camera.y-Game.TILE_SIZE*3+220,
						this.getY()-Camera.y-Game.TILE_SIZE*3+230,
						this.getY()-Camera.y-Game.TILE_SIZE*3+240,
						
						this.getY()-Camera.y-Game.TILE_SIZE*3+260,
						this.getY()-Camera.y-Game.TILE_SIZE*3+230,
						this.getY()-Camera.y-Game.TILE_SIZE*3+200},
						6);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
				
			}
			
		}else if(Game.Ambiente=="Esgoto") {
			g.drawImage(img,this.getX()-Camera.x+20+(20),this.getY()-Camera.y+10,32,32,null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.7f));
			g.drawImage(Sombra(img),this.getX()-Camera.x+20+(20),this.getY()-Camera.y+10,32,32,null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
	}
						
	
}