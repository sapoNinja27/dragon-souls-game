package Entidades.Cenario;

import java.awt.Color;
import java.awt.Graphics;

import Configuration.Configuracoes;
import Entidades.Entity;
import Main.Game;
import World.Camera;

public class ParedeInvisivel extends Entity{
	private int dist;
	public ParedeInvisivel(int x, int y,int dist) {
		super(x, y, 0,0);
		this.dist=dist;
	}
	public void tick() {
		
		setMask(0,0,0,1,Configuracoes.HEIGHT*11+8);
		setMask(1,dist*Configuracoes.TILE_SIZE-2,0,1,Configuracoes.HEIGHT*11+8);
		
		checkCollision();
	}
	public void checkCollision(){
		for(int i = 0; i < Game.entities.size(); i++){
			Entity atual = Game.entities.get(i);
			if(atual instanceof ParedeInvisivel) {
				if(Entity.isColidding(Game.player, atual,0,0)) {
						Game.player.setX(atual.getX()-15);
						Game.player.parado=true;
				}
				if(Entity.isColidding(Game.player, atual,0,1)) {
					Game.player.setX(atual.getX()+(dist*Configuracoes.TILE_SIZE)-47);
					Game.player.parado=true;
				}
			}
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.red);
//		g.drawRect(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
//		g.drawRect(this.getX() - Camera.x+maskx[1],this.getY() - Camera.y+masky[1],maskw[1],maskh[1]);
		
	}
						
	
}