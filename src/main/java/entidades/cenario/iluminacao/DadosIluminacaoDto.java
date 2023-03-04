package entidades.cenario.iluminacao;

import lombok.Builder;
import lombok.Getter;

import java.awt.*;

@Getter
@Builder
public class DadosIluminacaoDto {
    private final int x;
    private final int y;
    private final Graphics graphics;
}