package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Entidades.Habilidade;
import Entidades.Players.Player;

public class MenuHabilidades {
	private Color bordaMenu, fundoMenu;
	private Habilidade arvoreHabilidade = new Habilidade();
	private Player player;

	public void atualizarPlayer(Player player){
		this.player = player;
	}
	public void tick(){
		bordaMenu = player.getCoresSet().get("bordaMenu");
		fundoMenu = player.getCoresSet().get("fundoMenu");
	}
	public void render(Graphics g) {
		int x = 60;
		int y = 100;

		desenharTextosInfo(x, y, g);
		desenharFundoMenu(x, y, g);

		g.setColor(bordaMenu);
		g.drawRect(x, y + 30, 1000, 50);
		g.setColor(fundoMenu);
		g.fillRect(x, y + 30, 1000, 50);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 25));
		g.drawString("Basico", x + 30, y + 65);

		g.setColor(bordaMenu);
		g.drawRect(x, y + 30 + 200, 1000, 50);
		g.setColor(fundoMenu);
		g.fillRect(x, y  + 30 + 200, 1000, 50);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 25));
		g.drawString("Avançado", x + 30, y + 265);

		arvoreHabilidade.render(g);
	}
	private void desenharTextosInfo(int x, int y, Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 30));
		g.drawString("Habilidades", x, y - 40);

		g.setFont(new Font("arial", Font.BOLD, 11));
		g.drawString(player.tipoPlayer() + " nível " + player.getNivel(), x, y - 15);
		g.drawString("Pontos de habilidade " + player.getPontosHabilidade(), x, y + 5);
		g.drawString("XP", x, y + 25);
		g.drawRect(x + 25, y + 17, 100, 8);
		g.fillRect(x + 25, y + 17, player.getXp(), 8);
	}

	private void desenharFundoMenu(int x, int y, Graphics g){
		g.setColor(bordaMenu);
		g.drawRect(x, y + 30, 1000, 500);
		g.setColor(fundoMenu);
		g.fillRect(x, y + 30, 1000, 500);
	}
}
