package main.processamento;


import main.DadosGame;
import main.Loading;
import main.enums.TipoAcao;
import main.enums.TipoGame;
import main.interfaces.mouse.Mouse;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import static java.awt.event.KeyEvent.*;
import static main.enums.DirecaoPlayer.DIREITA;
import static main.enums.DirecaoPlayer.ESQUERDA;
import static main.enums.MovimentoPlayer.*;

public class GerenciadorEstado {

    private DadosGame dadosGame;
    private final GerenciadorMenu gerenciadorMenu = new GerenciadorMenu();
    private final GerenciadorEntidades gerenciadorEntidades = new GerenciadorEntidades();
    //TODO isso fica dentro de menu
//    private Cutscene cutscene;

    public void iniciar(DadosGame dadosGame) {
        this.dadosGame = dadosGame;
    }

    public void tick(Mouse mouse) {
        if (gerenciadorEntidades.getEntities().isEmpty()) {
            gerenciadorEntidades.getEntities().addAll(dadosGame.getEntities());
        }
        Loading.tick();
        if (!Loading.isLoading()) {
            switch (dadosGame.getEstadoGame()) {
                case CUTSCENE:
//                cutscene.tick();
                    break;
                case NORMAL:
                    gerenciadorEntidades.tick(dadosGame);
                    break;
                case MENU:
                    gerenciadorMenu.tick(dadosGame, mouse);
                    break;
            }
        }
    }

    public void renderBaixo(Graphics g) {
        switch (dadosGame.getEstadoGame()) {
            case CUTSCENE:
//            cutscene.render(g);
                break;
            case NORMAL:
                gerenciadorEntidades.render(g, dadosGame);
                break;
            default:
                break;
        }
    }

    public void renderHD(Graphics g) {
        if (dadosGame.getEstadoGame().equals(TipoGame.MENU)) {
            gerenciadorMenu.render(g, dadosGame);
        }
    }

    private void pressionar(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dadosGame.escPressed();
        }

        if (e.getKeyCode() == KeyEvent.VK_TAB) {
            dadosGame.tabPressed();
        }

        switch (dadosGame.getEstadoGame()) {
            case CUTSCENE:
            case MENU:
                break;
            case NORMAL:
                switch (e.getKeyCode()) {
                    case VK_E:
                        gerenciadorEntidades.interagir();
                        break;
                    case VK_1:
                        dadosGame.getPlayer().getGerenciadorMovimentos().setarAnimacao(HABILIDADE_POSTURA_OFENSIVA);
                        break;
                    case VK_D:
                        dadosGame.getPlayer().atualizarDirecao(DIREITA);
                        dadosGame.getPlayer().getGerenciadorMovimentos().setarAnimacao(ANDANDO);
                        dadosGame.getPlayer().setMoved(true);
                        break;
                    case VK_A:
                        dadosGame.getPlayer().atualizarDirecao(ESQUERDA);
                        dadosGame.getPlayer().getGerenciadorMovimentos().setarAnimacao(ANDANDO);
                        dadosGame.getPlayer().setMoved(true);
                        break;
                    case VK_W:
                        dadosGame.getPlayer().getGerenciadorMovimentos().setarAnimacao(SUBINDO);
                        break;
                    case VK_R:
                        dadosGame.getPlayer().getGerenciadorMovimentos().setarAnimacao(HABILIDADE_POSTURA_SELVAGEM);
                        break;
                    case VK_Q:
                        dadosGame.getPlayer().getGerenciadorMovimentos().setarAnimacao(ATACANDO);
                        break;
                    case VK_H:
                        dadosGame.getPlayer().attHudHelp();
                        break;
                }
                //TODO ajustar
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    dadosGame.saveGame(0);
                }
                break;
        }
    }

    public void soltar(KeyEvent e) {
        switch (dadosGame.getEstadoGame()) {
            case MENU:
            case CUTSCENE:
                break;
            case NORMAL:
                switch (e.getKeyCode()) {
                    case VK_D:
                    case VK_A:
                        dadosGame.getPlayer().getGerenciadorMovimentos().setarAnimacao(PARANDO);
                        dadosGame.getPlayer().setMoved(false);
                        break;
                    case VK_W:
                        if (dadosGame.getPlayer().getGerenciadorMovimentos().noAr()) {
                            dadosGame.getPlayer().getGerenciadorMovimentos().setarAnimacao(CAINDO, 0.3);
                        }
                        break;
                }

//                if (e.getKeyCode() == KeyEvent.VK_UP) {
//
//                }
//			if(e.getKeyCode() == KeyEvent.VK_LEFT){
//				player.camL=false;
//				player.camx=0;
//			}
//			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//				player.camR=false;
//				player.camx=0;
//			}
                break;
        }
    }

    public void gerenciarCliques(KeyEvent e, TipoAcao tipoAcao) {
        if (tipoAcao.equals(TipoAcao.PRESSIONAR)) {
            pressionar(e);
        }
        if (tipoAcao.equals(TipoAcao.SOLTAR)) {
            soltar(e);
        }
    }

    public void render(BufferedImage image, BufferStrategy bs) {
        Graphics g = image.getGraphics();
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, dadosGame.getWidth(), dadosGame.getHeight());
        renderBaixo(g);
        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, dadosGame.getScaleWidth(), dadosGame.getScaleHeight(), null);
        renderHD(g);
        Loading.render(g, dadosGame);
        bs.show();
    }
}
