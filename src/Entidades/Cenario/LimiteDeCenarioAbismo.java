package Entidades.Cenario;

import java.awt.Color;
import java.awt.Graphics;
import Entidades.Entidade;
import Menu.MascaraHitBox;
import World.Camera;
public class LimiteDeCenarioAbismo extends Entidade {

	public LimiteDeCenarioAbismo(int x, int y, int width, int height) {
		super(x, y, width, height);
		adicionarMascara(new MascaraHitBox(0, 0, 64, 64));
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(this.getX() - Camera.x, this.getY() - Camera.y - 10, 250, 250);
	}

}