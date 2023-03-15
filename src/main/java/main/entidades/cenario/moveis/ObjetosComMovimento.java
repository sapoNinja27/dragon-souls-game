package main.entidades.cenario.moveis;

import main.entidades.Entidade;
import lombok.Setter;

@Setter
public class ObjetosComMovimento extends Entidade {
    public ObjetosComMovimento(int x, int y) {
        super(x, y, 0, 0, 10, 10, 1);
    }
}