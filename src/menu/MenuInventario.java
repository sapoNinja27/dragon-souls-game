package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import configuracoes.Configuracao;
import enums.TipoGame;
import enums.TipoMenu;
import jObjects.Botao;
public class MenuInventario {
	Botao[] botoes = { new Botao(25, 50, 100, 25, "Op��es", Color.red, 2, 16, 18, 30, 50),
			new Botao(618-25, 50, 105, 25, "Habilidade", Color.red, 2, 9, 18, 30, 50),
			new Botao(618-70, 265, 150, 25, "Voltar Ao Jogo", Color.red, 2, 13, 18, 30, 50),
			new Botao(618-70, 300, 150, 25, "Menu Principal", Color.red, 2, 13, 18, 30, 50)};
	public void tick() {
		for (int i = 0; i < botoes.length; i++) {
			botoes[i].tick();
		}
		if (botoes[0].isClicked()) {
			Configuracao.estadoMenu = TipoMenu.OPCOESPAUSE;
		}
		if (botoes[1].isClicked()) {
			Configuracao.estadoMenu = TipoMenu.HABILIDADES;
		}
		if (botoes[2].isClicked()) {
			Configuracao.estadoGame = TipoGame.NORMAL;
			Configuracao.estadoMenu = TipoMenu.INVENTARIO;
		}
		if (botoes[3].isClicked()) {
			Configuracao.estadoGame = TipoGame.MENU;
			Configuracao.estadoMenu = TipoMenu.INICIAL;
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
