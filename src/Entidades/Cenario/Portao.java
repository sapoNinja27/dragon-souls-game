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
		if(Game.Ambiente=="Cidade") {
			checkCollisionPortao();
		}
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
			porta1[i]=Game.cenario.getSprite((0+i)*Game.TILE_SIZE,1*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
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
		g.drawImage(Game.cenario.getSprite(0,5*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),
				this.getX()-Camera.x-29,this.getY()-Camera.y+3,Game.TILE_SIZE,Game.TILE_SIZE,null);
		g.drawImage(Game.cenario.getSprite(1*Game.TILE_SIZE,5*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),
				this.getX()-Camera.x-29+64,this.getY()-Camera.y+3,Game.TILE_SIZE,Game.TILE_SIZE,null);	
		}
						
	
}