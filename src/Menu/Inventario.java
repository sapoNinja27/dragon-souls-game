package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Configuration.Configuracoes;
import enums.TipoGame;
import enums.TipoMenu;
import jObjects.Botao;
public class Inventario {
	Botao[] botoes = { new Botao(25, 50, 100, 25, "Opções", Color.red, 2, 16, 18, 30, 50),
			new Botao(618-25, 50, 105, 25, "Habilidade", Color.red, 2, 9, 18, 30, 50),
			new Botao(618-70, 265, 150, 25, "Voltar Ao Jogo", Color.red, 2, 13, 18, 30, 50),
			new Botao(618-70, 300, 150, 25, "Menu Principal", Color.red, 2, 13, 18, 30, 50)};
	public void tick() {
		for (int i = 0; i < botoes.length; i++) {
			botoes[i].tick();
		}
		if (botoes[0].isClicked()) {
			Configuracoes.estadoMenu = TipoMenu.OPCOESPAUSE;
		}
		if (botoes[1].isClicked()) {
			Configuracoes.estadoMenu = TipoMenu.HABILIDADES;
		}
		if (botoes[2].isClicked()) {
			Configuracoes.estadoGame = TipoGame.NORMAL;
			Configuracoes.estadoMenu = TipoMenu.INVENTARIO;
		}
		if (botoes[3].isClicked()) {
			Configuracoes.estadoGame = TipoGame.MENU;
			Configuracoes.estadoMenu = TipoMenu.INICIAL;
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < botoes.length; i++) {
			botoes[i].render(g);
		}
		g.setColor(Color.black);
		g.setFont(new Font("arial",Font.BOLD,15));
	}
	
}
