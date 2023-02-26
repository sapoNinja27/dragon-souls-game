package menu;

import configuracoes.DadosGame;
import graficos.Loading;
import graficos.Spritesheet;
import jObjects.Botao;
import utils.MatematicaUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuPrincipal extends Menu {
    private final BufferedImage logo;
    private final List<Botao> botoes = new ArrayList<>(0);
    private final int widthLogo = 366;
    private final int heigthLogo = 153;

    public MenuPrincipal() {
        Spritesheet spritesheet = new Spritesheet("/menus/logo compacto.png");
        logo = spritesheet.getSprite(0, 0, widthLogo, heigthLogo);
        fundo = new Spritesheet("/menus/fundo.png");
    }

    @Override
    public void tick(DadosGame dadosGame) {
        super.tick(dadosGame);
        for (Botao botao : getBotoes(dadosGame)) {
            botao.tick();
            if (botao.isClicked()) {
                botao.doAction();
            }
        }
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        super.render(g, dadosGame);
        g.drawImage(logo,
                MatematicaUtils.posicaoMeio(widthLogo * 2, dadosGame.getScaleWidth()), 100,
                widthLogo * 2, heigthLogo * 2,
                null);
        for (Botao botao : getBotoes(dadosGame)) {
            botao.render(g);
        }
    }

    private List<Botao> getBotoes(DadosGame dadosGame) {
        int posicaoY = 435;
        int posicaoMeioTela = MatematicaUtils.posicaoMeio(399, dadosGame.getScaleWidth());
        if (botoes.isEmpty()) {
            botoes.addAll(Arrays.asList(
                    new Botao(posicaoMeioTela, posicaoY, 91, 30, "Jogar", Color.red, 2, 20, 20, 30, 50, () -> {
                        Loading.start(dadosGame);
                        Loading.stop();
                        dadosGame.jogar();
                        //TODO verificar o por que disso
                        //				Game.player.visivel = true;
                        //				Game.player.Hudvisivel = true;
                        //				Game.player.depth = 7;
                        //				Game.cen.CenaStart(0);
                    }),
                    new Botao(posicaoMeioTela + 103, posicaoY, 90, 30, "Continuar", Color.red, 2, 3, 20, 30, 50, dadosGame::carregarJogo),
                    new Botao(posicaoMeioTela + 206, posicaoY, 90, 30, "Opções", Color.red, 2, 13, 20, 30, 50, dadosGame::configuracoes),
                    new Botao(posicaoMeioTela + 309, posicaoY, 90, 30, "Sair", Color.red, 2, 26, 20, 30, 50, () -> System.exit(1))
            ));
        }
        return botoes;
    }
}
