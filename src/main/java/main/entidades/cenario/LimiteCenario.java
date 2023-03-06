package main.entidades.cenario;

import main.entidades.Entidade;
import main.entidades.Mascara;
import main.entidades.players.Player;
import main.DadosGame;
import main.enums.DirecaoPlayer;
import main.enums.TipoMascara;

import java.awt.*;

public class LimiteCenario extends Entidade {

    private final TipoLimite tipo;

    public LimiteCenario(int x, int y, int tipo, int width, int height) {
        super(x, y, width, height);
        depth = 30;
        this.tipo = TipoLimite.fromInt(tipo);
        adicionarMascara(Mascara.builder().tipoMascara(TipoMascara.HITBOX).x(62).y(y).height(height).width(width).build());
    }

    @Override
    public void tick(DadosGame dadosGame) {
        Player player = dadosGame.getPlayer();
        if (colidindo) {
            switch (tipo) {
                case DIREITO:
                    if(player.getDirecao().equals(DirecaoPlayer.DIREITA)){
                        player.setX(player.getX() - 5);
                    }
                    break;
                case ESQUERDO:
                    if(player.getDirecao().equals(DirecaoPlayer.ESQUERDA)){
                        player.setX(getX() - 19);
                    }
                    break;
            }
        }
        super.tick(dadosGame);
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        super.render(g, dadosGame);
    }

    enum TipoLimite {
        ESQUERDO(1),
        DIREITO(2),
        ABISMO(3);

        final int tipoLimite;

        TipoLimite(int i) {
            this.tipoLimite = i;
        }

        static TipoLimite fromInt(int i) {
            for (TipoLimite t : TipoLimite.values()) {
                if (t.tipoLimite == i) {
                    return t;
                }
            }
            return null;
        }
    }
}