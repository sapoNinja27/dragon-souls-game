package Entidades.Cenario;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entidades.Entity;
import Main.Game;
import World.Camera;
import World.Tile;

public class Porta extends Entity{
	public boolean emFrente;
	private int frames=0;
	private float op=0.f;
	private BufferedImage[] porta1;
	private Color cor;
	private int tipo;
	private BufferedImage fund[]=new BufferedImage[2];
	public Porta(int x, int y, Color cor, int tipo) {
		super(x, y, 0,0);
		porta1=new BufferedImage[2];
		setCor(cor);
		this.tipo=tipo;
	}
	private void setCor(Color cor) {
		for(int i=0;i <2 ; i++) {
			porta1[i]=Tile.colorir(Game.cenario.getSprite((i+2)*Game.TILE_SIZE,(2)*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),cor);
		}
		for(int i=0;i <2 ; i++) {
			fund[i]=Tile.colorir(Game.cenario.getSprite((i+4)*Game.TILE_SIZE,(0)*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),cor);
		}
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
		if(tipo==0) {
			if(Game.Ambiente=="Cidade") {
				checkCollisionPorta();
				if(!emFrente) {
					Game.player.clicouPortas=false;
				}
			}
		}else {
			if(Game.Ambiente=="Terraço") {
				checkCollisionPortaTerraco();
				if(!emFrente) {
					Game.player.clicouPortas=false;
				}
			}
		}
		
		

	}
	public void checkCollisionPorta(){
		if(Entity.isColidding(Game.player, this,0,0)) {
			emFrente=true;
			if(Game.player.clicouPortas) {
				Game.player.clicouPortas=false;
//				teleportar(Game.portaTerraco.get(i).getX()-40,
//						Game.portaTerraco.get(i).getY(),
//						Game.portaTerraco.get(i).getX()-40,
//						Game.portaTerraco.get(i).getY(),
//						Game.player.dir);
//				Game.player.lastPorta=i;
				Game.Ambiente="Terraço";
			}
		}else {
			emFrente=false;
		}
	}
	public void checkCollisionPortaTerraco(){
		for(int i = 0; i < Game.portaTerraco.size(); i++){
			Porta atual = Game.portaTerraco.get(i);
			if(atual instanceof Porta) {
				if(Entity.isColidding(Game.player, atual,0,0)) {
					atual.emFrente=true;
					if(Game.player.clicouPortas) {
						Game.player.clicouPortas=false;
						teleportar(Game.portas.get(i).getX()-40,
								Game.portas.get(i).getY(),
								Game.portas.get(i).getX()-40,
								Game.portas.get(i).getY(),
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
		if(tipo==0) {
			if(!emFrente) {
				g.drawImage(porta1[0],this.getX()-Camera.x-Game.TILE_SIZE,this.getY()-Camera.y-Game.TILE_SIZE,Game.TILE_SIZE*2,Game.TILE_SIZE*2,null);
			}else {
				g.drawImage(porta1[1],this.getX()-Camera.x-Game.TILE_SIZE,this.getY()-Camera.y-Game.TILE_SIZE,Game.TILE_SIZE*2,Game.TILE_SIZE*2,null);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
				g.setFont(new Font("Cambria Math",Font.ROMAN_BASELINE,20));
				if(cor==Color.white) {
					g.setColor(Color.blue);
				}else {
					g.setColor(Color.white);
				}
				g.drawString("Terraço", this.getX()-Camera.x-35, this.getY()-Camera.y-30);
//				g.drawLine(100+190, 150, 100+350, 150);
//				g.drawLine(100+160, 125, 100+310, 125);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			}
		}else {
//			g.drawImage(fund[0],this.getX()-Camera.x-Game.TILE_SIZE,this.getY()-Camera.y-Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
//			g.drawImage(fund[1],this.getX()-Camera.x-Game.TILE_SIZE,this.getY()-Camera.y-Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
			if(!emFrente) {
				g.drawImage(porta1[0],this.getX()-Camera.x-Game.TILE_SIZE,this.getY()-Camera.y-Game.TILE_SIZE,Game.TILE_SIZE*2,Game.TILE_SIZE*2,null);
			}else {
				g.drawImage(porta1[1],this.getX()-Camera.x-Game.TILE_SIZE,this.getY()-Camera.y-Game.TILE_SIZE,Game.TILE_SIZE*2,Game.TILE_SIZE*2,null);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
				g.setFont(new Font("Cambria Math",Font.ROMAN_BASELINE,20));
				if(cor==Color.white) {
					g.setColor(Color.blue);
				}else {
					g.setColor(Color.white);
				}
				g.drawString("Cidade", this.getX()-Camera.x-35, this.getY()-Camera.y-30);
//				g.drawLine(100+190, 150, 100+350, 150);
//				g.drawLine(100+160, 125, 100+310, 125);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			}
		}
		
			
			
			
			
			
			

			
			
			
			
			
		}
						
	
}