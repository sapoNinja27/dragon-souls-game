package main.enums;

import entidades.Entidade;
import entidades.cenario.objetoscommovimento.LixoEsgoto;
import entidades.cenario.objetoscommovimento.ObjetosComMovimento;
import entidades.cenario.objetoscommovimento.Transito;
import entidades.players.principal.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import main.DadosGame;
import main.menu.graficos.Spritesheet;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

@AllArgsConstructor
public enum TipoAmbiente {
    PERIMETRO_SUPERIOR("Perimetro superior", (dto) -> {
        Player player = dto.getDadosGame().getPlayer();
        Random rand = player.getRand();
        DadosGame dadosGame = dto.getDadosGame();
        List<Entidade> drawDistance = dto.getDrawList();
        if (dadosGame.isDia()) {
            if (rand.nextInt(25) == 0) {
                ObjetosComMovimento am = new Transito(player.getX() - 1100, player.getY() + 64 + rand.nextInt(5), dadosGame, new Spritesheet("/cenario/cenario.png"));
                am.setSpeed(rand.nextInt(13));
                if (drawDistance.size() < rand.nextInt(3)) {
                    return am;
                }
            }
        } else {
            if (rand.nextInt(100) == 0) {
                ObjetosComMovimento am = new Transito(player.getX() - 1100, player.getY() + 64 + rand.nextInt(5), dadosGame, new Spritesheet("/cenario/cenario.png"));
                am.setSpeed(rand.nextInt(13));
                if (drawDistance.size() < rand.nextInt(2)) {
                    return am;
                }
            }
        }
        return null;
    }),
    CIDADE_DE_BAIXO("Cidade de baixo", (dto) -> {
        //TODO cidade de baixo vai ter geracao de inimigos alem de obj com movimento
        return null;
    }),
    ESGOTOS("Esgotos", (dto) -> {
        //TODO esgho vai ter geracao de inimigos alem de obj com movimento
        Player player = dto.getDadosGame().getPlayer();
        Random rand = player.getRand();
        DadosGame dadosGame = dto.getDadosGame();
        List<Entidade> drawDistance = dto.getDrawList();
        if (rand.nextInt(50) == 0) {
            ObjetosComMovimento am = new LixoEsgoto(player.getX() + 500, player.getY() + 58 + rand.nextInt(2) * 32, new Spritesheet("/cenario/cenario.png"));
            am.setSpeed(rand.nextInt(3));
            if (drawDistance.size() < rand.nextInt(10)) {
                return am;
            }
        }
        return null;
    }),
    INTERNO("Ambiente interno", (dto) -> null);

    @Getter
    private final String description;

    private final Function<DadosGameEntidadeDto, Entidade> function;

    public Entidade gerarEntidade(DadosGame dadosGame, List<Entidade> drawDistance) {
        return function.apply(DadosGameEntidadeDto
                .builder()
                .dadosGame(dadosGame)
                .drawList(drawDistance)
                .build());
    }
    @Getter
    @Builder
    static
    class DadosGameEntidadeDto {
        private final DadosGame dadosGame;
        private final List<Entidade> drawList;
    }
}
