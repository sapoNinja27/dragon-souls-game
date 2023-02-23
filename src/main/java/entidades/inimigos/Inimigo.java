package entidades.inimigos;

import java.awt.image.BufferedImage;

import entidades.Entidade;

public class Inimigo extends Entidade {

	private double speed = 0.4;

	private int frames = 0,maxFrames = 20,index = 0,maxIndex = 1;

	private BufferedImage[] sprites;

	private int life = 10;

	private boolean isDamaged = false;
	private int damageFrames = 10,damageCurrent = 0;

	public Inimigo(int x, int y) {
		super(x, y, 0,0);
		sprites = new BufferedImage[2];
	}

	public void tick(){
//		tick2();
	}

	public void destroySelf() {
//		Game.enemies.remove(this);
//		Game.entities.remove(this);
	}

}
