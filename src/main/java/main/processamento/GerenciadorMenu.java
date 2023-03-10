package main.processamento;

import main.DadosGame;
import main.enums.TipoMenu;
import main.interfaces.menu.Menu;
import main.interfaces.menu.*;
import main.interfaces.mouse.Mouse;

import java.awt.*;
import java.util.HashMap;

import static main.enums.TipoMenu.*;

public class GerenciadorMenu {
    private final HashMap<TipoMenu, Menu> menus = new HashMap<TipoMenu, Menu>() {{
        put(INICIAL, new MenuPrincipal());
        put(OPCOES, new MenuOpcoes("Configurações", 1300, 535));
        put(LOAD, new MenuCarregamento("Carregar Jogo", 1300, 534));
        put(HABILIDADES,new MenuHabilidades("Habilidades", 1000, 535));
        put(INVENTARIO,new MenuInventario("Inventário", 1000, 535));
    }};

    public void tick(DadosGame dadosGame, Mouse mouse) {
        dadosGame.getPlayer().tick(dadosGame);
        menus.get(dadosGame.getEstadoMenu()).tick(dadosGame, mouse);
    }

    public void render(Graphics g, DadosGame dadosGame) {
        menus.get(dadosGame.getEstadoMenu()).render(g, dadosGame);
    }
}
