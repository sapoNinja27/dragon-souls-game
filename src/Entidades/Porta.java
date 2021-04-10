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
import World.Tile;

public class Porta extends Entity{
	public boolean emFrente;
	private int frames=0;
	private float op=0.f;
	private BufferedImage[] porta1;
	private Color cor;
	public Porta(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		porta1=new BufferedImage[2];
	}
	public void setCor(Color cor) {
		this.cor=cor;
	}
	public void tick() {
		depth=1;
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
		setMask(0,0-25,-20,46,80);
		for(int i=0;i <2 ; i++) {
			porta1[i]=Game.cenario.getSprite((i+2)*Game.TILE_SIZE,(2)*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
		}
		checkCollisionPorta();

	}
	public void checkCollisionPorta(){
		for(int i = 0; i < Game.portas.size(); i++){
			Porta atual = Game.portas.get(i);
			if(atual instanceof Porta) {
				if(Entity.isColidding(Game.player, atual,0,0)) {
					atual.emFrente=true;
//					Game.entities.remove(atual);
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
			g.drawImage(Tile.colorir(porta1[0],cor),this.getX()-Camera.x-Game.TILE_SIZE,this.getY()-Camera.y-Game.TILE_SIZE,Game.TILE_SIZE*2,Game.TILE_SIZE*2,null);
		}else {
			g.drawImage(Tile.colorir(porta1[1],cor),this.getX()-Camera.x-Game.TILE_SIZE,this.getY()-Camera.y-Game.TILE_SIZE,Game.TILE_SIZE*2,Game.TILE_SIZE*2,null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
			g.setFont(new Font("Cambria Math",Font.ROMAN_BASELINE,20));
			if(cor==Color.white) {
				g.setColor(Color.blue);
			}else {
				g.setColor(Color.white);
			}
			g.drawString("Terra�o", this.getX()-Camera.x-35, this.getY()-Camera.y-30);
//			g.drawLine(100+190, 150, 100+350, 150);
//			g.drawLine(100+160, 125, 100+310, 125);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
//		g.drawRect(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
		
			
			
			
			
			
			

			
			
			
			
			
		}
						
	
}