package processamento;

import configuracoes.DadosGame;
import menu.*;

import java.awt.*;

import static enums.TipoMenu.INICIAL;

public class GerenciadorMenu {
    private final MenuPrincipal menuPrincipal = new MenuPrincipal();
    private final MenuOpcoes menuOpcoes = new MenuOpcoes();
    private final MenuCarregamento menuCarregamento = new MenuCarregamento();
    private final MenuHabilidades menuHabilidades = new MenuHabilidades();
    private final MenuInventario inv = new MenuInventario();

    public void tick(DadosGame dadosGame) {
        dadosGame.getPlayer().tick(dadosGame);
        switch (dadosGame.getEstadoMenu()) {
            case INICIAL:
                menuPrincipal.tick(dadosGame);
                break;
            case LOAD:
                menuCarregamento.tick(dadosGame);
                break;
            case GAMEOVER:
                break;
            case INVENTARIO:
                inv.tick(dadosGame);
                break;
            case OPCOES:
                menuOpcoes.tick(dadosGame, dadosGame.getLastEstadoMenu().equals(INICIAL));
                break;
            case HABILIDADES:
                menuHabilidades.tick();
                break;
        }
    }

    public void render(Graphics g, DadosGame dadosGame) {
        switch (dadosGame.getEstadoMenu()) {
            case INICIAL:
                menuPrincipal.render(g);
                break;
            case LOAD:
                menuCarregamento.render(g);
                break;
            case GAMEOVER:
                break;
            case INVENTARIO:
                inv.render(g);
                break;
            case OPCOES:
                menuOpcoes.render(g, dadosGame, dadosGame.getLastEstadoMenu().equals(INICIAL));
                break;
            case HABILIDADES:
                menuHabilidades.render(g, dadosGame);
                break;
        }
    }
}
