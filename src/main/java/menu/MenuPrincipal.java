package menu;

import main.DadosGame;
import graficos.Loading;
import graficos.Spritesheet;
import jObjects.Botao;
import main.utils.MatematicaUtils;

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
                    Botao.builder().x(posicaoMeioTela).y(posicaoY)
                            .altura(91).largura(30)
                            .texto("Jogar").cor(Color.red).borda(2)
                            .spacingX(20).spacingY(20)
                            .arcoAltura(30).arcoLargura(50)
                            .acao(() -> {
                                Loading.start(dadosGame);
                                Loading.stop();
                                dadosGame.jogar();
                                //TODO verificar o por que disso
                                //				Game.player.visivel = true;
                                //				Game.player.Hudvisivel = true;
                                //				Game.player.depth = 7;
                                //				Game.cen.CenaStart(0);
                            }).build(),
                    Botao.builder().x(posicaoMeioTela + 103).y(posicaoY)
                            .altura(91).largura(30)
                            .texto("Continuar").cor(Color.red).borda(2)
                            .spacingX(20).spacingY(20)
                            .arcoAltura(30).arcoLargura(50)
                            .acao(dadosGame::carregarJogo).build(),
                    Botao.builder().x(posicaoMeioTela + 206).y(posicaoY)
                            .altura(91).largura(30)
                            .texto("Opções").cor(Color.red).borda(2)
                            .spacingX(20).spacingY(20)
                            .arcoAltura(30).arcoLargura(50)
                            .acao(dadosGame::configuracoes).build(),
                    Botao.builder().x(posicaoMeioTela + 309).y(posicaoY)
                            .altura(91).largura(30)
                            .texto("Sair").cor(Color.red).borda(2)
                            .spacingX(20).spacingY(20)
                            .arcoAltura(30).arcoLargura(50)
                            .acao(() -> System.exit(1)).build()
            ));
        }
        return botoes;
    }
}
