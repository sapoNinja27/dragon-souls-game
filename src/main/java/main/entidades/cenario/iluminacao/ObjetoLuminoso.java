package main.entidades.cenario.iluminacao;

import main.entidades.Entidade;
import main.enums.TipoIluminacao;

public class ObjetoLuminoso extends Entidade {
    protected TipoIluminacao tipoIluminacao;

    public ObjetoLuminoso(int x, int y, int width, int height, TipoIluminacao tipoIluminacao) {
        super(x, y, width, height);
        this.tipoIluminacao = tipoIluminacao;
    }
}
