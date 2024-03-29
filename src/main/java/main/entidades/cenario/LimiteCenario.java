package main.entidades.cenario;

import main.entidades.Entidade;
import main.entidades.Mascara;
import main.entidades.players.Player;
import main.DadosGame;
import main.enums.DirecaoPlayer;
import main.enums.TipoMascara;

import java.awt.*;

import static java.util.Objects.nonNull;

public class LimiteCenario extends Entidade {

    private final TipoLimite tipo;

    public LimiteCenario(int x, int y, int tipo, int width, int height) {
        super(x, y, width, height, 30, 0, 1);
        this.tipo = TipoLimite.fromInt(tipo);
        adicionarMascara(Mascara.builder().alias("limiteCenario" + tipo).x(tipo == 1 ? 0 : 62).y(y).height(height).width(width).build());
    }

    @Override
    public void tick(DadosGame dadosGame) {
        Player player = dadosGame.getPlayer();
        if (colidindoComPlayer(dadosGame.getPlayer())) {
            switch (tipo) {
                case DIREITO:
                    if (player.getDirecao().equals(DirecaoPlayer.DIREITA)) {
                        player.move(player.getX() - 5, player.getY());
                    }
                    break;
                case ESQUERDO:
                    if (player.getDirecao().equals(DirecaoPlayer.ESQUERDA)) {
                        player.move(player.getX() + 5, player.getY());
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