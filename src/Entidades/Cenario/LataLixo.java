package Entidades.Cenario;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Configuration.Configuracoes;
import Entidades.Entity;
import Main.Game;
import World.Camera;

public class LataLixo extends Entity{
	public boolean emFrente;
	private final int[] index= {Game.rand.nextInt(2),Game.rand.nextInt(2),Game.rand.nextInt(2)};
	private final int[] pos= {Game.rand.nextInt(2),Game.rand.nextInt(2),Game.rand.nextInt(2)};
	private BufferedImage[] lata;
	public LataLixo(int x, int y) {
		super(x, y, 0,0);
		lata=new BufferedImage[2];
	}
	public void tick() {
		depth=1;
		setMask(0,0-25,-20,46,80);
		for(int i=0;i <2 ; i++) {
			
			lata[i]=Game.cenario.getSprite((4+i)*Configuracoes.TILE_SIZE,1*Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE);
			
		}

	}
	public void render(Graphics g) {
		for(int i=0;i<3;i++) {
			
			if(pos[i]==1) {
				g.drawImage(lata[index[i]],this.getX()-Camera.x+20+(-i*20),this.getY()-Camera.y+7,Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,null);
			}else{
				g.drawImage(inverter(lata[index[i]]),this.getX()-Camera.x+20+(-i*20),this.getY()-Camera.y+7,Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,null);
			}
			
		}
		if(Configuracoes.dia) {
			for(int i=0;i<3;i++) {
				if(index[i]==0) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillOval(this.getX()-Camera.x+40+Game.rand.nextInt(25)+(-i*20),this.getY()-Camera.y+7+Game.rand.nextInt(20),3,3);
				}
			}
		}
		
			
			
			
			
			
			

			
			
			
			
			
		}
						
	
}