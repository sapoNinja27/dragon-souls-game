package entidades.players.principal.habilidades;

import jObjects.Botao;
import jObjects.Mouse.Mouse;
import lombok.Getter;
import main.enums.MovimentoPlayer;
import main.utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Getter
public class Habilidade {
    private final int x;
    private final int y;
    private final int width;
    private final int heigth;
    private int nivel;
    private int melhoria;
    private final List<BufferedImage> filhas = new ArrayList<>();
    private Botao habilidade;

    private final String titulo;
    private final String descricao;
    private final String custo;
    private final BufferedImage icone;
    private final MovimentoPlayer movimentoPlayer;
    private final boolean basica;

    public Habilidade(int x, int y, int width, int heigth, String titulo, String descricao, String custo, BufferedImage icone, MovimentoPlayer movimentoPlayer, boolean basica) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
        this.titulo = titulo;
        this.descricao = descricao;
        this.custo = custo;
        this.icone = icone;
        this.movimentoPlayer = movimentoPlayer;
        this.basica = basica;
    }

    public void tick(Mouse mouse) {
        getBotao().tick(mouse);
    }

    public void render(Graphics g) {
        getBotao().render(g);
    }

    public boolean isOver() {
        return getBotao().isOver();
    }

    private Botao getBotao() {
        if (isNull(habilidade)) {
            habilidade = Botao.builder()
                    .custom(true)
                    .x(x)
                    .y(y)
                    .width(width)
                    .height(heigth)
                    .mascara(
                            ImageUtils.createPolygon(
                                    x, y + 18,
                                    x + 18, y,
                                    x + 44, y,
                                    x + 64, y + 18,
                                    x + 64, y + 44,
                                    x + 44, y + 64,
                                    x + 18, y + 64,
                                    x, y + 44))
                    .image(icone)
                    .build();
        }
        return habilidade;
    }
}
