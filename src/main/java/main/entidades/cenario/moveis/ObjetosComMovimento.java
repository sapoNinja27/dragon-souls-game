package main.entidades.cenario.moveis;

import main.entidades.Entidade;
import lombok.Setter;

@Setter
public class ObjetosComMovimento extends Entidade {
    protected int speed = 10;

    public ObjetosComMovimento(int x, int y) {
        super(x, y, 0, 0);
    }
}