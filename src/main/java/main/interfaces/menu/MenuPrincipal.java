package main.interfaces.menu;

import main.interfaces.mouse.Mouse;
import main.DadosGame;
import main.Loading;
import main.utils.Spritesheet;
import main.interfaces.Botao;
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
        super("", 0, 0);
        Spritesheet spritesheet = new Spritesheet("/menus/logo compacto.png");
        logo = spritesheet.getSprite(0, 0, widthLogo, heigthLogo);
        fundo = new Spritesheet("/menus/fundo.png").getSprite();
    }

    @Override
    public void tick(DadosGame dadosGame, Mouse mouse) {
        super.tick(dadosGame, mouse);
        for (Botao botao : getBotoes(dadosGame)) {
            botao.tick(mouse);
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
                            .width(91).height(30)
                            .texto("Jogar").cor(Color.red).borda(2)
                            .spacingX(22).spacingY(20)
                            .arcWidth(30).arcHeight(50)
                            .acao(() -> {
                                Loading.start(dadosGame);
                                Loading.stop();
                                dadosGame.jogar();
                            }).build(),
                    Botao.builder().x(posicaoMeioTela + 103).y(posicaoY)
                            .width(91).height(30)
                            .texto("Continuar").cor(Color.red).borda(2)
                            .spacingX(3).spacingY(20)
                            .arcWidth(30).arcHeight(50)
                            .acao(dadosGame::menuLoad).build(),
                    Botao.builder().x(posicaoMeioTela + 206).y(posicaoY)
                            .width(91).height(30)
                            .texto("Op��es").cor(Color.red).borda(2)
                            .spacingX(16).spacingY(20)
                            .arcWidth(30).arcHeight(50)
                            .acao(dadosGame::configuracoes).build(),
                    Botao.builder().x(posicaoMeioTela + 309).y(posicaoY)
                            .width(91).height(30)
                            .texto("Sair").cor(Color.red).borda(2)
                            .spacingX(29).spacingY(20)
                            .arcWidth(30).arcHeight(50)
                            .acao(() -> System.exit(1)).build()
            ));
        }
        return botoes;
    }
}
