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

	public void applySave(String str) {
		String[] spl = str.split("/");
		for (int i = 0; i < spl.length; i++) {
//			String[] spl2 = spl[i].split(":");
//			switch (spl2[0]) {
//			case "level":
//				World.restartGame("level" + spl2[1] + ".png");
//				Configuracoes.estadoGame = TipoGame.NORMAL;
//				Configuracoes.estadoMenu = TipoMenu.HABILIDADES;
//				break;
//			}
		}
	}

	public String loadGame(int encode) {
		String line = "";
		File file = new File("save.txt");
		if (file.exists()) {
			try {
				String singleLine = null;
				BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
				try {
					while ((singleLine = reader.readLine()) != null) {
						String[] trans = singleLine.split(":");
						char[] val = trans[1].toCharArray();
						trans[1] = "";
						for (int i = 0; i < val.length; i++) {
							val[i] -= encode;
							trans[1] += val[i];
						}
						line += trans[0];
						line += ":";
						line += trans[1];
						line += "/";
					}
				} catch (IOException e) {

				}
			} catch (FileNotFoundException e) {

			}
		}
		return line;
	}

	public void saveGame(String[] val1, int[] val2, int encode) {
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter("save.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < val1.length; i++) {
			String current = val1[i];
			current += ":";
			char[] value = Integer.toString(val2[i]).toCharArray();
			for (int n = 0; n < value.length; n++) {
				value[n] += encode;
				current += value[n];
			}
			try {
				write.write(current);
				if (i < val1.length - 1) {
					write.newLine();
				}
			} catch (IOException e) {

			}
			try {
				write.flush();
				write.close();
			} catch (IOException e) {

			}
		}
	}

	public void tick() {
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
				World.startGame();
				Loading.stop();
				Configuracoes.estadoGame = TipoGame.NORMAL;
				Configuracoes.estadoMenu = TipoMenu.HABILIDADES;
				Game.player.visivel = true;
				Game.player.Hudvisivel = true;
				Game.player.depth = 7;
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
			g.drawImage(Game.fundo.getSprite(posx, posy, 1440, 720), 0, 0, 720, 360, null);
		} else if (Configuracoes.estadoMenu == TipoMenu.HABILIDADES || Configuracoes.estadoMenu == TipoMenu.INVENTARIO
				|| Configuracoes.estadoMenu == TipoMenu.OPCOESPAUSE) {
			if (Game.player.getId()==0) {
				// menu do tai
				g.drawImage(Game.fundoT.getSprite(posx, posy, 1440, 720), 0, 0, 720, 360, null);
			} else if (Game.player.getId()==2) {
				// menu do ace
				g.drawImage(Game.fundoA.getSprite(posx, posy, 1440, 720), 0, 0, 720, 360, null);
			} else if (Game.player.getId()==1) {
				// menu do sander
				g.drawImage(Game.fundoS.getSprite(posx, posy, 1440, 720), 0, 0, 720, 360, null);
			}
		}
		if (Configuracoes.estadoMenu == TipoMenu.INICIAL) {
			g.drawImage(Game.Menu.getSprite(2700, 0, 400, 200), 720 / 2 - 200, 30, null);
			for (int i = 0; i < botoes.length; i++) {
				botoes[i].render(g);
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
