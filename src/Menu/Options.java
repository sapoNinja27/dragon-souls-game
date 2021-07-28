package Menu;

import java.awt.Color;
import java.awt.Graphics;

import Configuration.Configuracoes;
import enums.TipoGame;
import enums.TipoMenu;
import jObjects.Botao;

public class Options {
	public int mx, my;
	private Botao[] principal = {
//			new Botao(720 / 2 - 200 + 91, 220, 90, 30, "Portugues", Color.DARK_GRAY, 2, 5, 20, 30, 50),
			new Botao(580, 312, 68, 28, "Voltar", Color.red, 2, 10, 20, 30, 50), };
	Botao[] pause = { new Botao(25, 50, 105, 25, "Habilidade", Color.red, 2, 9, 18, 30, 50),
			new Botao(618-25, 50, 100, 25, "Inventario", Color.red, 2, 9, 18, 30, 50),
			new Botao(618-70, 265, 150, 25, "Voltar Ao Jogo", Color.red, 2, 13, 18, 30, 50),
			new Botao(618-70, 300, 150, 25, "Menu Principal", Color.red, 2, 13, 18, 30, 50)};
	private Botao[] botoes = {
			new Botao(720 / 2 - 9, 120, 90, 30, "On", Color.green, 2, 5, 20,30, 50),
			new Botao(720 / 2 - 9, 170, 90, 30, "On", Color.green, 2, 5, 20, 30, 50)};
	public void tick() {
		for (int i = 0; i < botoes.length; i++) {
			botoes[i].tick();
		}
		if (Configuracoes.estadoMenu == TipoMenu.OPCOESPRINCIPAL) {
			for (int i = 0; i < principal.length; i++) {
				principal[i].tick();
			}
		}else if (Configuracoes.estadoMenu == TipoMenu.OPCOESPAUSE) {
			for (int i = 0; i < pause.length; i++) {
				pause[i].tick();
			}
		}
		if (botoes[0].isClicked()) {
			if(Configuracoes.musica) {
				botoes[0].setText(String.valueOf("Off"));
				botoes[0].setCor(Color.red);
				botoes[0].setSpacing(60,20);
				Configuracoes.musica=false;
			}else if(!Configuracoes.musica) {
				botoes[0].setText(String.valueOf("On"));
				botoes[0].setCor(Color.green);
				botoes[0].setSpacing(5,20);
				Configuracoes.musica=true;
			}
		}
		if (botoes[1].isClicked()) {
			if(Configuracoes.efeitos) {
				botoes[1].setText(String.valueOf("Off"));
				botoes[1].setCor(Color.red);
				botoes[1].setSpacing(60,20);
				Configuracoes.efeitos=false;
			}else if(!Configuracoes.efeitos) {
				botoes[1].setText(String.valueOf("On"));
				botoes[1].setCor(Color.green);
				botoes[1].setSpacing(5,20);
				Configuracoes.efeitos=true;
			}
		}
		if (principal[0].isClicked()) {
			Configuracoes.estadoMenu=TipoMenu.INICIAL;
		}
		if (pause[0].isClicked()) {
			Configuracoes.estadoMenu = TipoMenu.HABILIDADES;
		}
		if (pause[1].isClicked()) {
			Configuracoes.estadoMenu = TipoMenu.INVENTARIO;
		}
		if (pause[2].isClicked()) {
			Configuracoes.estadoGame = TipoGame.NORMAL;
			Configuracoes.estadoMenu = TipoMenu.OPCOESPAUSE;
		}
		if (pause[3].isClicked()) {
			Configuracoes.estadoGame = TipoGame.MENU;
			Configuracoes.estadoMenu = TipoMenu.INICIAL;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRoundRect(720 / 3 - 40, 65, 260 + 20, 200 + 20, 30, 50);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(720 / 3 - 30, 75, 260, 200, 30, 50);
		g.setColor(Color.black);
		g.drawString("Opções", 720 / 3 + 55, 95);
		g.drawString("Musica", 720 / 3 + 10, 140);
		g.drawString("Efeitos", 720 / 3 + 10, 190);
//		// volume
//		g.setColor(Color.black);
//		g.fillRect(720 / 2 + 20, 85, 99, 20);
//		if (Menu[currentOption] == "Volume") {
//			g.fillRect(720 / 2 + 20, 85, volume, 20);
//		} else {
//			g.fillRect(720 / 2 + 20, 85, Mouse.getX(), 20);
//		}

		if (Configuracoes.estadoMenu == TipoMenu.OPCOESPRINCIPAL) {
			for (int i = 0; i < principal.length; i++) {
				principal[i].render(g);
			}
		} else  if (Configuracoes.estadoMenu == TipoMenu.OPCOESPAUSE) {
			for (int i = 0; i < pause.length; i++) {
				pause[i].render(g);
			}
		}
		for (int i = 0; i < botoes.length; i++) {
			botoes[i].render(g);
		}
	}
}
