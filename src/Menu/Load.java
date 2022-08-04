package Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import Configuration.Configuracoes;
import Main.Game;
import enums.TipoMenu;
import jObjects.Botao;

public class Load {
	private Botao[] botoes = { new Botao(720 / 2 - 200 + 91, 120, 90, 30, "Espaço 1", Color.DARK_GRAY, 2, 5, 20, 0, 0),
			new Botao(720 / 2 - 200 + 91, 170, 90, 30, "Espaço 2", Color.DARK_GRAY, 2, 5, 20, 0, 0),
			new Botao(720 / 2 - 200 + 91, 220, 90, 30, "Espaço 3", Color.DARK_GRAY, 2, 5, 20, 0, 0),
			new Botao(580, 312, 68, 28, "Voltar", Color.red, 2, 10, 20, 30, 50), };
	public static boolean saveExists = false;
	public static boolean saveGame = false;

	public void tick() {
		for (int i = 0; i < botoes.length; i++) {
			botoes[i].tick();
		}
		File file = new File("save.txt");
		if (file.exists()) {
			saveExists = true;
		} else {
			saveExists = false;
		}
		// pra deletar file=new File("save.txt"); file.delete();

		botoes[0].setText(String.valueOf(file));
		if (botoes[0].isClicked()) {
			if (file.exists()) {
//				String saver = Game.menu.loadGame(0);
//				Game.menu.applySave(saver);
			}
		}
		if (botoes[3].isClicked()) {
			Configuracoes.estadoMenu = TipoMenu.INICIAL;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRoundRect(720 / 3 - 40, 65, 260 + 20, 200 + 20, 30, 50);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(720 / 3 - 30, 75, 260, 200, 30, 50);
		g.setColor(Color.black);
		g.fillRoundRect(580, 312, 68, 28, 30, 50);
		g.setColor(Color.black);
		for (int i = 0; i < botoes.length; i++) {
			botoes[i].render(g);
		}
		g.drawString("Carregar Jogo", 720 / 2 - 80, 95);
	}
}
