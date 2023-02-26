package menu;

import configuracoes.DadosGame;
import graficos.Loading;
import graficos.Spritesheet;
import jObjects.Botao;

import java.awt.*;

public class MenuPrincipal {
    private final Spritesheet fundo;
    private final Spritesheet logo;
    private int posx = 0;
    private final Botao[] botoes = {
            new Botao(720 / 2 - 200, 250, 91, 30, "Jogar", Color.red, 2, 20, 20, 30, 50),
            new Botao(720 / 2 - 200 + 91 + 12, 250, 90, 30, "Continuar", Color.red, 2, 3, 20, 30, 50),
            new Botao(720 / 2 - 200 + 91 * 2 + 12 * 2, 250, 90, 30, "Opções", Color.red, 2, 13, 20, 30, 50),
            new Botao(720 / 2 - 200 + 91 * 3 + 12 * 3, 250, 90, 30, "Sair", Color.red, 2, 26, 20, 30, 50)
    };

    public MenuPrincipal() {
        fundo = new Spritesheet("/menus/fundo.png");
        logo = new Spritesheet("/menus/Menu.png");
    }

    private void atualizarFundo() {
        posx++;
        if (posx == 1536) {
            posx = 0;
        }
    }

    public void tick(DadosGame dadosGame) {
        atualizarFundo();
        for (int i = 0; i < botoes.length; i++) {
            botoes[i].tick();
        }
        if (botoes[0].isClicked()) {
            Loading.start(dadosGame);
//				world.startGame();
            Loading.stop();
            dadosGame.jogar();
//				Game.player.visivel = true;
//				Game.player.Hudvisivel = true;
//				Game.player.depth = 7;
//				Game.cen.CenaStart(0);
        }
        if (botoes[1].isClicked()) {
            dadosGame.carregarJogo();
        }
        if (botoes[2].isClicked()) {
            dadosGame.configuracoes();
        }
        if (botoes[3].isClicked()) {
            System.exit(1);
        }
    }

    public void render(Graphics g) {
        g.drawImage(logo.getSprite(2700, 0, 400, 200), 720 / 2 - 200, 30, null);
        for (Botao botoe : botoes) {
            botoe.render(g);
        }
    }
}
