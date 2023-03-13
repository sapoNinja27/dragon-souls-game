package main.entidades.players.habilidades;

import main.interfaces.Botao;
import main.interfaces.mouse.Mouse;
import lombok.Getter;
import main.enums.MovimentoPlayer;
import main.utils.ImageUtils;
import main.utils.MatematicaUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Habilidade {
    private final int x;
    private final int y;
    private final int width;
    private final int heigth;
    private int nivel;
    private int melhoria;
    private final List<BufferedImage> filhas = new ArrayList<>();
    private final Botao botao;

    private final String titulo;
    private final String descricao;
    private final String custo;
    private final BufferedImage icone;
    private final int actionButton;
    private final MovimentoPlayer movimentoPlayer;
    private final boolean transformacao;

    public Habilidade(int x, int y, int width, int heigth, String titulo, String descricao, String custo, BufferedImage icone, MovimentoPlayer movimentoPlayer, boolean transformacao, int actionButton) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
        this.titulo = titulo;
        this.descricao = descricao;
        this.custo = custo;
        this.icone = icone;
        this.movimentoPlayer = movimentoPlayer;
        this.transformacao = transformacao;
        this.actionButton = actionButton;
        botao = createBotao(x, y, width, heigth);
    }

    public void tick(Mouse mouse) {
        botao.tick(mouse);
    }

    public void render(Graphics g) {
        botao.render(g);
    }

    public boolean isOver() {
        return botao.isOver();
    }

    private Botao createBotao(int x, int y, int width, int heigth) {
        return Botao.builder()
                .custom(true)
                .x(x)
                .y(y)
                .width(width)
                .height(heigth)
                .mascara(
                        ImageUtils.createPolygon(
                                x, y + MatematicaUtils.porcentagem(heigth, 18),
                                x + MatematicaUtils.porcentagem(width, 18), y,
                                x + MatematicaUtils.porcentagem(width, 44), y,
                                x + width, y + MatematicaUtils.porcentagem(heigth, 18),
                                x + width, y + MatematicaUtils.porcentagem(heigth, 44),
                                x + MatematicaUtils.porcentagem(width, 44), y + heigth,
                                x + MatematicaUtils.porcentagem(width, 18), y + heigth,
                                x, y + MatematicaUtils.porcentagem(heigth, 44)))
                .image(icone)
                .build();
    }
}
