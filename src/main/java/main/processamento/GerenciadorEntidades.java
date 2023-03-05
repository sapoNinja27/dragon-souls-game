package main.processamento;

import entidades.Entidade;
import entidades.cenario.Plataforma;
import entidades.cenario.objetoscommovimento.ObjetosComMovimento;
import entidades.players.principal.Player;
import main.DadosGame;
import main.enums.AcaoPlayer;
import main.enums.TipoAmbiente;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class GerenciadorEntidades {

    private boolean interagiu;

    private final List<Entidade> entities = new ArrayList<>();

    private boolean interagiu() {
        boolean aux = interagiu;
        interagiu = false;
        return aux;
    }
    //    public void checkCollisionEnemy() {
//        for (int i = 0; i < enemies.size(); i++) {
//            Enemy atual = enemies.get(i);
//            if (Entidade.corpoColidindo(this, atual, 1, 0)) {
//                if (atacando) {
//                    if (furia >= maxFuria) {
//                        furia = 100;
//                        if (indexAtk == 28) {
//                            defesa += 1;
//                            if (defesa >= defesaMaxima) {
//                                defesa = 100;
//                            }
//                        }
//                    } else if (furia >= maxFuria) {
//                        furia = 100;
//                        if (indexAtk == 28) {
//                            defesa += 1;
//                            if (defesa >= defesaMaxima) {
//                                defesa = 100;
//                            }
//                        }
//                    } else {
//                        if (indexAtk == 28) {
//                            furia += 1;
//                        }
//                    }
//                }
//            }
//        }
//    }

    private void verificaOpcoesColisao(Entidade atual, DadosGame dadosGame) {
//        if (atual instanceof Bueiro) {
//            if (interagiu()) {
//                Loading.start();
//                Configuracao.local = TipoAmbiente.ESGOTOS;
//                int destino = player.getY() + 898;
//                player.setY(destino);
//                player2.setY(destino);
//                Loading.stop();
//            }
//        }
//        if (atual instanceof EscadaEsgoto) {
//            if (interagiu()) {
//                Loading.start();
//                Configuracao.local = TipoAmbiente.RUA;
//                int destino = player.getY() - 955;
//                player.setY(destino);
//                player2.setY(destino);
//                Loading.stop();
//            }
//        }
//        if (atual instanceof Portao) {
//            Configuracao.p1 = player;
//            Configuracao.p2 = player2;
////            Loading.start();
////            World.changeArea();
////            Loading.stop();
//        }
//        if (atual instanceof LimiteDeCenarioAbismo) {
////            player.caindo = false;
////            player.parado = true;
////            player.vida--;
//            Configuracao.local = TipoAmbiente.TELHADO;
//        }
    }

    private void gerarEntidadesCenario(DadosGame dadosGame, List<Entidade> drawDistance) {
        drawDistance = drawDistance.stream().filter(entidade -> entities instanceof ObjetosComMovimento).collect(Collectors.toList());
        TipoAmbiente local = dadosGame.getLocal();
        Entidade obj = local.gerarEntidade(dadosGame, drawDistance);
        if (nonNull(obj)) {
            entities.add(obj);
        }
    }

    private void limparObjetos(Entidade entidade, Player player) {
        if (entidade instanceof ObjetosComMovimento) {
            if (player.distanciaX(entidade.getX()) > 1200) {
                entities.remove(entidade);
            }
            if (player.distanciaY(entidade.getY()) > 1200) {
                entities.remove(entidade);
            }
        }
    }

    private void avaliarSombreamento(Entidade entidade, DadosGame dadosGame) {
        if (entidade.isObjetoLuminoso()) return;
        Integer distanciaMinima = entities
                .stream()
                .filter(Entidade::isObjetoLuminoso)
                .map(
                        e -> (int) e.distanciaX(entidade.getX())
                )
                .sorted()
                .findFirst()
                .orElse(500);
        entidade.atualizarSombreamento(distanciaMinima > 500 ? 500 : distanciaMinima, dadosGame.isDia());
    }

    private void avaliarColisaoSimples(Entidade entidade, DadosGame dadosGame) {
        if (entidade instanceof Player) {
            Entidade target = entities
                    .stream()
                    .filter(e -> !(e instanceof Player))
                    .filter(e -> e.buscarColisao(entidade))
                    .findFirst()
                    .orElse(null);
            if (nonNull(target)) {
                entidade.atualizarColisao(target);
            }
        } else {
            entidade.atualizarColisao(dadosGame.getPlayer());
        }
    }

    private void avaliarGravidade(Entidade entidade, DadosGame dadosGame) {
        if (entidade instanceof Player) {
            Plataforma target = (Plataforma) entities
                    .stream()
                    .filter(e -> e.drawDistance(dadosGame.getPlayer()))
                    .filter(e -> e.isClasseRelativa(Plataforma.class))
                    .min(Comparator.comparingInt(e -> e.calculateDistance(entidade)))
                    .orElse(null);

            if (nonNull(target)) {
                entidade.atualizarGravidade(target);
            }
        } else {
//TODO gravidade apenas para entidades que são afetadas por ela
        }
    }

    private List<Entidade> getDrawDistance(DadosGame dadosGame) {
        return entities
                .stream()
                .filter(e -> e.drawDistance(dadosGame.getPlayer()))
                .collect(Collectors.toList());
    }

    public void addEntidade(Entidade entidade) {
        this.entities.add(entidade);
    }

    public void acaoPlayer(AcaoPlayer acaoPlayer, Player player) {
        player.executarAcao(acaoPlayer);
    }

    public void tick(DadosGame dadosGame) {
        Player player = dadosGame.getPlayer();
        if (player.isDentro()) {
            //renderiza dentro
        }
        List<Entidade> tickDistance = getDrawDistance(dadosGame);
        for (Entidade e : tickDistance) {
            e.tick(dadosGame);
            avaliarSombreamento(e, dadosGame);
            avaliarColisaoSimples(e, dadosGame);
            avaliarGravidade(e, dadosGame);
            limparObjetos(e, player);
        }

        gerarEntidadesCenario(dadosGame, tickDistance);
    }

    public void render(Graphics g, DadosGame dadosGame) {
        entities.sort(Entidade.nodeSorter);
        List<Entidade> renderDistance = getDrawDistance(dadosGame);
        for (Entidade e : renderDistance) {
            e.render(g, dadosGame);
        }
    }
}
