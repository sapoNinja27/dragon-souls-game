package Menu;

import java.awt.Graphics;

import Configuration.Configuracoes;
import Entidades.Players.Player;
import enums.TipoMenu;

public class GerenciadorMenu {
	private MenuPrincipal menuPrincipal = new MenuPrincipal();
	private MenuOpcoes menuOpcoes = new MenuOpcoes();
	private MenuCarregamento menuCarregamento = new MenuCarregamento();
	private MenuHabilidades menuHabilidades = new MenuHabilidades();
	private Inventario inv = new Inventario();

	public GerenciadorMenu() {

	}

	public void atualizarPlayer(Player player){
		menuHabilidades.atualizarPlayer(player);
	}

	public void tick() {
		if (Configuracoes.estadoMenu == TipoMenu.INICIAL) {
			menuPrincipal.tick();
		}else if (Configuracoes.estadoMenu == TipoMenu.LOAD) {
			menuCarregamento.tick();
		} else if (Configuracoes.estadoMenu == TipoMenu.OPCOESPAUSE
				|| Configuracoes.estadoMenu == TipoMenu.OPCOESPRINCIPAL) {
			menuOpcoes.tick();
		} else if (Configuracoes.estadoMenu == TipoMenu.HABILIDADES) {
			menuHabilidades.tick();
		} else if (Configuracoes.estadoMenu == TipoMenu.INVENTARIO) {
			inv.tick();
		}
	}

	public void render(Graphics g) {
		if (Configuracoes.estadoMenu == TipoMenu.INICIAL) {
			menuPrincipal.render(g);
		} else if (Configuracoes.estadoMenu == TipoMenu.LOAD) {
			menuCarregamento.render(g);
		} else if (Configuracoes.estadoMenu == TipoMenu.OPCOESPRINCIPAL || Configuracoes.estadoMenu == TipoMenu.OPCOESPAUSE) {
			menuOpcoes.render(g);
		} else if (Configuracoes.estadoMenu == TipoMenu.HABILIDADES) {
			menuHabilidades.render(g);
		} else if (Configuracoes.estadoMenu == TipoMenu.INVENTARIO) {
			inv.render(g);
		}
	}
}
