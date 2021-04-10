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

public class Portao extends Entity{
	public boolean emFrente;
	private float op=0.1f;
	private int frames=0;
	private final int[] index= {Game.rand.nextInt(2),Game.rand.nextInt(2),Game.rand.nextInt(2),Game.rand.nextInt(2),Game.rand.nextInt(2)};
	private BufferedImage[] porta1;
	public Portao(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		porta1=new BufferedImage[2];
	}
	public void tick() {
		depth=4;
		checkCollisionPortao();
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
		setMask(0,0,20,64,40);
		for(int i=0;i <2 ; i++) {
			porta1[i]=Game.cenario.getSprite((4+i)*Game.TILE_SIZE,1*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
		}

	}
	public void checkCollisionPortao(){
		for(int i = 0; i < Game.portoes.size(); i++){
			Portao atual = Game.portoes.get(i);
			if(atual instanceof Portao) {
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
		if(emFrente) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
			g.setFont(new Font("Cambria Math",Font.ROMAN_BASELINE,20));
			g.setColor(Color.white);
			g.drawString("Rua", this.getX()-Camera.x-20, this.getY()-Camera.y-20);
//			g.drawLine(100+190, 150, 100+350, 150);
//			g.drawLine(100+160, 125, 100+310, 125);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		g.drawImage(Game.cenario.getSprite(Game.TILE_SIZE,5*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),
				this.getX()-Camera.x-29,this.getY()-Camera.y+3,Game.TILE_SIZE,Game.TILE_SIZE,null);
		g.drawImage(Game.cenario.getSprite(2*Game.TILE_SIZE,5*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),
				this.getX()-Camera.x-29+64,this.getY()-Camera.y+3,Game.TILE_SIZE,Game.TILE_SIZE,null);
//		g.setColor(Color.red);
//		Rectangle rect= new Rectangle(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
//		Rectangle rect2= new Rectangle(this.getX() - Camera.x+maskx[1],this.getY() - Camera.y+masky[1],maskw[1],maskh[1]);
//		Rectangle rect3= new Rectangle(this.getX() - Camera.x+maskx[2],this.getY() - Camera.y+masky[2],maskw[2],maskh[2]);
//		Rectangle rect4= new Rectangle(this.getX() - Camera.x+maskx[3],this.getY() - Camera.y+masky[3],maskw[3],maskh[3]);
//		Rectangle rect5= new Rectangle(this.getX() - Camera.x+maskx[4],this.getY() - Camera.y+masky[4],maskw[4],maskh[4]);
//		g.drawRect(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
		
		
		
			
			
			
			
			
			

			
			
			
			
			
		}
						
	
}