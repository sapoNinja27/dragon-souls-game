package processamento;

import java.awt.Graphics;

import configuracoes.Configuracao;
import entidades.players.Player;
import menu.*;
import enums.TipoMenu;

public class GerenciadorMenu {
	private MenuPrincipal menuPrincipal = new MenuPrincipal();
	private MenuOpcoes menuOpcoes = new MenuOpcoes();
	private MenuCarregamento menuCarregamento = new MenuCarregamento();
	private MenuHabilidades menuHabilidades = new MenuHabilidades();
	private MenuInventario inv = new MenuInventario();

	public GerenciadorMenu() {

	}

	public void atualizarPlayer(Player player){
		menuHabilidades.atualizarPlayer(player);
	}

	public void tick() {
		if (Configuracao.estadoMenu == TipoMenu.INICIAL) {
			menuPrincipal.tick();
		}else if (Configuracao.estadoMenu == TipoMenu.LOAD) {
			menuCarregamento.tick();
		} else if (Configuracao.estadoMenu == TipoMenu.OPCOESPAUSE
				|| Configuracao.estadoMenu == TipoMenu.OPCOESPRINCIPAL) {
			menuOpcoes.tick();
		} else if (Configuracao.estadoMenu == TipoMenu.HABILIDADES) {
			menuHabilidades.tick();
		} else if (Configuracao.estadoMenu == TipoMenu.INVENTARIO) {
			inv.tick();
		}
	}

	public void render(Graphics g) {
		if (Configuracao.estadoMenu == TipoMenu.INICIAL) {
			menuPrincipal.render(g);
		} else if (Configuracao.estadoMenu == TipoMenu.LOAD) {
			menuCarregamento.render(g);
		} else if (Configuracao.estadoMenu == TipoMenu.OPCOESPRINCIPAL || Configuracao.estadoMenu == TipoMenu.OPCOESPAUSE) {
			menuOpcoes.render(g);
		} else if (Configuracao.estadoMenu == TipoMenu.HABILIDADES) {
			menuHabilidades.render(g);
		} else if (Configuracao.estadoMenu == TipoMenu.INVENTARIO) {
			inv.render(g);
		}
	}
}
