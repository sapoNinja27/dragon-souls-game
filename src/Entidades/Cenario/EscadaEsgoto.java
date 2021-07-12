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

public class EscadaEsgoto extends Entity{
	public boolean emFrente;
	private BufferedImage bueiro[]=new BufferedImage[2];
	private float op=0.1f;
	private boolean eCorpo=true;
	private int frames = 0;
	public EscadaEsgoto(int x, int y) {
		super(x, y, 0, 0);
	}
	public void setSubida() {
		this.eCorpo=false;
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
		depth=0;
		setMask(0,18,0,31,40);
		
		bueiro[0]=Game.cenario.getSprite(0*Game.TILE_SIZE,(6)*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
		bueiro[1]=Game.cenario.getSprite(0*Game.TILE_SIZE,(6)*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE-3);
		
		if(Game.Ambiente=="Esgoto") {
			checkCollision();
			if(!emFrente) {
				Game.player.clicouBueiros=false;
			}
		}
		

	}
	public void checkCollision(){
		for(int i = 0; i < Game.escadasDeEsgoto.size(); i++){
			EscadaEsgoto atual = Game.escadasDeEsgoto.get(i);
			if(atual instanceof EscadaEsgoto) {
				if(Entity.isColidding(Game.player, atual,0,0)) {
					atual.emFrente=true;
					if(Game.player.clicouBueiros) {
						Game.player.clicouBueiros=false;
						teleportar(Game.bueiros.get(i).getX(),
								Game.bueiros.get(i).getY()-Game.TILE_SIZE,
								Game.bueiros.get(i).getX(),
								Game.bueiros.get(i).getY()-Game.TILE_SIZE,
								Game.player.dir);
						Game.Ambiente="Cidade";
					}
				}else {
					atual.emFrente=false;
				}
			}
		}
	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		if(eCorpo) {
			g.drawImage(bueiro[1],this.getX()-Camera.x-27,this.getY()-Camera.y,Game.TILE_SIZE*2,Game.TILE_SIZE,null);
		}else {
			g.drawImage(bueiro[0],this.getX()-Camera.x-27,this.getY()-Camera.y,Game.TILE_SIZE*2,Game.TILE_SIZE,null);
			if(emFrente) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
				g.setFont(new Font("Cambria Math",Font.ROMAN_BASELINE,20));
				g.setColor(Color.white);
				g.drawString("Cidade", this.getX()-Camera.x+5, this.getY()-Camera.y-20);
//				g.drawLine(100+190, 150, 100+350, 150);
//				g.drawLine(100+160, 125, 100+310, 125);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			}
		}
		
		
		
		Rectangle rect= new Rectangle(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
		Rectangle rect2= new Rectangle(this.getX() - Camera.x+maskx[1],this.getY() - Camera.y+masky[1],maskw[1],maskh[1]);
		Rectangle rect3= new Rectangle(this.getX() - Camera.x+maskx[2],this.getY() - Camera.y+masky[2],maskw[2],maskh[2]);
		Rectangle rect4= new Rectangle(this.getX() - Camera.x+maskx[3],this.getY() - Camera.y+masky[3],maskw[3],maskh[3]);
		Rectangle rect5= new Rectangle(this.getX() - Camera.x+maskx[4],this.getY() - Camera.y+masky[4],maskw[4],maskh[4]);
//		g.drawRect(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
		
			
			
			
			
			
			

			
			
			
			
			
		}
						
	
}