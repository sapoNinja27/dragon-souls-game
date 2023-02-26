package processamento;

import configuracoes.DadosGame;
import enums.TipoMenu;
import menu.*;
import menu.Menu;

import java.awt.*;
import java.util.HashMap;

import static enums.TipoMenu.*;

public class GerenciadorMenu {
    private final HashMap<TipoMenu, Menu> menus = new HashMap<TipoMenu, Menu>() {{
        put(INICIAL, new MenuPrincipal());
        put(OPCOES, new MenuOpcoes());
        put(LOAD, new MenuCarregamento());
        put(HABILIDADES,new MenuHabilidades());
        put(INVENTARIO,new MenuInventario());
    }};

    public void tick(DadosGame dadosGame) {
        dadosGame.getPlayer().tick(dadosGame);
        menus.get(dadosGame.getEstadoMenu()).tick(dadosGame);
    }

    public void render(Graphics g, DadosGame dadosGame) {
        menus.get(dadosGame.getEstadoMenu()).render(g, dadosGame);
    }
}
