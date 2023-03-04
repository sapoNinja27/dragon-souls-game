package main.enums;

import entidades.cenario.iluminacao.DadosIluminacaoDto;
import lombok.Getter;
import main.utils.ImageUtils;

import java.awt.*;
import java.util.function.Function;

public enum TipoIluminacao {
    POSTE_LUZ((dto) -> {
        int posX = dto.getX();
        int posY = dto.getY();
        Graphics g = dto.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        g.setColor(Color.white);
        g.fillPolygon(ImageUtils.createPolygon(
                posX + 3, posY + 63,
                posX + 18, posY + 55,

                posX + 39, posY + 200,
                posX - 60, posY + 197
        ));
        posX = posX + 67;
        g.fillPolygon(ImageUtils.createPolygon(
                posX + 3, posY + 55,
                posX + 18, posY + 63,
                posX + 80, posY + 197,
                posX - 20, posY + 200
        ));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        return 0;
    });

    @Getter
    private final Function<DadosIluminacaoDto, Integer> function;

    TipoIluminacao(Function<DadosIluminacaoDto, Integer> function) {
        this.function = function;
    }

}
