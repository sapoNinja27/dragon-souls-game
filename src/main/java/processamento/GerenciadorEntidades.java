package processamento;

import main.DadosGame;
import entidades.Entidade;
import entidades.cenario.Plataforma;
import entidades.cenario.Predio;
import entidades.cenario.objetoscommovimento.ObjetosComMovimento;
import entidades.inimigos.Inimigo;
import entidades.players.principal.Player;
import main.enums.AcaoPlayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class GerenciadorEntidades {

    private boolean interagiu;

    private List<Entidade> entities;

    private List<Predio> predios;

    private List<ObjetosComMovimento> objetos;

    private List<Inimigo> inimigos;

    private final Random rand = new Random();

    public GerenciadorEntidades() {
        entities = new ArrayList<>();
        predios = new ArrayList<>();
        inimigos = new ArrayList<>();
        objetos = new ArrayList<>();
//        player = new Tai(0, 0);
//        player2 = new Tai(0, 0);
//        player.setPlayerUm();
//        player2.setPlayerDois();
//        entities.add(player);
//        entities.add(player2);
    }

    private boolean interagiu() {
        boolean aux = interagiu;
        interagiu = false;
        return aux;
    }

    public void addEntidade(Entidade entidade) {
        this.entities.add(entidade);
    }

    public void refreshListsSTC(boolean newWorld) {
        entities = new ArrayList<>();
        predios = new ArrayList<>();
        inimigos = new ArrayList<>();
        objetos = new ArrayList<>();
//        if (!newWorld) {
////            player = Configuracao.p1;
////            player2 = Configuracao.p2;
//        } else {
//            player = new Tai(0, 0);
//            player2 = new Tai(0, 0);
//        }
//        player.setPlayerUm();
//        player2.setPlayerDois();
//        player2.setPlayer(player);
//        entities.add(player);
//        entities.add(player2);
    }

    private boolean drawDistance(Entidade e, Player player) {
        return (e.distanciaX(player.getX()) < e.getDrawLimitX() && e.distanciaY(player.getY()) < e.getDrawLimitY()) || e instanceof Player;
    }

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

    public void tick(DadosGame dadosGame) {
        Player player = dadosGame.getPlayer();
        if (player.isDentro()) {
            //renderiza dentro
        }
        List<Entidade> tickDistance = entities.stream().filter(entidade -> drawDistance(entidade, dadosGame.getPlayer())).collect(Collectors.toList());
        for (Entidade e : tickDistance) {
            e.tick(dadosGame);
            avaliarSombreamento(e, dadosGame);
            avaliarColisaoSimples(e, dadosGame);
            avaliarGravidade(e, dadosGame);


//                if (e instanceof ParedeInvisivel) {
//                    if (e.corpoColidindo(player, Arrays.asList("esquerda", "direita"))) {
//                        e.teleportarPlayer(dadosGame);
//                    }
//                }
//                if (e.corpoColidindo(player)) {
//                    verificaOpcoesColisao(e, dadosGame);
//                }
        }
        for (Inimigo inimigo : inimigos) {
            inimigo.tick();
        }

//            for (Entidade e : predios) {
//                e.tick();
//            }
//            if(player.caiu_no_chao) {
//                gerarObj();
//            }
//            for (ObjetosComMovimento objeto : objetos) {
//                objeto.tick();
//            }
    }

    public void render(Graphics g, DadosGame dadosGame) {
//        for (Predio predio : predios) {
//            if (drawDistance(predio)) {
//                predio.render(g);
//            }
//        }
//        for (Enemy enemy : enemies) {
//            if (drawDistance(enemy)) {
//                enemy.render(g);
//            }
//        }
        entities.sort(Entidade.nodeSorter);
        List<Entidade> renderDistance = entities.stream().filter(entidade -> drawDistance(entidade, dadosGame.getPlayer())).collect(Collectors.toList());
        for (Entidade e : renderDistance) {
            e.render(g, dadosGame);
        }

//        for (ObjetosComMovimento objeto : objetos) {
//            objeto.render(g);
//        }
    }


    public void gerarObj(DadosGame dadosGame) {
//        if (Configuracao.local == TipoAmbiente.ESGOTOS) {
//            if (rand.nextInt(50) == 0) {
//                ObjetosComMovimento am = new LixoEsgoto(player.getX() + 500, player.getY() + 58 + rand.nextInt(2) * 32, new Spritesheet("/cenario/cenario.png"));
//                am.setSpeed(rand.nextInt(3));
//                if (objetos.size() < rand.nextInt(10)) {
//                    objetos.add(am);
//                }
//            }
//
//        } else if (Configuracao.local == TipoAmbiente.RUA) {
//            if (Configuracao.dia) {
//                if (rand.nextInt(25) == 0) {
//                    ObjetosComMovimento am = new Transito(player.getX() - 1100, player.getY() + 64 + rand.nextInt(5), new Spritesheet("/cenario/cenario.png"));
//                    am.setSpeed(rand.nextInt(13));
//                    if (objetos.size() < rand.nextInt(3)) {
//                        objetos.add(am);
//                    }
//                }
//            } else {
//                if (rand.nextInt(100) == 0) {
//                    ObjetosComMovimento am = new Transito(player.getX() - 1100, player.getY() + 64 + rand.nextInt(5), new Spritesheet("/cenario/cenario.png"));
//                    am.setSpeed(rand.nextInt(13));
//                    if (objetos.size() < rand.nextInt(2)) {
//                        objetos.add(am);
//                    }
//                }
//            }
//
//        }
//        limparObjetos();
    }

    private void limparObjetos() {
//        for (ObjetosComMovimento objeto : objetos) {
//            if (player.distanciaX(objeto.getX()) > 1200) {
//                objetos.remove(objeto);
//            }
//            if (player.distanciaY(objeto.getY()) > 1200) {
//                objetos.remove(objeto);
//            }
//        }
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
                    .filter(e ->  !(e instanceof Player))
                    .filter(e -> e.buscarColisao(entidade))
                    .findFirst()
                    .orElse(null);
            if (nonNull(target)) {
                entidade.atualizarColisao(target);
            }
        }else {
            entidade.atualizarColisao(dadosGame.getPlayer());
        }
    }

    private void avaliarGravidade(Entidade entidade, DadosGame dadosGame) {
        if (entidade instanceof Player) {
            Plataforma target = (Plataforma) entities
                    .stream()
                    .filter(e -> drawDistance(e, dadosGame.getPlayer()))
                    .filter(e -> e.isClasseRelativa(Plataforma.class))
                    .min(Comparator.comparingInt(e -> e.calculateDistance(entidade)))
                    .orElse(null);

            if (nonNull(target)) {
                entidade.atualizarGravidade(target);
            }
        }else {
//            if(entidade instanceof Bueiro){
//                entidade.atualizarColisao(dadosGame.getPlayer());
//            }
        }
    }

    public void acaoPlayer(AcaoPlayer acaoPlayer, Player player) {
        player.executarAcao(acaoPlayer);
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


    public DadosGame getDadosGame() {
        return new DadosGame();
    }

}
