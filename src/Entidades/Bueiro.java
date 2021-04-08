package Entidades;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class Bueiro extends Entity{
	public boolean emFrente;
	private BufferedImage[] bueiro;
	private float op=0.1f;
	private int frames = 0;
	public Bueiro(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		bueiro=new BufferedImage[2];
	}
	public void tick() {
		if(emFrente) {
			frames++;
			if(frames>=10) {
				if(op<0.9f) {
					op+=0.1f;
				}
			}
			
		}else {
			frames=0;
			op=0.1f;
		}
		depth=1;
		setMask(0,-47,-30,32,40);
		for(int i=0;i <2 ; i++) {
			bueiro[i]=Game.cenario.getSprite((1)*Game.TILE_SIZE,(3+i)*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
		}
		checkCollisionBueiro();
		

	}
	public void checkCollisionBueiro(){
		for(int i = 0; i < Game.bueiros.size(); i++){
			Bueiro atual = Game.bueiros.get(i);
			if(atual instanceof Bueiro) {
				if(Entity.isColidding(Game.player, atual,0,0)) {
					atual.emFrente=true;
				}else {
					atual.emFrente=false;
				}
			}
		}
	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle rect= new Rectangle(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
		Rectangle rect2= new Rectangle(this.getX() - Camera.x+maskx[1],this.getY() - Camera.y+masky[1],maskw[1],maskh[1]);
		Rectangle rect3= new Rectangle(this.getX() - Camera.x+maskx[2],this.getY() - Camera.y+masky[2],maskw[2],maskh[2]);
		Rectangle rect4= new Rectangle(this.getX() - Camera.x+maskx[3],this.getY() - Camera.y+masky[3],maskw[3],maskh[3]);
		Rectangle rect5= new Rectangle(this.getX() - Camera.x+maskx[4],this.getY() - Camera.y+masky[4],maskw[4],maskh[4]);
		
		if(!emFrente) {
			g.drawImage(bueiro[0],this.getX()-Camera.x-Game.TILE_SIZE,this.getY()-Camera.y,Game.TILE_SIZE,Game.TILE_SIZE,null);
		}else {
			g.drawImage(bueiro[1],this.getX()-Camera.x-Game.TILE_SIZE,this.getY()-Camera.y,Game.TILE_SIZE,Game.TILE_SIZE,null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
			g.setFont(new Font("Cambria Math",Font.ROMAN_BASELINE,36));
			g.setColor(Color.black);
			g.drawString("Esgotos", 100+190, 150);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		
		
		
//		g.drawRect(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
		
			
			
			
			
			
			

			
			
			
			
			
		}
						
	
}