package main.entidades.cenario.iluminacao;

import main.DadosGame;
import main.enums.TipoIluminacao;
import main.world.Camera;

import java.awt.*;

public class Ilminacao extends ObjetoLuminoso {
    public Ilminacao(int x, int y, int width, int height, TipoIluminacao tipoIluminacao) {
        super(x, y, width, height, tipoIluminacao);
        depth = 15;
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        if (!dadosGame.isDia()) {
            int posX = this.getX() - Camera.x - getWidth() + 50;
            int posY = this.getY() - Camera.y - getHeight() * 3;
            tipoIluminacao.render(g, posX, posY);
        }
        super.render(g, dadosGame);
    }
}