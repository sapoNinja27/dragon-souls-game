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

public class Plataforma extends Entity{
	private int tipo=0;
	private boolean emFrente;
	private BufferedImage[] img;
	private Color cor=Color.white;
	private int frames=0;
	private float op =0.1f;
	private boolean invisivel=false;
	public Plataforma(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		img= new BufferedImage[14];
	}
	public void setTipo(int tipo) {
		this.tipo=tipo-1;
	}
	public void invisivel() {
		invisivel=true;
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
		for(int i=0; i<(img.length/2);i++) {
			img[i]=Game.cenario.getSprite((1+i)*Game.TILE_SIZE,3*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
		}
		if(!invisivel) {
			setMask(0,0,-3,64,3);
		}else {
			setMask(0,0,-3+64,64,3);
		}
		
		//mascara bueiro
		setMask(1,17,-30,32,40);
		if(Game.Ambiente=="Cidade") {
			if(tipo==0) {
				checkCollisionBueiro();
			}
			if(!emFrente) {
				Game.player.clicouBueiros=false;
			}
			
		}
	}
	public void checkCollisionBueiro(){
		for(int i = 0; i < Game.bueiros.size(); i++){
			Plataforma atual = Game.bueiros.get(i);
			if(atual instanceof Plataforma) {
				if(Entity.isColidding(Game.player, atual,0,1)) {
					atual.emFrente=true;
					if(Game.player.clicouBueiros) {
						Game.player.clicouBueiros=false;
						teleportar(Game.escadasDeEsgoto.get(i).getX(),
								Game.escadasDeEsgoto.get(i).getY(),
								Game.escadasDeEsgoto.get(i).getX(),
								Game.escadasDeEsgoto.get(i).getY(),
								Game.player.dir);
						Game.Ambiente="Esgoto";
					}
				}else {
					atual.emFrente=false;
				}
			}
		}
	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(!invisivel) {
			if(!emFrente) {
				g.drawImage(img[tipo],this.getX() - Camera.x,this.getY() - Camera.y, null);
			}else {
				g.drawImage(img[tipo+1],this.getX() - Camera.x,this.getY() - Camera.y, null);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
				g.setFont(new Font("Cambria Math",Font.ROMAN_BASELINE,20));
				g.setColor(Color.white);
				g.drawString("Esgotos", this.getX()-Camera.x-10, this.getY()-Camera.y+30);
//				g.drawLine(100+190, 150, 100+350, 150);
//				g.drawLine(100+160, 125, 100+310, 125);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			}
			
		}
	}
}
