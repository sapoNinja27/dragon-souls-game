package main.processamento;

import jObjects.Mouse.Mouse;
import main.DadosGame;
import main.enums.TipoMenu;
import main.menu.Menu;
import main.menu.*;

import java.awt.*;
import java.util.HashMap;

import static main.enums.TipoMenu.*;

public class GerenciadorMenu {
    private final HashMap<TipoMenu, Menu> menus = new HashMap<TipoMenu, Menu>() {{
        put(INICIAL, new MenuPrincipal());
        put(OPCOES, new MenuOpcoes());
        put(LOAD, new MenuCarregamento());
        put(HABILIDADES,new MenuHabilidades());
        put(INVENTARIO,new MenuInventario());
    }};

    public void tick(DadosGame dadosGame, Mouse mouse) {
        dadosGame.getPlayer().tick(dadosGame);
        menus.get(dadosGame.getEstadoMenu()).tick(dadosGame, mouse);
    }

    public void render(Graphics g, DadosGame dadosGame) {
        menus.get(dadosGame.getEstadoMenu()).render(g, dadosGame);
    }
}
