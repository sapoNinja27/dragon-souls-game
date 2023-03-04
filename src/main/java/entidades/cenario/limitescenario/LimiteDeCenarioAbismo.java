package entidades.cenario.limitescenario;

import main.DadosGame;
import entidades.Entidade;
import main.world.Camera;

import java.awt.*;
public class LimiteDeCenarioAbismo extends Entidade {

	public LimiteDeCenarioAbismo(int x, int y, int width, int height) {
		super(x, y, width, height);
//		adicionarMascara(new MascaraHitBox(0, 0, 64, 64));
	}
//TODO mover a responsabilidade de setar o player pra ca
	@Override
	public void render(Graphics g, DadosGame dadosGame) {
		g.setColor(Color.black);
		g.fillOval(this.getX() - Camera.x, this.getY() - Camera.y - 10, 250, 250);
	}

}