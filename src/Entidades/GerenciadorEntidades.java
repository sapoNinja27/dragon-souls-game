package Entidades;

import Configuration.Configuracoes;
import Entidades.Cenario.ObjetosComMovimento.LixoEsgoto;
import Entidades.Cenario.ObjetosComMovimento.ObjetosComMovimento;
import Entidades.Cenario.ObjetosComMovimento.Transito;
import Entidades.Cenario.Plataforma;
import Entidades.Cenario.PosteLuz;
import Entidades.Cenario.Predio;
import Entidades.Enemies.Enemy;
import Entidades.Players.Ace;
import Entidades.Players.Player;
import Entidades.Players.Tai;
import Graficos.Spritesheet;
import Main.Game;
import enums.AcaoPlayer;
import enums.TipoAmbiente;
import enums.TipoGame;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GerenciadorEntidades {

    private List<Entidade> entities;

    private List<Predio> predios;

    private List<ObjetosComMovimento> objetos;

    private List<Enemy> enemies;

    private  Player player;

    private Player player2;

    private final Random rand= new Random();


    public void refreshListsSTC(boolean newWorld) {
        entities = new ArrayList<Entidade>();
        predios = new ArrayList<Predio>();
        enemies = new ArrayList<Enemy>();
        objetos = new ArrayList<ObjetosComMovimento>();
        if (!newWorld) {
            player = Configuracoes.p1;
            player2 = Configuracoes.p2;
        } else {
            player = new Tai(0, 0);
            player2 = new Ace(0, 0);
        }
        player.setPlayerUm();
        player2.setPlayerDois();
        entities.add(player);
        entities.add(player2);
    }

    public void iniciarListas() {
        entities = new ArrayList<Entidade>();
        predios = new ArrayList<Predio>();
        enemies = new ArrayList<Enemy>();
        objetos = new ArrayList<ObjetosComMovimento>();
        player = new Tai(0, 0);
        player2 = new Ace(0, 0);
        player.setPlayerUm();
        player2.setPlayerDois();
        entities.add(player);
        entities.add(player2);
    }

    public void tick(){
        if (Configuracoes.estadoGame == TipoGame.NORMAL) {
            for (Entidade e : entities) {
                e.tick();
            }
            for (Enemy enemy : enemies) {
                enemy.tick();
            }
            for (Entidade e : predios) {
                e.tick();
            }
            if(player.caiu_no_chao) {
                gerarObj();
            }
            for (ObjetosComMovimento objeto : objetos) {
                objeto.tick();
            }
        }
    }

    public void trocaPersonagem() {
        Player p = player;
        Player p2 = player2;
        entities.remove(player);
        entities.remove(player2);
        p.right = false;
        p.left = false;
        p.moved = false;
        p2.right = false;
        p2.left = false;
        p2.moved = false;
        player = p2;
        player2 = p;
        player.setHudvisivel(true);
        entities.add(player);
        entities.add(player2);
        player.parado = true;
    }

    public void render(Graphics g){

        for (Predio predio : predios) {
            if(predio.d)
            predio.render(g);
        }
        for (Enemy enemy : enemies) {
            enemy.render(g);
        }
        entities.sort(Entidade.nodeSorter);
        for (Entidade e : entities) {
            if ((e.distanciaX(player.getX()) < 1000 && e.distanciaY(player.getY()) < 500) || e instanceof Player) {
                e.render(g);
            }
        }
        for (ObjetosComMovimento objeto : objetos) {
            objeto.render(g);
        }
    }


    public void gerarObj() {
        if (Configuracoes.local == TipoAmbiente.ESGOTOS) {
            if (rand.nextInt(50) == 0) {
                ObjetosComMovimento am = new LixoEsgoto(player.getX() + 500, player.getY() + 58 + rand.nextInt(2) * 32,new Spritesheet("/cenario/cenario.png"));
                am.setSpeed(rand.nextInt(3));
                if (objetos.size() < rand.nextInt(10)) {
                    objetos.add(am);
                }
            }

        } else if (Configuracoes.local == TipoAmbiente.RUA) {
            if (Configuracoes.dia) {
                if (rand.nextInt(25) == 0) {
                    ObjetosComMovimento am = new Transito(player.getX() - 1100, player.getY() + 64 + rand.nextInt(5),new Spritesheet("/cenario/cenario.png"));
                    am.setSpeed(rand.nextInt(13));
                    if (objetos.size() < rand.nextInt(3)) {
                        objetos.add(am);
                    }
                }
            } else {
                if (rand.nextInt(100) == 0) {
                    ObjetosComMovimento am = new Transito(player.getX() - 1100, player.getY() + 64 + rand.nextInt(5),new Spritesheet("/cenario/cenario.png"));
                    am.setSpeed(rand.nextInt(13));
                    if (objetos.size() < rand.nextInt(2)) {
                        objetos.add(am);
                    }
                }
            }

        }
        limparObjetos();
    }

    private void limparObjetos(){
        for (ObjetosComMovimento objeto: objetos) {
            if(player.distanciaX(objeto.getX())>1200) {
                objetos.remove(objeto);
            }
            if(player.distanciaY(objeto.getY())>1200) {
                objetos.remove(objeto);
            }
        }
    }
    private void avaliarSombreamentoPlayer(){
        for (int i = 0; i < entities.size(); i++) {
            Entidade atual = entities.get(i);
            if (atual instanceof PosteLuz) {
                player.atualizarSombreamento((ObjetoLuminoso) atual);
            }
        }
    }

    public void acaoPlayer(AcaoPlayer acaoPlayer){
        player.executarAcao(isFreeY(), acaoPlayer);
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


    private boolean isFreeY() {
        for (Entidade atual : entities) {
            if (atual instanceof Plataforma) {
                if (player.corpoColidindo(atual)) {
                    return false;
                }

            }
        }
        return true;
    }

}
