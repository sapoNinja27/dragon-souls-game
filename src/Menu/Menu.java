package Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Configuration.Configuracoes;
import Main.Game;
import World.World;
import enums.TipoGame;
import enums.TipoMenu;
import jObjects.Botao;

public class Menu {
	Options menOp = new Options();
	Load menLo = new Load();
	Habilidades hab = new Habilidades();
	Inventario inv = new Inventario();
	int posx = 0, posy = 0;
	private Botao[] botoes = { new Botao(720 / 2 - 200, 250, 91, 30, "Jogar", Color.red, 2, 20, 20, 30, 50),
			new Botao(720 / 2 - 200 + 91 + 12, 250, 90, 30, "Continuar", Color.red, 2, 3, 20, 30, 50),
			new Botao(720 / 2 - 200 + 91 * 2 + 12 * 2, 250, 90, 30, "Opções", Color.red, 2, 13, 20, 30, 50),
			new Botao(720 / 2 - 200 + 91 * 3 + 12 * 3, 250, 90, 30, "Sair", Color.red, 2, 26, 20, 30, 50), };


	public void tick() {
		//TODO apagar
//		Loading.start();
//				world.startGame();
//		Loading.stop();
		Configuracoes.estadoGame = TipoGame.NORMAL;


		posx++;
		if (posx == 2976 - 1440) {
			posx = 0;
		}
		if (Configuracoes.estadoMenu == TipoMenu.INICIAL) {
			for (int i = 0; i < botoes.length; i++) {
				botoes[i].tick();
			}
			if (botoes[0].isClicked()) {
				Loading.start();
//				world.startGame();
				Loading.stop();
				Configuracoes.estadoGame = TipoGame.NORMAL;
				Configuracoes.estadoMenu = TipoMenu.HABILIDADES;
//				Game.player.visivel = true;
//				Game.player.Hudvisivel = true;
//				Game.player.depth = 7;
//				Game.cen.CenaStart(0);
			}
			if (botoes[1].isClicked()) {
				Configuracoes.estadoMenu = TipoMenu.LOAD;
			}
			if (botoes[2].isClicked()) {
				Configuracoes.estadoMenu = TipoMenu.OPCOESPRINCIPAL;
			}
			if (botoes[3].isClicked()) {
				System.exit(1);
			}
		}else if (Configuracoes.estadoMenu == TipoMenu.LOAD) {
			menLo.tick();
		} else if (Configuracoes.estadoMenu == TipoMenu.OPCOESPAUSE
				|| Configuracoes.estadoMenu == TipoMenu.OPCOESPRINCIPAL) {
			menOp.tick();
		} else if (Configuracoes.estadoMenu == TipoMenu.HABILIDADES) {
			hab.tick();
		} else if (Configuracoes.estadoMenu == TipoMenu.INVENTARIO) {
			inv.tick();
		}
	}

	public void render(Graphics g) {
		if (Configuracoes.estadoMenu == TipoMenu.LOAD || Configuracoes.estadoMenu == TipoMenu.INICIAL
				|| Configuracoes.estadoMenu == TipoMenu.OPCOESPRINCIPAL) {
//			g.drawImage(fundo.getSprite(posx, posy, 1440, 720), 0, 0, 720, 360, null);
		} else if (Configuracoes.estadoMenu == TipoMenu.HABILIDADES || Configuracoes.estadoMenu == TipoMenu.INVENTARIO
				|| Configuracoes.estadoMenu == TipoMenu.OPCOESPAUSE) {
		}
		if (Configuracoes.estadoMenu == TipoMenu.INICIAL) {
//			g.drawImage(Game.Menu.getSprite(2700, 0, 400, 200), 720 / 2 - 200, 30, null);
			for (Botao botoe : botoes) {
				botoe.render(g);
			}
		} else if (Configuracoes.estadoMenu == TipoMenu.LOAD) {
			menLo.render(g);
		} else if (Configuracoes.estadoMenu == TipoMenu.OPCOESPRINCIPAL || Configuracoes.estadoMenu == TipoMenu.OPCOESPAUSE) {
			menOp.render(g);
		} else if (Configuracoes.estadoMenu == TipoMenu.HABILIDADES) {
			hab.render(g);
		} else if (Configuracoes.estadoMenu == TipoMenu.INVENTARIO) {
			inv.render(g);
		}
	}
}
