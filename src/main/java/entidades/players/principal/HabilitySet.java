package entidades.players.principal;

import entidades.players.principal.habilidades.Habilidade;
import entidades.players.principal.habilidades.*;
import main.utils.MatematicaUtils;

import java.util.Arrays;
import java.util.List;

public class HabilitySet {
    private static final int DEFAULT_POSITION = MatematicaUtils.posicaoMeio(64, 333);
    private final Habilidade atacar = new PosturaOfensiva(DEFAULT_POSITION, 240, 64, 64);
    private final Habilidade bloquear = new PosturaDefensiva(333 + DEFAULT_POSITION, 240, 64, 64);
    private final Habilidade transformar = new PosturaSelvagem(666 + DEFAULT_POSITION, 240, 64, 64);
    private final Habilidade upgrade = new PosturaCalma(100 + DEFAULT_POSITION, 540, 64, 64);
    private final Habilidade ondaDeChoque = new PosturaIrritada(433 + DEFAULT_POSITION, 540, 64, 64);
    private final Habilidade pulsoGravitacional = new PosturaFirme(766 + DEFAULT_POSITION, 540, 64, 64);

    public List<Habilidade> getHabilidades() {
        return Arrays.asList(atacar, bloquear, transformar, upgrade, ondaDeChoque, pulsoGravitacional);
    }
}
