package entidades.cenario.iluminacao;

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
            int posX = this.getX() - Camera.x - width + 50;
            int posY = this.getY() - Camera.y - height * 3;
            tipoIluminacao.getFunction().apply(
                    DadosIluminacaoDto
                            .builder()
                            .graphics(g)
                            .x(posX)
                            .y(posY)
                            .build()
            );
        }
        super.render(g, dadosGame);
    }
}