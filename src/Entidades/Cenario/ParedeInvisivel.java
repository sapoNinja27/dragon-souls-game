package Entidades.Cenario;

import java.awt.Graphics;

import Entidades.Entity;
import Main.Game;

public class ParedeInvisivel extends Entity{
	private int index=0;
	public ParedeInvisivel(int x, int y) {
		super(x, y, 0,0);
	}
	public void setParede(int index) {
		this.index=index;
	}
	public void tick() {
		if(index==0) {
			setMask(0,0,0,1,Game.HEIGHT*11+8);
		}else if(index==1) {
			setMask(1,64-2,0,1,Game.HEIGHT*11+8);
		}
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
					Game.player.setX(atual.getX()+17);
					Game.player.parado=true;
				}
			}
		}
	}
	public void render(Graphics g) {
//		g.setColor(Color.red);
//		g.drawRect(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
//		g.drawRect(this.getX() - Camera.x+maskx[1],this.getY() - Camera.y+masky[1],maskw[1],maskh[1]);
		
	}
						
	
}