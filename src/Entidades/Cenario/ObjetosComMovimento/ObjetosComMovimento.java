package Entidades.Cenario.ObjetosComMovimento;

import Entidades.Entity;

public class ObjetosComMovimento extends Entity{
	protected int speed=10;
	public ObjetosComMovimento(int x, int y) {
		super(x, y,0,0);
	}
	public void setSpeed(int speed){
		this.speed=speed;
	}
}