package Entidades.Cenario.ObjetosComMovimento;

import Entidades.Entidade;

public class ObjetosComMovimento extends Entidade {
	protected int speed=10;
	public ObjetosComMovimento(int x, int y) {
		super(x, y,0,0);
	}
	public void setSpeed(int speed){
		this.speed=speed;
	}
}