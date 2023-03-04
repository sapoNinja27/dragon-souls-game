package entidades.players.principal;

import entidades.players.principal.habilidades.Habilidade;
import entidades.players.principal.habilidades.*;

import java.util.Arrays;
import java.util.List;

public class HabilitySet {
    private final Habilidade atacar = new PosturaOfensiva();
    private final Habilidade bloquear = new PosturaDefensiva();
    private final Habilidade transformar = new PosturaSelvagem();
    private final Habilidade upgrade = new PosturaCalma();
    private final Habilidade ondaDeChoque = new PosturaIrritada();
    private final Habilidade pulsoGravitacional = new PosturaFirme();

    public List<Habilidade> getHabilidades() {
        return Arrays.asList(atacar, bloquear, transformar, upgrade, ondaDeChoque, pulsoGravitacional);
    }
}
