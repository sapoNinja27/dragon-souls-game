package entidades;

import main.enums.TipoMascara;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * Representa a mascara da entidade
 * */
public class Mascara {

    private String nome;
    private int x;
    private int y;
    private int altura;
    private int largura;
    private TipoMascara tipoMascara;
}
